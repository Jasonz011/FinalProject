import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartPanel extends JPanel implements ActionListener {

    private JTextField textField;
    private JButton submitButton;
    private JButton clearButton;
    private JFrame enclosingFrame;
    private BufferedImage background;

    public StartPanel(JFrame frame) {
        enclosingFrame = frame;
        try {
            background = ImageIO.read(new File("src\\startBack.jpg"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        textField = new JTextField(10);
        submitButton = new JButton("Submit");
        clearButton = new JButton("Clear");
        add(textField);  // textField doesn't need a listener since nothing needs to happen when we type in text
        add(submitButton);
        add(clearButton);
        submitButton.addActionListener(this);
        clearButton.addActionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, -100, null);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.RED);
        g.drawString("Please enter your name:", 50, 30);
        textField.setLocation(50, 50);
        submitButton.setLocation(50, 100);
        clearButton.setLocation(150, 100);
    }

    // ACTIONLISTENER INTERFACE METHODS
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button == submitButton) {
                String playerName = textField.getText();
                GameFrame f = new GameFrame(playerName);
                enclosingFrame.setVisible(false);
            } else {
                textField.setText("");
            }
        }
    }
}
