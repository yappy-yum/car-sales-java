package LoginSystem;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;

import Helper.fileSystem.imageSystem;
import Helper.login.Profile;
import LoginSystem.Argon2.Argon;
import io.github.cdimascio.dotenv.Dotenv;

public class storage {

    Argon.Hash argon = new Argon().new Hash();

    /*//////////////////////////////////////////////////////////////
                              constructor
    //////////////////////////////////////////////////////////////*/
    
    public storage() {
        Dotenv dotenv = Dotenv.load();

        Users.put(
            dotenv.get("OWNER_USERNAME"), 
            new Profile.userProfile(
                Profile.Department.OWNER, true,
                null, null, null, 0, 0,
                dotenv.get("OWNER_USERNAME"), 
                argon.HashIt(dotenv.get("OWNER_PASSWORD")), 
                null, null, 
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
            Users.values().stream().anyMatch(
                profile ->
                    profile.phoneNumber == phoneNumber &&
                    argon.verify(favText, profile.favText) &&
                    argon.verify(String.valueOf(favNum), profile.favNum)
            );
    }

    /*//////////////////////////////////////////////////////////////
                            verify customer
    //////////////////////////////////////////////////////////////*/
    
    public void setVerified(String username) { Users.get(username).isVerified = true; }

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
        
        if (approval) {    
            Users.put(
                username, 
                new Profile.userProfile(
                    job.department, true,
                    job.firstName, job.lastName, job.gender, job.phoneNumber, job.age,
                    username, job.password,
                    job.favText, job.favNum, null
                )
            );
        } else if (!approval) {
            Users.remove(username);
        }
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
    


}