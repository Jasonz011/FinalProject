public class GameLogic {
    private String[][] letters;

    public GameLogic() {
        letters = new String[4][4];
    }

    public void fillArray() {
        String[] consonants = {"b","c","d","f","g","h", "j","k","l","m","n","p","q","r","s","t","v","w","x","y","z"};
        String[] vowels = {"a", "e", "i", "o", "u"};
        for (int r = 0; r<letters.length; r++) {
            for (int c = 0; c<letters[0].length; c++) {
                // decides whether to put a vowel or consonant at that position of the 2D array
                int rand = (int) (Math.random() * 100 + 1);
                // for some reason this doesn't work as intended, instead the whole array is filled with null elements
                if (rand <= 40) {
                    letters[r][c] = vowels[(int) (Math.random() * 5)];
                } else {
                    letters[r][c] = consonants[(int) (Math.random() * 21)];
                }
            }
        }
    }

    public String[][] getLetters() {
        return letters;
    }

    public void printLetters() {
        for (int r = 0; r<letters.length; r++) {
            for (int c = 0; c < letters[0].length; c++) {
                System.out.print(letters[r][c] + " ");
            }
            System.out.println();
        }
    }
}
