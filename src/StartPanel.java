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
        add(textField);  // textField doesn't need a listener since nothing needs to happen when we type in text
        add(submitButton);
        submitButton.addActionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, -100, null);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.RED);
        textField.setLocation(170, 457);
        submitButton.setLocation(310, 457);
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
