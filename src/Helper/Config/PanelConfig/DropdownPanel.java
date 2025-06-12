package Helper.Config.PanelConfig;

import java.awt.Color;

import javax.swing.JPanel;

import Helper.Config.roundedBorder;
import Helper.fileSystem.imageSystem;

/**
 * 
 * Panel Background specifically for drop down
 * 
 */
public abstract class DropdownPanel extends JPanel {
    
    public DropdownPanel() {
        setOpaque(false);
        setLayout(null);
        setBorder(
            new roundedBorder(
                20, 
                Color.BLACK, 
                imageSystem._reduceColorTransparency(Color.GRAY, 0.7f)
            )
        );        
    }   

}
