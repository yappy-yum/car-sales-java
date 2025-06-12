package Comment;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

import Components.initializer;
import Helper.Comp.createComp;
import Helper.Config.roundedBorder;
import Helper.login.Profile;
import Inventory.stockDetails;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Supplier;

/**
 * 
 * Helper to create the comment JLabel components
 * 
 */
public class ComConfig 
{

    /*//////////////////////////////////////////////////////////////
                             JLabel Header
    //////////////////////////////////////////////////////////////*/    

    public static class createHeader {

        public JLabel Header;
        public stockDetails.transactDetails Car;

        public createHeader(JTextField input, initializer i) {
            Header = createComp.createJLabel(
                                    "Comment", 
                                    20, 
                                    25, 
                                    700, 
                                    50, 
                                    new Font("Arial", Font.BOLD, 20), 
                                    Color.BLACK
                                );

            if (!i.isLogin.isLogin) return;

            String username = i.isLogin.currentProfile.username;
            List<stockDetails.transactDetails> Transact = i.stockInventory.SearchCarViaUsername(username);

            if (i.isLogin.currentProfile.department != Profile.Department.CUSTOMER) return;
            if (Transact == null) return;

            for (stockDetails.transactDetails _Car : Transact) {
                JLabel dummyLabel = HeaderLabelConfig(_Car, Header);
                if (dummyLabel != null) {
                    Header.setText(dummyLabel.getText());
                    input.setEditable(true);
                    Car = _Car;
                    return;
                }
            }

        }

    }

    /*//////////////////////////////////////////////////////////////
                             Input Section
    //////////////////////////////////////////////////////////////*/    

    public static JTextField CreateInput() {
        return createComp.createJTextField
        (
            20, 
            100, 
            600, 
            50, 
            new Font("Arial", Font.BOLD, 18), 
            new roundedBorder(10, Color.BLACK, null), 
            Color.BLACK
        );
    }

    /*//////////////////////////////////////////////////////////////
                             Output Section
    //////////////////////////////////////////////////////////////*/    

    public static class CreateOutput {

        public JTextPane Output = new JTextPane();
        public JScrollPane Scroll = new JScrollPane(Output);

        public CreateOutput() {
            Output.setOpaque(false);
            Output.setBorder(new roundedBorder(10, Color.BLACK, null));
            Output.setEditable(false);
            Output.setFont(new Font("Arial", Font.BOLD, 15));
            Output.setPreferredSize(new Dimension(960, 280));

            Scroll.setBounds(20, 185, 960, 280);
            Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            Scroll.setOpaque(false);
            Scroll.getViewport().setOpaque(false);
            Scroll.setBorder(null);
            Scroll.setFocusable(false);
        }

    }

    /*//////////////////////////////////////////////////////////////
                             Append Action
    //////////////////////////////////////////////////////////////*/   

    public static class SubmitAction {

        public ActionListener Action;
        public String CarId;

        public SubmitAction
                    (
                        initializer i, 
                        JTextField input, 
                        JTextPane output, 
                        Supplier<stockDetails.transactDetails> _CarSupplier, 
                        JLabel Header,
                        UpdateCallback callback
                    ) 
                    
        {
            this.CarId = null;

            Action = _ -> 
                        {
                            stockDetails.transactDetails _Car = _CarSupplier.get();
                            if (_Car == null) return;

                            String text = input.getText().trim();

                            if (!text.isEmpty()) {
                                try 
                                {
                                    output
                                        .getDocument()
                                        .insertString(
                                            output.getDocument().getLength(),

                                            "Car: " + _Car.carId + ", " + _Car.carDetails.carName + "\n" +
                                            "Salesman: " + _Car.Salesman + "\n" +
                                            "Customer: " + i.isLogin.currentProfile.username + "\n" + 
                                            "Comment: " + "\n" + 
                                            text + "\n\n", 

                                            null
                                        );

                                    input.setText("");
                                    output.setCaretPosition(output.getDocument().getLength());

                                    if (!i.isLogin.isLogin) return; 
                                    LoopCommentRenew loop = new LoopCommentRenew(i, _Car.carId, input, Header); 
                                    
                                    if (loop.DummyLabel != null) Header.setText(loop.DummyLabel.getText());
                                    if (loop.CarID != null) {
                                        CarId = loop.CarID;

                                        stockDetails.transactDetails newCar = i.stockInventory.SearchCarViaID(CarId);
                                        callback.CarUpdates(newCar);
                                    }
                                } 
                                
                                catch (BadLocationException LOVE)
                                {
                                    LOVE.printStackTrace();
                                }
                            }
                        };
        }

    }

    /*//////////////////////////////////////////////////////////////
                       Helper: Header Text Config
    //////////////////////////////////////////////////////////////*/    

    protected static JLabel HeaderLabelConfig(stockDetails.transactDetails Car, JLabel header) {

        if (!Car.isCommented && Car.carDetails.status == stockDetails.CarStatus.SOLD) {
            header
                .setText(
                    ("Comment On ")
                        .concat(Car.carDetails.carName)
                        .concat(" by ")
                        .concat(Car.Salesman)
                );

            return header;
        }

        return null;

    }

    /*//////////////////////////////////////////////////////////////
                         Helper: Loop Next Car
    //////////////////////////////////////////////////////////////*/    

    protected static class LoopCommentRenew {

        JLabel DummyLabel;
        String CarID;

        protected LoopCommentRenew
                            (
                                initializer i, 
                                String carId, 
                                JTextField input, 
                                JLabel Header
                            ) 
                            
        {

            List<stockDetails.transactDetails> Transact = i.stockInventory.SearchCarViaUsername(i.isLogin.currentProfile.username);
            if (Transact == null) return;

            String _CarId = carId;

            for (stockDetails.transactDetails Car : Transact) {
                if (_CarId != null && Car.carId.equals(carId)) {
                    Car.isCommented = true;
                    _CarId = null;
                    continue;
                }

                DummyLabel = HeaderLabelConfig(Car, Header);
                if (DummyLabel != null) {
                    this.CarID = Car.carId; 
                    return;
                }

            } 

            DummyLabel = new JLabel();
            DummyLabel.setText("Comment");
            input.setEditable(false);

        }

    }

}