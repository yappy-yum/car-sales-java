package Helper.Comp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import Helper.Config.roundedBorder;
import Helper.Config.tableRenderConfig;
import Helper.fileSystem.imageSystem;

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

        public JLabel logo;
        public JButton text;

        public createJTable(
            String[] columnTitles, 
            int X, int Y,
            int fixedWidth, 
            Color GridColor, Color textColor, 
            Color titleFillColor, Color cellFillColor,
            Font font, 
            Consumer<String> profile,
            ImageIcon logo, String text
        ) {
            // text and logo
            if (logo != null && text != null) {
                System.out.println("executed");
                this.logo = createJLabel(logo, X, Y - 170, 100, 300);
                this.text = createJButton(text, X + 100, Y - 60, 170, 50, new roundedBorder(10, Color.BLACK, imageSystem._reduceColorTransparency(Color.GRAY, 0.3f)), textColor, font);
            }

            tableModel = new DefaultTableModel(columnTitles, 0);

            // basic JTable config
            table = new JTable(tableModel);
            table.setFillsViewportHeight(true);
            table.setOpaque(false);
            table.setForeground(textColor);
            table.setGridColor(GridColor);
            table.setFont(font);
            table.setEnabled(false);
            table.setRowHeight(30);

            
            // Force header height to match row height (e.g. 30)
            JTableHeader header = new JTableHeader(table.getColumnModel()) {
                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(fixedWidth, 30);
                }
            };
            table.setTableHeader(header);

            header.setOpaque(false);
            header.setBackground(titleFillColor);
            header.setEnabled(false);
            header.setSize(fixedWidth, header.getPreferredSize().height);
            header.setLocation(X, Y);
            header.setFont(font);   
            header.setPreferredSize(new Dimension(fixedWidth, 30));

            table.setLocation(X, Y + header.getHeight());

            // For height, use preferred height based on rows
            table.setSize(fixedWidth, table.getPreferredSize().height);

            // Center header text
            DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
            headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            // Data cell background
            table.setDefaultRenderer(
                Object.class, 
                tableRenderConfig.createCenterAlignedRenderer(textColor, cellFillColor)
            );

            // check profiles
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent i) { 
                    int row = table.rowAtPoint(i.getPoint());

                    if (row >= 0) {
                        Object value = table.getValueAt(row, 0);
                        profile.accept(value.toString());
                    }
                }
            });


        }

        public void addRow(Object[] rowData) {
            tableModel.addRow(rowData);
            int fixedWidth = table.getWidth();
            int newHeight = table.getPreferredSize().height;
            table.setSize(fixedWidth, newHeight);
        }
    }



}
