package Helper.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

import Helper.Comp.createComp;
import Helper.Config.roundedBorder;
import Helper.fileSystem.fontSystem;
import Helper.fileSystem.imageSystem;

public class loginComp {
    
    /*//////////////////////////////////////////////////////////////
                                Welcome
    //////////////////////////////////////////////////////////////*/    
    
    /**
     * create JLabel of Header
     * 
     * @return created JLabel
     * 
     */
    public static JLabel createWelcomeHeader() {
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
    public static JTextArea createWelcomeSubHeader() {
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
    public static JButton createRL() {
        JButton button = createComp.createJButton(
            "login", 
            150, 320, 
            200, 50, 
            new roundedBorder(20, Color.BLACK, null), 
            Color.BLACK, fontSystem.SLAB.deriveFont(40f)
        );
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
    public static JButton createClose() {
        JButton button = createComp.createJButton(
            "close",
            205, 420,
            90, 50,
            new roundedBorder(20, Color.BLACK, null),
            Color.BLACK, fontSystem.SLAB.deriveFont(30f)
        );
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
    public static void SwitchMessage(boolean isLogin, JLabel header, JTextArea subHeader, JButton button) {
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

    /*//////////////////////////////////////////////////////////////
                          password instruction
    //////////////////////////////////////////////////////////////*/    
    
    public static class createPasswordInstructor {
        public JButton button;
        public JTextArea textArea;
        public JTextArea textBackground;

        public createPasswordInstructor(int X, int Y) {

            button = createComp.createJButton(
                "i", 
                X, Y, 
                30, 30, 
                null, Color.BLACK,
                new Font("Arial", Font.BOLD, 17)
            );
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
                new roundedBorder(
                    5, 
                    Color.BLACK, 
                    imageSystem._reduceColorTransparency(
                        Color.GRAY, 
                        0.9f
                    )
                ), 
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
    }    

    public static class createCarLengthInstructor {
        public JButton button;
        public JTextArea textArea;
        public JTextArea textBackground;

        public createCarLengthInstructor(int X, int Y) {

            button = createComp.createJButton(
                "i", 
                X, Y, 
                30, 30, 
                null, Color.BLACK,
                new Font("Arial", Font.BOLD, 17)
            );
            button.setVisible(false);

            textArea = createComp.createJTextArea(
                """
                LENGTH X HEIGHT

                Ex: 1000 X 500
                """,
                X - 40, Y - 48,
                100, 50,
                new Font("Arial", Font.BOLD, 10),
                null, Color.BLACK
            );

            textBackground = createComp.createJTextArea(
                null, X - 45, Y - 52,
                100, 50,
                null, 
                new roundedBorder(
                    5, 
                    Color.BLACK, 
                    imageSystem._reduceColorTransparency(
                        Color.GRAY, 
                        0.9f
                    )
                ), 
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
    }
    
    public static class CreateNonLoginBookInstructor {
        public JTextArea textArea;
        public JTextArea textBackground;

        public CreateNonLoginBookInstructor(int X, int Y) {

            textArea = createComp.createJTextArea(
                """
                Only Verified 
                Customer 
                Can Book
                """,
                X, Y,
                100, 60,
                new Font("Arial", Font.BOLD, 11),
                null, Color.BLACK
            );

            textBackground = createComp.createJTextArea(
                null, 
                X - 5, Y - 4,
                80, 50,
                null, 
                new roundedBorder(
                    5, 
                    Color.BLACK, 
                    imageSystem._reduceColorTransparency(
                        Color.GRAY, 
                        0.8f
                    )
                ), 
                null
            );

            textArea.setVisible(false);
            textBackground.setVisible(false);
        }
    }    

    public static JButton createFillNext(String _text, int X, int Y, int fontSize, Runnable method) {
        JButton button = createComp.createJButton(
            _text, 
            X, Y, 
            300, 50, 
            null, Color.BLACK,
            new Font("SansSerif", Font.PLAIN, fontSize)
        );

        button.addActionListener( _ -> {
            method.run();
        });

        return button;
    }

    public static JLabel createErrorMessage(String message, int X, int Y) {
        return createComp.createJLabel(
            message, X, Y, 
            500, 50, 
            new Font("Arial", Font.BOLD, 15), 
            Color.RED
        );
    } 

    public static JLabel createSuccessMessage(String message, int X, int Y) {
        return createComp.createJLabel(
            message, X, Y, 
            450, 50, 
            new Font("SansSerif", Font.BOLD, 15), 
            Color.GREEN
        );
    }

    public static JLabel createLoading(int X, int Y) {
        JLabel label = createComp.createJLabel(
            "Loading ", 
            X, Y, 
            450, 50,
            new Font("SansSerif", Font.BOLD, 15), 
            Color.BLACK
        );

        Timer timer = new Timer(
            500, 
            new ActionListener() {
                int dotCount = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    dotCount = (dotCount + 1) % 5; // cycle between 0 to 4 dots
                    String dots = ".".repeat(dotCount);
                    label.setText("Loading " + dots);
                }
            }
        );
        timer.start();

        return label;
    }

    /**
     * special JPanel for the login page of the welcome statement from the 
     * half of the login page panel
     * 
     * @return created JPanel
     * 
     */
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
