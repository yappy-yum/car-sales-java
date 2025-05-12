package StoreAnimation;

import java.util.ArrayList;
import java.util.List;

import Helper.Animation.videoAnim;

/**
 * Class to store and manage multiple videoAnim instances.
 */
public class videoAnimStorage {
    
    private final List<videoAnim> animations = new ArrayList<>();

    /**
     * Register a new videoAnim instance for tracking.
     * 
     * @param anim The videoAnim instance to register.
     */
    public void addAnim(videoAnim anim) {
        if (anim != null) {
            animations.add(anim);
        }
    }

    /**
     * Disposes all registered videoAnim instances and clears the list.
     */
    public void disposeAnim() {
        for (videoAnim anim : animations) {
            if (anim != null) {
                anim.dispose();
            }
        }
        animations.clear();
    }
}
