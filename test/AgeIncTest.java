import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;

import Helper.login.Profile;
import LoginSystem.schedule;
import LoginSystem.storage;

import org.junit.jupiter.api.BeforeEach;
import framework.ProfileSample;

public class AgeIncTest {
    
    schedule schedule;
    storage storage;

    /*//////////////////////////////////////////////////////////////
                              setUp method
    //////////////////////////////////////////////////////////////*/    

    @BeforeEach
    void setUp() { storage = new storage(); }

    @Test
    void Test_Age_Eventually_Incremented_On_Every_New_Year() throws InterruptedException {

        // declare temporary memory slots which to be used thoughout the test
        Profile.userProfile Customer_Lvy = ProfileSample.Customer.Lvy;
        Profile.CV Salesman_AlanCV = ProfileSample.Salesman.AlanCV;
        Profile.CV Manager_LoeCV = ProfileSample.Manager.LoeCV; 
        
        int Lvy_Age = Customer_Lvy.age;
        int Alan_Age = Salesman_AlanCV.age;
        int Loe_Age = Manager_LoeCV.age;

        System.out.println("Running Test_Age_Eventually_Incremented_On_Every_New_Year: Registering Customer ... ");
        storage.customerRegister(Customer_Lvy);

        System.out.println("Running Test_Age_Eventually_Incremented_On_Every_New_Year: Registering Salesman ... ");
        storage.employeeRegister(Salesman_AlanCV);

        System.out.println("Running Test_Age_Eventually_Incremented_On_Every_New_Year: Registering Manager ... ");
        storage.employeeRegister(Manager_LoeCV);

        System.out.println("Running Test_Age_Eventually_Incremented_On_Every_New_Year: Verifying them ... ");
        storage.setVerified(Customer_Lvy.username);
        storage.setApproval(Salesman_AlanCV.username, true);
        storage.setApproval(Manager_LoeCV.username, true);

        System.out.println("Running Test_Age_Eventually_Incremented_On_Every_New_Year: Verifying their age actually gets incremented ... ");

        Clock setClock = Clock.fixed(
            LocalDateTime.of(2090, 1, 1, 0, 0).toInstant(ZoneOffset.UTC), 
            ZoneId.of("UTC")
        );

        schedule = new schedule(storage::_incAge, setClock);

        Thread.sleep(1000); // wait a moment for the scheduler to run
        assertEquals(storage.Users.get(Customer_Lvy.username).age, Lvy_Age + 1);
        assertEquals(storage.Users.get(Salesman_AlanCV.username).age, Alan_Age + 1);
        assertEquals(storage.Users.get(Manager_LoeCV.username).age, Loe_Age + 1);

    }



}
