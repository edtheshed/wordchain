import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordCheckerShould {

    private WordChecker wordChecker;

    @BeforeEach
    void setUp() {
        wordChecker = new WordChecker();
    }

    @Test
    void return_true_when_given_a_valid_word() {
        assertTrue(wordChecker.checkWord("cat"));
    }

    @Test
    void return_false_when_given_an_invalid_word() {
        assertFalse(wordChecker.checkWord("sfsss"));
    }
}
