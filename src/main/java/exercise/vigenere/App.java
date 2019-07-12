package exercise.vigenere;

import exercise.vigenere.security.Cipher;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

// "encrypt" "encrypt" "top secret"
// "decrypt" "encrypt" "top secret"
// "encryptDir" "encrypt" "/Users/arendon/Documents/Java/vigenere-cipher/sample_dir"
// "decryptDir" "encrypt" "/Users/arendon/Documents/Java/vigenere-cipher/sample_dir.encrypted"

public class App {

    // Cipher Char Set for the Vinegere Cipher algorithm is determined here
    private static final String CIPHER_CHAR_SET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz \t\n\r~!@#$%^&*()_+-=[]\\{}|;':\",./<>?";

    public static void main(String[] args) {

        if (args.length != 3) {

            System.out.println("Exact 3 parameters required - [action] [key] [target]");

            System.exit(1);

        }


        String action, key, target;

        action = args[0];

        key = args[1];

        target = args[2];


        if ("encrypt".equalsIgnoreCase(action)) {

            String encryption = Cipher.Vigenere(true, target, key, CIPHER_CHAR_SET);

            System.out.println(encryption);

        } else if ("decrypt".equalsIgnoreCase(action)) {

            String decryption = Cipher.Vigenere(false, target, key, CIPHER_CHAR_SET);

            System.out.println(decryption);

        } else if ("encryptDir".equalsIgnoreCase(action)) {

            File src = new File(target);

            File dest = new File( target + ".encrypted" );


            Walker fileWalker = new Walker(true, key, CIPHER_CHAR_SET);

            fileWalker.walk(src, dest);

        } else if ("decryptDir".equalsIgnoreCase(action)) {

            File src = new File(target);

            File dest = new File( target + ".decrypted" );


            Walker fileWalker = new Walker(false, key, CIPHER_CHAR_SET);

            fileWalker.walk(src, dest);

        } else {
            System.out.println("action [" + action + "] not implemented");
        }

    }

}
