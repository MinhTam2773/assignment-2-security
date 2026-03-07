package app;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class ManageComputers {
    
    private static final String[] VALID_CPU = {"i3", "i5", "i7", "i9"};
    private static final String[] VALID_RAM = {"4GB", "8GB", "16GB", "32GB"};
    private static final String[] VALID_DISK = {"256GB", "512GB", "1TB", "2TB"};
    private static final String[] VALID_GPU = {"RTX5060", "RTX5070", "RTX5080", "RTX5090"};
    private static final String[] VALID_SCREEN = {"14\"", "16\"", "18\"", "21\""};
    
    
    public static void main(String args[]) {
        
        // CHANGED: Now uses ArrayList<ComputerInfo> instead of ArrayList<Computer>
        ArrayList<ComputerInfo> computers = new ArrayList<ComputerInfo>(); 

        Scanner s = new Scanner(System.in);
        String menuOption="";

        do { //Start of main program loop

            //Show computer data in ArrayList<ComputerInfo>
            showComputers(computers); 

            //Display menu and return menu option selected by the user
            menuOption = getMenuSelection(s);

            switch(menuOption) {
                //Add new computer
                case "a": 
                    addComputer(computers, s);
                    break;

                //Delete a computer    
                case "d": 
                    deleteComputer(computers, s);
                    break;

                //Edit a computer    
                case "e": 
                    editComputer(computers, s);
                    break;
            }

        } while ( ! menuOption.equals("x") ); //Stop when "x" is entered

        s.close(); //Close keyboard scanner

    } //End of main

    //-----------------------------
    //Display menu and get user selection, return it
    private static String getMenuSelection(Scanner s) {
        String menuOption="";

        //Display menu options on-screen
        System.out.println("----------");
        System.out.println("A) Add Computer");
        System.out.println("D) Delete Computer");
        System.out.println("E) Edit Computer");
        System.out.println("X) eXit");
        System.out.println("----------");

        //Get menu selection from keyboard
        System.out.print("Enter menu selection:");
        menuOption = s.nextLine();

        menuOption = menuOption.toLowerCase(); //Make lower case for comparison purposes

        return menuOption;
    } //End of getMenuSelection

    //-----------------------------
    //Show data for all laptops and desktops stored in ArrayList<ComputerInfo>
    // CHANGED: Parameter type changed from ArrayList<Computer> to ArrayList<ComputerInfo>
    private static void showComputers(ArrayList<ComputerInfo> computers) {
        int computerListNumber = 0;

        System.out.println("=========");
        System.out.println("LIST OF COMPUTERS:-");

        for (ComputerInfo c : computers) {
            computerListNumber++;
            // Call overridden toString() method for current object
            System.out.println(computerListNumber + ": " + c.toString());
        }

        System.out.println("=========");
    } //End of showComputers

    //-----------------------------
    //Add a new Laptop or Desktop computer to the ArrayList<ComputerInfo>
    // CHANGED: Parameter type changed
    private static void addComputer(ArrayList<ComputerInfo> computers, Scanner s) {
        String computerType = "";

        System.out.println("ADDING COMPUTER:-");

        System.out.println("Enter type of computer to add ('L' for Laptop, 'D' for Desktop):");
        computerType = s.nextLine();
        computerType = computerType.toLowerCase();

        switch(computerType) {

            //Add a laptop
            case "l": 
                // Get CPU, RAM, Disk, and Screen info with validation
                String cpu = promptWhitelisted(s, "Enter CPU", VALID_CPU);
                String ram = promptWhitelisted(s, "Enter RAM", VALID_RAM);
                String disk = promptWhitelisted(s, "Enter Disk", VALID_DISK);
                String screenSize = promptWhitelisted(s, "Enter screen size", VALID_SCREEN);

                // CHANGED: Create new Laptop directly (no tempComputer needed)
                computers.add(new Laptop(cpu, ram, disk, screenSize));
                break;
            
            //Add a desktop    
            case "d": 
                // Get CPU, RAM, Disk, and GPU info with validation
                cpu = promptWhitelisted(s, "Enter CPU", VALID_CPU);
                ram = promptWhitelisted(s, "Enter RAM", VALID_RAM);
                disk = promptWhitelisted(s, "Enter Disk", VALID_DISK);
                String GPUType = promptWhitelisted(s, "Enter GPU", VALID_GPU);

                // CHANGED: Create new Desktop directly
                computers.add(new Desktop(cpu, ram, disk, GPUType));
                break;

            //Invalid computer type to add entered
            default:
                System.out.println("Invalid computer type entered!");
        }
    } //End of addComputer

    //-----------------------------
    //Delete a specified computer from the ArrayList
    // CHANGED: Parameter type changed
    private static void deleteComputer(ArrayList<ComputerInfo> computers, Scanner s) {
        int computerListNumberToDelete = 0;

        System.out.println("DELETE COMPUTER:-");

        System.out.print("Enter number of computer to delete:");
        try {
            computerListNumberToDelete = Integer.parseInt(s.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
            return;
        }

        //Check if computer list number is valid before deleting
        if (computerListNumberToDelete >= 1 && computerListNumberToDelete <= computers.size()) {
            // Subtract 1 to get ArrayList index
            computers.remove(computerListNumberToDelete - 1);
            System.out.println("Computer deleted successfully.");
        }   
        else {
            System.out.println("Invalid computer number entered!");
        }
    } //End of deleteComputer

    //-----------------------------
    //Edit a computer. Since objects are IMMUTABLE, we must REPLACE the old object
    //with a new one containing the updated values.
    // CHANGED: Complete rewrite to handle immutability
    private static void editComputer(ArrayList<ComputerInfo> computers, Scanner s) {
        int computerListNumberToEdit = 0;

        System.out.println("EDIT COMPUTER:-");

        System.out.print("Enter number of computer to edit:");
        try {
            computerListNumberToEdit = Integer.parseInt(s.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
            return;
        }

        //Check that computerListNumberToEdit is valid first
        if (computerListNumberToEdit >= 1 && computerListNumberToEdit <= computers.size()) {
            
            int index = computerListNumberToEdit - 1;
            ComputerInfo computerToEdit = computers.get(index);
            
            // Determine the type and create a NEW object with updated values
            if (computerToEdit instanceof Laptop) {
                System.out.println("Editing a Laptop:");
                
                // Get new values with validation
                String cpu = promptWhitelisted(s, "Enter CPU", VALID_CPU);
                String ram = promptWhitelisted(s, "Enter RAM", VALID_RAM);
                String disk = promptWhitelisted(s, "Enter Disk", VALID_DISK);
                String screenSize = promptWhitelisted(s, "Enter screen size", VALID_SCREEN);
                
                // CHANGED: Create a NEW Laptop object (immutable) and REPLACE the old one
                Laptop updatedLaptop = new Laptop(cpu, ram, disk, screenSize);
                computers.set(index, updatedLaptop);
                System.out.println("Laptop updated successfully.");
            }
            else if (computerToEdit instanceof Desktop) {
                System.out.println("Editing a Desktop:");
                
                // Get new values with validation
                String cpu = promptWhitelisted(s, "Enter CPU", VALID_CPU);
                String ram = promptWhitelisted(s, "Enter RAM", VALID_RAM);
                String disk = promptWhitelisted(s, "Enter Disk", VALID_DISK);
                String GPUType = promptWhitelisted(s, "Enter GPU", VALID_GPU);
                
                // CHANGED: Create a NEW Desktop object and REPLACE the old one
                Desktop updatedDesktop = new Desktop(cpu, ram, disk, GPUType);
                computers.set(index, updatedDesktop);
                System.out.println("Desktop updated successfully.");
            }
        }
        else {
            System.out.println("Invalid computer number entered!");
        }
    } //End of editComputer
    
    // Prompts the user to enter a value and checks it against the whitelist
    // Keeps looping until a valid value is entered then returns it.
    private static String promptWhitelisted(Scanner s, String prompt, String[] whitelist) {
        while (true) {
            System.out.print(prompt + " " + Arrays.toString(whitelist) + ": ");
            String input = s.nextLine().trim();
            for (String valid : whitelist) {
                if (valid.equalsIgnoreCase(input)) {
                    return valid;  // Return the canonical version from whitelist
                }
            }
            System.out.println("Invalid input: \"" + input + "\" is not allowed.");
            System.out.println("Valid options are: " + Arrays.toString(whitelist));
        }
    }

    // REMOVED: getComputerData() method is no longer needed since we're not using
    // a temporary Computer object anymore. Validation is handled directly.
    
} //End of ManageComputer class