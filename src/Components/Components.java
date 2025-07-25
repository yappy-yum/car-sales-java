package Components;

import Helper.Animation.videoAnim;
import Helper.Animation.componentAnim;
import Helper.Comp.createJFX;
import Helper.Comp.createScroll;
import Helper.Comp.helpStoreComp;
import Helper.Config.PanelConfig.GradientPanel;
import Helper.Config.PanelConfig.PanelHelper;
import Helper.fileSystem.videoSystem;
import Helper.login.Profile;
import LoginSystem.isLogin;
import LoginSystem.LoginPage.Customer.Customer;
import frontPage.FaQConfig;
import frontPage.isDarkTheme;

import java.util.Collections;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Components extends GradientPanel {

    JFrame frame;
    JScrollPane scrollPane;

    initializer i;
    isDarkTheme isDarkTheme;
    SwitchThemeComp Switch;
    isLogin isLogin;
    Window window;

    /*//////////////////////////////////////////////////////////////
                             Parent JPanel
    //////////////////////////////////////////////////////////////*/
    
    @Override
    public boolean isDarkTheme() { return isDarkTheme.isDarkTheme; }
    
    /*//////////////////////////////////////////////////////////////
                              constructor
    //////////////////////////////////////////////////////////////*/    
    
    public Components(initializer i, Window w) {
        this.i = i;
        this.isDarkTheme = i.isDarkTheme;
        this.isLogin = i.isLogin;
        this.frame = i.frame;
        this.Switch = i.switchThemeComp;
        this.window = w;

        setLayout(null);
    }

    public void addScroll(JScrollPane scrollPane) { 
        this.scrollPane = scrollPane; 
    }  

    /*//////////////////////////////////////////////////////////////
                            add short wejeo
    //////////////////////////////////////////////////////////////*/ 

    /**
     * add short videos
     * 
     * @param video the short video to be added
     * @param x X-coordinate
     * @param y Y-coordinate
     * @param width width length of the video
     * @param height height length of the video
     * 
     */
    public void addShortVideo(String videoPath, int x, int y, int width, int height) {
        Platform.setImplicitExit(false);            
        Platform.runLater(() -> {
            JFXPanel fJfxPanel = createJFX.createJFXPanel(x, y, width, height);
            add(fJfxPanel);
    
            // Adjust video size
            videoSystem._adjustVideoSize(
                videoPath, width, height, mediaView -> {
                    MediaPlayer player = mediaView.getMediaPlayer();
        
                    if (player != null) {
                        player.setOnEndOfMedia(() -> player.seek(Duration.ZERO));
        
                        createJFX.setupJavaFXScene(fJfxPanel, mediaView, width, height);
        
                        createScroll.mouseScroll(fJfxPanel, scrollPane);
        
                        _videoAnim(fJfxPanel, x, y, player);

                        i.storeVid.store(player);
        
                        // Force the JFXPanel to update
                        Platform.runLater(() -> {
                            fJfxPanel.invalidate();
                            fJfxPanel.repaint();
                        });
                    } 
                }
            );
        });
    }
    
    /**
     * helper to handle video animation for {@link #addShortVideo}
     * 
     * @param fJfxPanel FFXPanel panel for video
     * @param x starting from X-coordinate
     * @param y starting from Y-coordinate
     * @param player the video player
     * 
     */
    protected void _videoAnim(JFXPanel fJfxPanel, int x, int y, MediaPlayer player) {
        videoAnim animation = new videoAnim(
            fJfxPanel, 
            x, y + 90, 
            x, y, 
            1500, 
            scrollPane, 
            () -> { fJfxPanel.setVisible(true); player.play(); }
        );
        i.videoAnimStorage.addAnim(animation);
    
        scrollPane.getViewport().addChangeListener(_ -> {
            if (animation.isInViewPort()) {
                if (!fJfxPanel.isVisible()) animation.start();
            } else {
                if (fJfxPanel.isVisible()) animation.exit(player);
            }
        });
    
        SwingUtilities.invokeLater(() -> {
            if (animation.isInViewPort()) {
                animation.start();
            } else {
                fJfxPanel.setVisible(false);
            }
        });
    }

    /*//////////////////////////////////////////////////////////////
                                add FaQ
    //////////////////////////////////////////////////////////////*/    

    public void _createFAQ(int x, int y, String question, String answer) {

        FaQConfig.FaQBuilder builder = new FaQConfig(isDarkTheme). new FaQBuilder(x, y, question, answer);

        JTextArea questionLabel = builder.question;
        JTextArea answerLabel = builder.answer;
        JButton toggleButton = builder.toggleButton;
        JPanel boxPanel = builder.FAQBox;

        // 🧩 Add to UI
        Stream.of(questionLabel, answerLabel, toggleButton).forEach(boxPanel::add);
        Stream.of(boxPanel).forEach(this::add);
        Collections.addAll(Switch.JPanels, boxPanel);
        Collections.addAll(Switch.texts, questionLabel, answerLabel);
        Switch.TButtons.add(toggleButton);


        i.compAnimStorage.addAnim(
                    new componentAnim(
                        boxPanel, 
                        x, y - 100, 
                        x, y, 
                        scrollPane
                    )
                );
    }

    /*//////////////////////////////////////////////////////////////
                         login and view profile
    //////////////////////////////////////////////////////////////*/    

    public void initializeProfile() {
        switch (isLogin.isLogin ? 1 : 0) {
            case 0: 
                helpStoreComp._startDropDown(
                    i,
                    () -> i.Customer = new Customer(i, window),
                    () -> i.Customer,
                    1000, 500
                ); 
                break;
        
            case 1: 
                if (isLogin.currentProfile.department == Profile.Department.CUSTOMER) {
                    _remove();
                    window._loadPurchasePage();
                }
                else if (isLogin.currentProfile.isVerified) {
                    _remove();
                    window._loadDBPage();
                }
        }
    }   
    
    public void _remove() { PanelHelper.clear(null, this); }

}