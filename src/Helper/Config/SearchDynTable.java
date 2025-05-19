package Helper.Config;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Helper.Comp.createComp;
import Helper.Comp.createDynamicTable;
import Helper.fileSystem.imageSystem;

public class SearchDynTable {
    
    public static class createSearchBar {

        public JTextField searchField;
        public JLabel searchIcon;

        public createSearchBar(
            createDynamicTable.createMultiTable multiTable,
            int X, 
            int Y,
            int width, 
            int height,
            Color textColor
        ) {
            searchField = createComp.createJTextField(
                X, 
                Y, 
                width, 
                height, 
                new Font("Arial", Font.PLAIN, 15), 
                new roundedBorder(10, Color.WHITE, null),
                textColor
            );
            searchIcon = new JLabel(imageSystem._scaleImage(imageSystem.SEARCH, 25, 25));
            searchIcon.setBounds(X + width - 45, Y + 5, 30, 30);

            String placeHolder = "Search Inventory";
            searchField.setForeground(Color.GRAY);
            searchField.setText(placeHolder);
            
            searchField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (searchField.getText().equals(placeHolder)) {
                        searchField.setText("");
                        searchField.setForeground(textColor);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (searchField.getText().trim().isEmpty()) {
                        searchField.setForeground(Color.GRAY);
                        searchField.setText(placeHolder);
                    }
                }
            });

            searchField
                .getDocument()
                .addDocumentListener(
                    new DocumentListener() {
                        
                        private void update() {
                            String text = searchField.getText();

                            for (TableRowSorter<DefaultTableModel> sorter : multiTable.sorters) {
                                if (text.equals(placeHolder) || text.trim().isEmpty()) {
                                    sorter.setRowFilter(null);
                                } else {
                                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                                }
                            }
                        }

                        public void insertUpdate(DocumentEvent e) { update(); }
                        public void removeUpdate(DocumentEvent e) { update(); }
                        public void changedUpdate(DocumentEvent e) { update(); }

                    }
                );
        }

    }

}
