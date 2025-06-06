package Components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;

import Helper.Config.roundedBorder;
import Helper.Config.tableRenderConfig;
import Helper.fileSystem.imageSystem;
import frontPage.isDarkTheme;

public class SwitchThemeComp {

    isDarkTheme isDarkTheme;

    /*//////////////////////////////////////////////////////////////
                               First Page
    //////////////////////////////////////////////////////////////*/  

    public ArrayList<JTextArea> texts = new ArrayList<>();

    public ArrayList<JButton> TButtons = new ArrayList<>();
    public ArrayList<JButton> IButtons = new ArrayList<>();

    public ArrayList<JLabel> TLabels = new ArrayList<>();
    public ArrayList<JLabel> ILabels = new ArrayList<>();

    public ArrayList<JPanel> JPanels = new ArrayList<>();

    /*//////////////////////////////////////////////////////////////
                         Second Page (Database)
    //////////////////////////////////////////////////////////////*/    

    public ArrayList<JLabel> SecondPageLabels = new ArrayList<>();
    public ArrayList<JButton> SecondPageButtons = new ArrayList<>();
    public ArrayList<JTextField> SecondPageJTextFields = new ArrayList<>();
    public ArrayList<JTable> JTables = new ArrayList<>();

    /*//////////////////////////////////////////////////////////////
                      Second Page (Customer Page)
    //////////////////////////////////////////////////////////////*/    

    public ArrayList<JComponent> CustLabel = new ArrayList<>();
    public ArrayList<JTextField> searchBar = new ArrayList<>();

    // dummy storage for others components
    public ArrayList<Object> dummy = new ArrayList<>();

    /*//////////////////////////////////////////////////////////////
                              constructor
    //////////////////////////////////////////////////////////////*/
     
    SwitchThemeComp(initializer i) { this.isDarkTheme = i.isDarkTheme; }    
    
    /*//////////////////////////////////////////////////////////////
                  switch components according to theme
    //////////////////////////////////////////////////////////////*/    
    
    public void switchTheme() {
        if (texts.size() > 0) _switchTexts();

        if (TButtons.size() > 0) _switchTButton();
        if (IButtons.size() > 0) _switchIButton();

        if (TLabels.size() > 0) _switchTLabel();
        if (ILabels.size() > 0) _switchILabel();

        if (JPanels.size() > 0) _switchJPanels();

        if (SecondPageLabels.size() > 0) _switchSecondPageLabels();
        if (SecondPageButtons.size() > 0) _switchSecondPageButtons();
        if (SecondPageJTextFields.size() > 0) _switchSecondPageJTextFields();
        if (JTables.size() > 0) _switchJTables();

        if (CustLabel.size() > 0) _switchCustLabel();
        if (searchBar.size() > 0) _switchSearchBar();
    }  

    /*//////////////////////////////////////////////////////////////
                               JTextArea
    //////////////////////////////////////////////////////////////*/    
    
    /**
     * 
     * adjust {@link #texts}
     */
    protected void _switchTexts() {
        for (JTextArea text : texts) {
            // black & white
            if (text.getForeground() == Color.BLACK || text.getForeground() == Color.WHITE) {
                text.setForeground(isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLACK);
            }
            
            // blue & pink
            if (text.getForeground() == Color.BLUE || text.getForeground() == Color.PINK) {
                text.setForeground(isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE);
                if (text.getBorder() != null) {
                    text.setBorder(
                        new roundedBorder(
                        20,    
                        isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE,
                        imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)
                        )
                    );
                }
            }
        }        
    }

    /*//////////////////////////////////////////////////////////////
                              Text Button
    //////////////////////////////////////////////////////////////*/    

    /**
     * 
     * adjust {@link #TButtons}
     */
    protected void _switchTButton() {
        for (JButton button : TButtons) {
            // black & white
            if (button.getForeground() == Color.BLACK || button.getForeground() == Color.WHITE) {
                button.setForeground(
                    (isDarkTheme.isDarkTheme) ? 
                    (Color.WHITE) : 
                    (button.getY() > 1000 && button.getY() < 4500) ? (Color.WHITE) : (Color.BLACK)
                );
                if (button.getBorder() != null) {
                    button.setBorder(
                        new roundedBorder(
                            40,    
                            (
                                (isDarkTheme.isDarkTheme) ? 
                                (Color.PINK) : 
                                (button.getY() > 1000 && button.getY() < 4500) ? (Color.PINK) : (Color.BLACK)
                            ),
                            imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)
                        )
                    );
                }
            }

            // faq + and -
            if (button.getText() == "+" || button.getText() == "-") {
                button.setForeground(isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE);
                button.setBorder(
                    new roundedBorder(
                        20, 
                        isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE, 
                        imageSystem._reduceColorTransparency(Color.GRAY, 0.2f)
                    )
                );
            }

        }        
    }

    /*//////////////////////////////////////////////////////////////
                              Image Button
    //////////////////////////////////////////////////////////////*/ 

    /**
     * 
     * adjust {@link #IButtons}
     * 
     * @notice comparison will be done on the length width but not
     * the ImageIcon itself, as the ImageIcon itself failed to be compared
     */
    protected void _switchIButton() {
        for (JButton button : IButtons) {
            // light & dark theme
            ImageIcon lightButton = imageSystem._scaleImage(imageSystem.LIGHT_BUTTON, 50, 50);
            ImageIcon darkButton = imageSystem._scaleImage(imageSystem.DARK_BUTTON, 50, 50);
            if (button.getX() == 915 || button.getX() == 1000) {
                button.setIcon(isDarkTheme.isDarkTheme ? lightButton : darkButton);
            }
        }        
    }

    /*//////////////////////////////////////////////////////////////
                               Text Label
    //////////////////////////////////////////////////////////////*/    

    /**
     * 
     * adjust {@link #TLabels}
     */
    protected void _switchTLabel() {
        for (JLabel label : TLabels) {
            // black & white
            if (label.getForeground() == Color.BLACK || label.getForeground() == Color.WHITE) {
                label.setForeground(isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLACK);
            }

            // big header
            Color DARK_GREEN = new Color(13, 15, 14);
            Color LIGHT_GREEN = new Color(59, 245, 99);
            // !! custom color on comparison is not working !!
            // if (label.getForeground() == DARK_GREEN || label.getForeground() == LIGHT_GREEN) {
            //     label.setForeground(isDarkTheme.isDarkTheme ? LIGHT_GREEN : DARK_GREEN);
            // }
            if (label.getWidth() == 600 && label.getHeight() == 100) {
                label.setForeground(isDarkTheme.isDarkTheme ? LIGHT_GREEN : DARK_GREEN);
            }

            // blue & pink
            if (label.getForeground() == Color.BLUE || label.getForeground() == Color.PINK) {
                label.setForeground(isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE);
            }
        }        
    }

    /*//////////////////////////////////////////////////////////////
                              Image Label
    //////////////////////////////////////////////////////////////*/    

    /**
     * 
     * adjust {@link #ILabels}
     * 
     * @notice comparison will be done on the length width but not
     * the ImageIcon itself, as the ImageIcon itself failed to be compared
     */
    protected void _switchILabel() {
        for (JLabel label : ILabels) {
            // highlight for big header
            ImageIcon yellowHighlight = imageSystem._reduceImageTransparency(imageSystem._scaleImage(imageSystem.YELLOW_HIGHLIGHT, 650, 250), 0.8f);
            ImageIcon greyHighlight = imageSystem._reduceImageTransparency(imageSystem._scaleImage(imageSystem.GREY_HIGHLIGHT, 650, 250), 0.5f);
            if (label.getWidth() == 650 && label.getHeight() == 97) {
                label.setIcon(isDarkTheme.isDarkTheme ? yellowHighlight : greyHighlight);
            }
        }
    }

    /*//////////////////////////////////////////////////////////////
                                 JPanel
    //////////////////////////////////////////////////////////////*/    

    /**
     * 
     * adjust {@link #JPanels}
     */
    protected void _switchJPanels() {
        for (JPanel panel : JPanels) {
            // black & white
            if (panel.getY() > 4900) {
                panel.setBorder(
                    new roundedBorder(
                        40, 
                        isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE, 
                        imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)
                    )
                );
            }
        }
    }

    /*//////////////////////////////////////////////////////////////
                                 JTable
    //////////////////////////////////////////////////////////////*/
    
    /**
     * 
     * adjust {@link #JTables}
     */
    protected void _switchJTables() {
        for (JTable table : JTables) {
            table.setGridColor(isDarkTheme.isDarkTheme ? Color.CYAN : Color.BLUE);
            table.setForeground(isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLACK);
            table.setSelectionForeground(isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLACK);
            table.setDefaultRenderer(
                Object.class, 
                tableRenderConfig.createCenterAlignedRenderer(
                    isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLACK, 
                    new Color(0, 0, 0, 0)
                )
            );
            _helpChangeTable(table);
        }
    }

    /**
     * 
     * adjust {@link #SecondPageLabels}
     */
    protected void _switchSecondPageLabels() {
        for (JLabel label : SecondPageLabels) {
            label.setForeground(isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLACK);
        }
    }

    /**
     * 
     * adjust {@link #SecondPageButtons}
     */
    protected void _switchSecondPageButtons() {
        for (JButton button : SecondPageButtons) {
            button.setForeground(isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE);
            button.setBorder(
                new roundedBorder(
                    15, 
                    isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLUE, 
                    imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)
                )
            );
        }
    }

    /**
     * 
     * adjust {@link #SecondPageJTextFields}
     */
    protected void _switchSecondPageJTextFields() {
        for (JTextField field : SecondPageJTextFields) {
            field.setBorder(
                new roundedBorder(
                    10, 
                    isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLUE, 
                    null
                )
            );
            field.setForeground(isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLACK);
        }
    }

    protected void _switchCustLabel() {
        for (JComponent label : CustLabel) {
            if (label.getBorder() != null) label.setBorder
                                                    (
                                                        new roundedBorder(
                                                            20, 
                                                            isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE, 
                                                            imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)
                                                        )
                                                    ); 

            if (label.getForeground() == Color.BLUE || label.getForeground() == Color.PINK) 
            {
                label.setForeground
                (
                    isDarkTheme.isDarkTheme ? 
                                Color.PINK : 
                                Color.BLUE
                );
            }
        }
    }

    protected void _switchSearchBar() {
        for (JTextField field : searchBar) {
            field.setForeground(
                isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLACK
            );
            field.setBorder(
                new roundedBorder(
                    20, 
                    isDarkTheme.isDarkTheme ? Color.PINK : Color.BLUE, 
                    imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)
                )
            );
        }
    }

    /*//////////////////////////////////////////////////////////////
                               clear data
    //////////////////////////////////////////////////////////////*/

    public void clearEverything() {
        Set<Container> touchedParents = new HashSet<>();

        // Batch-remove JTextAreas
        if (texts.size() > 0) for (JTextArea text : texts) safelyRemoveAndTrack(text, touchedParents);
        if (TButtons.size() > 0) for (JButton btn : TButtons) safelyRemoveAndTrack(btn, touchedParents);
        if (IButtons.size() > 0) for (JButton btn : IButtons) safelyRemoveAndTrack(btn, touchedParents);
        if (TLabels.size() > 0) for (JLabel lbl : TLabels) safelyRemoveAndTrack(lbl, touchedParents);
        if (ILabels.size() > 0) for (JLabel lbl : ILabels) safelyRemoveAndTrack(lbl, touchedParents);
        if (JPanels.size() > 0) for (JPanel panel : JPanels) safelyRemoveAndTrack(panel, touchedParents);
        if (dummy.size() > 0) for (Object dummyObj : dummy) safelyRemoveAndTrack(dummyObj, touchedParents);
        if (JTables.size() > 0) for (JTable table : JTables) safelyRemoveAndTrack(table, touchedParents);
        if (SecondPageLabels.size() > 0) for (JLabel label : SecondPageLabels) safelyRemoveAndTrack(label, touchedParents);
        if (SecondPageButtons.size() > 0) for (JButton button : SecondPageButtons) safelyRemoveAndTrack(button, touchedParents);
        if (SecondPageJTextFields.size() > 0) for (JTextField field : SecondPageJTextFields) safelyRemoveAndTrack(field, touchedParents);
        if (CustLabel.size() > 0) for (JComponent label : CustLabel) safelyRemoveAndTrack(label, touchedParents);
        if (searchBar.size() > 0) for (JTextField field : searchBar) safelyRemoveAndTrack(field, touchedParents);

        SwingUtilities.invokeLater(() -> {
            for (Container parent : touchedParents) {
                parent.removeAll();
                // parent.revalidate();
                // parent.repaint();                
            }

            // Clear lists
            texts.clear();        TButtons.clear();     IButtons.clear();     
            TLabels.clear();      ILabels.clear();      JPanels.clear();
            dummy.clear();        JTables.clear();      SecondPageLabels.clear();   
            CustLabel.clear();    searchBar.clear();
            SecondPageButtons.clear();              SecondPageJTextFields.clear(); 
        });
    }

    // Track parent for batch repaint/revalidate
    private void safelyRemoveAndTrack(Object obj, Set<Container> touchedParents) 
    {
        if 
        (
            obj instanceof Component comp && 
            comp.getParent() != null
        ) 
        {
            touchedParents.add(comp.getParent());                
        }
    }

    /*//////////////////////////////////////////////////////////////
                          Table Changes Config
    //////////////////////////////////////////////////////////////*/    

    private void _helpChangeTable(JTable table) {
        if (table.getColumnModel().getColumnCount() > 1) {
            table.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column
                ) {
                    if (value instanceof ImageIcon) {
                        JLabel label = new JLabel((ImageIcon) value);
                        label.setHorizontalAlignment(SwingConstants.CENTER);
                        label.setOpaque(true);
                        label.setBackground(new Color(0, 0, 0, 0));
                        return label;
                    }

                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    c.setForeground(isDarkTheme.isDarkTheme ? Color.WHITE : Color.BLACK);
                    c.setBackground(new Color(0, 0, 0, 0));

                    if (c instanceof JComponent) ((JComponent) c).setOpaque(true);
                    if (c instanceof JLabel) ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);

                    return c;
                }
            });

            table.repaint();
        }
    }


}