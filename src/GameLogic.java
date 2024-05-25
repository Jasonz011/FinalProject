public class GameLogic {
    private String[][] letters;
//    private boolean validArray;

    public GameLogic() {
        letters = new String[4][4];
    }

    public void fillArray() {
        String[] vowels = {"a", "e", "i", "o", "u"}; // 32% chance for a vowel
        String[] rareLetters = {"j", "q", "z", "x"}; // 4% chance for a letter that doesn't occur often
        String[] consonants = {"b","c","d","f","g","h","k","l","m","n","p","r","s","t","v","w","y"}; // 52% chance for the rest
        for (int r = 0; r<letters.length; r++) {
            for (int c = 0; c<letters[0].length; c++) {
                // decides whether to put a vowel or consonant at that position of the 2D array
                int rand = (int) (Math.random() * 100 + 1);
                // for some reason this doesn't work as intended, instead the whole array is filled with null elements
                if (rand <= 32) {
                    letters[r][c] = vowels[(int) (Math.random() * 5)].toUpperCase();
                } else if (rand <= 36) {
                    letters[r][c] = rareLetters[(int) (Math.random() * 4)].toUpperCase();
                } else {
                    letters[r][c] = consonants[(int) (Math.random() * 17)].toUpperCase();
                }
            }
        }
    }

//    //method to check if the wordArray has enough eligible words, up to later decision
//    public void checkArray() {
//        while (!validArray) {
//            fillArray();
//            // if
//        }
//        //else
//    }

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
