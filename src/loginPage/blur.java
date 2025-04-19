package loginPage;

import java.awt.AWTException;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * a simple class that dedicated to apply a blur effect on the whole window
 * 
 * <p>
 * 
 * to apply the blur effect, simply create an instance of this class will do
 * 
 * @author yappy-yum
 * 
 */
public class blur {
    public blur(JFrame frame) {
        applyBlur(frame);
    }

    private void applyBlur(JFrame frame) {
        try {
            Robot robot = new Robot();
            Insets insets = frame.getInsets();
            Rectangle captureRect = new Rectangle(
                frame.getX() + insets.left,
                frame.getY() + insets.top,
                frame.getWidth() - insets.left - insets.right,
                frame.getHeight() - insets.top - insets.bottom
            );
            BufferedImage capturedImage = robot.createScreenCapture(captureRect);

            BufferedImage blurredImage = blurImage(capturedImage, 30);

            JLabel label = new JLabel(new ImageIcon(blurredImage));
            frame.setContentPane(label);
            frame.revalidate();
            frame.repaint();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage blurImage(BufferedImage image, int iterations) {
        float[] gaussianMatrix = {
            1f / 16f, 2f / 16f, 1f / 16f,
            2f / 16f, 4f / 16f, 2f / 16f,
            1f / 16f, 2f / 16f, 1f / 16f
        };
        Kernel kernel = new Kernel(3, 3, gaussianMatrix);
        ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);

        BufferedImage output = image;
        for (int i = 0; i < iterations; i++) {
            output = op.filter(output, null);
        }
        return output;
    }
}
