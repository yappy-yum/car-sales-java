package LoginSystem;

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
     * help to switch the current login status
     * 
     * <p>
     * 
     * <b> 
     * can only be called when user has successfully login 
     * to their account
     * <b>
     * 
     */
    public void setLogin() {
        isLogin = !isLogin;
    }

}
