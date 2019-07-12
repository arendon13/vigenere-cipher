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

            Cipher cipher = new Cipher(CIPHER_CHAR_SET);

            String encryption = cipher.Vigenere(true, target, key);

            System.out.println(encryption);

        } else if ("decrypt".equalsIgnoreCase(action)) {

            Cipher cipher = new Cipher(CIPHER_CHAR_SET);

            String decryption = cipher.Vigenere(false, target, key);

            System.out.println(decryption);

        } else if ("encryptDir".equalsIgnoreCase(action)) {

            File src = new File(target);

            File dest = new File( target + ".encrypted" );


            Walker fileWalker = new Walker(true, key, CIPHER_CHAR_SET);

            try {
                fileWalker.walk(src, dest);
            } catch (IOException io) {
                // Ideally would like to log these errors but for the purpose of this exercise, will be using System.err
                System.err.println("An error occurred while copying files");
            }

        } else if ("decryptDir".equalsIgnoreCase(action)) {

            File src = new File(target);

            File dest = new File( target + ".decrypted" );


            Walker fileWalker = new Walker(false, key, CIPHER_CHAR_SET);

            try {
                fileWalker.walk(src, dest);
            } catch (IOException io) {
                System.err.println("An error occurred while copying files");
            }

        } else {
            System.out.println("action [" + action + "] not implemented");
        }

    }

}
