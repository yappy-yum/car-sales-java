package Helper;

import java.awt.AWTException;
import java.awt.Container;
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
 * <p> to apply the blur effect, simply create an instance of this class will do
 * <p> to remove the blur effect, simply call {@link #removeBlur} will do
 * 
 * @author yappy-yum
 * 
 */
public class blur {

    /**
     * The number of times the blur filter is applied to the captured image.
     * 
     * <p>
     * Each time the blur filter is applied, the image becomes slightly more blurry.
     * The more iterations we run, the stronger and smoother the blur effect appears.
     * 
     * <p>
     * In general:
     * <ul>
     *   <li>A low number (e.g., 5 or 10) gives a soft blur.</li>
     *   <li>A higher number (e.g., 30 or more) makes the blur more intense, like frosted glass.</li>
     * </ul>
     * 
     * <p>
     * This value will be used inside the {@link #blurImage} method when applying the Gaussian blur effect.
     * 
     */
    private final int ITERATION = 30;    

    private JFrame frame;
    private Container oriContent;

    /**
     * constructor to directly initialize the blur effects on the whole window
     * 
     * @param frame The frame to apply the blur effect on.
     * 
     */
    public blur(JFrame frame) {
        this.frame = frame;
        this.oriContent = frame.getContentPane();
        applyBlur();
    }

    /**
     * Captures the visible part of the frame and applies a blur effect to it.
     * 
     * <p> Here’s how this works step-by-step:
     * <ol>
     *   <li>We use the {@code Robot} class to take a screenshot of the frame's content.</li>
     *   <li>We calculate the area to capture by removing the window borders (called insets).</li>
     *   <li>We apply a Gaussian blur filter multiple times on the captured image.</li>
     *   <li>We create a JLabel that displays this blurred image.</li>
     *   <li>We set this label as the new content of the frame (replacing the original UI temporarily).</li>
     *   <li>Finally, we refresh (repaint) the frame so that the changes appear instantly.</li>
     * </ol>
     * 
     * Note: The original content is stored so it can be restored later using {@link #removeBlur}.
     */    
    private void applyBlur() {
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

            BufferedImage blurredImage = blurImage(capturedImage);

            JLabel label = new JLabel(new ImageIcon(blurredImage));
            frame.setContentPane(label);
            frame.revalidate();
            frame.repaint();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * Applies a Gaussian blur filter to a given image multiple times.
     * 
     * <p>
     * Gaussian blur is a smoothing technique used to make an image look "out of focus."
     * 
     * In this method:
     * <ul>
     *   <li>We define a 3x3 matrix (called a kernel) that represents the blur pattern.</li>
     *   <li>We use the {@code ConvolveOp} class to apply the blur effect using this kernel.</li>
     *   <li>We apply the blur operation several times (defined by {@link #ITERATION}) 
     *       to make the blur stronger.</li>
     * </ul>
     * 
     * @param image The original image to blur (a screenshot of the window).
     * @return A blurred version of the image.
     * 
     */    
    private BufferedImage blurImage(BufferedImage image) {
        float[] gaussianMatrix = {
            1f / 16f, 2f / 16f, 1f / 16f,
            2f / 16f, 4f / 16f, 2f / 16f,
            1f / 16f, 2f / 16f, 1f / 16f
        };
        Kernel kernel = new Kernel(3, 3, gaussianMatrix);
        ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);

        BufferedImage output = image;
        for (int i = 0; i < ITERATION; i++) {
            output = op.filter(output, null);
        }
        return output;
    }

    /**
     * Restores the original content pane, removing the blur effect.
     * 
     * <p>
     * This method sets the JFrame’s content back to what it was before the blur effect.
     * Useful when the blur effect is used temporarily and needs to be cleared.
     * 
     */    
    public void removeBlur() {
        frame.setContentPane(oriContent);
        frame.revalidate();
        frame.repaint();

        oriContent = null;
    }
}
