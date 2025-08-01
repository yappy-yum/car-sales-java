package SecondPage.EmployeePage.unverifiedDB;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import Components.Window;
import Components.initializer;
import Helper.blur;
import Helper.Comp.createComp;
import Helper.Config.roundedBorder;
import Helper.Config.PanelConfig.DropdownPanel;
import Helper.fileSystem.fontSystem;
import Helper.login.Profile;

/**
 * 
 * class that preview the emplyee info, including the 
 * CV they've filled in
 * 
 */
public class VerifyEmployee extends DropdownPanel {

    initializer i;
    Window w;
    blur blur;
    
    public VerifyEmployee(
        initializer i,
        Window w,
        String user
    ) {
        this.i = i;
        this.w = w;
        this.blur = new blur(i.frame);

        _addX();
        _addProfile(user);
    }

    /*//////////////////////////////////////////////////////////////
                              Close Button
    //////////////////////////////////////////////////////////////*/    

    void _addX() {
        JButton closeButton = createComp.createJButton(
            "X", 
            900, 20, 
            60 , 40,
            new roundedBorder(10, Color.BLACK, null),
            Color.BLACK,
            new Font("Arial", Font.BOLD, 20)
        );
        closeButton.setVisible(true);
        closeButton.addActionListener(
            _ -> {
                blur.removeBlur();
                remove(this);
                SwingUtilities.invokeLater( () -> { w._loadDBPage(); } );
            }
        );
        add(closeButton);
    }    

    void _addProfile(String roadSit) {
        Profile.CV profile = i.storage.Job.get(roadSit);

        JTextArea info = createComp.createJTextArea(
            "First Name: " + profile.firstName + "\n\n" + 
            "Last Name: " + profile.lastName + "\n\n" + 
            "Username: " + profile.username + "\n\n" + 
            "Gender: " + profile.gender + "\n\n" + 
            "Age: " + profile.age + "\n\n" + 
            "Phone Number: " + 0 + profile.phoneNumber, 
            20, 50, 
            500, 500, 
            fontSystem.TAGES.deriveFont(25f), 
            null, 
            Color.BLACK
        );

        createComp.CVFill cv = new createComp.CVFill();

        cv.textPane.setDocument(profile.CV);
        cv.textPane.setEditable(false);
        cv.textPane.setPreferredSize(new Dimension(450, 500));

        cv.scrollPane.setBounds(500, 20, 380, 450);

        info.setVisible(true);
        add(info);

        cv.scrollPane.setVisible(true);
        add(cv.scrollPane);

    }

}
