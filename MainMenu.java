/**
 * 
 */
package seatBookingSystem;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 */
public class MainMenu {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		// use your file path
		String DATA_FILE = "C:\\Users\\97455\\eclipse-workspace\\SeatBookingSystem\\src\\seatBookingSystem\\seats.txt";
		
		// print the first line
        System.out.print("-- Seat Booking System --");
        
        // scanner for the menu input
		Scanner scan = new Scanner(System.in);
		
		SeatManagement manager = new SeatManagement();
		
		manager.loadData(DATA_FILE);
		
		while(true) {	
		// menu output appendix B
		System.out.print("\n\t--Main Menu--");
		System.out.print("\n\t 1- Reserve Seat");
		System.out.print("\n\t 2- Cancel Seat");
		System.out.print("\n\t 3- View Seat Reservations");
		System.out.print("\n\t Q- Quit");
		
		System.out.print("\nPick: ");
		
		// loop
		String choice = " ";
		
		try {
		// user input for options 1,2,3
		choice = scan.next();
		// for q
		} catch (NumberFormatException e) {
			System.out.print("\nWrong input. Enter a number (1, 2, or 3) or the letter Q.");
			continue;
		}
		
		// choosing available options
		switch(choice) {
		
		case "1":
			System.out.println("\nYou clicked option 1: Reserve seat");
			manager.reserveSeat();
			break;
		
		case "2":
			System.out.println("\nYou clicked option 2: Cancel Seat");
			manager.cancelSeat();
			break;
			
		case "3":
			System.out.print("\nYou clicked option 3: View Seat Reservations");
			manager.viewReservations();
			break;
			
		case "Q":
		case "q":
			System.out.println("\nYou clicked option Q: Quit");
		    
			// save data on exit
				manager.saveSeatData(DATA_FILE);
				
				scan.close(); // close scanner
				
			return; // leave the loop
			
		default:
			System.out.print("\nWrong Input. Try again.");
	
		}
		
		} // end of while loop
		
	} // end of main

} //end of class