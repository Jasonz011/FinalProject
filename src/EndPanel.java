import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EndPanel extends JPanel {
    private BufferedImage background;
    private int points;
    public EndPanel(JFrame frame, int points) {
        try {
            background = ImageIO.read(new File("")); //fill in
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.points = points;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // just do this
        g.drawImage(background, 0, 0, null);
        g.setFont(new Font("Courier New", Font.BOLD, 24));
        g.drawString("Points Earned: " + points, 200, 75);
    }
}