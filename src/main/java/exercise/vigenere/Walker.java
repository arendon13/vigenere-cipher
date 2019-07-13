package exercise.vigenere;

import exercise.vigenere.security.Cipher;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Walker {

    private boolean isEncrypting;

    private String key, cipherCharSet;

    private Cipher cipher;

    public Walker(boolean isEncrypting, String key, String cipherCharSet) {

        this.isEncrypting = isEncrypting;

        this.key = key;

        this.cipherCharSet = cipherCharSet;

        cipher = new Cipher(this.cipherCharSet);

    }

    public void walk(File src, File dest) throws IOException {

        // perform checks
        if( src == null || dest == null ) {
            // Ideally would like to log these errors but for the purpose of this exercise, will be using System.err
            System.err.println("Valid source or destination paths not given!");
            return;
        }

        if( !src.isDirectory() ) {
            System.err.println("Source path is not a Directory!");
            return;
        }

        if(dest.exists()) {
            if( !dest.isDirectory() ){
                System.err.println("Existing destination path is not a Directory!");
                return;
            }
        } else {
            dest.mkdir();
        }


        File[] srcFiles = src.listFiles();

        if( src.listFiles() == null || srcFiles.length == 0 ) {
            System.err.println("No files found under the specified source path!");
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

                try (
                        Stream<String> stream = Files.lines(Paths.get(file.toPath().toString()));
                        PrintWriter output = new PrintWriter(fileDest)
                ) {
                    // encrypt each line and add to new file
                    stream.forEach( line -> {
                        String encryptedLine = cipher.Vigenere(isEncrypting, line, key);
                        output.write( encryptedLine + "\n" );
                    });
                }

            }

        }

    }

}
