package SecondPage;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import Comment.__init__;
import Components.Window;
import Components.initializer;
import Helper.Comp.helpStoreComp;
import Helper.Config.roundedBorder;
import Helper.Config.PanelConfig.GradientPanel;
import Helper.Config.PanelConfig.PanelHelper;
import Helper.fileSystem.imageSystem;
import Helper.login.Profile;
import SecondPage.EmployeePage.verifiedDB.CustomerDB;
import SecondPage.EmployeePage.verifiedDB.InventoryDB;
import SecondPage.EmployeePage.verifiedDB.ManagerDB;
import SecondPage.EmployeePage.verifiedDB.SalesmanDB;
import Details.checkProfile;

public class UI extends GradientPanel {

    /*//////////////////////////////////////////////////////////////
                                 images
    //////////////////////////////////////////////////////////////*/ 
    
    private final ImageIcon COMPANY_LOGO = imageSystem.COMPANY_LOGO;

    private final ImageIcon DARK_BUTTON = imageSystem.DARK_BUTTON;
    private final ImageIcon LIGHT_BUTTON = imageSystem.LIGHT_BUTTON;
    
    /*//////////////////////////////////////////////////////////////
                              constructor
    //////////////////////////////////////////////////////////////*/    
 
    initializer i;
    Window w;
    JScrollPane scroll;

    public UI(initializer i, Window w) { this.i = i; this.w = w; }
    public void _setScroll(JScrollPane scroll) { this.scroll = scroll; _init_(); }

    /*//////////////////////////////////////////////////////////////
                             Parent JPanel
    //////////////////////////////////////////////////////////////*/
    
    @Override
    public boolean isDarkTheme() { return i.isDarkTheme.isDarkTheme; }

    /*//////////////////////////////////////////////////////////////
                            JPanel Settings
    //////////////////////////////////////////////////////////////*/    

    void _init_() { setLayout(null); _addTopParts(); } 
    
    /*//////////////////////////////////////////////////////////////
                                Top Part
    //////////////////////////////////////////////////////////////*/    
    
    void _addTopParts() {

        // left side - company logo
        JButton companyLogo = helpStoreComp.addJButton(
            i, this, 
            imageSystem._scaleImage(
                COMPANY_LOGO, 
                120, 120
            ), 
            50, 20, 
            50, 2, 
            110, 100
        );
        companyLogo.addActionListener(
            _ -> { 
                PanelHelper.clear(null, this); 
                SwingUtilities.invokeLater(() -> { w._loadFrontPage(); });
            }
        );

        // right side - profile pic
        JButton profilePic = helpStoreComp.addJButton(
            i, this, 
            imageSystem._scaleImage(
                i.isLogin.currentProfile.pfp, 
                50, 50
            ), 
            1150, 70, 
            1150, 20, 
            50, 50
        );
        profilePic.addActionListener(
            _ -> {
                helpStoreComp._startDropDown(
                    i, 
                    () -> { i.checkProfile = new checkProfile(i, w, null); }, 
                    () -> i.checkProfile, 
                    1000, 500
                );
            }
        );

        // right side - theme icon
        _helpAddThemeIcon(1070, 20);

        _initializeCustomerDB();
        _initializeInventoryDB();
        if (i.isLogin.currentProfile.department != Profile.Department.SALESMAN) _initializeSalesmanDB();
        if (i.isLogin.currentProfile.department == Profile.Department.OWNER) _initializeManagerDB();  

    }

    void _helpAddThemeIcon(int targetX, int targetY) {
        helpStoreComp.addJButton(
            i, this, 
            imageSystem._scaleImage(imageSystem.CHATBOX_ICON, 50, 50), 
            targetX, targetY + 90, 
            targetX, targetY, 
            50, 50
        ).addActionListener( _ -> {  __init__.CommentInit(i, w, this);});;

        helpStoreComp.addJButton(
            i, this,
            imageSystem._scaleImage(
                i.isDarkTheme.isDarkTheme ? LIGHT_BUTTON : DARK_BUTTON, 
                50, 50
            ),
            targetX - 70, targetY + 90, 
            targetX - 70, targetY, 
            50, 50
        ).addActionListener( _ -> { i.isDarkTheme.switchTheme(this, i.switchThemeComp); });
    }    

    void _initializeCustomerDB() { _initializeCustomer(); _customerButton(); }
    void _initializeInventoryDB() { _initializeInventory(); _inventoryButton(); }
    void _initializeSalesmanDB() { _initializeSalesman(); _salesmanButton(); }
    void _initializeManagerDB() { _initializeManager(); _managerButton(); }

    /*//////////////////////////////////////////////////////////////
                              Create Table
    //////////////////////////////////////////////////////////////*/    

    void _initializeCustomer() {
        i.CustomerDB = new CustomerDB(
            i, 
            w, 
            this, 
            "Verified Customers Profile", 
            "Unverified Customers Profile",
            new String[] {"Username", "First Name", "Last Name", "Gender", "Age"},
            new String[] {"Username", "First Name", "Last Name", "Gender", "Age", "Approve", "Reject"}
        );
    }
    
    void _initializeSalesman() {
        i.SalesmanDB = new SalesmanDB(
            i, 
            w, 
            this, 
            "Verified Salesmen Profile", 
            "Unverified Salesmen Profile",
            new String[] {"Username", "First Name", "Last Name", "Gender", "Age"},
            new String[] {"Username", "First Name", "Last Name", "Gender", "Age", "Approve", "Reject"}
        );
    }

    void _initializeManager() {
        i.ManagerDB = new ManagerDB(
            i, 
            w, 
            this, 
            "Verified Managers Profile", 
            "Unverified Managers Profile", 
            new String[] {"Username", "First Name", "Last Name", "Gender", "Age"},
            new String[] {"Username", "First Name", "Last Name", "Gender", "Age", "Approve", "Reject"}
        );
    }

    void _initializeInventory() {
        i.InventoryDB = new InventoryDB(
            i, 
            w, 
            this, 
            "Inventory", 
            null,
            new String[] {"Car ID", "logo", "Car Name", "Status", "Account Payable", "Account Receivable"}
        );
    }

    /*//////////////////////////////////////////////////////////////
                              set visible
    //////////////////////////////////////////////////////////////*/    

    void _customerDBVisible(boolean bool) {
        if (bool) {
            i.CustomerDB._setUnverifiedVisibility(false);
            i.CustomerDB._setVerifiedVisibility(true);
        }
        else {
            i.CustomerDB._setUnverifiedVisibility(false);
            i.CustomerDB._setVerifiedVisibility(false);
        }
    }

    void _salesmanDBVisible(boolean bool) {
        if (bool) {
            i.SalesmanDB._setUnverifiedVisibility(false);
            i.SalesmanDB._setVerifiedVisibility(true);
        }
        else {
            i.SalesmanDB._setUnverifiedVisibility(false);
            i.SalesmanDB._setVerifiedVisibility(false);
        }
    }

    void _managerDBVisible(boolean bool) {
        if (bool) {
            i.ManagerDB._setUnverifiedVisibility(false);
            i.ManagerDB._setVerifiedVisibility(true);
        }
        else {
            i.ManagerDB._setUnverifiedVisibility(false);
            i.ManagerDB._setVerifiedVisibility(false);
        }
    }

    void _inventoryDBVisible(boolean bool) {
        if (bool) {
            i.InventoryDB._setUnverifiedVisibility(false);
            i.InventoryDB._setVerifiedVisibility(true);
        }
        else {
            i.InventoryDB._setUnverifiedVisibility(false);
            i.InventoryDB._setVerifiedVisibility(false);
        }
    }

    /*//////////////////////////////////////////////////////////////
                              Button Topic
    //////////////////////////////////////////////////////////////*/    

    void _customerButton() {
        JButton button = helpStoreComp.addJButton(
            i, this, 
            "Customers", 
            50, 80, 
            50, 130, 
            170, 50,
            new roundedBorder(
                15, 
                i.isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLUE, 
                imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)
            ),
            i.isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE,
            new Font("Arial", Font.BOLD, 18)
        );
        button.addActionListener( _ -> { 
            if (i.CustomerDB != null) _customerDBVisible(true);
            if (i.SalesmanDB != null) _salesmanDBVisible(false); 
            if (i.ManagerDB != null) _managerDBVisible(false);
            if (i.InventoryDB != null) _inventoryDBVisible(false);
        });

        i.switchThemeComp.SecondPageButtons.add(button);
    }

    void _salesmanButton() {
        JButton button = helpStoreComp.addJButton(
            i, this, 
            "Salesmen", 
            410, 80, 
            410, 130, 
            170, 50,
            new roundedBorder(
                15, 
                i.isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLUE, 
                imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)
            ),
            i.isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE,
            new Font("Arial", Font.BOLD, 18)
        );
        button.addActionListener( _ -> {
            if (i.CustomerDB != null) _customerDBVisible(false);
            if (i.SalesmanDB != null) _salesmanDBVisible(true);
            if (i.ManagerDB != null) _managerDBVisible(false);
            if (i.InventoryDB != null) _inventoryDBVisible(false);
        });

        i.switchThemeComp.SecondPageButtons.add(button);
    }

    void _managerButton() {
        JButton button = helpStoreComp.addJButton(
            i, this, 
            "Managers", 
            590, 80, 
            590, 130, 
            170, 50,
            new roundedBorder(
                15, 
                i.isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLUE, 
                imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)
            ),
            i.isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE,
            new Font("Arial", Font.BOLD, 18)
        );
        button.addActionListener( _ -> {
            if (i.CustomerDB != null) _customerDBVisible(false);
            if (i.SalesmanDB != null) _salesmanDBVisible(false);
            if (i.ManagerDB != null) _managerDBVisible(true);
            if (i.InventoryDB != null) _inventoryDBVisible(false);
        });

        i.switchThemeComp.SecondPageButtons.add(button);
    }

    void _inventoryButton() {
        JButton button = helpStoreComp.addJButton(
            i, this, 
            "Inventory", 
            230, 80, 
            230, 130, 
            170, 50, 
            new roundedBorder(
                15, 
                i.isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLUE, 
                imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)), 
            i.isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE, 
            new Font("Arial", Font.BOLD, 18)
        );
        button.addActionListener( _ -> {
            if (i.CustomerDB != null) _customerDBVisible(false);
            if (i.SalesmanDB != null) _salesmanDBVisible(false);
            if (i.ManagerDB != null) _managerDBVisible(false);
            if (i.InventoryDB != null) _inventoryDBVisible(true);
        });

        i.switchThemeComp.SecondPageButtons.add(button);

    }  

}
