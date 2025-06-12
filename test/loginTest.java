import org.junit.jupiter.api.Test;

import javax.swing.ImageIcon;
import Helper.fileSystem.imageSystem;
import Helper.login.Profile;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;

import LoginSystem.storage;
import framework.ImageUtils;
import framework.ProfileSample;
import framework.Pseudorandomness;

public class loginTest {

    storage storage;

    /* ------------------ setUp ------------------ */

    /*//////////////////////////////////////////////////////////////
                              setUp Method
    //////////////////////////////////////////////////////////////*/    

    @BeforeEach
    void setUp() { storage = new storage(); System.setProperty("java.awt.headless", "true"); }

    /* ------------------ Password ------------------ */

    /*//////////////////////////////////////////////////////////////
                          Fail: Empty Password
    //////////////////////////////////////////////////////////////*/    
    
    @Test
    void Test_Fail_With_Empty_Password() { 
        assertFalse(storage.isYourPasswordStrong("")); 
    }

    /*//////////////////////////////////////////////////////////////
                         Fail: Small Letter PWD
    //////////////////////////////////////////////////////////////*/    

    @Test
    void Test_Fail_With_Only_Small_Letters_Password() {
        int MAX_LOOPS = Pseudorandomness.GetRandomNumber(1, 100);

        for (int i = 0; i < MAX_LOOPS; i++) {
            assertFalse(
                storage.isYourPasswordStrong(
                    Pseudorandomness.GetRandomSmallLetters(
                        Pseudorandomness.GetRandomNumber(0, 1000)
                    )
                )
            ); 
        }

        System.out.println("Total Loops Done on Test_Fail_With_Only_Small_Letters_Password: " + MAX_LOOPS);
    }

    /*//////////////////////////////////////////////////////////////
                        Fail: Capital Letter PWD
    //////////////////////////////////////////////////////////////*/    

    @Test
    void Test_Fail_With_Only_Capital_Letters_Password() {
        int MAX_LOOPS = Pseudorandomness.GetRandomNumber(1, 100);

        for (int i = 0; i < MAX_LOOPS; i++) {
            assertFalse(
                storage.isYourPasswordStrong(
                    Pseudorandomness.GetRandomCapitalLetters(
                        Pseudorandomness.GetRandomNumber(0, 1000)
                    )
                )
            ); 
        }

        System.out.println("Total Loops Done on Test_Fail_With_Only_Capital_Letters_Password: " + MAX_LOOPS);
    }

    /*//////////////////////////////////////////////////////////////
                            Fail: Digit PWD
    //////////////////////////////////////////////////////////////*/    

    @Test
    void Test_Fail_With_Only_Digits_Password() {
        int MAX_LOOPS = Pseudorandomness.GetRandomNumber(1, 100);

        for (int i = 0; i < MAX_LOOPS; i++) {
            assertFalse(
                storage.isYourPasswordStrong(
                    Pseudorandomness.GetRandomDigits(
                        Pseudorandomness.GetRandomNumber(0, 1000)
                    )
                )
            ); 
        }

        System.out.println("Total Loops Done on Test_Fail_With_Only_Digits_Password: " + MAX_LOOPS);
    }

    /*//////////////////////////////////////////////////////////////
                            Fail: Symbol PWD
    //////////////////////////////////////////////////////////////*/    

    @Test
    void Test_Fail_With_Only_Symbols_Password() {
        int MAX_LOOPS = Pseudorandomness.GetRandomNumber(1, 100);

        for (int i = 0; i < MAX_LOOPS; i++) {
            assertFalse(
                storage.isYourPasswordStrong(
                    Pseudorandomness.GetRandomSymbols(
                        Pseudorandomness.GetRandomNumber(0, 1000)
                    )
                )
            ); 
        }

        System.out.println("Total Loops Done on Test_Fail_With_Only_Symbols_Password: " + MAX_LOOPS);
    }

    /*//////////////////////////////////////////////////////////////
                            Fail: Spaces PWD
    //////////////////////////////////////////////////////////////*/    

    @Test
    void Test_Fail_With_Only_Space_Password() { 
        int MAX_LOOPS = Pseudorandomness.GetRandomNumber(1, 100);

        for (int i = 0; i < MAX_LOOPS; i++) {
            assertFalse(
                storage.isYourPasswordStrong(
                    Pseudorandomness.GetRandomSpaces(
                        Pseudorandomness.GetRandomNumber(0, 1000)
                    )
                )
            ); 
        }

        System.out.println("Total Loops Done on Test_Fail_With_Only_Space_Password: " + MAX_LOOPS);
    }

    /*//////////////////////////////////////////////////////////////
                       Fail: Spaces & Symbols PWD
    //////////////////////////////////////////////////////////////*/    

    @Test
    void Test_Fail_With_Only_Spaces_And_Symbols_Password() {
        String password = Pseudorandomness.GetRandomSpaces(
                                        Pseudorandomness.GetRandomNumber(
                                            0, 
                                            10
                                        )
                                    ).concat(
                                        Pseudorandomness.GetRandomSymbols(
                                            Pseudorandomness.GetRandomNumber(
                                                0, 
                                                10
                                            )
                                        )
                                    );

        assertFalse(storage.isYourPasswordStrong(password));
        System.out.println("Password Generated from Test_Fail_With_Only_Spaces_And_Symbols_Password: " + password);
    }

    /*//////////////////////////////////////////////////////////////
              Fail: Spaces & Symbols & Capital Letters PWD
    //////////////////////////////////////////////////////////////*/    

    @Test
    void Test_Fail_With_Only_Spaces_And_Symbols_And_Capital_Letters_Password() {
        String password = Pseudorandomness.GetRandomSpaces(
                                        Pseudorandomness.GetRandomNumber(
                                            0, 
                                            10
                                        )
                                    ).concat(
                                        Pseudorandomness.GetRandomSymbols(
                                            Pseudorandomness.GetRandomNumber(
                                                0, 
                                                10
                                            )
                                        )
                                    ).concat(
                                        Pseudorandomness.GetRandomCapitalLetters(
                                            Pseudorandomness.GetRandomNumber(
                                                0, 
                                                10
                                            )
                                        )
                                    );

        assertFalse(storage.isYourPasswordStrong(password));
        System.out.println("Password Generated from Test_Fail_With_Only_Spaces_And_Symbols_And_Capital_Letters_Password: " + password);
    }

    /*//////////////////////////////////////////////////////////////
                        Fail: One Character PWD
    //////////////////////////////////////////////////////////////*/    

    @Test
    void Test_Fail_With_Only_One_Cheracter_For_Each_Bits_Password() {
        String password = Pseudorandomness.GetRandomSmallLetters(1)
                                                .concat(
                                                    Pseudorandomness.GetRandomDigits(1)
                                                ).concat(
                                                    Pseudorandomness.GetRandomCapitalLetters(1)
                                                ).concat(
                                                    Pseudorandomness.GetRandomSpaces(1)
                                                ).concat(
                                                    Pseudorandomness.GetRandomSymbols(1)
                                                );

        assertFalse(storage.isYourPasswordStrong(password));
        System.out.println("Password Generated from Test_Fail_With_Only_One_Cheracter_For_Each_Bits_Password: " + password);
    }

    /*//////////////////////////////////////////////////////////////
                          Success: Strong PWD
    //////////////////////////////////////////////////////////////*/    

    @Test
    void Test_Strong_Password() {
        String password = Pseudorandomness.GetRandomSmallLetters(
                                                Pseudorandomness.GetRandomNumber(
                                                    2, 
                                                    10
                                                )                                        
                                            ).concat(
                                                Pseudorandomness.GetRandomDigits(
                                                    Pseudorandomness.GetRandomNumber(
                                                        2, 
                                                        10
                                                    )
                                                )
                                            ).concat(
                                                Pseudorandomness.GetRandomCapitalLetters(
                                                    Pseudorandomness.GetRandomNumber(
                                                        2, 
                                                        10
                                                    )
                                                )
                                            ).concat(
                                                Pseudorandomness.GetRandomSpaces(
                                                    Pseudorandomness.GetRandomNumber(
                                                        2, 
                                                        10
                                                    )
                                                )
                                            ).concat(
                                                Pseudorandomness.GetRandomSymbols(
                                                    Pseudorandomness.GetRandomNumber(
                                                        2, 
                                                        10
                                                    )
                                                )
                                            );

        assertTrue(storage.isYourPasswordStrong(password));
        System.out.println("Password Generated from Test_Strong_Password: " + password);
    }

    /* ------------------ Username Uniqueness ------------------ */

    /*//////////////////////////////////////////////////////////////
                        Success: Username Unique
    //////////////////////////////////////////////////////////////*/    

    @Test
    void Test_Username_Unique() {
        int MAX_LOOPS = Pseudorandomness.GetRandomNumber(1, 100);

        for (int i = 0; i < MAX_LOOPS; i++) {
            assertTrue(
                storage.isUsernameUnique(
                    Pseudorandomness.GetRandomSmallLetters(
                        Pseudorandomness.GetRandomNumber(0, 1000)
                    )
                )
            );
            assertTrue(
                storage.isUsernameUnique(
                    Pseudorandomness.GetRandomCapitalLetters(1).concat(
                        Pseudorandomness.GetRandomSmallLetters(
                            Pseudorandomness.GetRandomNumber(0, 1000)
                        )
                    )
                )
            );
        }

        System.out.println("Total Loops Done on Test_Username_Unique: " + MAX_LOOPS);
    }

    /* ------------------ Phone Number Uniqueness ------------------ */

    /*//////////////////////////////////////////////////////////////
                           Success: PH Unique
    //////////////////////////////////////////////////////////////*/    

    @Test
    void Test_Phone_Number_Unique() {
        int MAX_LOOPS = Pseudorandomness.GetRandomNumber(1, 100);

        for (int i = 0; i < MAX_LOOPS; i++) {
            assertTrue(
                storage.isPhoneNumberUnique(
                    Integer.parseInt(
                        "01".concat(
                            Pseudorandomness.GetRandomDigits(8)
                        )
                    )
                )
            );
        }

        System.out.println("Total Loops Done on Test_Phone_Number_Unique: " + MAX_LOOPS);
    }

    /* ------------------ All User Profile to be Tested ------------------ */

    /*//////////////////////////////////////////////////////////////
                          Checks Registration
    //////////////////////////////////////////////////////////////*/
    
    @Test 
    void Test_Big_Large_Huge_Registration() {

        // declare temporary memory slots which to be used thoughout the test
        Profile.userProfile Customer_Kelvin = ProfileSample.Customer.Kelvin;

        Profile.userProfile Salesman_Lily = ProfileSample.Salesman.Lily;
        Profile.CV Salesman_LilyCV = ProfileSample.Salesman.LilyCV;

        Profile.userProfile Manager_John = ProfileSample.Manager.John;
        Profile.CV Manager_JohnCV = ProfileSample.Manager.JohnCV;

        // test starting from here
        System.out.println("Running Test_Big_Large_Huge_Registration: Registering Customer ... "); 
        storage.customerRegister(Customer_Kelvin);

        System.out.println("Running Test_Big_Large_Huge_Registration: Registering Salesman ... "); 
        storage.employeeRegister(Salesman_LilyCV);

        System.out.println("Running Test_Big_Large_Huge_Registration: Registering Manager ... "); 
        storage.employeeRegister(Manager_JohnCV);

        System.out.println("Running Test_Big_Large_Huge_Registration: Testing Configuration and settings storage ... ");

        assertTrue(storage.Users.containsKey(Customer_Kelvin.username));
        assertFalse(storage.Users.containsKey(Salesman_Lily.username));
        assertFalse(storage.Users.containsKey(Manager_John.username));

        assertFalse(storage.CustomerVerification.containsKey(Customer_Kelvin.username));
        assertFalse(storage.CustomerVerification.containsKey(Salesman_Lily.username));
        assertFalse(storage.CustomerVerification.containsKey(Manager_John.username));

        assertFalse(storage.Job.containsKey(Customer_Kelvin.username));
        assertTrue(storage.Job.containsKey(Salesman_LilyCV.username));
        assertTrue(storage.Job.containsKey(Manager_JohnCV.username));

        System.out.println("Running Test_Big_Large_Huge_Registration: Approving all the users ... ");

        storage.setVerified(Customer_Kelvin.username);
        storage.setApproval(Salesman_LilyCV.username, true);
        storage.setApproval(Manager_JohnCV.username, true);

        System.out.println("Running Test_Big_Large_Huge_Registration: Testing Configuration and settings storage ... ");

        assertTrue(storage.Users.containsKey(Customer_Kelvin.username));
        assertTrue(storage.Users.containsKey(Salesman_Lily.username));
        assertTrue(storage.Users.containsKey(Manager_John.username));

        assertFalse(storage.CustomerVerification.containsKey(Customer_Kelvin.username));
        assertFalse(storage.CustomerVerification.containsKey(Salesman_Lily.username));
        assertFalse(storage.CustomerVerification.containsKey(Manager_John.username));

        System.out.println("Running Test_Big_Large_Huge_Registration: Checking passwords Stored ... ");

        assertFalse(Customer_Kelvin.password.equals(storage.Users.get(Customer_Kelvin.username).password));
        assertFalse(Customer_Kelvin.favNum.equals(storage.Users.get(Customer_Kelvin.username).favNum));
        assertFalse(Customer_Kelvin.favText.equals(storage.Users.get(Customer_Kelvin.username).favText));

        assertFalse(Salesman_Lily.password.equals(storage.Users.get(Salesman_Lily.username).password));
        assertFalse(Salesman_Lily.favNum.equals(storage.Users.get(Salesman_Lily.username).favNum));
        assertFalse(Salesman_Lily.favText.equals(storage.Users.get(Salesman_Lily.username).favText));

        assertFalse(Manager_John.password.equals(storage.Users.get(Manager_John.username).password));
        assertFalse(Manager_John.favNum.equals(storage.Users.get(Manager_John.username).favNum));
        assertFalse(Manager_John.favText.equals(storage.Users.get(Manager_John.username).favText));

        System.out.println("Running Test_Big_Large_Huge_Registration: Testing login functions ... ");

        assertTrue(storage.login(Customer_Kelvin.username, Customer_Kelvin.password));
        assertTrue(storage.login(Salesman_Lily.username, Salesman_Lily.password));
        assertTrue(storage.login(Manager_John.username, Manager_John.password));

        assertFalse(storage.login(Customer_Kelvin.username, storage.Users.get(Customer_Kelvin.username).password));
        assertFalse(storage.login(Salesman_Lily.username, storage.Users.get(Salesman_Lily.username).password));
        assertFalse(storage.login(Manager_John.username, storage.Users.get(Manager_John.username).password));

    }

    /*//////////////////////////////////////////////////////////////
                           Checks Search User
    //////////////////////////////////////////////////////////////*/    

    @Test
    void Test_Search_User() {

        // declare temporary memory slots which to be used thoughout the test
        Profile.userProfile Customer_Lvy = ProfileSample.Customer.Lvy;

        Profile.userProfile Salesman_Alan = ProfileSample.Salesman.Alan;
        Profile.CV Salesman_AlanCV = ProfileSample.Salesman.AlanCV;

        Profile.userProfile Manager_Loe = ProfileSample.Manager.Loe;
        Profile.CV Manager_LoeCV = ProfileSample.Manager.LoeCV;        

        System.out.println("Running Test_Search_User: Registering Customer ... ");
        storage.customerRegister(Customer_Lvy);

        System.out.println("Running Test_Search_User: Registering Salesman ... ");
        storage.employeeRegister(Salesman_AlanCV);

        System.out.println("Running Test_Search_User: Registering Manager ... ");
        storage.employeeRegister(Manager_LoeCV);

        System.out.println("Running Test_Search_User: Verifying them ... ");

        storage.setVerified(Customer_Lvy.username);
        storage.setApproval(Salesman_AlanCV.username, true);
        storage.setApproval(Manager_LoeCV.username, true);

        System.out.println("Running Test_Search_User: Testing Configuration and settings storage ... ");

        assertTrue(storage.Users.containsKey(Customer_Lvy.username));
        assertTrue(storage.Users.containsKey(Salesman_Alan.username));
        assertTrue(storage.Users.containsKey(Manager_Loe.username));

        Customer_Lvy.isVerified = true;

        assertTrue(storage.Users.get(Customer_Lvy.username).username.equals(Customer_Lvy.username));
        assertTrue(storage.Users.get(Customer_Lvy.username).isVerified == Customer_Lvy.isVerified);
        assertTrue(storage.Users.get(Customer_Lvy.username).firstName.equals(Customer_Lvy.firstName));
        assertTrue(storage.Users.get(Customer_Lvy.username).lastName.equals(Customer_Lvy.lastName));

        assertTrue(storage.Users.get(Salesman_Alan.username).username.equals(Salesman_Alan.username));
        assertTrue(storage.Users.get(Salesman_Alan.username).isVerified == Salesman_Alan.isVerified);
        assertTrue(storage.Users.get(Salesman_Alan.username).firstName.equals(Salesman_Alan.firstName));
        assertTrue(storage.Users.get(Salesman_Alan.username).lastName.equals(Salesman_Alan.lastName));

        assertTrue(storage.Users.get(Manager_Loe.username).username.equals(Manager_Loe.username));
        assertTrue(storage.Users.get(Manager_Loe.username).isVerified == Manager_Loe.isVerified);
        assertTrue(storage.Users.get(Manager_Loe.username).firstName.equals(Manager_Loe.firstName));
        assertTrue(storage.Users.get(Manager_Loe.username).lastName.equals(Manager_Loe.lastName));

        System.out.println("Running Test_Search_User: Testing search methods via usernames ... ");

        assertTrue(storage.searchUser(Customer_Lvy.username).username.equals(Customer_Lvy.username));
        assertTrue(storage.searchUser(Salesman_Alan.username).username.equals(Salesman_Alan.username));
        assertTrue(storage.searchUser(Manager_Loe.username).username.equals(Manager_Loe.username));

        System.out.println("Running Test_Search_User: Testing search methods via phone numbers ... ");

        assertTrue(storage.searchUser(Customer_Lvy.phoneNumber).phoneNumber == Customer_Lvy.phoneNumber);
        assertTrue(storage.searchUser(Salesman_Alan.phoneNumber).phoneNumber == Salesman_Alan.phoneNumber);
        assertTrue(storage.searchUser(Manager_Loe.phoneNumber).phoneNumber == Manager_Loe.phoneNumber);
    }

    /*//////////////////////////////////////////////////////////////
                          Checks Make Changes
    //////////////////////////////////////////////////////////////*/
    
    @Test
    void Test_Make_Changes() {

        // declare temporary memory slots which to be used thoughout the test
        Profile.userProfile Customer_Lvy = ProfileSample.Customer.Lvy;

        Profile.userProfile Salesman_Alan = ProfileSample.Salesman.Alan;
        Profile.CV Salesman_AlanCV = ProfileSample.Salesman.AlanCV;

        Profile.userProfile Manager_Loe = ProfileSample.Manager.Loe;
        Profile.CV Manager_LoeCV = ProfileSample.Manager.LoeCV;   

        System.out.println("Running Test_Make_Changes: Registering Customer ... ");
        storage.customerRegister(Customer_Lvy);

        System.out.println("Running Test_Make_Changes: Registering Salesman ... ");
        storage.employeeRegister(Salesman_AlanCV);

        System.out.println("Running Test_Make_Changes: Registering Manager ... ");
        storage.employeeRegister(Manager_LoeCV);

        System.out.println("Running Test_Make_Changes: Verifying them ... ");

        storage.setVerified(Customer_Lvy.username);
        storage.setApproval(Salesman_AlanCV.username, true);
        storage.setApproval(Manager_LoeCV.username, true);

        System.out.println("Running Test_Make_Changes: Testing make Changes ... ");

        storage.changeDetails(
                Customer_Lvy.username, 
                "Lvy's new username", 
                "12345poqiw0", 
                "Hello World", 
                6969, 
                Integer.parseInt("0129109074")
            );
        storage.changeDetails(
                Salesman_Alan.username, 
                "Alan's new username", 
                "12345poqiw0", 
                "Hello World", 
                6969, 
                Integer.parseInt("0120003174")
            );
        storage.changeDetails(
                Manager_Loe.username, 
                "Loe's new username", 
                "12345poqiw0", 
                "Hello World", 
                6969, 
                Integer.parseInt("0199009321")
            );

        System.out.println("Running Test_Make_Changes: Testing Updated Data ... ");

        // Lvy
        assertFalse(storage.Users.containsKey(Customer_Lvy.username));
        assertTrue(storage.Users.get("Lvy's new username").username.equals("Lvy's new username"));
        assertTrue(storage.Users.get("Lvy's new username").phoneNumber == Integer.parseInt("0129109074"));

        // Alan
        assertFalse(storage.Users.containsKey(Salesman_Alan.username));
        assertTrue(storage.Users.get("Alan's new username").username.equals("Alan's new username"));
        assertTrue(storage.Users.get("Alan's new username").phoneNumber == Integer.parseInt("0120003174"));

        // Loe
        assertFalse(storage.Users.containsKey(Manager_Loe.username));
        assertTrue(storage.Users.get("Loe's new username").username.equals("Loe's new username"));
        assertTrue(storage.Users.get("Loe's new username").phoneNumber == Integer.parseInt("0199009321"));

    }

    /*//////////////////////////////////////////////////////////////
                   Test Image Decryption & Encryption
    //////////////////////////////////////////////////////////////*/
    
    @Test
    void Test_Image_Encryption_Decryption() {
        System.setProperty("java.awt.headless", "true");

        ImageIcon RandomImage_1 = imageSystem.AUDI;
        ImageIcon RandomImage_2 = imageSystem.BMW_I9;

        ImageIcon RandomImage_3 = imageSystem.FERRARI;
        ImageIcon RandomImage_4 = imageSystem.BUGATTI_VEYRON;

        System.out.println("Running Test_Image_Encryption_Decryption: Testing Encryption ... ");

        storage._encryptImage("Bob", RandomImage_1, RandomImage_2);
        storage._encryptImage("John", RandomImage_3, RandomImage_4);

        System.out.println("Running Test_Image_Encryption_Decryption: Testing Decryption ... ");

        ImageIcon decrypted1 = storage._decryptFaceImage("Bob", storage.dotenv.get("DECRYPT_PASSWORD"));
        ImageIcon decrypted2 = storage._decryptDocsImage("Bob", storage.dotenv.get("DECRYPT_PASSWORD"));
        ImageIcon decrypted3 = storage._decryptFaceImage("John", storage.dotenv.get("DECRYPT_PASSWORD"));
        ImageIcon decrypted4 = storage._decryptDocsImage("John", storage.dotenv.get("DECRYPT_PASSWORD"));

        assertArrayEquals(ImageUtils.imageIconToBytes(RandomImage_1, "jpeg"), ImageUtils.imageIconToBytes(decrypted1, "jpeg"));
        assertArrayEquals(ImageUtils.imageIconToBytes(RandomImage_2, "jpeg"), ImageUtils.imageIconToBytes(decrypted2, "jpeg"));
        assertArrayEquals(ImageUtils.imageIconToBytes(RandomImage_3, "jpeg"), ImageUtils.imageIconToBytes(decrypted3, "jpeg"));
        assertArrayEquals(ImageUtils.imageIconToBytes(RandomImage_4, "jpeg"), ImageUtils.imageIconToBytes(decrypted4, "jpeg"));

    }




}
    
