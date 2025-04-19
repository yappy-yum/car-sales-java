package Components;
import frontPage.FrontPage;
import frontPage.isDarkTheme;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import Helper.Comp.createScroll;
import Helper.fileSystem.imageSystem;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;


public class Window {
    
    JFrame frame = new JFrame();
    JScrollBar scrollBar;


    public Window() {
        _initializeWindow();
        _initializeCursor();
    
        isDarkTheme i = new isDarkTheme();
        Components component = new Components(i);
    
        JScrollPane scrollPane = new JScrollPane(component);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    
        // Hook up custom scroll control
        _initializeControl(scrollPane);
        component.addScroll(scrollPane);
    
        // Fix layout
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
    
        frame.setVisible(true);
    
        // Reset scroll position
        SwingUtilities.invokeLater(() -> {
            scrollPane.getViewport().setViewPosition(new Point(0, 0));
        });

        new FrontPage(i, component, scrollPane);

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
        frame.setTitle("millionaire hub");
        frame.setResizable(false);
        frame.setSize(1280, 720); // HD size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
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
        frame.setCursor(cursor);
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
    void _initializeControl(JScrollPane scrollPane) {
        createScroll.keyboardScroll(scrollPane);
        createScroll.mouseScroll(scrollPane);
    }    

}