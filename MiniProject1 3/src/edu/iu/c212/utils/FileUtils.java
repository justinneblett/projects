package edu.iu.c212.utils;

import edu.iu.c212.models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtils {
    private static File inputFile = new File("MiniProject1\\src\\edu\\iu\\c212\\resources\\input.txt");
    private static File outputFile = new File("MiniProject1\\src\\edu\\iu\\c212\\resources\\output.txt");
    private static File inventoryFile = new File("MiniProject1\\src\\edu\\iu\\c212\\resources\\inventory.txt");
    private static File staffFile = new File("MiniProject1\\src\\edu\\iu\\c212\\resources\\staff.txt");
    private static File staffAvailabilityFile = new File("MiniProject1\\src\\edu\\iu\\c212\\resources\\staff_availability_IN.txt");
    private static File shiftSchedulesFile = new File("MiniProject1\\src\\edu\\iu\\c212\\resources\\shift_schedules_IN.txt");
    private static File storeScheduleFile = new File("MiniProject1\\src\\edu\\iu\\c212\\resources\\shift_schedules_OUT.txt");


    /**
     * Reads items from the inventory text file.
     * @return An ArrayList containing the items listed in the text file.
     * @throws IOException if there's an error during the file reading process.
     */






    public static List<Item> readInventoryFromFile() throws IOException {
        System.out.println(inventoryFile/*.toURI()*/.getPath() + "\n" + inventoryFile.exists());
        // depending on your OS, toURI() may need to be used when working with paths
        List<Item> items = new ArrayList<>();
        Scanner sc = new Scanner(inventoryFile);
        while (sc.hasNextLine()){
            String one = sc.nextLine();
            String [] parts = one.split(",");
            String name = parts[0];
            double price = Double.parseDouble(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int aisleNo = Integer.parseInt(parts[3]);
            items.add(new Item(name, price, quantity, aisleNo));
        }
        sc.close();
        return items;
    }







    /**
     * Reads employee data from the staff availability text file.
     * @return An ArrayList containing the employees listed in the file.
     * @throws IOException if there's an error during the file reading process.
     */

    public static List<Staff> readStaffFromFile() throws IOException {
        List<Staff> staff = new ArrayList<>();
        Scanner st = new Scanner((staffAvailabilityFile));
        while (st.hasNextLine()){
            String info = st.nextLine();
            String[] parts = info.split("\\s+");

            String fullName = parts[0] + " " + parts[1];
            int age = Integer.parseInt(parts[2]);
            String role = parts[3];
            if (role.compareTo("M") == 0){
                role = "Manager";
            } else if (role.compareTo("C") == 0) {
                role = "Cashier";
            } else if (role.compareTo("G") == 0) {
                role = "Gardening Expert";
            }
            String avail = parts[4];
            staff.add(new Staff(fullName, age, role, avail));
        }
        return staff;

    }

    /**
     * Writes updated inventory data to the inventory file.
     * @param items The list of items to be written into the file.
     * @throws IOException if there's an error during the file writing process.
     */


    public static void writeInventoryToFile(List<Item> items) throws IOException {
        FileWriter wrt = null;
        try {
            wrt = new FileWriter(inventoryFile);
            for (Item item : items) {
                wrt.write(item.getName() + "," +
                        item.getPrice() + "," +
                        item.getQuantity() + "," +
                        item.getAisleNum() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        wrt.close();

    }
    /**
     * This method writes data into staff.
     * @param employees is the list of values from which writes the items.
     */


    public static void writeStaffToFile(List<Staff> employees) {
        for (Staff e : employees) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(staffFile))) {
                String temp = e.toString();
                pw.println(temp);
            } catch (IOException ex) {
                System.out.println("IO Exception");
            }
        }
    }











    public static List<String> readCommandsFromFile() throws IOException {
        List<String> commands = new ArrayList<>();
        Scanner sc = new Scanner((inputFile));
        while (sc.hasNextLine()) {
            String one = sc.nextLine();
            commands.add(one);
        }
        sc.close();
        return commands;

    }

    public static void writeStoreScheduleToFile(List<String> lines) {
        for (String str : lines) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(storeScheduleFile))) {
                pw.println(str);
            } catch (IOException ex) {
                System.out.println("IO Exception");
            }
        }

    }









    public static void writeLineToOutputFile(String line) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(outputFile, true))) {
            pw.println(line);
        } catch (IOException ex) {
            System.out.println("IO Exception");
        }
    }

}
