import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class WordChain {
    private final String start;
    private final String end;
    private final Set<String> candidates;
    private final ConcurrentHashMap<String, Set<String>> tree;

    public WordChain(String start, String end) {
//        if start and end is not in wordList, then throw
        this.start = start;
        this.end = end;
        candidates = WordReader.getCandidates(start.length());
        tree = TreeBuilder.getTree(start, end, candidates);
    }

    public List<String> getChain(String start, String current) {
        //foreach key in tree
        //if key == endWord
        //save key as endWord for next iteration
//        if (path.contains(start)) return path;
//        else {
//            String found = current;
//            for (String key : tree.keySet()) {
//                if (tree.get(key).contains(current)){
//                    found = key;
//                    path.add(key);
//                }
//            }
//            return getChain(start, found, path);
//        }


        if (start.equals(end)) {
            return new ArrayList<>();
        }
        LinkedList<List<String>> queue = new LinkedList<>();
        List<String> visited = new ArrayList<>();
        List<String> root = new ArrayList<>();
        visited.add(start);
        root.add(start);
        queue.add(root);

        while (!queue.isEmpty()) {
            //path = first item from queue
            List<String> path = queue.pop();
            String node = path.get(path.size() - 1);
            if (!visited.contains(node)) {
                Set<String> neighbours = tree.get(node);
                for (String neighbour : neighbours) {
                    List<String> new_path = path;
                    new_path.add(neighbour);
                    queue.add(new_path);
                    if (neighbour.equals(end)) {
                        return new_path;
                    }
                }

            }
        }
        throw new IllegalArgumentException();

    }

    public Set<String> getCandidates() {
        return candidates;
    }


}
