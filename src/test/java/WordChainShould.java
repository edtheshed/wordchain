import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordChainShould {

    @Test
    void change_one_letter_of_start_word_to_a_letter_in_end_word() {
        String start = "cat";
        String end = "dog";
        char[] endChars = end.toCharArray();

        WordChainGame game = new WordChainGame(start, end);

        game.next();

        char[] progressChars = game.getProgress().toCharArray();

        int matchingLetter = 0;

        for (int letterIndex = 0; letterIndex < end.length(); letterIndex++) {
            if(progressChars[letterIndex] == endChars[letterIndex])
                matchingLetter++;
        }

        assertTrue(matchingLetter == 1);
    }

    @Test
    void change_whole_word_to_end_word() {
        String start = "blaze";
        String end = "churn";
        char[] endChars = end.toCharArray();

        WordChainGame game = new WordChainGame(start, end);

        assertEquals(end, game.calculate());
        System.out.println(game.getIterations());
    }
}
