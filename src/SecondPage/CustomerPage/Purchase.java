package SecondPage.CustomerPage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Comment.__init__;
import Components.Window;
import Components.initializer;
import Details.checkProfile;
import Helper.Comp.PanelHelper;
import Helper.Comp.createComp;
import Helper.Comp.helpStoreComp;
import Helper.Config.BookingConfig;
import Helper.Config.roundedBorder;
import Helper.fileSystem.imageSystem;
import Helper.login.loginComp;
import Inventory.stockDetails;
import Inventory.stockInventory;
import frontPage.isDarkTheme;

public class Purchase extends JPanel {
    
    initializer i;
    isDarkTheme isDarkTheme;
    stockInventory stockInventory;
    Window W;
    JScrollPane scroll;

    JTextField searchBar;
    ArrayList<JPanel> panels = new ArrayList<>();

    ImageIcon COMPANY_LOGO = imageSystem._scaleImage(imageSystem.COMPANY_LOGO, 120, 120);

    /*//////////////////////////////////////////////////////////////
                              Constructor
    //////////////////////////////////////////////////////////////*/    

    public Purchase(initializer i, Window W) {
        this.i = i;
        this.W = W;
        this.isDarkTheme = i.isDarkTheme;
        this.stockInventory = i.stockInventory;

        SwingUtilities.invokeLater(() -> {            
            _addAllMenus(); 
            _addTops();
            i.switchThemeComp.dummy.add(this); 
            PanelHelper.resizeHeightToFit(this); 

            searchBar.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    filterPanels(searchBar.getText());
                }
            });

        });
    }

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
        Color topColor = isDarkTheme.isDarkTheme ? BLACK : WHITE;
        Color bottomColor = isDarkTheme.isDarkTheme ? DARK_BLUE : PINK;

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
                             1. Insert Data
    //////////////////////////////////////////////////////////////*/    

    void _addAllMenus() {
        int index = 0;

        for (stockDetails.transactDetails car : stockInventory.carDetails) {
            if (car.DateBookedAt == null && car.DateBookedAt == null && car.TimeBookedAt == null && car.TimeBookedAt == null) {
                JPanel panel = _addManuPanel(
                                    index % 2 == 0 ? 20 : 660, 
                                    (400 * (index / 2)) + 280 // @audit datatype int, therefore no decimals
                                );
                
                panel.putClientProperty("car", car);

                _addMenuComp(panel, car);
                _addComp(panel);
                i.switchThemeComp.dummy.add(panel);

                panel.setVisible(true);
                panels.add(panel);
                index++;
            }
        }

        SwingUtilities.invokeLater(() -> {
            i.scrollPane.getViewport().setViewPosition(new Point(0, 0));
        });           

        this.revalidate();
        this.repaint();
    }

    /*//////////////////////////////////////////////////////////////
                             2. create box
    //////////////////////////////////////////////////////////////*/    

    JPanel _addManuPanel(int x, int y) {
        return createComp.createJPanel(
            x, 
            y, 
            550, 
            370, 
            new roundedBorder(
                10, 
                i.isDarkTheme.isDarkTheme ? Color.PINK : Color.BLACK, 
                imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)
            )
        );
    }

    void _helpAddThemeIcon(int targetX, int targetY) {
        helpStoreComp.addJButton(
            i, this, 
            imageSystem._scaleImage(imageSystem.CHATBOX_ICON, 50, 50), 
            targetX, targetY + 90, 
            targetX, targetY, 
            50, 50
        ).addActionListener( _ -> { __init__.CommentInit(i, W, this); });;

        helpStoreComp.addJButton(
            i, this,
            imageSystem._scaleImage(
                i.isDarkTheme.isDarkTheme ? imageSystem.LIGHT_BUTTON : imageSystem.DARK_BUTTON, 
                50, 50
            ),
            targetX - 70, targetY + 90, 
            targetX - 70, targetY, 
            50, 50
        ).addActionListener( _ -> { i.isDarkTheme.switchTheme(this, i.switchThemeComp); });
    }    

    /*//////////////////////////////////////////////////////////////
                         3. Add Data to JPanel
    //////////////////////////////////////////////////////////////*/    

    void _addMenuComp(JPanel panel, stockDetails.transactDetails car) {

        // book
        JButton StartBook = createComp.createJButton(
            "Book Now!", 
            30, 290, 
            150, 50, 
            new roundedBorder(
                15, 
                i.isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE, 
                imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)
            ), 
            i.isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE, 
            new Font("Arial", Font.BOLD, 17)
        );
        _addComp(StartBook, panel);
        StartBook.addActionListener( 
            _ -> { 
                BookingConfig.showBookingDialog(
                    panel, 
                    W, 
                    stockInventory, 
                    car.carId, 
                    i.isLogin.currentProfile.username
                ); 
            } 
        );

        if (!i.isLogin.currentProfile.isVerified) {
            StartBook.setEnabled(false);

            loginComp.CreateNonLoginBookInstructor hi = new loginComp.CreateNonLoginBookInstructor(30, 230);

            panel.add(hi.textArea);
            panel.add(hi.textBackground);
            i.switchThemeComp.dummy.add(hi.textArea);
            i.switchThemeComp.dummy.add(hi.textBackground);

            StartBook.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    hi.textArea.setVisible(true);
                    hi.textBackground.setVisible(true);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    hi.textArea.setVisible(false);
                    hi.textBackground.setVisible(false);
                }
            });
        }

        // car brand
        _addComp(
            createComp.createJLabel(
                car.carDetails.carLogo, 
                20, 20, 
                80, 60
            ),
            panel
        );

        // car image
        _addComp(
            createComp.createJLabel(
                car.carDetails.carImage, 
                20, 90, 
                250, 200
            ),
            panel
        );

        // car full name
        _addComp(
            createComp.createJLabel(
                car.carDetails.carName, 
                120, 20, 
                500, 70, 
                new Font("Arial", Font.BOLD, 17), 
                i.isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE
            ),
            panel
        );

        // car id
        _addComp(
            createComp.createJLabel(
                car.carId, 
                300, 80, 
                500, 70, 
                new Font("Arial", Font.BOLD, 17), 
                i.isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE
            ),
            panel
        );

        // horse power
        _addComp(
            createComp.createJLabel(
                "Horse Power: " + car.carDetails.carHorsePower, 
                300, 130,  // 80
                500, 70, 
                new Font("Arial", Font.BOLD, 17), 
                i.isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE
            ),
            panel
        );

        // car length
        _addComp(
            createComp.createJLabel(
                "Length: " + car.carDetails.carLength, 
                300, 190, 
                500, 70, 
                new Font("Arial", Font.BOLD, 17), 
                i.isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE
            ),
            panel
        );

        // selling Price
        _addComp(
            createComp.createJLabel(
                "Selling Price: RM" + String.format("%.2f", car.carDetails.sellingPrice), 
                300, 250, 
                500, 70, 
                new Font("Arial", Font.BOLD, 17), 
                i.isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE
            ),
            panel
        );
    }

    /*//////////////////////////////////////////////////////////////
                             Add to JPanel
    //////////////////////////////////////////////////////////////*/    

    /**
     * add any <code>comp</code> into <code>panel</code>, usually for "car menus"
     * 
     * @param comp Component to be added into the JPanel "Box" for "car menus"
     * @param panel JPanel for "box", usually for "car menus"
     * 
     */
    void _addComp(JComponent comp, JPanel panel) { 
        comp.setVisible(true); 
        i.switchThemeComp.CustLabel.add(comp);
        panel.add(comp); 
    }

    /**
     * add any <code>comp</code> to extended JPanel 
     * 
     * @param comp Component to be added into the JPanel
     * 
     */
    void _addComp(JComponent comp) {
        comp.setVisible(true);
        add(comp);
        i.switchThemeComp.CustLabel.add(comp);
    }

    void _addJTextField(JTextField field) {
        field.setVisible(true);
        add(field);
        i.switchThemeComp.searchBar.add(field);
    } 

    /*//////////////////////////////////////////////////////////////
                           Filter Search Cars
    //////////////////////////////////////////////////////////////*/

    void filterPanels(String query) {
        query = query.toLowerCase(Locale.ROOT).trim();

        int visibleCount = 0;

        for (Component comp : getComponents()) {
            if (comp instanceof JPanel && comp != searchBar) {
                JPanel panel = (JPanel) comp;
                stockDetails.transactDetails car = (stockDetails.transactDetails) panel.getClientProperty("car");

                if (car != null) {
                    StringBuilder fullText = new StringBuilder();

                    fullText.append(car.carDetails.carName).append(" ");
                    fullText.append(car.carId).append(" ");
                    fullText.append("Horse Power: ").append(car.carDetails.carHorsePower).append(" ");
                    fullText.append("Length: ").append(car.carDetails.carLength).append(" ");
                    fullText.append("Selling Price: RM").append(String.format("%.2f", car.carDetails.sellingPrice));

                    boolean matches = fullText
                                        .toString()
                                        .toLowerCase(Locale.ROOT)
                                        .contains(query);

                    panel.setVisible(matches);

                    if (matches) {
                        panel.setLocation(
                            (visibleCount % 2 == 0) ? 20 : 660,
                            (400 * (visibleCount / 2)) + 280 // @audit datatype int, therefore no decimals
                        );
                        visibleCount++;
                    }
                }
            }
        }

        revalidate();
        repaint();
    }

    void _addTops() {
        // logo
        JButton logoButton = createComp.createJButton(
            COMPANY_LOGO, 
            50, 
            2, 
            110, 
            100
        );
        logoButton.addActionListener(
            _ -> {
                panels.stream().forEach(i -> PanelHelper.clear(i));
                PanelHelper.clear(this);
                W._loadFrontPage();
            }
        );
        _addComp(logoButton);

        // query statement
        helpStoreComp.addJLabel(
            i, this,
            "Visit our showroom at:",
            50, 120,
            50, 90,
            175, 30,
            new Font("Calibri", Font.BOLD, 15),
            i.isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE
        );

        // address
        helpStoreComp.addJTextArea(
            i, this,
            """
            Lot 45-2, Jalan Bukit Pantai,
            59100 Bangsar,
            Kuala Lumpur, WP Malaysia       
            """, 
            15, 150, 
            15, 130, 
            230, 100, 
            new Font("Calibri", Font.BOLD, 15), 
            new roundedBorder(
                20, 
                i.isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE, 
                imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)
            ),
            i.isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE
        );

        // pfp
        helpStoreComp.addJButton(
            i, this, 
            imageSystem._scaleImage(
                i.isLogin.currentProfile.pfp, 
                50, 
                50
            ), 
            1150, 70, 
            1150, 20, 
            50, 50
        ).addActionListener(
            _ -> {
                helpStoreComp._startDropDown(
                    i, 
                    () -> { i.checkProfile = new checkProfile(i, W, null); }, 
                    () -> i.checkProfile, 
                    1000, 500
                );
            }
        );

        // verification status
        helpStoreComp.addJLabel(
            i, this, 
            i.isLogin.currentProfile.isVerified ? "Verified" : "Unverified", 
            1140, 
            100, 
            1140, 
            70, 
            80, 50, 
            new Font("SansSerif", Font.BOLD, 15), 
            i.isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLACK
        );

        _helpAddThemeIcon(1070, 20);

        // search bar
        searchBar = helpStoreComp.addJTextField(
            i, this, 
            500, 200, 
            500, 150,
            300, 40,
            new Font("Arial", Font.BOLD, 17),
            new roundedBorder(
                10, 
                i.isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE, 
                imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)
            ),
            i.isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLACK
        );
        helpStoreComp.addJLabel(
            i, this,
            "Search Available Car",
            320, 200,
            320, 150,
            300, 40,
            new Font("Arial", Font.BOLD, 17),
            i.isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE
        );        

    }

    

}
