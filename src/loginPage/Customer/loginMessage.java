package loginPage.Customer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import Helper.Comp.createComp;
import Helper.RoundedBorder.roundedBorder;
import Helper.fileSystem.fontSystem;
import Helper.fileSystem.imageSystem;

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

    public static class createPasswordInstructor {
        JButton button;
        JTextArea textArea;
        JTextArea textBackground;

        public createPasswordInstructor(int X, int Y) {

            button = createComp.createJButton(
                "i", 
                X, Y, 
                30, 30, 
                null, Color.BLACK
            );
            button.setFont(new Font("Arial", Font.BOLD, 17));
            button.setVisible(false);

            textArea = createComp.createJTextArea(
                """
                Password must contain at least:
                - 2 Capital letters
                - 2 Lowercase 
                - 2 Numbers
                - 2 Symbols
                - 2 Empty spaces
                """,
                X - 90, Y - 88,
                170, 90,
                new Font("Arial", Font.BOLD, 10),
                null, Color.BLACK
            );

            textBackground = createComp.createJTextArea(
                null, X - 95, Y - 92,
                170, 90,
                null, 
                new roundedBorder(5, Color.BLACK, imageSystem._reduceColorTransparency(Color.GRAY, 0.9f)), 
                null
            );

            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent E) {
                    textBackground.setVisible(true);
                    textArea.setVisible(true);
                }
    
                @Override
                public void mouseExited(MouseEvent E) {
                    textBackground.setVisible(false);
                    textArea.setVisible(false);
                }
            });

        }

        public List<Component> getComponents() {
            return Arrays.asList(textArea, textBackground, button);
        }

    }

    static JButton createRegisterNext(loginPage LP) {
        JButton button = createComp.createJButton(
            "Register ➜", 
            820, 440, 
            150, 50, 
            null, Color.BLACK
        );
        button.setFont(new Font("SansSerif", Font.PLAIN, 20));
        button.setVisible(true);

        button.addActionListener( _ -> {
            LP.CheckFirstRegister();
        });

        return button;
    }

    static JButton createRegister(loginPage LP) {
        JButton button = createComp.createJButton(
            "Register", 
            820, 440, 
            150, 50, 
            null, Color.BLACK
        );
        button.setFont(new Font("SansSerif", Font.PLAIN, 20));

        button.addActionListener( _ -> {
            LP.CheckSecondRegister();
        });

        return button;
    }

    static JButton createLoginNext(loginPage LP) {
        JButton button = createComp.createJButton(
            "Login ➜", 
            320, 440, 
            150, 50, 
            null, Color.BLACK
        );
        button.setFont(new Font("SansSerif", Font.PLAIN, 20));
        button.setVisible(true);

        button.addActionListener( _ -> {
            LP.CheckFirstLogin();
        });

        return button;
    }

    static JButton createForgetPassword(loginPage LP) {
        JButton button = createComp.createJButton(
            "forget password",
            320, 400,
            150, 50,
            null, Color.BLACK
        );
        button.setFont(new Font("SansSerif", Font.PLAIN, 20));
        button.setVisible(true);

        button.addActionListener( _ -> {
            LP.fullLogin();
        });

        return button;
    }

    static JLabel createErrorMessage(String message, int X, int Y) {
        JLabel label = createComp.createJLabel(
            message, X, Y, 
            450, 50, 
            new Font("Arial", Font.PLAIN, 15), 
            Color.RED
        );

        return label;
    } 

    static JButton passwordView(int X, int Y) {
        JButton button = createComp.createJButton(
            imageSystem._scaleImage(
                imageSystem.PASSWORD_HIDE, 
                30, 30
            ),
            X, Y, 
            30, 30
        );

        button.addActionListener( _ -> {
            button.setIcon(imageSystem._scaleImage(
                imageSystem.PASSWORD_SHOW, 
                30, 30
            ));
        });

        return button;

    }

}
