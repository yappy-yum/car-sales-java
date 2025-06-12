package Helper.fileSystem;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

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

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
        {
            File selectedFile = fileChooser.getSelectedFile();

            // check if it's an image file
            String fileName = selectedFile.getName().toLowerCase();
            
            if 
            (
                fileName.endsWith(".png") || 
                fileName.endsWith(".jpg") || 
                fileName.endsWith(".jpeg")
            ) 
            {
                return new ImageIcon(selectedFile.getAbsolutePath());
            }
            else 
            {
                System.err.println("Invalid image file type selected.");
            }
            
        }

        return null; 
    }
}
