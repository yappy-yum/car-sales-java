package Details;

import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Components.SwitchThemeComp;
import Components.Window;
import Components.initializer;
import Helper.blur;
import Helper.Comp.PanelHelper;
import Helper.Comp.createComp;
import Helper.Config.roundedBorder;
import Helper.fileSystem.ImageUploader;
import Helper.fileSystem.imageSystem;

/**
 * 
 * verification for customer to confirm their identity
 * by sending their face and document images. 
 * 
 * <p>
 * 
 * the confidential image will be hashed, and is stored in the 
 * system with the hashed images, rather than the original images.
 * 
 * <p>
 * 
 * when the customer verification approval has made, regardless of 
 * the result, the hashed images will gets deleted from the system.
 * the registered account will not be deleted if verification failed, allowing
 * customer to have another attempt to verify their identity.
 * 
 * @author yappy-yum
 *
 */
public class Verify extends JPanel {

    blur blur;
    SwitchThemeComp S;
    initializer i;
    Window w;

    JButton FaceButton, DocsButton;
    JLabel FaceLabel, DocsLabel;

    JButton close, submit;

    JLabel header;
    String user;
    Object classToInteractWith;

    /*//////////////////////////////////////////////////////////////
                                 Images
    //////////////////////////////////////////////////////////////*/
    
    ImageIcon FACE = imageSystem._scaleImage(imageSystem.DEFAULT_FACE, 200, 200);
    ImageIcon DOCS = imageSystem._scaleImage(imageSystem.DEFAULT_DOCS, 200, 200);

    /*//////////////////////////////////////////////////////////////
                              constructor
    //////////////////////////////////////////////////////////////*/    
    
    public Verify(initializer i, Window w, String user, Object classToInteractWith) {
        this.blur = new blur(i.frame);
        this.S = i.switchThemeComp;
        this.i = i;
        this.w = w;
        this.user = user;
        this.classToInteractWith = classToInteractWith;

        _background();
        _addDefaultBase();
        _addHeader();
        _addSubmit();
        _addClose();
        _addComp();
        
        S.dummy.add(this);
    }

    /*//////////////////////////////////////////////////////////////
                               Background
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
                         Add Confidential Here
    //////////////////////////////////////////////////////////////*/    

    void _addDefaultBase() {
        FaceButton = createComp.createJButton(
            FACE, 
            20, 80, 
            200, 200
        );
        DocsButton = createComp.createJButton(
            DOCS, 
            300, 80, 
            200, 200
        );

        FaceLabel = createComp.createJLabel(
            "Face Image", 
            80, 300, 
            100, 50,
            new Font("Arial", Font.BOLD, 15), 
            Color.BLACK
        );
        DocsLabel = createComp.createJLabel(
            "Document Image", 
            330, 300, 
            150, 50,
            new Font("Arial", Font.BOLD, 15), 
            Color.BLACK
        );

        _setImage(FaceButton);
        _setImage(DocsButton);
    }

    void _addSubmit() {
        submit = createComp.createJButton(
            "Submit", 
            680, 300, 
            100, 80, 
            new roundedBorder(15, Color.BLACK, null), 
            Color.BLACK, 
            new Font("Arial", Font.BOLD, 20)
        );
        submit.addActionListener( _ -> { 
            if (FaceButton.getIcon() != FACE && DocsButton.getIcon() != DOCS) {
                i.storage._encryptImage(user, (ImageIcon) FaceButton.getIcon(), (ImageIcon) DocsButton.getIcon());
                _removing();
            }
        });
    }
    
    /*//////////////////////////////////////////////////////////////
                                 header
    //////////////////////////////////////////////////////////////*/    

    void _addHeader() {
        header = createComp.createJLabel(
            "lets verify your identity using face and document images", 
            20, 30, 
            600, 50, 
            new Font("Arial", Font.BOLD, 20), 
            Color.BLACK
        );
    }

    /*//////////////////////////////////////////////////////////////
                                 close
    //////////////////////////////////////////////////////////////*/    

    void _addClose() {
        close = createComp.createJButton(
            "X", 
            700, 20, 
            60, 40, 
            new roundedBorder(10, Color.BLACK, null), Color.BLACK, 
            new Font("Arial", Font.BOLD, 20)
        );
        close.addActionListener( _ -> { _removing(); });
    }

    void _addComp() {
        FaceButton.setVisible(true);
        DocsButton.setVisible(true);
        FaceLabel.setVisible(true);
        DocsLabel.setVisible(true);
        submit.setVisible(true);
        close.setVisible(true);
        header.setVisible(true);

        add(FaceButton);
        add(DocsButton);
        add(FaceLabel);
        add(DocsLabel);
        add(submit);
        add(close);
        add(header);

        S.dummy.add(FaceButton);
        S.dummy.add(DocsButton);
        S.dummy.add(FaceLabel);
        S.dummy.add(DocsLabel);
        S.dummy.add(submit);
        S.dummy.add(close);
        S.dummy.add(header);
    }

    /*//////////////////////////////////////////////////////////////
                             Helper: Remove
    //////////////////////////////////////////////////////////////*/    

    void _removing() {
        blur.removeBlur();
        PanelHelper.clear(this);
        SwingUtilities.invokeLater(() -> {
            try {
                classToInteractWith
                        .getClass()
                        .getMethod("_removeVerify")
                        .invoke(classToInteractWith);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();    
            }
        });
    }

    void _setImage(JButton baseImg) {
        baseImg.addActionListener(
            _ -> { 
                ImageIcon face = ImageUploader.uploadImageIcon();
                if (face != null) baseImg.setIcon(imageSystem._scaleImage(face, 200, 200));
            }
        );
    }

}
