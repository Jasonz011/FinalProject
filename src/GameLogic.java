public class GameLogic {
    private String[][] letters;

    public GameLogic() {
        letters = new String[4][4];
    }

    public void fillArray() {
        String[] vowels = {"a", "e", "i", "o", "u"}; // 40% chance for a vowel
        String[] rareLetters = {"j", "q", "z", "x"}; // 1% chance for a letter that doesn't occur often
        String[] consonants = {"b","c","d","f","g","h","k","l","m","n","p","r","s","t","v","w","y"}; // 52% chance for the rest
        for (int r = 0; r<letters.length; r++) {
            for (int c = 0; c<letters[0].length; c++) {
                // decides whether to put a vowel or consonant at that position of the 2D array
                int rand = (int) (Math.random() * 100 + 1);
                if (rand <= 40) {
                    letters[r][c] = vowels[(int) (Math.random() * 5)].toUpperCase();
                } else if (rand <= 41) {
                    letters[r][c] = rareLetters[(int) (Math.random() * 4)].toUpperCase();
                } else {
                    letters[r][c] = consonants[(int) (Math.random() * 17)].toUpperCase();
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
