/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package midterm;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class ordering__system {
    
    static Scanner s=  new Scanner(System.in);
    static final String FileName = "C:\\Users\\ADMIN\\Documents\\credentials.txt";
    static final int Shift = 1;
    
    public static void main(String[] args) throws IOException {
        
        System.out.println("================================================================");
        System.out.println("      𓌉◯𓇋 Welcome to the Restaurant ordering System!𓌉◯𓇋       ");
        System.out.println("================================================================");
     
        
//ask if the customer want to login or create a new account//
        System.out.println("[1] Create Account\n[2] Login");
        System.out.print("Enter choice: "); 
        int choice = s.nextInt();
        s.nextLine(); //Consume new line
        
        if(choice == 1){           
            CreateAcc();
        }else if(choice == 2){
            Login(); 
        }else{
            System.out.println("Invalid Choice");
        }      
    }
     
   public static void CreateAcc(){
       //Create Account for the Customer//
       System.out.println("================================================================");
       System.out.println("                    🔒  Create New Account 🔒                    ");
       System.out.println("================================================================");
       
       //Ask the customer username and password//
       System.out.print("Enter Username: ");
       String NewU = s.nextLine();
       
       System.out.print("Enter New password: ");
       String NewP = s.nextLine();
       
       String encryptPass = encryptCaesar(NewP,Shift);
      
       
       try{
           FileWriter Ca = new FileWriter(FileName, true);
           Ca.write(NewU + ", " + encryptPass + "\n");
           Ca.close();

           System.out.println("Account Created Successfully");
           Login();
           
       }catch (IOException e) {
           System.out.println("Error Saving Account");
       }
   } 
   
   public static String encryptCaesar(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                result.append((char) ((ch - base + shift) % 26 + base));
            } else if (Character.isDigit(ch)) {
                result.append((char) ((ch - '0' + shift) % 10 + '0'));
            } else {
                result.append(ch); 
            }
        }

        return result.toString();
    }
   
       public static boolean validate(String OldU,String OldP) {
        String encryptedInputPassword = encryptCaesar(OldP, Shift);

        try (BufferedReader reader = new BufferedReader(new FileReader(FileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts.length == 2){
                    String fileUsername = parts[0].trim();
                    String filePassword = parts[1].trim();
                    
                    if(fileUsername.equals(OldU) && filePassword.equals(encryptedInputPassword)){
                        return true;
                    }
                
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: ");
        }
        return false;
    }
   
       
   public static void Login() throws IOException{
       
       System.out.println("================================================================");
       System.out.println("                       🔐 Login Account                          ");
       System.out.println("================================================================");
       
       System.out.print("Enter Username: ");
       String OldU = s.nextLine();
       
       System.out.print("Enter password: ");
       String OldP = s.nextLine();
       
       
       if (validate( OldU, OldP)) {
           
           System.out.println(" 🔓 Login Successfully");
           System.out.println("Welcome " + OldU);
           displayMenu();
       }else{
           System.out.println("❌ Wrong Username or Password");
           Login();
       }
   }
   public static void displayMenu() {
       
       int choice;
       int quantity;
       double totalPrice = 0;
       
        
        int pizzaQty = 0;
        int burgerQty = 0;
        int friesQty = 0;
       
       do{
           System.out.println("----------------------------------------------------------------");
           System.out.println("                         Java Menu                              ");
           
           
           System.out.println("[1] Pizza - ₱ 120");
           System.out.println("[2] Burger - ₱ 50");
           System.out.println("[3] Fries - ₱ 50");
           System.out.println("[4] Exit");
           System.out.println("--------------------------------------");
           System.out.print("Choose an Item: ");
           while(!s.hasNextInt()){
               System.out.println("Please choose and Item");
           }
           choice = s.nextInt();
           s.nextLine();
           

           switch(choice) {
               case 1 -> {
                   System.out.print("Enter quantity: ");
                   quantity = s.nextInt();
                   pizzaQty += quantity;                  
                   totalPrice += 120 * quantity;
                   System.out.println("You ordered Pizza x" + quantity);
               }
               case 2 -> {
                   System.out.print("Enter quantity: ");
                   quantity = s.nextInt();
                   burgerQty += quantity;
                   totalPrice += 50 * quantity;
                   System.out.println("You ordered Burger x" + quantity);
               }
               case 3 -> {
                   System.out.print("Enter quantity: ");
                   quantity = s.nextInt();
                   friesQty += quantity;
                   totalPrice += 50 * quantity;
                   System.out.println("You ordered Fries x" + quantity);
               }
               case 4 -> 
                   System.out.println("Thank you for Dining with us!");
               default -> System.out.println("Invalid choice! Please select a valid option");
           
           }
             
       }while (choice !=4);
       
       System.out.println();
       System.out.println("---------------------- 📜 Order Summary ------------------------");
       
       if (pizzaQty > 0){
           System.out.println("Pizza x" + pizzaQty + " - ₱" + (pizzaQty * 120));                       
       }
       if (burgerQty > 0){
           System.out.println("Burger x" + burgerQty + " - ₱" + (burgerQty * 50));                      
       }
       if (friesQty > 0){
           System.out.println("Fries x" + friesQty + " - ₱" + (friesQty * 50));                      
       }
       

       System.out.println("----------------------------");
       System.out.println("Total: ₱" + totalPrice);
       
   }

}