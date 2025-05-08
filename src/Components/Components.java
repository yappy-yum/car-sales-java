package Components;

import Helper.Animation.videoAnim;
import Helper.Animation.componentAnim;
import Helper.Comp.createComp;
import Helper.Comp.createJFX;
import Helper.Comp.createScroll;
import Helper.fileSystem.videoSystem;
import LoginSystem.isLogin;
import LoginSystem.LoginPage.Customer;
import frontPage.FaQConfig;
import frontPage.isDarkTheme;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Collections;
import java.util.stream.Stream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Components extends JPanel {

    JFrame frame;
    JScrollPane scrollPane;

    initializer i;
    isDarkTheme isDarkTheme;
    SwitchThemeComp Switch;
    isLogin isLogin;
    Window window;
    
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
        setPreferredSize(new Dimension(1280, 5750));

    }

    public void addScroll(JScrollPane scrollPane) { 
        this.scrollPane = scrollPane; 
    }

    /*//////////////////////////////////////////////////////////////
                          background gradient
    //////////////////////////////////////////////////////////////*/

    private static final Color BLACK = Color.decode("#1a1919");  
    private static final Color DARK_BLUE = new Color(25, 25, 128);
    private static final Color WHITE = Color.decode("#f5e6f3");
    private static final Color PINK = new Color(255, 192, 203);    

   /*//////////////////////////////////////////////////////////////
                         paint background theme
    //////////////////////////////////////////////////////////////*/    
    
    @Override
    protected void paintComponent(Graphics G) {
        super.paintComponent(G);
        Graphics2D G2D = (Graphics2D) G;

        G2D.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        // determine the gradient colors
        Color topColor = isDarkTheme.isDarkTheme ? BLACK : WHITE;
        Color bottomColor = isDarkTheme.isDarkTheme ? DARK_BLUE : PINK;

        // setup gradient
        GradientPaint gradient = new GradientPaint(
            0, 0, topColor, 
            getWidth(), getHeight(), bottomColor
        );  

        // apply gradient
        G2D.setPaint(gradient);
        G2D.fillRect(0, 0, getWidth(), getHeight());
    }       

    /*//////////////////////////////////////////////////////////////
                             add JTextArea
    //////////////////////////////////////////////////////////////*/    
    
    /**
     * adding texts into JTextArea data type, usually long text
     * 
     * @param _text texts to be added into JTextArea
     * @param startX X coordinate of animation start from
     * @param startY Y coordinate of animation start from
     * @param targetX X coordinate of animation to
     * @param targetY Y coordinate of animation to
     * @param width the length width of the JTextArea
     * @param height the length height of the JTextArea
     * @param font the type font to be used for the text
     * @param border JTextArea box border
     * @param textColor text color
     * 
     */
    public void addJTextArea(
        String _text, 
        int startX, int startY,
        int targetX, int targetY,
        int width, int height,
        Font font, Border border,
        Color textColor
    ) {
        JTextArea text = createComp.createJTextArea(
            _text, 
            startX, startY, 
            width, height, 
            font, border, 
            textColor
        );
        
        Switch.texts.add(text);
        add(text);
        
        new componentAnim(
            text, 
            startX, startY, 
            targetX, targetY, 
            scrollPane
        );
    }

    /*//////////////////////////////////////////////////////////////
                              add JButton
    //////////////////////////////////////////////////////////////*/
     
    /**
     * 
     * adding texts into JButton data type, usually clickable
     * 
     * @param _text texts to be added into JButton
     * @param startX X coordinate of animation start from
     * @param startY Y coordinate of animation start from
     * @param targetX X coordinate of animation to
     * @param targetY Y coordinate of animation to
     * @param width the length width of the JButton
     * @param height the length height of the JButton
     * @param border the border of the JButton
     * @param textColor text color
     * @return a JButton, usually when there'a an additional action required
     * `
     */
    public JButton addJButton(
        String _text, 
        int startX, int startY,
        int targetX, int targetY,
        int width, int height,
        Border border, 
        Color textColor
    ) {
        JButton button = createComp.createJButton(
            _text, 
            startX, startY, 
            width, height, 
            border, textColor
        );

        Switch.TButtons.add(button);
        add(button);

        new componentAnim(
            button, 
            startX, startY, 
            targetX, targetY, 
            scrollPane
        );

        return button; 
    }   
    
    /**
     * 
     * adding image/icon into JButton, usually clickable
     * 
     * @param icon image/icon to be added into JButton
     * @param startX X coordinate of animation start from
     * @param startY Y coordinate of animation start from
     * @param targetX X coordinate of animation to
     * @param targetY Y coordinate of animation to
     * @param width the length width of the JButton
     * @param height the length height of the JButton
     * @return a JButton, usually when there's an additional action required
     * 
     */
    public JButton addJButton(
        ImageIcon icon,
        int startX, int startY,
        int targetX, int targetY,
        int width, int height
    ) {
        JButton button = createComp.createJButton(
            icon, 
            startX, startY, 
            width, height
        );
        
        Switch.IButtons.add(button);
        add(button);
        
        new componentAnim(
            button, 
            startX, startY, 
            targetX, targetY, 
            scrollPane
        );
        
        return button;
    }

    /*//////////////////////////////////////////////////////////////
                               add JLabel
    //////////////////////////////////////////////////////////////*/
     
    /**
     * 
     * adding texts into JLabel, usually short text.
     * 
     * @param _text texts to be added into JLabel
     * @param startX X coordinate of animation start from
     * @param startY Y coordinate of animation start from
     * @param targetX X coordinate of animation to
     * @param targetY Y coordinate of animation to
     * @param width the length width of the JLabel
     * @param height the length height of the JLabel
     * @param font the type font to be used for the text
     * @param darkColor text color when light theme
     * @param lightColor text color when dark theme
     * 
     */
    public void addJLabel(
        String _text,
        int startX, int startY,
        int targetX, int targetY,
        int width, int height,
        Font font, Color textColor
    ) {        
        JLabel label = createComp.createJLabel(
            _text, 
            startX, startY, 
            width, height, 
            font, textColor
        );
        
        Switch.TLabels.add(label);
        add(label);

        new componentAnim(
            label, 
            startX, startY, 
            targetX, targetY, 
            scrollPane
        );
    }
    
    /**
     * 
     * adding image/icon into JLabel, normal image/icon
     * 
     * @param icon image/icon to be added into JLabel
     * @param startX X coordinate of animation start from
     * @param startY Y coordinate of animation start from
     * @param targetX X coordinate of animation to
     * @param targetY Y coordinate of animation to
     * @param width the length width of the JLabel
     * @param height the length height of the JLabel
     * 
     */
    public void addJLabel(
        ImageIcon icon,
        int startX, int startY,
        int targetX, int targetY,
        int width, int height
    ) {
        JLabel label = createComp.createJLabel(
            icon, 
            startX, startY, 
            width, height
        );
        
        Switch.ILabels.add(label);
        add(label);

        new componentAnim(
            label, 
            startX, startY, 
            targetX, targetY, 
            scrollPane    
        );
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
    
        scrollPane.getViewport().addChangeListener(_ -> {
            if (animation.isFullyVisible()) {
                if (!fJfxPanel.isVisible()) animation.start();
            } else {
                if (fJfxPanel.isVisible()) animation.exit(player);
            }
        });
    
        SwingUtilities.invokeLater(() -> {
            if (animation.isFullyVisible()) {
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


        new componentAnim(boxPanel, x, y - 100, x, y, scrollPane);
    }

    /*//////////////////////////////////////////////////////////////
                         login and view profile
    //////////////////////////////////////////////////////////////*/    

    public void initializeProfile() {
        switch (isLogin.isLogin ? 1 : 0) {
            case 0: _initializeLoginPage(); break;
        
            case 1: System.out.println("viewing profile"); break;
        }
    }

    protected void _initializeLoginPage() {        
        i.Customer = new Customer(i, window);

        int X = (1280 - 1000) / 2; 
        int Y = (720 - 500) / 2;

        i.Customer.setBounds(X, Y, 1000, 500);
        i.Customer.setVisible(true);

        frame.getContentPane().add(i.Customer);

        componentAnim anim = new componentAnim(
            i.Customer, 
            X, Y - 100, 
            X, Y, 
            scrollPane
        );
        anim.start();
    }
    
    
    /*//////////////////////////////////////////////////////////////
                           switch color theme
    //////////////////////////////////////////////////////////////*/
     
    public void switchTheme() {
        repaint();
        revalidate();

        Switch.switchTheme();
    }  

}