package Helper.login;

import javax.swing.ImageIcon;
import javax.swing.text.StyledDocument;

public class Profile {

    public enum Department {
        OWNER,
        MANAGER,
        SALESMAN,
        CUSTOMER
    }

    public enum Approval {
        PENDING,
        APPROVED
    }

    /*//////////////////////////////////////////////////////////////
                              Full Details
    //////////////////////////////////////////////////////////////*/    
    
    public static class userProfile {

        public Department department;
        public boolean isVerified;

        public String firstName, lastName;
        public String username, password;
        public String favText, favNum; 

        public String gender;
        public int age;
        public int phoneNumber;

        public ImageIcon pfp;

        public userProfile(
            Department department, boolean isVerified, 
            String firstName, String lastName, 
            String gender, int phoneNumber, int age,
            String username, String Password, 
            String favText, String favNum, ImageIcon pfp
        ) {
            this.department = department;
            this.isVerified = isVerified;
            this.firstName = firstName;
            this.lastName = lastName;
            this.gender = gender;
            this.age = age;
            this.phoneNumber = phoneNumber;
            this.username = username;
            this.password = Password;
            this.favText = favText;
            this.favNum = favNum;
            this.pfp = pfp;
        }
    }

    /*//////////////////////////////////////////////////////////////
                             Check Details
    //////////////////////////////////////////////////////////////*/    

    public static class seeProfile {
        public Department department;
        public boolean isVerified;

        public String firstName;
        public String lastName;
        public String username;

        public String gender;
        public int age;
        public int phoneNumber;

        public ImageIcon pfp;

        public seeProfile(
            Department department, boolean isVerified, 
            String firstName, String lastName, 
            String gender, int phoneNumber, int age,
            String username, ImageIcon pfp
        ) {
            this.department = department;
            this.isVerified = isVerified;
            this.firstName = firstName;
            this.lastName = lastName;
            this.gender = gender;
            this.age = age;
            this.phoneNumber = phoneNumber;
            this.username = username;
            this.pfp = pfp;
        }

    }

    /*//////////////////////////////////////////////////////////////
                              Employee CV
    //////////////////////////////////////////////////////////////*/    

    public static class CV {
        public Department department;
        public Approval approval;

        public StyledDocument CV;

        public String firstName, lastName;
        public String username, password;
        public String favText, favNum; 

        public String gender;
        public int age;
        public int phoneNumber;

        public CV(
            Department department, Approval approval, StyledDocument CV,
            String firstName, String lastName, 
            String username, String password,
            String favText, String favNum, 
            String gender, int age, int phoneNumber
        ) {
            this.department = department;
            this.approval = approval;
            this.CV = CV;
            this.firstName = firstName;
            this.lastName = lastName;
            this.gender = gender;
            this.age = age;
            this.phoneNumber = phoneNumber;
            this.username = username;
            this.password = password;
            this.favText = favText;
            this.favNum = favNum;
        }
        
    }

    /*//////////////////////////////////////////////////////////////
                            Confidential ID
    //////////////////////////////////////////////////////////////*/
    
    public static class confidential {

        public String hashedFace, hashedID;
        public byte[] encryptedFace, encryptedID;

        public confidential(String hashedFace, String hashedID, byte[] encryptedFace, byte[] encryptedID) {
            this.hashedFace = hashedFace;
            this.hashedID = hashedID;
            this.encryptedFace = encryptedFace;
            this.encryptedID = encryptedID;
        }

    }

}
