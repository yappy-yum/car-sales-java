package Components;
import frontPage.FrontPage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Helper.Comp.createScroll;
import Helper.Config.PanelConfig.PanelHelper;
import Helper.fileSystem.imageSystem;
import SecondPage.UI;
import SecondPage.CustomerPage.Purchase;
import StoreAnimation.compAnimStorage;
import StoreAnimation.videoAnimStorage;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;


public class Window {
    
    initializer i;

    /*//////////////////////////////////////////////////////////////
                              constructor
    //////////////////////////////////////////////////////////////*/    

    public Window(initializer i) {
        this.i = i;
        
        _initializeWindow();
        _initializeCursor();
        _initFrontPage();
        
        i.frame.setVisible(true);  
        i.frontPage = new FrontPage(i, this);           
    }
    
   /*//////////////////////////////////////////////////////////////
                        initialize window JFrame
    //////////////////////////////////////////////////////////////*/    

    /**
     * <b> Initializes GUI window </b> 
     * 
     * <p> HD (720p): 1280 x 720          
     * <p> Full HD (1080p): 1920 x 1080     
     * <p> Quad HD (1440p): 2560 x 1440     
     * <p> Ultra HD (4K): 3840 x 2160       
     * <p> Ultra HD (8K): 7680 x 4320    
     * 
     * <p>   
     * 
     * <b> HD size with unallowed resizable is used </b>
     * 
     */
    void _initializeWindow() {
        i.frame.setTitle("Millionaire Hub");
        i.frame.setIconImage(imageSystem.COMPANY_LOGO.getImage());
        i.frame.setResizable(false);
        i.frame.setSize(1280, 720); // HD size
        i.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        i.frame.setLocationRelativeTo(null);
        i.frame.setLayout(null);
    }

    /*//////////////////////////////////////////////////////////////
                        initialize custom cursor
    //////////////////////////////////////////////////////////////*/    

    /**
     * set custom cursor
     * 
     */
    void _initializeCursor() {
        Cursor cursor = Toolkit
                            .getDefaultToolkit()
                            .createCustomCursor(
                                imageSystem.MOUSE_CURSOR.getImage(), 
                                new Point(0, 0),
                                "professional cursor"
                            );
        i.frame.setCursor(cursor);
    }       


    /*//////////////////////////////////////////////////////////////
                      initialize hardware control
    //////////////////////////////////////////////////////////////*/    
    
    /**
     * 
     * set up mouse scroll wheel and keyboard up and down key to move the 
     * window scroll bar up and down
     * 
     * @param scrollPane a scroll bar component to be applied hardware control to
     * 
     */
    void _initializeScrollControl(JPanel component) {
        i.scrollPane = new JScrollPane(component);
        i.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        createScroll.keyboardScroll(i.scrollPane);
        createScroll.mouseScroll(i.scrollPane);
    }    

    /*//////////////////////////////////////////////////////////////
                                 reload
    //////////////////////////////////////////////////////////////*/    

    /**
     * reload all components, especially when user clikc close button
     * 
     */
    public void _loadFrontPage() {              
        _throw();
        _initFrontPage();

        // @audit
        // not needed since the JVM actually knows and collect all unused objects
        // and this will actually makes the program lagger too
        // System.gc();
            
        i.frame.setVisible(true);
        i.frontPage = new FrontPage(i, this); 
    }

    public void _loadDBPage() {
        _throw();
        _initDBPage();
        
        i.frame.setVisible(true);
    }

    public void _loadPurchasePage() {
        _throw();
        _initPurchasePage();
        
        i.frame.setVisible(true);
    }

    /*//////////////////////////////////////////////////////////////
                             helper method
    //////////////////////////////////////////////////////////////*/    

    void _initFrontPage() {
        _nullify();

        i.storeVid = new storeVid();
        i.compAnimStorage = new compAnimStorage();
        i.videoAnimStorage = new videoAnimStorage();
        i.switchThemeComp = new SwitchThemeComp(i);
        i.component = new Components(i, this);
    
        _initializeScrollControl(i.component);
        i.component.addScroll(i.scrollPane);

        i.frame.getContentPane().removeAll();
        i.frame.getContentPane().setLayout(new BorderLayout());
        i.frame.getContentPane().add(i.scrollPane, BorderLayout.CENTER);
    }

    void _initDBPage() {
        _nullify();

        i.switchThemeComp = new SwitchThemeComp(i);
        i.compAnimStorage = new compAnimStorage();
        i.UI = new UI(i, this);

        _initializeScrollControl(i.UI);
        i.UI._setScroll(i.scrollPane);
        PanelHelper.resizeHeightToFit(i.UI);

        i.frame.getContentPane().removeAll();
        i.frame.getContentPane().setLayout(new BorderLayout());
        i.frame.getContentPane().add(i.scrollPane, BorderLayout.CENTER);
        
    }

    void _initPurchasePage() {
        _nullify();

        i.switchThemeComp = new SwitchThemeComp(i);
        i.compAnimStorage = new compAnimStorage();
        i.Purchase = new Purchase(i, this);

        _initializeScrollControl(i.Purchase);
        PanelHelper.resizeHeightToFit(i.Purchase);

        i.frame.getContentPane().removeAll();
        i.frame.getContentPane().setLayout(new BorderLayout());
        i.frame.getContentPane().add(i.scrollPane, BorderLayout.CENTER);
    }

    void _nullify() {
        i.scrollPane = null;
        i.component = null;
        i.switchThemeComp = null;
        i.frontPage = null;
        i.Customer = null;
        i.AboutUs = null;
        i.TaC = null;
        i.Job = null;
        i.compAnimStorage = null;
        i.videoAnimStorage = null;
        i.storeVid = null;
        i.checkProfile = null;
        i.UI = null;
        i.CustomerDB = null;
        i.SalesmanDB = null;
        i.ManagerDB = null;
        i.InventoryDB = null;
        i.password = null;
        i.VerifyCust = null;
        i.VerifyEmployee = null;
        i.Verify = null;
        i.checkCar = null;
        i.AddCar = null;
        i.Deletion = null;
        i.Purchase = null;
        i.comment = null;
    }

    void _throw() {
        i.frame.getContentPane().removeAll();
        if (i.switchThemeComp != null) i.switchThemeComp.clearEverything();
        if (i.checkProfile != null) i.frame.remove(i.checkProfile);
        if (i.scrollPane != null) i.frame.remove(i.scrollPane);
        if (i.UI != null) i.frame.remove(i.UI);
        if (i.component != null) i.component._remove();
        if (i.component != null) i.frame.remove(i.component);
        if (i.Customer != null) i.frame.remove(i.Customer);
        if (i.AboutUs != null) i.frame.remove(i.AboutUs);
        if (i.TaC != null) i.frame.remove(i.TaC);
        if (i.Job != null) i.frame.remove(i.Job);
        if (i.compAnimStorage != null) i.compAnimStorage.disposeAnim();
        if (i.videoAnimStorage != null) i.videoAnimStorage.disposeAnim();
        if (i.password != null) i.frame.remove(i.password);
        if (i.VerifyCust != null) i.frame.remove(i.VerifyCust);
        if (i.VerifyEmployee != null) i.frame.remove(i.VerifyEmployee);
        if (i.Verify != null) i.frame.remove(i.Verify);
        if (i.checkCar != null) i.frame.remove(i.checkCar);
        if (i.AddCar != null) i.frame.remove(i.AddCar);
        if (i.Deletion != null) i.frame.remove(i.Deletion);
        if (i.Purchase != null) i.frame.remove(i.Purchase);
        if (i.comment != null) i.frame.remove(i.comment);
        if (i.storeVid != null) new Thread(() -> i.storeVid.clearAll()).start(); // @audit may slower during clearance, therefore push it to background

        i.frame.revalidate();
        i.frame.repaint();
    }

}