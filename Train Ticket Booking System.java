import java.util.*;

public class TrainTicketBooking {

    static class Train {
        String trainNumber, trainName, source, destination;
        String departureTime, arrivalTime;
        int    totalSeats, availableSeats;
        double farePerSeat;

        Train(String trainNumber, String trainName, String source, String destination,
              String departureTime, String arrivalTime, int totalSeats, double farePerSeat) {
            this.trainNumber    = trainNumber;
            this.trainName      = trainName;
            this.source         = source;
            this.destination    = destination;
            this.departureTime  = departureTime;
            this.arrivalTime    = arrivalTime;
            this.totalSeats     = totalSeats;
            this.availableSeats = totalSeats;
            this.farePerSeat    = farePerSeat;
        }
    }

    static class Passenger {
        String name, gender;
        int    age;

        Passenger(String name, int age, String gender) {
            this.name   = name;
            this.age    = age;
            this.gender = gender;
        }
    }

    static class Booking {
        static int counter = 1000;

        String           bookingId, trainNumber, trainName;
        String           source, destination, journeyDate, status;
        List<Passenger>  passengers;
        double           totalFare;

        Booking(String trainNumber, String trainName, String source,
                String destination, String journeyDate,
                List<Passenger> passengers, double totalFare) {
            this.bookingId   = "BK" + (++counter);
            this.trainNumber = trainNumber;
            this.trainName   = trainName;
            this.source      = source;
            this.destination = destination;
            this.journeyDate = journeyDate;
            this.passengers  = passengers;
            this.totalFare   = totalFare;
            this.status      = "CONFIRMED";
        }
    }

    static List<Train>   trains   = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();
    static Scanner       sc       = new Scanner(System.in);

    static void initTrains() {
        trains.add(new Train("12301", "Rajdhani Express",   "New Delhi", "Mumbai",    "16:55", "08:35+1", 120, 1500));
        trains.add(new Train("12951", "Mumbai Rajdhani",    "Mumbai",    "New Delhi", "17:40", "09:55+1", 120, 1500));
        trains.add(new Train("12002", "Shatabdi Express",   "New Delhi", "Bhopal",    "06:00", "14:05",    80,  900));
        trains.add(new Train("12627", "Karnataka Express",  "New Delhi", "Bengaluru", "21:30", "05:10+2", 100, 1800));
        trains.add(new Train("12621", "Tamil Nadu Express", "New Delhi", "Chennai",   "22:30", "07:40+2", 100, 1750));
        trains.add(new Train("12303", "Poorva Express",     "Howrah",    "New Delhi", "08:05", "20:40+1",  90, 1200));
        trains.add(new Train("12309", "Rajendra Nagar Exp","Patna",     "Mumbai",    "13:45", "08:20+2",  80, 1350));
        trains.add(new Train("22691", "Rajdhani Express",  "Bengaluru", "New Delhi", "20:00", "05:50+2", 100, 1900));
    }

    static void printLine() {
        System.out.println("--------------------------------------------------" +
                           "------------------------------");
    }

    static void printHeader(String title) {
        System.out.println();
        printLine();
        System.out.println("   " + title);
        printLine();
    }

    static String readLine(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("  Please enter a valid number.");
            }
        }
    }

    static boolean isValidDate(String date) {
        if (date == null || !date.matches("\\d{2}-\\d{2}-\\d{4}")) return false;
        String[] parts = date.split("-");
        int dd   = Integer.parseInt(parts[0]);
        int mm   = Integer.parseInt(parts[1]) - 1; 
        int yyyy = Integer.parseInt(parts[2]);

        if (mm < 0 || mm > 11 || dd < 1 || dd > 31) return false;

        Calendar input = Calendar.getInstance();
        input.setLenient(false);
        try {
            input.set(yyyy, mm, dd, 0, 0, 0);
            input.getTime(); 
        } catch (Exception e) {
            return false;
        }

        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        return !input.before(today);
    }

    static Train findTrain(String trainNumber) {
        for (Train t : trains)
            if (t.trainNumber.equalsIgnoreCase(trainNumber)) return t;
        return null;
    }

    static Booking findBooking(String bookingId) {
        for (Booking b : bookings)
            if (b.bookingId.equalsIgnoreCase(bookingId)) return b;
        return null;
    }

    static void viewAllTrains() {
        printHeader("AVAILABLE TRAINS");
        System.out.printf("%-8s  %-22s  %-12s  %-12s  %-8s  %-9s  %-6s  %s%n",
                "Train No", "Name", "From", "To", "Departs", "Arrives", "Seats", "Fare");
        printLine();
        for (Train t : trains) {
            System.out.printf("%-8s  %-22s  %-12s  %-12s  %-8s  %-9s  %-6d  Rs.%.0f%n",
                    t.trainNumber, t.trainName, t.source, t.destination,
                    t.departureTime, t.arrivalTime, t.availableSeats, t.farePerSeat);
        }
    }

    static void searchTrains() {
        printHeader("SEARCH TRAINS");
        String from = readLine("  From (city): ").toLowerCase();
        String to   = readLine("  To   (city): ").toLowerCase();

        List<Train> results = new ArrayList<>();
        for (Train t : trains)
            if (t.source.toLowerCase().contains(from) &&
                t.destination.toLowerCase().contains(to))
                results.add(t);

        if (results.isEmpty()) {
            System.out.println("\n  No trains found for this route.");
            return;
        }

        System.out.println("\n  Found " + results.size() + " train(s):\n");
        System.out.printf("  %-8s  %-22s  %-8s  %-9s  %-6s  %s%n",
                "Train No", "Name", "Departs", "Arrives", "Seats", "Fare");
        System.out.println("  " + "-".repeat(72));
        for (Train t : results) {
            System.out.printf("  %-8s  %-22s  %-8s  %-9s  %-6d  Rs.%.0f%n",
                    t.trainNumber, t.trainName, t.departureTime,
                    t.arrivalTime, t.availableSeats, t.farePerSeat);
        }
    }

    static void bookTicket() {
        printHeader("BOOK TICKET");
        viewAllTrains();

        String trainNo = readLine("\n  Enter Train Number: ");
        Train train = findTrain(trainNo);

        if (train == null) {
            System.out.println("  Train not found."); return;
        }
        if (train.availableSeats == 0) {
            System.out.println("  No seats available on this train."); return;
        }

        String date;
        while (true) {
            date = readLine("  Journey Date (dd-MM-yyyy): ");
            if (isValidDate(date)) break;
            System.out.println("  Invalid date or past date. Format: dd-MM-yyyy");
        }

        int numPassengers;
        while (true) {
            numPassengers = readInt("  Number of Passengers (1-" + train.availableSeats + "): ");
            if (numPassengers >= 1 && numPassengers <= train.availableSeats) break;
            System.out.println("  Enter a number between 1 and " + train.availableSeats);
        }

        List<Passenger> passengers = new ArrayList<>();
        for (int i = 1; i <= numPassengers; i++) {
            System.out.println("\n  -- Passenger " + i + " --");
            String name = readLine("    Name   : ");
            int age;
            while (true) {
                age = readInt("    Age    : ");
                if (age > 0 && age <= 120) break;
                System.out.println("    Enter a valid age (1-120).");
            }
            String gender;
            while (true) {
                gender = readLine("    Gender (M/F/O): ").toUpperCase();
                if (gender.equals("M") || gender.equals("F") || gender.equals("O")) break;
                System.out.println("    Enter M, F, or O.");
            }
            passengers.add(new Passenger(name, age, gender));
        }

        double totalFare = numPassengers * train.farePerSeat;
        System.out.println();
        printLine();
        System.out.println("  BOOKING SUMMARY");
        printLine();
        System.out.println("  Train      : " + train.trainNumber + " - " + train.trainName);
        System.out.println("  Route      : " + train.source + " -> " + train.destination);
        System.out.println("  Date       : " + date);
        System.out.println("  Departure  : " + train.departureTime + "  |  Arrival: " + train.arrivalTime);
        System.out.println("  Passengers : " + numPassengers);
        System.out.printf ("  Total Fare : Rs.%.2f%n", totalFare);
        printLine();

        String confirm = readLine("  Confirm booking? (Y/N): ");
        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("  Booking cancelled."); return;
        }

        Booking booking = new Booking(train.trainNumber, train.trainName,
                train.source, train.destination, date, passengers, totalFare);
        bookings.add(booking);
        train.availableSeats -= numPassengers;

        System.out.println("\n  Booking CONFIRMED!");
        System.out.println("  Your Booking ID: " + booking.bookingId +
                           "  (note this for cancellation / enquiry)");
    }

    static void viewBooking() {
        printHeader("VIEW BOOKING");
        String id = readLine("  Enter Booking ID: ");
        Booking b = findBooking(id);

        if (b == null) {
            System.out.println("  Booking not found."); return;
        }

        System.out.println();
        printLine();
        System.out.println("  Booking ID   : " + b.bookingId);
        System.out.println("  Status       : " + b.status);
        System.out.println("  Train        : " + b.trainNumber + " - " + b.trainName);
        System.out.println("  Route        : " + b.source + " -> " + b.destination);
        System.out.println("  Journey Date : " + b.journeyDate);
        System.out.println("  Passengers   : " + b.passengers.size());
        System.out.println();
        System.out.printf("  %-20s  %-5s  %s%n", "Name", "Age", "Gender");
        System.out.println("  " + "-".repeat(35));
        for (Passenger p : b.passengers) {
            String g = p.gender.equals("M") ? "Male" : p.gender.equals("F") ? "Female" : "Other";
            System.out.printf("  %-20s  %-5d  %s%n", p.name, p.age, g);
        }
        System.out.println();
        System.out.printf("  Total Fare   : Rs.%.2f%n", b.totalFare);
        printLine();
    }

    static void cancelBooking() {
        printHeader("CANCEL BOOKING");
        String id = readLine("  Enter Booking ID to cancel: ");
        Booking b = findBooking(id);

        if (b == null) {
            System.out.println("  Booking not found."); return;
        }
        if (b.status.equals("CANCELLED")) {
            System.out.println("  This booking is already cancelled."); return;
        }

        System.out.println();
        System.out.println("  Booking : " + b.bookingId + " | Train: " + b.trainName +
                           " | Route: " + b.source + " -> " + b.destination +
                           " | Date: " + b.journeyDate);
        System.out.printf ("  Refund  : Rs.%.2f (75%% of Rs.%.2f)%n",
                b.totalFare * 0.75, b.totalFare);

        String confirm = readLine("  Proceed with cancellation? (Y/N): ");
        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("  Cancellation aborted."); return;
        }

        b.status = "CANCELLED";
        Train t = findTrain(b.trainNumber);
        if (t != null) t.availableSeats += b.passengers.size();

        System.out.printf("%n  Booking %s cancelled. Refund of Rs.%.2f will be processed.%n",
                b.bookingId, b.totalFare * 0.75);
    }

    static void viewAllBookings() {
        printHeader("ALL BOOKINGS");
        if (bookings.isEmpty()) {
            System.out.println("  No bookings yet."); return;
        }
        System.out.printf("  %-8s  %-10s  %-22s  %-12s  %-8s  %s%n",
                "Book ID", "Status", "Train", "Date", "Pax", "Fare");
        System.out.println("  " + "-".repeat(78));
        for (Booking b : bookings) {
            System.out.printf("  %-8s  %-10s  %-22s  %-12s  %-8d  Rs.%.0f%n",
                    b.bookingId, b.status, b.trainName,
                    b.journeyDate, b.passengers.size(), b.totalFare);
        }
    }


    public static void main(String[] args) {
        initTrains();

        System.out.println();
        System.out.println("  ==========================================");
        System.out.println("    INDIAN RAILWAY TICKET BOOKING SYSTEM   ");
        System.out.println("  ==========================================");

        while (true) {
            System.out.println();
            System.out.println("  MAIN MENU");
            System.out.println("  ---------------------------------");
            System.out.println("  1. View All Trains");
            System.out.println("  2. Search Trains by Route");
            System.out.println("  3. Book a Ticket");
            System.out.println("  4. View Booking Details");
            System.out.println("  5. Cancel a Booking");
            System.out.println("  6. View All Bookings");
            System.out.println("  7. Exit");
            System.out.println("  ---------------------------------");

            int choice = readInt("  Choose (1-7): ");

            if      (choice == 1) viewAllTrains();
            else if (choice == 2) searchTrains();
            else if (choice == 3) bookTicket();
            else if (choice == 4) viewBooking();
            else if (choice == 5) cancelBooking();
            else if (choice == 6) viewAllBookings();
            else if (choice == 7) {
                System.out.println("\n  Thank you! Goodbye.");
                sc.close();
                System.exit(0);
            } else {
                System.out.println("  Invalid choice. Enter 1-7.");
            }
        }
    }
}
