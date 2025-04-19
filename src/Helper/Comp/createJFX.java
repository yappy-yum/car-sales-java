package Helper.Comp;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;

public class createJFX {
    
    public static JFXPanel createJFXPanel(int x, int y, int width, int height) {
        JFXPanel panel = new JFXPanel();
        panel.setBounds(x, y + 90, width, height); // note: y+90 is baked in
        panel.setFocusable(false);
        return panel;
    }

    public static void setupJavaFXScene(JFXPanel panel, MediaView mediaView, int width, int height) {
        Platform.runLater(() -> {
            Rectangle overlay = new Rectangle(width, height);
            overlay.setFill(javafx.scene.paint.Color.rgb(128, 128, 128, 0.3));
            overlay.setMouseTransparent(true);

            mediaView.setMouseTransparent(true);

            StackPane stack = new StackPane(mediaView, overlay);
            stack.setPrefSize(width, height);
            stack.setMouseTransparent(true);

            Scene scene = new Scene(stack, width, height);
            scene.setCursor(Cursor.NONE);

            scene.setOnMouseEntered(e -> e.consume());
            scene.setOnMouseExited(e -> e.consume());

            panel.setScene(scene);
        });
    }


}
