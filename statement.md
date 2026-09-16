# Project Statement — Train Ticket Booking System

---

## Problem Statement

Booking a train ticket in India usually involves either standing in a long queue at the station counter or navigating a slow and confusing online portal. For a lot of people, especially those who are not very comfortable with technology, the process can be frustrating. Even for those who are okay with it, things like checking seat availability, managing multiple passengers in one booking, or tracking a cancellation are steps that feel more complicated than they need to be.

From a learning perspective, there is also a gap — most Java beginners practice with very basic programs like calculators or number games, which do not reflect how real applications actually work. There is a need for a project that is simple enough to build as a beginner but still models something from the real world with proper data, logic, and user interaction.

This project tries to address both sides of that. It simulates a railway ticket booking system through a console-based Java application, giving users a clear and step-by-step way to search trains, make bookings, and handle cancellations — while also serving as a practical demonstration of core Java and OOP concepts.

---

## Scope of the Project

This project covers the basic workflow of a ticket booking system — from browsing available trains to confirming or cancelling a booking. It is designed to run as a standalone console application without any database, internet connection, or external library.

What is included:
- A fixed set of pre-loaded train data (8 trains on common Indian routes)
- Searching trains by source and destination
- Booking tickets for one or more passengers with basic input validation
- Auto-generated booking IDs for each reservation
- Viewing booking details and cancelling a booking with a refund calculation
- Tracking seat availability in real time within the session

What is not included:
- No actual payment processing
- No database or file storage — data resets when the program closes
- No login or user account system
- No live train data or API integration
- No GUI — the application is entirely text-based

The scope is kept limited on purpose since the goal is to demonstrate programming concepts in a clear and manageable way, not to build a production-ready system.

---

## Target Users

The primary users of this application are students and learners who want to understand how a real-world booking workflow can be built using Java. It works as a reference project for anyone studying OOP, collections, or console-based application design.

As a simulated system, it can also be used by a general audience who wants to see how train booking works at a logical level — choosing a train, entering passenger details, getting a booking ID, and cancelling if needed. The interface is kept simple and prompt-driven so that anyone who can read and type can use it without any prior technical knowledge.

---

## High-Level Features

**Train Listing**
The system stores details of multiple trains including train number, name, source, destination, departure and arrival times, total seats, and fare. All of this can be viewed at once in a formatted table.

**Route Search**
Users can search for trains by entering a source and destination city. The system filters and shows only the relevant trains, making it easier to find what you need without scrolling through everything.

**Ticket Booking**
Users can book tickets for multiple passengers in a single booking. The system collects each passenger's name, age and gender, validates the journey date, calculates the total fare, and confirms the booking with a unique Booking ID.

**Booking Enquiry**
Any confirmed or cancelled booking can be looked up using the Booking ID. The full details — train, route, date, passenger list and fare — are shown on screen.

**Cancellation and Refund**
A confirmed booking can be cancelled at any time. The system applies a 75% refund policy and automatically adds the seats back to the train's available count.

**Session Booking History**
All bookings made during a session can be viewed together in a summary table showing the booking ID, train name, date, number of passengers, total fare and current status.
