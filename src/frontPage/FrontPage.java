package frontPage;
import Components.Components;
import Helper.RoundedBorder.roundedBorderFactory;
import Helper.fileSystem.imageSystem;
import Helper.fileSystem.videoSystem;
import javafx.scene.media.Media;

import java.awt.Font;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;


public class FrontPage {
    
    isDarkTheme isDarkTheme;
    Components components;
    JScrollPane scrollPane;

    roundedBorderFactory border = new roundedBorderFactory();

    /*//////////////////////////////////////////////////////////////
                        light/dark theme button
    //////////////////////////////////////////////////////////////*/
     
    /**
     * load all the neccessary images from {@link #imageSystem}
     * 
     * <p> load light theme icon </p>
     * 
     */
    private final ImageIcon LIGHT_BUTTON = imageSystem.LIGHT_BUTTON;

    /**
     * Load all the neccessary images from {@link #imageSystem}
     * 
     * <p> load dark theme icon </p>
     * 
     */
    private final ImageIcon DARK_BUTTON = imageSystem.DARK_BUTTON;    

    /*//////////////////////////////////////////////////////////////
                         big header highlights
    //////////////////////////////////////////////////////////////*/
     
    /**
     * load all the neccessary images from {@link #imageSystem}
     * 
     * <p> load yellow highlight </p>
     *
     */
    private final ImageIcon YELLOW_HIGHLIGHT = imageSystem.YELLOW_HIGHLIGHT;
    
    /**
     * Load all the neccessary images from {@link #imageSystem}
     * 
     * <p> load grey highlight </p> 
     * 
     */
    private final ImageIcon GREY_HIGHLIGHT = imageSystem.GREY_HIGHLIGHT;  
    
    /*//////////////////////////////////////////////////////////////
                              company logo
    //////////////////////////////////////////////////////////////*/
     
    /**
     * Load all the neccessary images from {@link #imageSystem}
     * 
     * <p> load company logo </p>
     * 
     */
    private final ImageIcon COMPANY_LOGO = imageSystem.COMPANY_LOGO;   

    /*//////////////////////////////////////////////////////////////
                               car logos
    //////////////////////////////////////////////////////////////*/
     
    private final ImageIcon BENTLEY_LOGO = imageSystem.BENTLEY;
    private final ImageIcon ROLLS_ROYCE_LOGO = imageSystem.ROLLS_ROYCE;
    private final ImageIcon BUGATTI_LOGO = imageSystem.BUGATTI;
    private final ImageIcon MERCEDES_LOGO = imageSystem.MERCEDES;  
    
    /*//////////////////////////////////////////////////////////////
                           short videos clip
    //////////////////////////////////////////////////////////////*/   
     
    private final Media BENTLEY_CLIP = videoSystem.BENTLEY;
    private final Media ROLLS_ROYCE_CLIP = videoSystem.ROLLS_ROYCE;
    private final Media BUGATTI_CLIP = videoSystem.BUGATTI;
    private final Media MERCEDES_CLIP = videoSystem.MERCEDES;
    
    /*//////////////////////////////////////////////////////////////
                              constructor
    //////////////////////////////////////////////////////////////*/    
    
    public FrontPage(isDarkTheme i, Components components, JScrollPane scrollPane) {
        this.components = components; 
        this.isDarkTheme = i; 
        this.scrollPane = scrollPane;

        SwingUtilities.invokeLater(this::_initializeFrontPage); 
    }

    /*//////////////////////////////////////////////////////////////
                    initialize front page interface
    //////////////////////////////////////////////////////////////*/    
    
    /**
     * a little helper to call all the method that initializes all the components
     * that will be displayed on the front page
     * 
     * @see {@link #topConter}
     * @see {@link #topLeft}
     * @see {@link #topRight}
     * 
     */
    void _initializeFrontPage() {
        topConter();
        topLeft();
        topRight();
        frontTexts();
        FaQ();
    }

    void topRight() {

        _helpAddLoginIcon("Register/Login", 1050, 20);
        _helpAddThemeIcon(985, 20);

    }

    void topConter() {

        int centerX = components.getWidth() / 2;

        // first sub-header
        components.addJLabel(
            "the", 
            centerX - 40, 40, 
            centerX - 40, 30, 
            60, 40, 
            imageSystem.SLAB.deriveFont(60f), 
            isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLACK
        );
        
        // big header
        Color DARK_GREEN = new Color(13, 15, 14);
        Color LIGHT_GREEN = new Color(59, 245, 99);
        components.addJLabel(
            "ONLY PLACE",
            centerX - 280, 100,
            centerX - 280, 80,
            600, 100,
            imageSystem.MONTSERRAT.deriveFont(80f),
            isDarkTheme.isDarkTheme ? LIGHT_GREEN : DARK_GREEN
        );

        // a highlight for big-header
        components.addJLabel(
            imageSystem._reduceImageTransparency(
                imageSystem._scaleImage(
                    isDarkTheme.isDarkTheme ? YELLOW_HIGHLIGHT : GREY_HIGHLIGHT, 
                    650, 250
                ),
                isDarkTheme.isDarkTheme ? 0.8f : 0.5f
            ), 
            centerX - 329, 113, 
            centerX - 329, 93, 
            650, 97
        );        

        // second sub-header
        components.addJLabel(
            "car lovers will ever need",
            centerX - 200, 290,
            centerX - 200, 200,
            400, 40,
            imageSystem.SLAB.deriveFont(55f),
            isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLACK
        );

    }

    void topLeft() {

        // company logo
        components.addJLabel(
            imageSystem._scaleImage(
                COMPANY_LOGO, 
                120, 120
            ),
            50, 12,
            50, 2,
            110, 100
        );

        // query statement
        components.addJLabel(
            "Visit our showroom at:",
            50, 100,
            50, 90,
            175, 30,
            new Font("Calibri", Font.BOLD, 15),
            isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE
        );

        // address
        components.addJTextArea(
            """
            Lot 45-2, Jalan Bukit Pantai,
            59100 Bangsar,
            Kuala Lumpur, WP Malaysia       
            """, 
            15, 140, 
            15, 130, 
            230, 100, 
            new Font("Calibri", Font.BOLD, 15), 
            border.create(
                20, 
                isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE, 
                imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)
            ),
            isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE
        );

    }

    /**
     * 
     * targetY = previous targetY + previous height + 50        <p>
     * height (one line) = 35                                   <p>
     * height (more than one line) = (35 * line of texts) + 5
     * 
     */
    void frontTexts() {
      
        _helpAddText(
            "What if your car was more than just a way to get around?", 
            330, 35
        );

        _helpAddText(
            """
            What if it was a statement of power, prestige, and innovation ... that designed not just
            to drive, but to transform every journey into an unforgettable experience?
            """, 
            415, 75
        );

        _helpAddText(
            "and no, we are not selling you anything ... ", 
            540, 35
        );

        _helpAddText(
            "Pichure this ...", 
            625, 35
        );

        _helpAddText(
            """
            Imagine pulling up to a five star hotel, the valet rushing to open your door. Heads turn 
            as you step out of a machine crafted for elegance. This isn't just a car ... it's your
            signature.        
            """, 
            710, 110
        );

        _helpAddLoginIcon("Register/Login for more", 1000, (870 + 600) - 70);
        _helpAddShortVideoAndLogo(
            BENTLEY_CLIP, BENTLEY_LOGO, 
            870, 890, 
            200, 120
        );    
        
        _helpAddText(
            """
            Imagine pressing the pedal, and in an instant, the world blurs around you. The roar of the 
            engine, the surge of adrenaline. This isn't just speed, it's raw power at your 
            fingertips ...        
            """, 
            1520, 110
        );

        _helpAddLoginIcon("Register/Login for more", 1000, (1680 + 600) - 70);
        _helpAddShortVideoAndLogo(
            BUGATTI_CLIP, BUGATTI_LOGO, 
            1680, 1670, 
            170, 120
        );

        _helpAddText(
            """
            Imagine gliding through the city, the world outside fades as you sink into handcrafted 
            leather seats. Every detail, every stitch, designed for your ultimate comfort. Luxury 
            isn't just about what you drive. It's about how you feel ...     
            """, 
            2330, 145
        );

        _helpAddLoginIcon("Register/Login for more", 1000, (2490 + 600) - 60);
        _helpAddShortVideoAndLogo(
            ROLLS_ROYCE_CLIP, ROLLS_ROYCE_LOGO, 
            2490, 2500, 
            200, 200
        );

        _helpAddText(
            """
            Imagine a car that feels alive, responding to your touch and instincts. Biometric 
            sensors sync with you, while its flowing design redefines the future. This isn't just a 
            car — it's a revolution on wheels ...        
            """, 
            3140, 145
        );

        _helpAddLoginIcon("Register/Login for more", 1000, (3300 + 600) - 70);
        _helpAddShortVideoAndLogo(
            MERCEDES_CLIP, MERCEDES_LOGO, 
            3300, 3320, 
            200, 112
        );

        _helpAddText(
            "Your Dream Car is Closer Than You Think", 
            3950, 35
        );

        _helpAddText(
            "and no, we are not trying to sell you anything ... ", 
            4035, 35
        );

        _helpAddText(
            "But let's be honest ...", 
            4120, 35
        );

        _helpAddText(
            """
            Owning a car like this isn't just about driving ... 
            it's about belonging to a world where only a few ever get access.        
            """, 
            4205, 75
        );

        _helpAddText(
            """
            It's about knowing that every curve, every stitch, and every rev of the engine was 
            designed for those who demand more.        
            """, 
            4330, 75
        );

        _helpAddText(
            "But here's the thing … not everyone gets in.", 
            4455, 35
        );

        _helpAddText(
            """
            Some will just watch from the sidelines ...
            While others take the wheel.   
            """, 
            4540, 75
        );

        _helpAddText(
            "Which one will you be ?", 
            4665, 35
        );

        _helpAddLoginIcon("Reguster/Login for more", 50, 4750);
        
    }

    void FaQ() {

        // ✅ Reset viewport BEFORE animations start
        SwingUtilities.invokeLater(() -> {
            scrollPane.getViewport().setViewPosition(new java.awt.Point(0, 0));
        });   

        components.addJLabel(
            "FAQ", 
            80, 4970, 
            80, 4930, 
            600, 50,
            imageSystem.ROSETTA.deriveFont(30f), 
            isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLACK
        );
        components._createFAQ(
            30, 5000, 
            "Question 1", 
            "Answer 1"
        );
        components._createFAQ(
            30, 5120, 
            "Question 2", 
            "Answer 2"
        );
        components._createFAQ(
            30, 5240, 
            "Question 3", 
            "Answer 3"
        );
        components._createFAQ(
            30, 5360, 
            "Question 4", 
            "Answer 4"
        );
        components._createFAQ(
            30, 5480, 
            "Question 5", 
            "Answer 5"
        );
    }

    void _helpAddText(String _text, int targetY, int height) {
        components.addJTextArea(
            _text, 
            30, targetY + 90,
            30, targetY, 
            1150, height, 
            new Font("SansSerif", Font.BOLD, 28), null, 
            isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLACK
        );
    }

    void _helpAddShortVideoAndLogo(
        Media video, ImageIcon logo,
        int videoTargetY, int logoTargetY,
        int logoWidth, int logoHeight
    ) {
        // reduce the size of an image
        logo = imageSystem._scaleImage(logo, logoWidth, logoHeight);
        
        // add logo
        components.addJLabel(
            logo, 
            40, logoTargetY + 90, 
            40, logoTargetY, 
            logoWidth, logoHeight
        );
        
        // add short video clip
        components.addShortVideo(
            video, 
            0, videoTargetY, 
            1280, 600
        );

    }

    void _helpAddLoginIcon(String loginText, int targetX, int targetY) {
        JButton loginButton = components.addJButton(
            loginText, 
            targetX, targetY + 90, 
            targetX, targetY, 
            loginText.length() > 20 ? 230 : 180, 50, 
            border.create(
                40, 
                (
                    (isDarkTheme.isDarkTheme) ?
                    (Color.PINK ): 
                    (targetY > 1000 && targetY < 4500) ? (Color.PINK) : (Color.BLACK)
                ),
                imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)
            ),
            isDarkTheme.isDarkTheme ? 
                Color.WHITE : 
                (targetY > 1000 && targetY < 4500) ? (Color.WHITE) : (Color.BLACK)
        );
    }

    void _helpAddThemeIcon(int targetX, int targetY) {
        JButton themeButton = components.addJButton(
            imageSystem._scaleImage(
                isDarkTheme.isDarkTheme ? LIGHT_BUTTON : DARK_BUTTON, 
                50, 50
            ),
            targetX, targetY + 90, 
            targetX, targetY, 
            50, 50
        );
        themeButton.addActionListener( _ -> {
            isDarkTheme.switchTheme();
            components.switchTheme();
        });
    }

    

}
