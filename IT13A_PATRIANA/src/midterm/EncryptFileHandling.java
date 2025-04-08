package midterm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EncryptFileHandling {
//EncryptFileHandling
    public static void main(String[] args) {
        int key = 6;
        String message = "I love you!\nGwapa ko!\nBuotan si Maam!";

        try {
            File myObj = new File("C:\\Input.txt");
            File encrypted = new File("C:\\encrypted.txt");

            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName() +" and "+ encrypted.getName());
            } else {
                System.out.println("The file already exists.");
            }

            FileWriter sulat = new FileWriter(myObj);
            sulat.write(message);
            sulat.close();
          

             String encryptedMessage = encryptMessage(message, key);
            
            FileWriter encryptednasulat = new FileWriter(encrypted);
            encryptednasulat.write(encryptedMessage);
            encryptednasulat.close();
            
            System.out.println("Original  : \n" + message +"\n");
            System.out.println("Encrypted message : \n" + encryptedMessage);

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String encryptMessage(String message, int key) {
        char[] chars = message.toCharArray();
        for (int i = 0; i < chars.length; i++) {
         
            if (Character.isLetterOrDigit(chars[i])) {
                chars[i] += key;
            }
        }
        return new String(chars);
    }
}
