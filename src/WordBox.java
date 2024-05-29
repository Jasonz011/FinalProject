import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;

public class WordBox {
    private String letter;

    private int boxX;
    private int boxY;
    private int textX;
    private int textY;
    private BufferedImage woodRect;
    private BufferedImage woodRectSelected;
    private BufferedImage currentImg;
    private Rectangle thisRect;

    public WordBox(String letter, int boxX, int boxY, int textX, int textY) {
        this.letter = letter;
        this.boxX = boxX;
        this.boxY = boxY;
        this.textX = textX;
        this.textY = textY;
        thisRect = new Rectangle(boxX, boxY, 80, 76);
        try {
            woodRect = ImageIO.read(new File("src\\woodRect.png"));
            woodRectSelected = ImageIO.read(new File("src\\woodRectSelected.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        currentImg = woodRect;
    }

    public int getBoxX() {
        return boxX;
    }

    public int getBoxY() {
        return boxY;
    }

    public int getTextX() {
        return textX;
    }

    public int getTextY() {
        return textY;
    }
    public void switchRect() {
        if (currentImg.equals(woodRect)) {
            currentImg = woodRectSelected;
        } else {
            currentImg = woodRect;
        }
    }
    public Rectangle getThisRect() {
        return thisRect;
    }

    public String getLetter() {
        return letter;
    }

    public BufferedImage getCurrentImg() {
        return currentImg;
    }
}
