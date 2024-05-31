package learn;

import java.util.Scanner;

public class CapsuleHotel{
    //field
    String[] capsules;

    //constructor
    public CapsuleHotel(int numberOfCapsules) {

        capsules = new String[numberOfCapsules];
    }

    //methods

    /**
     * Method Name: getMenuOption
     * The getMenuOption method is designed to display a simple menu for a guest system and prompt the user to choose
     * an option. It takes a Scanner object as a parameter to read user input and returns the selected option as a String.
     * @param Scanner console
     * @return String
     */

    public String getMenuOption(Scanner console){
        // Showing the menu
        System.out.println("\nGuest Menu");
        System.out.println("==========");

        System.out.println("1. Check In");
        System.out.println("2. Check Out");
        System.out.println("3. View Guests");
        System.out.println("4. Exit");
        System.out.print("Choose an option [1-4]: ");

        int choice = Integer.parseInt(console.nextLine());
        // returning the userinput as a string.
        switch (choice) {
            case 1:
                return "1";
            case 2:
                return "2";
            case 3:
                return "3";
            case 4:
                return "4";
            default:
                return "That is not a valid choice. Please try again.";
        }
    }


    /*
     * Method name: isCapsuleNumValid
     * The isCapsuleNumValid method is designed to check if the user has given the valid capsule number
     * while checking in a guest, checkout a guest, and view the guest in that capsule
     * @param int value
     * @return boolean
     */
    public boolean isCapsuleNumValid(int value) {
        int numberOfCapsules = capsules.length;

        if (value < 1 || value > numberOfCapsules) {
            return false;
        }
        return true;
    }



    /**
     * Method Name: handleCheckIn
     * The handleCheckIn method is designed to facilitate the check-in process for guests into capsules. It takes a Scanner
     * object for user input and a String representing the guest's name. The method returns a boolean value indicating the
     * success or failure of the check-in process.
     * @param Scanner console
     * @param String guestName
     * @return boolean
     * If the capsule number does not exist the user should see the following error message:
     * Error :(
     * Capsule #9 does not exist. (9 is an example, should be replaced by the capsule number the user tries to input)
     * If the Guest is successfully booked the user should see the following success message:
     * Success :)
     * John is booked in capsule #3 (John and 3 are examples, they should be replaced by the user inputs)
     * If the capsule is occupied the user should see the following error message:
     * Error :(
     * Capsule #5 is occupied. (5 is an example, should be replaced by the capsule number the user tries to input)
     */


    public boolean handleCheckIn(Scanner console, String guestName) {
        // Length of the capsule array
        int numberOfCapsules = capsules.length;

        // Loop until valid capsule number is entered
        while (true) {
            System.out.print("Capsule #[1-" + numberOfCapsules + "]: ");

            // Checking if console has next line
            if (!console.hasNextLine()) {
                System.out.println("\nError: No input available.");
                return false;
            }

            int capsuleNum;

            // Catch an exception if the user input cannot be parsed as an integer.
            try {
                capsuleNum = Integer.parseInt(console.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nError: Invalid input. Please enter a number.");
                continue;
            }

            // Check if the capsule number is valid
            // Check if the capsule is unoccupied
            // Insert the guest in the specified capsule
            if (!isCapsuleNumValid(capsuleNum)) {
                System.out.println("\nError :(");
                System.out.printf("Capsule #%s does not exist.", capsuleNum);
                System.out.println();
            } else if (capsules[capsuleNum - 1] != null) {
                System.out.println("\nError :(");
                System.out.printf("Capsule #%s is occupied.", capsuleNum);
                System.out.println();
            } else {
                capsules[capsuleNum - 1] = guestName;
                System.out.println("\nSuccess :)");
                System.out.printf("%s is booked in capsule #%s.", guestName, capsuleNum);
                System.out.println();
                return true;
            }
        }
    }


    /*
     * Method name: isGuestAvailable
     * @return boolean
     * The method isGuestAvailable is designed to check if there is any guests available in any
     * capsules. It returns false if there is at least one guest, otherwise true.
     */
    public boolean isGuestAvailable() {
        //boolean
        for (int i = 0; i < capsules.length; i ++) {
            if (capsules[i] != null) {
                return false;
            }
        }
        return true;
    }


    /**
     * Method Name: handleCheckOut
     * The handleCheckOut method is designed to manage the check-out process for guests from capsules. It takes a Scanner
     * object for user input and returns a boolean value indicating the success or failure of the check-out process.
     * @param Scanner console
     * @return boolean
     * If no guests are checked into the hotel the user should see the following error message:
     * Sorry... check out is only available if there's at least one guest.
     * If the capsule number does not exist the user should see the following error message:
     * Error :(
     * Capsule #9 does not exist. (9 is an example, should be replaced by the capsule number the user tries to input)
     * If the Guest is successfully booked the user should see the following success message:
     * Success :)
     * John checked out from capsule #3 (John and 3 are examples, they should be replaced by the user inputs)
     * If the capsule is unoccupied the user should see the following error message:
     * Error :(
     * Capsule #5 is unoccupied. (5 is an example, should be replaced by the capsule number the user tries to input)
     */


    public boolean handleCheckOut(Scanner console) {
        System.out.println("\nGuest Check Out");
        System.out.println("===============");

        int numberOfCapsules = capsules.length;

        // Checking if all the capsules are empty
        if (isGuestAvailable()) {
            System.out.println("Sorry... check out is only available if there's at least one guest.");
            return false;
        }

        int capsuleNum = 0;

        // Loop until valid capsule number is entered
        do {
            if (!console.hasNextLine()) {
                System.out.println("\nError: No input available.");
                return false;
            }

            System.out.print("Capsule #[1-" + numberOfCapsules + "]: ");

            try {
                capsuleNum = Integer.parseInt(console.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nError: Invalid input. Please enter a number.");
                continue;
            }

            if (!isCapsuleNumValid(capsuleNum)) {
                System.out.println("\nError :(");
                System.out.printf("Capsule #%s does not exist.", capsuleNum);
                System.out.println();
            } else if (capsules[capsuleNum - 1] == null) {
                System.out.println("\nError :(");
                System.out.printf("Capsule #%s is unoccupied.", capsuleNum);
                System.out.println();
            }
        } while (!isCapsuleNumValid(capsuleNum) || capsules[capsuleNum - 1] == null);


        String guestName = capsules[capsuleNum - 1];
        capsules[capsuleNum - 1] = null;
        System.out.println("\nSuccess :)");
        System.out.printf("%s checked out from capsule #%s.", guestName, capsuleNum);
        System.out.println();
        return true;
    }


    /**
     * Method Name: viewGuests
     * The viewGuests method is designed to display information about the guests occupying capsules. It takes a Scanner object for user input and prints a list of guests in capsules along with their capsule numbers, if the capsule is null [unoccupied] should be printed, otherwise the guest name should be printed
     * @param Scanner console
     * Example Output:
     * Capsule: Guest
     * 1: John
     * 2: [unoccupied]
     * 3: [unoccupied]
     * 4: [unoccupied]
     * 5: [unoccupied]
     * 6: [unoccupied]
     * 7: [unoccupied]
     * 8: [unoccupied]
     * 9: [unoccupied]
     * 10: [unoccupied]
     * 11: [unoccupied]
     *
     * 11 guests should always be displayed. If the user inputs a number at the end of the array the last 11 capsules
     * should be displayed. If the user enters a number at the beginning of the array the first 11 capsules should be
     * displayed. Otherwise the capsule number entered and the 5 below it and 5 above it should be displayed.
     *
     * If the user enters a capsule number that does not exist the user should see the following error message:
     * Error :(
     * Capsule #51 does not exist. (51 is an example, this number should be replaced with the user input)
     */

    public void viewGuests(Scanner console) {
        System.out.println("\nView Guests");
        System.out.println("===========");

        int numberOfCapsules = capsules.length;
        int capsuleNum;

        // Loop until valid capsule number is entered
        do {
            System.out.print("Capsule #[1-" + numberOfCapsules + "]: ");

            if (!console.hasNextLine()) {
                System.out.println("\nError: No input available.");
                return;
            }

            capsuleNum = Integer.parseInt(console.nextLine());

            if (!isCapsuleNumValid(capsuleNum)) {
                System.out.println("\nError :(");
                System.out.printf("Capsule #%s does not exist.", capsuleNum);
                System.out.println();
            }
        } while (!isCapsuleNumValid(capsuleNum));

        System.out.println("\nCapsule: Guest");

        // Calculating the range of capsule numbers to show to the users
        int start;
        int end;

        // Check if the number of capsules are less than 11, show all
        // Check if the input capsule number is close to the end
        // Check if the input capsule number is close to the beginning
        // Else show 5 capsules before and 5 capsules after the input capsule number
        if (numberOfCapsules <= 11) {
            start = 0;
            end = numberOfCapsules - 1;
        } else if (capsuleNum >= numberOfCapsules - 5) {
            start = numberOfCapsules - 11;
            end = numberOfCapsules - 1;
        } else if (capsuleNum <= 5) {
            start = 0;
            end = 10;
        } else {
            start = capsuleNum - 5 - 1;
            end = capsuleNum + 5 - 1;
        }

        for (int i = start; i <= end; i ++) {
            String guest = (capsules[i] == null) ? "[unoccupied]" : capsules[i];
            System.out.println(i + 1 + ": " + guest);
        }
    }


    /**
     * Method Name: confirmExit
     * The confirmExit method is designed to prompt the user for confirmation before exiting a program. It takes a
     * Scanner object for user input and returns a boolean value indicating whether the user wants to proceed with the exit.
     * @param Scanner console
     * @return boolean
     * if the user enters y or Y the method should return true, otherwise it should return false.
     */

    public static boolean confirmExit(Scanner console) {
        System.out.println("\nExit");
        System.out.println("====");
        System.out.println("Are you sure you want to exit?");
        System.out.println("All data will be lost.");
        System.out.print("Exit [y/n]: ");

        // Making the user input to lower case
        String ans = console.nextLine().toLowerCase();
        if (ans.equalsIgnoreCase("y")) {
            System.out.println("\nGoodbye!");
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("Welcome to Capsule-Capsule.");
        System.out.println("===========================");
        System.out.print("Enter the number of capsules available: ");

        int numberOfCapsules = Integer.parseInt(console.nextLine());

        // Create a String array with the capacity of numberOfCapsules

        System.out.printf("\nThere are %s unoccupied capsules ready to be booked.%n", numberOfCapsules);

        CapsuleHotel hotelApp = new CapsuleHotel(numberOfCapsules);

        /** Write a Switch Statement below to invoke a method depending on the user input from get menu option. When
         * invoking a method use hotelApp.MethodName()*/

        String userInput;
        String guestName;

        do {
            userInput = hotelApp.getMenuOption(console);
            switch (userInput) {
                case "1":
                    System.out.println("\nGuest Check In");
                    System.out.println("==============");
                    System.out.print("Guest Name: ");
                    guestName = console.nextLine();
                    hotelApp.handleCheckIn(console, guestName);
                    break;
                case "2":
                    hotelApp.handleCheckOut(console);
                    break;
                case "3":
                    hotelApp.viewGuests(console);
                    break;
                case "4":
                    hotelApp.confirmExit(console);
                    break;
                default:
                    System.out.println("not a valid choice");
                    break;
            }
        } while (!userInput.equals("4"));
    }
}