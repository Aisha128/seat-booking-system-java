/**
 * 
 */
package seatBookingSystem;

/**
 * 
 */
public class Seat {

	// Part 1. Data Members
		private String seatNum;
		private String seatClass;
		private boolean isWindow;
		private boolean isAisle;
		private boolean isTable;
		private double seatPrice;
		// if reserved email, free if not
		private String email;
		
		// Part 2. Member Functions
		// The constructor function
		public Seat(String seatNum, String seatClass, boolean isWindow, boolean isAisle, boolean isTable, double seatPrice, String email){
			this.seatNum = seatNum;
			this.seatClass = seatClass;
			this.isWindow = isWindow;
			this.isAisle = isAisle;
			this.isTable = isTable;
			this.seatPrice = seatPrice;
			this.email = email;
			
		}
		
		// Getter and Setter Methods
		public boolean isReserved() {
			return !this.email.equalsIgnoreCase("free");
		}
		public String getSeatNumber() {
			return this.seatNum;
		}
		
		public String getEmail() {
			return this.email;
		}
		
		public void setSeatNumber(String rNo) {
			this.seatNum = rNo;
		}
		
		public void setEmail(String email) {
			this.email = email;
		}
		
		// check if seat is reserved
		public void printSeat() {
			String priceFormatted = String.format("2%f", this.seatPrice);
			
			System.out.print("\n" + this.seatNum + "\t" + this.seatClass + "\t" + this.isWindow + "\t" + this.isAisle + "\t" + this.isTable + "\t" + priceFormatted + "\t" + this.email);
		}
        
		
		// Converts seat objects to String
		public String toString() {
			return (seatNum + " " + seatClass + " " + isWindow + " " + isAisle + " " + isTable + " " + seatPrice + " " + email);
			
		}
		

	}

