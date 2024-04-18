//Adam Vasquez
//Feb 20, 2023
//Project 1 - Amusement Park Project
import java.util.*;

public class Main {
    
    public static String assignTicket(int age) { //Method that assigns ticket type based on age
        if (age < 14 && age >= 0)
            return "Child";
        else if (age < 62 && age >= 14)
            return "Adult";
        else if (age < 125 && age >= 62)
            return "Senior";
        else
            return "Not Human";
    }

    public static void main(String[] args) {
    	//Initialize variables used in main class
        String name;
        int age;
        String ticketType;
        String ride = "";
        int people;
        int ridePreferenceChoice;
        char userInput;

        Scanner scanner = new Scanner(System.in); //Scanner to read user inputs
        TicketSystem ticketSystem = new TicketSystem();

        System.out.println("Hello, my name is Adam and I am the ticket receptionist. Are you interested in entering the amusement park? Type 'Y' for yes and 'N' for no.");
        userInput = scanner.next().charAt(0); //Input on whether the user wants to enter the amusement park

        if (userInput == 'Y' || userInput == 'y') { //If user inputs Y or y, let the user into amusement park

            System.out.println("How many people are buying tickets? \nNote: We are only allowing a maximum of 5 people go in at this moment.");
            people = scanner.nextInt();//Input of how many people the user brought with them that want a ticket, however there is a limit of 5 people able to be let in
            scanner.nextLine();

            if (people <= 5 && people > 0) { //If the user brought between 1-5 people

                for (int i = 0; i < people; i++) {//For loop that asks each person wanting a ticket
                	System.out.println("Please enter the name for person " + (i + 1) + ".");
                    name = scanner.nextLine();//Input the name of the person
                    System.out.println("Please enter the age for person " + (i + 1) + ".");
                    age = scanner.nextInt();//Input the age of the person 
                    scanner.nextLine();
                    ticketType = assignTicket(age);//Assigns ticket type based on age
                    System.out.println("Please select a ride preference for person " + (i + 1) + "."); //Gives the person the option to choose from 4 rides
                    System.out.println("[1] Penguin Mountain");
                    System.out.println("[2] The Milky Way Carousel");
                    System.out.println("[3] The Ferris Wheel of Fortune");
                    System.out.println("[4] The Drop of Doom");
                    ridePreferenceChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (ridePreferenceChoice) {//Switch statement that assigns the ride the person wants to ride to the ride string variable
                        case 1:
                            ride = "Penguin Mountain";
                            break;
                        case 2:
                            ride = "The Milky Way Carousel";
                            break;
                        case 3:
                            ride = "The Ferris Wheel of Fortune";
                            break;
                        case 4:
                            ride = "The Drop of Doom";
                            break;
                        default:
                            System.out.println("Invalid choice");
                            Runtime.getRuntime().exit(0);
                    }

                    Visitor visitor = new Visitor(name, age, ticketType, ride);//Makes visitor object with the name, age, ticket type, and ride of the visitor
                    ticketSystem.purchaseTicket(visitor); // Purchase ticket

                    if (!ride.isEmpty()) {
                        ticketSystem.joinRideQueue(visitor, ride); // Join ride queue
                    }
                }

                System.out.println("\nSimulating Rides:");
                ticketSystem.simulateRides(); //Simulates rides
                System.out.println("\nHopefully you had a good time, please come again soon!");
                Runtime.getRuntime().exit(0);
                
            } else
                System.out.println("Sorry, we cannot provide service to you."); //If user inputed more than 5 people or less than 1
            	Runtime.getRuntime().exit(0);
        } else
            System.out.println("Okay, please come again soon!"); //If user did not choose Y or y
        	Runtime.getRuntime().exit(0);

        scanner.close();
    }

}