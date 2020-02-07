import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TreeBuilder {
    public static ConcurrentHashMap<String, Set<String>> getTree(String start, String end, Set<String> listOfCandidates) {
        ConcurrentHashMap<String, Set<String>> tree = new ConcurrentHashMap<>();

        // We start the tree with the root node
        Set<String> matches = getChildWords(start, listOfCandidates);
        tree.put(start, matches);

        //for each edge in root, we get its child nodes
        for (String word : matches) {
            tree.put(word, getChildWords(word, listOfCandidates));
        }

        //while tree !contain end
        //call new function and pass tree
        do {
            getSubsequentChildWords(tree, listOfCandidates);
        } while (!tree.containsKey(end));


        return tree;

    }

    private static void getSubsequentChildWords(ConcurrentHashMap<String, Set<String>> tree, Set<String> listOfCandidates) {

        for (String key: tree.keySet()){
            for (String val: tree.get(key)){
                tree.put(val, getChildWords(val, listOfCandidates));
            }
        }
    }

    private static Set<String> getChildWords(String start, Set<String> listOfCandidates) {
        List<String> matchers = new ArrayList<>();

        for (int i = 0; i < start.length(); i++) {
            String match = start.substring(0, i) + "\\w" + start.substring(i + 1);

            matchers.add(match);
        }

        Set<String> matches = new HashSet<>();
        for (String match : matchers) {

            matches.addAll(listOfCandidates.stream().filter(s -> s.matches(match)).collect(Collectors.toList()));
        }
        return matches;
    }


}
