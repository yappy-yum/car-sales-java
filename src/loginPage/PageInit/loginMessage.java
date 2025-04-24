package loginPage.PageInit;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import Helper.Comp.createComp;
import Helper.RoundedBorder.roundedBorder;
import Helper.fileSystem.fontSystem;

/**
 * this class is nothing but a helper to create some texts and buttons 
 * on the half sides to let user to navigate between login and register
 * 
 */
public class loginMessage {

    /*//////////////////////////////////////////////////////////////
                                Welcome
    //////////////////////////////////////////////////////////////*/    
    
    /**
     * create JLabel of Header
     * 
     * @return created JLabel
     * 
     */
    static JLabel createWelcomeHeader() {
        JLabel label = createComp.createJLabel(
            "Welcome Back", 
            150, 50, 
            300, 100, 
            fontSystem.SLAB.deriveFont(50f), 
            Color.BLACK
        );
        label.setVisible(true);

        return label;
    }

    /*//////////////////////////////////////////////////////////////
                              sub-Welcome
    //////////////////////////////////////////////////////////////*/    

    /**
     * create JTextArea of Sub-Header
     * 
     * @return created JTextArea
     * 
     */
    static JTextArea createWelcomeSubHeader() {
        JTextArea textArea = createComp.createJTextArea(
            """
            To keep connected with us please login
                      with your personal info        
            """, 
            80, 200, 
            400, 100, 
            new Font("SansSerif", Font.PLAIN, 20), 
            null, Color.BLACK
        );
        textArea.setVisible(true);


        return textArea;
    }

    /*//////////////////////////////////////////////////////////////
                         register/login button
    //////////////////////////////////////////////////////////////*/    
    
    /**
     * create JButton to let user switch between login and register
     * 
     * @return created JButton
     * 
     */
    static JButton createRL() {
        JButton button = createComp.createJButton(
            "login", 
            150, 320, 
            200, 50, 
            new roundedBorder(20, Color.BLACK, null), 
            Color.BLACK
        );
        button.setFont(fontSystem.SLAB.deriveFont(40f));
        button.setVisible(true);

        return button;
    }

    /*//////////////////////////////////////////////////////////////
                              close button
    //////////////////////////////////////////////////////////////*/    

    /**
     * create JButton to let user close the login page
     * 
     * @return created JButton
     * 
     */
    static JButton createClose() {
        JButton button = createComp.createJButton(
            "close",
            205, 420,
            90, 50,
            new roundedBorder(20, Color.BLACK, null),
            Color.BLACK
        );
        button.setFont(fontSystem.SLAB.deriveFont(30f));
        button.setVisible(true);

        return button;
    }

    /*//////////////////////////////////////////////////////////////
                                Switched
    //////////////////////////////////////////////////////////////*/    

    /**
     * a helper to switch the texts message when user switch between login and register
     * 
     * @param isLogin current action
     * @param header welcome header text
     * @param subHeader welcome sub-header text
     * @param button login/register button
     * 
     */
    static void SwitchMessage(boolean isLogin, JLabel header, JTextArea subHeader, JButton button) {
        header.setText(!isLogin ? "Welcome Back" : "Hello Friend");
        header.setBounds(!isLogin ? 150 : 180, 50, 300, 100);

        subHeader.setText(
            !isLogin 
            ? 
            """
            To keep connected with us please login
                      with your personal info        
            """  
            : 
            """
            Enter your personal details and
                  start journey with us
            """
        );
        subHeader.setBounds(
            !isLogin ? 80 : 120, 200, 
            400, 100
        );

        button.setText(!isLogin ? "login" : "register");
    }

}
