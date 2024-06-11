import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JLabel;
import java.io.FileNotFoundException;

public class EndPanel extends JPanel {
    private BufferedImage background;
    private File playerData;
    private int words;
    private int points;
    private JLabel playerLabel;
    private ArrayList<Player> players;
    public EndPanel(String name, int points, int words) {
        try {
            background = ImageIO.read(new File("src\\newEndScreen.png"));
            playerData = new File("src\\playerData.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        players = new ArrayList<>();
        this.points = points;
        this.words = words;
        players.add(new Player(name, points, 1));
        setPlayerData();
        sortPlayerData();
        addData();
        playerLabel = new JLabel(name, SwingConstants.CENTER);
        playerLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
        add(playerLabel);
        revalidate();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // just do this
        g.drawImage(background, 0, 0, null);
        g.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        g.drawString("Words: " + words, 100, 60);
        g.drawString("Score: " + points, 100, 80);

        // if statements cause blank screen to be printed for some reason
        int y = 100;
        if (players.size() > 5) {
            for (int i = players.size()-1; i>players.size()-6; i--) {
                g.drawString(players.get(i).getName(), 70, y);
                g.drawString(String.valueOf(players.get(i).getScore()), 200, y);
                y += 20;
            }
        } else {
            for (int i = players.size()-1; i>-1; i--) {
                g.drawString(players.get(i).getName(), 70, y);
                g.drawString(String.valueOf(players.get(i).getScore()), 200, y);
                y += 20;
            }
        }
    }

    public void setPlayerData() {
        try {
            File myFile = new File("src\\playerData.txt");
            Scanner fileScanner = new Scanner(myFile);
            int i = 2;
            while (fileScanner.hasNext()) {
                String word = fileScanner.nextLine();
                String[] currentData = word.split("\\|");
                players.add(new Player(currentData[0], Integer.parseInt(currentData[1]), i));
                i++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sortPlayerData() {
        if (players != null) {
            players.sort(new CustomComparator());
        }
    }

    private void addData() {
        if (players != null) {
            try (PrintWriter p = new PrintWriter(playerData)) {
                for (int i = 0; i<players.size(); i++) {
                    p.println(players.get(i));
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}


/* sources
https://stackoverflow.com/questions/19506769/how-to-center-jlabel-in-jframe-swing for centering label
https://docs.oracle.com/javase%2F8%2Fdocs%2Fapi%2F%2F/java/awt/Dimension.html for the dimension class
https://docs.oracle.com/javase%2F8%2Fdocs%2Fapi%2F%2F/javax/swing/JLabel.html for setting the size of the label
https://docs.oracle.com/javase%2F8%2Fdocs%2Fapi%2F%2F/java/util/List.html#sort-java.util.Comparator- for sorting with comparator
https://docs.oracle.com/javase/8/docs/api/ for printwriter
 */