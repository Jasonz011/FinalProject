import javax.swing.*;

public class EndFrame {
    private EndPanel endPanel;

    public EndFrame(String name, int points, String pName, int words) {
        JFrame frame = new JFrame("Scoreboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 470);
        frame.setLocationRelativeTo(null); // auto-centers frame in screen

        // create and add panel
        endPanel = new EndPanel(pName, points, words);
        frame.add(endPanel);

        // display the frame
        frame.setVisible(true);

        // no thread needed here since we aren't doing animation for this frame/panel
    }
}
