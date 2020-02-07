import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class WordReader {

    public static Set<String> getCandidates(int length) {
            Set<String> candidates = new HashSet<>();

        try {
            BufferedReader in = new BufferedReader(new FileReader("/Users/edwardrixon/repos/wordchain/src/main/java/wordlist.txt"));

            String str;
            while ((str = in.readLine()) != null) {
                if (str.length() == length) {
                    candidates.add(str.toLowerCase());
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        return candidates;
    }
}
