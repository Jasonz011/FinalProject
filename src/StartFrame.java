import javax.swing.*;

public class StartFrame {
    private StartPanel panel;

    public StartFrame() {
        JFrame frame = new JFrame("Welcome");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(521, 630);
        frame.setLocationRelativeTo(null); // auto-centers frame in screen

        // create and add panel
        panel = new StartPanel(frame);
        frame.add(panel);

        // display the frame
        frame.setVisible(true);

        // no thread needed here since we aren't doing animation for this frame/panel
    }
}
