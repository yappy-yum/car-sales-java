package Helper.fileSystem;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

/**
 * <p> a class that dedicated to store all the image (and fonts) used in the program </p>
 * 
 * this class also contain a method {@link #_scaleImage} to scale an image
 * 
 * @author yappy-yum
 * 
 */
public class imageSystem {
    
    /**
     * ready to be concatenated to provide a complete file path to fetch all the images
     * 
     * @notice <b>
     * file path may differ, therefore ensures that changes has made in the {@link #filePath} class
     * before running the program to avoid unneccessary missing dependencies or images
     * 
     */
    private static final String FILE_PATH = filePath.FILE_PATH.concat("img/"); 
    
    /*//////////////////////////////////////////////////////////////
                              company logo
    //////////////////////////////////////////////////////////////*/
     
    public static final ImageIcon COMPANY_LOGO = new ImageIcon(FILE_PATH.concat("frontPage/companyLogo.png"));    

    /*//////////////////////////////////////////////////////////////
                            custom highlight
    //////////////////////////////////////////////////////////////*/
     
    public static final ImageIcon YELLOW_HIGHLIGHT = new ImageIcon(FILE_PATH.concat("frontPage/yellowHighlight.png"));
    public static final ImageIcon GREY_HIGHLIGHT = new ImageIcon(FILE_PATH.concat("frontPage/greyHighlight.png"));
    
    /*//////////////////////////////////////////////////////////////
                         light/dark theme icon
    //////////////////////////////////////////////////////////////*/
     
    public static final ImageIcon LIGHT_BUTTON = new ImageIcon(FILE_PATH.concat("theme/lightTheme.png"));
    public static final ImageIcon DARK_BUTTON = new ImageIcon(FILE_PATH.concat("theme/darkTheme.png"));

    /*//////////////////////////////////////////////////////////////
                             car logo icon
    //////////////////////////////////////////////////////////////*/
     
    public static final ImageIcon AUDI = new ImageIcon(FILE_PATH.concat("car_logo/Audi.png"));
    public static final ImageIcon BENTLEY = new ImageIcon(FILE_PATH.concat("car_logo/Bentley.png"));
    public static final ImageIcon BMW = new ImageIcon(FILE_PATH.concat("car_logo/BMW.png"));
    public static final ImageIcon BUGATTI = new ImageIcon(FILE_PATH.concat("car_logo/Bugatti.jpeg"));
    public static final ImageIcon FERRARI = new ImageIcon(FILE_PATH.concat("car_logo/Ferrari.jpeg"));
    public static final ImageIcon LAMBORGHINI = new ImageIcon(FILE_PATH.concat("car_logo/Lamborghini.jpeg"));
    public static final ImageIcon MAYBACH = new ImageIcon(FILE_PATH.concat("car_logo/Maybach.png"));
    public static final ImageIcon MERCEDES = new ImageIcon(FILE_PATH.concat("car_logo/Mercedes.png"));
    public static final ImageIcon ROLLS_ROYCE = new ImageIcon(FILE_PATH.concat("car_logo/RollsRoyce.png")); 
    
    /*//////////////////////////////////////////////////////////////
                           mouse cursor icon
    //////////////////////////////////////////////////////////////*/
     
    public static final ImageIcon MOUSE_CURSOR = new ImageIcon(FILE_PATH.concat("mouse_cursor/cursor.jpeg")); 

    /*//////////////////////////////////////////////////////////////
                              profile icon
    //////////////////////////////////////////////////////////////*/
     
    public static final ImageIcon PROFILE = new ImageIcon(FILE_PATH.concat("profile/profileIcon.png"));  
    
    /*//////////////////////////////////////////////////////////////
                          password show & hide
    //////////////////////////////////////////////////////////////*/
     
    public static final ImageIcon PASSWORD_SHOW = new ImageIcon(FILE_PATH.concat("password/show.png"));
    public static final ImageIcon PASSWORD_HIDE = new ImageIcon(FILE_PATH.concat("password/hide.png"));   
    
    /*//////////////////////////////////////////////////////////////
                             text edit icon
    //////////////////////////////////////////////////////////////*/    
    
    public static final ImageIcon BOLD = new ImageIcon(FILE_PATH.concat("textAnnotate/Bold.png"));
    public static final ImageIcon RALIC = new ImageIcon(FILE_PATH.concat("textAnnotate/Ralic.png"));
    public static final ImageIcon UNDERLINE = new ImageIcon(FILE_PATH.concat("textAnnotate/underline.png"));

    public static final ImageIcon LEFT_TEXT = new ImageIcon(FILE_PATH.concat("textAnnotate/left.png"));
    public static final ImageIcon CENTER_TEXT = new ImageIcon(FILE_PATH.concat("textAnnotate/center.png"));
    public static final ImageIcon RIGHT_TEXT = new ImageIcon(FILE_PATH.concat("textAnnotate/right.png"));
    public static final ImageIcon JUSTIFY_TEXT = new ImageIcon(FILE_PATH.concat("textAnnotate/justify.png"));

    /*//////////////////////////////////////////////////////////////
                            scale image size
    //////////////////////////////////////////////////////////////*/

    /**
     * a helper function to scale an image
     * 
     * @param image the image in ImageIcon data type to be scaled
     * @param width the length width of the image to be scaled to
     * @param height the length height of the image to be scaled to
     * @return a scaled image with ImageIcon data type
     * 
     */
    public static ImageIcon _scaleImage(ImageIcon image, int width, int height) {
        return new ImageIcon(
            image
            .getImage()
            .getScaledInstance(width, height, Image.SCALE_SMOOTH)
        );
    }

    /*//////////////////////////////////////////////////////////////
                    reduce image transparency method
    //////////////////////////////////////////////////////////////*/    

    /**
     * a helper function to reduce the transparency of an image so that 
     * an image behind can be seen through
     * 
     * @param image an image to be reduced of its transparency
     * @param alpha transparency level
     * @return a low transparency image with ImageIcon data type
     * 
     */
    public static ImageIcon _reduceImageTransparency(ImageIcon image, float alpha) {
        // Get original image and dimensions
        Image originalImage = image.getImage();
        int width = originalImage.getWidth(null);
        int height = originalImage.getHeight(null);

        // Create a BufferedImage with transparency
        BufferedImage transparentImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = transparentImage.createGraphics();

        // Set transparency level
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.drawImage(originalImage, 0, 0, null);
        g2d.dispose();

        // Return a new ImageIcon
        return new ImageIcon(transparentImage);
    }

    /*//////////////////////////////////////////////////////////////
                    reduce color transparency method
    //////////////////////////////////////////////////////////////*/    
    
    /**
     * A helper function to reduce the transparency of a color so that
     * it appears more transparent.
     *
     * @param color a color to be reduced of its transparency
     * @param alpha transparency level (0.0f to 1.0f)
     * @return a new Color object with reduced transparency
     * 
     */
    public static Color _reduceColorTransparency(Color color, float alpha) {
        // Get the original color components
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        // Create and return a new Color with modified alpha value
        return new Color(red, green, blue, Math.round(alpha * 255));
    }


}
