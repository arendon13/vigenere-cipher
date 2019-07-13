package exercise.vigenere;

import exercise.vigenere.security.Cipher;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CipherTest {

    public static final String CIPHER_CHAR_SET = "abcdefghijklmnopqrstuvwxyz";

    @Test
    public void testEncrypt() {

        String text = "top secret";

        String key = "encrypt";


        Cipher cipher = new Cipher(CIPHER_CHAR_SET);

        String encrypted = cipher.Vigenere(true, text, key);


        String expected = "xbr jcrkig";

        assertEquals(expected, encrypted);

    }

    @Test
    public void testDecrypt() {

        String text = "xbr jcrkig";

        String key = "encrypt";


        Cipher cipher = new Cipher(CIPHER_CHAR_SET);

        String decrypted = cipher.Vigenere(false, text, key);


        String expected = "top secret";

        assertEquals(expected, decrypted);

    }

}
