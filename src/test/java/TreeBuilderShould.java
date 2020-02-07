import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TreeBuilderShould {

    @Test
    void generate_list_of_words_with_start_and_end_words_in_it() {
        String start = "cat";
        String end = "dog";
        Set<String> listOfCandidates = WordReader.getCandidates(start.length());

        var tree = TreeBuilder.getTree(start, end, listOfCandidates);

        assertTrue(tree.containsKey("cat"));
        assertTrue(tree.containsKey("dog"));
        assertTrue(tree.containsKey("pat"));
        assertTrue(tree.containsKey("put"));
    }
}
