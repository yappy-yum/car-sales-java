package framework;

import Helper.fileSystem.imageSystem;
import Helper.login.Profile;

public class ProfileSample {

    public class Customer {

        public static Profile.userProfile Kelvin = new Profile.userProfile(
            Profile.Department.CUSTOMER, 
            true, 
            "Kelvin", 
            "Tan", 
            "Male", 
            Integer.parseInt("0123910987"), 
            30, 
            "Kel666", 
            "Kelvin Tan's Password",
            "Kelvin's Favourite Text", 
            "Kelvin's Favourite Number", 
            imageSystem._scaleImage(imageSystem.PROFILE, 50, 50)
        );

        public static Profile.userProfile Lvy = new Profile.userProfile(
            Profile.Department.CUSTOMER, 
            false, 
            "Lvy", 
            "Lum", 
            "Female", 
            Integer.parseInt("0123933389"), 
            27, 
            "LLI", 
            "LLI's Password",
            "LLI's Favourite Text", 
            "LLI's Favourite Number", 
            imageSystem._scaleImage(imageSystem.PROFILE, 50, 50)
        );        

    }

    public class Salesman {
 
        // ----------------- Lily ----------------- //
        public static Profile.userProfile Lily = new Profile.userProfile(
            Profile.Department.SALESMAN, 
            true, 
            "Lily", 
            "Lim", 
            "Female", 
            Integer.parseInt("0193012911"), 
            25, 
            "LL", 
            "LL's Password",
            "LL's Favourite Text", 
            "LL's Favourite Number", 
            imageSystem._scaleImage(imageSystem.PROFILE, 50, 50)
        );
        public static Profile.CV LilyCV = new Profile.CV(
            Lily.department, 
            Profile.Approval.PENDING, 
            new StringToStyledDocument("Lily's CV"), 
            Lily.firstName, 
            Lily.lastName, 
            Lily.username, 
            Lily.password,
            Lily.favText, 
            Lily.favNum, 
            Lily.gender, 
            Lily.age, 
            Lily.phoneNumber
        );

        // ----------------- Alan ----------------- //
        public static Profile.userProfile Alan = new Profile.userProfile(
            Profile.Department.SALESMAN, 
            true, 
            "Alan", 
            "Tan", 
            "Male", 
            Integer.parseInt("0193992428"), 
            30, 
            "Alan", 
            "Alan's Password",
            "Alan's Favourite Text", 
            "Alan's Favourite Number", 
            imageSystem._scaleImage(imageSystem.PROFILE, 50, 50)
        );
        public static Profile.CV AlanCV = new Profile.CV(
            Alan.department, 
            Profile.Approval.PENDING, 
            new StringToStyledDocument("Lily's CV"), 
            Alan.firstName, 
            Alan.lastName, 
            Alan.username, 
            Alan.password,
            Alan.favText, 
            Alan.favNum, 
            Alan.gender, 
            Alan.age, 
            Alan.phoneNumber
        );        

    }

    public class Manager {

        // ----------------- John ----------------- //
        public static Profile.userProfile John = new Profile.userProfile(
            Profile.Department.MANAGER, 
            true, 
            "John", 
            "Doe", 
            "Male", 
            Integer.parseInt("0123456789"), 
            30, 
            "JD", 
            "JD's Password",
            "JD's Favourite Text", 
            "JD's Favourite Number", 
            imageSystem._scaleImage(imageSystem.PROFILE, 50, 50)
        );
        public static Profile.CV JohnCV = new Profile.CV(
            John.department, 
            Profile.Approval.PENDING, 
            new StringToStyledDocument("John's CV"), 
            John.firstName, 
            John.lastName, 
            John.username, 
            John.password,
            John.favText, 
            John.favNum, 
            John.gender, 
            John.age, 
            John.phoneNumber
        );

        // ----------------- Jane ----------------- //
        public static Profile.userProfile Loe = new Profile.userProfile(
            Profile.Department.MANAGER, 
            true, 
            "Loe", 
            "Chong", 
            "Female", 
            Integer.parseInt("0123853711"), 
            40, 
            "Loe", 
            "Loe's Password",
            "Loe's Favourite Text", 
            "Loe's Favourite Number", 
            imageSystem._scaleImage(imageSystem.PROFILE, 50, 50)
        );
        public static Profile.CV LoeCV = new Profile.CV(
            Loe.department, 
            Profile.Approval.PENDING, 
            new StringToStyledDocument("Loe's CV"), 
            Loe.firstName, 
            Loe.lastName, 
            Loe.username, 
            Loe.password,
            Loe.favText, 
            Loe.favNum, 
            Loe.gender, 
            Loe.age, 
            Loe.phoneNumber
        );

    }

}
