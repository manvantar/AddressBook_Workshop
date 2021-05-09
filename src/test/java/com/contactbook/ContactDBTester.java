package com.contactbook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactDBTester {
    AddressBook addressBook = new AddressBook();
    Contact contact1=new Contact("Manu" ,"KV","Bengaluru" , "Karnataka" , 560076 ,2121232, "manukvshetty@gmail.com");
    Contact contact2=new Contact("Srinivar","KV","Bengaluru" , "Karnataka" , 560076 ,2121232, "srinivar@gmail.com");
    Contact contact3=new Contact("inivar","KV","Mysuru"    , "Karnataka" , 560076 ,2121232, "nivar@gmail.com");
    Contact contact4=new Contact("iar","KV", "Vizak"    , "Andhra"    , 780076 ,2121232, "iar@gmail.com");

    @Test
    public void givenQueryToFetchData_whenMatctedWithExistingList_shouldReturnTrue() throws SQLException {
        addressBook.addMultipleContacts(new Contact[]{contact1, contact2, contact3, contact4});
        AddressBook addressBook2 = new AddressBook();
        List<Contact> contactList = addressBook2.getDataFromDB();
        Assertions.assertEquals(contactList,addressBook.contactlist);
    }

    @Test
    public void givenQueryToFetchData_whenMatctedWithNONExistingList_shouldReturnTrue() throws SQLException {
        addressBook.addMultipleContacts(new Contact[]{contact1, contact2, contact3});
        AddressBook addressBook2 = new AddressBook();
        List<Contact> contactList = addressBook2.getDataFromDB();
        Assertions.assertNotEquals(contactList,addressBook.contactlist);
    }

}
