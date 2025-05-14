package Helper.fileSystem;

import javax.swing.*;
import java.io.File;

public class ImageUploader {

    /**
     * Opens a file chooser dialog and returns the selected image.
     * 
     * @return ImageIcon of the selected image, or null if canceled or invalid file.
     * 
     */
    public static ImageIcon uploadImageIcon() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an image");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // Optional: check if it's an image file
            String fileName = selectedFile.getName().toLowerCase();
            if (fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
                return new ImageIcon(selectedFile.getAbsolutePath());
            } else {
                System.err.println("Invalid image file type selected.");
            }
        }

        return null; 
    }
}
