package LoginSystem.LoginPage.Job;

import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledDocument;

import Components.SwitchThemeComp;
import Components.Window;
import Components.initializer;
import Helper.blur;
import Helper.Animation.componentAnim;
import Helper.Comp.PanelHelper;
import Helper.Comp.createComp;
import Helper.Config.roundedBorder;
import Helper.fileSystem.imageSystem;
import Helper.login.Profile;
import LoginSystem.Argon2.Argon;
import LoginSystem.LoginPage.PromptMessage;

public class Job extends JPanel {

    SwitchThemeComp S;
    JButton submitButton;
    JButton closeButton;
    blur blur;
    Window window;
    JobReadyComp readyComp;
    PromptMessage message;
    initializer i;
    Argon.Hash argon = new Argon().new Hash();

    Profile.CV CV = new Profile.CV(
        null, null, null, 
        null, null, 
        null, null, 
        null, null, 
        null, 0, 0
    );

    /*//////////////////////////////////////////////////////////////
                              constructor
    //////////////////////////////////////////////////////////////*/    
    
    public Job(initializer i, Window window) {
        this.window = window;
        this.blur = new blur(i.frame);
        this.i = i;
        this.readyComp = new JobReadyComp(i);
        this.S = i.switchThemeComp;

        _background();
        _addComp();
        _addX();
        _addSubmit();
        _addTextAnnotate();

        readyComp.setVisible(true);
        S.dummy.add(this);
    }

    /*//////////////////////////////////////////////////////////////
                           Background JPanel
    //////////////////////////////////////////////////////////////*/    

    void _background() {
        setOpaque(false);
        setLayout(null);
        setBorder(
            new roundedBorder(
                20, 
                Color.BLACK, 
                imageSystem._reduceColorTransparency(Color.GRAY, 0.7f)
            )
        );
    }    

    /*//////////////////////////////////////////////////////////////
                             add to JPanel
    //////////////////////////////////////////////////////////////*/    

    void _addComp() {
        // header
        add(readyComp.header);

        // Name
        add(readyComp.FirstName.label);
        add(readyComp.FirstName.textField);
        add(readyComp.LastName.label);
        add(readyComp.LastName.textField);
        add(readyComp.Username.label);
        add(readyComp.Username.textField);
        
        // Personal Data
        add(readyComp.PhoneNum.label);
        add(readyComp.PhoneNum.textField);
        add(readyComp.Age.label);
        add(readyComp.Age.textField);
        add(readyComp.Gender.gender[0]);
        add(readyComp.Gender.gender[1]);
        add(readyComp.Gender.gender[2]);
        add(readyComp.Gender.label[0]);
        add(readyComp.Gender.label[1]);
        add(readyComp.Gender.label[2]);
        add(readyComp.Gender.others);

        // Roles
        add(readyComp.RolesLabel);
        add(readyComp.Roles.role[0]);
        add(readyComp.Roles.role[1]);
        add(readyComp.Roles.label[0]);
        add(readyComp.Roles.label[1]);

        // Passwords
        add(readyComp.PasswordInstructor.button);
        add(readyComp.PasswordInstructor.textArea);
        add(readyComp.PasswordInstructor.textBackground);
        add(readyComp.Password.label);
        add(readyComp.Password.button);
        add(readyComp.Password.passwordField);
        add(readyComp.FavNum.label);
        add(readyComp.FavNum.button);
        add(readyComp.FavNum.passwordField);
        add(readyComp.FavText.label);
        add(readyComp.FavText.button);
        add(readyComp.FavText.passwordField);

        // CV
        add(readyComp.CVLabel);
        add(readyComp.CVScroll);
        add(readyComp.loadingLabel);

        // text annotation
        Arrays.stream(readyComp.annotateIcon).forEach(i -> add(i));
        _addToDummy();
    }

    void _addToDummy() {
        // header
        S.dummy.add(readyComp.header);

        // Name
        S.dummy.add(readyComp.FirstName.label);
        S.dummy.add(readyComp.FirstName.textField);
        S.dummy.add(readyComp.LastName.label);
        S.dummy.add(readyComp.LastName.textField);
        S.dummy.add(readyComp.Username.label);
        S.dummy.add(readyComp.Username.textField);
        
        // Personal Data
        S.dummy.add(readyComp.PhoneNum.label);
        S.dummy.add(readyComp.PhoneNum.textField);
        S.dummy.add(readyComp.Age.label);
        S.dummy.add(readyComp.Age.textField);
        S.dummy.add(readyComp.Gender.gender[0]);
        S.dummy.add(readyComp.Gender.gender[1]);
        S.dummy.add(readyComp.Gender.gender[2]);
        S.dummy.add(readyComp.Gender.label[0]);
        S.dummy.add(readyComp.Gender.label[1]);
        S.dummy.add(readyComp.Gender.label[2]);
        S.dummy.add(readyComp.Gender.others);

        // Roles
        S.dummy.add(readyComp.RolesLabel);
        S.dummy.add(readyComp.Roles.role[0]);
        S.dummy.add(readyComp.Roles.role[1]);
        S.dummy.add(readyComp.Roles.label[0]);
        S.dummy.add(readyComp.Roles.label[1]);

        // Passwords
        S.dummy.add(readyComp.PasswordInstructor.button);
        S.dummy.add(readyComp.PasswordInstructor.textArea);
        S.dummy.add(readyComp.PasswordInstructor.textBackground);
        S.dummy.add(readyComp.Password.label);
        S.dummy.add(readyComp.Password.button);
        S.dummy.add(readyComp.Password.passwordField);
        S.dummy.add(readyComp.FavNum.label);
        S.dummy.add(readyComp.FavNum.button);
        S.dummy.add(readyComp.FavNum.passwordField);
        S.dummy.add(readyComp.FavText.label);
        S.dummy.add(readyComp.FavText.button);
        S.dummy.add(readyComp.FavText.passwordField);

        // CV
        S.dummy.add(readyComp.CVLabel);
        S.dummy.add(readyComp.CVScroll);
        S.dummy.add(readyComp.loadingLabel);

        // text annotation
        Arrays.stream(readyComp.annotateIcon).forEach(i -> S.dummy.add(i));
    }    

    void _addX() {
        closeButton = createComp.createJButton(
            "Close", 
            850, 410, 
            100, 80, 
            null, Color.BLACK, 
            new Font("Arial", Font.BOLD, 20)
        );
        closeButton.setVisible(true);
        closeButton.addActionListener(
            _ -> {
                blur.removeBlur();
                blur = null;
                SwingUtilities.invokeLater(
                    () -> {
                        PanelHelper.clear(this);
                        // window._loadFrontPage();
                        SwingUtilities.invokeLater(() -> { window._loadFrontPage(); });
                    }
                );
            }
        );
        S.dummy.add(closeButton);
        add(closeButton);
    }

   /*//////////////////////////////////////////////////////////////
                           add Submit & Close
    //////////////////////////////////////////////////////////////*/    

    void _addSubmit() {
        submitButton = createComp.createJButton(
            "Submit", 
            750, 410, 
            100, 80, 
            null, Color.BLACK, 
            new Font("Arial", Font.BOLD, 20)
        );
        submitButton.setVisible(true);
        submitButton.addActionListener( 
            _ -> { 
                _checkDetails();
            } 
        );
        S.dummy.add(submitButton);
        add(submitButton);
    }

    void _checkDetails() {
        String FirstName = new String();
        String lastName = new String();
        String username = new String();
        String phoneNumber = new String(); 
        String age = new String();
        String gender = new String();
        String role = new String();

        String password = new String();
        String favText = new String();
        String favNum = new String();
        StyledDocument cv = new DefaultStyledDocument();

        FirstName = readyComp.FirstName.textField.getText();
        lastName = readyComp.LastName.textField.getText();
        username = readyComp.Username.textField.getText();
        phoneNumber = readyComp.PhoneNum.textField.getText();
        age = String.valueOf(readyComp.Age.textField.getText());
        gender = readyComp.Gender.getSelectedGender();
        role = readyComp.Roles.getSelectedRole();
        password = String.valueOf(readyComp.Password.passwordField.getPassword());
        favText = String.valueOf(readyComp.FavText.passwordField.getPassword());
        favNum = String.valueOf(readyComp.FavNum.passwordField.getPassword());
        cv = readyComp.CVFillPane.getStyledDocument();

        if (!FirstName.matches("[a-zA-Z]+") || !lastName.matches("[a-zA-Z]+")) {
            _promptMessage(readyComp.ErrorMessage[0]);
            return;
        }
        if (username.trim().isEmpty() || !i.storage.isUsernameUnique(username)) {
            _promptMessage(readyComp.ErrorMessage[1]);
            return;
        }
        if (!String.valueOf(phoneNumber).chars().allMatch(Character::isDigit) || !(String.valueOf(phoneNumber).matches("^01[0-46-9][0-9]{7,8}$") || String.valueOf(phoneNumber).matches("^0[3-9][0-9]{7,8}$"))) {
            _promptMessage(readyComp.ErrorMessage[2]);
            return;
        }
        if (!i.storage.isPhoneNumberUnique(Integer.parseInt(phoneNumber))) {
            _promptMessage(readyComp.ErrorMessage[3]);
            return;
        }
        if (String.valueOf(age).trim().isEmpty() || !String.valueOf(age).chars().allMatch(Character::isDigit) || Integer.parseInt(age) < 18 || Integer.parseInt(age) > 60) {
            _promptMessage(readyComp.ErrorMessage[4]);
            return;
        }
        if (gender == null || gender.trim() == "") {
            _promptMessage(readyComp.ErrorMessage[5]);
            return;
        }
        if (readyComp.Gender.isOthersSelectedButEmpty()) {
            _promptMessage(readyComp.ErrorMessage[6]);
            return;
        }
        if (role == null || role.trim() == "") {
            _promptMessage(readyComp.ErrorMessage[7]);
            return;
        }
        if (!i.storage.isYourPasswordStrong(password)) {
            _promptMessage(readyComp.ErrorMessage[8]);
            return;
        }
        if (favText.trim().isEmpty() || !favText.matches("[a-zA-Z]+")) {
            _promptMessage(readyComp.ErrorMessage[9]);
            return;
        }
        if (favNum.trim().isEmpty() || !favNum.chars().allMatch(Character::isDigit)) {
            _promptMessage(readyComp.ErrorMessage[10]);
            return;
        }
        if (createComp.isStyledDocumentEmpty(cv)) {
            _promptMessage(readyComp.ErrorMessage[11]);
            return;
        }

        final String _FirstName = FirstName;
        final String _lastName = lastName;
        final String _username = username;
        final int _phoneNumber = Integer.parseInt(phoneNumber);
        final int _age = Integer.parseInt(age);
        final String _gender = gender;
        final String _role = role;
        final String _password = password;
        final String _favText = favText;
        final String _favNum = favNum;
        final StyledDocument _cv = cv;

        readyComp._setEditable(false); 
        submitButton.setEnabled(false);
        closeButton.setEnabled(false);
        readyComp.loadingLabel.setVisible(true);
        new Thread(() -> {
            CV.department = _role.equals("Manager") ? Profile.Department.MANAGER : Profile.Department.SALESMAN;
            CV.approval = Profile.Approval.PENDING;
            CV.CV = _cv;
            CV.firstName = _FirstName;
            CV.lastName = _lastName;
            CV.username = _username;
            CV.password = _password;
            CV.favText = _favText;
            CV.favNum = _favNum;
            CV.gender = _gender;
            CV.age = _age;
            CV.phoneNumber = _phoneNumber;
            i.storage.employeeRegister(CV);

            SwingUtilities.invokeLater(() -> {
                closeButton.setEnabled(true);
                readyComp.loadingLabel.setVisible(false);
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

        i.compAnimStorage.addAnim(
            new componentAnim(
                message, 
                350, 150, 
                350, 250, 
                i.scrollPane
            ).start()
        );
    }

    void _addTextAnnotate() {
        Arrays.stream(readyComp.Roles.role).forEach(i -> i.setVisible(true));
    }

}
