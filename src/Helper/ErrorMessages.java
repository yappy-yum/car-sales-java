package Helper;

import javax.swing.JLabel;
import Helper.login.loginComp;

public class ErrorMessages {

    /*//////////////////////////////////////////////////////////////
                         Add Inventory Message
    //////////////////////////////////////////////////////////////*/    

    public static final JLabel[] ERROR_MESSAGE_FOR_ADD_CAR = {
        loginComp.createErrorMessage("Car Brand is Blank", 20, 50),
        loginComp.createErrorMessage("Car Full Name is Blank", 20, 50),
        loginComp.createErrorMessage("Car Length is Blank", 20, 50),
        loginComp.createErrorMessage("Car Length is of Wrong Format, hower 'i' for more", 20, 50),
        loginComp.createErrorMessage("Car Horse Power is Blank", 20, 50),
        loginComp.createErrorMessage("Car Horse Power only accepts numbers", 20, 50),
        loginComp.createErrorMessage("Payable From is Blank", 20, 50),
        loginComp.createErrorMessage("Car Cost is Blank", 20, 50),
        loginComp.createErrorMessage("Car Cost only accepts numbers", 20, 50),
        loginComp.createErrorMessage("Car Selling Price is Blank", 20, 50),
        loginComp.createErrorMessage("Car Selling Price only accepts numbers", 20, 50),
        loginComp.createErrorMessage("Car Brand Icon is not inserted", 20, 50),
        loginComp.createErrorMessage("Car Image is not inserted", 20, 50)
    };

    /*//////////////////////////////////////////////////////////////
                            Checks Job Data
    //////////////////////////////////////////////////////////////*/    

    public static final JLabel[] ERROR_MESSAGE_FOR_JOB_CHECKS = {
        loginComp.createErrorMessage("First name and Last name must only contain characters", 20, 50),
        loginComp.createErrorMessage("Username has used", 20, 50),
        loginComp.createErrorMessage("Phone number is not valid", 20, 50),
        loginComp.createErrorMessage("Phone number is used", 20, 50),
        loginComp.createErrorMessage("only the age between 18 - 60", 20, 50),
        loginComp.createErrorMessage("Gender is not selected", 20, 50),
        loginComp.createErrorMessage("'Others' gender is selected, but did not explicitly named your gender", 20, 50),
        loginComp.createErrorMessage("Role is not selected", 20, 50),
        loginComp.createErrorMessage("Password is weak, hower 'i' for more", 20, 50),
        loginComp.createErrorMessage("favourite text must contain only characters", 20, 50),
        loginComp.createErrorMessage("favourite number must contain only numbers", 20, 50),
        loginComp.createErrorMessage("CV is empty", 20, 50)        
    };

    /*//////////////////////////////////////////////////////////////
                       Checks Frst Page Register
    //////////////////////////////////////////////////////////////*/    

    public static final JLabel[] ERROR_MESSAGE_FOR_FIRST_PAGE_REGISTER_CHECKS = {
        loginComp.createErrorMessage("First name and Last name must only contain characters", 20, 50),
        loginComp.createErrorMessage("Username has used", 20, 50),
        loginComp.createErrorMessage("Phone number is not valid", 20, 50),
        loginComp.createErrorMessage("Phone number is used", 20, 50),
        loginComp.createErrorMessage("only the age between 18 - 60", 20, 50)        
    };

    /*//////////////////////////////////////////////////////////////
                      Checks Second Page Register
    //////////////////////////////////////////////////////////////*/
        
    public static final JLabel[] ERROR_MESSAGE_FOR_SECOND_PAGE_REGISTER_CHECKS = {
        loginComp.createErrorMessage("Gender is not selected", 20, 50),
        loginComp.createErrorMessage("'Others' gender is selected, but did not explicitly named your gender", 20, 50),
        loginComp.createErrorMessage("Password is weak, hower 'i' for more", 20, 50),
        loginComp.createErrorMessage("favourite text must contain only characters", 20, 50),
        loginComp.createErrorMessage("favourite number must contain only numbers", 20, 50)        
    };

}
