package loginPage.Customer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Component;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import Helper.Comp.createComp;
import Helper.RoundedBorder.roundedBorder;
import Helper.RoundedBorder.roundedBorderFactory;
import Helper.fileSystem.imageSystem;

public class loginFill {

    /*//////////////////////////////////////////////////////////////
                                 JLabel
    //////////////////////////////////////////////////////////////*/    
    
    /**
     * create label
     * 
     * @param _text label text
     * @param X X-coordinate    
     * @param Y Y-coordinate
     * @param width width length of the label
     * @param height height length of the label
     * @return created JLabel
     * 
     */
    public static JLabel setLabel(String _text, int X, int Y, int width, int height) {
        return createComp.createJLabel(
            _text,
            X, Y,
            width, height,
            new Font("Arial", Font.BOLD, 18),
            Color.BLACK
        );
    }

    /*//////////////////////////////////////////////////////////////
                               JTextField
    //////////////////////////////////////////////////////////////*/    
    
    /**
     * longer text field width
     * 
     * @param X X-coordinate
     * @param Y Y-coordinate
     * @return created JTextField
     * 
     */
    public static JTextField FillLongJTextArea(int X, int Y) {
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

    /**
     * shorter text field width
     * 
     * @param X X-coordinate
     * @param Y Y-coordinate
     * @return created JTextField
     * 
     */
    public static JTextField FillShortJTextArea(int X, int Y) {
        return createComp.createJTextField(
            X, Y,
            230, 40, 
            new Font("Arial", Font.BOLD, 18), 
            roundedBorderFactory.create(
                10, 
                Color.BLACK,
                null
            ),
            Color.BLACK
        );
    }

    /*//////////////////////////////////////////////////////////////
                             JPasswordField
    //////////////////////////////////////////////////////////////*/    

    /**
     * a sub-class that returns a longer JPasswordField for password 
     * filling box and a button that switch between show and hide password
     *  
     */
    public static class FillLongJPassWordField {
        JPasswordField passwordField;
        JButton button;

        ImageIcon PASSWORD_SHOW = imageSystem._scaleImage(imageSystem.PASSWORD_SHOW, 30, 30);
        ImageIcon PASSWORD_HIDE = imageSystem._scaleImage(imageSystem.PASSWORD_HIDE, 30, 30);

        boolean isPasswordVisible = false;

        public FillLongJPassWordField(int X, int Y) {
            passwordField = createComp.createJPasswordField(
                X, Y, 
                300, 40, 
                '*', Color.BLACK, 
                new Font(null, Font.BOLD, 15), 
                roundedBorderFactory.create(
                    10, 
                    Color.BLACK, 
                    null
                )
            );

            button = createComp.createJButton(
                PASSWORD_HIDE, 
                X + 255, Y + 5,
                30, 30
            );

            button.addActionListener( _ -> {
                isPasswordVisible = !isPasswordVisible;
    
                if (isPasswordVisible) {
                    passwordField.setEchoChar((char) 0);
                    button.setIcon(PASSWORD_SHOW);
                } else {
                    passwordField.setEchoChar('*');
                    button.setIcon(PASSWORD_HIDE);
                }
            });

            passwordField.setVisible(false);
            button.setVisible(false);
            
        }

        public List<Component> getComponents() {
            return Arrays.asList(button, passwordField);
        }
    }    

    /**
     * a sub-class that returns shorter JPasswordField for password 
     * filling box and a button that switch between show and hide password
     * 
     */
    public static class FillShortJPassWordField {
        JPasswordField passwordField;
        JButton button;

        ImageIcon PASSWORD_SHOW = imageSystem._scaleImage(imageSystem.PASSWORD_SHOW, 30, 30);
        ImageIcon PASSWORD_HIDE = imageSystem._scaleImage(imageSystem.PASSWORD_HIDE, 30, 30);

        boolean isPasswordVisible = false;

        public FillShortJPassWordField(int X, int Y) {
            passwordField = createComp.createJPasswordField(
                X, Y, 
                230, 40, 
                '*', Color.BLACK, 
                new Font(null, Font.BOLD, 15), 
                roundedBorderFactory.create(
                    10, 
                    Color.BLACK, 
                    null
                )
            );

            button = createComp.createJButton(
                PASSWORD_HIDE, 
                X + 185, Y + 5,
                30, 30
            );

            button.addActionListener( _ -> {
                isPasswordVisible = !isPasswordVisible;
    
                if (isPasswordVisible) {
                    passwordField.setEchoChar((char) 0);
                    button.setIcon(PASSWORD_SHOW);
                } else {
                    passwordField.setEchoChar('*');
                    button.setIcon(PASSWORD_HIDE);
                }
            });

            passwordField.setVisible(false);
            button.setVisible(false);
            
        }

        public List<Component> getComponents() {
            return Arrays.asList(button, passwordField);
        }
    }

    /*//////////////////////////////////////////////////////////////
                                 JPanel
    //////////////////////////////////////////////////////////////*/    

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

    /*//////////////////////////////////////////////////////////////
                             JToggleButton
    //////////////////////////////////////////////////////////////*/    

    public static class createGender {
        JToggleButton[] gender = new JToggleButton[3];
        JLabel[] label = new JLabel[3];
        JTextField others = new JTextField();

        ButtonGroup genderGroup = new ButtonGroup();
        String[] normalGender = {"Male", "Female", "Others"};

        public createGender(int X, int Y) {
            
            for (int i = 0; i < normalGender.length; i++) {
                final int index = i;

                // create toggle button
                gender[i] = createComp.createJToggleButton(
                    new Font("SansSerif", Font.BOLD, 30),
                    normalGender[i]
                );
                gender[i].setBounds(X + 10 + (i * 100), Y, 100, 40);
                genderGroup.add(gender[i]);

                // set toggle button action
                gender[i].addActionListener( _ -> {
                    Enumeration<AbstractButton> buttons = genderGroup.getElements();
                    while (buttons.hasMoreElements()) {
                        AbstractButton b = buttons.nextElement();
                        b.setText("⚪");
                    }
                    gender[index].setText("⚫");

                    if (gender[index].getActionCommand().equals("Others")) {
                        others.setEditable(true);
                    } else {
                        others.setEditable(false);
                        others.setText("");
                    }
                });

                label[i] = createComp.createJLabel(
                    normalGender[i], 
                    X + (i * 95), Y - 30, 
                    100, 100, 
                    new Font("SansSerif", Font.BOLD, 15), 
                    Color.BLACK
                );

            }

            others = createComp.createJTextField(
                X + 290, Y, 
                155, 40, 
                new Font(null, Font.BOLD, 15), 
                roundedBorderFactory.create(
                    10, 
                    Color.BLACK, 
                    null
                ),
                Color.BLACK
            );
            others.setEditable(false);

        }

        public List<Component> getAllComponents() {
            return Stream.of(
                Stream.of(others),
                Arrays.stream(gender),
                Arrays.stream(label)
            ).flatMap(s -> s).collect(Collectors.toList());
        }
    }
    

}
