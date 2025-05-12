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
            } catch (Exception e) {
                System.err.println("Failed to dispose MediaPlayer: " + e.getMessage());
            }
        }
        mediaPlayers.clear();
    }
}
