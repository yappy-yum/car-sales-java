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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Components.initializer;
import Helper.Comp.createComp;
import Helper.Comp.createDynamicTable;
import Helper.Comp.createComp.createJTable;
import Helper.Config.SearchDynTable;
import Helper.login.Profile;
import Inventory.stockDetails;

public abstract class dynamicTable {

    initializer i;
    JPanel panel;

    public JLabel label;
    public JTextField searchBar;
    public JLabel searchIcon;

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
    
    public dynamicTable(initializer i, JPanel panel, String title, Profile.Department department, String[] columnTitles) {
        this.i = i;
        this.panel = panel;
        this.title = title;
        this.department = department;
        this.columnTitles = columnTitles;

        _initTable();
        _config();
    }

    /*//////////////////////////////////////////////////////////////
                             create JTable
    //////////////////////////////////////////////////////////////*/
    
    void _initTable() {
        createDynamicTable.createMultiTable multiTable = new createDynamicTable.createMultiTable(
            i.stockInventory.carLogo, 
            20, 
            390, 
            columnTitles, 
            1200, 
            Color.CYAN, 
            Color.WHITE, 
            Color.PINK, 
            new Color(0, 0, 0, 0), 
            new Font("Arial", Font.BOLD, 15), 
            _ -> {}
        );
        this.logos = multiTable.logos;
        this.texts = multiTable.texts;

        _loadData(multiTable);

        this.tables = multiTable.tables;
        this.sorters = multiTable.sorters;
        this.label = multiTable.labelForNullStock != null ? 
                     multiTable.labelForNullStock : 
                     createComp.createJLabel(
                        title, 
                        50, 200, 
                        300, 100,
                        new Font("Arial", Font.BOLD, 20),
                        Color.WHITE
                     );

        SearchDynTable.createSearchBar searchBar = new SearchDynTable.createSearchBar(
            multiTable, 
            250, 230,
            250, 43, 
            Color.WHITE
        );
        this.searchBar = searchBar.searchField;
        this.searchIcon = searchBar.searchIcon;
    }

    void _loadData(createDynamicTable.createMultiTable multiTable) {
        int index = 0;

        for (Map.Entry<String, ImageIcon> entry : i.stockInventory.carLogo.entrySet()) {
            String logoName = entry.getKey();

            for (stockDetails.transactDetails car : i.stockInventory.carDetails) {

                if (car.carDetails.logoName.equals(logoName)) {
                    
                    multiTable.addRow(
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

            index ++ ;
        }
    }

    void _config() {
        // panel
        if (label != null) panel.add(label);
        if (searchBar != null) panel.add(searchBar);
        if (searchIcon != null) panel.add(searchIcon);
        logos.forEach(t -> { if (t != null) panel.add(t); });
        texts.forEach(t -> { if (t != null) panel.add(t); });
        tables.forEach(t -> { if (t.table != null) panel.add(t.table.getTableHeader()); });
        tables.forEach(t -> { if (t.table != null) panel.add(t.table); });

        // dummy
        if (label != null) i.switchThemeComp.dummy.add(label);
        if (searchBar != null) i.switchThemeComp.dummy.add(searchBar);
        if (searchIcon != null) i.switchThemeComp.dummy.add(searchIcon);
        logos.forEach(t -> { if (t != null) i.switchThemeComp.dummy.add(t); });
        texts.forEach(t -> { if (t != null) i.switchThemeComp.dummy.add(t); });
        tables.forEach(t -> { if (t.table != null) i.switchThemeComp.dummy.add(t.table.getTableHeader()); });
        tables.forEach(t -> { if (t.table != null) i.switchThemeComp.dummy.add(t.table); });

        // hide them
        if (label != null) label.setVisible(false);
        if (searchBar != null) searchBar.setVisible(false);
        if (searchIcon != null) searchIcon.setVisible(false);
        logos.forEach(t -> { if (t != null) t.setVisible(false); });
        texts.forEach(t -> { if (t != null) t.setVisible(false); });
        tables.forEach(t -> { if (t.table != null) t.table.getTableHeader().setVisible(false); });
        tables.forEach(t -> { if (t.table != null) t.table.setVisible(false); });

    }
    
}
