import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordChainGame {

    private final String start;
    private String progress;
    private final String end;
    private int iterations;
    private int totalIterations;
    private Random random;
    private boolean failed;
    private String chars = "abcdefghijklmnopqrstuvwxyz";
    private List<String> wordHistory;
    private WordChecker wordChecker;

    public WordChainGame(String start, String end) {
        if (start.length() != end.length())
            throw new IllegalArgumentException();

        this.wordChecker = new WordChecker();

        if (!wordIsValid(start) || !wordIsValid(end))
            throw new IllegalArgumentException();

        this.start = start;
        this.progress = start;
        this.end = end;
        this.iterations = 0;
        this.totalIterations = 0;
        random = new Random();
        this.failed = false;
        this.wordHistory = new ArrayList<>();
    }


    public void next() {

        char[] progressArray = progress.toCharArray();
        char[] endArray = end.toCharArray();
        iterations++;
        int firstTry = 0;
        int randomTries = 0;

        for (int index = 0; index < start.length(); index++) {

            int letterIndex = index;

            if (failed) {
                letterIndex = setRandomLetterToChange();
            }

            if (progressArray[letterIndex] != endArray[letterIndex] && !failed){
                setLetter(progressArray, letterIndex, endArray[letterIndex]);
            }
            else if (failed){
                generateRandomResult(progressArray, letterIndex, randomTries);
            }
            else {
                continue;
            }

            String potentialWord = String.valueOf(progressArray);

            if (wordIsValid(potentialWord)) {

                if (wordHistory.contains(potentialWord)){
                    continue;
                }

                progress = potentialWord;
                wordHistory.add(progress);
                totalIterations++;
                System.out.println(progress);
                return;
            }
            else {
                firstTry++;
                progressArray = progress.toCharArray();
                if (firstTry == start.length())
                    failed = true;
            }
        }

        if (iterations > 40){
            iterations = 0;
            failed = true;
        }
    }

    private void setLetter(char[] progressArray, int letterIndex, char c) {
        progressArray[letterIndex] = c;
    }

    private int setRandomLetterToChange() {
        int letterIndex;
        letterIndex = random.nextInt(start.length());
        return letterIndex;
    }

    private void generateRandomResult(char[] progressArray, int letterIndex, int randomTries) {
        char c = chars.charAt(random.nextInt(chars.length()));
        if (progressArray[letterIndex] != c){
            setLetter(progressArray, letterIndex, c);
            randomTries++;
        }

        if (randomTries > 3)
            failed = false;
    }

    private boolean wordIsValid(String word) {
        return wordChecker.checkWord(word);
    }

    public String getProgress() {
        return progress;
    }

    public String calculate() {
        while (!progress.equals(end)){
            next();
        }

        return progress;
    }

    public int getIterations() {
        return totalIterations;
    }
}
