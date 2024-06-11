import java.util.ArrayList;

public class GameLogic {
    private String[][] letters;

    public GameLogic() {
        letters = new String[4][4];
    }

    public void fillArray() {
        for (int i = 0; i < letters.length; i++) {
            for (int j = 0; j < letters[0].length; j++) {
                String letter = getLetter(Math.random() * 100);
                letters[i][j] = letter;
            }
        }
    }

    public String getLetter(double value) {
        if (value < 11.1607) return "E";
        else if (value < 19.6573) return "A";
        else if (value < 27.2382) return "R";
        else if (value < 34.783) return "I";
        else if (value < 41.9465) return "O";
        else if (value < 48.8974) return "T";
        else if (value < 55.5518) return "N";
        else if (value < 61.2869) return "S";
        else if (value < 66.7762) return "L";
        else if (value < 71.315) return "C";
        else if (value < 74.9458) return "U";
        else if (value < 78.3302) return "D";
        else if (value < 81.4973) return "P";
        else if (value < 84.5102) return "M";
        else if (value < 87.5136) return "H";
        else if (value < 89.9841) return "G";
        else if (value < 92.0561) return "B";
        else if (value < 93.8682) return "F";
        else if (value < 95.6461) return "Y";
        else if (value < 96.936) return "W";
        else if (value < 98.0376) return "K";
        else if (value < 99.045) return "V";
        else if (value < 99.3352) return "X";
        else if (value < 99.6074) return "Z";
        else if (value < 99.8039) return "J";
        else return "Q";
    }


    public String[][] getLetters() {
        return letters;
    }
}

//  https://www3.nd.edu/~busiforc/handouts/cryptography/letterfrequencies.html
