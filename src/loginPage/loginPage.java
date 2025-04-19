package loginPage;

import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Helper.RoundedBorder.roundedBorder;
import frontPage.isDarkTheme;

public class loginPage extends JPanel {
    JLayeredPane layeredPane = new JLayeredPane();
    isDarkTheme isDarkTheme;
    
    public loginPage(isDarkTheme isDarkTheme) {
        this.isDarkTheme = isDarkTheme;

        setBounds(440, 500, 400, 500);
        setBorder(new roundedBorder(40, Color.PINK, Color.BLUE));
        setFocusable(false);
        setOpaque(false);
        
    }

}