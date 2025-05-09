package LoginSystem.LoginPage;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import Helper.Comp.createComp;
import Helper.RoundedBorder.roundedBorder;
import Helper.login.loginComp;
import Helper.login.loginFill;

public class JobReadyComp {
    
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
                               Passwords
    //////////////////////////////////////////////////////////////*/
     
    public loginComp.createPasswordInstructor PasswordInstructor = new loginComp.createPasswordInstructor(910, 140);
    public loginFill.createLabelAndLongJPasswordField Password = new loginFill.createLabelAndLongJPasswordField("Password:", 530, 170);
    public loginFill.createLabelAndShortJPasswordField FavText = new loginFill.createLabelAndShortJPasswordField("Favourite texts:", 530, 100);
    public loginFill.createLabelAndShortJPasswordField FavNum = new loginFill.createLabelAndShortJPasswordField("Favourite numbers:", 530, 30); 

    /*//////////////////////////////////////////////////////////////
                                   CV
    //////////////////////////////////////////////////////////////*/    
    
    public JLabel CVLabel = createComp.createJLabel("CV:", 530, 240, 80, 20, new Font("Arial", Font.BOLD, 20), Color.BLACK);    
    public JTextArea CVFill = CVFill();

    public JTextArea CVFill() {
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setBorder(new roundedBorder(10, Color.BLACK, null));
        textArea.setEditable(true);
        textArea.setBounds(580, 240, 370, 170);
        return textArea;
    }

    /*//////////////////////////////////////////////////////////////
                            setVisible(true)
    //////////////////////////////////////////////////////////////*/
     
    public void setVisible() {
        // header
        header.setVisible(true);

        // Name
        FirstName.label.setVisible(true);
        FirstName.textField.setVisible(true);
        LastName.label.setVisible(true);
        LastName.textField.setVisible(true);
        Username.label.setVisible(true);
        Username.textField.setVisible(true);
        
        // Personal Data
        PhoneNum.label.setVisible(true);
        PhoneNum.textField.setVisible(true);
        Age.label.setVisible(true);
        Age.textField.setVisible(true);
        Gender.gender[0].setVisible(true);
        Gender.gender[1].setVisible(true);
        Gender.gender[2].setVisible(true);
        Gender.label[0].setVisible(true);
        Gender.label[1].setVisible(true);
        Gender.label[2].setVisible(true);
        Gender.others.setVisible(true);

        // Passwords
        PasswordInstructor.button.setVisible(true);
        Password.label.setVisible(true);
        Password.passwordField.setVisible(true);
        Password.button.setVisible(true);
        FavText.label.setVisible(true);
        FavText.passwordField.setVisible(true);
        FavText.button.setVisible(true);
        FavNum.label.setVisible(true);
        FavNum.passwordField.setVisible(true);
        FavNum.button.setVisible(true);

        // CV
        CVLabel.setVisible(true);
        CVFill.setVisible(true);
    }    

}
