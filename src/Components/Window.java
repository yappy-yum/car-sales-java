package Components;
import frontPage.FrontPage;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import Helper.Comp.createScroll;
import Helper.fileSystem.imageSystem;

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
        _init();
        
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
        i.frame.setTitle("millionaire hub");
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
    void _initializeScrollControl(Components component) {
        i.scrollPane = new JScrollPane(component);
        i.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        createScroll.keyboardScroll(i.scrollPane);
        createScroll.mouseScroll(i.scrollPane);
        
        component.addScroll(i.scrollPane);
    }    

    /*//////////////////////////////////////////////////////////////
                                 reload
    //////////////////////////////////////////////////////////////*/    

    /**
     * reload all components, especially when user has successfully login
     * 
     */
    public void _reloadEverything() {              
        i.frame.getContentPane().removeAll();
        if (i.scrollPane != null) i.frame.remove(i.scrollPane);
        if (i.component != null) i.frame.remove(i.component);
        if (i.Customer != null) i.frame.remove(i.Customer);
        if (i.AboutUs != null) i.frame.remove(i.AboutUs);
        if (i.TaC != null) i.frame.remove(i.TaC);
        if (i.Job != null) i.frame.remove(i.Job);
        
        i.switchThemeComp.clearEverything();
        _init();
            
        i.frame.setVisible(true);
        i.frontPage = new FrontPage(i, this); 
    }

    /*//////////////////////////////////////////////////////////////
                             helper method
    //////////////////////////////////////////////////////////////*/    

    void _init() {
        i.scrollPane = null;
        i.component = null;
        i.switchThemeComp = null;
        i.frontPage = null;
        i.Customer = null;
        i.AboutUs = null;
        i.TaC = null;

        i.switchThemeComp = new SwitchThemeComp(i);
        i.component = new Components(i, this);
    
        _initializeScrollControl(i.component);

        i.frame.getContentPane().removeAll();
        i.frame.getContentPane().setLayout(new BorderLayout());
        i.frame.getContentPane().add(i.scrollPane, BorderLayout.CENTER);
    }

}