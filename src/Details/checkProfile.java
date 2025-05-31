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
import Helper.Comp.PanelHelper;
import Helper.Comp.createComp;
import Helper.Comp.helpStoreComp;
import Helper.Config.roundedBorder;
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
    String user;

    /*//////////////////////////////////////////////////////////////
                              constructor
    //////////////////////////////////////////////////////////////*/    

    public checkProfile(initializer i, Window window, String user) {
        this.blur = new blur(i.frame);
        this.storage = i.storage;
        this.window = window;
        this.S = i.switchThemeComp;
        this.profile = i.isLogin.currentProfile;
        this.i = i;
        this.user = user;

        _background();
        if (user == null) _changeInformation();
        _addX();
        _verify();
        if (i.isLogin.currentProfile.department != Profile.Department.OWNER) _deleteAccount();
        if (user == null) _logout();
        _addHeader();
        _addInformation();

        S.dummy.add(this);
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
                imageSystem._reduceColorTransparency(Color.GRAY, 0.7f)
            )
        );
    }

    /*//////////////////////////////////////////////////////////////
                             X Close Button
    //////////////////////////////////////////////////////////////*/    

    void _addX() {
        JButton closeButton = createComp.createJButton(
            "X", 
            900, 20, 
            60, 40, 
            new roundedBorder(10, Color.BLACK, null), Color.BLACK, 
            new Font("Arial", Font.BOLD, 20)
        );
        closeButton.setVisible(true);
        closeButton.addActionListener( _ -> { _close(); } );
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

    /*//////////////////////////////////////////////////////////////
                               Left Info
    //////////////////////////////////////////////////////////////*/    

    void _addLeftInfo() {
        JTextArea LeftInfo;

        if (user == null) {
            LeftInfo = createComp.createJTextArea(
                "First Name: " + profile.firstName + "\n\n" +
                "Last Name: " + profile.lastName + "\n\n" +
                "Username: " + profile.username + "\n\n" +
                "Gender: " + profile.gender + "\n\n" +
                "Age: " + profile.age + "\n\n" +
                "Phone Number: " + 0 + profile.phoneNumber,
                20, 100, 
                500, 500, 
                fontSystem.TAGES.deriveFont(25f), 
                null, Color.BLACK
            );
        } else {
            Profile.seeProfile userProf = storage.searchUser(user);

            LeftInfo = createComp.createJTextArea(
                "First Name: " + userProf.firstName + "\n\n" +
                "Last Name: " + userProf.lastName + "\n\n" +
                "Username: " + userProf.username + "\n\n" +
                "Gender: " + userProf.gender + "\n\n" +
                "Age: " + userProf.age + "\n\n" +
                "Phone Number: " + 0 + userProf.phoneNumber,
                20, 100, 
                500, 500, 
                fontSystem.TAGES.deriveFont(25f), 
                null, Color.BLACK
            );
        }

        LeftInfo.setVisible(true);
        S.dummy.add(LeftInfo);
        add(LeftInfo);
    }

    /*//////////////////////////////////////////////////////////////
                               Right PFP
    //////////////////////////////////////////////////////////////*/  

    void _RightPFP() {
        ImageIcon pfp = user == null ?
                        imageSystem._scaleImage(profile.pfp, 100, 100) :
                        storage.searchUser(user).pfp;

        JButton RightPFP = createComp.createJButton(
            pfp, 
            600, 100, 
            100, 100
        );
        RightPFP.setVisible(true);
        S.dummy.add(RightPFP);
        add(RightPFP);

        if (user == null)    
            RightPFP.addActionListener( _ -> {
                ImageIcon pfpSelected = ImageUploader.uploadImageIcon();
                if (pfpSelected != null) i.storage.setPFP(profile.username, pfpSelected);
                if (pfpSelected != null) i.isLogin.currentProfile.pfp = pfpSelected;
                RightPFP.setIcon(imageSystem._scaleImage(pfpSelected, 100, 100));
            });
    }

    /*//////////////////////////////////////////////////////////////
                               Right Info
    //////////////////////////////////////////////////////////////*/    

    void _addRight() {
        JTextArea RightInfo;

        if (user == null) {
            RightInfo = createComp.createJTextArea(
                "Role: " + profile.department + "\n\n" +
                "Status: " + (profile.isVerified ? "Verified" : "Not Verified"),
                520, 280, 
                450, 400, 
                fontSystem.TAGES.deriveFont(25f), 
                null, Color.BLACK
            );
        } else {
            Profile.seeProfile userProf = storage.searchUser(user);

            RightInfo = createComp.createJTextArea(
                "Role: " + userProf.department + "\n\n" +
                "Status: " + (userProf.isVerified ? "Verified" : "Not Verified"),
                520, 280, 
                450, 400, 
                fontSystem.TAGES.deriveFont(25f), 
                null, Color.BLACK
            );
        }

        RightInfo.setVisible(true);
        S.dummy.add(RightInfo);
        add(RightInfo);
    }

    /*//////////////////////////////////////////////////////////////
                               Edit Info
    //////////////////////////////////////////////////////////////*/

    void _changeInformation() {
        JButton button = createComp.createJButton(
            "Edit Details", 
            800, 430, 
            170, 50, 
            new roundedBorder(15, Color.BLACK, null), Color.BLACK,
            new Font("Arial", Font.BOLD, 20)
        );
        button.setVisible(true);
        button.addActionListener( _ -> { _promptChanges(); });

        S.dummy.add(button);
        add(button);
    }

    /*//////////////////////////////////////////////////////////////
                                 Logout
    //////////////////////////////////////////////////////////////*/    

    void _logout() {
        JButton button = createComp.createJButton(
            "Logout", 
            670, 430, 
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
                PanelHelper.clear(this);
                SwingUtilities.invokeLater(() -> { window._loadFrontPage(); } );
            }
        );

        add(button);
        S.dummy.add(button);
    }

    void _verify() {
        if (i.isLogin.currentProfile.department == Profile.Department.CUSTOMER && !i.isLogin.currentProfile.isVerified) {
            JButton button = createComp.createJButton(
                "Verify", 
                570, 430, 
                90, 50, 
                new roundedBorder(15, Color.BLACK, null), Color.BLACK,
                new Font("Arial", Font.BOLD, 20)
            );
            button.setVisible(true);
            button.addActionListener( _ -> { _promptVerify(); } );

            add(button);  
            S.dummy.add(button);          
        }
    }

    void _deleteAccount() {
        JButton button = createComp.createJButton(
            "Delete", 
            800, 370, 
            170, 50, 
            new roundedBorder(15, Color.BLACK, null), Color.BLACK,
            new Font("Arial", Font.BOLD, 20)
        );
        button.setVisible(true);
        button.setEnabled(true);
        button.addActionListener( _ -> { _promptDelete(); } );

        add(button);
        S.dummy.add(button);
    }

    /*//////////////////////////////////////////////////////////////
                          Component Clearance
    //////////////////////////////////////////////////////////////*/    

    public void _removeChanges() { PanelHelper.clear(change); change = null; }

    void _promptChanges() {
        change = new changeInformation(i, window, this);
        change.setBounds(400, 200, 500, 500);
        change.setVisible(true);

        i.frame.getContentPane().add(change);
        
        i.compAnimStorage.addAnim(
            new componentAnim(
                change, 
                400, 200, 
                400, 100, 
                i.scrollPane
            ).start()
        );
    }

    public void _removeVerify() { PanelHelper.clear(i.Verify); }

    public void _removeEverything() { PanelHelper.clear(i.Deletion); }

    void _promptVerify() {
        helpStoreComp._startDropDown(
            i, 
            () -> i.Verify = new Verify(i, window, i.isLogin.currentProfile.username, this), 
            () -> i.Verify, 
            800, 400
        );
    }    

    void _promptDelete() {
        helpStoreComp._startDropDown(
            i, 
            () -> i.Deletion = new Deletion(i, window, this), 
            () -> i.Deletion, 
            800, 200
        );
    }

    public void _close() {
        blur.removeBlur();
        PanelHelper.clear(this);
        SwingUtilities.invokeLater(
            () -> {
                if (user == null) window._loadFrontPage();
                if (user != null) window._loadSecondPage();
            }
        );
    }

}
