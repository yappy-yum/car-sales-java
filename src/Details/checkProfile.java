package Details;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import Components.SwitchThemeComp;
import Components.Window;
import Components.initializer;
import Helper.blur;
import Helper.Animation.componentAnim;
import Helper.Comp.createComp;
import Helper.RoundedBorder.roundedBorder;
import Helper.fileSystem.ImageUploader;
import Helper.fileSystem.fontSystem;
import Helper.fileSystem.imageSystem;
import Helper.login.Profile;
import LoginSystem.storage;

public class checkProfile extends JPanel {
    
    blur blur;
    Window window;
    SwitchThemeComp S;
    storage storage;
    Profile.seeProfile profile;
    changeInformation change;
    initializer i;

    /*//////////////////////////////////////////////////////////////
                              constructor
    //////////////////////////////////////////////////////////////*/    

    public checkProfile(initializer i, Window window) {
        this.blur = new blur(i.frame);
        this.storage = i.storage;
        this.window = window;
        this.S = i.switchThemeComp;
        this.profile = i.isLogin.currentProfile;
        this.i = i;

        _background();
        _changeInformation();
        _addX();
        _logout();
        _addHeader();
        _addInformation();
    }

    /*//////////////////////////////////////////////////////////////
                           JPanel Background
    //////////////////////////////////////////////////////////////*/
    
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

    void _addX() {
        JButton closeButton = createComp.createJButton(
            "X", 
            900, 20, 
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
        S.dummy.add(closeButton);
        add(closeButton);
    }  

    /*//////////////////////////////////////////////////////////////
                                 Header
    //////////////////////////////////////////////////////////////*/
    
    void _addHeader() {
        JLabel header = createComp.createJLabel(
            "Your Profile", 
            450, 20, 
            200, 50, 
            new Font("Arial", Font.BOLD, 25), 
            Color.BLACK
        );
        header.setVisible(true);
        S.dummy.add(header);
        add(header);
    }

    /*//////////////////////////////////////////////////////////////
                            add informations
    //////////////////////////////////////////////////////////////*/   
    
    void _addInformation() {
        _addLeftInfo();
        _RightPFP();
        _addRight();
    }

    void _addLeftInfo() {
        JTextArea LeftInfo = createComp.createJTextArea(
            "First Name: " + profile.firstName + "\n\n" +
            "Last Name: " + profile.lastName + "\n\n" +
            "Username: " + profile.username + "\n\n" +
            "Gender: " + profile.gender + "\n\n" +
            "Age: " + profile.age + "\n\n" +
            "Phone Number: " + 0 + profile.phoneNumber + "\n\n" +
            "Gender: " + profile.gender, 
            20, 100, 
            500, 500, 
            fontSystem.TAGES.deriveFont(25f), 
            null, Color.BLACK
        );
        LeftInfo.setVisible(true);
        S.dummy.add(LeftInfo);
        add(LeftInfo);
    }

    void _RightPFP() {
        JButton RightPFP = createComp.createJButton(
            imageSystem._scaleImage(profile.pfp, 100, 100), 
            600, 100, 
            100, 100
        );
        RightPFP.setVisible(true);
        S.dummy.add(RightPFP);
        add(RightPFP);

        RightPFP.addActionListener( _ -> {
            ImageIcon pfpSelected = ImageUploader.uploadImageIcon();
            if (pfpSelected != null) i.storage.setPFP(profile.username, pfpSelected);
            if (pfpSelected != null) i.isLogin.currentProfile.pfp = pfpSelected;
            RightPFP.setIcon(imageSystem._scaleImage(pfpSelected, 100, 100));
        });
    }

    void _addRight() {
        JTextArea RightInfo = createComp.createJTextArea(
            "Role: " + profile.department + "\n\n" +
            "Status: " + (profile.isVerified ? "Verified" : "Not Verified"),
            520, 280, 
            450, 400, 
            fontSystem.TAGES.deriveFont(25f), 
            null, Color.BLACK
        );
        RightInfo.setVisible(true);
        S.dummy.add(RightInfo);
        add(RightInfo);
    }

    void _changeInformation() {
        JButton button = createComp.createJButton(
            "Edit Details", 
            750, 430, 
            170, 50, 
            new roundedBorder(15, Color.BLACK, null), Color.BLACK,
            new Font("Arial", Font.BOLD, 20)
        );
        button.setVisible(true);
        button.addActionListener( _ -> { _promptChanges(); });

        S.dummy.add(button);
        add(button);
    }

    void _logout() {
        JButton button = createComp.createJButton(
            "Logout", 
            550, 430, 
            120, 50, 
            new roundedBorder(15, Color.BLACK, null), Color.BLACK,
            new Font("Arial", Font.BOLD, 20)
        );
        button.setVisible(true);
        button.addActionListener(
            _ -> {
                i.isLogin.currentProfile = null;
                i.isLogin.isLogin = false;

                blur.removeBlur();
                SwingUtilities.invokeLater(
                    () -> {
                        window._reloadEverything();
                    }
                );
            }
        );

        add(button);
        S.dummy.add(button);
    }

    public void _removeChanges() { remove(change); change = null; }

    void _promptChanges() {
        System.out.println("clicked");
        change = new changeInformation(i, window, this);
        change.setBounds(400, 200, 500, 500);
        change.setVisible(true);

        i.frame.getContentPane().add(change);
        
        componentAnim anim = new componentAnim(
            change, 
            400, 200, 
            400, 100, 
            i.scrollPane
        );
        anim.start();
        i.compAnimStorage.addAnim(anim);
    }

}
