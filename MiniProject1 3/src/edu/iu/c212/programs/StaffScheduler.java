package edu.iu.c212.programs;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaffScheduler{

    /**
     * Manages staff scheduling and creates store schedules based on employee availability.
     */

    private List<Employee> employees = new ArrayList<>();


    private Map<String, Integer> dayToHoursMap = new HashMap<>();

    /**
     * Represents an employee and their availability information.
     */
    private static class Employee {
        String firstName;
        String lastName;
        int age;
        String role;
        String daysAvailable;



        /**
         * It checks if the employee can work on a certain day.
         * @param day The day of the week ("W" for Wednesday)
         * @return true if the employee can work on the certain day, else.if not it's false
         */
        public boolean isAvailableOnDay(String day) {
            return daysAvailable.contains(day);
        }



        /**
         * it gets the first letter of the employee's last name.
         * @return The first letter of the last name
         */

        public char getLastNameInitial() {
            return lastName.charAt(0);
        }



        /**
         * Gets the first name of the employee.
         * @return first name
         */

        public String getFirstName() {
            return firstName;
        }




        public Employee(String firstName, String lastName, int age, String role, String daysAvailable) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.role = role;
            this.daysAvailable = daysAvailable;
        }
    }

    /**
     * This function reads staff availability data from a file and incorporates employees into the system.
     * @param staffAvailabilityFile The file path for the staff availability data.
     * @throws IOException if there's an error during the file reading process.
     */













    public void readStaffAvailability(String staffAvailabilityFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(staffAvailabilityFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                if (data.length >= 7) {
                    String firstName = data[0];
                    String lastName = data[1];
                    int age = Integer.parseInt(data[2]);
                    String role = data[3];
                    String daysAvailable = data[4];
                    employees.add(new Employee(firstName, lastName, age, role, daysAvailable));
                } else {
                    System.out.println("Invalid input line: " + line);
                }
            }
        }
    }

    /**
     * This function retrieves shift schedules from a file and adjusts the mapping from days to hours accordingly.
     * @param shiftSchedulesFile The file path for the shift schedule data.
     * @throws IOException if an error arises during the file reading process.
     */


    public void readShiftSchedules(String shiftSchedulesFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(shiftSchedulesFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                if (data.length == 4) {
                    String dayOfWeek = data[0];
                    int openTime = Integer.parseInt(data[1]);
                    int closeTime = Integer.parseInt(data[2]);
                    int hours = closeTime - openTime;
                    dayToHoursMap.put(dayOfWeek, hours);
                } else {
                    System.out.println("Incorrect input line in shift schedules: " + line);
                }
            }
        }
    }












    /**
     * Saves the store schedule to a file according to employee availability.
     * @param storeScheduleFile The file path where the store schedule will be saved.
     * @throws IOException if there's an issue during the file writing process.
     */

    public void writeStoreSchedule(String storeScheduleFile) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(storeScheduleFile))) {

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy 'at' HHmm");
            String timestamp = "Created on " + now.format(formatter);
            writer.write(timestamp);

            String[] daysOfWeek = {"M", "T", "W", "TR", "F", "SAT", "SUN"};
            for (String day : daysOfWeek) {
                writer.print(day + " ");
                for (Employee employee : employees) {
                    if (employee.isAvailableOnDay(day)) {
                        writer.print("(" + employee.getFirstName() + " " + employee.getLastNameInitial() + ") ");
                    }
                }
                writer.println();
            }
        }
    }











    /**
     * Entry point for executing staff scheduling operations.
     * @param args Command-line arguments.
     */


    public static void main(String[] args) {
        StaffScheduler scheduler = new StaffScheduler();
        try {
            scheduler.readStaffAvailability("staff_availability_IN.txt");
            System.out.println("Staff availability read successfully.");

            scheduler.readShiftSchedules("shift_schedules_IN.txt");
            System.out.println("Shift schedules read successfully.");

            scheduler.writeStoreSchedule("store_schedule_OUT.txt");
            System.out.println("Store schedule generated and saved successfully.");

        } catch (IOException e) {
            System.out.println("There's an error reading staff availability file: " + e.getMessage());
        }
    }
}

