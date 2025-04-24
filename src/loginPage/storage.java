package loginPage;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import loginPage.Argon2.Argon;

public class storage {

    Argon.Hash argon = new Argon().new Hash();

    /*//////////////////////////////////////////////////////////////
                             users details
    //////////////////////////////////////////////////////////////*/    
    
    public HashMap<String, Profile.userProfile> Users = new HashMap<String, Profile.userProfile>();

    /*//////////////////////////////////////////////////////////////
                              registration
    //////////////////////////////////////////////////////////////*/
     
    public boolean register(Profile.userProfile profile) {
        checkUniqueness isUnique = new checkUniqueness(profile.username, profile.phoneNumber);

        if (!isUnique.username) return false;
        if (!isUnique.phoneNumber) return false;
        if (!isYourPasswordStrong(profile.password)) return false;

        String hashedPassword = argon.HashIt(profile.password);
        String hashedFavText = argon.HashIt(profile.favText);
        String hashedFavNum = argon.HashIt(profile.favNum);

        Users.put(
            profile.username, 
            new Profile.userProfile(
                profile.status, profile.firstName, profile.lastName, 
                profile.gender, profile.phoneNumber, profile.age,
                profile.username, hashedPassword, 
                hashedFavText, hashedFavNum
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
     * @param gender gender of the user
     * @param phoneNumber phone number of the user
     * @param IC IC number of the user (input plain integer, not hashed)
     * @param favText favourite text of the user (input plain text, not hashed)
     * @param favNum favourite number of the user (input plain integer, not hashed)
     * @return true if login is successful
     * 
     */
    public boolean login(
        String firstName, String lastName, 
        String gender, int phoneNumber, int IC, 
        String favText, int favNum
    ) {
        return 
            Users.values().stream().anyMatch(
                profile ->
                    profile.firstName.equals(firstName) &&
                    profile.lastName.equals(lastName) &&
                    profile.gender.equals(gender) &&
                    profile.phoneNumber == phoneNumber &&
                    argon.verify(favText, profile.favText) &&
                    argon.verify(String.valueOf(favNum), profile.favNum)
            );
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
     * @return true if change is successful
     * 
     */
    public boolean changeDetails(
        String oldUsername, String newUsername,
        String newPassword,
        String newFavText, int newFavNum,
        int newPhoneNumber
    ) {
        Profile.userProfile profile = Users.get(oldUsername);
        Users.remove(oldUsername);

        checkUniqueness isUnique = new checkUniqueness(newUsername, newPhoneNumber);

        if (!isUnique.username) return false;
        if (!isUnique.phoneNumber) return false;
        if (!isYourPasswordStrong(newPassword)) return false;

        String hashedPassword = argon.HashIt(newPassword);
        String hashedFavText = argon.HashIt(newFavText);
        String hashedFavNum = argon.HashIt(newFavNum);

        Users.put(
            newUsername, 
            new Profile.userProfile(
                profile.status, profile.firstName, profile.lastName, 
                profile.gender, newPhoneNumber, profile.age,
                newUsername, hashedPassword, 
                hashedFavText, hashedFavNum
            )
        );
        return true;
    }

    /*//////////////////////////////////////////////////////////////
                        check profile uniqueness
    //////////////////////////////////////////////////////////////*/    

    /**
     * this sub-class responsible for checking if a user information, such 
     * as username, and phone number, are unique and has not been used
     * 
     */
    class checkUniqueness { 
        boolean username;
        boolean phoneNumber;

        checkUniqueness(String username, int phoneNumber) {
            if (username != null) this.username = !Users.containsKey(username);
            if (phoneNumber != 0) this.phoneNumber = Users.values().stream().noneMatch(profile -> profile.phoneNumber == phoneNumber);
        }
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
    protected boolean isYourPasswordStrong(String password) {

        String twoCapitals = "(.*[A-Z].*[A-Z])";  // capital letters
        String twoLowercase = "(.*[a-z].*[a-z])"; // lowercase letters
        String twoNumbers = "(.*\\d.*\\d)";       // numbers
        String twoSymbols = "(.*[^a-zA-Z0-9].*[^a-zA-Z0-9])"; // symbols
        String twoSpaces = "(.*\\s.*\\s)";        // spaces

        Matcher Capitals = Pattern.compile(twoCapitals).matcher(password);
        Matcher Lowercase = Pattern.compile(twoLowercase).matcher(password);
        Matcher Numbers = Pattern.compile(twoNumbers).matcher(password);
        Matcher Symbols = Pattern.compile(twoSymbols).matcher(password);
        Matcher Spaces = Pattern.compile(twoSpaces).matcher(password);

        return 
        Capitals.matches() &&
        Lowercase.matches() &&
        Numbers.matches() &&
        Symbols.matches() &&
        Spaces.matches();

    }    


}