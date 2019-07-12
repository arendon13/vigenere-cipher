package exercise.vigenere;

import exercise.vigenere.security.Cipher;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CipherTest {

    @Test
    public void testEncrypt() {

        final String CIPHER_CHAR_SET = "abcdefghijklmnopqrstuvwxyz";

        String text = "top secret";

        String key = "encrypt";

        String encrypted = Cipher.Vigenere(true, text, key, CIPHER_CHAR_SET);


        String expected = "xbr jcrkig";

        assertEquals(expected, encrypted);

    }

    @Test
    public void testDecrypt() {

        final String CIPHER_CHAR_SET = "abcdefghijklmnopqrstuvwxyz";

        String text = "xbr jcrkig";

        String key = "encrypt";

        String decrypted = Cipher.Vigenere(false, text, key, CIPHER_CHAR_SET);


        String expected = "top secret";

        assertEquals(expected, decrypted);

    }

}
