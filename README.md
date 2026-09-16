# Train Ticket Booking System

## Project Title
Train Ticket Booking System (Console-based Java Application)

---

## Overview

This project is a simple train ticket booking system built in Java. The idea came from wanting to apply what we learned in our Java programming course to something practical and relatable. Almost everyone has booked a train ticket at some point, so it felt like a good real-world use case to work with.

The application runs entirely in the terminal. Users can check available trains, search by their route, book tickets for one or more passengers, view their booking, and cancel it if needed. There is no database — everything is stored in memory while the program is running, which keeps it simple and focused on the core logic.

The whole project is written in a single Java file with no external dependencies.

---

## Features

- View all available trains with route, departure/arrival times, seat count and fare
- Search trains by entering source and destination city
- Book tickets for multiple passengers in one go
- Auto-generated Booking ID for each confirmed reservation
- Input validation for dates (rejects past dates and invalid formats), age, gender etc.
- View complete booking details using the Booking ID
- Cancel a booking — 75% refund is calculated and seats are freed up automatically
- View a list of all bookings made in the current session

---

## Technologies and Tools Used

- **Language:** Java (JDK 8 or above)
- **Library:** `java.util.*` only — no third-party libraries
  - `ArrayList`, `List` — for storing trains, bookings, passengers
  - `Scanner` — for taking user input
  - `Calendar` — for validating journey dates
- **IDE used:** (mention your IDE here — e.g. VS Code / IntelliJ / Eclipse / Notepad++)
- **Compiler:** `javac` (comes with the JDK)

---

## Steps to Install and Run

**Step 1 — Make sure Java is installed**

Open your terminal or command prompt and type:
```
java -version
```
If you see a version number, you're good to go. If not, download the JDK from https://www.oracle.com/java/technologies/downloads/ and install it.

**Step 2 — Get the source file**

Copy or download `TrainTicketBooking.java` and place it in a folder, for example:
```
C:\Users\Pranav\TrainProject\TrainTicketBooking.java
```

**Step 3 — Open terminal in that folder**

On Windows you can Shift + Right-click the folder and choose "Open PowerShell window here". On Linux/Mac just `cd` into the folder.

**Step 4 — Compile the file**
```
javac TrainTicketBooking.java
```
This will create a `TrainTicketBooking.class` file in the same folder.

**Step 5 — Run the program**
```
java TrainTicketBooking
```
The main menu will show up and the program is ready to use.

---

## Testing Instructions

Here are a few test cases you can try to verify everything works correctly.

**Test 1 — View trains**

Select option `1` from the menu. You should see a list of 8 trains displayed in a table with train number, name, source, destination, timings, available seats and fare per seat.

**Test 2 — Search by route**

Select option `2`, enter `Delhi` as the source and `Mumbai` as the destination. The program should show only the trains that match that route.

**Test 3 — Book a ticket**

Select option `3`. Enter train number `12301`, give a future date like `25-12-2026`, and book for 2 passengers. Fill in the name, age and gender for each. Confirm with `Y`. You should get a Booking ID like `BK1001` and see the total fare displayed.

**Test 4 — View a booking**

Select option `4` and enter the Booking ID from the previous step (e.g. `BK1001`). You should see all the passenger details, route, date and fare.

**Test 5 — Cancel a booking**

Select option `5`, enter the same Booking ID, and confirm with `Y`. The status should change to CANCELLED and a 75% refund amount should be shown. If you go back to view all trains, the seats should be restored.

**Test 6 — Invalid date check**

Try booking a ticket and enter `31-02-2026` as the date. The program should reject it and ask again. Then try a past date like `01-01-2020` — same thing, it should not accept it.

**Test 7 — View all bookings**

Select option `6` to see a summary of everything booked so far in the session, including the one we cancelled. The status column should show `CONFIRMED` or `CANCELLED` accordingly.

---

## Author

Pranav  
B.Tech Computer Science  
[Your College Name]  
[Academic Year]
