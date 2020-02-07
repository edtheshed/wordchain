import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordChainShould {

    private String start;
    private String end;
    private WordChain wordChain;

    @BeforeEach
    void setUp() {
        start = "cat";
        end = "dog";
        wordChain = new WordChain(start, end);
    }

    @Test
    void convert_start_word_to_end_word() {

        List<String> attempt = wordChain.getChain(start, end);

        assertEquals(end, attempt.get(attempt.size()));
    }

    @Test
    void have_a_list_of_words_that_are_the_same_length_as_the_keywords() {
        assertTrue(wordChain.getCandidates().stream().allMatch(s -> s.length() == start.length()));
    }
}
