package LoginSystem.LoginPage.Customer;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Helper.Comp.createComp;
import Helper.login.loginComp;
import Helper.login.loginFill;

public class CustReadyComp {

    public JPanel half = loginComp.Panel();
    public JLabel header = loginComp.createWelcomeHeader();
    public JTextArea subHeader = loginComp.createWelcomeSubHeader();
    public JButton LRButton = loginComp.createRL();
    public JButton close = loginComp.createClose();

    /*//////////////////////////////////////////////////////////////
                              constructor
    //////////////////////////////////////////////////////////////*/    
     
    CustReadyComp(Customer C) {         
        this.FirstRegisterButton = loginComp.createFillNext("Register ▶", 770, 440, 20, C::CheckFirstRegister);
        this.register = loginComp.createFillNext("Register", 770, 440, 20, C::CheckSecondRegister); 
        this.login1 = loginComp.createFillNext("Login ▶", 270, 440, 20, C::CheckFirstLogin);
        this.login2 = loginComp.createFillNext("Login ▶", 270, 440, 20, C::checkSecondLogin);
        this.alternative1 = loginComp.createFillNext("𝙵̲𝚘̲𝚛̲𝚐̲𝚎̲𝚝̲ ̲𝚄̲𝚜̲𝚎̲𝚛̲𝚗̲𝚊̲𝚖̲𝚎̲/̲𝙿̲𝚊̲𝚜̲𝚜̲𝚠̲𝚘̲𝚛̲𝚍̲?̲", 200, 310, 15, C::fullLogin);    
        this.alternative2 = loginComp.createFillNext("𝙻̲𝚘̲𝚐̲𝚒̲𝚗̲ ̲𝚞̲𝚜̲𝚒̲𝚗̲𝚐̲ ̲𝚞̲𝚜̲𝚎̲𝚛̲𝚗̲𝚊̲𝚖̲𝚎̲ ̲𝚊̲𝚗̲𝚍̲ ̲𝚙̲𝚊̲𝚜̲𝚜̲𝚠̲𝚘̲𝚛̲𝚍̲", 190, 330, 15, C::FillLogin); 
    }
    
    /*//////////////////////////////////////////////////////////////
                        Registration Components
    //////////////////////////////////////////////////////////////*/
    
    /// ================= first page =================
     
    public JLabel FirstPageRegisterLabel = createComp.createJLabel("Register", 700, 30, 110, 40, new Font("Arial", Font.BOLD, 18), Color.BLACK);

    public loginFill.createLabelAndLongJTextField RegisterFirstName = new loginFill.createLabelAndLongJTextField("First Name:", 530, 90);
    public loginFill.createLabelAndLongJTextField RegisterLastName = new loginFill.createLabelAndLongJTextField("Last Name:", 530, 160);
    public loginFill.createLabelAndLongJTextField RegisterUsername = new loginFill.createLabelAndLongJTextField("Username:", 530, 230);
    public loginFill.createLabelAndShortJTextField RegisterPhoneNum = new loginFill.createLabelAndShortJTextField("Phone Number:", 530, 300);
    public loginFill.createLabelAndShortJTextField RegisterAge = new loginFill.createLabelAndShortJTextField("Age:", 530, 370);    

    public JButton FirstRegisterButton;

    /// ================= second page =================
    
    public loginFill.createGender RegisterGender = new loginFill.createGender(530, 30);
    public loginComp.createPasswordInstructor RegisterPasswordInstructor = new loginComp.createPasswordInstructor(910, 200);
    public loginFill.createLabelAndLongJPasswordField RegisterPassword = new loginFill.createLabelAndLongJPasswordField("Password:", 530, 230);
    public loginFill.createLabelAndShortJPasswordField RegisterFavText = new loginFill.createLabelAndShortJPasswordField("Favourite texts:", 530, 300);
    public loginFill.createLabelAndShortJPasswordField RegisterFavNum = new loginFill.createLabelAndShortJPasswordField("Favourite numbers:", 530, 370);
    public JTextArea passwordInstruct = createComp.createJTextArea(
        """
        password is used for authentication    🔐

        favourite texts and numbers are password 
        alternative during the login process   🛡️
        """, 
        530, 110, 
        455, 100, 
        new Font("Segoe UI Emoji", Font.PLAIN, 20), 
        null, Color.BLACK
    );
    public JButton register;    

    /*//////////////////////////////////////////////////////////////
                            login Components
    //////////////////////////////////////////////////////////////*/   
     
    /// ================= first page =================    
    
    public JLabel FirstPageLoginLabel = createComp.createJLabel("Login", 250, 30, 110, 40, new Font("Arial", Font.BOLD, 18), Color.BLACK);

    public loginFill.createLabelAndLongJTextField LoginUsername = new loginFill.createLabelAndLongJTextField("Username:", 30, 150);
    public loginFill.createLabelAndLongJPasswordField LoginPassword = new loginFill.createLabelAndLongJPasswordField("Password:", 30, 250);
    public JButton login1;
    public JButton alternative1;    

    /// ================= second page =================    
    
    public loginFill.createLabelAndShortJTextField LoginPhoneNumber = new loginFill.createLabelAndShortJTextField("Phone Number:", 30, 90);
    public loginFill.createLabelAndShortJPasswordField LoginFavNum = new loginFill.createLabelAndShortJPasswordField("Favourite number:", 30, 180);
    public loginFill.createLabelAndShortJPasswordField LoginFavText = new loginFill.createLabelAndShortJPasswordField("Favourite text:", 30, 270);
    public JButton login2;
    public JButton alternative2; 

    /*//////////////////////////////////////////////////////////////
                       Registration Error Message
    //////////////////////////////////////////////////////////////*/    

    public JLabel[] RegisterFirstErrorMessage = {
        loginComp.createErrorMessage("First name and Last name must only contain characters", 20, 50),
        loginComp.createErrorMessage("Username has used", 20, 50),
        loginComp.createErrorMessage("Phone number is not valid", 20, 50),
        loginComp.createErrorMessage("Phone number is used", 20, 50),
        loginComp.createErrorMessage("only the age between 18 - 60", 20, 50)
    };

    public JLabel[] RegisterSecondErrorMessage = {
        loginComp.createErrorMessage("Gender is not selected", 20, 50),
        loginComp.createErrorMessage("'Others' gender is selected, but did not explicitly named your gender", 20, 50),
        loginComp.createErrorMessage("Password is weak, hower 'i' for more", 20, 50),
        loginComp.createErrorMessage("favourite text must contain only characters", 20, 50),
        loginComp.createErrorMessage("favourite number must contain only numbers", 20, 50)
    };

    /*//////////////////////////////////////////////////////////////
                          Login Error Message
    //////////////////////////////////////////////////////////////*/  

    public JLabel[] LoginErrorMessage = {
        loginComp.createErrorMessage("incorrect username or/and password", 20, 50),
        loginComp.createErrorMessage("incorrect phone number or/and favourite text or/and number", 20, 50)
    };

    /*//////////////////////////////////////////////////////////////
                            Success Message
    //////////////////////////////////////////////////////////////*/
     
    public JLabel[] successLabel = {
        loginComp.createSuccessMessage("Register Success, click 'Close' to reload and login again", 20, 50),
        loginComp.createSuccessMessage("Login Success, click 'Close' to reload and login again", 20, 50)
    }; 
    
    public JLabel[] loadingLabel = {
        loginComp.createLoading(530, 410),
        loginComp.createLoading(30, 380)
    };    

    /*//////////////////////////////////////////////////////////////
                             set visibility
    //////////////////////////////////////////////////////////////*/    

    public void _setFirstRegisterVisible(boolean isVisible) {
        FirstPageRegisterLabel.setVisible(isVisible);
        RegisterFirstName.label.setVisible(isVisible);
        RegisterFirstName.textField.setVisible(isVisible);
        RegisterLastName.label.setVisible(isVisible);
        RegisterLastName.textField.setVisible(isVisible);
        RegisterUsername.label.setVisible(isVisible);
        RegisterUsername.textField.setVisible(isVisible);
        RegisterPhoneNum.label.setVisible(isVisible);
        RegisterPhoneNum.textField.setVisible(isVisible);
        RegisterAge.label.setVisible(isVisible);
        RegisterAge.textField.setVisible(isVisible);
        FirstRegisterButton.setVisible(isVisible);
    }

    public void _setSecondRegisterVisible(boolean isVisible) {
        RegisterGender.gender[0].setVisible(isVisible);
        RegisterGender.gender[1].setVisible(isVisible);
        RegisterGender.gender[2].setVisible(isVisible);
        RegisterGender.label[0].setVisible(isVisible);
        RegisterGender.label[1].setVisible(isVisible);
        RegisterGender.label[2].setVisible(isVisible);
        RegisterGender.others.setVisible(isVisible);
        RegisterPasswordInstructor.button.setVisible(isVisible);
        RegisterPassword.label.setVisible(isVisible);
        RegisterPassword.passwordField.setVisible(isVisible);
        RegisterPassword.button.setVisible(isVisible);
        RegisterFavText.label.setVisible(isVisible);
        RegisterFavText.passwordField.setVisible(isVisible);
        RegisterFavText.button.setVisible(isVisible);
        RegisterFavNum.label.setVisible(isVisible);
        RegisterFavNum.passwordField.setVisible(isVisible);
        RegisterFavNum.button.setVisible(isVisible);
        passwordInstruct.setVisible(isVisible);
        register.setVisible(isVisible);
    }

    public void _setFirstLoginVisible(boolean isVisible) {
        FirstPageLoginLabel.setVisible(isVisible);
        LoginUsername.label.setVisible(isVisible);
        LoginUsername.textField.setVisible(isVisible);
        LoginPassword.label.setVisible(isVisible);
        LoginPassword.passwordField.setVisible(isVisible);
        LoginPassword.button.setVisible(isVisible);
        login1.setVisible(isVisible);
        alternative1.setVisible(isVisible);
    }

    public void _setSecondLoginVisible(boolean isVisible) {
        LoginPhoneNumber.label.setVisible(isVisible);
        LoginPhoneNumber.textField.setVisible(isVisible);
        LoginFavNum.label.setVisible(isVisible);
        LoginFavNum.button.setVisible(isVisible);
        LoginFavNum.passwordField.setVisible(isVisible);
        LoginFavText.label.setVisible(isVisible);
        LoginFavText.passwordField.setVisible(isVisible);
        LoginFavText.button.setVisible(isVisible);
        login2.setVisible(isVisible);
        alternative2.setVisible(isVisible);
    }    

    /*//////////////////////////////////////////////////////////////
                            set clickability
    //////////////////////////////////////////////////////////////*/    
     
    public void _setClickable(boolean bool) {
        RegisterGender._setClickable(bool);
        RegisterPassword._setEditable(bool);
        RegisterFavNum._setEditable(bool);
        RegisterFavText._setEditable(bool);

        register.setEnabled(bool);
        login1.setEnabled(bool);
        login2.setEnabled(bool);
        alternative1.setEnabled(bool);
        alternative2.setEnabled(bool);

        LoginUsername._setEditable(bool);
        LoginPassword._setEditable(bool);        

        LRButton.setEnabled(bool);
        close.setEnabled(bool);
    }    

}