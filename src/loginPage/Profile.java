package loginPage;

public class Profile {
    
    public static class userProfile {
        enum Status {
            ADMIN,
            MANAGER,
            SALESMAN,
            CUSTOMER
        }
        Status status;

        String firstName, lastName;
        String username, password;
        String favText, favNum; 

        String gender;
        int age;
        int phoneNumber;

        userProfile(
            Status status, String firstName, String lastName, 
            String gender, int phoneNumber, int age,
            String username, String hashedPassword, 
            String hashedFavText, String hashedFavNum
        ) {
            this.status = status;
            this.firstName = firstName;
            this.lastName = lastName;
            this.gender = gender;
            this.age = age;
            this.phoneNumber = phoneNumber;
            this.username = username;
            this.password = hashedPassword;
            this.favText = hashedFavText;
            this.favNum = hashedFavNum;
        }
    }

}
