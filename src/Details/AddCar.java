package Details;

import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import Components.SwitchThemeComp;
import Components.Window;
import Components.initializer;
import Helper.ErrorMessages;
import Helper.blur;
import Helper.Animation.componentAnim;
import Helper.Comp.createComp;
import Helper.Config.roundedBorder;
import Helper.Config.PanelConfig.DropdownPanel;
import Helper.Config.PanelConfig.PanelHelper;
import Helper.fileSystem.ImageUploader;
import Helper.fileSystem.fontSystem;
import Helper.fileSystem.imageSystem;
import Helper.login.loginComp;
import Helper.login.loginFill;
import Inventory.stockDetails;
import LoginSystem.LoginPage.PromptMessage;

public class AddCar extends DropdownPanel {

    initializer i;
    SwitchThemeComp S;
    Window W;
    blur blur;

    PromptMessage message;
 
    JButton Submit;
    JButton Close;

    JButton Brand;
    JButton Image;

    ImageIcon CAR_BRAND_ICON = imageSystem._scaleImage(imageSystem.BASE_CAR_BRAND, 80, 60);
    ImageIcon CAR_IMAGE_ICON = imageSystem._scaleImage(imageSystem.BASE_CAR_IMAGE, 250, 200);

    // car brand
    loginFill.createLabelAndShortJTextField CarBrand = new loginFill.createLabelAndShortJTextField("Car Brand:", 30, 30);
    // car full name
    loginFill.createLabelAndShortJTextField CarFullName = new loginFill.createLabelAndShortJTextField("Car Full Name:", 30, 100);
    // car length instruction
    loginComp.createCarLengthInstructor CarLengthInstructor = new loginComp.createCarLengthInstructor(430, 138);
    // car length
    loginFill.createLabelAndShortJTextField CarLength = new loginFill.createLabelAndShortJTextField("Car Length:", 30, 170);  
    // car horse power
    loginFill.createLabelAndShortJTextField CarHorsePower = new loginFill.createLabelAndShortJTextField("Car Horse Power:", 30, 240);
    // payable from
    loginFill.createLabelAndShortJTextField PayableFrom = new loginFill.createLabelAndShortJTextField("Payable From:", 30, 310);
    // car cost
    loginFill.createLabelAndShortJTextField CarCost = new loginFill.createLabelAndShortJTextField("Car Cost:", 30, 380);
    // car selling price
    loginFill.createLabelAndShortJTextField CarSellingPrice = new loginFill.createLabelAndShortJTextField("Car Selling Price:", 30, 450);

    /*//////////////////////////////////////////////////////////////
                              Constructor
    //////////////////////////////////////////////////////////////*/    

    public AddCar(initializer i, Window W) {
        this.i = i;
        this.W = W;
        this.S = i.switchThemeComp;
        this.blur = new blur(i.frame);

        _addRightInsertImage();
        _addSubmitCloseButton();
        _addComp();
        _setVisible();

    }

    /*//////////////////////////////////////////////////////////////
                             Submit & Close
    //////////////////////////////////////////////////////////////*/    

    void _addSubmitCloseButton() {
        Close = createComp.createJButton(
            "Close", 
            800, 
            400, 
            90, 
            50, 
            new roundedBorder(20, Color.BLACK, null), 
            Color.BLACK, 
            fontSystem.SLAB.deriveFont(30f)
        );
        Close.addActionListener( _ -> { _close(); } );

        Submit = createComp.createJButton(
            "Submit", 
            600, 
            400, 
            150, 
            50, 
            new roundedBorder(20, Color.BLACK, null), 
            Color.BLACK, 
            fontSystem.SLAB.deriveFont(30f)
        );
        Submit.addActionListener( _ -> { _Checks(); } );
    }

    /*//////////////////////////////////////////////////////////////
                              Insert Image
    //////////////////////////////////////////////////////////////*/    

    void _addRightInsertImage() {
        Brand = createComp.createJButton(
            CAR_BRAND_ICON, 
            700, 40, 
            80, 60
        );
        Brand.addActionListener(
            _ -> {
                ImageIcon img = ImageUploader.uploadImageIcon();
                if (img != null) {
                    Brand.setIcon(
                        imageSystem._scaleImage(
                            img, 
                            80, 
                            80
                        )
                    );
                }
            }
        );

        Image = createComp.createJButton(
            CAR_IMAGE_ICON, 
            600, 120, 
            250, 200
        );
        Image.addActionListener(
            _ -> {
                ImageIcon img = ImageUploader.uploadImageIcon();
                if (img != null) {
                    Image.setIcon(
                        imageSystem._scaleImage(
                            img, 
                            250, 
                            200
                        )
                    );
                }
            }
        );
    }

    /*//////////////////////////////////////////////////////////////
                               Add JPanel
    //////////////////////////////////////////////////////////////*/    

    void _addComp() {
        add(Close);
        add(Submit);

        add(Brand);
        add(Image);

        add(CarLengthInstructor.button);
        add(CarLengthInstructor.textArea);
        add(CarLengthInstructor.textBackground);
        add(CarBrand.label);
        add(CarBrand.textField);
        add(CarFullName.label);
        add(CarFullName.textField);
        add(CarLength.label);
        add(CarLength.textField);
        add(CarHorsePower.label);
        add(CarHorsePower.textField);
        add(PayableFrom.label);
        add(PayableFrom.textField);
        add(CarCost.label);
        add(CarCost.textField);
        add(CarSellingPrice.label);
        add(CarSellingPrice.textField);
    }

    /*//////////////////////////////////////////////////////////////
                                 Checks
    //////////////////////////////////////////////////////////////*/    

    void _Checks() {
        String _CarBrand = CarBrand.textField.getText();
        String _CarFullName = CarFullName.textField.getText();
        String _CarLength = CarLength.textField.getText();
        String _CarHorsePower = CarHorsePower.textField.getText();
        String _PayableFrom = PayableFrom.textField.getText();
        String _CarCost = CarCost.textField.getText();
        String _CarSellingPrice = CarSellingPrice.textField.getText();
        ImageIcon _BrandLogo = (ImageIcon) Brand.getIcon();
        ImageIcon _CarImage = (ImageIcon) Image.getIcon();
        
        if (_CarBrand.trim().isEmpty()) {
            _promptMessage(ErrorMessages.ERROR_MESSAGE_FOR_ADD_CAR[0]);
            return;
        }
        if (_CarFullName.trim().isEmpty()) {
            _promptMessage(ErrorMessages.ERROR_MESSAGE_FOR_ADD_CAR[1]);
            return;
        }
        if (_CarLength.trim().isEmpty()) {
            _promptMessage(ErrorMessages.ERROR_MESSAGE_FOR_ADD_CAR[2]);
            return;
        }
        if (!_CarLength.matches("\\d+ X \\d+")) {
            _promptMessage(ErrorMessages.ERROR_MESSAGE_FOR_ADD_CAR[3]);
            return;
        }
        if (_CarHorsePower.trim().isEmpty()) {
            _promptMessage(ErrorMessages.ERROR_MESSAGE_FOR_ADD_CAR[4]);
            return;
        }
        if (!_CarHorsePower.trim().matches("\\d+")) {
            _promptMessage(ErrorMessages.ERROR_MESSAGE_FOR_ADD_CAR[5]);
            return;
        }
        if (_PayableFrom.trim().isEmpty()) {
            _promptMessage(ErrorMessages.ERROR_MESSAGE_FOR_ADD_CAR[6]);
            return;
        }
        if (_CarCost.trim().isEmpty()) {
            _promptMessage(ErrorMessages.ERROR_MESSAGE_FOR_ADD_CAR[7]);
            return;
        }
        if (!_CarCost.trim().matches("^\\d+(\\.\\d{1,2})?$")) {
            _promptMessage(ErrorMessages.ERROR_MESSAGE_FOR_ADD_CAR[8]);
            return;
        }
        if (_CarSellingPrice.trim().isEmpty()) {
            _promptMessage(ErrorMessages.ERROR_MESSAGE_FOR_ADD_CAR[9]);
            return;
        }
        if (!_CarSellingPrice.trim().matches("^\\d+(\\.\\d{1,2})?$")) {
            _promptMessage(ErrorMessages.ERROR_MESSAGE_FOR_ADD_CAR[10]);
            return;
        }
        if (_BrandLogo == CAR_BRAND_ICON) {
            _promptMessage(ErrorMessages.ERROR_MESSAGE_FOR_ADD_CAR[11]);
            return;
        }
        if (_CarImage == CAR_IMAGE_ICON) {
            _promptMessage(ErrorMessages.ERROR_MESSAGE_FOR_ADD_CAR[12]);
            return;
        }

        _insertCars(
            _CarBrand, 
            _CarFullName, 
            _CarLength, 
            _CarHorsePower, 
            _PayableFrom,
            _CarCost,
            _CarSellingPrice,
            _BrandLogo,
            _CarImage
        );
    }

    void _insertCars(
        String _CarBrand, 
        String _CarFullName, 
        String _CarLength, 
        String _CarHorsePower, 
        String _PayableFrom, 
        String _CarCost, 
        String _CarSellingPrice,
        ImageIcon _BrandLogo, 
        ImageIcon _CarImage
    ) {
        i.stockInventory.buyCar(
            new stockDetails.transactDetails(
                i.CarIDGenerator.GenerateCarId(), 
                new stockDetails.CarDetails(
                    stockDetails.CarStatus.AVAILABLE, 
                    _BrandLogo, 
                    _CarImage, 
                    _CarBrand, 
                    _CarFullName, 
                    _PayableFrom, 
                    null,
                    _CarLength, 
                    Double.parseDouble(_CarCost), 
                    Double.parseDouble(_CarSellingPrice), 
                    Integer.parseInt(_CarHorsePower)
                ), 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a")).toLowerCase(), 
                null, 
                null, 
                null, 
                null,
                null,
                false
            )
        );

        _close();
    }

    /*//////////////////////////////////////////////////////////////
                               Drop Down
    //////////////////////////////////////////////////////////////*/    

    public void _removeMessage() { remove(message); message = null; }

    void _promptMessage(JLabel text) {
        message = new PromptMessage(i, text, this);
        message.setBounds(300, 150, 550, 180);
        message.setVisible(true);

        i.frame.getContentPane().add(message);

        i.compAnimStorage.addAnim(
            new componentAnim(
                message, 
                350, 150, 
                350, 250, 
                i.scrollPane
            ).start()
        );
    }

    /*//////////////////////////////////////////////////////////////
                                 Close
    //////////////////////////////////////////////////////////////*/    

    void _close() {
        PanelHelper.clear(null, this);
        SwingUtilities.invokeLater(() -> { W._loadDBPage(); });
    }

    /*//////////////////////////////////////////////////////////////
                              Visible True
    //////////////////////////////////////////////////////////////*/
    
    void _setVisible() {
        CarBrand.label.setVisible(true);
        CarBrand.textField.setVisible(true);

        CarFullName.label.setVisible(true);
        CarFullName.textField.setVisible(true);

        CarLength.label.setVisible(true);
        CarLength.textField.setVisible(true);

        CarHorsePower.label.setVisible(true);
        CarHorsePower.textField.setVisible(true);

        PayableFrom.label.setVisible(true);
        PayableFrom.textField.setVisible(true);

        CarCost.label.setVisible(true);
        CarCost.textField.setVisible(true);

        CarSellingPrice.label.setVisible(true);
        CarSellingPrice.textField.setVisible(true);

        Brand.setVisible(true);

        Close.setVisible(true);
        Submit.setVisible(true);

        CarLengthInstructor.button.setVisible(true);
    }
    

}
