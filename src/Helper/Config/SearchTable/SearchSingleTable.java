package Helper.Config.SearchTable;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Helper.Comp.createComp;
import Helper.fileSystem.imageSystem;

public class SearchSingleTable {
 
    public static class createSearchBar {

        public JTextField searchField;
        public JLabel searchIcon;

        public createSearchBar(
            JTable table, 
            DefaultTableModel model,
            int X, 
            int Y, 
            int width, 
            int height,
            Color textColor,
            Border border,
            String placeholder
        ) {
            searchField = createComp.createJTextField(
                X, 
                Y, 
                width, 
                height, 
                new Font("Arial", Font.PLAIN, 15), 
                border, 
                textColor
            );
            searchIcon = new JLabel(imageSystem._scaleImage(imageSystem.SEARCH, 25, 25));
            searchIcon.setBounds(X + width - 45, Y + 5, 30, 30);

            // Set placeholder text
            searchField.setForeground(Color.GRAY);
            searchField.setText(placeholder);

            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            table.setRowSorter(sorter);

            // Placeholder handling on focus
            searchField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (searchField.getText().equals(placeholder)) {
                        searchField.setText("");
                        searchField.setForeground(textColor);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (searchField.getText().trim().isEmpty()) {
                        searchField.setForeground(Color.GRAY);
                        searchField.setText(placeholder);
                    }
                }
            });

            // Filtering table on text change
            searchField
                .getDocument()
                .addDocumentListener(
                    new DocumentListener() {

                        private void update() {
                            String text = searchField.getText();

                            if (text.equals(placeholder) || text.trim().isEmpty()) sorter.setRowFilter(null);
                            else sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                        }

                        public void insertUpdate(DocumentEvent e) { update(); }
                        public void removeUpdate(DocumentEvent e) { update(); }
                        public void changedUpdate(DocumentEvent e) { update(); } 
                    
                    }
                );
        }
    }


}

