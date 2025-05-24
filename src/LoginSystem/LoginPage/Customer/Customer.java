package LoginSystem.LoginPage.Customer;

import java.awt.Color;
import java.awt.Component;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import Components.Components;
import Components.SwitchThemeComp;
import Components.Window;
import Components.initializer;
import Helper.blur;
import Helper.Animation.componentAnim;
import Helper.Comp.PanelHelper;
import Helper.Config.roundedBorder;
import Helper.fileSystem.imageSystem;
import Helper.login.Profile;
import Helper.login.loginComp;
import LoginSystem.storage;
import LoginSystem.LoginPage.PromptMessage;

public class Customer extends JPanel {
    
    SwitchThemeComp S;
    JScrollPane pane;
    initializer i;
    blur blur;
    Window window;
    Components component;
    storage storage;
    CustReadyComp readyComp = new CustReadyComp(this);
    PromptMessage message;

    Profile.userProfile user = new Profile.userProfile(
        null, false, null, null, 
        null, 0, 0, 
        null, null, 
        null, null, 
        imageSystem._scaleImage(imageSystem.PROFILE, 50, 50)
    );

    /*//////////////////////////////////////////////////////////////
                              constructor
    //////////////////////////////////////////////////////////////*/    

    public Customer(initializer i, Window w) {
        this.blur = new blur(i.frame);
        this.pane = i.scrollPane;
        this.component = i.component;
        this.storage = i.storage;
        this.window = w;
        this.i = i;
        this.S = i.switchThemeComp;

        background();
        S.dummy.add(this);
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
        JPanel half = readyComp.half;

        add(half);
        half.add(readyComp.header);
        half.add(readyComp.subHeader);
        half.add(readyComp.LRButton);
        half.add(readyComp.close);

        // Add action listener to the button
        readyComp.LRButton.addActionListener( 
            _ -> { 
                toggleLoginRegister(
                    readyComp.header, 
                    readyComp.subHeader, 
                    readyComp.LRButton, 
                    half
                ); 
            }
        );
        readyComp.close.addActionListener( 
            _ -> {
                blur.removeBlur();
                PanelHelper.clear(half);
                PanelHelper.clear(this);
                readyComp = null;
                SwingUtilities.invokeLater(() -> { window._loadFrontPage(); });
            }
        );
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
        i.compAnimStorage.addAnim(anim);
        
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
     * 2.1 fill the first page of registration
     * 
     */
    protected void FillFirstRegister() { 
        readyComp._setFirstRegisterVisible(true);
        readyComp._setSecondRegisterVisible(false);
    }    

    /**
     * 
     * 2.1 when the "Register ▶" clicked, filled details is checked. Then show up the second page
     * 
     */
    protected void CheckFirstRegister() {

        String FirstName = new String();
        String lastName = new String();
        String username = new String();
        String phoneNumber = new String(); 
        String age = new String();

        FirstName = readyComp.RegisterFirstName.textField.getText();
        lastName = readyComp.RegisterLastName.textField.getText();
        username = readyComp.RegisterUsername.textField.getText();
        phoneNumber = readyComp.RegisterPhoneNum.textField.getText().trim();
        age = readyComp.RegisterAge.textField.getText().trim();

        System.out.println("checking first register");

        if (!FirstName.matches("[a-zA-Z]+") || !lastName.matches("[a-zA-Z]+")) {
            _promptMessage(readyComp.RegisterFirstErrorMessage[0]);
            return;
        }
        if (username.trim().isEmpty() || !storage.isUsernameUnique(username)) {
            _promptMessage(readyComp.RegisterFirstErrorMessage[1]);
            return;
        }
        if (!phoneNumber.chars().allMatch(Character::isDigit) || !(phoneNumber.matches("^01[0-46-9][0-9]{7,8}$") || phoneNumber.matches("^0[3-9][0-9]{7,8}$"))) {
            _promptMessage(readyComp.RegisterFirstErrorMessage[2]);
            return;
        }
        if (!storage.isPhoneNumberUnique(Integer.parseInt(phoneNumber))) {
            _promptMessage(readyComp.RegisterFirstErrorMessage[3]);
            return;
        }
        if (age.trim().isEmpty() || !age.chars().allMatch(Character::isDigit) || Integer.parseInt(age) < 18 || Integer.parseInt(age) > 60) {
            _promptMessage(readyComp.RegisterFirstErrorMessage[4]);
            return;
        }

        user.department = Profile.Department.CUSTOMER;
        user.firstName = FirstName;
        user.lastName = lastName;
        user.username = username;
        user.phoneNumber = Integer.parseInt(phoneNumber);
        user.age = Integer.parseInt(age);

        readyComp._setFirstRegisterVisible(false);
        readyComp._setSecondRegisterVisible(true);
    }    

    /**
     * 
     * 2.1 when the "Register" clicked, filled details is checked. Then store the details by calling {@link #_register}
     * 
     */
    protected void CheckSecondRegister() {

        String gender = new String();
        String password = new String();
        String favText = new String();
        String favNum = new String();

        gender = readyComp.RegisterGender.getSelectedGender();
        password = String.valueOf(readyComp.RegisterPassword.passwordField.getPassword());
        favText = String.valueOf(readyComp.RegisterFavText.passwordField.getPassword());
        favNum = String.valueOf(readyComp.RegisterFavNum.passwordField.getPassword());

        if (gender == null || gender.trim() == "") {
            _promptMessage(readyComp.RegisterSecondErrorMessage[0]);
            return;
        }
        if (readyComp.RegisterGender.isOthersSelectedButEmpty()) {
            _promptMessage(readyComp.RegisterSecondErrorMessage[1]);
            return;
        }
        if (!storage.isYourPasswordStrong(password)) {
            _promptMessage(readyComp.RegisterSecondErrorMessage[2]);
            return;
        }
        if (favText.trim().isEmpty() || !favText.matches("[a-zA-Z]+")) {
            _promptMessage(readyComp.RegisterSecondErrorMessage[3]);
            return;
        }
        if (favNum.trim().isEmpty() || !favNum.chars().allMatch(Character::isDigit)) {
            _promptMessage(readyComp.RegisterSecondErrorMessage[4]);
            return;
        }

        user.gender = gender;
        user.password = password;
        user.favText = favText;
        user.favNum = favNum;

        _register();

    }

    /**
     * 
     * 2.1 store the details
     * 
     */
    protected void _register() {
        readyComp._setClickable(false);
        readyComp.loadingLabel[0].setVisible(true);

        new Thread(() -> {
            if (user.pfp == null) user.pfp = imageSystem._scaleImage(user.pfp, 50, 50);
            storage.customerRegister(user);

            SwingUtilities.invokeLater(() -> {
                System.out.println("Username: " + user.username);
                System.out.println("Password: " + storage.Users.get(user.username).password);
                System.out.println("Favourite Texts: " + storage.Users.get(user.username).favText);
                System.out.println("Favourite Number: " + storage.Users.get(user.username).favNum);
                _promptMessage(readyComp.successLabel[0]);
                readyComp.loadingLabel[0].setVisible(false);
                readyComp.close.setEnabled(true);
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
        readyComp._setSecondLoginVisible(false);
        readyComp._setFirstLoginVisible(true);
        readyComp._setClickable(true);
    }   
    
    /**
     * 
     * 2.2 when the "Login ▶" clicked, filled details is checked
     * 
     */
    protected void CheckFirstLogin() {
        readyComp._setClickable(false);

        String _username = "";
        String _password = "";

        _username = readyComp.LoginUsername.textField.getText();
        _password = String.valueOf(readyComp.LoginPassword.passwordField.getPassword());

        user.username = _username;
        user.password = _password;

        readyComp.loadingLabel[1].setVisible(true);
        new Thread(() -> {
            boolean checkLogin = storage.login(user.username, user.password);

            SwingUtilities.invokeLater(() -> {
                if (!checkLogin) {
                    _promptMessage(readyComp.LoginErrorMessage[0]);
                    readyComp.loadingLabel[1].setVisible(false);
                    readyComp._setClickable(true);
                } else {
                    _setLogin();
                    _promptMessage(readyComp.successLabel[1]);
                    readyComp.loadingLabel[1].setVisible(false);
                    readyComp.close.setEnabled(true);
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
        readyComp._setFirstLoginVisible(false);
        readyComp._setSecondLoginVisible(true);
        readyComp._setClickable(true);
    }  
    
    protected void checkSecondLogin() {
        readyComp._setClickable(false);

        String _phoneNumber = "";
        String _favText = "";
        String _favNum = "";

        _phoneNumber = readyComp.LoginPhoneNumber.textField.getText();
        _favNum = String.valueOf(readyComp.LoginFavNum.passwordField.getPassword());
        _favText = String.valueOf(readyComp.LoginFavText.passwordField.getPassword());

        final int phoneNumber = Integer.parseInt(_phoneNumber);
        final String favText = _favText;
        final String favNum = _favNum;

        readyComp.loadingLabel[1].setVisible(true);
        new Thread(() -> {
            boolean checkLogin = storage.login(phoneNumber, favText, favNum);

            SwingUtilities.invokeLater(() -> {
                if (!checkLogin) {
                    _promptMessage(readyComp.LoginErrorMessage[1]);
                    readyComp.loadingLabel[1].setVisible(false);
                    readyComp._setClickable(true);
                } else {
                    _setLogin();
                    _promptMessage(readyComp.successLabel[1]);
                    readyComp.loadingLabel[1].setVisible(false);
                    readyComp.close.setEnabled(true);
                }
            });
        }).start();
    }
    
    /*//////////////////////////////////////////////////////////////
                               add JPanel
    //////////////////////////////////////////////////////////////*/
     
    /**
     * 1.2 add all the components to the JPanel
     * 
     */
    protected void _addComp() {
        // first page register
        add(readyComp.FirstPageRegisterLabel);
        add(readyComp.RegisterFirstName.label);
        add(readyComp.RegisterFirstName.textField);
        add(readyComp.RegisterLastName.label);
        add(readyComp.RegisterLastName.textField);
        add(readyComp.RegisterUsername.label);
        add(readyComp.RegisterUsername.textField);
        add(readyComp.RegisterPhoneNum.label);
        add(readyComp.RegisterPhoneNum.textField);
        add(readyComp.RegisterAge.label);
        add(readyComp.RegisterAge.textField);
        add(readyComp.FirstRegisterButton);
 
        // second page register
        add(readyComp.RegisterGender.gender[0]);
        add(readyComp.RegisterGender.gender[1]);
        add(readyComp.RegisterGender.gender[2]);
        add(readyComp.RegisterGender.label[0]);
        add(readyComp.RegisterGender.label[1]);
        add(readyComp.RegisterGender.label[2]);
        add(readyComp.RegisterGender.others);
        add(readyComp.RegisterPasswordInstructor.button);
        add(readyComp.RegisterPasswordInstructor.textArea);
        add(readyComp.RegisterPasswordInstructor.textBackground);
        add(readyComp.RegisterPassword.label);
        add(readyComp.RegisterPassword.button);
        add(readyComp.RegisterPassword.passwordField);
        add(readyComp.RegisterFavText.label);
        add(readyComp.RegisterFavText.button);
        add(readyComp.RegisterFavText.passwordField);
        add(readyComp.RegisterFavNum.label);
        add(readyComp.RegisterFavNum.button);
        add(readyComp.RegisterFavNum.passwordField);
        add(readyComp.passwordInstruct);
        add(readyComp.register);

        // first page login
        add(readyComp.FirstPageLoginLabel);
        add(readyComp.LoginUsername.label);
        add(readyComp.LoginUsername.textField);
        add(readyComp.LoginPassword.label);
        add(readyComp.LoginPassword.button);
        add(readyComp.LoginPassword.passwordField);
        add(readyComp.login1);
        add(readyComp.alternative1);

        // second page login
        add(readyComp.LoginPhoneNumber.label);
        add(readyComp.LoginPhoneNumber.textField);
        add(readyComp.LoginFavNum.label);
        add(readyComp.LoginFavNum.button);
        add(readyComp.LoginFavNum.passwordField);
        add(readyComp.LoginFavText.label);
        add(readyComp.LoginFavText.button);
        add(readyComp.LoginFavText.passwordField);
        add(readyComp.login2);
        add(readyComp.alternative2);

        // error && success message
        Arrays.stream(readyComp.RegisterFirstErrorMessage).forEach(i -> add((Component) i));
        Arrays.stream(readyComp.RegisterSecondErrorMessage).forEach(i -> add((Component) i));
        Arrays.stream(readyComp.loadingLabel).forEach(i -> add((Component) i));

        _addToDummy();

    } 
    
    /**
     * 1.2 add all the components to the JPanel
     * 
     */
    protected void _addToDummy() {
        // first page register
        S.dummy.add(readyComp.FirstPageRegisterLabel);
        S.dummy.add(readyComp.RegisterFirstName.label);
        S.dummy.add(readyComp.RegisterFirstName.textField);
        S.dummy.add(readyComp.RegisterLastName.label);
        S.dummy.add(readyComp.RegisterLastName.textField);
        S.dummy.add(readyComp.RegisterUsername.label);
        S.dummy.add(readyComp.RegisterUsername.textField);
        S.dummy.add(readyComp.RegisterPhoneNum.label);
        S.dummy.add(readyComp.RegisterPhoneNum.textField);
        S.dummy.add(readyComp.RegisterAge.label);
        S.dummy.add(readyComp.RegisterAge.textField);
        S.dummy.add(readyComp.FirstRegisterButton);

        // second page register
        S.dummy.add(readyComp.RegisterGender.gender[0]);
        S.dummy.add(readyComp.RegisterGender.gender[1]);
        S.dummy.add(readyComp.RegisterGender.gender[2]);
        S.dummy.add(readyComp.RegisterGender.label[0]);
        S.dummy.add(readyComp.RegisterGender.label[1]);
        S.dummy.add(readyComp.RegisterGender.label[2]);
        S.dummy.add(readyComp.RegisterGender.others);
        S.dummy.add(readyComp.RegisterPasswordInstructor.button);
        S.dummy.add(readyComp.RegisterPasswordInstructor.textArea);
        S.dummy.add(readyComp.RegisterPasswordInstructor.textBackground);
        S.dummy.add(readyComp.RegisterPassword.label);
        S.dummy.add(readyComp.RegisterPassword.button);
        S.dummy.add(readyComp.RegisterPassword.passwordField);
        S.dummy.add(readyComp.RegisterFavText.label);
        S.dummy.add(readyComp.RegisterFavText.button);
        S.dummy.add(readyComp.RegisterFavText.passwordField);
        S.dummy.add(readyComp.RegisterFavNum.label);
        S.dummy.add(readyComp.RegisterFavNum.button);
        S.dummy.add(readyComp.RegisterFavNum.passwordField);
        S.dummy.add(readyComp.passwordInstruct);
        S.dummy.add(readyComp.register);

        // first page login
        S.dummy.add(readyComp.FirstPageLoginLabel);
        S.dummy.add(readyComp.LoginUsername.label);
        S.dummy.add(readyComp.LoginUsername.textField);
        S.dummy.add(readyComp.LoginPassword.label);
        S.dummy.add(readyComp.LoginPassword.button);
        S.dummy.add(readyComp.LoginPassword.passwordField);
        S.dummy.add(readyComp.login1);
        S.dummy.add(readyComp.alternative1);

        // second page login
        S.dummy.add(readyComp.LoginPhoneNumber.label);
        S.dummy.add(readyComp.LoginPhoneNumber.textField);
        S.dummy.add(readyComp.LoginFavNum.label);
        S.dummy.add(readyComp.LoginFavNum.button);
        S.dummy.add(readyComp.LoginFavNum.passwordField);
        S.dummy.add(readyComp.LoginFavText.label);
        S.dummy.add(readyComp.LoginFavText.button);
        S.dummy.add(readyComp.LoginFavText.passwordField);
        S.dummy.add(readyComp.login2);
        S.dummy.add(readyComp.alternative2);

        // error && success message
        Arrays.stream(readyComp.RegisterFirstErrorMessage).forEach(i -> S.dummy.add((Component) i));
        Arrays.stream(readyComp.RegisterSecondErrorMessage).forEach(i -> S.dummy.add((Component) i));
        Arrays.stream(readyComp.loadingLabel).forEach(i -> S.dummy.add((Component) i));

        Fill();

    }     

    /*//////////////////////////////////////////////////////////////
                             Prompt Message
    //////////////////////////////////////////////////////////////*/    

    public void _removeMessage() { remove(message); message = null; }

    void _promptMessage(JLabel text) {
        message = new PromptMessage(i, text, this);
        message.setBounds(300, 150, 550, 180);
        message.setVisible(true);

        i.frame.getContentPane().add(message);

        i.compAnimStorage.addAnim(
            new componentAnim(
                message, 
                350, 150, 
                350, 250, 
                i.scrollPane
            ).start()
        );
    }

    void _setLogin() {
        i.isLogin.isLogin = true;

        Profile.seeProfile _user = new Profile.seeProfile(
            null, false, 
            null, null, 
            null, 0, 
            0, null, null
        );

        if (user.username != null) _user = storage.searchUser(user.username);
        if (user.phoneNumber != 0) _user = storage.searchUser(user.phoneNumber);

        i.isLogin.currentProfile = _user;
    }

}