package Components;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Helper.RoundedBorder.roundedBorder;
import Helper.RoundedBorder.roundedBorderFactory;
import Helper.fileSystem.imageSystem;
import frontPage.isDarkTheme;

public class SwitchThemeComp {

    isDarkTheme isDarkTheme;

    /*//////////////////////////////////////////////////////////////
                          store all components
    //////////////////////////////////////////////////////////////*/    

    ArrayList<JTextArea> texts = new ArrayList<>();

    ArrayList<JButton> TButtons = new ArrayList<>();
    ArrayList<JButton> IButtons = new ArrayList<>();

    ArrayList<JLabel> TLabels = new ArrayList<>();
    ArrayList<JLabel> ILabels = new ArrayList<>();

    ArrayList<JPanel> JPanels = new ArrayList<>();

    /*//////////////////////////////////////////////////////////////
                              constructor
    //////////////////////////////////////////////////////////////*/
     
    SwitchThemeComp(initializer i) { this.isDarkTheme = i.isDarkTheme; }    
    
    /*//////////////////////////////////////////////////////////////
                  switch components according to theme
    //////////////////////////////////////////////////////////////*/    
    
    public void switchTheme() {
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
    protected void _switchTexts() {
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
                        roundedBorderFactory.create(
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
    protected void _switchTButton() {
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
                        roundedBorderFactory.create(
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

            // faq + and -
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
    protected void _switchIButton() {
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
    protected void _switchTLabel() {
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
    protected void _switchILabel() {
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
    protected void _switchJPanels() {
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

    /*//////////////////////////////////////////////////////////////
                               clear data
    //////////////////////////////////////////////////////////////*/
    
    public void clearEverything() {
        texts.clear();
        TButtons.clear();
        IButtons.clear();
        TLabels.clear();
        ILabels.clear();
        JPanels.clear();
    }

}