package Helper.Comp;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Helper.Comp.createComp.createJTable;

public class createDynamicTable {
    
    /**
     * This helper class will generate multiple tables dynamically
     * based on the number of car logos stored in {@link #stockInventory.carLogo}
     * 
     */
    public static class createMultiTable {

        public List<createJTable> tables = new ArrayList<>();
        public List<TableRowSorter<DefaultTableModel>> sorters = new ArrayList<>();
        public JLabel labelForNullStock;

        public List<JLabel> logos = new ArrayList<>();
        public List<JButton> texts = new ArrayList<>();

        int Y_OFFSET = 0, Y_GAP = 20;

        public createMultiTable(
            HashMap<String, ImageIcon> carLogo,
            int X, int Y,
            String[] columnTitles,
            int fixedWidth,
            Color GridColor, Color textColor,
            Color titleFillColor, Color cellFillColor,
            Font font,
            Consumer<String> profile
        ) {
            if (carLogo.isEmpty() || carLogo == null) {
                labelForNullStock = createComp.createJLabel(
                    "No Inventory", 
                    X + 20, Y - 100, 
                    200, 30, 
                    font, textColor
                );
                
                return;
            }

            for (Map.Entry<String, ImageIcon> entry : carLogo.entrySet()) {
                String logoName = entry.getKey();
                ImageIcon logo = entry.getValue();

                String text = logoName;

                createJTable table = new createJTable(
                    columnTitles, 
                    X, Y + Y_OFFSET, 
                    fixedWidth, 
                    GridColor, textColor, 
                    titleFillColor, cellFillColor, 
                    font, 
                    profile, 
                    logo, text
                );
                // table.logo
                texts.add(table.text);
                logos.add(table.logo);

                tables.add(table);

                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(table.tableModel);
                table.table.setRowSorter(sorter);
                sorters.add(sorter);
                
                Y_OFFSET += table.table.getHeight() + Y_GAP;
            }
        }

        public void addRow(int tableIndex, Object[] rowData) {
            tables.get(tableIndex).addRow(rowData);
            JTable table = tables.get(tableIndex).table;

            int fixedWidth = table.getWidth();
            int newHeight = table.getPreferredSize().height;
            table.setSize(fixedWidth, newHeight);
        }

    }

}
