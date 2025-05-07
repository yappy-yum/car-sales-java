package loginPage.Customer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
import loginPage.Profile;
import loginPage.storage;
import Helper.login.loginFill;
import Helper.login.loginComp;

public class loginPage extends JPanel {

    JScrollPane pane;
    blur blur;
    Window window;
    Components component;
    storage storage;

    JButton LRButton;
    JButton close;

    Profile.userProfile user = new Profile.userProfile(
        null, null, null, 
        null, 0, 0, 
        null, null, 
        null, null
    );

    /*//////////////////////////////////////////////////////////////
                        Registration Components
    //////////////////////////////////////////////////////////////*/    

    loginFill.createLabelAndLongJTextField RegisterFirstName = new loginFill.createLabelAndLongJTextField("First Name:", 530, 90);
    loginFill.createLabelAndLongJTextField RegisterLastName = new loginFill.createLabelAndLongJTextField("Last Name:", 530, 160);
    loginFill.createLabelAndLongJTextField RegisterUsername = new loginFill.createLabelAndLongJTextField("Username:", 530, 230);
    loginFill.createLabelAndShortJTextField RegisterPhoneNum = new loginFill.createLabelAndShortJTextField("Phone Number:", 530, 300);
    loginFill.createLabelAndShortJTextField RegisterAge = new loginFill.createLabelAndShortJTextField("Age:", 530, 370);

    Deque<Object> FirstPageRegister = new LinkedList<>(
        Arrays.asList(
            createComp.createJLabel("Register", 700, 30, 110, 40, new Font("Arial", Font.BOLD, 18), Color.BLACK),

            RegisterFirstName.label,
            RegisterFirstName.textField,
            
            RegisterLastName.label,
            RegisterLastName.textField,

            RegisterUsername.label,
            RegisterUsername.textField,

            RegisterPhoneNum.label,
            RegisterPhoneNum.textField,

            RegisterAge.label,
            RegisterAge.textField,

            loginComp.createFillNext("Register ▶", 770, 440, 20, this::CheckFirstRegister)
        )
    );

    loginFill.createGender RegisterGender = new loginFill.createGender(530, 30);
    loginComp.createPasswordInstructor RegisterPasswordInstructor = new loginComp.createPasswordInstructor(910, 200);
    loginFill.createLabelAndLongJPasswordField RegisterPassword = new loginFill.createLabelAndLongJPasswordField("Password:", 530, 230);
    loginFill.createLabelAndShortJPasswordField RegisterFavText = new loginFill.createLabelAndShortJPasswordField("Favourite texts:", 530, 300);
    loginFill.createLabelAndShortJPasswordField RegisterFavNum = new loginFill.createLabelAndShortJPasswordField("Favourite numbers:", 530, 370);
    JButton register = loginComp.createFillNext("Register", 770, 440, 20, this::CheckSecondRegister);

    Deque<Object> SecondPageRegister = new LinkedList<>(
        Arrays.asList(
            RegisterGender.gender[0],
            RegisterGender.gender[1],
            RegisterGender.gender[2],

            RegisterGender.label[0],
            RegisterGender.label[1],
            RegisterGender.label[2],

            RegisterGender.others,

            RegisterPasswordInstructor.button,
            RegisterPasswordInstructor.textArea,
            RegisterPasswordInstructor.textBackground,

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

            RegisterPassword.button,
            RegisterPassword.label,
            RegisterPassword.passwordField,

            RegisterFavText.button,
            RegisterFavText.label,
            RegisterFavText.passwordField,

            RegisterFavNum.button,
            RegisterFavNum.label,
            RegisterFavNum.passwordField,

            register

        )
    );
 
    /*//////////////////////////////////////////////////////////////
                            login Components
    //////////////////////////////////////////////////////////////*/   
     
    loginFill.createLabelAndLongJTextField LoginUsername = new loginFill.createLabelAndLongJTextField("Username:", 30, 150);
    loginFill.createLabelAndLongJPasswordField LoginPassword = new loginFill.createLabelAndLongJPasswordField("Password:", 30, 250);
    JButton login1 = loginComp.createFillNext("Login ▶", 270, 440, 20, this::CheckFirstLogin);
    JButton alternative1 = loginComp.createFillNext("𝙵̲𝚘̲𝚛̲𝚐̲𝚎̲𝚝̲ ̲𝚄̲𝚜̲𝚎̲𝚛̲𝚗̲𝚊̲𝚖̲𝚎̲/̲𝙿̲𝚊̲𝚜̲𝚜̲𝚠̲𝚘̲𝚛̲𝚍̲?̲", 200, 310, 15, this::fullLogin);


    Deque<Object> FirstPageLogin = new LinkedList<>(
        Arrays.asList(
            createComp.createJLabel("Login", 250, 30, 110, 40, new Font("Arial", Font.BOLD, 18), Color.BLACK),

            LoginUsername.label,
            LoginUsername.textField,

            LoginPassword.button,
            LoginPassword.label,
            LoginPassword.passwordField,

            alternative1,

            login1
        )
    ); 

    loginFill.createLabelAndShortJTextField LoginPhoneNumber = new loginFill.createLabelAndShortJTextField("Phone Number:", 30, 90);
    loginFill.createLabelAndShortJPasswordField LoginFavNum = new loginFill.createLabelAndShortJPasswordField("Favourite number:", 30, 180);
    loginFill.createLabelAndShortJPasswordField LoginFavText = new loginFill.createLabelAndShortJPasswordField("Favourite text:", 30, 270);
    JButton login2 = loginComp.createFillNext("Login ▶", 270, 440, 20, this::checkSecondLogin);
    JButton alternative2 = loginComp.createFillNext("𝐿̲𝑜̲𝑔̲𝑖̲𝑛̲ 𝑢̲𝑠̲𝑖̲𝑛̲𝑔̲ 𝑈̲𝑠̲𝑒̲𝑟̲𝑛̲𝑎̲𝑚̲𝑒̲ 𝑎̲𝑛̲𝑑̲ 𝑃̲𝑎̲𝑠̲𝑠̲𝑤̲𝑜̲𝑟̲𝑑̲", 200, 310, 15, this::FillLogin);


    Deque<Object> SecondPageLogin = new LinkedList<>(
        Arrays.asList(    
            LoginPhoneNumber.label,
            LoginPhoneNumber.textField,

            LoginFavNum.button,
            LoginFavNum.label,
            LoginFavNum.passwordField,

            LoginFavText.button,
            LoginFavText.label,
            LoginFavText.passwordField,

            alternative2,

            login2

        )
    );

    /*//////////////////////////////////////////////////////////////
                       Registration Error Message
    //////////////////////////////////////////////////////////////*/    

    List<JLabel> RegisterFirstErrorMessage = new LinkedList<>(
        Arrays.asList(
            loginComp.createErrorMessage("First name and Last name must only contain characters", 505, 410),
            loginComp.createErrorMessage("Username has used", 505, 410),
            loginComp.createErrorMessage("Phone number is not valid", 505, 410),
            loginComp.createErrorMessage("only the age between 18 - 60", 505, 410)
        )
    );

    List<JLabel> RegisterSecondErrorMessage = new LinkedList<>(
        Arrays.asList(
            loginComp.createErrorMessage("Gender is not selected", 505, 410),
            loginComp.createErrorMessage("'Others' gender is selected, but did not explicitly named your gender", 505, 410),
            loginComp.createErrorMessage("Password is weak, hower 'i' for more", 505, 410),
            loginComp.createErrorMessage("favourite text must contain only characters", 505, 410),
            loginComp.createErrorMessage("favourite number must contain only numbers", 505, 410)
        )
    );

    /*//////////////////////////////////////////////////////////////
                          Login Error Message
    //////////////////////////////////////////////////////////////*/  

    JLabel[] LoginErrorMessage = {
        loginComp.createErrorMessage("incorrect username or/and password", 30, 410),
        loginComp.createErrorMessage("incorrect phone number or/and favourite text or/and number", 30, 410)
    };

    /*//////////////////////////////////////////////////////////////
                            Success Message
    //////////////////////////////////////////////////////////////*/
     
    JLabel[] successLabel = {
        loginComp.createSuccessMessage("◀ Register Success, click 'Close' to reload and login again", 510, 410),
        loginComp.createSuccessMessage("Login Success, click 'Close' to reload and login again ▶", 30, 380)
    }; 
    
    JLabel[] loadingLabel = {
        loginComp.createLoading(530, 410),
        loginComp.createLoading(30, 380)
    };

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
        JPanel half = loginComp.Panel();
        JLabel header = loginComp.createWelcomeHeader();
        JTextArea subHeader = loginComp.createWelcomeSubHeader();
        LRButton = loginComp.createRL();
        close = loginComp.createClose();

        add(half);
        half.add(header);
        half.add(subHeader);
        half.add(LRButton);
        half.add(close);

        // Add action listener to the button
        LRButton.addActionListener( _ -> { toggleLoginRegister(header, subHeader, LRButton, half); });
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
        loginComp.SwitchMessage(isLogin, header, subHeader, button);

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
        SecondPageRegister.forEach(i -> ((Component) i).setVisible(false));
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
                        username.trim().isEmpty() || 
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

        user.status = Profile.userProfile.Status.CUSTOMER;
        user.firstName = FirstName;
        user.lastName = lastName;
        user.username = username;
        user.phoneNumber = Integer.parseInt(phoneNumber);
        user.age = Integer.parseInt(age);

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
            switch (index == 9 || index == 8 ? 1 : 0) {
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

        String gender = new String();
        String password = new String();
        String favText = new String();
        String favNum = new String();

        gender = RegisterGender.getSelectedGender();
        
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
                case 0:
                    if 
                    (
                        !shouldBreak &&
                        gender == null || gender == ""
                    ) {
                        ((Component) l).setVisible(true);
                        shouldBreak = true;
                    }
                    break;

                case 1:
                    if 
                    (
                        !shouldBreak &&
                        RegisterGender.isOthersSelectedButEmpty()
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
                        !storage.isYourPasswordStrong(password)
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
                        !favText.matches("[a-zA-Z]+")
                    )
                    {
                        ((Component) l).setVisible(true);
                        shouldBreak = true;
                    }
                    break;

                case 4:
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


            if (shouldBreak) return;
        }

        user.gender = gender;
        user.password = password;
        user.favText = favText;
        user.favNum = favNum;  
        
        _register();
    }

    protected void _register() {
        _makeUnclickable();
        loadingLabel[0].setVisible(true);

        new Thread(() -> {
            storage.customerRegister(user);

            SwingUtilities.invokeLater(() -> {
                System.out.println("Username: " + user.username);
                System.out.println("Password: " + storage.Users.get(user.username).password);
                System.out.println("Favourite Texts: " + storage.Users.get(user.username).favText);
                System.out.println("Favourite Number: " + storage.Users.get(user.username).favNum);
                loadingLabel[0].setVisible(false);
                successLabel[0].setVisible(true);
                close.setEnabled(true);
            });
        }).start();

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
        SecondPageLogin.forEach(i -> ((Component) i).setVisible(false));
        FirstPageLogin.forEach(i -> ((Component) i).setVisible(true));
        _makeclickable();
    }    
    
    /**
     * 
     * 2.2 when the "Login ->" clicked, filled details is checked
     * 
     */
    protected void CheckFirstLogin() {
        _makeUnclickable();
        System.out.println("checking login");

        Arrays.stream(LoginErrorMessage).forEach(i -> ((Component) i).setVisible(false));

        String _username = "";
        String _password = "";

        int index = 0;
        for (Object i : FirstPageLogin) {
            switch (index) {
                case 2:
                    _username = ((JTextField) i).getText();
                    break;
                case 5:
                    _password = String.valueOf(((JPasswordField) i).getPassword());
                    break;
            }
            index ++;
        }

        final String username = _username;
        final String password = _password; 
        
        loadingLabel[1].setVisible(true);

        new Thread(() -> {
            boolean chechLogin = storage.login(username, password);

            SwingUtilities.invokeLater(() -> {
                if (!chechLogin) {
                    LoginErrorMessage[0].setVisible(true);
                    loadingLabel[1].setVisible(false);
                    _makeclickable();
                } else {
                    successLabel[1].setVisible(true);
                    loadingLabel[1].setVisible(false);
                    _makeUnclickable();
                    close.setEnabled(true);
                }
            });
        }).start();

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
        _makeclickable();
    }

    protected void checkSecondLogin() {
        Arrays.stream(LoginErrorMessage).forEach(i -> ((Component) i).setVisible(false)); 
        _makeUnclickable();

        String _phoneNumber = "";
        String _favText = "";
        String _favNum = "";

        int index = 0;
        for (Object i : SecondPageLogin) {
            switch (index) {
                case 1:
                    _phoneNumber = ((JTextField) i).getText();
                    break;
                case 4:
                    _favNum = String.valueOf(((JPasswordField) i).getPassword());
                    break;
                case 7:
                    _favText = String.valueOf(((JPasswordField) i).getPassword());
                    break;
            }
            index ++;
        }

        final int phoneNumber = Integer.parseInt(_phoneNumber);
        final String favText = _favText;
        final String favNum = _favNum;

        loadingLabel[1].setVisible(true);

        new Thread(() -> {
            boolean checkLogin = storage.login(phoneNumber, favText, favNum);

            SwingUtilities.invokeLater(() -> {
                if (!checkLogin) {
                    LoginErrorMessage[1].setVisible(true);
                    loadingLabel[1].setVisible(false);
                    _makeclickable();
                } else {
                    successLabel[1].setVisible(true);
                    loadingLabel[1].setVisible(false);
                    _makeUnclickable();
                    close.setEnabled(true);
                }
            });
        }).start();

    }

    /**
     * 1.2 add all the components to the JPanel
     * 
     */
    protected void _addComp() {
        FirstPageRegister.forEach(i -> add((Component) i));
        RegisterFirstErrorMessage.forEach(i -> add((Component) i));
        SecondPageRegister.forEach(i -> add((Component) i));

        RegisterSecondErrorMessage.forEach(i -> add((Component) i));
        Arrays.stream(successLabel).forEach(i -> add((Component) i));
        Arrays.stream(loadingLabel).forEach(i -> add((Component) i));
        Arrays.stream(LoginErrorMessage).forEach(i -> add((Component) i));

        FirstPageLogin.forEach(i -> add((Component) i));
        SecondPageLogin.forEach(i -> add((Component) i));

        Fill();
    }

    protected void _makeUnclickable() {
        RegisterGender.makeUnclickable();
        RegisterPassword.setUnEditable();
        RegisterFavNum.setUnEditable();
        RegisterFavText.setUnEditable();

        register.setEnabled(false);
        login1.setEnabled(false);
        login2.setEnabled(false);
        alternative1.setEnabled(false);
        alternative2.setEnabled(false);

        LoginUsername.setUnEditable();
        LoginPassword.setUnEditable();

        LRButton.setEnabled(false);
        close.setEnabled(false);
    }    

    protected void _makeclickable() {
        RegisterGender._makeclickable();
        RegisterPassword.setEditable();
        RegisterFavNum.setEditable();
        RegisterFavText.setEditable();

        register.setEnabled(true);
        login1.setEnabled(true);
        login2.setEnabled(true);
        alternative1.setEnabled(true);
        alternative2.setEnabled(true);

        LoginUsername.setEditable();
        LoginPassword.setEditable();        

        LRButton.setEnabled(true);
        close.setEnabled(true);
    }    

}
