import java.util.*;

public class TrainTicketBooking {

    static Scanner sc = new Scanner(System.in);

    static String[] trainNo = {
        "12301", "12951", "12002", "12627",
        "12621", "12303", "12309", "22691"
    };

    static String[] trainName = {
        "Rajdhani Express",
        "Mumbai Rajdhani",
        "Shatabdi Express",
        "Karnataka Express",
        "Tamil Nadu Express",
        "Poorva Express",
        "Rajendra Nagar Exp",
        "Rajdhani Express"
    };

    static String[] from = {
        "New Delhi", "Mumbai", "New Delhi", "New Delhi",
        "New Delhi", "Howrah", "Patna", "Bengaluru"
    };

    static String[] to = {
        "Mumbai", "New Delhi", "Bhopal", "Bengaluru",
        "Chennai", "New Delhi", "Mumbai", "New Delhi"
    };

    static String[] departure = {
        "16:55", "17:40", "06:00", "21:30",
        "22:30", "08:05", "13:45", "20:00"
    };

    static String[] arrival = {
        "08:35+1", "09:55+1", "14:05", "05:10+2",
        "07:40+2", "20:40+1", "08:20+2", "05:50+2"
    };

    static int[] seats = {
        120, 120, 80, 100, 100, 90, 80, 100
    };

    static double[] fare = {
        1500, 1500, 900, 1800, 1750, 1200, 1350, 1900
    };

    static ArrayList<Booking> bookings = new ArrayList<>();

    static int bookingCounter = 1000;

    static class Booking {
        String id;
        int trainIndex;
        String date;
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> ages = new ArrayList<>();
        ArrayList<String> genders = new ArrayList<>();
        double totalFare;
        boolean cancelled;

        Booking(int trainIndex, String date) {
            this.id = "BK" + (++bookingCounter);
            this.trainIndex = trainIndex;
            this.date = date;
            this.cancelled = false;
        }
    }

    public static void showTrains() {

        System.out.println("\n---------------- AVAILABLE TRAINS ----------------");

        for (int i = 0; i < trainNo.length; i++) {
            System.out.println("Train Number : " + trainNo[i]);
            System.out.println("Train Name   : " + trainName[i]);
            System.out.println("Route        : " + from[i] + " -> " + to[i]);
            System.out.println("Departure    : " + departure[i]);
            System.out.println("Arrival      : " + arrival[i]);
            System.out.println("Seats        : " + seats[i]);
            System.out.println("Fare         : Rs." + fare[i]);
            System.out.println("-----------------------------------------------");
        }
    }

    public static void searchTrain() {

        System.out.println("\n---------------- SEARCH TRAIN ----------------");

        System.out.print("Enter starting city: ");
        String start = sc.nextLine();

        System.out.print("Enter destination city: ");
        String destination = sc.nextLine();

        boolean found = false;

        for (int i = 0; i < trainNo.length; i++) {

            if (from[i].equalsIgnoreCase(start)
                    && to[i].equalsIgnoreCase(destination)) {

                found = true;

                System.out.println("\nTrain Number : " + trainNo[i]);
                System.out.println("Train Name   : " + trainName[i]);
                System.out.println("Departure    : " + departure[i]);
                System.out.println("Arrival      : " + arrival[i]);
                System.out.println("Seats        : " + seats[i]);
                System.out.println("Fare         : Rs." + fare[i]);
            }
        }

        if (!found) {
            System.out.println("No train found for this route.");
        }
    }

    public static void bookTicket() {

        showTrains();

        System.out.print("\nEnter train number: ");
        String number = sc.nextLine();

        int index = -1;

        for (int i = 0; i < trainNo.length; i++) {
            if (trainNo[i].equals(number)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Train not found.");
            return;
        }

        if (seats[index] == 0) {
            System.out.println("No seats available.");
            return;
        }

        System.out.print("Enter journey date (dd-MM-yyyy): ");
        String date = sc.nextLine();

        if (!validDate(date)) {
            System.out.println("Invalid date.");
            return;
        }

        System.out.print("How many passengers? ");
        int count = sc.nextInt();
        sc.nextLine();

        if (count < 1 || count > seats[index]) {
            System.out.println("Invalid number of passengers.");
            return;
        }

        Booking b = new Booking(index, date);

        for (int i = 0; i < count; i++) {

            System.out.println("\nPassenger " + (i + 1));

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Age: ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.print("Gender (M/F/O): ");
            String gender = sc.nextLine();

            b.names.add(name);
            b.ages.add(age);
            b.genders.add(gender);
        }

        b.totalFare = count * fare[index];

        System.out.println("\n------------- BOOKING DETAILS -------------");
        System.out.println("Train       : " + trainNo[index] + " - " + trainName[index]);
        System.out.println("Route       : " + from[index] + " -> " + to[index]);
        System.out.println("Date        : " + date);
        System.out.println("Passengers  : " + count);
        System.out.println("Total Fare  : Rs." + b.totalFare);

        System.out.print("Confirm booking? (Y/N): ");
        String answer = sc.nextLine();

        if (answer.equalsIgnoreCase("Y")) {

            seats[index] = seats[index] - count;
            bookings.add(b);

            System.out.println("\nBooking successful!");
            System.out.println("Your Booking ID is: " + b.id);

        } else {
            System.out.println("Booking cancelled.");
        }
    }

    public static void viewBooking() {

        System.out.print("\nEnter booking ID: ");
        String id = sc.nextLine();

        Booking b = findBooking(id);

        if (b == null) {
            System.out.println("Booking not found.");
            return;
        }

        System.out.println("\n------------- BOOKING -------------");
        System.out.println("Booking ID : " + b.id);
        System.out.println("Status     : " +
                (b.cancelled ? "CANCELLED" : "CONFIRMED"));

        System.out.println("Train      : " +
                trainNo[b.trainIndex] + " - " +
                trainName[b.trainIndex]);

        System.out.println("Route      : " +
                from[b.trainIndex] + " -> " +
                to[b.trainIndex]);

        System.out.println("Date       : " + b.date);

        System.out.println("\nPassengers:");

        for (int i = 0; i < b.names.size(); i++) {
            System.out.println(
                    (i + 1) + ". " +
                    b.names.get(i) + "  Age: " +
                    b.ages.get(i) + "  Gender: " +
                    b.genders.get(i)
            );
        }

        System.out.println("Total Fare : Rs." + b.totalFare);
    }

    public static void cancelBooking() {

        System.out.print("\nEnter booking ID: ");
        String id = sc.nextLine();

        Booking b = findBooking(id);

        if (b == null) {
            System.out.println("Booking not found.");
            return;
        }

        if (b.cancelled) {
            System.out.println("Booking is already cancelled.");
            return;
        }

        System.out.println("Booking amount: Rs." + b.totalFare);
        System.out.println("Refund amount: Rs." + (b.totalFare * 0.75));

        System.out.print("Do you want to cancel? (Y/N): ");
        String answer = sc.nextLine();

        if (answer.equalsIgnoreCase("Y")) {

            b.cancelled = true;

            seats[b.trainIndex] =
                    seats[b.trainIndex] + b.names.size();

            System.out.println("Booking cancelled.");
            System.out.println("Refund: Rs." + (b.totalFare * 0.75));

        } else {
            System.out.println("Cancellation stopped.");
        }
    }

    public static void showAllBookings() {

        if (bookings.size() == 0) {
            System.out.println("\nNo bookings available.");
            return;
        }

        System.out.println("\n---------------- ALL BOOKINGS ----------------");

        for (Booking b : bookings) {

            System.out.println("Booking ID : " + b.id);
            System.out.println("Train      : " + trainName[b.trainIndex]);
            System.out.println("Date       : " + b.date);
            System.out.println("Passengers : " + b.names.size());
            System.out.println("Status     : " +
                    (b.cancelled ? "CANCELLED" : "CONFIRMED"));
            System.out.println("Fare       : Rs." + b.totalFare);
            System.out.println("---------------------------------------------");
        }
    }

    public static Booking findBooking(String id) {

        for (Booking b : bookings) {
            if (b.id.equalsIgnoreCase(id)) {
                return b;
            }
        }

        return null;
    }

    public static boolean validDate(String date) {

        if (!date.matches("\\d{2}-\\d{2}-\\d{4}")) {
            return false;
        }

        String[] parts = date.split("-");

        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        if (month < 1 || month > 12) {
            return false;
        }

        if (day < 1 || day > 31) {
            return false;
        }

        Calendar c = Calendar.getInstance();
        c.setLenient(false);

        try {
            c.set(year, month - 1, day);
            c.getTime();
        } catch (Exception e) {
            return false;
        }

        Calendar today = Calendar.getInstance();

        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        return !c.before(today);
    }

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n====================================");
            System.out.println("    TRAIN TICKET BOOKING SYSTEM");
            System.out.println("====================================");

            System.out.println("1. View all trains");
            System.out.println("2. Search train");
            System.out.println("3. Book ticket");
            System.out.println("4. View booking");
            System.out.println("5. Cancel booking");
            System.out.println("6. View all bookings");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Please enter a number.");
                sc.nextLine();
                continue;
            }

            if (choice == 1) {
                showTrains();
            } 
            else if (choice == 2) {
                searchTrain();
            } 
            else if (choice == 3) {
                bookTicket();
            } 
            else if (choice == 4) {
                viewBooking();
            } 
            else if (choice == 5) {
                cancelBooking();
            } 
            else if (choice == 6) {
                showAllBookings();
            } 
            else if (choice == 7) {
                System.out.println("Thank you for using the system.");
                break;
            } 
            else {
                System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}
