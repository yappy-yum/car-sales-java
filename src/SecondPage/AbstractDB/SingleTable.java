package SecondPage.AbstractDB;

import java.awt.Color;
import java.awt.Font;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Components.initializer;
import Helper.Comp.createComp;
import Helper.Config.SearchSingleTable;
import Helper.login.Profile;

public abstract class SingleTable {
    
    protected initializer i;
    protected JPanel panel;
 
    public JLabel label;
    public JTextField searchBar;
    public JLabel searchIcon;

    public JTable table;
    public DefaultTableModel tableModel;

    public String title;
    public Profile.Department department;
    public String[] columnTitles;

    /*//////////////////////////////////////////////////////////////
                              constructor
    //////////////////////////////////////////////////////////////*/    

    public SingleTable(initializer i, JPanel panel, String title, Profile.Department department, String[] columnTitles) {
        this.i = i;
        this.panel = panel;
        this.title = title;
        this.department = department;
        this.columnTitles = columnTitles;

        _initTable();
        _config();
    }

    /*//////////////////////////////////////////////////////////////
                             Create JTable
    //////////////////////////////////////////////////////////////*/    
 
    void _initTable() {
        createComp.createJTable _initTable = new createComp.createJTable(
            columnTitles,
            20,
            320,
            1200,
            Color.CYAN,
            Color.WHITE,
            Color.PINK,
            new Color(0, 0, 0, 0),
            new Font("Arial", Font.BOLD, 15),
            _ -> {},
            null, null
        );

        this.table = _initTable.table;
        this.tableModel = _initTable.tableModel;

        _loadData(_initTable);

        this.label = createComp.createJLabel(
            title,
            50, 200,
            300, 100,
            new Font("Arial", Font.BOLD, 20),
            Color.WHITE
        );

        SearchSingleTable.createSearchBar searchBar = new SearchSingleTable.createSearchBar(
            table, tableModel,
            250, 230,
            250, 43,
            Color.WHITE
        );
        this.searchBar = searchBar.searchField;
        this.searchIcon = searchBar.searchIcon;
    }

    /*//////////////////////////////////////////////////////////////
                               Load Datas
    //////////////////////////////////////////////////////////////*/    

    void _loadData(createComp.createJTable _initTable) {
        for (Map.Entry<String, Profile.userProfile> user : i.storage.Users.entrySet()) {
            if (user.getValue().isVerified && user.getValue().department == department) {
                _initTable.addRow(new Object[] {
                    user.getKey(),
                    user.getValue().firstName,
                    user.getValue().lastName,
                    user.getValue().gender,
                    user.getValue().age
                });
            }
        }
    }

    /*//////////////////////////////////////////////////////////////
                             Add to JPanel
    //////////////////////////////////////////////////////////////*/    

    void _config() {
        // panel
        panel.add(label);
        panel.add(searchBar);
        panel.add(searchIcon);
        panel.add(table.getTableHeader());
        panel.add(table);

        // dummy
        i.switchThemeComp.dummy.add(label);
        i.switchThemeComp.dummy.add(searchBar);
        i.switchThemeComp.dummy.add(searchIcon);
        i.switchThemeComp.dummy.add(table.getTableHeader());
        i.switchThemeComp.dummy.add(table);

        // hide them
        label.setVisible(false);
        searchBar.setVisible(false);
        searchIcon.setVisible(false);
        table.getTableHeader().setVisible(false);
        table.setVisible(false);
    }

}
