import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

import LoginSystem.storage;

public class loginTest {

    storage storage = new storage();
    
    @Test
    void testWeakPassword() {
        assertFalse(storage.isYourPasswordStrong("123abc"));
    }


}
