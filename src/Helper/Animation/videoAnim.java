package Helper.Animation;

import javax.swing.*;
import java.awt.*;

import javafx.scene.media.*;

/**
 * Class that used to apply animation on a video when displayed. The 
 * animation is a smooth transition from one position to another.
 * 
 * <p>
 * 
 * This class also arrange the video visibility on when to be visible. The 
 * video will only display when it enters the viewport, and will be hidden 
 * when it leaves the viewport.
 * 
 * <p>
 * 
 * To apply the animation on a video, simply create an instance of this 
 * class will do, and call the {@link #start} method to start the animation if the 
 * video is meant to be visible upon initialization.
 * 
 * @author yappy-yum
 * 
 */
public class videoAnim {
    private Timer animationTimer;
    private Component videoPanel;
    private int startY, targetY;
    private long animationStartTime;
    private int duration;
    private JScrollPane scrollPane;
    private boolean isAnimating = false;
    private Runnable onComplete;

    public videoAnim(
        Component component, 
        int startX, int startY, 
        int targetX, int targetY, 
        int duration, 
        JScrollPane scrollPane, 
        Runnable onComplete
    ) {
        this.videoPanel = component;
        this.startY = startY;
        this.targetY = targetY;
        this.duration = duration;
        this.scrollPane = scrollPane;
        this.onComplete = onComplete;

        videoPanel.setVisible(false); // Hide initially
        setupAnimation();
    }

    private void setupAnimation() {
        animationTimer = new Timer(4, _ -> {
            long elapsed = System.currentTimeMillis() - animationStartTime;
            if (elapsed < duration) {
                double progress = (double) elapsed / duration;
                int currentY = (int) (startY + (targetY - startY) * progress);
                videoPanel.setLocation(videoPanel.getX(), currentY);
                videoPanel.getParent().repaint();
            } else {
                animationTimer.stop();
                isAnimating = false;
                videoPanel.setLocation(videoPanel.getX(), targetY);
                videoPanel.getParent().repaint();
                SwingUtilities.invokeLater(onComplete);
            }
        });
    }

    public void start() {
        if (!isAnimating) {
            animationStartTime = System.currentTimeMillis();
            videoPanel.setVisible(true);
            animationTimer.start();
            isAnimating = true;
        }
    }

    public void exit(MediaPlayer player) {
        if (videoPanel.isVisible()) {
    
            videoPanel.setVisible(false); // Hide the video panel
    
            if (player == null) {
                System.out.println("❌ exit(): MediaPlayer is null. Cannot reset.");
                return;
            }
    
            // Always try to stop and reset
            player.stop(); // Stops and resets the video

        } else {
            System.out.println("exit(): Video panel already hidden.");
        }
    }
            

    public boolean isFullyVisible() {
        JViewport viewport = scrollPane.getViewport();
        Rectangle viewportBounds = viewport.getViewRect();
        Rectangle compBounds = videoPanel.getBounds();
        return viewportBounds.intersects(compBounds);
    }

    /**
     * Dispose of this video animation instance.
     * This method cleans up resources used by the animation (e.g., timer).
     */
    public void dispose() {
        // Stop the animation timer if it's still running
        if (animationTimer != null) {
            animationTimer.stop();
        }

        // Clear references to avoid memory leaks
        videoPanel = null;
        scrollPane = null;
        onComplete = null;

    }
        
}