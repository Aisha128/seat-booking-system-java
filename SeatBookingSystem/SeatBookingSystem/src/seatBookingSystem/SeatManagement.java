package seatBookingSystem;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SeatManagement {

    // ===== Data member =====
    private ArrayList<Seat> seatList;

    // ===== Constructor =====
    public SeatManagement() {
        seatList = new ArrayList<>();
    }

    // ===== Load Data function =====
    public void loadData(String fileName) throws FileNotFoundException {

        // use a scanner for the file
        FileReader read = new FileReader(fileName);
        Scanner fileScanner = new Scanner(read);

        while (fileScanner.hasNextLine()) {

            String line = fileScanner.nextLine();

            // ignore empty lines
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] elements = line.split(" ");

            // line should be: num class win aisle table price email
            if (elements.length >= 7) {

                String seatNum   = elements[0];
                String seatClass = elements[1];
                boolean isWindow = Boolean.parseBoolean(elements[2]);
                boolean isAisle  = Boolean.parseBoolean(elements[3]);
                boolean isTable  = Boolean.parseBoolean(elements[4]);
                double seatPrice = Double.parseDouble(elements[5]);
                String email     = elements[6];

                Seat tempSeat = new Seat(seatNum, seatClass, isWindow, isAisle, isTable, seatPrice, email);

                seatList.add(tempSeat);
            }
        }

        fileScanner.close();
    }

    // ===== Reserve seat function =====
    public void reserveSeat() {

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the seat Number to reserve: ");
        String seatNumInput = scan.nextLine().trim();

        System.out.print("Enter your email: ");
        String userEmail = scan.nextLine().trim();

        boolean found = false;

        for (Seat seat : seatList) {
            if (seat.getSeatNumber().equalsIgnoreCase(seatNumInput)) {
                found = true;

                if (!seat.isReserved()) {
                    seat.setEmail(userEmail);
                    System.out.println("Seat " + seatNumInput +
                            " reserved successfully by " + userEmail + ".");
                } else {
                    System.out.println("Error: Seat " + seatNumInput +
                            " is already reserved.");
                }
                return;
            }
        }

        if (!found) {
            System.out.println("Error: Seat " + seatNumInput + " not found.");
        }
    }

    // ===== Cancel seat function =====
    public void cancelSeat() {

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the reserved seat number to cancel: ");
        String seatNumInput = scan.nextLine().trim();

        System.out.print("Enter your email: ");
        String userEmail = scan.nextLine().trim();

        boolean found = false;

        for (Seat seat : seatList) {
            if (seat.getSeatNumber().equalsIgnoreCase(seatNumInput)) {
                found = true;

                if (!seat.isReserved()) {
                    System.out.println("Error: Seat " + seatNumInput +
                            " is not reserved. Cancellation failed.");
                    return;
                }

                if (seat.getEmail().equalsIgnoreCase(userEmail)) {
                    seat.setEmail("free");
                    System.out.println("Seat " + seatNumInput +
                            " reservation successfully cancelled.");
                } else {
                    System.out.println("Error: Seat " + seatNumInput +
                            " is reserved by another email. Cancellation failed.");
                }
                return;
            }
        }

        if (!found) {
            System.out.println("Error: Seat " + seatNumInput + " not found.");
        }
    }

    // ===== Print all seat details =====
    public void printSeatDetails() {

        System.out.println("\nSeat Details:-");
        System.out.println("Num\tClass\tWindow\tAisle\tTable\tPrice\tStatus");
        System.out.println("------------------------------------------------");

        for (Seat seat : seatList) {
            seat.printSeat();
        }

        System.out.println("------------------------------------------------\n");
    }

    // ===== View seat reservations =====
    public void viewReservations() {

        System.out.println("\nCurrent Seat Reservations:-");
        System.out.println("Num\tEmail");

        boolean foundReserved = false;

        for (Seat seat : seatList) {
            if (seat.isReserved()) {
                System.out.println(seat.getSeatNumber() + "\t" + seat.getEmail());
                foundReserved = true;
            }
        }

        if (!foundReserved) {
            System.out.println("No seats are reserved.");
        }

        System.out.println();
    }

    // ===== Save seat data back to file =====
    public void saveSeatData(String filePath) throws FileNotFoundException {

        PrintWriter write = new PrintWriter(filePath);

        for (Seat seat : seatList) {
            String line = seat.toString();
            write.println(line);
        }

        write.close();

        System.out.println("\nData saved to " + filePath + " before exit.");
    }
}
