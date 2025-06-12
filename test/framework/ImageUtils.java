package framework;

import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

public class ImageUtils {

    public static byte[] imageIconToBytes(ImageIcon icon, String format) {
        try 
        {
            BufferedImage img = new BufferedImage(
                    icon.getIconWidth(),
                    icon.getIconHeight(),
                    BufferedImage.TYPE_INT_ARGB
            );

            img
                .getGraphics()
                .drawImage(
                    icon.getImage(), 
                    0,
                    0, 
                    null
                );

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            
            ImageIO.write(img, format, baos);
            return baos.toByteArray();

        } 
        catch (Exception LOVE) 
        {
            LOVE.printStackTrace();
            return null;
        }
    }
}
