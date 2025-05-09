package LoginSystem.LoginPage;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Components.Window;
import Components.initializer;
import Helper.blur;
import Helper.Comp.createComp;
import Helper.RoundedBorder.roundedBorder;
import Helper.fileSystem.imageSystem;

public class Job extends JPanel {

    blur blur;
    initializer i;
    Window window;
    JobReadyComp readyComp = new JobReadyComp();
    
    public Job(initializer i, Window window) {
        this.i = i;
        this.window = window;
        this.blur = new blur(i.frame);

        _background();
        _addComp();
        _addX();

        readyComp.setVisible();
    }

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
        add(readyComp.CVFill);

    }

    void _addX() {
        JButton closeButton = createComp.createJButton(
            "X", 
            50, 20, 
            60, 40, 
            new roundedBorder(10, Color.BLACK, null), Color.BLACK, 
            new Font("Arial", Font.BOLD, 20)
        );
        closeButton.setVisible(true);
        closeButton.addActionListener(
            _ -> {
                blur.removeBlur();
                SwingUtilities.invokeLater(
                    () -> {
                        window._reloadEverything();
                    }
                );
            }
        );
        add(closeButton);
    }

}
