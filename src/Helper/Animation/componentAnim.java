package Helper.Animation;
import javax.swing.*;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;

/**
 * Class that used to apply animation on a component when displayed. The 
 * animation is a smooth transition from one position to another.
 * 
 * <p>
 * 
 * This class also arrange the component visibility on when to be visible. The 
 * component will only display when it enters the viewport, and will be hidden 
 * when it leaves the viewport.
 * 
 * <p>
 * 
 * To apply the animation on a component, simply create an instance of this 
 * class will do, and call the {@link #start} method to start the animation if the 
 * component is meant to be visible upon initialization.
 * 
 * @author yappy-yum
 * 
 */
public class componentAnim {
    private Timer animationTimer;
    private Component targetComponent;
    private int startX, startY;
    private int targetX, targetY;
    private long animationStartTime;
    private int duration = 500;
    private boolean isAnimating = false;
    private boolean isExiting = false;
    private JScrollPane scrollPane;
    private boolean lastState = false;
    private ChangeListener viewportListener;
    private Runnable onComplete; // 🔥 Add this field to store callback


    public componentAnim(Component component, int startX, int startY, int targetX, int targetY, JScrollPane scrollPane) {
        this.targetComponent = component;
        this.startX = startX;
        this.startY = startY;
        this.targetX = targetX;
        this.targetY = targetY;
        this.scrollPane = scrollPane;
        
        targetComponent.setVisible(false);
        setupAnimation();
        setupViewportListener();

        // Check immediately if component is already visible on startup
        SwingUtilities.invokeLater(() -> {
            if (isFullyVisible(targetComponent)) {
                start(() -> {});  // Start animation immediately
            }
        });        
    }

    private void setupAnimation() {
        animationTimer = new Timer(4, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long elapsed = System.currentTimeMillis() - animationStartTime;
                if (elapsed < duration) {
                    double progress = (double) elapsed / duration;
                    if (isExiting) {
                        progress = 1 - progress;
                    } else {
                        progress = 1 - Math.pow(1 - progress, 3);
                    }
                    int currentX = (int) (startX + (targetX - startX) * progress);
                    int currentY = (int) (startY + (targetY - startY) * progress);
                    targetComponent.setLocation(currentX, currentY);

                    targetComponent.getParent().repaint();
                } else {
                    animationTimer.stop(); // ✅ Stop the timer properly
                    isAnimating = false;
    
                    if (isExiting) {
                        targetComponent.setVisible(false);
                    }
                    targetComponent.setLocation(targetX, targetY);
                    targetComponent.getParent().repaint();
    
                    // ✅ Ensure the next animation starts after this one
                    if (onComplete != null) {
                        SwingUtilities.invokeLater(onComplete);  // ✅ Call next animation safely
                    }
                }
            }
        });
    }

    private void start(Runnable onComplete) {
        if (!isAnimating && !lastState) {
            this.onComplete = onComplete;  // ✅ Store the callback
            targetComponent.setVisible(true);
            animationStartTime = System.currentTimeMillis();
            isExiting = false;
            animationTimer.start();
            isAnimating = true;
            lastState = true;
        }
    }

    // ✅ Overload `start()` to allow calls without a callback
    public componentAnim start() {
        start(() -> {}); // Calls the existing start() method with an empty callback
        return this;
    }

    public void exit() {
        if (!isAnimating && lastState) {
            targetComponent.setVisible(false); // Instantly hide without animation
            lastState = false;
        }
    }
    
    private void setupViewportListener() {
        viewportListener = _ -> {
            if (isFullyVisible(targetComponent)) {
                if (!targetComponent.isVisible()) {
                    start(() -> {}); // ✅ Only show when fully visible
                }
            } else if (!isFullyVisible(targetComponent)) {
                if (targetComponent.isVisible()) {
                    exit(); // ✅ Only hide when completely gone
                }
            }
        };
        scrollPane.getViewport().addChangeListener(viewportListener);
    }

    
    public void setDuration(int milliseconds) {
        duration = milliseconds;
        setupAnimation();
    }

    private boolean isFullyVisible(Component comp) {
        JViewport viewport = scrollPane.getViewport();
        Rectangle viewportBounds = viewport.getViewRect();
        
        // 🔥 Use actual width & height for precise checking
        Rectangle compBounds = new Rectangle(comp.getX(), comp.getY(), comp.getWidth(), comp.getHeight());
        
        // ✅ Fully visible only if completely inside viewport
        return viewportBounds.contains(compBounds);
    }

    public void dispose() {
        if (animationTimer != null) {
            animationTimer.stop();
        }

        if (viewportListener != null && scrollPane != null) {
            scrollPane.getViewport().removeChangeListener(viewportListener);
        }

        // Nullify references to help GC
        targetComponent = null;
        scrollPane = null;
        onComplete = null;
        viewportListener = null;
    }

    
}
