package loginPage.Customer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Components.Components;
import Components.Window;
import Components.initializer;
import Helper.blur;
import Helper.Animation.componentAnim;
import Helper.Comp.createComp;
import Helper.RoundedBorder.roundedBorder;
import Helper.fileSystem.imageSystem;
import loginPage.storage;

public class loginPage extends JPanel {

    JScrollPane pane;
    blur blur;
    Window window;
    Components component;
    storage storage;

    /*//////////////////////////////////////////////////////////////
                        Registration Components
    //////////////////////////////////////////////////////////////*/    

    Deque<Object> FirstPageRegister = new LinkedList<>(
        Arrays.asList(
            loginFill.setLabel("Registration", 700, 30, 110, 40),

            loginFill.setLabel("First Name:", 530, 90, 110, 40),
            loginFill.FillLongJTextArea(650, 90),

            loginFill.setLabel("Last Name:", 530, 160, 110, 40),
            loginFill.FillLongJTextArea(650, 160),

            loginFill.setLabel("Username:", 530, 230, 110, 40),
            loginFill.FillLongJTextArea(650, 230),

            loginFill.setLabel("Phone Number:", 530, 300, 150, 40),
            loginFill.FillShortJTextArea(720, 300),

            loginFill.setLabel("Age:", 530, 370, 110, 40),
            loginFill.FillShortJTextArea(720, 370),

            loginMessage.createRegisterNext(this)
        )
    );

    loginMessage.createPasswordInstructor instructor1 = new loginMessage.createPasswordInstructor(910, 200);
    loginFill.createGender gender = new loginFill.createGender(530, 30);
    loginFill.FillLongJPassWordField longPasswordField = new loginFill.FillLongJPassWordField(650, 230);
    loginFill.FillShortJPassWordField shortPasswordField1 = new loginFill.FillShortJPassWordField(720, 300);
    loginFill.FillShortJPassWordField shortPasswordField2 = new loginFill.FillShortJPassWordField(720, 370);
    Deque<Object> SecondPageRegister = new LinkedList<>(
        Arrays.asList(
            gender.getAllComponents().get(0), // label[0]
            gender.getAllComponents().get(1), // label[1]
            gender.getAllComponents().get(2), // label[2]
            gender.getAllComponents().get(3), // gender[0]
            gender.getAllComponents().get(4), // gender[1]
            gender.getAllComponents().get(5), // gender[2]
            gender.getAllComponents().get(6), // others textfield

            instructor1.getComponents().get(0),
            instructor1.getComponents().get(1),
            instructor1.getComponents().get(2),

            createComp.createJTextArea(
                """
                password is used for authentication    🔐

                favourite texts and numbers are password 
                alternative during the login process   🛡️
                """, 
                530, 110, 
                455, 100, 
                new Font("Segoe UI Emoji", Font.PLAIN, 20), 
                null, Color.BLACK
            ),

            loginFill.setLabel("Password:", 530, 230, 110, 40),
            longPasswordField.getComponents().get(0),
            longPasswordField.getComponents().get(1),

            loginFill.setLabel("Favourite Texts:", 530, 300, 170, 40),
            shortPasswordField1.getComponents().get(0),
            shortPasswordField1.getComponents().get(1),

            loginFill.setLabel("Favourite Numbers:", 530, 370, 190, 40),
            shortPasswordField2.getComponents().get(0),
            shortPasswordField2.getComponents().get(1),
            
            loginMessage.createRegister(this)
        )
    );
 
    /*//////////////////////////////////////////////////////////////
                            login Components
    //////////////////////////////////////////////////////////////*/   
     
    loginMessage.createPasswordInstructor instructor2 = new loginMessage.createPasswordInstructor(420, 260);
    loginFill.FillLongJPassWordField passwordField2 = new loginFill.FillLongJPassWordField(150, 290);
    Deque<Object> FirstPageLogin = new LinkedList<>(
        Arrays.asList(
            instructor2.getComponents().get(0),
            instructor2.getComponents().get(1),
            instructor2.getComponents().get(2),

            loginFill.setLabel("Username:", 30, 220, 110, 40),
            loginFill.FillLongJTextArea(150, 220),

            loginFill.setLabel("Password:", 30, 290, 110, 40),
            passwordField2.getComponents().get(0),
            passwordField2.getComponents().get(1),

            loginMessage.createLoginNext(this)

        )
    ); 

    loginFill.FillShortJPassWordField shortPasswordField3 = new loginFill.FillShortJPassWordField(150, 170);
    loginFill.FillShortJPassWordField shortPasswordField4 = new loginFill.FillShortJPassWordField(150, 240);
    Deque<Object> SecondPageLogin = new LinkedList<>(
        Arrays.asList(
            loginFill.setLabel("Phone Number", 30, 100, 110, 40),
            loginFill.FillLongJTextArea(150, 100),

            loginFill.setLabel("Favourite Texts", 30, 170, 170, 40),
            shortPasswordField3.getComponents().get(0),
            shortPasswordField3.getComponents().get(1),

            loginFill.setLabel("Favourite Numbers", 30, 240, 190, 40),
            shortPasswordField4.getComponents().get(0),
            shortPasswordField4.getComponents().get(1)

        )
    );

    /*//////////////////////////////////////////////////////////////
                       Registration Error Message
    //////////////////////////////////////////////////////////////*/    

    List<Object> RegisterFirstErrorMessage = new ArrayList<>(
        Arrays.asList(
            loginMessage.createErrorMessage("First name and Last name must only contain characters", 520, 410),
            loginMessage.createErrorMessage("Username has used", 520, 410),
            loginMessage.createErrorMessage("Phone number is not valid", 520, 410),
            loginMessage.createErrorMessage("only the age between 18 - 60", 520, 410)
        )
    );

    List<Object> RegisterSecondErrorMessage = new ArrayList<>(
        Arrays.asList(
            loginMessage.createErrorMessage("Gender is not selected", 520, 410),
            loginMessage.createErrorMessage("Password is weak", 520, 410),
            loginMessage.createErrorMessage("favourite text must contain only characters", 520, 410),
            loginMessage.createErrorMessage("favourite number must contain only numbers", 520, 410)
        )
    );

    /*//////////////////////////////////////////////////////////////
                          Login Error Message
    //////////////////////////////////////////////////////////////*/    

    List<Object> LoginErrorMessage = new ArrayList<>(
        Arrays.asList(
            loginMessage.createErrorMessage("incorrect username or/and password", 30, 410)
        )
    );

    /*//////////////////////////////////////////////////////////////
                              constructor
    //////////////////////////////////////////////////////////////*/    

    public loginPage(initializer i, Window w) {
        this.blur = new blur(i.frame);
        this.pane = i.scrollPane;
        this.component = i.component;
        this.storage = i.storage;
        this.window = w;

        background();
    }

    /*//////////////////////////////////////////////////////////////
                            login background
    //////////////////////////////////////////////////////////////*/    

    /**
     * 1. create the login page background
     * 
     * <p>
     * 
     * 1.1 {@link #halfNotice} create a half welcome page on the login page 
     * 
     * <p>
     * 
     * 1.2 {@link #_addComp} add all the components to the JPanel
     * 
     */
    protected void background() {
        setOpaque(false);
        setLayout(null);
        setBorder(
            new roundedBorder(
                20, 
                Color.BLACK, 
                imageSystem._reduceColorTransparency(Color.GRAY, 0.7f)
            )
        );

        halfNotice();
        _addComp();
    }

    /*//////////////////////////////////////////////////////////////
                                  half
    //////////////////////////////////////////////////////////////*/    

    /**
     * 
     * 1.1 create a half welcome page on the login page
     * 
     */
    protected void halfNotice() {
        JPanel half = loginFill.Panel();
        JLabel header = loginMessage.createWelcomeHeader();
        JTextArea subHeader = loginMessage.createWelcomeSubHeader();
        JButton button = loginMessage.createRL();
        JButton close = loginMessage.createClose();

        add(half);
        half.add(header);
        half.add(subHeader);
        half.add(button);
        half.add(close);

        // Add action listener to the button
        button.addActionListener( _ -> { toggleLoginRegister(header, subHeader, button, half); });
        close.addActionListener( _ -> {
            blur.removeBlur();
            SwingUtilities.invokeLater(() -> {
                window._reloadEverything();
            });
        });
    }

    /**
     * a helper method to switch the welcome statement and move the half 
     * panel to the other side, when "login" or ""register" button is clicked
     * 
     * @param header the welcome header
     * @param subHeader the welcome sub-header
     * @param button the login/register button
     * @param half the half background panel
     * 
     */
    protected void toggleLoginRegister(JLabel header, JTextArea subHeader, JButton button, JPanel half) {
        if (half.getLocation().x != 0 && half.getLocation().x != 500) return;

        boolean isLogin = half.getLocation().x == 0;
        loginMessage.SwitchMessage(isLogin, header, subHeader, button);

        int shift = isLogin ? 500 : -500;

        int X = half.getLocation().x;
        int Y = half.getLocation().y;

        // Animate the panel movement
        componentAnim anim = new componentAnim(half, X, Y, (X + shift), Y, pane);
        anim.setDuration(1500);
        anim.start();
    }    

    /*//////////////////////////////////////////////////////////////
                              Login System
    //////////////////////////////////////////////////////////////*/    

    /**
     * 2. initialize register and login stuff
     * 
     * <p>
     * 
     * 2.1 {@link #FillFirstRegister} fill the first show of registration
     * 2.2 {@link #FillLogin} fill the first show of login
     * 
     */
    protected void Fill() {
        
        FillFirstRegister();
        FillLogin();

    }

    /*//////////////////////////////////////////////////////////////
                        First Page Registration
    //////////////////////////////////////////////////////////////*/   

    /**
     * 
     * 2.1 fill the first show of registration
     * 
     */
    protected void FillFirstRegister() { 
        FirstPageRegister.forEach(i -> ((Component) i).setVisible(true)); 
    }

    /**
     * 
     * 2.1 when the "register ->" clicked, filled details is checked
     * 
     */
    protected void CheckFirstRegister() {

        RegisterFirstErrorMessage.forEach(i -> ((Component) i).setVisible(false));

        String FirstName = new String();
        String lastName = new String();
        String username = new String();
        String phoneNumber = new String(); 
        String age = new String();

        int index = 0;
        for (Object i : FirstPageRegister) {
            switch (index) {
                case 2:
                    FirstName = ((JTextField) i).getText();
                    break;
                case 4:
                    lastName = ((JTextField) i).getText();
                    break;
                case 6:
                    username = ((JTextField) i).getText();
                    break;
                case 8:
                    phoneNumber = ((JTextField) i).getText().trim();
                    break;
                case 10:
                    age = ((JTextField) i).getText().trim();
                    break;
            }
            index ++;
        }

        for (int i = 0; i < RegisterFirstErrorMessage.size(); i ++) {
            Object l = RegisterFirstErrorMessage.get(i);
            boolean shouldBreak = false;

            switch (i) {
                case 0:
                    if 
                    (
                        !shouldBreak &&
                        !FirstName.matches("[a-zA-Z]+") || 
                        !lastName.matches("[a-zA-Z]+") 
                    ) 
                    {
                        ((Component) l).setVisible(true);
                        shouldBreak = true;
                    }
                    break;
            
                case 1:
                    if 
                    (
                        !shouldBreak &&
                        username.isEmpty() || 
                        !storage.isUsernameUnique(username)
                    )
                    {
                        ((Component) l).setVisible(true);
                        shouldBreak = true;
                    }
                    break;

                case 2:
                    if 
                    (
                        !shouldBreak &&
                        !phoneNumber.chars().allMatch(Character::isDigit) ||
                        !(
                            phoneNumber.matches("^01[0-46-9][0-9]{7,8}$") ||
                            phoneNumber.matches("^0[3-9][0-9]{7,8}$")
                        )
                    )
                    {
                        ((Component) l).setVisible(true);
                        shouldBreak = true;
                    }
                    break;

                case 3:
                    if 
                    (
                        !shouldBreak &&
                        age.isEmpty() ||
                        !age.chars().allMatch(Character::isDigit) ||
                        Integer.parseInt(age) < 18 || 
                        Integer.parseInt(age) > 60
                    )
                    {
                        ((Component) l).setVisible(true);
                        shouldBreak = true;
                    }
                    break;
            }

            if (shouldBreak) return;

        }

        FirstPageRegister.forEach(i -> ((Component) i).setVisible(false));
        FillSecondRegister();

    }

    /*//////////////////////////////////////////////////////////////
                        Second Page Registration
    //////////////////////////////////////////////////////////////*/    

    /**
     * 
     * 2.1 once the registration checks has passed, show the second page
     * 
     */    
    protected void FillSecondRegister() { 
        int index = 0;
        for (Object i : SecondPageRegister) {
            switch (index == 7 || index == 8 ? 1 : 0) {
                case 0:
                    ((Component) i).setVisible(true);
                    break;
            
                case 1:
                    ((Component) i).setVisible(false);
                    break;
            }
            index ++;
        }
    }

    /**
     * 
     * 2.1 when the "register" clicked, filled details is checked
     * 
     */    
    protected void CheckSecondRegister() {
        RegisterSecondErrorMessage.forEach(i -> ((Component) i).setVisible(false));

        String password = new String();
        String favText = new String();
        String favNum = new String();

        int index = 0;
        for (Object i : SecondPageRegister) {
            switch (index) {
                case 13:
                    password = String.valueOf(((JPasswordField) i).getPassword());
                    break;
                case 16:
                    favText = ((JTextField) i).getText();
                    break;
                case 19:
                    favNum = ((JTextField) i).getText();
                    break;
            }
            index ++;
        }

        for (int i = 0; i < RegisterSecondErrorMessage.size(); i ++) {
            Object l = RegisterSecondErrorMessage.get(i);
            boolean shouldBreak = false;

            switch (i) {
                case 1:
                    if 
                    (
                        !shouldBreak &&
                        !storage.isYourPasswordStrong(password)
                    )
                    {
                        ((Component) l).setVisible(true);
                        shouldBreak = true;
                    }
                    break;
            
                case 2:
                    if 
                    (
                        !shouldBreak &&
                        !favText.matches("[a-zA-Z]+")
                    )
                    {
                        ((Component) l).setVisible(true);
                        shouldBreak = true;
                    }
                    break;

                case 3:
                    if 
                    (
                        !shouldBreak &&
                        !favNum.matches("[0-9]+") ||
                        !favNum.chars().allMatch(Character::isDigit)
                    )
                    {
                        ((Component) l).setVisible(true);
                        shouldBreak = true;
                    }
                    break;
            }


            if (shouldBreak) {
                System.out.println("register failed");
                return;
            };
        }

        System.out.println("register successful");

        // String firstName = new String();
        // String lastName = new String();
        // String username = new String();
        // String phoneNumber = new String();
        // String age = new String();        
        
        // int l = 0;
        // for (Object i : FirstPageRegister) {
        //     switch (l) {
        //         case 1:
        //             firstName = ((JTextField) i).getText();
        //             break;
        //         case 3:
        //             lastName = ((JTextField) i).getText();
        //             break;
        //         case 5:
        //             username = ((JTextField) i).getText();
        //             break;
        //         case 7:
        //             phoneNumber = ((JTextField) i).getText();
        //             break;
        //         case 9:
        //             phoneNumber = ((JTextField) i).getText();
        //             break;
        //         case 19:
        //             age = ((JTextField) i).getText();
        //             break;
        //     }
        //     l ++;
        // }

        
    }

    /*//////////////////////////////////////////////////////////////
                            First Page Login
    //////////////////////////////////////////////////////////////*/    

    /**
     * 
     * 2.2 Fill the first show of login
     * 
     */
    protected void FillLogin() {
        int index = 0;
        for (Object i : FirstPageLogin) {
            switch (index == 0 || index == 1 ? 1 : 0) {
                case 0:
                    ((Component) i).setVisible(true);
                    break;
                case 1:
                    ((Component) i).setVisible(false);
                    break;
            }
            index ++;
        }
    }    
    
    /**
     * 
     * 2.2 when the "Login ->" clicked, filled details is checked
     * 
     */
    protected void CheckFirstLogin() {
        LoginErrorMessage.forEach(i -> ((Component) i).setVisible(false));

        String username = new String();
        String password = new String();

        int index = 0;
        for (Object i : FirstPageLogin) {
            switch (index) {
                case 4:
                    username = ((JTextField) i).getText();
                    break;
                case 7:
                    password = String.valueOf(((JPasswordField) i).getPassword());
                    break;
            }
            index ++;
        }

        for (int i = 0; i < LoginErrorMessage.size(); i ++) {
            Object l = LoginErrorMessage.get(i);

            if 
            (
                !storage.login(username, password)
            ) {
                ((Component) l).setVisible(true);
            } 
            else return ;
        }
    }

    /*//////////////////////////////////////////////////////////////
                           Second Page Login
    //////////////////////////////////////////////////////////////*/    

    /**
     * 
     * 2.2 when user chose to have an alternative login method
     * 
     */
    protected void fullLogin() {
        FirstPageLogin.forEach(i -> ((Component) i).setVisible(false));
        SecondPageLogin.forEach(i -> ((Component) i).setVisible(true));
    }

    /**
     * 1.2 add all the components to the JPanel
     * 
     */
    protected void _addComp() {
        FirstPageRegister.forEach(i -> add((Component) i));
        FirstPageLogin.forEach(i -> add((Component) i));
        RegisterFirstErrorMessage.forEach(i -> add((Component) i));
        LoginErrorMessage.forEach(i -> add((Component) i));
        SecondPageRegister.forEach(i -> add((Component) i));
        SecondPageLogin.forEach(i -> add((Component) i));
        RegisterSecondErrorMessage.forEach(i -> add((Component) i));

        Fill();
    }

}
