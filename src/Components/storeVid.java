package Components;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.media.MediaPlayer;

public class storeVid {
    private final List<MediaPlayer> mediaPlayers = new ArrayList<>();

    /*//////////////////////////////////////////////////////////////
                                 store
    //////////////////////////////////////////////////////////////*/    

    public void store(MediaPlayer player) {
        if (player != null) {
            mediaPlayers.add(player);
        }
    }

    /*//////////////////////////////////////////////////////////////
                                 delete
    //////////////////////////////////////////////////////////////*/    

    public void clearAll() {
        for (MediaPlayer player : mediaPlayers) {
            try {
                player.stop();
                player.dispose();
                player = null;
            } catch (Exception e) {
                System.err.println("Failed to dispose MediaPlayer: " + e.getMessage());
            }
        }
        mediaPlayers.clear();
    }

    // @audit have below method uncommented if the method above causes lag
    // public void clearAll() {
    //     // Just to make sure nothing crashes
    //     try {
    //         for (int j = 0; j < mediaPlayers.size(); j++) {
    //             MediaPlayer player = mediaPlayers.get(j);
    //             if (player != null) {
    //                 player.stop();
    //                 player.dispose();
    //                 // player = null; // this line is useless and can be removed
    //             }
    //         }
    //     } catch (Exception e) {
    //         System.err.println("Video cleanup failed: " + e.getMessage());
    //     } finally {
    //         mediaPlayers.clear(); // always clear at the end
    //     }
    // }

}
