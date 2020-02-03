import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordChecker {

    public WordChecker() {

    }

    public boolean checkWord(String word) {
        try {

            BufferedReader in = new BufferedReader(new FileReader("/Users/codurance/Documents/repos/exercises/wordChain/src/main/java/wordlist.txt"));

            String str;
            while ((str = in.readLine()) != null) {
                if (str.equals(word)) {
                    return true;
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        return false;
    }
}
