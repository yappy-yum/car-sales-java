package SecondPage.AbstractDB;

import java.awt.Color;
import java.awt.Font;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Components.Window;
import Components.initializer;
import Details.AddCar;
import Helper.Comp.createComp;
import Helper.Comp.helpStoreComp;
import Helper.Config.SearchSingleTable;
import Helper.Config.roundedBorder;
import Helper.Config.tableAddIcon;
import Helper.login.Profile;
import Inventory.stockDetails;

public abstract class singleTable {
    
    initializer i;
    Window w;
    JPanel panel;
    int rowHeight;
    int columnToFetchDataForProfileView;
    String placeHolder;

    int verifiedIntInstruct;
    int unverifiedIntInstruct;

    int verifiedIntProfileSetUp;
    public JLabel verifiedLabel;
    public JTextField verifiedSearchBar;
    public JLabel verifiedSearchIcon;
    public JTable verifiedTable;
    public DefaultTableModel verifiedTableModel;
    public String verifiedTitle;
    public String[] verifiedColumnTitles;
    public JButton verifiedButton;
    public String verifiedDBPhrase;
    
    
    int unverifiedIntProfileSetUp;
    public JTextField unverifiedSearchBar;
    public JLabel unverifiedSearchIcon;
    public JLabel unverifiedLabel;
    public JTable unverifiedTable;
    public DefaultTableModel unverifiedTableModel;
    public String unverifiedTitle;
    public String[] unverifiedColumnTitles;
    public JButton unverifiedButton;
    public String unverifiedDBPhrase;

    JButton addInventoryButton;





    /*//////////////////////////////////////////////////////////////
                              constructor
    //////////////////////////////////////////////////////////////*/    

    /**
     * 
     * @param i
     * @param w
     * @param panel
     * @param verifiedTitle
     * @param unverifiedTitle
     * @param verifiedColumnTitles
     * @param unverifiedColumnTitles
     * @param rowHeight
     * @param verifiedIntInstruct 1 == customer; 2 == salesman; 3 == manager; 4 == inventory
     * @param unverifiedIntInstruct 1 == customer; 2 == salesman; 3 == manager
     * @param placeHolder
     * @param columnToFetchDataForProfileView
     * @param verifiedIntProfileSetUp 1 == user profile; 2 == inventory
     * @param unverifiedIntProfileSetUp 3 == unverified customer; 4 == unverified emplyee
     * @param unverifiedDBPhrase
     * @param verifiedDBPhrase
     */
    public singleTable(
        initializer i, 
        Window w,
        JPanel panel, 
        String verifiedTitle,
        String unverifiedTitle,
        String[] verifiedColumnTitles, 
        String[] unverifiedColumnTitles,
        int rowHeight, 
        int verifiedIntInstruct, 
        int unverifiedIntInstruct,
        String placeHolder,
        int columnToFetchDataForProfileView,
        int verifiedIntProfileSetUp,
        int unverifiedIntProfileSetUp,
        String unverifiedDBPhrase,
        String verifiedDBPhrase
    ) {
        this.i = i;
        this.w = w;
        this.panel = panel;
        this.rowHeight = rowHeight;
        this.columnToFetchDataForProfileView = columnToFetchDataForProfileView;
        this.placeHolder = placeHolder;
        this.verifiedTitle = verifiedTitle;
        this.unverifiedTitle = unverifiedTitle;
        this.verifiedIntProfileSetUp = verifiedIntProfileSetUp;
        this.unverifiedIntProfileSetUp = unverifiedIntProfileSetUp;
        this.verifiedColumnTitles = verifiedColumnTitles;
        this.unverifiedColumnTitles = unverifiedColumnTitles;
        this.verifiedIntInstruct = verifiedIntInstruct;
        this.unverifiedIntInstruct = unverifiedIntInstruct;
        this.unverifiedDBPhrase = unverifiedDBPhrase;
        this.verifiedDBPhrase = verifiedDBPhrase;

        _initTableForVerified();
        _initTableForUnverified();
        _createButtons();
        _config();

        i.switchThemeComp.dummy.add(this);
    }

    /*//////////////////////////////////////////////////////////////
                             Create JTable
    //////////////////////////////////////////////////////////////*/    
 
    void _initTableForVerified() {
        createComp.createJTable _initTable = new createComp.createJTable(
            i,
            w,
            verifiedColumnTitles,
            20,
            320,
            1200,
            rowHeight,
            Color.CYAN,
            Color.WHITE,
            Color.PINK,
            new Color(0, 0, 0, 0),
            new Font("Arial", Font.BOLD, 15),
            columnToFetchDataForProfileView,
            verifiedIntProfileSetUp
        );

        this.verifiedTable = _initTable.table;
        this.verifiedTableModel = _initTable.tableModel;

        _loadData(_initTable);

        this.verifiedLabel = createComp.createJLabel(
            verifiedTitle,
            50, 200,
            300, 100,
            new Font("Arial", Font.BOLD, 20),
            Color.WHITE
        );

        SearchSingleTable.createSearchBar searchBar = new SearchSingleTable.createSearchBar(
            verifiedTable, 
            verifiedTableModel,
            350, 
            230,
            270, 
            43,
            Color.WHITE,
            placeHolder
        );
        this.verifiedSearchBar = searchBar.searchField;
        this.verifiedSearchIcon = searchBar.searchIcon;
    }

    void _initTableForUnverified() {
        tableAddIcon.createProfileTable table = new tableAddIcon.createProfileTable(
            i, 
            w, 
            unverifiedColumnTitles, 
            20, 
            320, 
            1200, 
            rowHeight, 
            Color.CYAN, 
            Color.WHITE, Color.PINK,  
            new Color(0, 0, 0, 0), 
            new Font("Arial", Font.BOLD, 15), 
            columnToFetchDataForProfileView, 
            unverifiedIntProfileSetUp,
            unverifiedIntInstruct
        );

        this.unverifiedTable = table.table.table;
        this.unverifiedTableModel = table.table.tableModel;

        _loadData(table);

        this.unverifiedLabel = createComp.createJLabel(
            unverifiedTitle,
            50, 200,
            300, 100,
            new Font("Arial", Font.BOLD, 20),
            Color.WHITE
        );

        SearchSingleTable.createSearchBar searchBar = new SearchSingleTable.createSearchBar(
            unverifiedTable, 
            unverifiedTableModel,
            350, 
            230,
            270, 
            43,
            Color.WHITE,
            placeHolder
        );
        this.unverifiedSearchBar = searchBar.searchField;
        this.unverifiedSearchIcon = searchBar.searchIcon;
    }

    /*//////////////////////////////////////////////////////////////
                               Load Datas
    //////////////////////////////////////////////////////////////*/    

    void _loadData(createComp.createJTable _initTable) {

        // load Customer Database
        if (verifiedIntInstruct == 1) {
            for (Map.Entry<String, Profile.userProfile> user : i.storage.Users.entrySet()) {
                if (user.getValue().isVerified && user.getValue().department == Profile.Department.CUSTOMER) {
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

        // load Salesman Database
        if (verifiedIntInstruct == 2) {
            for (Map.Entry<String, Profile.userProfile> user : i.storage.Users.entrySet()) {
                if (user.getValue().isVerified && user.getValue().department == Profile.Department.SALESMAN) {
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

        // load Manager Database
        if (verifiedIntInstruct == 3) {
            for (Map.Entry<String, Profile.userProfile> user : i.storage.Users.entrySet()) {
                if (user.getValue().isVerified && user.getValue().department == Profile.Department.MANAGER) {
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

        // load Inventory Database
        if (verifiedIntInstruct == 4) {
            for (stockDetails.transactDetails car : i.stockInventory.carDetails) {
                _initTable.addRow(new Object[] {
                    car.carId,
                    car.carDetails.carLogo,
                    car.carDetails.carName,
                    car.carDetails.status,
                    car.carDetails.BoughtFrom,
                    car.carDetails.SellTo
                });
            } 
        }

    }

    void _loadData(tableAddIcon.createProfileTable table) {

        // load Customer Verification Database
        if (unverifiedIntInstruct == 1) {
            for (Map.Entry<String, Profile.userProfile> user : i.storage.Users.entrySet()) {
                if (!user.getValue().isVerified && user.getValue().department == Profile.Department.CUSTOMER) {
                    if (i.storage.CustomerVerification.containsKey(user.getKey())) {
                        table.table.addRow(new Object[] {
                            user.getKey(),
                            user.getValue().firstName,
                            user.getValue().lastName,
                            user.getValue().gender,
                            user.getValue().age
                        });
                    }
                }
            }
        }

        // load Salesman Verification Database
        if (unverifiedIntInstruct == 2) {
            for (Map.Entry<String, Profile.CV> employee : i.storage.Job.entrySet()) {
                if (employee.getValue().department == Profile.Department.SALESMAN) {
                    table.table.addRow(new Object[] {
                        employee.getKey(),
                        employee.getValue().firstName,
                        employee.getValue().lastName,
                        employee.getValue().gender,
                        employee.getValue().age
                    });
                }
            }
        }

        // load Manager Verification Database
        if (unverifiedIntInstruct == 3) {
            for (Map.Entry<String, Profile.CV> employee : i.storage.Job.entrySet()) {
                if (employee.getValue().department == Profile.Department.MANAGER) {
                    table.table.addRow(new Object[] {
                        employee.getKey(),
                        employee.getValue().firstName,
                        employee.getValue().lastName,
                        employee.getValue().gender,
                        employee.getValue().age
                    });
                }
            }
        }

    }

    /*//////////////////////////////////////////////////////////////
                             Add to JPanel
    //////////////////////////////////////////////////////////////*/    

    void _config() {
        // panel
        if (verifiedLabel != null) panel.add(verifiedLabel);
        if (verifiedSearchBar != null) panel.add(verifiedSearchBar);
        if (verifiedSearchIcon != null) panel.add(verifiedSearchIcon);
        if (verifiedTable.getTableHeader() != null) panel.add(verifiedTable.getTableHeader());
        if (verifiedTable != null) panel.add(verifiedTable);
        if (verifiedButton != null) panel.add(verifiedButton);

        if (unverifiedLabel != null) panel.add(unverifiedLabel);
        if (unverifiedSearchBar != null) panel.add(unverifiedSearchBar);
        if (unverifiedSearchIcon != null) panel.add(unverifiedSearchIcon);
        if (unverifiedTable != null) panel.add(unverifiedTable.getTableHeader());
        if (unverifiedTable != null) panel.add(unverifiedTable);
        if (unverifiedButton != null) panel.add(unverifiedButton);

        if (addInventoryButton != null) panel.add(addInventoryButton);

        // dummy
        if (verifiedLabel != null) i.switchThemeComp.SecondPageLabels.add(verifiedLabel);
        if (verifiedSearchBar != null) i.switchThemeComp.SecondPageJTextFields.add(verifiedSearchBar);
        if (verifiedSearchIcon != null) i.switchThemeComp.dummy.add(verifiedSearchIcon);
        if (verifiedTable != null) i.switchThemeComp.dummy.add(verifiedTable.getTableHeader());
        if (verifiedButton != null) i.switchThemeComp.SecondPageButtons.add(verifiedButton);

        if (unverifiedLabel != null) i.switchThemeComp.SecondPageLabels.add(unverifiedLabel);
        if (unverifiedSearchBar != null) i.switchThemeComp.SecondPageJTextFields.add(unverifiedSearchBar);
        if (unverifiedSearchIcon != null) i.switchThemeComp.dummy.add(unverifiedSearchIcon);
        if (unverifiedTable != null) i.switchThemeComp.dummy.add(unverifiedTable.getTableHeader());
        if (unverifiedButton != null) i.switchThemeComp.SecondPageButtons.add(unverifiedButton);

        if (addInventoryButton != null) i.switchThemeComp.SecondPageButtons.add(addInventoryButton);

        // hide them
        _setVerifiedVisibility(false);
        _setUnverifiedVisibility(false);
    }  
    
    void _createButtons() {

        if (verifiedDBPhrase != null && unverifiedDBPhrase != null) {
            verifiedButton = createComp.createJButton(
                verifiedDBPhrase, 
                1000, 
                250, 
                200, 
                50, 
                new roundedBorder(15, Color.WHITE, null), 
                Color.PINK, 
                new Font("Arial", Font.BOLD, 15)
            );
            
            verifiedButton.addActionListener( _ -> {
                _setVerifiedVisibility(true);
                _setUnverifiedVisibility(false);
            });;

            unverifiedButton = createComp.createJButton(
                unverifiedDBPhrase, 
                1000, 
                250, 
                200, 
                50, 
                new roundedBorder(15, Color.WHITE, null), 
                Color.PINK, 
                new Font("Arial", Font.BOLD, 15)
            );
            
            unverifiedButton.addActionListener( _ -> {
                _setVerifiedVisibility(false);
                _setUnverifiedVisibility(true);
            });
        } 

        else {

            addInventoryButton = createComp.createJButton(
                "Add Cars", 
                1000, 
                250, 
                200, 
                50, 
                new roundedBorder(15, Color.WHITE, null), 
                Color.PINK, 
                new Font("Arial", Font.BOLD, 15)
            );

            addInventoryButton.addActionListener( _ -> {
                helpStoreComp._startDropDown(
                    i, 
                    () -> { i.AddCar = new AddCar(i, w); }, 
                    () -> i.AddCar, 
                    1000, 
                    500
                );
            });    

        }
    }

    /*//////////////////////////////////////////////////////////////
                        Switch Table Visibility
    //////////////////////////////////////////////////////////////*/
    
    public void _setVerifiedVisibility(boolean bool) {
        if (verifiedLabel != null) verifiedLabel.setVisible(bool);
        if (verifiedSearchBar != null) verifiedSearchBar.setVisible(bool);
        if (verifiedSearchIcon != null) verifiedSearchIcon.setVisible(bool);
        if (verifiedTable != null) verifiedTable.getTableHeader().setVisible(bool);
        if (verifiedTable != null) verifiedTable.setVisible(bool);
        if (unverifiedButton != null) unverifiedButton.setVisible(bool);
        if (addInventoryButton != null) addInventoryButton.setVisible(bool);

    }

    public void _setUnverifiedVisibility(boolean bool) {
        if (unverifiedLabel != null) unverifiedLabel.setVisible(bool);
        if (unverifiedSearchBar != null) unverifiedSearchBar.setVisible(bool);
        if (unverifiedSearchIcon != null) unverifiedSearchIcon.setVisible(bool);
        if (unverifiedTable != null) unverifiedTable.getTableHeader().setVisible(bool);
        if (unverifiedTable != null) unverifiedTable.setVisible(bool);
        if (verifiedButton != null) verifiedButton.setVisible(bool);    
        if (addInventoryButton != null) addInventoryButton.setVisible(bool);

    }

}
