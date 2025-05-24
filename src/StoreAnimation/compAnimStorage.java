package StoreAnimation;

import java.util.ArrayList;
import java.util.List;

import Helper.Animation.componentAnim;

public class compAnimStorage {
    
    private final List<componentAnim> animations = new ArrayList<>();

    /**
     * Register a new componentAnim instance for tracking.
     * 
     * @param anim The componentAnim instance to register.
     */
    public void addAnim(componentAnim anim) {
        if (anim != null) {
            animations.add(anim);
        }
    }

    /**
     * Disposes all registered componentAnim instances and clears the list.
     */
    public void disposeAnim() {
        for (componentAnim anim : animations) {
            if (anim != null) {
                anim.dispose();
            }
            anim = null;
        }
        animations.clear();
    }

}
