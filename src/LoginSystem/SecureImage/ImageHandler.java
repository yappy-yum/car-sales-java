package LoginSystem.SecureImage;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageHandler 
{

    /*//////////////////////////////////////////////////////////////
                               Encryption
    //////////////////////////////////////////////////////////////*/    
    
    /**
     * Encrypt/Hash the Image
     * 
     * @param icon
     * @param password
     * @return
     * @throws Exception
     */
    public 
    static 
    Object[] 
    hashImage
                            (
                                ImageIcon icon, 
                                String password
                            ) 
                            throws Exception 
    {
        // 1. create empty container to store image bytes in memory
        // 2. create blank image with the exact same size as the `icon`
        ByteArrayOutputStream imgBytes = new ByteArrayOutputStream();
        BufferedImage bImage = new BufferedImage
                                (
                                    icon.getIconWidth(), 
                                    icon.getIconHeight(), 
                                    BufferedImage.TYPE_INT_RGB
                                );

        // 3. draw image on the blank image, then throw away
        Graphics2D g = bImage.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();

        // 4. save image in png format
        ImageIO.write(bImage, "png", imgBytes);

        // 5. convert the byte image to byte array
        byte[] imageBytes = imgBytes.toByteArray();

        // 6. hash the image using SHA-256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String hash = Base64.getEncoder().encodeToString(digest.digest(imageBytes));

        // 7. creates 16 bytes of AES key from the password given 
        byte[] keyBytes = new byte[16];
        byte[] pwdBytes = password.getBytes();
        System.arraycopy(pwdBytes, 0, keyBytes, 0, Math.min(pwdBytes.length, 16));
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");

        // 8. encrypt the image
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(imageBytes);

        // 9. Return hash and encrypted bytes
        return new Object[] { hash, encrypted };
    }

    /*//////////////////////////////////////////////////////////////
                               Decryption
    //////////////////////////////////////////////////////////////*/    

    /**
     * get/decrypt the image
     * 
     * @param filePath
     * @param password
     * @return
     * @throws Exception
     * 
     */
    public 
    static 
    ImageIcon 
    getImage
                            (
                                String hash, 
                                String password, 
                                byte[] encryptedData
                            ) 
                            throws Exception 
                            
    {
        // 1. ready AES from the password given to be used for decryption
        byte[] keyBytes = new byte[16];
        byte[] pwdBytes = password.getBytes();
        System.arraycopy(pwdBytes, 0, keyBytes, 0, Math.min(pwdBytes.length, 16));
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");

        // 2. decrypt the image using the AES key
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(encryptedData);

        // 3. verify the password using SHA-256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String newHash = Base64.getEncoder().encodeToString(digest.digest(decrypted));
        if (!newHash.equals(hash)) throw new SecurityException("hash incorrect, you're unauthorized !!!");

        // 4. if password is correct, return the decrypted image
        return new ImageIcon(decrypted);
    }

}
