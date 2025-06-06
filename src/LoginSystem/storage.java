package LoginSystem;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Helper.fileSystem.imageSystem;
import Helper.login.Profile;
import LoginSystem.Argon2.Argon;
import LoginSystem.SecureImage.ImageHandler;
import io.github.cdimascio.dotenv.Dotenv;

public class storage {

    Argon.Hash argon = new Argon().new Hash();
    Dotenv dotenv = Dotenv.load();

    /*//////////////////////////////////////////////////////////////
                              constructor
    //////////////////////////////////////////////////////////////*/
    
    public storage() {
        Users.put(
            dotenv.get("OWNER_USERNAME"), 
            new Profile.userProfile(
                Profile.Department.OWNER, 
                true,
                null, 
                null, 
                null, 
                0, 
                0,
                dotenv.get("OWNER_USERNAME"), 
                argon.HashIt(dotenv.get("OWNER_PASSWORD")), 
                null, 
                null, 
                imageSystem._scaleImage(imageSystem.PROFILE, 50, 50)
            )
        );
    }

    /*//////////////////////////////////////////////////////////////
                             users details
    //////////////////////////////////////////////////////////////*/    
    
    /**
     * store all the users detail
     */
    public HashMap<String, Profile.userProfile> Users = new HashMap<String, Profile.userProfile>();

    /**
     * stores all the car a customer has bought, including booked
     * 
     * @apiNote
     * when customer account is deleted, this HashMap wont get deleted, but instead at <code>(deleted)</code>
     * right after this username 
     * 
     */
    public HashMap<String, Profile.CarHolder> CustomersCarHolder = new HashMap<String, Profile.CarHolder>();

    /**
     * store all the confidential of the customer, such as docs id and face for verification
     * 
     * <p> <b>
     * 
     * will gets deleted when verification successfull
     * 
     */
    public HashMap<String, Profile.confidential> CustomerVerification = new HashMap<String, Profile.confidential>();

    /**
     * store all the CV details for salesman and manager
     * 
     * <p>
     * 
     * when Admin has approve the Profile.CVpending.approval, all the informations will 
     * automatically moved to the `Users` HashMap, meaning they can directly login
     *
     */
    public HashMap<String, Profile.CV> Job = new HashMap<String, Profile.CV>();

    /*//////////////////////////////////////////////////////////////
                              registration
    //////////////////////////////////////////////////////////////*/
     
    /**
     * registration only open for customer, salesman and manager will require an approval
     * from the admin by having a CV attached and perhaps an interview conducted. The 
     * approval will be made by the admin, therefore if one's approved, can straight away
     * login, rather than going through the registration process at first
     * 
     * @param profile user's information
     * @return true if registration is successful
     * 
     */
    public boolean customerRegister(Profile.userProfile user) {
        String hashedPassword = argon.HashIt(user.password);
        String hashedFavText = argon.HashIt(user.favText);
        String hashedFavNum = argon.HashIt(user.favNum);

        Users.put(
            user.username, 
            new Profile.userProfile(
                user.department, false, user.firstName, user.lastName, 
                user.gender, user.phoneNumber, user.age,
                user.username, hashedPassword, 
                hashedFavText, hashedFavNum, 
                imageSystem._scaleImage(imageSystem.PROFILE, 50, 50)
            )
        );
        return true;
    }

    public boolean employeeRegister(Profile.CV employee) {
        String hashedPassword = argon.HashIt(employee.password);
        String hashedFavText = argon.HashIt(employee.favText);
        String hashedFavNum = argon.HashIt(employee.favNum);

        Job.put(
            employee.username, 
            new Profile.CV(
                employee.department, employee.approval, 
                employee.CV, employee.firstName, employee.lastName, 
                employee.username, hashedPassword,
                hashedFavText, hashedFavNum, 
                employee.gender, employee.age, employee.phoneNumber
            )
        );
        return true;
    }

    /*//////////////////////////////////////////////////////////////
                                 login
    //////////////////////////////////////////////////////////////*/    
    
    /**
     * normal login using username and password
     * 
     * @param username username of the user 
     * @param password password of the user (input plain text, not hashed)
     * @return true if login is successful
     * 
     */
    public boolean login(String username, String password) {
        if (!Users.containsKey(username)) return false;

        return argon.verify(
            password, 
            Users.get(username).password
        );
    }

    /**
     * login using all the users information without using username and password.
     * 
     * @apiNote
     * verification may take longer time due to the number of hash
     * comparisons needed, including IC, favourite text, and favourite number
     * 
     * @param firstName first name of the user 
     * @param lastName last name of the user
     * @param phoneNumber phone number of the user
     * @param IC IC number of the user (input plain integer, not hashed)
     * @param favText favourite text of the user (input plain text, not hashed)
     * @param favNum favourite number of the user (input plain integer, not hashed)
     * @return true if login is successful
     * 
     */
    public boolean login(
        int phoneNumber,
        String favText, String favNum
    ) {
        return 
            Users
                .values()
                .stream()
                .anyMatch(
                    profile ->
                        profile.phoneNumber == phoneNumber &&
                        argon.verify(favText, profile.favText) &&
                        argon.verify(String.valueOf(favNum), profile.favNum)
                );
    }

    /*//////////////////////////////////////////////////////////////
                            verify customer
    //////////////////////////////////////////////////////////////*/
    
    public void setVerified(String username) { 
        Users.get(username).isVerified = true;

        CustomersCarHolder.put(
            username, 
            new Profile.CarHolder(
                username, 
                null, 
                null
            )
        );
        
        rejectVerification(username);
    }

    public void rejectVerification(String username) { 
        CustomerVerification.remove(username); 
    }

    /*//////////////////////////////////////////////////////////////
                             change details
    //////////////////////////////////////////////////////////////*/
    
    /**
     * edit user details
     * 
     * @param oldUsername user's old username
     * @param newUsername user's new username
     * @param newPassword user's new password
     * @param newFavText user's new favourite text
     * @param newFavNum user's new favourite number
     * @param newPhoneNumber user's new phone number
     * 
     */
    public void changeDetails(
        String oldUsername, String newUsername,
        String newPassword,
        String newFavText, int newFavNum,
        int newPhoneNumber
    ) {
        Profile.userProfile profile = Users.get(oldUsername);
        Users.remove(oldUsername);

        String hashedPassword = argon.HashIt(newPassword);
        String hashedFavText = argon.HashIt(newFavText);
        String hashedFavNum = argon.HashIt(newFavNum);

        Users.put(
            newUsername, 
            new Profile.userProfile(
                profile.department, profile.isVerified, profile.firstName, profile.lastName, 
                profile.gender, newPhoneNumber, profile.age,
                newUsername, hashedPassword, 
                hashedFavText, hashedFavNum, profile.pfp
            )
        );
    }

    /*//////////////////////////////////////////////////////////////
                              job approval
    //////////////////////////////////////////////////////////////*/    
     
    public void setApproval(String username, boolean approval) {
        Profile.CV job = Job.get(username);
        
        if (approval) Users.put(
                            username, 
                            new Profile.userProfile(
                                job.department, true,
                                job.firstName, job.lastName, job.gender, job.phoneNumber, job.age,
                                username, job.password,
                                job.favText, job.favNum, 
                                imageSystem._scaleImage(imageSystem.PROFILE, 50, 50)
                            )
                        );
        Job.remove(username);
    }

    /*//////////////////////////////////////////////////////////////
                                set PFP
    //////////////////////////////////////////////////////////////*/
    
    public void setPFP(String username, ImageIcon pfp) { Users.get(username).pfp = pfp; }

    /*//////////////////////////////////////////////////////////////
                        check profile uniqueness
    //////////////////////////////////////////////////////////////*/   
     
    public boolean isUsernameUnique(String username) {
        return 
            !Users.containsKey(username) &&
            !Job.containsKey(username);
    }

    public boolean isPhoneNumberUnique(int phoneNumber) {
        return 
            Users.values().stream().noneMatch(profile -> profile.phoneNumber == phoneNumber) &&
            Job.values().stream().noneMatch(profile -> profile.phoneNumber == phoneNumber);
    }

    /*//////////////////////////////////////////////////////////////
                              Remove User
    //////////////////////////////////////////////////////////////*/    
    
    public void removeUser(String username) { 
        Users.remove(username);

        if (CustomersCarHolder.containsKey(username)) {
            
            if (CustomersCarHolder.get(username).CarId.size() > 0 && CustomersCarHolder.get(username).CarName.size() > 0) {
                Profile.CarHolder dummy = CustomersCarHolder.get(username);
                dummy.Username.concat( " (deleted)" );

                
                CustomersCarHolder.put(
                    username.concat(" (deleted)"), 
                    dummy
                );
            }
            
            CustomersCarHolder.remove(username);
        }

        if (CustomerVerification.containsKey(username)) _deleteConfidential(username);
    }

    /*//////////////////////////////////////////////////////////////
                              Search User
    //////////////////////////////////////////////////////////////*/
    
    public Profile.seeProfile searchUser(String username) {
        Profile.userProfile profile = Users.get(username);
        Profile.seeProfile seeProfile = new Profile.seeProfile(
            profile.department, profile.isVerified, 
            profile.firstName, profile.lastName, 
            profile.gender, profile.phoneNumber, profile.age,
            profile.username, profile.pfp
        );
        return seeProfile;
    }

    public Profile.seeProfile searchUser(int phoneNumber) {
        Profile.userProfile profile = Users.values().stream().filter(i -> i.phoneNumber == phoneNumber).findFirst().get();
        Profile.seeProfile seeProfile = new Profile.seeProfile(
            profile.department, profile.isVerified, 
            profile.firstName, profile.lastName, 
            profile.gender, profile.phoneNumber, profile.age,
            profile.username, profile.pfp
        );
        return seeProfile;
    }

    /*//////////////////////////////////////////////////////////////
                             password rules
    //////////////////////////////////////////////////////////////*/
     
    /**
     * a helper method to check if the password is strong. A strong password
     * should have adhere to all the following features:
     * 
     * <p> at least 2 capital letters </p>
     * <p> at least 2 lowercase letters </p>
     * <p> at least 2 numbers </p>
     * <p> at least 2 symbols </p>
     * <p> at least 2 empty spaces </p>
     * 
     * @param password
     * @return true if the password is strong, false otherwise
     * 
     */
    public boolean isYourPasswordStrong(String password) {

        String twoCapitals = "(.*[A-Z].*[A-Z])";       // two capital letters
        String twoLowercase = "(.*[a-z].*[a-z])";      // two lowercase letters
        String twoNumbers = "(.*\\d.*\\d)";            // two digits
        String twoSymbols = "(.*[^a-zA-Z0-9].*[^a-zA-Z0-9])"; // two symbols
        String twoSpaces = "(.*\\s.*\\s)";             // two spaces
    
        Matcher Capitals = Pattern.compile(twoCapitals).matcher(password);
        Matcher Lowercase = Pattern.compile(twoLowercase).matcher(password);
        Matcher Numbers = Pattern.compile(twoNumbers).matcher(password);
        Matcher Symbols = Pattern.compile(twoSymbols).matcher(password);
        Matcher Spaces = Pattern.compile(twoSpaces).matcher(password);
    
        return 
            Capitals.find() &&
            Lowercase.find() &&
            Numbers.find() &&
            Symbols.find() &&
            Spaces.find();
    }

    /*//////////////////////////////////////////////////////////////
                                New Year
    //////////////////////////////////////////////////////////////*/    
    
    public void _incAge() {
        for (Profile.userProfile profile : Users.values()) profile.age++;
        for (Profile.CV profile : Job.values()) profile.age++;
    }

    /*//////////////////////////////////////////////////////////////
                           Encrypt & Decrypt
    //////////////////////////////////////////////////////////////*/    

    public void _encryptImage(String username, ImageIcon Face, ImageIcon DocID) {

        try {

            Object[] hashedFace = ImageHandler.hashImage(Face, dotenv.get("DECRYPT_PASSWORD"));
            Object[] hashedDocID = ImageHandler.hashImage(DocID, dotenv.get("DECRYPT_PASSWORD"));
            CustomerVerification.put(
                username, 
                new Profile.confidential(
                    (String) hashedFace[0], 
                    (String) hashedDocID[0], 
                    (byte[]) hashedFace[1], 
                    (byte[]) hashedDocID[1]
                )
            );

        } 
        catch (Exception o) {

            o.printStackTrace();
            JOptionPane.showMessageDialog(
                null, 
                "Error encrypting image: " + o.getMessage()
            );

        }

    }

    public ImageIcon _decryptFaceImage(String username, String password) {

        try {
            
            Profile.confidential profile = CustomerVerification.get(username);

            String hash = profile.hashedFace;
            byte[] encrypted = profile.encryptedFace;

            return ImageHandler.getImage(hash, password, encrypted);

        }
        catch (Exception o) {

            o.printStackTrace();
            JOptionPane.showMessageDialog(
                null, 
                "Error decrypting face image: " + o.getMessage()
            );
            return null;

        }

    }

    public ImageIcon _decryptDocsImage(String username, String password) {

        try {
            
            Profile.confidential profile = CustomerVerification.get(username);

            String hash = profile.hashedID;
            byte[] encrypted = profile.encryptedID;

            return ImageHandler.getImage(hash, password, encrypted);

        }
        catch (Exception o) {

            o.printStackTrace();
            JOptionPane.showMessageDialog(
                null, 
                "Error decrypting face image: " + o.getMessage()
            );
            return null;

        }

    }

    /*//////////////////////////////////////////////////////////////
                        Delete User Confidential
    //////////////////////////////////////////////////////////////*/    

    public void _deleteConfidential(String username) 
    {
        CustomerVerification.remove(username);
    }


}