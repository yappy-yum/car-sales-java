package Helper.Comp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import Components.Window;
import Components.initializer;
import Details.checkCar;
import Details.checkProfile;
import Helper.Config.roundedBorder;
import Helper.Config.tableRenderConfig;
import SecondPage.password;
import SecondPage.EmployeePage.unverifiedDB.VerifyEmployee;

public class createComp {

    /*//////////////////////////////////////////////////////////////
                               JTextArea
    //////////////////////////////////////////////////////////////*/    
    
    /**
     * create long texts with JTextArea
     * 
     * @param _text long texts to be created
     * @param X starting from X-coordinate
     * @param Y starting from Y-coordinate
     * @param width width length of the JTextArea
     * @param height height length of the JTextArea
     * @param font text font
     * @param border JTextArea box border
     * @param textColor text color
     * @return created JTextArea
     * 
     */
    public static JTextArea createJTextArea(
        String _text, 
        int X, int Y,
        int width, int height,
        Font font, Border border,
        Color textColor
    ) {
        JTextArea text = new JTextArea(_text);

        text.setBounds(X, Y, width, height);
        text.setOpaque(false);
        text.setEditable(false);
        text.setForeground(textColor);
        text.setFont(font);
        text.setBorder(border);
        text.setFocusable(false);
        text.setVisible(false);

        return text;
    }

    public static JTextField createJTextField(
        int X, int Y,
        int width, int height,
        Font font, Border border,
        Color textColor
    ) {
        JTextField text = new JTextField();

        text.setBounds(X, Y, width, height);
        text.setOpaque(false);
        text.setEditable(true);
        text.setForeground(textColor);
        text.setFont(font);
        text.setBorder(border);
        text.setFocusable(true);
        text.setVisible(false);

        return text;
    }

    public static JPasswordField createJPasswordField(
        int X, int Y,
        int width, int height,
        char echo, Color textColor,
        Font textFont, Border border
    ) {
        JPasswordField text = new JPasswordField();

        text.setBounds(X, Y, width, height);
        text.setEchoChar(echo);
        text.setForeground(textColor);
        text.setOpaque(false);
        text.setFocusable(true);
        text.setFont(textFont);
        text.setBorder(border);
        text.setVisible(false);

        return text;
    }

    /**
     * create a clickable multiple choice button: hardcoded ⚪ and ⚫
     * 
     * @apiNote dont forget to create ButtonGroup
     * 
     * @param font type of font
     * @param _command recorded action command from clicked toggle button
     * @return created JToggleButton
     * 
     */
    public static JToggleButton createJToggleButton(Font font, String _command) {
        JToggleButton dot = new JToggleButton();

        dot.setText("⚪");
        dot.setFont(font);
        dot.setFocusPainted(false);
        dot.setOpaque(false);
        dot.setContentAreaFilled(false);
        dot.setBorder(null);
        dot.setActionCommand(_command);
        dot.setVisible(false);

        return dot;
    }

    /*//////////////////////////////////////////////////////////////
                                JButton
    //////////////////////////////////////////////////////////////*/    

    /**
     * create a clickable button containing short text(s)
     * 
     * @param _text short text(s) inside the button
     * @param X starting from X-coordinate
     * @param Y starting from Y-coordinate
     * @param width width length of the button
     * @param height height length of the button
     * @param border button box border
     * @param textColor text color
     * @return created JButton
     * 
     */
    public static JButton createJButton(
        String _text, 
        int X, int Y,
        int width, int height,
        Border border, 
        Color textColor, Font font
    ) {
        JButton button = new JButton(_text);

        button.setBounds(X, Y, width, height);
        button.setBorder(border);
        button.setForeground(textColor);
        button.setFocusable(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setFont(font);

        return button;
    }

    /**
     * create a clickable button containing ImageIcon
     * 
     * @param icon ImageIcon inside the button
     * @param X starting from X-coordinate
     * @param Y starting from Y-coordinate
     * @param width width length of the button
     * @param height height length of the button
     * @return created JButton
     * 
     */
    public static JButton createJButton(
        ImageIcon icon, 
        int X, int Y,
        int width, int height
    ) {
        JButton button = new JButton(icon);

        button.setBounds(X, Y, width, height);
        button.setFocusable(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorder(null);
        button.setBackground(null);

        return button;
    }

    /*//////////////////////////////////////////////////////////////
                                 JLabel
    //////////////////////////////////////////////////////////////*/    

    /**
     * create a short label text
     * 
     * @param _text short text inside the label
     * @param X starting from X-coordinate
     * @param Y starting from Y-coordinate
     * @param width width length of the label
     * @param height height length of the label
     * @param font text font
     * @param border label box border
     * @param textColor text color
     * @return created JLabel
     * 
     */
    public static JLabel createJLabel(
        String _text, 
        int X, int Y,
        int width, int height,
        Font font, Color textColor
    ) {
        JLabel label = new JLabel(_text);

        label.setBounds(X, Y, width, height);
        label.setOpaque(false);
        label.setForeground(textColor);
        label.setFont(font);
        label.setFocusable(false);
        label.setVisible(false);

        return label;
    }

    /**
     * create a label image
     * 
     * @param icon ImageIcon inside the label
     * @param X starting from X-coordinate
     * @param Y starting from Y-coordinate
     * @param width width length of the label
     * @param height height length of the label
     * @return created JLabel
     * 
     */
    public static JLabel createJLabel(
        ImageIcon icon, 
        int X, int Y,
        int width, int height
    ) {
        JLabel label = new JLabel(icon);

        label.setBounds(X, Y, width, height);
        label.setOpaque(false);
        label.setFocusable(false);

        return label;
    }

    /*//////////////////////////////////////////////////////////////
                                 JPanel
    //////////////////////////////////////////////////////////////*/    

    /**
     * create a JPanel (mostly to be used as a box)
     * 
     * @param x starting from X-coordinate
     * @param y starting from Y-coordinate
     * @param panelWidth width length of the JPanel
     * @param panelHeight height length of the JPanel
     * @param border JPanel box border
     * @return created JPanel
     * 
     */
    public static JPanel createJPanel(
        int x, int y, 
        int panelWidth, int panelHeight,
        Border border
    ) {
        JPanel panel = new JPanel();

        panel.setLayout(null);
        panel.setBorder(border);
        panel.setLocation(x, y);
        panel.setOpaque(false);
        panel.setSize(panelWidth, panelHeight);

        return panel;
    }

    /*//////////////////////////////////////////////////////////////
                                CV text
    //////////////////////////////////////////////////////////////*/
    
    /**
     * create CV text area
     * 
     * <p>
     * 
     * everything is hard coded
     * 
     * 
     * @return created CV text area
     * 
     */
    public static class CVFill {
        public JTextPane textPane = new JTextPane();
        public JScrollPane scrollPane = new JScrollPane(textPane);

        public CVFill() {
            textPane.setOpaque(false);
            textPane.setBorder(new roundedBorder(10, Color.BLACK, null));
            textPane.setEditable(true);
            textPane.setFont(new Font("Arial", Font.PLAIN, 15));
            textPane.setPreferredSize(new Dimension(400, 110));
            textPane.setEditorKit(new wordWrap());

            scrollPane.setBounds(580, 300, 400, 110);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);
            scrollPane.setBorder(null);
            scrollPane.setFocusable(false);
        }

    }   
    
    public static boolean isStyledDocumentEmpty(StyledDocument styledDocument) {
        try {
            return styledDocument
                        .getText(0, styledDocument.getLength())
                        .trim()
                        .isEmpty();
        } catch (BadLocationException e) {
            e.printStackTrace();
            return true;
        }
    }    

    /*//////////////////////////////////////////////////////////////
                                 JTable
    //////////////////////////////////////////////////////////////*/
    
    public static class createJTable {

        public JTable table;
        public DefaultTableModel tableModel;

        /**
         * 
         * 
         * @param i
         * @param w
         * @param columnTitles
         * @param X
         * @param Y
         * @param fixedWidth
         * @param fixedHeight
         * @param GridColor
         * @param textColor
         * @param titleFillColor
         * @param cellFillColor
         * @param font
         * @param columnToFetchDataForProfileView
         * @param intProfileSetUp 0 == user profile (unverified); 1 == user profile (verified); 2 == inventory; 3 == verify customer; 4 == job
         * 
         */
        public createJTable(
            initializer i,
            Window w,
            String[] columnTitles,
            int X, int Y,
            int fixedWidth,
            int fixedHeight,
            Color GridColor, 
            Color textColor,
            Color titleFillColor, 
            Color cellFillColor,
            Font font,
            int columnToFetchDataForProfileView,
            int intProfileSetUp
        ) {
            tableModel = new DefaultTableModel(columnTitles, 0);

            table = new JTable(tableModel);
            table.setFillsViewportHeight(true);
            table.setOpaque(false);
            table.setForeground(textColor);
            table.setGridColor(GridColor);
            table.setFont(font);
            table.setEnabled(true);
            table.setRowSelectionAllowed(false);
            table.setColumnSelectionAllowed(false);
            table.setCellSelectionEnabled(false);
            table.setFocusable(false);
            table.setSelectionBackground(cellFillColor);
            table.setSelectionForeground(textColor);

            if (fixedHeight > 0) table.setRowHeight(fixedHeight);
            else table.setRowHeight(50);

            JTableHeader header = new JTableHeader(table.getColumnModel()) {
                @Override
                public Dimension getPreferredSize() { return new Dimension(fixedWidth, 30); }
            };
            table.setTableHeader(header);
            header.setOpaque(false);
            header.setBackground(titleFillColor);
            header.setEnabled(false);
            header.setSize(fixedWidth, 30);
            header.setLocation(X, Y);
            header.setFont(font);
            header.setPreferredSize(new Dimension(fixedWidth, 30));
            table.setLocation(X, Y + 30);

            if (fixedHeight > 0) {
                int rowCount = tableModel.getRowCount();
                int totalHeight = 30 + (rowCount * fixedHeight);
                table.setSize(fixedWidth, totalHeight);
            } else {
                table.setSize(fixedWidth, table.getPreferredSize().height);
            }

            DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
            headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            if (table.getColumnModel().getColumnCount() > 0) {
                table
                    .getColumnModel()
                    .getColumn(1)
                    .setCellRenderer(new DefaultTableCellRenderer() {
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
                                label.setBackground(cellFillColor);
                                return label;
                            }

                            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                            c.setForeground(textColor);
                            c.setBackground(cellFillColor);

                            ((JComponent) c).setOpaque(true);
                            ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);
                            
                            return c;
                        }
                    });
            }


            // 🎨 All other cells
            table.setDefaultRenderer(
                Object.class,
                tableRenderConfig.createCenterAlignedRenderer(textColor, cellFillColor)
            );       

            // 🖱️ Mouse callback
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent E) {
                    int viewRow = table.rowAtPoint(E.getPoint());
                    int viewCol = table.columnAtPoint(E.getPoint());

                    if (viewRow < 0 || viewCol < 0) return;

                    String columnName = table.getColumnName(viewCol);
                    if ("Approve".equals(columnName) || "Reject".equals(columnName)) return;

                    int modelRow;
                    try {
                        modelRow = table.convertRowIndexToModel(viewRow);
                    } catch (IndexOutOfBoundsException ex) {
                        return; // 🔐 Row was deleted mid-click
                    }

                    String data = getFirstColumnValueAt(columnToFetchDataForProfileView, modelRow);

                    if (data != null) {
                        if (intProfileSetUp == 1) {
                            helpStoreComp._startDropDown(
                                i,
                                () -> { i.checkProfile = new checkProfile(i, w, data); },
                                () -> i.checkProfile,
                                1000, 500
                            );
                        }

                        if (intProfileSetUp == 2) {
                            helpStoreComp._startDropDown(
                                i, 
                                () -> { i.checkCar = new checkCar(i, w, data); }, 
                                () -> i.checkCar, 
                                1000, 500
                            );
                        }

                        if (intProfileSetUp == 3) {
                            helpStoreComp._startDropDown(
                                i,
                                () -> { i.password = new password(i, w, data); },
                                () -> i.password,
                                500, 150
                            );
                        }

                        if (intProfileSetUp == 4) {
                            helpStoreComp._startDropDown(
                                i,
                                () -> { i.VerifyEmployee = new VerifyEmployee(i, w, data); },
                                () -> i.VerifyEmployee,
                                1000, 500
                            );
                        }                        
                    }
                }

            });

            i.switchThemeComp.JTables.add(table);

        }

        public void addRow(Object[] rowData) {
            tableModel.addRow(rowData);
            int fixedWidth = table.getWidth();
            int rowHeight = table.getRowHeight();
            int totalHeight = 30 + (tableModel.getRowCount() * rowHeight);
            table.setSize(fixedWidth, totalHeight);
        }

        public String getFirstColumnValueAt(int column, int row) {
            if (row >= 0 && row < tableModel.getRowCount()) {
                Object value = tableModel.getValueAt(row, column);
                return value != null ? value.toString() : null;
            }
            return null;
        }

    }






}
