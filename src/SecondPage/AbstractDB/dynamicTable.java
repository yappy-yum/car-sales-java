package SecondPage.AbstractDB;

import java.awt.Color;
import java.awt.Font;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Components.Window;
import Components.initializer;
import Helper.Comp.createDynamicTable;
import Helper.Comp.createComp.createJTable;
import Helper.login.Profile;
import Inventory.stockDetails;

/**
 * @deprecated
 * 
 * @author yappy-yum
 * 
 */
@Deprecated
public abstract class dynamicTable {

    initializer i;
    public JPanel panel;
    public Window w;

    public JLabel label;
    public JTextField searchBar;
    public JLabel searchIcon;
    public int columnToFetchDataForProfileView;
    public int intProfileSetUp;

    public List<createJTable> tables = new ArrayList<>();
    public List<TableRowSorter<DefaultTableModel>> sorters = new ArrayList<>();

    public String title;
    public Profile.Department department;
    public String[] columnTitles;

    List<JLabel> logos = new ArrayList<>();
    List<JButton> texts = new ArrayList<>();

    /*//////////////////////////////////////////////////////////////
                              constructor
    //////////////////////////////////////////////////////////////*/
    
    public dynamicTable(initializer i, Window w, JPanel panel, String title, Profile.Department department, String[] columnTitles, int columnToFetchDataForProfileView, int intProfileSetUp) {
        this.i = i;
        this.w = w;
        this.panel = panel;
        this.title = title;
        this.department = department;
        this.columnTitles = columnTitles;
        this.columnToFetchDataForProfileView = columnToFetchDataForProfileView;
        this.intProfileSetUp = intProfileSetUp;

        _initTable();
        _config();
    }

    /*//////////////////////////////////////////////////////////////
                             create JTable
    //////////////////////////////////////////////////////////////*/
    
    createDynamicTable.createMultiTable _initTable() {
        createDynamicTable.createMultiTable multiTable = new createDynamicTable.createMultiTable(
            i, 
            w, 
            panel,
            i.stockInventory.carLogo, 
            20, 
            200, 
            columnTitles, 
            1200, 
            60,
            Color.CYAN, 
            Color.WHITE, Color.PINK, 
            new Color(0, 0, 0, 0), 
            new Font("Arial", Font.BOLD, 15), 
            columnToFetchDataForProfileView,
            intProfileSetUp
        );

        this.tables = multiTable.tables;
        this.logos = multiTable.logos;
        this.texts = multiTable.texts;

        // Load data after tables are created
        _loadData(multiTable);

        return multiTable;
    }

    /*//////////////////////////////////////////////////////////////
                               loads data
    //////////////////////////////////////////////////////////////*/    

    void _loadData(createDynamicTable.createMultiTable multiTable) {
        int index = 0;
        for (Map.Entry<String, ImageIcon> entry : i.stockInventory.carLogo.entrySet()) {
            String logoName = entry.getKey();

            for (stockDetails.transactDetails car : i.stockInventory.carDetails) {
                if (car.carDetails.logoName.equals(logoName)) {
                    
                    addRow(
                        index, 
                        new Object[] {
                            car.carDetails.carName,
                            car.carDetails.buyingPrice,
                            car.carDetails.sellingPrice,
                            car.carDetails.BoughtFrom,
                            car.carDetails.SellTo
                        }
                    );
                    
                }
            }

            index++;
        }
    }

    /*//////////////////////////////////////////////////////////////
                       reposition table location
    //////////////////////////////////////////////////////////////*/    

    public void repositionTables() {
        int Y_OFFSET = 250; 
        int ROW_HEIGHT = 30;
        int HEADER_HEIGHT = 30;
        int TABLE_GAP = 50;

        for (int i = 0; i < tables.size(); i++) {
            createJTable table = tables.get(i);
            JTable jTable = table.table;
            
            int totalHeight = (jTable.getRowCount() * ROW_HEIGHT) + HEADER_HEIGHT;
            
            jTable.setSize(1200, totalHeight);
            jTable.setLocation(20, Y_OFFSET);
            
            if (jTable.getTableHeader() != null) {
                jTable.getTableHeader().setLocation(20, Y_OFFSET);
                jTable.getTableHeader().setSize(1200, HEADER_HEIGHT);
            }
            
            Y_OFFSET += totalHeight + TABLE_GAP;
        }
    }

    /*//////////////////////////////////////////////////////////////
                             add to JPanel
    //////////////////////////////////////////////////////////////*/    

    void _config() {
        // panel
        if (label != null) panel.add(label);
        if (searchBar != null) panel.add(searchBar);
        if (searchIcon != null) panel.add(searchIcon);
        logos.forEach(t -> { if (t != null) panel.add(t); });
        texts.forEach(t -> { if (t != null) panel.add(t); });
        tables.forEach(t -> { if (t.table != null) panel.add(t.table.getTableHeader()); });
        tables.forEach(t -> { if (t.table != null) panel.add(t.table); });

        // hide them
        if (label != null) label.setVisible(false);
        if (searchBar != null) searchBar.setVisible(false);
        if (searchIcon != null) searchIcon.setVisible(false);
        logos.forEach(t -> { if (t != null) t.setVisible(false); });
        texts.forEach(t -> { if (t != null) t.setVisible(false); });
        tables.forEach(t -> { if (t.table != null) t.table.getTableHeader().setVisible(false); });
        tables.forEach(t -> { if (t.table != null) t.table.setVisible(false); });

    }

    /*//////////////////////////////////////////////////////////////
                              insert data
    //////////////////////////////////////////////////////////////*/    

    void addRow(int tableIndex, Object[] rowData) {
        tables.get(tableIndex).addRow(rowData);
        JTable table = tables.get(tableIndex).table;

        int fixedWidth = table.getWidth();
        int newHeight = table.getPreferredSize().height;
        table.setSize(fixedWidth, newHeight);
    }    

}
