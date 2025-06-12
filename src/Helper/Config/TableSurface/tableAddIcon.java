package Helper.Config.TableSurface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import Components.Window;
import Components.initializer;
import Helper.Comp.createComp.createJTable;
import Helper.fileSystem.imageSystem;

/**
 * to add tick and cross icons into the table, allowing staff to manage users
 * verification approval 
 * 
 * @author yappy-yum
 * 
 */
public class tableAddIcon {

    public static class createProfileTable {

        public ImageIcon tick = imageSystem._scaleImage(imageSystem.TICK, 50, 50);
        public ImageIcon cross = imageSystem._scaleImage(imageSystem.CROSS, 50, 50); 
        public createJTable table;

        public createProfileTable(
            initializer i,
            Window w,
            String[] columnTitles,
            int X, 
            int Y,
            int fixedWidth, 
            int fixedHeight,
            Color gridColor, 
            Color textColor,
            Color titleFillColor, 
            Color cellFillColor,
            Font font,
            int columnToFetchDataForProfileView,
            int intProfileSetUp,
            int intCharacter
        ) {
            this.table = new createJTable(
                i, 
                w, 
                columnTitles,
                X, 
                Y, 
                fixedWidth, 
                fixedHeight,
                gridColor, 
                textColor,
                titleFillColor, 
                cellFillColor,
                font,
                columnToFetchDataForProfileView,
                intProfileSetUp
            );

            JTable table = this.table.table;

            if (hasColumn(table, "Approve")) table.getColumn("Approve").setCellRenderer(new IconButtonRenderer(tick));
            if (hasColumn(table, "Reject")) table.getColumn("Reject").setCellRenderer(new IconButtonRenderer(cross));

            if (hasColumn(table, "Approve")) table.getColumn("Approve").setCellEditor(new IconButtonEditor(i, tick, table, true, intCharacter));
            if (hasColumn(table, "Reject")) table.getColumn("Reject").setCellEditor(new IconButtonEditor(i, cross, table, false, intCharacter));
        }

        /*//////////////////////////////////////////////////////////////
                                    constructor
        //////////////////////////////////////////////////////////////*/        

        private static class IconButtonRenderer extends JButton implements TableCellRenderer {
            public IconButtonRenderer(ImageIcon icon) {
                setIcon(icon);
                setOpaque(false);
                setFocusable(false);
                setBorderPainted(false);
                setContentAreaFilled(false); 
                setHorizontalAlignment(SwingConstants.CENTER);
            }

            @Override
            public Component getTableCellRendererComponent(
                JTable table, 
                Object value, 
                boolean isSelected,
                boolean hasFocus, 
                int row, 
                int column
            ) { return this; }
        }

        /*//////////////////////////////////////////////////////////////
                              ImageIcon ActionListener
        //////////////////////////////////////////////////////////////*/        

        private static class IconButtonEditor extends AbstractCellEditor implements TableCellEditor {
            private JButton button;

            public IconButtonEditor(
                initializer i, 
                ImageIcon icon, 
                JTable table, 
                boolean isApprove, 
                int intCharacter
            ) {

                button = new JButton(icon);
                button.setOpaque(false);
                button.setFocusable(false);
                button.setBorderPainted(false);
                button.setContentAreaFilled(false);
                button.setHorizontalAlignment(SwingConstants.CENTER);

                button.addActionListener(_ -> {

                    int row = table.getEditingRow();
                    String username = table.getValueAt(row, 0).toString();

                    if (row >= 0) {

                        // customer
                        if (intCharacter == 1) {
                            if (isApprove) i.storage.setVerified(username);
                            if (!isApprove) i.storage.rejectVerification(username);
                        }

                        // salesman
                        if (intCharacter == 2) {
                            System.out.println("Salesman Approved: " + isApprove + ": " + username);
                            i.storage.setApproval(username, isApprove);
                        }

                        // manager
                        if (intCharacter == 3) {
                            System.out.println("Manager Approved: " + isApprove + ": " + username);
                            i.storage.setApproval(username, isApprove);
                        }

                        ((DefaultTableModel) table.getModel()).removeRow(row);
                        fireEditingStopped();
                    }

                });

            }

            @Override
            public Component getTableCellEditorComponent(
                JTable table, 
                Object value, 
                boolean isSelected, 
                int row, 
                int column
            ) { return button; }

            @Override
            public Object getCellEditorValue() { return null; }
        }

        /*//////////////////////////////////////////////////////////////
                                 add Checks
        //////////////////////////////////////////////////////////////*/        

        private boolean hasColumn(JTable table, String ColumnName) {
            try {
                table.getColumn(ColumnName);
                return true;
            } catch (Exception e) { return false; }
        }
        
    }
}
