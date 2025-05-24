package Helper.Comp;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Components.Window;
import Components.initializer;
import Helper.Comp.createComp.createJTable;

/**
 * 
 * @deprecated
 * 
 * @author yappy-yum
 * 
 */
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

        int Y_OFFSET = 390, Y_GAP = 120;

        public createMultiTable(
            initializer i, Window w, JPanel panel,
            HashMap<String, ImageIcon> carLogo,
            int X, 
            int Y,
            String[] columnTitles,
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
            if (carLogo.isEmpty() || carLogo == null) {
                labelForNullStock = createComp.createJLabel(
                    "No Inventory", 
                    X + 20, Y - 100, 
                    200, 30, 
                    font, textColor
                );
                
                return;
            }

            int size = carLogo.size();
            for (int l = 0; l < size; l++) {
                createJTable table = new createJTable(
                    i, 
                    w, 
                    columnTitles, 
                    X, Y + Y_OFFSET, 
                    fixedWidth, 
                    fixedHeight,
                    GridColor, textColor, 
                    titleFillColor, cellFillColor, 
                    font, 
                    columnToFetchDataForProfileView,
                    intProfileSetUp
                );

                tables.add(table);

                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(table.tableModel);
                table.table.setRowSorter(sorter);
                sorters.add(sorter);
            
            }
        }

    }

}
