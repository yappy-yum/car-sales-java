package Details;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import Components.SwitchThemeComp;
import Components.Window;
import Components.initializer;
import Helper.blur;
import Helper.Comp.PanelHelper;
import Helper.Comp.createComp;
import Helper.Config.roundedBorder;
import Helper.fileSystem.imageSystem;
import Inventory.stockDetails;
import Helper.login.Profile;

public class checkCar extends JPanel {

    blur blur;
    Window window;
    SwitchThemeComp S;
    initializer i;
    String CarID;

    /*//////////////////////////////////////////////////////////////
                              constructor
    //////////////////////////////////////////////////////////////*/    
    
    public checkCar(initializer i, Window window, String carId) {
        this.blur = new blur(i.frame);
        this.window = window;
        this.S = i.switchThemeComp;
        this.i = i;
        this.CarID = carId;

        _background();
        _addX();
        _addHeader();
        _addLeftInfo();
        _addRightImage();

        if 
        (
            i.isLogin.currentProfile.department == Profile.Department.SALESMAN &&
            i.stockInventory.SearchCarViaID(carId).carDetails.status == stockDetails.CarStatus.BOOKED
        ) _addSetPaidButton();

        S.dummy.add(this);
    }

    /*//////////////////////////////////////////////////////////////
                           JPanel Background
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
                             X Close Button
    //////////////////////////////////////////////////////////////*/    

    void _addX() {
        JButton closeButton = createComp.createJButton(
            "X",
            900, 20,
            60, 40,
            new roundedBorder(10, Color.BLACK, null), Color.BLACK,
            new Font("Arial", Font.BOLD, 20)
        );
        closeButton.setVisible(true);
        closeButton.addActionListener( _ -> { _close(); } );
        S.dummy.add(closeButton);
        add(closeButton);
    }

    void _close() {
        blur.removeBlur();
        blur = null;
        PanelHelper.clear(this);
        SwingUtilities.invokeLater(() -> { window._loadDBPage();});        
    }

    /*//////////////////////////////////////////////////////////////
                                 Header
    //////////////////////////////////////////////////////////////*/    

    void _addHeader() {
        JLabel header = createComp.createJLabel(
            "Car Details", 
            450, 20, 
            200, 50, 
            new Font("Arial", Font.BOLD, 25), 
            Color.BLACK
        );
        header.setVisible(true);
        S.dummy.add(header);
        add(header);
    }

    /*//////////////////////////////////////////////////////////////
                            Add Informations
    //////////////////////////////////////////////////////////////*/    

    void _addLeftInfo() {
        stockDetails.transactDetails car = i.stockInventory.SearchCarViaID(CarID);

        JTextArea leftInfo = createComp.createJTextArea(
            "Car ID: " + car.carId + "\n" +
            "Car Brand: " + car.carDetails.logoName + "\n" +
            "Car Name: " + car.carDetails.carName + "\n" +
            "Cost: RM" + String.format("%.2f", car.carDetails.buyingPrice) + "\n" +
            "Price: RM" + String.format("%.2f", car.carDetails.sellingPrice) + "\n" +
            "Receivable From: " + car.carDetails.BoughtFrom + "\n" +
            "Payable To: " + car.carDetails.SellTo + "\n" +
            "Car Length: " + car.carDetails.carLength + "\n" + 
            "Horse Power: " + car.carDetails.carHorsePower + "\n" +
            "Status: " + car.carDetails.status + "\n" +
            "Date Bought: " + car.DateBought + " " + car.TimeBought + "\n" +
            "Date Booked: " + car.DateBookedAt + " " + car.TimeBookedAt + "\n" +
            "Date Sold: " + car.DateSold + " " + car.TimeSold,
            20, 100,
            500, 500,
            new Font(Font.MONOSPACED, Font.BOLD, 20),
            null, Color.BLACK
        );
        leftInfo.setVisible(true);
        S.dummy.add(leftInfo);
        add(leftInfo);
    }

    /*//////////////////////////////////////////////////////////////
                                 Image
    //////////////////////////////////////////////////////////////*/    

    void _addRightImage() {
        stockDetails.transactDetails car = i.stockInventory.SearchCarViaID(CarID);

        JLabel rightImage = createComp.createJLabel(
            car.carDetails.carImage,
            650, 100, 
            250, 200
        );
        rightImage.setVisible(true);
        S.dummy.add(rightImage);
        add(rightImage);
    }

    void _addSetPaidButton() {

        JButton ApprovePaymentButton = createComp.createJButton(
            "Paid", 
            850, 400, 
            100, 80, 
            new roundedBorder(10, Color.BLACK, null), 
            Color.BLACK, 
            new Font("Arial", Font.BOLD, 20)
        );
        ApprovePaymentButton.addActionListener( 
            _ -> { 
                i.stockInventory.sellCar(
                    CarID, 
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a")).toLowerCase()
                ); 
                _close();
            } 
        );

        add(ApprovePaymentButton);
        S.dummy.add(ApprovePaymentButton);

        JButton RejectBookingButton = createComp.createJButton(
            "Cancel", 
            700, 400, 
            100, 80,
            new roundedBorder(10, Color.BLACK, null), 
            Color.BLACK, 
            new Font("Arial", Font.BOLD, 20)
        );
        RejectBookingButton.addActionListener( 
            _ -> { 
                i.stockInventory.cancerBooking(CarID);
                _close(); 
            } 
        );

        add(RejectBookingButton);
        S.dummy.add(RejectBookingButton);

    }

}
