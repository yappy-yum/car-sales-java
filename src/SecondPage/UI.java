package SecondPage;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Components.Window;
import Components.initializer;
import Helper.Comp.helpStoreComp;
import Helper.Config.roundedBorder;
import Helper.fileSystem.imageSystem;
import SecondPage.EmployeePage.CustomerDB;
import SecondPage.EmployeePage.InventoryDB;
import SecondPage.EmployeePage.ManagerDB;
import SecondPage.EmployeePage.SalesmanDB;
import Details.checkProfile;
import Helper.login.Profile;

public class UI extends JPanel {

    /*//////////////////////////////////////////////////////////////
                                 images
    //////////////////////////////////////////////////////////////*/ 
    
    private final ImageIcon COMPANY_LOGO = imageSystem.COMPANY_LOGO;

    private final ImageIcon DARK_BUTTON = imageSystem.DARK_BUTTON;
    private final ImageIcon LIGHT_BUTTON = imageSystem.LIGHT_BUTTON;

    /*//////////////////////////////////////////////////////////////
                            Database Buttons
    //////////////////////////////////////////////////////////////*/
    
    JButton customerButton, salesmanButton, managerButton, InventoryButton, JobButton;
    JLabel customerLabel, salesmanLabel, managerLabel, InventoryLabel, JobLabel;
    
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
        companyLogo.addActionListener(_ -> { w._loadFrontPage(); });
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
                    () -> { i.checkProfile = new checkProfile(i, w); }, 
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
            i, this, 
            "Customers Profile", 
            Profile.Department.CUSTOMER, 
            new String[] {"Username", "First Name", "Last Name", "Gender", "Age"}
        );
        i.switchThemeComp.dummy.add(i.CustomerDB);
    }
    
    void _initializeSalesman() {
        i.SalesmanDB = new SalesmanDB(
            i, this, 
            "Salesmen Profile", 
            Profile.Department.SALESMAN,
            new String[] {"Username", "First Name", "Last Name", "Gender", "Age"}
        );
        i.switchThemeComp.dummy.add(i.SalesmanDB);
    }

    void _initializeManager() {
        i.ManagerDB = new ManagerDB(
            i, this, 
            "Managers Profile", 
            Profile.Department.MANAGER,
            new String[] {"Username", "First Name", "Last Name", "Gender", "Age"}
        );
        i.switchThemeComp.dummy.add(i.ManagerDB);
    }

    void _initializeInventory() {
        i.InventoryDB = new InventoryDB(
            i, this, 
            "Inventory", 
            Profile.Department.SALESMAN,
            new String[] {"Car Name", "Buying Price", "Selling Price", "Bought From", "Sell To"}
        );
        i.switchThemeComp.dummy.add(i.InventoryDB);
    }

    /*//////////////////////////////////////////////////////////////
                              set visible
    //////////////////////////////////////////////////////////////*/    

    void _customerDBVisible(boolean bool) {
        i.CustomerDB.label.setVisible(bool);
        i.CustomerDB.searchBar.setVisible(bool);
        i.CustomerDB.searchIcon.setVisible(bool);
        i.CustomerDB.table.getTableHeader().setVisible(bool);
        i.CustomerDB.table.setVisible(bool);
    }

    void _salesmanDBVisible(boolean bool) {
        i.SalesmanDB.label.setVisible(bool);
        i.SalesmanDB.searchBar.setVisible(bool);
        i.SalesmanDB.searchIcon.setVisible(bool);
        i.SalesmanDB.table.getTableHeader().setVisible(bool);
        i.SalesmanDB.table.setVisible(bool);
    }

    void _managerDBVisible(boolean bool) {
        i.ManagerDB.label.setVisible(bool);
        i.ManagerDB.searchBar.setVisible(bool);
        i.ManagerDB.searchIcon.setVisible(bool);
        i.ManagerDB.table.getTableHeader().setVisible(bool);
        i.ManagerDB.table.setVisible(bool);
    }

    void _inventoryBDVisible(boolean bool) {
        if (i.InventoryDB.label != null) i.InventoryDB.label.setVisible(bool);
        if (i.InventoryDB.searchBar != null) i.InventoryDB.searchBar.setVisible(bool);
        if (i.InventoryDB.searchIcon != null) i.InventoryDB.searchIcon.setVisible(bool);
        i.InventoryDB.tables.stream().forEach(i -> { if (i.table != null) i.table.setVisible(bool); });
        i.InventoryDB.tables.stream().forEach(i -> { if (i.table != null) i.table.getTableHeader().setVisible(bool); });
        i.InventoryDB.tables.stream().forEach(i -> { if (i.table != null) i.text.setVisible(bool); });
        i.InventoryDB.tables.stream().forEach(i -> { if (i.table != null) i.logo.setVisible(bool); });
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
            _inventoryBDVisible(false);
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
            _inventoryBDVisible(false);
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
            _inventoryBDVisible(false);
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
            _inventoryBDVisible(true);
        });
    }

}
