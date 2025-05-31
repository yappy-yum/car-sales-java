package LoginSystem.LoginPage.Job;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import Components.initializer;
import Helper.Comp.createComp;
import Helper.ErrorMessages;
import Helper.login.annotateButton;
import Helper.login.loginComp;
import Helper.login.loginFill;

public class JobReadyComp {

    initializer i;
    public JobReadyComp(initializer i) { this.i = i; }
    
    /*//////////////////////////////////////////////////////////////
                                 header
    //////////////////////////////////////////////////////////////*/
     
    public JLabel header = createComp.createJLabel(
        "Job Street", 
        200, 35, 
        180, 20, 
        new Font("Arial", Font.BOLD, 25), 
        Color.BLACK
    );    

    /*//////////////////////////////////////////////////////////////
                                  Name
    //////////////////////////////////////////////////////////////*/
     
    public loginFill.createLabelAndLongJTextField FirstName = new loginFill.createLabelAndLongJTextField("First Name:", 20, 90); 
    public loginFill.createLabelAndLongJTextField LastName = new loginFill.createLabelAndLongJTextField("Last Name:", 20, 160);  
    public loginFill.createLabelAndLongJTextField Username = new loginFill.createLabelAndLongJTextField("Username:", 20, 230);
    
    /*//////////////////////////////////////////////////////////////
                             Personal Data
    //////////////////////////////////////////////////////////////*/
     
    public loginFill.createLabelAndShortJTextField PhoneNum = new loginFill.createLabelAndShortJTextField("Phone Number:", 20, 300);
    public loginFill.createLabelAndShortJTextField Age = new loginFill.createLabelAndShortJTextField("Age:", 20, 370); 
    public loginFill.createGender Gender = new loginFill.createGender(20, 440);

    /*//////////////////////////////////////////////////////////////
                                 Roles
    //////////////////////////////////////////////////////////////*/
    
    public JLabel RolesLabel = createComp.createJLabel("Roles:", 530, 40, 80, 20, new Font("Arial", Font.BOLD, 20), Color.BLACK);
    public loginFill.createRole Roles = new loginFill.createRole(630, 30);

    /*//////////////////////////////////////////////////////////////
                               Passwords
    //////////////////////////////////////////////////////////////*/
     
    public loginComp.createPasswordInstructor PasswordInstructor = new loginComp.createPasswordInstructor(910, 200);
    public loginFill.createLabelAndLongJPasswordField Password = new loginFill.createLabelAndLongJPasswordField("Password:", 530, 230);
    public loginFill.createLabelAndShortJPasswordField FavText = new loginFill.createLabelAndShortJPasswordField("Favourite texts:", 530, 160);
    public loginFill.createLabelAndShortJPasswordField FavNum = new loginFill.createLabelAndShortJPasswordField("Favourite numbers:", 530, 90); 

    /*//////////////////////////////////////////////////////////////
                                   CV
    //////////////////////////////////////////////////////////////*/    
    
    public JLabel CVLabel = createComp.createJLabel("CV:", 530, 300, 80, 20, new Font("Arial", Font.BOLD, 20), Color.BLACK); 
    
    public createComp.CVFill CVFill = new createComp.CVFill();
    public JTextPane CVFillPane = CVFill.textPane;
    public JScrollPane CVScroll = CVFill.scrollPane;

    /*//////////////////////////////////////////////////////////////
                          text annotate button
    //////////////////////////////////////////////////////////////*/
    
    public JButton[] annotateIcon = {
        annotateButton.boldButton(CVFillPane, 480, 330),
        annotateButton.italicButton(CVFillPane, 480, 370),
        annotateButton.underlineButton(CVFillPane, 480, 410),

        annotateButton.LeftButton(CVFillPane, 530, 330),
        annotateButton.CenterButton(CVFillPane, 530, 370),
        annotateButton.RightButton(CVFillPane, 530, 410),
        annotateButton.JustifyButton(CVFillPane, 530, 450)
    };

    /*//////////////////////////////////////////////////////////////
                             Error Message
    //////////////////////////////////////////////////////////////*/
     
    public JLabel[] ErrorMessage = ErrorMessages.ERROR_MESSAGE_FOR_JOB_CHECKS;

    public JLabel SuccessMessage = loginComp.createSuccessMessage("Submitted Successfully", 20, 50);
    public JLabel loadingLabel = loginComp.createLoading(580, 420);

    /*//////////////////////////////////////////////////////////////
                            setVisible(true)
    //////////////////////////////////////////////////////////////*/
     
    public void setVisible(boolean bool) {
        // header
        header.setVisible(bool);

        // Name
        FirstName.label.setVisible(bool);
        FirstName.textField.setVisible(bool);
        LastName.label.setVisible(bool);
        LastName.textField.setVisible(bool);
        Username.label.setVisible(bool);
        Username.textField.setVisible(bool);
        
        // Personal Data
        PhoneNum.label.setVisible(bool);
        PhoneNum.textField.setVisible(bool);
        Age.label.setVisible(bool);
        Age.textField.setVisible(bool);
        Gender.gender[0].setVisible(bool);
        Gender.gender[1].setVisible(bool);
        Gender.gender[2].setVisible(bool);
        Gender.label[0].setVisible(bool);
        Gender.label[1].setVisible(bool);
        Gender.label[2].setVisible(bool);
        Gender.others.setVisible(bool);

        // Roles
        RolesLabel.setVisible(bool);
        Roles.role[0].setVisible(bool);
        Roles.role[1].setVisible(bool);
        Roles.label[0].setVisible(bool);
        Roles.label[1].setVisible(bool);

        // Passwords
        PasswordInstructor.button.setVisible(bool);
        Password.label.setVisible(bool);
        Password.passwordField.setVisible(bool);
        Password.button.setVisible(bool);
        FavText.label.setVisible(bool);
        FavText.passwordField.setVisible(bool);
        FavText.button.setVisible(bool);
        FavNum.label.setVisible(bool);
        FavNum.passwordField.setVisible(bool);
        FavNum.button.setVisible(bool);

        // CV
        CVLabel.setVisible(bool);
        CVScroll.setVisible(bool);
    }    

    public void _setEditable(boolean bool) {
        FirstName.textField.setEditable(bool);
        LastName.textField.setEditable(bool);
        Username.textField.setEditable(bool);
        PhoneNum.textField.setEditable(bool);
        Age.textField.setEditable(bool);
        Gender._setClickable(bool);
        Roles._setClickable(bool);
        Password.passwordField.setEditable(bool);
        FavText.passwordField.setEditable(bool);
        FavNum.passwordField.setEditable(bool);
        CVFillPane.setEditable(bool);
    }

}
