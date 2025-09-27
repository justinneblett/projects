package edu.iu.c212.utils;

import edu.iu.c212.models.Item;
import edu.iu.c212.models.Staff;

import java.io.IOException;
import java.util.List;

public interface IStore {
    List<Item> getItemsFromFile();

    List<Staff> getStaffFromFile();
    void saveItemsFromFile() throws IOException;

    void saveStaffFromFile();
    void takeAction();


}
