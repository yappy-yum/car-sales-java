package LoginSystem;

import javax.swing.JTextField;

public class Profile {
    
    public static class userProfile {
        public enum Status {
            ADMIN,
            MANAGER,
            SALESMAN,
            CUSTOMER
        }
        public Status status;

        public String firstName, lastName;
        public String username, password;
        public String favText, favNum; 

        public String gender;
        public int age;
        public int phoneNumber;

        public userProfile(
            Status status, String firstName, String lastName, 
            String gender, int phoneNumber, int age,
            String username, String Password, 
            String favText, String favNum
        ) {
            this.status = status;
            this.firstName = firstName;
            this.lastName = lastName;
            this.gender = gender;
            this.age = age;
            this.phoneNumber = phoneNumber;
            this.username = username;
            this.password = Password;
            this.favText = favText;
            this.favNum = favNum;
        }
    }

    public static class CV {
        public enum Status {
            MANAGER,
            SALESMAN
        }
        public Status status;

        public enum Approval {
            PENDING,
            APPROVED
        }
        public Approval approval;

        public JTextField CV;

        public String firstName, lastName;
        public String username, password;
        public String favText, favNum; 

        public String gender;
        public int age;
        public int phoneNumber;

        public CV(
            Status status, Approval approval, JTextField CV,
            String firstName, String lastName, 
            String username, String password,
            String favText, String favNum, 
            String gender, int age, int phoneNumber
        ) {
            this.status = status;
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

}
