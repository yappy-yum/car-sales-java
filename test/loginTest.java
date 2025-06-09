import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import LoginSystem.storage;

public class loginTest {

    storage storage = new storage();
    
    @Test
    void Test_Fail_With_Empty_Password() { 
        assertFalse(storage.isYourPasswordStrong("")); 
    }

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


}
    
