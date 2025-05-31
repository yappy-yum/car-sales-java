import java.util.Random;

public class Pseudorandomness {

    /*//////////////////////////////////////////////////////////////
                             Small Letters
    //////////////////////////////////////////////////////////////*/    
    
    /**
     * get <code>_amount</code> amount of random small letters (Ex: abc....)
     * 
     * @param _amount amount of small letters to be generated
     * @return geenrated <code>_amount</code> amount of small letters
     * 
     */
    public static String GetRandomSmallLetters(int _amount) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < _amount; i++) {
            result.append(
                (char) 
                ('a' + (int)(Math.random() * 26))
            );
        }

        return result.toString();
    }

    /*//////////////////////////////////////////////////////////////
                            Capital Letters
    //////////////////////////////////////////////////////////////*/    

    /**
     * get <code>_amount</code> amount of random capital letters (Ex: ABC...)
     * 
     * @param _amount amount of capital letters to be generated
     * @return geenrated <code>_amount</code> amount of capital letters
     * 
     */
    public static String GetRandomCapitalLetters(int _amount) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < _amount; i++) {
            result.append(
                (char) 
                ('A' + (int)(Math.random() * 26))
            );
        }

        return result.toString();
    }    

    /*//////////////////////////////////////////////////////////////
                                 Digits
    //////////////////////////////////////////////////////////////*/    

    /**
     * get <code>_amount</code> amount of random digits (Ex: 123...)
     * 
     * @param _amount amount of digits
     * @return geenrated <code>_amount</code> amount of digits
     * 
     */
    public static String GetRandomDigits(int _amount) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < _amount; i++) {
            result.append(
                (char) 
                ('0' + (int)(Math.random() * 10))
            );
        }

        return result.toString();
    }

    /*//////////////////////////////////////////////////////////////
                                Symbols
    //////////////////////////////////////////////////////////////*/    

    /**
     * get <code>_amount</code> amount of random symbols (Ex: ~!@#$...)
     * 
     * @param _amount amount of symbols
     * @return geenrated <code>_amount</code> amount of symbols
     * 
     */
    public static String GetRandomSymbols(int _amount) {
        String symbols = "`~!@#$%^&*()-_=+[{]}\\|'\";:/?.>,<";
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < _amount; i++) {
            result.append(
                symbols.charAt(
                    (int)
                    (Math.random() * symbols.length())
                )
            );
        }

        return result.toString();
    }

    /*//////////////////////////////////////////////////////////////
                                 Spaces
    //////////////////////////////////////////////////////////////*/    

    /**
     * get <code>_amount</code> amount of random spaces
     * 
     * @param _amount amount of spaces
     * @return geenrated <code>_amount</code> amount of spaces
     * 
     */
    public static String GetRandomSpaces(int _amount) { return " ".repeat(Math.max(0, _amount)); }

    /*//////////////////////////////////////////////////////////////
                             Random Integer
    //////////////////////////////////////////////////////////////*/    

    /**
     * generate pseudorandom number ranging from <code>smallest</code> to <code>biggest</code>, including 
     * <code>smallest</code> or <code>biggest</code>
     * 
     * @param smallest pseudominimum
     * @param biggest pseudomaximum
     * @return generated pseudorandom number
     * 
     */
    public static int GetRandomNumber(int smallest, int biggest) { return new Random().nextInt((biggest - smallest + 1)) + smallest; }

}
