package edu.iu.c212.utils;

import edu.iu.c212.models.Item;
import edu.iu.c212.models.Staff;
import edu.iu.c212.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Store implements IStore {
    private final List<Staff> staff;
    private final List<Item> items;

    public Store(){
        staff = getStaffFromFile();
        items = getItemsFromFile();
        takeAction();
    }






    @Override
    public List<Item> getItemsFromFile() {
        try {
            return FileUtils.readInventoryFromFile();
        } catch (IOException e) {
            throw new RuntimeException("E", e);
        }
    }







    @Override
    public List<Staff> getStaffFromFile() {

        try {
            return  FileUtils.readStaffFromFile();
        } catch (IOException e) {
            throw new RuntimeException("e", e);
        }
    }





    @Override
    public void saveItemsFromFile() throws IOException {
        FileUtils.writeInventoryToFile(items);
    }





    @Override
    public void saveStaffFromFile() {
        FileUtils.writeStaffToFile(staff);
    }



    @Override
    public void takeAction() {
        try {

            List<String> commands = FileUtils.readCommandsFromFile();


            for (String command : commands) {
                String[] parts = command.split(" ");
                String action = parts[0];


                switch (action) {
                    case "ADD":
                        if (parts.length == 6) {
                            String itemName = parts[1] + " " + parts[2];
                            double itemCost = Double.parseDouble(parts[3]);
                            int itemQuantity = Integer.parseInt(parts[4]);
                            int itemAisle = Integer.parseInt(parts[5]);
                            items.add(new Item(itemName, itemCost, itemQuantity, itemAisle));
                            FileUtils.writeLineToOutputFile(itemName + " was added to inventory");
                        } else {
                            FileUtils.writeLineToOutputFile("Invalid ADD command: " + command);
                        }
                        break;
                    case "COST":
                        if (parts.length == 2 || parts.length == 3) {
                            String itemName = parts[1];
                            boolean found = false;
                            for (Item item : items) {
                                if (item.getName().equals(itemName)) {
                                    FileUtils.writeLineToOutputFile(itemName + ": $" + item.getPrice());
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                FileUtils.writeLineToOutputFile("ERROR: " + itemName + " cannot be found");
                            }
                        } else {
                            FileUtils.writeLineToOutputFile("Invalid COST command: " + command);
                        }
                        break;
                    case "FIND":
                        if (parts.length > 1) {
                            String itemName = command.replace("FIND ", "").replace("'", "");
                            boolean found = false;
                            for (Item item : items) {
                                if (item.getName().equals(itemName)) {
                                    FileUtils.writeLineToOutputFile(item.getQuantity() + " " + itemName + " are available in " + item.getAisleNum());
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                FileUtils.writeLineToOutputFile("ERROR: " + itemName + " cannot be found");
                            }
                        } else {
                            FileUtils.writeLineToOutputFile("Invalid FIND command was put in: " + command);
                        }
                        break;
                    case "FIRE":
                        if (parts.length == 3) {
                            String staffName = parts[1] + " " + parts[2];
                            boolean found = false;
                            for (Staff staff : staff) {
                                if (staff.getFullName().equals(staffName)) {
                                    FileUtils.writeLineToOutputFile(staffName + " was fired");
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                FileUtils.writeLineToOutputFile("ERROR: " + staffName + " cannot be found");
                            }
                        } else {

                            FileUtils.writeLineToOutputFile("Invalid FIRE command: " + command);
                        }
                        break;
                    case "HIRE":
                        if (parts.length == 5) {
                            String staffName = parts[1].replace("'", "");
                            int age = Integer.parseInt(parts[3]);
                            String role = parts[4];
                            staff.add(new Staff(staffName, age, role));
                            FileUtils.writeLineToOutputFile(staffName + " has been hired as a " + role);
                        } else {
                            FileUtils.writeLineToOutputFile("Invalid HIRE command: " + command);
                        }
                        break;
                    case "PROMOTE":
                        if (parts.length == 4) {
                            String staffName = parts[1].replace("'", "");
                            String newRole = parts[3];
                            boolean found = false;
                            for (Staff staff : staff) {
                                if (staff.getFullName().equals(staffName)) {
                                    staff.setRole(newRole);
                                    FileUtils.writeLineToOutputFile(staffName + " was promoted to " + newRole);
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                FileUtils.writeLineToOutputFile("ERROR: " + staffName + " cannot be found");
                            }
                        } else {
                            FileUtils.writeLineToOutputFile("Invalid PROMOTE command: " + command);
                        }
                        break;
                    case "SAW":
                        FileUtils.writeLineToOutputFile("Planks sawed.");
                        break;
                    case "SCHEDULE":
                        FileUtils.writeLineToOutputFile("Schedule created.");
                        break;
                    case "SELL":
                        if (parts.length == 3) {
                            String itemName = parts[1].replace("'", "");
                            int quantity = Integer.parseInt(parts[2]);
                            boolean found = false;
                            for (Item item : items) {
                                if (item.getName().equals(itemName)) {
                                    if (item.getQuantity() >= quantity) {
                                        item.setQuantity(item.getQuantity() - quantity);
                                        FileUtils.writeLineToOutputFile(quantity + " " + itemName + " was sold");
                                    } else {
                                        FileUtils.writeLineToOutputFile("ERROR: " + itemName + " could not be sold");
                                    }
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                FileUtils.writeLineToOutputFile("ERROR: " + itemName + " could not be sold");
                            }
                        } else {
                            FileUtils.writeLineToOutputFile("Invalid SELL command: " + command);
                        }
                        break;
                    case "QUANTITY":
                        if (parts.length == 2) {
                            String itemName = parts[1].replace("'", "");
                            boolean found = false;
                            for (Item item : items) {
                                if (item.getName().equals(itemName)) {
                                    FileUtils.writeLineToOutputFile("You have " + String.valueOf(item.getQuantity()) + " " + itemName);
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                FileUtils.writeLineToOutputFile("ERROR: " + itemName + " cannot be found");
                            }
                        } else {
                            FileUtils.writeLineToOutputFile("Invalid QUANTITY command: " + command);
                        }
                        break;
                    case "EXIT":
                        System.out.println("Thank you for visiting High’s Hardware and Gardening!");
                        System.exit(0);
                        break;
                    default:
                        // Invalid command
                        System.out.println("Invalid command: " + command);
                        break;


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

}




