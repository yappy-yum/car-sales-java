package loginPage.Argon2;

import java.util.HashMap;
import java.util.Scanner;

/**
 * <p> A sample class to test the Argon2 hashing algorithm on a simple login system. 
 * 
 * <p> A plain yet simple login system with username and password, using Argon2 hashing, and
 *     HashMap to store the user data
 * 
 * <p> everything will be done solely on the command line, with no JFrame or GUI
 * 
 */
public class testArgon {

    static HashMap<String, String> UserProfile = new HashMap<>();
    static Argon.Hash argon = new Argon().new Hash();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.print("Register or Login? (R/L): ");
            String decision = scanner.nextLine();

            // Register
            if (decision.equals("R")) {
                _register();
                continue;
            } 
            
            // Login
            if (decision.equals("L")) {
                _login();
                continue;
            } 
            
            // Invalid
            else {
                System.out.println("Invalid input. Type R or L.\n");
            }
        }

    }

    static void _register() {

        // 1. input Username
        System.out.print("Username: ");
        String username = scanner.nextLine();

        // 2. ensures username must be unique
        if (UserProfile.containsKey(username)) {
            System.out.println("Username already exists.");
            return;
        }

        // 3. input Password
        System.out.print("Password: ");
        String password = scanner.nextLine();

        // 4. Hash password
        String hashedPassword = argon.HashIt(password);
        System.out.println("Hashed password: " + hashedPassword);

        // 5. Add to HashMap
        UserProfile.put(username, hashedPassword);
        System.out.println("Registration successful." + "\n");

    }

    static void _login() {

        // 1. input Username
        System.out.print("Username: ");
        String username = scanner.nextLine();

        // 2. ensures username must exist
        if (!UserProfile.containsKey(username)) {
            System.out.println("Username does not exist." + "\n");
            return;
        }

        // 3. input Password
        System.out.print("Password: ");
        String password = scanner.nextLine();

        // 4. get the correct hashed password from HashMap and verify it
        String hashedPassword = UserProfile.get(username);
        if (argon.verify(password, hashedPassword)) {
            System.out.println("Login successful." + "\n");
        } else {
            System.out.println("password incorrect, login failed." + "\n");
        }

    }
}
