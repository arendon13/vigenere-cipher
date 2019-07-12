package exercise.vigenere.security;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cipher {

    private Cipher() {

    }

    public static String Vigenere(boolean isEncrypting, String input, String key, String cipherCharSet) {

        // Establishing a custom bi-directional mapping of the characters in the CipherCharSet
        Map<Integer,Character> numToCharSet = mapNumToCharSet(cipherCharSet);

        Map<Character,Integer> charSetToNum = mapCharSetToNum(cipherCharSet);


        int charSetSize = numToCharSet.size();

        StringBuilder sb = new StringBuilder();

        // iterate through each character in the given string to encrypt/decrypt
        for(int i = 0, j = 0; i < input.length(); i++) {

            char textChar = input.charAt(i);

            // if a current char in the input is not found in the CipherCharSet, leave as is
            if ( !charSetToNum.containsKey(textChar) ) {
                sb.append(textChar);
                continue;
            }


            char keyChar = key.charAt(j % key.length());

            // getting our initial custom number assigned to the keyChar
            int charSetMappingStart = charSetToNum.get(keyChar);

            Map<Integer,Integer> sourceSetMap = new LinkedHashMap<>();

            // x represents the integers in previously created numToCharSet and charSetToNum maps
            for(int x = 1, y = charSetMappingStart; x <= charSetSize; x++, y++) {

                int cipheredCharNum = y % charSetSize;

                if (cipheredCharNum == 0) cipheredCharNum = charSetSize; // necessary since our custom mapping begins at index 1

                if( isEncrypting ) {
                    sourceSetMap.put(x,cipheredCharNum);
                } else {
                    sourceSetMap.put(cipheredCharNum, x);
                }

            }


            //  getting mapping for current text character based off the created sourceSetMap
            int textMap = charSetToNum.get(textChar);

            int encryptedCharNum = sourceSetMap.get(textMap);

            sb.append(numToCharSet.get(encryptedCharNum));

            j++;

        }

        return sb.toString();

    }

    private static Map<Integer,Character> mapNumToCharSet(String cipherCharSet) {

        // Incrementing integers are mapped to the chars in the CipherCharSet
        Map<Integer,Character> charSet = new LinkedHashMap<>();

        for( int i = 1; i <= cipherCharSet.length(); i++ ) {

            char c = cipherCharSet.charAt(i-1);

            charSet.put(i, c);

        }

        return charSet;

    }

    private static Map<Character,Integer> mapCharSetToNum(String cipherCharSet) {

        // CipherCharSet characters are mapped to incrementing integers
        Map<Character,Integer> charSet = new LinkedHashMap<>();

        for( int i = 1; i <= cipherCharSet.length(); i++ ) {

            char c = cipherCharSet.charAt(i-1);

            charSet.put(c, i);

        }

        return charSet;

    }

}
