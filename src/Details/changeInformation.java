package Details;

import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Components.SwitchThemeComp;
import Components.Window;
import Components.initializer;
import Helper.blur;
import Helper.Animation.componentAnim;
import Helper.Comp.createComp;
import Helper.RoundedBorder.roundedBorder;
import Helper.fileSystem.imageSystem;
import Helper.login.Profile;
import Helper.login.loginComp;
import Helper.login.loginFill;
import LoginSystem.storage;
import LoginSystem.LoginPage.PromptMessage;
import LoginSystem.LoginPage.Job.JobReadyComp;

public class changeInformation extends JPanel {
    
    blur blur;
    SwitchThemeComp S;
    storage storage;
    Profile.seeProfile profile;
    PromptMessage message;
    Window window;
    initializer i;
    JobReadyComp readyComp;
    Object classToInteractWith;
    JLabel loading = loginComp.createLoading(30, 450);
    JButton submit;
    JButton close;

    // phone number
    loginFill.createLabelAndShortJTextField PhoneNum = new loginFill.createLabelAndShortJTextField("Phone Number:", 30, 80);
    // username
    loginFill.createLabelAndLongJTextField Username = new loginFill.createLabelAndLongJTextField("Username:", 30, 150);
    // password intruction
    loginComp.createPasswordInstructor passInstruct = new loginComp.createPasswordInstructor(410, 190);
    // password
    loginFill.createLabelAndLongJPasswordField password = new loginFill.createLabelAndLongJPasswordField("Password:", 30, 220);
    // Favourite Text
    loginFill.createLabelAndShortJPasswordField favText = new loginFill.createLabelAndShortJPasswordField("Favourite texts:", 30, 290);
    // Favourite Number
    loginFill.createLabelAndShortJPasswordField favNum = new loginFill.createLabelAndShortJPasswordField("Favourite numbers:", 30, 360);

    /*//////////////////////////////////////////////////////////////
                              constructor
    //////////////////////////////////////////////////////////////*/    

    public changeInformation(initializer i, Window window, Object classToInteractWith) {
        System.out.println("clicked - 1");
        this.blur = new blur(i.frame);
        this.storage = i.storage;
        this.S = i.switchThemeComp;
        this.profile = i.isLogin.currentProfile;
        this.i = i;
        this.window = window;
        this.classToInteractWith = classToInteractWith;
        this.readyComp = new JobReadyComp(i);
        this.readyComp.setVisible(true);

        _background();
        _addComp();
        _addCloseAndSubmit();
    }    

    void _background() {
        setOpaque(false);
        setLayout(null);
        setBorder(
            new roundedBorder(
                20, 
                Color.BLACK, 
                imageSystem._reduceColorTransparency(Color.GRAY, 0.7f))
        );
    }   

    /*//////////////////////////////////////////////////////////////
                             add components
    //////////////////////////////////////////////////////////////*/    
    
    void _addComp() {
        add(passInstruct.button);
        add(passInstruct.textArea);
        add(passInstruct.textBackground);
        add(password.label);
        add(password.button);
        add(password.passwordField);
        add(PhoneNum.label);
        add(PhoneNum.textField);
        add(Username.label);
        add(Username.textField);
        add(favText.label);
        add(favText.button);
        add(favText.passwordField);
        add(favNum.label);
        add(favNum.button);
        add(favNum.passwordField);
        add(loading);

        _addToDummy();
    }

    void _addToDummy() {
        S.dummy.add(passInstruct.button);
        S.dummy.add(passInstruct.textArea);
        S.dummy.add(passInstruct.textBackground);
        S.dummy.add(password.label);
        S.dummy.add(password.button);
        S.dummy.add(password.passwordField);
        S.dummy.add(PhoneNum.label);
        S.dummy.add(PhoneNum.textField);
        S.dummy.add(Username.label);
        S.dummy.add(Username.textField);
        S.dummy.add(favText.label);
        S.dummy.add(favText.button);
        S.dummy.add(favText.passwordField);
        S.dummy.add(favNum.label);
        S.dummy.add(favNum.button);
        S.dummy.add(favNum.passwordField);
        S.dummy.add(loading);

        _setVisible();
    }   

    /*//////////////////////////////////////////////////////////////
                              set visible
    //////////////////////////////////////////////////////////////*/    
    
    void _setVisible() {
        passInstruct.button.setVisible(true);
        password.label.setVisible(true);
        password.button.setVisible(true);
        password.passwordField.setVisible(true);
        PhoneNum.label.setVisible(true);
        PhoneNum.textField.setVisible(true);
        Username.label.setVisible(true);
        Username.textField.setVisible(true);
        favText.label.setVisible(true);
        favText.button.setVisible(true);
        favText.passwordField.setVisible(true);
        favNum.label.setVisible(true);
        favNum.button.setVisible(true);
        favNum.passwordField.setVisible(true);
    }

    void _checkDetails() {
        String _phoneNumber = PhoneNum.textField.getText();
        String _username = Username.textField.getText();
        String _password = String.valueOf(password.passwordField.getPassword());
        String _favText = String.valueOf(favText.passwordField.getPassword());
        String _favNum = String.valueOf(favNum.passwordField.getPassword());

        if (!String.valueOf(_phoneNumber).chars().allMatch(Character::isDigit) || !(String.valueOf(_phoneNumber).matches("^01[0-46-9][0-9]{7,8}$") || String.valueOf(_phoneNumber).matches("^0[3-9][0-9]{7,8}$"))) {
            _promptMessage(readyComp.ErrorMessage[2]);
            return;
        }
        if (!i.storage.isPhoneNumberUnique(Integer.parseInt(_phoneNumber))) {
            if (Integer.parseInt(_phoneNumber) != i.isLogin.currentProfile.phoneNumber) {
                _promptMessage(readyComp.ErrorMessage[3]);
                return;
            }
        }
        if (_username.trim().isEmpty() || !i.storage.isUsernameUnique(_username)) {
            if (!_username.equals(i.isLogin.currentProfile.username)) {
                _promptMessage(readyComp.ErrorMessage[1]);
                return;
            }
        }
        if (!i.storage.isYourPasswordStrong(_password)) {
            _promptMessage(readyComp.ErrorMessage[8]);
            return;
        }   
        if (_favText.trim().isEmpty() || !_favText.matches("[a-zA-Z]+")) {
            _promptMessage(readyComp.ErrorMessage[9]);
            return;
        }             
        if (_favNum.trim().isEmpty() || !_favNum.chars().allMatch(Character::isDigit)) {
            _promptMessage(readyComp.ErrorMessage[10]);
            return;
        }       
        
        loading.setVisible(true);
        _makeUnclickable();
        new Thread(() -> {
            i.storage.changeDetails(
                i.isLogin.currentProfile.username, _username, 
                _password, _favText, 
                Integer.parseInt(_favNum), Integer.parseInt(_phoneNumber)
            );
            _setLogin(_username);

            SwingUtilities.invokeLater(() -> {
                loading.setVisible(false);
                close.setEnabled(true);
                _promptMessage(readyComp.SuccessMessage);
            });
        }).start();

    }

    public void _removeMessage() { remove(message); message = null; }

    void _promptMessage(JLabel text) {
        message = new PromptMessage(i, text, this);
        message.setBounds(300, 150, 550, 180);
        message.setVisible(true);

        i.frame.getContentPane().add(message);

        componentAnim anim = new componentAnim(
            message, 
            350, 150, 
            350, 250, 
            i.scrollPane
        );
        anim.start();
        i.compAnimStorage.addAnim(anim);
    }  
    
    void _addCloseAndSubmit() {
        close = createComp.createJButton(
            "Close", 
            380, 430, 
            90, 50, 
            new roundedBorder(20, Color.BLACK, null),
            Color.BLACK,
            new Font("Arial", Font.BOLD, 17)
        );
        close.setVisible(true);
        close.addActionListener(
            _ -> {
                blur.removeBlur();
                remove(this);
                SwingUtilities.invokeLater(() -> { 
                    try {
                        classToInteractWith
                                .getClass()
                                .getMethod("_removeChanges")
                                .invoke(classToInteractWith);
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace(); 
                    }
                });
            }
        );
        S.dummy.add(close);
        add(close);

        submit = createComp.createJButton(
            "Submit", 
            270, 430, 
            110, 50, 
            new roundedBorder(20, Color.BLACK, null),
            Color.BLACK,
            new Font("Arial", Font.BOLD, 17)
        );
        submit.setVisible(true);
        submit.addActionListener( _ -> { _checkDetails(); } );
        S.dummy.add(submit);
        add(submit);
    }

    void _makeUnclickable() {
        PhoneNum.textField.setEditable(false);
        password.passwordField.setEditable(false);
        favText.passwordField.setEditable(false);
        favNum.passwordField.setEditable(false);
        Username.textField.setEditable(false);
        close.setEnabled(false);
        submit.setEnabled(false);
    }

    void _setLogin(String _newUsername) {
        i.isLogin.isLogin = true;
        Profile.seeProfile _user = storage.searchUser(_newUsername);        
        i.isLogin.currentProfile = _user;
    }

}
