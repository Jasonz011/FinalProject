import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GamePanel extends JPanel implements KeyListener, MouseListener, ActionListener {
    private BufferedImage background;
    private BufferedImage woodRect;
    private BufferedImage woodRectSelected;
    private String[][] letterBoard;
    private WordBox[][] gameBoard;
    private ArrayList<String> words;
    private String currentWord;
    private Timer timer;
    private int time;
    private int points;
    private JButton continueButton;
    private JFrame enclosingFrame;

    public GamePanel(String name) {
        readData();
        fillBoard();
        gameBoard = new WordBox[4][4];
        try {
            background = ImageIO.read(new File("src\\wordHuntBack.jpg"));
            woodRect = ImageIO.read(new File("src\\woodRect.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        int boxY = 40;
        int textY = 98;
        for (int r = 0; r<letterBoard.length; r++) {
            int boxX = 54;
            int textX = 73;
            for (int c = 0; c < letterBoard[0].length; c++) {
                gameBoard[r][c] = new WordBox(letterBoard[r][c], boxX, boxY, textX, textY);
                boxX += 105;
                textX += 105;
            }
            boxY += 112;
            textY += 112;
        }
        enclosingFrame = new JFrame(name);
        continueButton = new JButton("continue");
        continueButton.addActionListener(this);
        currentWord = "";
        time = 5;
        points = 0;
        timer = new Timer(1000, this); // this Timer will call the actionPerformed interface method every 1000ms = 1 second
        timer.start();
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true); // this line of code + one below makes this panel active for keylistener events
        requestFocusInWindow(); // see comment above
    }
    public void readData() {
        words = new ArrayList<>();
        try {
            File myFile = new File("src\\words.txt");
            Scanner fileScanner = new Scanner(myFile);
            while (fileScanner.hasNext()) {
                String word = fileScanner.nextLine();
                words.add(word);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void fillBoard() {
        GameLogic g = new GameLogic();
        g.fillArray();
        letterBoard = g.getLetters();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // just do this
        continueButton.setLocation(400, 400);
        g.drawImage(background, 0, 0, null);
        g.setFont(new Font("Comic Sans", Font.BOLD, 60));
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                g.drawImage(gameBoard[i][j].getCurrentImg(), gameBoard[i][j].getBoxX(), gameBoard[i][j].getBoxY(), null);
                if (gameBoard[i][j].getLetter().equals("I")) {
                    g.drawString(gameBoard[i][j].getLetter(), gameBoard[i][j].getTextX() + 12, gameBoard[i][j].getTextY());
                } else if (gameBoard[i][j].getLetter().equals("W") || gameBoard[i][j].getLetter().equals("M")) {
                    g.drawString(gameBoard[i][j].getLetter(), gameBoard[i][j].getTextX() - 6, gameBoard[i][j].getTextY());
                } else {
                    g.drawString(gameBoard[i][j].getLetter(), gameBoard[i][j].getTextX(), gameBoard[i][j].getTextY());
                }
            }
        }



        g.setFont(new Font("Courier New", Font.BOLD, 24));
        if (time > 0) {
            g.drawString("Time: " + time, 20, 70);
        } else {
            g.drawString("Time's Up!", 280, 40);
        }
    }

    // ----- KeyListener interface methods -----
    public void keyTyped(KeyEvent e) { } // unimplemented

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
    }

    // ----- MouseListener interface methods -----
    public void mouseClicked(MouseEvent e) { }
    // this method isn't called, so mouseReleased is best

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {  // left mouse click
            Point mouseClickLocation = e.getPoint();
            // if statement about the rectangle of the word touching the mouse cursor
            // add the letter of the rectangle its touching to currentWord
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {  // left mouse click
            if (words.contains(currentWord)) {
                int length = currentWord.length();
                if (length == 3) {
                    points += 100;
                } else if (length == 4) {
                    points += 400;
                } else if (length == 5) {
                    points += 800;
                } else if (length >= 6) {
                    points += 1000 + (length-5) * 400;
                }
            }
            currentWord = "";
        }
    }

    public void mouseEntered(MouseEvent e) { } // unimplemented

    public void mouseExited(MouseEvent e) { } // unimplemented

    // ACTIONLISTENER INTERFACE METHODS: used for buttons AND timers!
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Timer) {
            time--;
        } else if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button == continueButton) {
                EndFrame end = new EndFrame("Scoreboard");
                enclosingFrame.setVisible(false);
            }
        }
    }
}