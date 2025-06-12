package Details;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import Components.SwitchThemeComp;
import Components.Window;
import Components.initializer;
import Helper.blur;
import Helper.Animation.componentAnim;
import Helper.Comp.createComp;
import Helper.Comp.helpStoreComp;
import Helper.Config.roundedBorder;
import Helper.Config.PanelConfig.DropdownPanel;
import Helper.Config.PanelConfig.PanelHelper;
import Helper.fileSystem.ImageUploader;
import Helper.fileSystem.fontSystem;
import Helper.fileSystem.imageSystem;
import Helper.login.Profile;
import Inventory.stockDetails;
import LoginSystem.storage;

public class checkProfile extends DropdownPanel {
    
    blur blur;
    Window window;
    SwitchThemeComp S;
    storage storage;
    Profile.seeProfile profile;
    changeInformation change;
    initializer i;
    String user;

    JLabel header;
    JButton closeButton;
    JTextArea LeftInfo;
    JButton RightPFP;
    JTextArea RightInfo;

    JButton EditButton;
    JButton LogoutButton;
    JButton VerifyButton;
    JButton DeleteButton;

    JButton SwitchToCar;
    JButton SwitchToProfile;
    List<JTextArea> CarDetails = new ArrayList<>();

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

        /* _background(); */        _addX();               _logout();          
        _changeInformation();       _deleteAccount();      _switchButton();
        _addHeader();               _verify();             _addInformation();
        _CarDetails();              _addComp();

    }

    /*//////////////////////////////////////////////////////////////
                             X Close Button
    //////////////////////////////////////////////////////////////*/    

    void _addX() {
        closeButton = createComp.createJButton(
            "X", 
            900, 20, 
            60, 40, 
            new roundedBorder(10, Color.BLACK, null), Color.BLACK, 
            new Font("Arial", Font.BOLD, 20)
        );
        closeButton.addActionListener( _ -> { _close(); } );
    }  

    /*//////////////////////////////////////////////////////////////
                                 Header
    //////////////////////////////////////////////////////////////*/
    
    void _addHeader() {
        header = createComp.createJLabel(
            "Your Profile", 
            20, 40, 
            200, 50, 
            new Font("Arial", Font.BOLD, 25), 
            Color.BLACK
        );
        header.setVisible(true);
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
        if (user == null) {
            LeftInfo = createComp.createJTextArea(
                "First Name: " + profile.firstName + "\n\n" +
                "Last Name: " + profile.lastName + "\n\n" +
                "Username: " + profile.username + "\n\n" +
                "Gender: " + profile.gender + "\n\n" +
                "Age: " + profile.age + "\n\n" +
                "Phone Number: " + 0 + profile.phoneNumber,
                20, 120, 
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
                20, 120, 
                500, 500, 
                fontSystem.TAGES.deriveFont(25f), 
                null, Color.BLACK
            );
        }
    }

    /*//////////////////////////////////////////////////////////////
                               Right PFP
    //////////////////////////////////////////////////////////////*/  

    void _RightPFP() {
        ImageIcon pfp = user == null ?
                        imageSystem._scaleImage(profile.pfp, 100, 100) :
                        imageSystem._scaleImage(storage.searchUser(user).pfp, 100, 100);

        RightPFP = createComp.createJButton(
            pfp, 
            600, 100, 
            100, 100
        );

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
    }

    /*//////////////////////////////////////////////////////////////
                               Edit Info
    //////////////////////////////////////////////////////////////*/

    void _changeInformation() {
        if (user == null) {
            EditButton = createComp.createJButton(
                "Edit Details", 
                800, 430, 
                170, 50, 
                new roundedBorder(15, Color.BLACK, null), Color.BLACK,
                new Font("Arial", Font.BOLD, 20)
            );
            EditButton.addActionListener( _ -> { _promptChanges(); });
        }
    }

    /*//////////////////////////////////////////////////////////////
                                 Logout
    //////////////////////////////////////////////////////////////*/    

    void _logout() {
        if (user == null) {
            LogoutButton = createComp.createJButton(
                "Logout", 
                670, 430, 
                120, 50, 
                new roundedBorder(15, Color.BLACK, null), Color.BLACK,
                new Font("Arial", Font.BOLD, 20)
            );

            LogoutButton.addActionListener( 
                _ -> {
                    i.isLogin.currentProfile = null;
                    i.isLogin.isLogin = false;

                    PanelHelper.clear(blur, this);
                    SwingUtilities.invokeLater(() -> window._loadFrontPage());
                }
            );

        };
    }

    /*//////////////////////////////////////////////////////////////
                                 Verify
    //////////////////////////////////////////////////////////////*/    

    void _verify() {
        if (i.isLogin.currentProfile.department == Profile.Department.CUSTOMER && !i.isLogin.currentProfile.isVerified) {
            VerifyButton = createComp.createJButton(
                "Verify", 
                570, 430, 
                90, 50, 
                new roundedBorder(15, Color.BLACK, null), Color.BLACK,
                new Font("Arial", Font.BOLD, 20)
            );
            VerifyButton.addActionListener( _ -> { _promptVerify(); } );
        }
    }

    /*//////////////////////////////////////////////////////////////
                                 Delete
    //////////////////////////////////////////////////////////////*/    

    void _deleteAccount() {
        if (i.isLogin.currentProfile.department != Profile.Department.OWNER && user == null) {
            DeleteButton = createComp.createJButton(
                "Delete", 
                800, 370, 
                170, 50, 
                new roundedBorder(15, Color.BLACK, null), Color.BLACK,
                new Font("Arial", Font.BOLD, 20)
            );
            DeleteButton.addActionListener( _ -> { _promptDelete(); } );
        }
    }

    /*//////////////////////////////////////////////////////////////
                                 Switch
    //////////////////////////////////////////////////////////////*/
    
    void _switchButton() {
        if (Profile.Department.CUSTOMER == i.isLogin.currentProfile.department  || user != null) {
            SwitchToCar = createComp.createJButton(
                "Your Payment History", 
                230, 40, 
                250, 40, 
                new roundedBorder(15, Color.BLACK, null), 
                Color.BLACK,
                new Font("Arial", Font.BOLD, 15)
            );
            SwitchToCar.addActionListener( _ -> { _setProfileVisible(false); _setCarVisible(true); } );

            SwitchToProfile = createComp.createJButton(
                "Switch to Profile", 
                230, 40, 
                250, 40, 
                new roundedBorder(15, Color.BLACK, null), 
                Color.BLACK,
                new Font("Arial", Font.BOLD, 15)
            );
            SwitchToProfile.addActionListener( _ -> { _setProfileVisible(true); _setCarVisible(false); } );
        }
    }

    /*//////////////////////////////////////////////////////////////
                            Car Booking Info
    //////////////////////////////////////////////////////////////*/    

    void _CarDetails() {
        int LeftIndex = 0;
        int RightIndex = 0;

        List<stockDetails.transactDetails> cars = i.stockInventory.SearchCarViaUsername
                                                            (
                                                                user == null                      ?
                                                                i.isLogin.currentProfile.username : 
                                                                user
                                                            );

        if (cars != null) {                                                            
            for (stockDetails.transactDetails car : cars) {
                if (car.carDetails.status == stockDetails.CarStatus.BOOKED) {
                    CarDetails.add(
                        createComp.createJTextArea(
                            car.carId + ", " + car.carDetails.carName + "\n" + 
                            "Booked on " + car.DateBookedAt + " at " + car.TimeBookedAt,
                            20, 100 + (LeftIndex * 50), 
                            500, 50, 
                            new Font("Arial", Font.BOLD, 15), 
                            null,
                            Color.BLACK
                        )
                    );
                    LeftIndex++;
                } else {
                    CarDetails.add(
                        createComp.createJTextArea(
                            "Salesman: " + car.Salesman + "\n" +
                            car.carId + ", " + car.carDetails.carName + "\n" +
                            "Sold on " + car.DateSold + " at " + car.TimeSold,
                            520, 100 + (RightIndex * 70), 
                            500, 50, 
                            new Font("Arial", Font.BOLD, 15), 
                            null,
                            Color.BLACK
                        )
                    );
                    RightIndex++;
                }
            }
        }
    }

    /*//////////////////////////////////////////////////////////////
                             Add to JPanel
    //////////////////////////////////////////////////////////////*/
    
    void _addComp() {

        if (EditButton != null) add(EditButton); 
        if (LogoutButton != null) add(LogoutButton);
        if (VerifyButton != null) add(VerifyButton);
        if (DeleteButton != null) add(DeleteButton);

        if (header != null) add(header); 
        if (closeButton != null) add(closeButton); 
        if (LeftInfo != null) add(LeftInfo); 
        if (RightPFP != null) add(RightPFP);
        if (RightInfo != null) add(RightInfo); 

        if (SwitchToCar != null) add(SwitchToCar); 
        if (SwitchToProfile != null) add(SwitchToProfile);
    if (CarDetails.size() > 0) CarDetails.stream().forEach(i -> { add(i); });

        _setProfileVisible(true);
        _setCarVisible(false);

    }

    void _setProfileVisible(boolean bool) {
        if (closeButton != null) closeButton.setVisible(bool);  
        if (LeftInfo != null) LeftInfo.setVisible(bool);
        if (RightPFP != null) RightPFP.setVisible(bool);
        if (RightInfo != null) RightInfo.setVisible(bool);
        
        if (EditButton != null) EditButton.setVisible(bool);
        if (LogoutButton != null) LogoutButton.setVisible(bool);
        if (VerifyButton != null) VerifyButton.setVisible(bool);
        if (DeleteButton != null) DeleteButton.setVisible(bool);

        if (SwitchToCar != null) SwitchToCar.setVisible(bool);
    }

    void _setCarVisible(boolean bool) {
        if (SwitchToProfile != null) SwitchToProfile.setVisible(bool);
        if (CarDetails.size() > 0) CarDetails.stream().forEach(i -> { i.setVisible(bool); });
    }

    /*//////////////////////////////////////////////////////////////
                          Component Clearance
    //////////////////////////////////////////////////////////////*/    

    public void _removeChanges() {remove(change); change = null; }

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

    public void _removeVerify() { remove(i.Verify); i.Verify = null; }

    public void _removeEverything() { remove(i.Deletion); i.Deletion = null; }

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
        PanelHelper.clear(blur, this);
        SwingUtilities.invokeLater(
            () -> {
                if (i.component != null) window._loadFrontPage();
                if (i.UI != null) window._loadDBPage();
                if (i.Purchase != null) window._loadPurchasePage();
            }
        );
    }

    public void _closeEverything() {
        PanelHelper.clear(blur, this);
        SwingUtilities.invokeLater( () -> { window._loadFrontPage(); } );
    }

}
