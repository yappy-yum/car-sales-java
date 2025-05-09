package Helper.fileSystem;

import java.awt.Font;
import java.io.File;

/**
 * 
 * helper class that fetch the custom fonts
 */
public class fontSystem {
    
    /**
     * ready to be concatenated to provide a complete file path to fetch all the images
     * 
     * @notice <b>
     * file path may differ, therefore ensures that changes has made in the {@link #filePath} class
     * before running the program to avoid unneccessary missing dependencies or images
     * 
     */
    private static final String FILE_PATH = filePath.FILE_PATH.concat("font/"); 

    /**
     * custom font: Nordin Slab Rounded Drawn by craftsupplyco
     * 
     * <p>
     * 
     * used for sub-headers in front page
     * 
     * @see https://www.fontspace.com/search?q=Nordin%20Slab%20Rounded%20Drawn%20by%20craftsupplyco
     * 
     */
    public static final Font SLAB;
    static {
        try {
            SLAB = Font.createFont(
                Font.TRUETYPE_FONT, 
                new File(FILE_PATH.concat("NordinslabroundeddrawnRegDemo-aYO0o.ttf"))
            );
        } catch (Exception e) {
            throw new RuntimeException("you're a failure :D", e);
        }
    }

    /**
     * custom font: Montserrat by 
     *          Julieta Ulanovsky, 
     *          Sol Matas, 
     *          Juan Pablo del Peral,
     *          Jacques Le Bailly
     * 
     * <p>
     * 
     * used for big-headers in front page
     * 
     * @see https://fonts.google.com/specimen/Montserrat
     * 
     */
    public static final Font MONTSERRAT;
    static {
        try {
            MONTSERRAT = Font.createFont(
                Font.TRUETYPE_FONT, 
                new File(FILE_PATH.concat("Montserrat-Bold.ttf"))
            );
        } catch (Exception e) {
            throw new RuntimeException("you're a failure :D", e);
        }
    }

    /**
     * 
     * https://www.fontspace.com/new/fonts
     * 
     */
    public static final Font ROSETTA;
    static {
        try {
            ROSETTA = Font.createFont(
                Font.TRUETYPE_FONT, 
                new File(FILE_PATH.concat("CsRosettaRegularDemo-rvpKO.TTF"))
            );
        } catch (Exception e) {
            throw new RuntimeException("you're a failure :D", e);
        }
    }

    public static final Font BEAUTIFUL_PEOPLE;
    static {
        try {
            BEAUTIFUL_PEOPLE = Font.createFont(
                Font.TRUETYPE_FONT, 
                new File(FILE_PATH.concat("BeautifulPeoplePersonalUse-dE0g.ttf"))
            );
        } catch (Exception e) {
            throw new RuntimeException("you're a failure :D", e);
        }
    }

}
