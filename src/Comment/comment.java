package Comment;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.function.Supplier;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import Components.Window;
import Components.initializer;
import Helper.blur;
import Helper.Comp.PanelHelper;
import Helper.Comp.createComp;
import Helper.Config.roundedBorder;
import Helper.fileSystem.imageSystem;
import Inventory.stockDetails;

public class comment extends JPanel {

    blur blur;
    initializer i;
    Window W;

    JTextField InputComment;
    JTextPane OutputPane;
    JScrollPane OutputScroll;

    JButton SubmitButton;
    JLabel Header;

    stockDetails.transactDetails Car;
    
    public comment(initializer i, Window W) {
        this.i = i;
        this.W = W;
        blur = new blur(i.frame);

        _background();      _close();
        _addInput();        _addOutput();
        _addSubmit();       _addHeader();
        _addSubmitListener(); 

        i.switchThemeComp.dummy.add(this);
    }

   /*//////////////////////////////////////////////////////////////
                           Background JPanel
    //////////////////////////////////////////////////////////////*/    

    void _background() {
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

    /*//////////////////////////////////////////////////////////////
                                 Close
    //////////////////////////////////////////////////////////////*/    
    
    void _close() {
        JButton closeButton = createComp.createJButton(
            "X", 
            900, 20, 
            60, 40, 
            new roundedBorder(10, Color.BLACK, null), 
            Color.BLACK, 
            new Font("Arial", Font.BOLD, 20)
        );
        closeButton.setVisible(true);
        closeButton.addActionListener(
            _ -> {
                blur.removeBlur();
                blur = null;
                PanelHelper.clear(this);
                SwingUtilities.invokeLater(
                    () -> {
                        if (i.component != null) W._loadFrontPage();
                        if (i.UI != null) W._loadDBPage();
                        if (i.Purchase != null) W._loadPurchasePage();
                    }
                );
            }
        );

        add(closeButton);
        i.switchThemeComp.dummy.add(closeButton);
    }

    void _addHeader() {
        ComConfig.createHeader a = new ComConfig.createHeader(InputComment, i);
        Header = a.Header;
        this.Car = a.Car;

        Header.setVisible(true);

        add(Header);
        i.switchThemeComp.dummy.add(Header);
    }

    /*//////////////////////////////////////////////////////////////
                                 Input
    //////////////////////////////////////////////////////////////*/    

    void _addInput() {
        InputComment = ComConfig.CreateInput();
        InputComment.setVisible(true);
        InputComment.setEditable(false);

        add(InputComment);
        i.switchThemeComp.dummy.add(InputComment);
    }

    /*//////////////////////////////////////////////////////////////
                                 Output
    //////////////////////////////////////////////////////////////*/

    void _addOutput() {
        OutputPane = i.SaveComp.CommentSection.Output;
        OutputScroll = i.SaveComp.CommentSection.Scroll;

        OutputScroll.setVisible(true);

        add(OutputScroll);
        i.switchThemeComp.dummy.add(OutputScroll);
    }

    /*//////////////////////////////////////////////////////////////
                             Submit Button
    //////////////////////////////////////////////////////////////*/
    
    void _addSubmit() {
        SubmitButton = createComp.createJButton(
            "Submit", 
            650, 100, 
            100, 50, 
            new roundedBorder(10, Color.BLACK, null), 
            Color.BLACK, 
            new Font("Arial", Font.BOLD, 15)
        );
        SubmitButton.setVisible(true);

        add(SubmitButton);
        i.switchThemeComp.dummy.add(SubmitButton);
    }

    /*//////////////////////////////////////////////////////////////
                             Apply Listener
    //////////////////////////////////////////////////////////////*/    

    void _addSubmitListener() {
        ActionListener SubmitAction = _actSubmitListener();

        SubmitButton.addActionListener(SubmitAction);
        InputComment.addActionListener(SubmitAction);

    }

    ActionListener _actSubmitListener() {
        ComConfig.SubmitAction b = new ComConfig.SubmitAction
                                            (
                                                    (initializer) i,
                                                    (JTextField) InputComment,
                                                    (JTextPane) OutputPane,
                                                    (Supplier<stockDetails.transactDetails>) (() -> this.Car),
                                                    (JLabel) Header,
                                                    (UpdateCallback) (stockDetails.transactDetails newCar) -> this.Car = newCar
                                            );

        if (b.CarId != null) this.Car = i.stockInventory.SearchCarViaID(b.CarId);
        if (Car != null) System.out.println("Car is commented: " + Car.isCommented);

        return b.Action;
    }

}
