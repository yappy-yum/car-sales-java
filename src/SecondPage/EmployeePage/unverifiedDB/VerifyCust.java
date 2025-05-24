package SecondPage.EmployeePage.unverifiedDB;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import Components.Window;
import Components.initializer;
import Helper.blur;
import Helper.Comp.createComp;
import Helper.Config.roundedBorder;
import Helper.fileSystem.fontSystem;
import Helper.fileSystem.imageSystem;
import Helper.login.Profile;

/**
 * 
 * class that preview the confidential of the customer, including their face and 
 * document verification
 * 
 */
public class VerifyCust extends JPanel {
    
    initializer i;
    String user;
    blur blur;
    Window w;

    public VerifyCust(
        initializer i, 
        Window w,
        String user, 
        ImageIcon Face, 
        ImageIcon Docs,
        Profile.seeProfile profile
    ) {
        this.i = i;
        this.w = w;
        this.user = user;
        this.blur = new blur(i.frame);

        _background();
        _addX();
        _addProfile(Face, Docs, profile);
        i.switchThemeComp.dummy.add(this);

    }

    /*//////////////////////////////////////////////////////////////
                               Background
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
                SwingUtilities.invokeLater( () -> { w._loadSecondPage(); } );
            }
        );
        i.switchThemeComp.dummy.add(closeButton);
        add(closeButton);
    }

    /*//////////////////////////////////////////////////////////////
                              load profile
    //////////////////////////////////////////////////////////////*/    
    
    void _addProfile(ImageIcon Face, ImageIcon Docs, Profile.seeProfile profile) {

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
        JLabel docId = createComp.createJLabel(
            Docs, 
            500, 50, 
            200, 200
        );
        JLabel face = createComp.createJLabel(
            Face, 
            500, 270, 
            200, 200
        );

        info.setVisible(true);
        i.switchThemeComp.dummy.add(info);
        add(info);

        docId.setVisible(true);
        i.switchThemeComp.dummy.add(docId);
        add(docId);

        face.setVisible(true);
        i.switchThemeComp.dummy.add(face);
        add(face);

    }

}
