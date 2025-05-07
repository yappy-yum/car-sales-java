package Helper.login;

import java.awt.Color;
import java.awt.Font;
import java.util.Enumeration;
 
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import Helper.Comp.createComp;
import Helper.RoundedBorder.roundedBorderFactory;
import Helper.fileSystem.imageSystem;

public class loginFill {
    
    public static class createLabelAndLongJTextField {
        public JLabel label;
        public JTextField textField;

        public createLabelAndLongJTextField(
            String _text,
            int X, int Y
        ) {
            label = createComp.createJLabel(
                _text, 
                X, Y, 
                200, 40, 
                new Font("Arial", Font.BOLD, 18), 
                Color.BLACK
            );
            label.setVisible(false);

            textField = createComp.createJTextField(
                X + 120, Y, 
                300, 40, 
                new Font("Arial", Font.BOLD, 18), 
                roundedBorderFactory.create(
                    10, 
                    Color.BLACK,
                    null
                ),
                Color.BLACK
            );
            textField.setVisible(false);
        }

        public void setUnEditable() {
            textField.setEditable(false);
        }

        public void setEditable() {
            textField.setEditable(true);
        }
    }

    public static class createLabelAndShortJTextField {
        public JLabel label;
        public JTextField textField;

        public createLabelAndShortJTextField(
            String _text,
            int X, int Y
        ) {
            label = createComp.createJLabel(
                _text, 
                X, Y, 
                200, 40, 
                new Font("Arial", Font.BOLD, 18), 
                Color.BLACK
            );
            label.setVisible(false);

            textField = createComp.createJTextField(
                X + 190, Y, 
                230, 40, 
                new Font("Arial", Font.BOLD, 18), 
                roundedBorderFactory.create(
                    10, 
                    Color.BLACK,
                    null
                ),
                Color.BLACK
            );
            textField.setVisible(false);
        }

        public void setUnEditable() {
            textField.setEditable(false);
        }
    }

    public static class createLabelAndLongJPasswordField {
        public JLabel label;
        public JPasswordField passwordField;
        public JButton button;

        boolean isPasswordVisible = false;
        ImageIcon PASSWORD_SHOW = imageSystem._scaleImage(imageSystem.PASSWORD_SHOW, 30, 30);
        ImageIcon PASSWORD_HIDE = imageSystem._scaleImage(imageSystem.PASSWORD_HIDE, 30, 30);

        public createLabelAndLongJPasswordField(
            String _text,
            int X, int Y
        ) {
            label = createComp.createJLabel(
                _text, 
                X, Y, 
                200, 40, 
                new Font("Arial", Font.BOLD, 18), 
                Color.BLACK
            );
            label.setVisible(false);

            passwordField = createComp.createJPasswordField(
                X + 120, Y, 
                300, 40, 
                '*', Color.BLACK, 
                new Font(null, Font.BOLD, 15), 
                roundedBorderFactory.create(
                    10, 
                    Color.BLACK, 
                    null
                )
            );
            passwordField.setVisible(false);

            button = createComp.createJButton(
                PASSWORD_HIDE, 
                (X + 120) + 255, Y + 5,
                30, 30
            );
            button.setVisible(false);
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

        }

        public void setUnEditable() {
            passwordField.setEditable(false);
        }

        public void setEditable() {
            passwordField.setEditable(true);
        }

    }

    public static class createLabelAndShortJPasswordField {
        public JLabel label;
        public JPasswordField passwordField;
        public JButton button;

        boolean isPasswordVisible = false;
        ImageIcon PASSWORD_SHOW = imageSystem._scaleImage(imageSystem.PASSWORD_SHOW, 30, 30);
        ImageIcon PASSWORD_HIDE = imageSystem._scaleImage(imageSystem.PASSWORD_HIDE, 30, 30);

        public createLabelAndShortJPasswordField(
            String _text,
            int X, int Y
        ) {
            label = createComp.createJLabel(
                _text, 
                X, Y, 
                200, 40, 
                new Font("Arial", Font.BOLD, 18), 
                Color.BLACK
            );
            label.setVisible(false);

            passwordField = createComp.createJPasswordField(
                X + 190, Y, 
                230, 40, 
                '*', Color.BLACK, 
                new Font(null, Font.BOLD, 15), 
                roundedBorderFactory.create(
                    10, 
                    Color.BLACK, 
                    null
                )
            );
            passwordField.setVisible(false);

            button = createComp.createJButton(
                PASSWORD_HIDE, 
                (X + 190) + 185, Y + 5,
                30, 30
            );
            button.setVisible(false);
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
        }

        public void setUnEditable() {
            passwordField.setEditable(false);
        }

        public void setEditable() {
            passwordField.setEditable(true);
        }

    }

    public static class createGender {
        public JToggleButton[] gender = new JToggleButton[3];
        public JLabel[] label = new JLabel[3];
        public JTextField others = new JTextField();

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

                // create label
                label[i] = createComp.createJLabel(
                    normalGender[i], 
                    X + (i * 95), Y - 30,
                    100, 100, 
                    new Font("Arial", Font.BOLD, 15), 
                    Color.BLACK
                );
            }

            // create text field
            others = createComp.createJTextField(
                X + 290, Y, 
                155, 40, 
                new Font("Arial", Font.BOLD, 18), 
                roundedBorderFactory.create(
                    10, 
                    Color.BLACK,
                    null
                ),
                Color.BLACK
            );
            others.setEditable(false);
        }

        public boolean isOthersSelectedButEmpty() {
            return gender[2].isSelected() && others.getText().trim().isEmpty();
        }

        public String getSelectedGender() {
            if (gender[1].isSelected() || gender[0].isSelected()) {
                return 
                    gender[0].isSelected() ? 
                    gender[0].getActionCommand() : 
                    gender[1].getActionCommand();
            } else if (gender[2].isSelected()) {
                return others.getText();
            }

            return null;
        }

        public void makeUnclickable() {
            for (JToggleButton i : gender) {
                i.setEnabled(false);
            }
            _setUnEditable();
        }

        public void _makeclickable() {
            for (JToggleButton i : gender) {
                i.setEnabled(true);
            }
            _setEditable();
        }

        void _setUnEditable() {
            others.setEditable(false);
        }

        void _setEditable() {
            others.setEditable(true);
        }
    }

}
