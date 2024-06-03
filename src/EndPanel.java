import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JLabel;

public class EndPanel extends JPanel {
    private BufferedImage background;
    private int points;
    private JLabel playerName;
    public EndPanel(String name, JFrame frame, int points) {
        try {
            background = ImageIO.read(new File("")); //fill in
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.points = points;
        playerName = new JLabel(name, SwingConstants.CENTER);
        playerName.setPreferredSize(new Dimension(200, 80));
        add(playerName);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // just do this
        g.drawImage(background, 0, 0, null);
        g.setFont(new Font("Courier New", Font.BOLD, 24));
        g.drawString("Points Earned: " + points, 200, 75);
    }
}


/* sources
https://stackoverflow.com/questions/19506769/how-to-center-jlabel-in-jframe-swing for centering label

https://docs.oracle.com/javase%2F8%2Fdocs%2Fapi%2F%2F/java/awt/Dimension.html for the dimension class
https://docs.oracle.com/javase%2F8%2Fdocs%2Fapi%2F%2F/javax/swing/JLabel.html for setting the size of the label
 */