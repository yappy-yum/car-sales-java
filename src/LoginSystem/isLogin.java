package LoginSystem;

import Helper.login.Profile;

/**
 * a global centralize class that determine the login status
 * 
 * @author yappy-yum
 * 
 */
public class isLogin {
    
    /**
     * 
     * by deafult, the login status is false (not login)
     * 
     */
    public boolean isLogin = false;

    /**
     * 
     * current login profile, which will be displayed on window
     * when someone checks the profile information
     * 
     */
    public Profile.seeProfile currentProfile;

}
