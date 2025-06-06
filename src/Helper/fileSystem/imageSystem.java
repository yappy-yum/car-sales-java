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
                         Base Car & Brand Logo
    //////////////////////////////////////////////////////////////*/
    
    public static final ImageIcon BASE_CAR_BRAND = new ImageIcon(FILE_PATH.concat("car_image/Icon/CarBrand.jpeg"));
    public static final ImageIcon BASE_CAR_IMAGE = new ImageIcon(FILE_PATH.concat("car_image/Icon/CarImage.jpeg"));

    /*//////////////////////////////////////////////////////////////
                             car logo icon
    //////////////////////////////////////////////////////////////*/
     
    public static final ImageIcon AUDI = new ImageIcon(FILE_PATH.concat("car_logo/Audi.jpeg"));
    public static final ImageIcon BENTLEY = new ImageIcon(FILE_PATH.concat("car_logo/Bentley.jpeg"));
    public static final ImageIcon BMW = new ImageIcon(FILE_PATH.concat("car_logo/BMW.jpeg"));
    public static final ImageIcon BUGATTI = new ImageIcon(FILE_PATH.concat("car_logo/Bugatti.jpeg"));
    public static final ImageIcon FERRARI = new ImageIcon(FILE_PATH.concat("car_logo/Ferrari.jpeg"));
    public static final ImageIcon LAMBORGHINI = new ImageIcon(FILE_PATH.concat("car_logo/Lamborghini.jpeg"));
    public static final ImageIcon MAYBACH = new ImageIcon(FILE_PATH.concat("car_logo/Maybach.jpeg"));
    public static final ImageIcon MERCEDES = new ImageIcon(FILE_PATH.concat("car_logo/Mercedes.jpeg"));
    public static final ImageIcon ROLLS_ROYCE = new ImageIcon(FILE_PATH.concat("car_logo/RollsRoyce.jpeg")); 

    /*//////////////////////////////////////////////////////////////
                               Car Images
    //////////////////////////////////////////////////////////////*/
    
    public static final ImageIcon ROLLS_ROYCE_PHANTOM = new ImageIcon(FILE_PATH.concat("car_image/RollsRoyce/Rolls_Royce_Phantom.jpeg"));
    public static final ImageIcon ROLLS_ROYCE_LUXE = new ImageIcon(FILE_PATH.concat("car_image/RollsRoyce/Rolls_Royce_Luxe.jpeg"));

    public static final ImageIcon BMW_I9 = new ImageIcon(FILE_PATH.concat("car_image/BMW/BMW_I9.jpeg"));
    public static final ImageIcon BMW_M4 = new ImageIcon(FILE_PATH.concat("car_image/BMW/BMW_M4.jpeg"));
    public static final ImageIcon BMW_M9 = new ImageIcon(FILE_PATH.concat("car_image/BMW/BMW_M9.jpeg"));

    public static final ImageIcon BUGATTI_CHIRON = new ImageIcon(FILE_PATH.concat("car_image/Bugatti/Bugatti_Chiron.jpeg"));
    public static final ImageIcon BUGATTI_VEYRON = new ImageIcon(FILE_PATH.concat("car_image/Bugatti/Bugatti_Veyron.jpeg"));

    public static final ImageIcon MERCEDES_Z4 = new ImageIcon(FILE_PATH.concat("car_image/Mercedes/Mercedes_Z4.jpeg"));
    public static final ImageIcon MERCEDES_VISION_AVTR = new ImageIcon(FILE_PATH.concat("car_image/Mercedes/Mercedes_Vision_AVTR.jpeg"));
    public static final ImageIcon MAYBACH_S680 = new ImageIcon(FILE_PATH.concat("car_image/Mercedes/Maybach_S680.jpeg"));

    public static final ImageIcon AUDI_TT = new ImageIcon(FILE_PATH.concat("car_image/Audi/Audi_TT.jpeg"));
    public static final ImageIcon AUDI_PB18_ETRON = new ImageIcon(FILE_PATH.concat("car_image/Audi/Audi_PB18_Etron.jpeg"));
    
    /*//////////////////////////////////////////////////////////////
                           mouse cursor icon
    //////////////////////////////////////////////////////////////*/
     
    public static final ImageIcon MOUSE_CURSOR = new ImageIcon(FILE_PATH.concat("mouse_cursor/cursor.jpeg")); 

    /*//////////////////////////////////////////////////////////////
                              ChatBox Icon
    //////////////////////////////////////////////////////////////*/    

    public static final ImageIcon CHATBOX_ICON = new ImageIcon(FILE_PATH.concat("frontPage/chatbox_icon.jpeg"));

    /*//////////////////////////////////////////////////////////////
                              profile icon
    //////////////////////////////////////////////////////////////*/
     
    /**
     * 
     * used to represent users pfp icon if user has no pfp 
     * 
     */
    public static final ImageIcon PROFILE = new ImageIcon(FILE_PATH.concat("profile/profileIcon.png"));  

    /**
     * 
     * used to represent users icon for search table data
     * 
     */
    public static final ImageIcon SEARCH = new ImageIcon(FILE_PATH.concat("profile/search.png"));
    
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
                              Tick & Cross
    //////////////////////////////////////////////////////////////*/
    
    public static final ImageIcon TICK = new ImageIcon(FILE_PATH.concat("approval/tick.png"));
    public static final ImageIcon CROSS = new ImageIcon(FILE_PATH.concat("approval/cross.png"));

    /*//////////////////////////////////////////////////////////////
                          default face & docs
    //////////////////////////////////////////////////////////////*/
    
    public static final ImageIcon DEFAULT_DOCS = new ImageIcon(FILE_PATH.concat("confidential/docs.jpeg"));
    public static final ImageIcon DEFAULT_FACE = new ImageIcon(FILE_PATH.concat("confidential/face.jpeg"));

    /*//////////////////////////////////////////////////////////////
                           sample - customer
    //////////////////////////////////////////////////////////////*/
    
    public static final ImageIcon CALEB_DOCS = new ImageIcon(FILE_PATH.concat("../sample/customer/Caleb/docs.jpeg"));
    public static final ImageIcon CALEB_FACE = new ImageIcon(FILE_PATH.concat("../sample/customer/Caleb/face.jpeg"));

    public static final ImageIcon CAMILA_DOCS = new ImageIcon(FILE_PATH.concat("../sample/customer/Camila/docs.jpeg"));
    public static final ImageIcon CAMILA_FACE = new ImageIcon(FILE_PATH.concat("../sample/customer/Camila/face.jpeg"));

    public static final ImageIcon ETHAN_DOCS = new ImageIcon(FILE_PATH.concat("../sample/customer/Ethan/docs.jpeg"));
    public static final ImageIcon ETHAN_FACE = new ImageIcon(FILE_PATH.concat("../sample/customer/Ethan/face.jpeg"));

    public static final ImageIcon GIANA_DOCS = new ImageIcon(FILE_PATH.concat("../sample/customer/Giana/docs.jpeg"));
    public static final ImageIcon GIANA_FACE = new ImageIcon(FILE_PATH.concat("../sample/customer/Giana/face.jpeg"));

    public static final ImageIcon LEO_DOCS = new ImageIcon(FILE_PATH.concat("../sample/customer/Leo/docs.jpeg"));
    public static final ImageIcon LEO_FACE = new ImageIcon(FILE_PATH.concat("../sample/customer/Leo/face.jpeg"));

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
        return new ImageIcon(image
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
        // Create a BufferedImage with transparency
        BufferedImage transparentImage = new BufferedImage(
                                                image.getImage().getWidth(null), 
                                                image.getImage().getHeight(null), 
                                                BufferedImage.TYPE_INT_ARGB
                                            );
        Graphics2D G = transparentImage.createGraphics();

        // Set transparency level
        G.setComposite(
            AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, 
                alpha
            )
        );
        G.drawImage(
            image.getImage(), 
            0, 
            0, 
            null
        );
        G.dispose();

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
        return new Color(
                    color.getRed(), 
                    color.getGreen(), 
                    color.getBlue(), 
                    Math.round(alpha * 255)
                );
    }


}
