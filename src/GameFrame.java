import javax.swing.*;


public class GameFrame implements Runnable {


    private GamePanel panel;


    public GameFrame(String name) {
        JFrame frame = new JFrame("Word Hunt");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(521, 630); // 100X100 per letter box and 20X20 in the margins
        // 4x4 grid, 100x4 + 20x5 = 500, and an extra 60 units of height for the timer
        frame.setLocationRelativeTo(null); // auto-centers frame in screen


        // create and add panel
        panel = new GamePanel(name, frame);
        frame.add(panel);


        // display the frame
        frame.setVisible(true);


        // start thread, required for animation
        Thread thread = new Thread(this);
        thread.start();
    }


    public void run() {
        while (true) {
            panel.repaint();  // we don't ever call "paintComponent" directly, but call this to refresh the panel
        }
    }
}

