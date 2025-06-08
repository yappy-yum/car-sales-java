package frontPage;
import Components.Window;
import Components.initializer;
import Details.AboutUs;
import Details.TaC;
import Details.checkProfile;
import Helper.Comp.PanelHelper;
import Helper.Comp.helpStoreComp;
import Helper.Config.roundedBorder;
import Helper.fileSystem.fontSystem;
import Helper.fileSystem.imageSystem;
import Helper.fileSystem.videoSystem;
import Helper.login.Profile;
import LoginSystem.LoginPage.Job.Job;
import javafx.embed.swing.JFXPanel;

import java.awt.Font;
import java.util.function.Supplier;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import Comment.__init__;


public class FrontPage {
    
    initializer i;
    Window window;
    String loginIconTexts;

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

    private final ImageIcon CHAT = imageSystem.CHATBOX_ICON;
    
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
     
    private final String BENTLEY_CLIP = videoSystem.BENTLEY;
    private final String ROLLS_ROYCE_CLIP = videoSystem.ROLLS_ROYCE;
    private final String BUGATTI_CLIP = videoSystem.BUGATTI;
    private final String MERCEDES_CLIP = videoSystem.MERCEDES;
    
    /*//////////////////////////////////////////////////////////////
                              constructor
    //////////////////////////////////////////////////////////////*/    
    
    public FrontPage(initializer i, Window window) {
        this.i = i;
        this.window = window;
        
        this.loginIconTexts = 
            !i.isLogin.isLogin ? 
                "Register/Login for more" : 
                (i.isLogin.currentProfile.department == Profile.Department.CUSTOMER)
                    ? "Click here to look for cars"
                    : "Click here check profiles";

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
        topCenter();
        topLeft();
        topRight();
        frontTexts();
        FaQ();
        buttom();

        _setPanelHeight();
    }

    /*//////////////////////////////////////////////////////////////
                             top components
    //////////////////////////////////////////////////////////////*/    

    void topCenter() {

        int centerX = i.component.getWidth() / 2;

        // first sub-header
        helpStoreComp.addJLabel(
            i, i.component,
            "the", 
            centerX - 40, 40, 
            centerX - 40, 30, 
            60, 40, 
            fontSystem.SLAB.deriveFont(60f), 
            i.isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLACK
        );
        
        // big header
        Color DARK_GREEN = new Color(13, 15, 14);
        Color LIGHT_GREEN = new Color(59, 245, 99);
        helpStoreComp.addJLabel(
            i, i.component,
            "ONLY PLACE",
            centerX - 280, 100,
            centerX - 280, 80,
            600, 100,
            fontSystem.MONTSERRAT.deriveFont(80f),
            i.isDarkTheme.isDarkTheme ? LIGHT_GREEN : DARK_GREEN
        );

        // a highlight for big-header
        helpStoreComp.addJLabel(
            i, i.component,
            imageSystem._reduceImageTransparency(
                imageSystem._scaleImage(
                    i.isDarkTheme.isDarkTheme ? YELLOW_HIGHLIGHT : GREY_HIGHLIGHT, 
                    650, 250
                ),
                i.isDarkTheme.isDarkTheme ? 0.8f : 0.5f
            ), 
            centerX - 329, 113, 
            centerX - 329, 93, 
            650, 97
        );        

        // second sub-header
        helpStoreComp.addJLabel(
            i, i.component,
            "car lovers will ever need",
            centerX - 200, 290,
            centerX - 200, 200,
            400, 40,
            fontSystem.SLAB.deriveFont(55f),
            i.isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLACK
        );

    }    

    /*//////////////////////////////////////////////////////////////
                          top right components
    //////////////////////////////////////////////////////////////*/    

    void topRight() {

        if (!i.isLogin.isLogin) {
            _helpAddLoginIcon("Register/Login", 1050, 20);
            _helpAddThemeIcon(985, 20);
        } 
        if (i.isLogin.isLogin) {
            _setLoginPfp();
            _helpAddThemeIcon(1070, 20);
            _helpAddVerifyStatusFrontPage(i.isLogin.currentProfile.isVerified);
            _helpAddLoginIcon(loginIconTexts, 960, 290);
        }

    }

    /*//////////////////////////////////////////////////////////////
                          top left components
    //////////////////////////////////////////////////////////////*/    

    void topLeft() {

        // company logo
        helpStoreComp.addJLabel(
            i, i.component,
            imageSystem._scaleImage(
                COMPANY_LOGO, 
                120, 120
            ),
            50, 20,
            50, 2,
            110, 100
        );

        // query statement
        helpStoreComp.addJLabel(
            i, i.component,
            "Visit our showroom at:",
            50, 120,
            50, 90,
            175, 30,
            new Font("Calibri", Font.BOLD, 15),
            i.isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE
        );

        // address
        helpStoreComp.addJTextArea(
            i, i.component,
            """
            Lot 45-2, Jalan Bukit Pantai,
            59100 Bangsar,
            Kuala Lumpur, WP Malaysia       
            """, 
            15, 150, 
            15, 130, 
            230, 100, 
            new Font("Calibri", Font.BOLD, 15), 
            new roundedBorder(
                20, 
                i.isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE, 
                imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)
            ),
            i.isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE
        );

    }

    /*//////////////////////////////////////////////////////////////
                      front texts and short videos
    //////////////////////////////////////////////////////////////*/

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

        _helpAddLoginIcon(loginIconTexts, 960, (870 + 600) - 70);
        _helpAddShortVideoAndLogo(
            BENTLEY_CLIP, BENTLEY_LOGO, 
            870, 850, 
            200, 180
        );    
        
        _helpAddText(
            """
            Imagine pressing the pedal, and in an instant, the world blurs around you. The roar of the 
            engine, the surge of adrenaline. This isn't just speed, it's raw power at your 
            fingertips ...        
            """, 
            1520, 110
        );

        _helpAddLoginIcon(loginIconTexts, 960, (1680 + 600) - 70);
        _helpAddShortVideoAndLogo(
            BUGATTI_CLIP, BUGATTI_LOGO, 
            1680, 1670, 
            190, 190
        );

        _helpAddText(
            """
            Imagine gliding through the city, the world outside fades as you sink into handcrafted 
            leather seats. Every detail, every stitch, designed for your ultimate comfort. Luxury 
            isn't just about what you drive. It's about how you feel ...     
            """, 
            2330, 145
        );

        _helpAddLoginIcon(loginIconTexts, 960, (2490 + 600) - 60);
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

        _helpAddLoginIcon(loginIconTexts, 960, (3300 + 600) - 70);
        _helpAddShortVideoAndLogo(
            MERCEDES_CLIP, MERCEDES_LOGO, 
            3300, 3320, 
            200, 200
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

        _helpAddLoginIcon(loginIconTexts, 50, 4750);
        
    }

    /*//////////////////////////////////////////////////////////////
                                  FaQ
    //////////////////////////////////////////////////////////////*/

    void FaQ() {

        helpStoreComp.addJLabel(
            i, i.component,
            "FAQ", 
            80, 4970, 
            80, 4930, 
            600, 50,
            fontSystem.ROSETTA.deriveFont(30f), 
            i.isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLACK
        );
        i.component._createFAQ(
            30, 5000, 
            "Question 1", 
            "Answer 1"
        );
        i.component._createFAQ(
            30, 5120, 
            "Question 2", 
            "Answer 2"
        );
        i.component._createFAQ(
            30, 5240, 
            "Question 3", 
            "Answer 3"
        );
        i.component._createFAQ(
            30, 5360, 
            "Question 4", 
            "Answer 4"
        );
        i.component._createFAQ(
            30, 5480, 
            "Question 5", 
            "Answer 5"
        );

        // ✅ Reset viewport BEFORE animations start
        SwingUtilities.invokeLater(() -> {
            i.scrollPane.getViewport().setViewPosition(new java.awt.Point(0, 0));
        });   
    }

    void buttom() {
        _helpTextButton(
            "About Us", 
            !i.isLogin.isLogin ? (i.frame.getWidth() / 2 - 220) : (i.frame.getWidth() / 2 - 220 - 200), 
            5600, 
            () -> {i.AboutUs = new AboutUs(i, window);}, 
            () -> i.AboutUs
        );

        _helpTextButton(
            "Terms & Conditions",
            !i.isLogin.isLogin ? (i.frame.getWidth() - 450) : (i.frame.getWidth() - 450 - 200), 
            5600,
            () -> {i.TaC = new TaC(i, window);},
            () -> i.TaC
        );

        if (!i.isLogin.isLogin) _helpTextButton(
                                    "Seeking Job?", 
                                    20, 
                                    5600, 
                                    () -> {i.Job = new Job(i, window);}, 
                                    () -> i.Job
                                );
    }

    /*//////////////////////////////////////////////////////////////
                        helper: add front texts
    //////////////////////////////////////////////////////////////*/    
    
    void _helpAddText(String _text, int targetY, int height) {
        helpStoreComp.addJTextArea(
            i, i.component,
            _text, 
            30, targetY + 90,
            30, targetY, 
            1150, height, 
            new Font("SansSerif", Font.BOLD, 28), null, 
            i.isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLACK
        );
    }

    /*//////////////////////////////////////////////////////////////
                   helper: add front videos and logos
    //////////////////////////////////////////////////////////////*/    

    void _helpAddShortVideoAndLogo(
        String videoPath, ImageIcon logo,
        int videoTargetY, int logoTargetY,
        int logoWidth, int logoHeight
    ) {
        // reduce the size of an image
        logo = imageSystem._scaleImage(logo, logoWidth, logoHeight);
        
        // add logo
        helpStoreComp.addJLabel(
            i, i.component,
            logo, 
            40, logoTargetY + 90, 
            40, logoTargetY, 
            logoWidth, logoHeight
        );

        // silently create a JFXPanel
        new JFXPanel();
        
        // add short video clip
        i.component.addShortVideo(
            videoPath, 
            0, videoTargetY, 
            1280, 600
        );

    } 
    
    /*//////////////////////////////////////////////////////////////
               helper: add actionListener for login icon
    //////////////////////////////////////////////////////////////*/

    void _helpAddLoginIcon(String loginText, int targetX, int targetY) {
        JButton loginButton = helpStoreComp.addJButton(
            i, i.component,
            loginText, 
            targetX, targetY + 90, 
            targetX, targetY, 
            loginText.length() > 20 ? 270 : 180, 50, 
            new roundedBorder(
                40, 
                (
                    (i.isDarkTheme.isDarkTheme) ?
                    (Color.PINK ): 
                    (targetY > 1000 && targetY < 4500) ? (Color.PINK) : (Color.BLACK)
                ),
                imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)
            ),
            i.isDarkTheme.isDarkTheme ? 
                Color.WHITE : 
                (targetY > 1000 && targetY < 4500) ? (Color.WHITE) : (Color.BLACK),
            new Font("SansSerif", Font.BOLD, 13)
        );
        loginButton.addActionListener( _ -> {
            i.component.initializeProfile();
        });
    }

    void _helpTextButton(String _text, int targetX, int targetY, Runnable initializer, Supplier<JComponent> componentGetter) {
        helpStoreComp.addJButton(
            i, i.component,
            _text, 
            targetX, targetY + 50, 
            targetX, targetY, 
            400, 50, null, 
            i.isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLACK,
            new Font("SansSerif", Font.BOLD, 20) 
        ).addActionListener( 
            _ -> { 
                helpStoreComp._startDropDown(
                    i,
                    initializer,
                    componentGetter,
                    1000, 500
                ); 
            }
        );
    }

    void _setLoginPfp() {
        helpStoreComp.addJButton(
            i, i.component,
            imageSystem._scaleImage(
                i.isLogin.currentProfile.pfp, 
                50, 50
            ),
            1150, 70, 
            1150, 20, 
            50, 50
        ).addActionListener( 
            _ -> {
                helpStoreComp._startDropDown(
                    i,
                    () -> { i.checkProfile = new checkProfile(i, window, null); },
                    () -> i.checkProfile, 
                    1000, 500
                );
            }
        );
    } 

    /*//////////////////////////////////////////////////////////////
               helper: add actionListener for theme icon
    //////////////////////////////////////////////////////////////*/       
    
    void _helpAddThemeIcon(int targetX, int targetY) {
        JButton ChatButton = helpStoreComp.addJButton(
                                    i, i.component, 
                                    imageSystem._scaleImage(CHAT, 50, 50), 
                                    targetX, targetY + 90, 
                                    targetX, targetY, 
                                    50, 50
                                );
        ChatButton.addActionListener( _ -> { __init__.CommentInit(i, window, i.component); });

        helpStoreComp.addJButton(
            i, i.component,
            imageSystem._scaleImage(
                i.isDarkTheme.isDarkTheme ? LIGHT_BUTTON : DARK_BUTTON, 
                50, 50
            ),
            targetX - 70, targetY + 90, 
            targetX - 70, targetY, 
            50, 50
        ).addActionListener( _ -> { i.isDarkTheme.switchTheme(i.component, i.switchThemeComp); });
    }

    void _helpAddVerifyStatusFrontPage(boolean isVerified) {
        helpStoreComp.addJLabel(
            i, i.component,
            isVerified ? "Verified" : "Unverified", 
            1140, 100,
            1140, 70, 
            80, 50, 
            new Font("SansSerif", Font.BOLD, 15),
            i.isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLACK
        );
    }

    
    void _setPanelHeight() { PanelHelper.resizeHeightToFit(i.component); }


}
