package frontPage;

import javax.swing.JPanel;

import Components.SwitchThemeComp;

/**
 * a global centralize class that determine the background theme
 * 
 * @author yappy-yum
 * 
 */
public class isDarkTheme {
    
    /**
     * 
     * by default would be in dark theme
     * 
     */
    public boolean isDarkTheme = true;


    /**
     * help to switch the current background theme
     * 
     * <p>
     * 
     * <b> 
     * can only be called when user switch the background theme
     * by clicking the theme icon in the top right corner of the window
     * <b>
     * 
     */
    public void switchTheme(JPanel panel, SwitchThemeComp s) {
        isDarkTheme = !isDarkTheme; 

        panel.repaint();
        panel.revalidate();

        s.switchTheme();
    }

}