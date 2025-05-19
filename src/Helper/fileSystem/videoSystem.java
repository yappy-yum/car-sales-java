package Helper.fileSystem;

import java.awt.Color;
import java.util.function.Consumer;

import javax.swing.JTextArea;

import Helper.Config.roundedBorder;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * <p> a class that dedicated to store all the videos used in the program </p>
 * 
 * this class also contain a method {@link #_addTransparentVideo} to apply
 * the transparency on a video
 * 
 * @author yappy-yum
 * 
 */
public class videoSystem {

    /**
     * ready to be concatenated to provide a complete file path to fetch all the images
     * 
     * @notice <b>
     * file path may differ, therefore ensures that changes has made in the {@link #filePath} class
     * before running the program to avoid unneccessary missing dependencies or images
     * 
     */
    private static final String FILE_PATH = "file:///".concat(filePath.FILE_PATH).concat("video/"); 

    /*//////////////////////////////////////////////////////////////
                         front page persuasion
    //////////////////////////////////////////////////////////////*/
     
    public static final String ROLLS_ROYCE = FILE_PATH.concat("RollsRoyce.mp4");    
    public static final String BUGATTI = FILE_PATH.concat("Bugatti.mp4");
    public static final String MERCEDES = FILE_PATH.concat("Mercedes.mp4");
    public static final String BENTLEY = FILE_PATH.concat("Bentley.mp4");
    
    /*//////////////////////////////////////////////////////////////
                        scale video size method
    //////////////////////////////////////////////////////////////*/    
    
    /**
     * adjust the size of the video
     * 
     * @param video the video player
     * @param width the length width to be scaled to
     * @param height the length height to be scaled to
     * @return the scaled video in MediaView data type
     * 
     */
    public static void _adjustVideoSize(String videoPath, double width, double height, Consumer<MediaView> onMediaViewReady) {
        Platform.runLater(() -> {
            Media media = new Media(videoPath);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            MediaView scalingVideo = new MediaView(mediaPlayer);
    
            scalingVideo.setFitWidth(width);
            scalingVideo.setFitHeight(height);
            scalingVideo.setPreserveRatio(false);
    
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            onMediaViewReady.accept(scalingVideo);
        });
    }

    /*//////////////////////////////////////////////////////////////
                           apply transparency
    //////////////////////////////////////////////////////////////*/    

    /**
     * A helper function to reduce the transparency of a video displayed in a MediaView.
     * 
     * <p>
     * 
     * it does not actually reduce the transparency of the video itself, but rather
     * having a light gray attached on the video, making it appear more transparent.
     *
     * @param mediaView The MediaView object displaying the video.
     * @param alpha Transparency level (float between 0.0 and 1.0).
     * 
     */
    public static JTextArea _addTransparentVideo(float alpha, int x, int y, int width, int height) {
        JTextArea box = new JTextArea(); 
        box.setBounds(x, y, width, height);
        box.setBorder(
            new roundedBorder(
                0,
                null, 
                imageSystem._reduceColorTransparency(Color.GRAY, alpha)
            )
        );
        box.setOpaque(false);
        box.setEditable(false);

        return box;
    }   
}
