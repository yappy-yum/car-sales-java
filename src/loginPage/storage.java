package loginPage;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;

public class storage {
    
    public boolean login = false;

    class userProfile {
        String fullName;
        String password;
        int age;
        ImageIcon profileImage;
        ImageIcon licenseProof;

        void add(String fullName, String password, int age, ImageIcon profileImage, ImageIcon licenseProof) {
            this.fullName = fullName;
            this.password = password;
            this.age = age;
            this.profileImage = profileImage;
            this.licenseProof = licenseProof;
        }
    }
    
    public HashMap<String, String> Managers = new HashMap<String, String>();
    public HashMap<String, String> Salesmans = new HashMap<String, String>();
    public HashMap<String, String> Customers = new HashMap<String, String>();


    /*//////////////////////////////////////////////////////////////
                            add/registration
    //////////////////////////////////////////////////////////////*/    
    
    public boolean addManager(String username, String password) {
        if (Managers.containsKey(username)) return false;
        if (!isYourPasswordStrong(password)) return false;
        
        Managers.put(username, password);
        return true;
    }
    public boolean addSalesman(String username, String password) {
        if (Salesmans.containsKey(username)) return false;
        if (!isYourPasswordStrong(password)) return false;
        
        Salesmans.put(username, password);
        return true;
    }
    public boolean addCustomer(String username, String password) {
        if (Customers.containsKey(username)) return false;
        if (!isYourPasswordStrong(password)) return false;
        
        Customers.put(username, password);
        return true;
    }

    /*//////////////////////////////////////////////////////////////
                                 login
    //////////////////////////////////////////////////////////////*/    

    public boolean ManagerLogin(String username, String password) {
        return 
            Managers.containsKey(username) && 
            Managers.get(username).equals(password);
    }
    public boolean SalesmanLogin(String username, String password) {
        return 
            Salesmans.containsKey(username) && 
            Salesmans.get(username).equals(password);
    }
    public boolean CustomerLogin(String username, String password) {
        return 
            Customers.containsKey(username) && 
            Customers.get(username).equals(password);
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

        // if (password == null) return false;

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
