import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;




public class GamePanel extends JPanel implements KeyListener, MouseListener, ActionListener, MouseMotionListener {
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
    private Point currentMouseLoc;
    private Point mouseDragLoc;
    private ArrayList<String> usedWords;
    private int endX;
    private int endY;
    private Rectangle prev;




    public GamePanel(String name) {
        readData();
        fillBoard();
        prev = new Rectangle();
        gameBoard = new WordBox[4][4];
        try {
            background = ImageIO.read(new File("src\\wordHuntBack.jpg"));
            woodRect = ImageIO.read(new File("src\\woodRect.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        int boxY = 140;
        int textY = 198;
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
        usedWords = new ArrayList<>();
        currentMouseLoc = new Point();
        mouseDragLoc = new Point();
        enclosingFrame = new JFrame(name);
        continueButton = new JButton("continue");
        add(continueButton);
        continueButton.addActionListener(this);
        currentWord = "";
        time = 65;
        points = 0;
        timer = new Timer(1000, this); // this Timer will call the actionPerformed interface method every 1000ms = 1 second
        timer.start();
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true); // this line of code + one below makes this panel active for keylistener events
        requestFocusInWindow(); // see comment above
        endX = 54 + 40;
        endY = 40 + 38;
    }
    public void readData() {
        words = new ArrayList<>();
        try {
            File myFile = new File("src\\words.txt");
            Scanner fileScanner = new Scanner(myFile);
            while (fileScanner.hasNext()) {
                String word = fileScanner.nextLine();
                words.add(word.toUpperCase());
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
        if (time <= 0) {
            continueButton.setLocation(210, 332);
        } else {
            continueButton.setLocation(700, 700);
        }
        g.drawImage(background, 0, 100, null);
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
        if (time > 0) { // need to fix so that if (time - time/60 * 60) is less than 10, you add a 0 because its single digit
            g.drawString("" + 0 + time/60 + ":" + (time - time/60 * 60), 400, 70);
        } else {
            g.drawString("Time's Up!", 280, 40);
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));
        g2.drawLine(currentMouseLoc.x, currentMouseLoc.y, endX, endY);
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
    public void mouseClicked(MouseEvent e) // pressed and released
    { }
    // this method isn't called, so mouseReleased is best




    public void mousePressed(MouseEvent e) { // pressed
        Rectangle mouseClickLocation = new Rectangle((int) e.getPoint().getX(), (int) e.getPoint().getY(), 1, 1);
        for (int r = 0; r < gameBoard.length; r++) {
            for (int c = 0; c < gameBoard[0].length; c++) {
                if (mouseClickLocation.intersects(gameBoard[r][c].getThisRect())) {
                    prev = gameBoard[r][c].getThisRect();
                    currentMouseLoc.setLocation(gameBoard[r][c].getBoxX() + 40, gameBoard[r][c].getBoxY() + 38);
                }
            }
        }
    }




    public void mouseReleased(MouseEvent e) { // released
        if (words.contains(currentWord)) {
            int length = currentWord.length();
            if (!usedWords.contains(currentWord)) {
                if (length == 3) {
                    points += 100;
                    System.out.println("blah");
                } else if (length == 4) {
                    points += 400;
                    System.out.println("blah");
                } else if (length == 5) {
                    points += 800;
                    System.out.println("blah");
                } else if (length >= 6) {
                    points += 1000 + (length - 5) * 400;
                    System.out.println("blah");
                }
                usedWords.add(currentWord);
            }
        }
        currentWord = "";
        for (int r = 0; r < gameBoard.length; r++) {
            for (int c = 0; c < gameBoard[0].length; c++) {
                gameBoard[r][c].switchToNormal();
            }
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
                EndFrame end = new EndFrame("Scoreboard", points);
                enclosingFrame.setVisible(false);
            }
        }
    }




    @Override
    public void mouseDragged(MouseEvent e) {
        if (!(time <= 0)) {
            Rectangle mouseClickLocation = new Rectangle((int) e.getPoint().getX(), (int) e.getPoint().getY(), 1, 1);
            for (int r = 0; r < gameBoard.length; r++) {
                for (int c = 0; c < gameBoard[0].length; c++) {
                    if (mouseClickLocation.intersects(gameBoard[r][c].getThisRect())) {
                        if (!gameBoard[r][c].isSelected()) {
                            endX = gameBoard[r][c].getBoxX() + 40;
                            endY = gameBoard[r][c].getBoxX() + 38;
                            currentWord += gameBoard[r][c].getLetter();
                            System.out.println("added to word");
                            gameBoard[r][c].switchToSelected();
                            prev = gameBoard[r][c].getThisRect();
                        }
                    }
                    currentMouseLoc.setLocation(endX, endY);
                }
            }
        }
    }




    @Override
    public void mouseMoved(MouseEvent e) {}
}








