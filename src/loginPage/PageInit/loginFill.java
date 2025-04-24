package loginPage.PageInit;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Helper.Comp.createComp;
import Helper.RoundedBorder.roundedBorder;
import Helper.RoundedBorder.roundedBorderFactory;

public class loginFill {

    public static JLabel setLabel(String _text, int X, int Y, int width, int height) {
        return createComp.createJLabel(
            _text,
            X, Y,
            width, height,
            new Font("Arial", Font.BOLD, 18),
            Color.BLACK
        );
    }
    
    public static JTextField FillWithJTextArea(int X, int Y) {
        return createComp.createJTextField(
            X, Y,
            300, 40, 
            new Font("Arial", Font.BOLD, 18), 
            roundedBorderFactory.create(
                10, 
                Color.BLACK,
                null
            ),
            Color.BLACK
        );
    }

    public static JPasswordField FillWithJPassWordField(int X, int Y) {
        return createComp.createJPasswordField(
            X, Y, 
            300, 40, 
            '*', Color.BLACK, 
            new Font(null, Font.BOLD, 18), 
            roundedBorderFactory.create(
                10, 
                Color.BLACK, 
                null
            )
        );
    }

    public static JPanel Panel() {
        JPanel panel = createComp.createJPanel(
            0, 0, 
            500, 500, 
            new roundedBorder(20, Color.BLACK, Color.GRAY)
        );
        panel.setVisible(true);
        
        return panel;
    }
    

}
