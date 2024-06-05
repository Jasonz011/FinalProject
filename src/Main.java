import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // test code
        Scanner scan = new Scanner(System.in);
        String option = scan.nextLine();
        if (option.equals("1")) {
            StartFrame startFrame = new StartFrame();
            System.out.println();
        } else if (option.equals("2")) {
            GameFrame gameFrame = new GameFrame("Eric");
        } else {
            EndFrame endFrame = new EndFrame("End Screen,", 100, "Eric", 10);
        }

        /* after finished all tests
        StartFrame startFrame = new StartFrame();
        */
    }
}
