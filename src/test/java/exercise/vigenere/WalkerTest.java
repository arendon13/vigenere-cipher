package exercise.vigenere;

import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertTrue;

public class WalkerTest {


    @Test
    public void testEncryptWalk() {

        final String CIPHER_CHAR_SET = "abcdefghijklmnopqrstuvwxyz";

        String key = "encrypt";

        File src = new File( "sample_dir" );

        File dest = new File( "sample_dir.encrypted" );


        // copy and encrypt files into .encrypted directory
        runCipherWalk(true, key, CIPHER_CHAR_SET, src, dest);


        // compare contents of original and encrypted

        File original = new File("sample_dir/abcde.txt");

        File encrypted = new File("sample_dir.encrypted/abcde.txt");

        boolean hasException = true;

        try {

            String originalContent = new String( Files.readAllBytes(Paths.get(original.toPath().toString())), "UTF-8" );

            String encryptedContent = new String( Files.readAllBytes(Paths.get(encrypted.toPath().toString())), "UTF-8" );

            assertTrue(!encryptedContent.equals(originalContent));

            hasException = false;

        } catch(Exception e) {

            assertTrue(!hasException);

        }

        assertTrue(!hasException);

    }

    @Test
    public void testDecryptWalk() {

        final String CIPHER_CHAR_SET = "abcdefghijklmnopqrstuvwxyz";

        String key = "encrypt";


        // copy and encrypt files into .encrypted directory
        File src = new File( "sample_dir" );

        File dest = new File( "sample_dir.encrypted" );

        runCipherWalk(true, key, CIPHER_CHAR_SET, src, dest);


        // copy and decrypt encrypted files into .decrypted directory
        src = new File( "sample_dir.encrypted" );

        dest = new File( "sample_dir.encrypted.decrypted" );

        runCipherWalk(false, key, CIPHER_CHAR_SET, src, dest);


        // compare contents of original and decrypted

        File original = new File("sample_dir/abcde.txt");

        File decrypted = new File("sample_dir.encrypted.decrypted/abcde.txt");

        boolean hasException = true;

        try {

            String originalContent = new String( Files.readAllBytes(Paths.get(original.toPath().toString())), "UTF-8" );

            String decryptedContent = new String( Files.readAllBytes(Paths.get(decrypted.toPath().toString())), "UTF-8" );

            assertTrue(decryptedContent.equals(originalContent));

            hasException = false;

        } catch(Exception e) {

            assertTrue(!hasException);

        }

        assertTrue(!hasException);

    }

    private void runCipherWalk(boolean isEncrypting, String key, String cipherCharSet, File src, File dest) {

        Walker fileWalker = new Walker(isEncrypting, key, cipherCharSet);

        fileWalker.walk(src, dest);

    }

}
