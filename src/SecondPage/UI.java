package SecondPage;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import Components.Window;
import Components.initializer;
import Helper.Comp.PanelHelper;
import Helper.Comp.helpStoreComp;
import Helper.Config.roundedBorder;
import Helper.fileSystem.imageSystem;
import SecondPage.EmployeePage.verifiedDB.CustomerDB;
import SecondPage.EmployeePage.verifiedDB.InventoryDB;
import SecondPage.EmployeePage.verifiedDB.ManagerDB;
import SecondPage.EmployeePage.verifiedDB.SalesmanDB;
import Details.checkProfile;

public class UI extends JPanel {

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
                          background gradient
    //////////////////////////////////////////////////////////////*/

    private static final Color BLACK = Color.decode("#1a1919");  
    private static final Color DARK_BLUE = new Color(25, 25, 128);
    private static final Color WHITE = Color.decode("#f5e6f3");
    private static final Color PINK = new Color(255, 192, 203);    

   /*//////////////////////////////////////////////////////////////
                         paint background theme
    //////////////////////////////////////////////////////////////*/    
    
    @Override
    protected void paintComponent(Graphics G) {
        super.paintComponent(G);
        Graphics2D G2D = (Graphics2D) G;

        G2D.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        // determine the gradient colors
        Color topColor = i.isDarkTheme.isDarkTheme ? BLACK : WHITE;
        Color bottomColor = i.isDarkTheme.isDarkTheme ? DARK_BLUE : PINK;

        // setup gradient
        GradientPaint gradient = new GradientPaint(
            0, 0, topColor, 
            getWidth(), getHeight(), bottomColor
        );  

        // apply gradient
        G2D.setPaint(gradient);
        G2D.fillRect(0, 0, getWidth(), getHeight());
    }       

    /*//////////////////////////////////////////////////////////////
                            JPanel Settings
    //////////////////////////////////////////////////////////////*/    

    void _init_() { setLayout(null); _addTopParts(); i.switchThemeComp.dummy.add(this); } 
    
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
        companyLogo.addActionListener(_ -> { PanelHelper.clear(this); SwingUtilities.invokeLater(() -> { w._loadFrontPage(); }); });
        i.switchThemeComp.dummy.add(companyLogo);

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
        i.switchThemeComp.dummy.add(profilePic);

        // right side - theme icon
        JButton themeIcon = helpStoreComp.addJButton(
            i, this, 
            imageSystem._scaleImage(
                i.isDarkTheme.isDarkTheme ? LIGHT_BUTTON : DARK_BUTTON, 
                50, 50
            ),
            1070, 110, 
            1070, 20, 
            50, 50
        );
        themeIcon.addActionListener( _ -> { i.isDarkTheme.switchTheme(this, i.switchThemeComp); });
        i.switchThemeComp.dummy.add(themeIcon);

        // if (i.isLogin.currentProfile.department == Profile.Department.CUSTOMER) ;
        // if (i.isLogin.currentProfile.department == Profile.Department.SALESMAN) ;
        // if (i.isLogin.currentProfile.department == Profile.Department.MANAGER) ;
        // if (i.isLogin.currentProfile.department == Profile.Department.OWNER) ;
        // _addCustomerDatabase(); 
        _initializeCustomer();
        _initializeSalesman();
        _initializeManager();
        _initializeInventory();
        _initializeInventory();
        _customerButton();
        _salesmanButton();
        _managerButton();
        _inventoryButton();

    }

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
        i.switchThemeComp.dummy.add(i.CustomerDB);
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
        i.switchThemeComp.dummy.add(i.SalesmanDB);
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
        i.switchThemeComp.dummy.add(i.ManagerDB);
    }

    void _initializeInventory() {
        i.InventoryDB = new InventoryDB(
            i, 
            w, 
            this, 
            "Verified Inventory", 
            "Unverified Inventory",
            new String[] {"logo", "Car Name", "Buying Price", "Selling Price", "Bought From", "Sell To"},
            new String[] {"Username", "First Name", "Last Name", "Gender", "Age", "Approve", "Reject"}
        );
        i.switchThemeComp.dummy.add(i.InventoryDB);
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
        helpStoreComp.addJButton(
            i, this, 
            "Customers", 
            50, 80, 
            50, 130, 
            170, 50,
            new roundedBorder(15, Color.WHITE, imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)),
            Color.PINK,
            new Font("Arial", Font.BOLD, 18)
        ).addActionListener( _ -> { 
            _customerDBVisible(true);
            _salesmanDBVisible(false); 
            _managerDBVisible(false);
            _inventoryDBVisible(false);
        });
    }

    void _salesmanButton() {
        helpStoreComp.addJButton(
            i, this, 
            "Salesmen", 
            230, 80, 
            230, 130, 
            170, 50,
            new roundedBorder(15, Color.WHITE, imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)),
            Color.PINK,
            new Font("Arial", Font.BOLD, 18)
        ).addActionListener( _ -> {
            _customerDBVisible(false);
            _salesmanDBVisible(true);
            _managerDBVisible(false);
            _inventoryDBVisible(false);
        });
    }

    void _managerButton() {
        helpStoreComp.addJButton(
            i, this, 
            "Managers", 
            410, 80, 
            410, 130, 
            170, 50,
            new roundedBorder(15, Color.WHITE, imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)),
            Color.PINK,
            new Font("Arial", Font.BOLD, 18)
        ).addActionListener( _ -> {
            _customerDBVisible(false);
            _salesmanDBVisible(false);
            _managerDBVisible(true);
            _inventoryDBVisible(false);
        });
    }

    void _inventoryButton() {
        helpStoreComp.addJButton(
            i, this, 
            "Inventory", 
            590, 80, 
            590, 130, 
            170, 50, 
            new roundedBorder(15, Color.WHITE, imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)), 
            Color.PINK, 
            new Font("Arial", Font.BOLD, 18)
        ).addActionListener( _ -> {
            _customerDBVisible(false);
            _salesmanDBVisible(false);
            _managerDBVisible(false);
            _inventoryDBVisible(true);
        });
    }  

}
