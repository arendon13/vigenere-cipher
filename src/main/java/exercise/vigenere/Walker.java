package exercise.vigenere;

import exercise.vigenere.security.Cipher;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Walker {

    private boolean isEncrypting;

    private String key, cipherCharSet;

    Walker(boolean isEncrypting, String key, String cipherCharSet) {

        this.isEncrypting = isEncrypting;

        this.key = key;

        this.cipherCharSet = cipherCharSet;

    }

    void walk(File src, File dest) {

        // perform checks
        if( src == null || dest == null ) {
            System.out.println("Valid source or destination paths not given!");
            return;
        }

        if( !src.isDirectory() ) {
            System.out.println("Source path is not a Directory!");
            return;
        }

        if(dest.exists()) {
            if( !dest.isDirectory() ){
                System.out.println("Existing destination path is not a Directory!");
                return;
            }
        } else {
            dest.mkdir();
        }


        File[] srcFiles = src.listFiles();

        if( src.listFiles() == null || srcFiles.length == 0 ) {
            System.out.println("No files found under the specified source path!");
            return;
        }

        // go through each file in the source directory
        for( File file : srcFiles ) {

            File fileDest = new File(dest, file.getName());

            if(file.isDirectory()){

                walk(file, fileDest);

            } else{

                if( fileDest.exists() ) {
                    continue;
                }

                try {

                    // copy existing file to new file destination
                    Files.copy(file.toPath(), fileDest.toPath());


                    // get file content, encrypt/decrypt content, overwrite file contents
                    String content = new String( Files.readAllBytes(Paths.get(fileDest.toPath().toString())), "UTF-8" );

                    String cipheredContent = Cipher.Vigenere(isEncrypting, content, key, cipherCharSet);

                    FileWriter f = new FileWriter(fileDest.toPath().toString());

                    try {
                        f.write(cipheredContent);
                    } catch(Exception e) {
                        System.out.println("Error occurred while writing to file.");
                    } finally {
                        f.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

    }

}
