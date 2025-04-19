package Components;

import Helper.Animation.videoAnim;
import Helper.Animation.componentAnim;
import Helper.Comp.createComp;
import Helper.Comp.createJFX;
import Helper.Comp.createScroll;
import Helper.RoundedBorder.roundedBorder;
import Helper.RoundedBorder.roundedBorderFactory;
import Helper.fileSystem.imageSystem;
import Helper.fileSystem.videoSystem;
import frontPage.isDarkTheme;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.Border;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class Components extends JPanel {

    JScrollPane scrollPane;
    isDarkTheme isDarkTheme;

    ArrayList<JTextArea> texts = new ArrayList<>();

    ArrayList<JButton> TButtons = new ArrayList<>();
    ArrayList<JButton> IButtons = new ArrayList<>();

    ArrayList<JLabel> TLabels = new ArrayList<>();
    ArrayList<JLabel> ILabels = new ArrayList<>();

    ArrayList<JPanel> JPanels = new ArrayList<>();
    
    /*//////////////////////////////////////////////////////////////
                              constructor
    //////////////////////////////////////////////////////////////*/    
    
    public Components(isDarkTheme i) {
        this.isDarkTheme = i;

        setLayout(null);
        setPreferredSize(new Dimension(1280, 8000));
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
     * 
     * adding texts into JTextArea data type, usually long text
     * 
     * @param _text texts to be added
     * @param startX X coordinate of animation start from
     * @param startY Y coordinate of animation start from
     * @param targetX X coordinate of animation to
     * @param targetY Y coordinate of animation to
     * @param width the length width of the JTextArea
     * @param height the length height of the JTextArea
     * @param font the type font to be used for the text
     * @param darkColor text color when light theme
     * @param lightColor text color when dark theme
     * 
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
        
        texts.add(text);
        add(text);
        
        // add animation effects
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
     * @param darkColor text color when light theme
     * @param lightColor text color when dark theme
     * @return a JButton, usually when there'a an additional action required
     * 
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

        TButtons.add(button);
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
     * @param border the border of the JButton
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
        
        IButtons.add(button);
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
        
        TLabels.add(label);
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
        
        ILabels.add(label);
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
     * Attaches a custom scroll wheel listener to the provided JFXPanel,
     * allowing scroll input even when the mouse hovers over a JavaFX video.
     *
     * @param panel The {@link JFXPanel} that should listen for scroll wheel events.
     *
     * <p>
     * This method uses {@link SwingUtilities#invokeLater} to ensure the listener is added
     * on the Swing event dispatch thread. The scroll delta is scaled by 20 pixels per notch
     * to match the main scroll behavior in the application.
     * 
     */
    public void addShortVideo(Media video, int x, int y, int width, int height) {
        JFXPanel fJfxPanel = createJFX.createJFXPanel(x, y, width, height);
        add(fJfxPanel);
    
        MediaView mediaView = videoSystem._adjustVideoSize(video, width, height);
        MediaPlayer player = mediaView.getMediaPlayer();
        player.setOnEndOfMedia(() -> player.seek(Duration.ZERO));
    
        createJFX.setupJavaFXScene(fJfxPanel, mediaView, width, height);
        attachScrollWheelListener(fJfxPanel);
        
        videoAnim animation = new videoAnim(fJfxPanel, x, y + 90, x, y, 1500, scrollPane, () -> {
            fJfxPanel.setVisible(true);
            player.play();
        });
    
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
    
    private void attachScrollWheelListener(JFXPanel panel) {
        createScroll.mouseScroll(panel, scrollPane);
    }    

    /*//////////////////////////////////////////////////////////////
                                add FaQ
    //////////////////////////////////////////////////////////////*/    

    public void _createFAQ(int x, int y, String question, String answer) {
        int panelWidth = 1000;
        int questionWidth = 700;
        int answerWidth = panelWidth - 20;
        int padding = 20;

        Color textColor = isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE;

        JTextArea questionLabel = _addQuestion(question, questionWidth, textColor, padding);
        JTextArea answerLabel = _addAnswer(answer, answerWidth, textColor, padding);
        JButton toggleButton = _addButton(panelWidth, textColor);
        JPanel boxPanel = _addBox(x, y, panelWidth);

        // Estimate real answer height
        int lineHeight = answerLabel.getFontMetrics(answerLabel.getFont()).getHeight();
        int charPerLine = answerWidth / answerLabel.getFontMetrics(answerLabel.getFont()).charWidth('a');
        int estimatedLines = (int) Math.ceil((double) answer.length() / charPerLine);
        int answerHeight = lineHeight * estimatedLines + 10;

        toggleButton.addActionListener( _ -> {
            boolean isExpanded = !answerLabel.isVisible();
            toggleButton.setText(isExpanded ? "-" : "+");

            Timer slideTimer = new Timer(8, null);
            int[] currentHeight = {
                isExpanded ? 0 : answerHeight
            };

            answerLabel.setVisible(true);

            slideTimer.addActionListener(_ -> {
                int step = 3;
                if (isExpanded) {
                    currentHeight[0] += step;
                    if (currentHeight[0] >= answerHeight) {
                        currentHeight[0] = answerHeight;
                        slideTimer.stop();
                    }
                } else {
                    currentHeight[0] -= step;
                    if (currentHeight[0] <= 0) {
                        currentHeight[0] = 0;
                        slideTimer.stop();
                        answerLabel.setVisible(false);
                    }
                }
            
                // Apply to answer
                answerLabel.setBounds(
                    answerLabel.getX(), 
                    answerLabel.getY(), 
                    answerLabel.getWidth(), 
                    currentHeight[0]
                );
            
                // ✅ Apply to boxPanel so the answer is visible!
                boxPanel.setSize(panelWidth, 60 + currentHeight[0]);
            });
            slideTimer.start();

        });

        JPanels.add(boxPanel);
        texts.add(questionLabel);
        texts.add(answerLabel);
        TButtons.add(toggleButton);

        boxPanel.add(questionLabel);
        boxPanel.add(answerLabel);
        boxPanel.add(toggleButton);
        this.add(boxPanel);

        new componentAnim(boxPanel, x, y - 100, x, y, scrollPane);
    }

    protected JTextArea _addQuestion(String question, int questionWidth, Color textColor, int padding) {
        JTextArea questionLabel = createComp.createJTextArea(
            question, 
            padding, 20, 
            questionWidth, 40, 
            new Font("Arial", Font.BOLD, 20), 
            null, 
            textColor
        );
        questionLabel.setLineWrap(true);
        questionLabel.setWrapStyleWord(true);
        return questionLabel;
    }

    protected JTextArea _addAnswer(String answer, int answerWidth, Color textColor, int padding) {
        JTextArea answerLabel = createComp.createJTextArea(
            answer,
            padding, 60,
            answerWidth, 100,
            new Font("Arial", Font.PLAIN, 16),
            null,
            textColor
        );
        answerLabel.setLineWrap(true);
        answerLabel.setWrapStyleWord(true);
        answerLabel.setVisible(false);
        return answerLabel;
    }

    protected JButton _addButton(int panelWidth, Color textColor) {
        return createComp.createJButton(
            "+", 
            panelWidth - 80, 10, 
            60, 35, 
            new roundedBorder(20, textColor, imageSystem._reduceColorTransparency(Color.GRAY, 0.2f)), 
            Color.PINK
        );
    }

    protected JPanel _addBox(int x, int y, int panelWidth) {
        JPanel FAQPanel = new JPanel();
        FAQPanel.setLayout(null);
        FAQPanel.setBorder(new roundedBorder(40, isDarkTheme.isDarkTheme ? Color.PINK : Color.BLACK, imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)));
        FAQPanel.setLocation(x, y);
        FAQPanel.setOpaque(false);
        FAQPanel.setSize(panelWidth, 60);

        return FAQPanel;
    }
    
    
    
    
    
    
    
    

    /*//////////////////////////////////////////////////////////////
                           switch color theme
    //////////////////////////////////////////////////////////////*/
     
    public void switchTheme() {
        repaint();
        revalidate();

        _switchTexts();

        _switchTButton();
        _switchIButton();

        _switchTLabel();
        _switchILabel();

        _switchJPanels();
    }  
    
    /**
     * 
     * adjust {@link #texts}
     */
    void _switchTexts() {
        for (JTextArea text : texts) {
            // black & white
            if (text.getForeground() == Color.BLACK || text.getForeground() == Color.WHITE) {
                text.setForeground(isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLACK);
            }
            
            // blue & pink
            if (text.getForeground() == Color.BLUE || text.getForeground() == Color.PINK) {
                text.setForeground(isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE);
                if (text.getBorder() != null) {
                    text.setBorder(
                        new roundedBorderFactory().create(
                        20,    
                        isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE,
                        imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)
                        )
                    );
                }
            }
        }        
    }

    /**
     * 
     * adjust {@link #TButtons}
     */
    void _switchTButton() {
        for (JButton button : TButtons) {
            // black & white
            if (button.getForeground() == Color.BLACK || button.getForeground() == Color.WHITE) {
                button.setForeground(
                    (isDarkTheme.isDarkTheme) ? 
                    (Color.WHITE) : 
                    (button.getY() > 1000 && button.getY() < 4500) ? (Color.WHITE) : (Color.BLACK)
                );
                if (button.getBorder() != null) {
                    button.setBorder(
                        new roundedBorderFactory().create(
                            40,    
                            (
                                (isDarkTheme.isDarkTheme) ? 
                                (Color.PINK) : 
                                (button.getY() > 1000 && button.getY() < 4500) ? (Color.PINK) : (Color.BLACK)
                            ),
                            imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)
                        )
                    );
                }
            }

            if (button.getText() == "+" || button.getText() == "-") {
                button.setForeground(isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE);
                button.setBorder(
                    new roundedBorder(
                        20, 
                        isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE, 
                        imageSystem._reduceColorTransparency(Color.GRAY, 0.2f)
                    )
                );
            }

        }        
    }

    /**
     * 
     * adjust {@link #IButtons}
     * 
     * @notice comparison will be done on the length width but not
     * the ImageIcon itself, as the ImageIcon itself failed to be compared
     */
    void _switchIButton() {
        for (JButton button : IButtons) {
            // light & dark theme
            ImageIcon lightButton = imageSystem._scaleImage(imageSystem.LIGHT_BUTTON, 50, 50);
            ImageIcon darkButton = imageSystem._scaleImage(imageSystem.DARK_BUTTON, 50, 50);
            if (button.getWidth() == 50 && button.getHeight() == 50) {
                button.setIcon(isDarkTheme.isDarkTheme ? lightButton : darkButton);
            }
        }        
    }

    /**
     * 
     * adjust {@link #TLabels}
     */
    void _switchTLabel() {
        for (JLabel label : TLabels) {
            // black & white
            if (label.getForeground() == Color.BLACK || label.getForeground() == Color.WHITE) {
                label.setForeground(isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLACK);
            }

            // big header
            Color DARK_GREEN = new Color(13, 15, 14);
            Color LIGHT_GREEN = new Color(59, 245, 99);
            // !! custom color on comparison is not working !!
            // if (label.getForeground() == DARK_GREEN || label.getForeground() == LIGHT_GREEN) {
            //     label.setForeground(isDarkTheme.isDarkTheme ? LIGHT_GREEN : DARK_GREEN);
            // }
            if (label.getWidth() == 600 && label.getHeight() == 100) {
                label.setForeground(isDarkTheme.isDarkTheme ? LIGHT_GREEN : DARK_GREEN);
            }

            // blue & pink
            if (label.getForeground() == Color.BLUE || label.getForeground() == Color.PINK) {
                label.setForeground(isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE);
            }
        }        
    }

    /**
     * 
     * adjust {@link #ILabels}
     * 
     * @notice comparison will be done on the length width but not
     * the ImageIcon itself, as the ImageIcon itself failed to be compared
     */
    void _switchILabel() {
        for (JLabel label : ILabels) {
            // highlight for big header
            ImageIcon yellowHighlight = imageSystem._reduceImageTransparency(imageSystem._scaleImage(imageSystem.YELLOW_HIGHLIGHT, 650, 250), 0.8f);
            ImageIcon greyHighlight = imageSystem._reduceImageTransparency(imageSystem._scaleImage(imageSystem.GREY_HIGHLIGHT, 650, 250), 0.5f);
            if (label.getWidth() == 650 && label.getHeight() == 97) {
                label.setIcon(isDarkTheme.isDarkTheme ? yellowHighlight : greyHighlight);
            }
        }
    }

    /**
     * 
     * adjust {@link #JPanels}
     */
    void _switchJPanels() {
        for (JPanel panel : JPanels) {
            // black & white
            if (panel.getY() > 4900) {
                panel.setBorder(
                    new roundedBorder(
                        40, 
                        isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE, 
                        imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)
                    )
                );
            }
        }
    }

}