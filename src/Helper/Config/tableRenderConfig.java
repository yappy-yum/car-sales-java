package Helper.Config;

import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class tableRenderConfig extends DefaultTableCellRenderer {

    public static DefaultTableCellRenderer createCenterAlignedRenderer(Color textColor, Color backgroundColor) {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column
            ) {
                Component c = super.getTableCellRendererComponent(
                                                table, 
                                                value, 
                                                isSelected, 
                                                hasFocus, 
                                                row, 
                                                column
                                        );

                if (isSelected) c.setBackground(table.getSelectionBackground());
                else c.setBackground(backgroundColor);

                c.setForeground(textColor);
                if (c instanceof JLabel) ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);

                return c;
            }
        };
    }

}
