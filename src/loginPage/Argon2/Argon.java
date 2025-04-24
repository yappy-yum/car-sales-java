package loginPage.Argon2;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

/**
 * 
 * this is a little helper class that hash the private information
 * 
 */
public class Argon {
    
    /**
     * @dev
     * <p> iteration:
     * 
     * <p> - determines the computational complexity of the hash
     * <p> - each iteration performs the same computation sequence again
     * <p> - the more the better, but may take longer time
     * <p> - prevent brute-force attacks
     * 
     */
    protected final int ITERATION = 10;

    /**
     * @dev
     * <p> memory:
     * 
     * <p> - determines RAM usage (KB) used by the algorithm
     * <p> - increase the difficulty of rainbow table attack, and 
     *       GPU-based attack
     * 
     */    
    protected final int MEMORY = 80000;

    /**
     * @dev
     * <p> parallelism:
     * 
     * <p> - determines the number of threads (CPU cores) used by the algorithm
     * 
     */    
    protected final int PARALLELISM = 1;

    /*//////////////////////////////////////////////////////////////
                             get hash here
    //////////////////////////////////////////////////////////////*/
     
    /**
     * enter this sub-class to hash the confidential information using 
     * {@link #HashIt}, and verify the password using {@link #verify}
     * 
     */
    public class Hash {
        Argon2 argon = Argon2Factory.create();

        public String HashIt(String text) {
            return _hash(text);
        }
        
        public String HashIt(int integer) {
            return _hash(
                String.valueOf(integer)
            );
        }

        /**
         * verify the login credentials 
         * 
         * @param password user's input password login attempt text (not hashed)
         * @param hash user's real hashed password during the registration
         * @return password validation in boolean
         * 
         */
        public boolean verify(String password, String hash) {
            return argon.verify(
                hash, 
                password.toCharArray()
            );
        }
    
        /**
         * hash the text
         * 
         * @param _text texts to be hashed
         * @return hashed text
         * 
         */
        protected String _hash(String _text) {    
            return argon.hash(  
                ITERATION, 
                MEMORY, 
                PARALLELISM, 
                _text.toCharArray()
            );
        }
    }        

}
