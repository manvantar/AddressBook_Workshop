package com.contactbook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.PublicKey;
import java.sql.SQLException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContactDBTester {
    AddressBook addressBook = new AddressBook();
    Contact contact1 = new Contact("Manu", "KV", "Bengaluru", "Karnataka", 560076, 2121232, "manukvshetty@gmail.com");
    Contact contact2 = new Contact("Srinivar", "KV", "Bengaluru", "Karnataka", 560076, 2121232, "srinivar@gmail.com");
    Contact contact3 = new Contact("inivar", "KV", "Mysuru", "Karnataka", 560076, 2121232, "nivar@gmail.com");
    Contact contact4 = new Contact("iar", "KV", "Vizak", "Andhra", 780076, 2121232, "iar@gmail.com");

    @Test
    public void givenQueryToFetchData_whenMatctedWithExistingList_shouldReturnTrue() throws SQLException {
        addressBook.addMultipleContacts(new Contact[]{contact1, contact2, contact3, contact4});
        AddressBook addressBook2 = new AddressBook();
        List<Contact> contactList = addressBook2.getDataFromDB();
        Assertions.assertEquals(contactList, addressBook.contactlist);
    }

    @Test
    public void givenQueryToFetchData_whenMatctedWithNONExistingList_shouldReturnTrue() throws SQLException {
        addressBook.addMultipleContacts(new Contact[]{contact1, contact2, contact3});
        AddressBook addressBook2 = new AddressBook();
        List<Contact> contactList = addressBook2.getDataFromDB();
        Assertions.assertNotEquals(contactList, addressBook.contactlist);
    }

    @BeforeEach
    public void loaddata() {
        addressBook.addMultipleContacts(new Contact[]{contact1, contact2, contact3});
    }

    @Test
    public void givenContactUpdateDetails_whenUpdated_shouldReturnTrue() throws SQLException {
        boolean check = addressBook.updatePhoneNumberDB("Manu", "KV", 87876890);
        Assertions.assertTrue(check);
    }

    @Test
    public void givenContactUpdateDetails_whenSyncWithDatabase_shouldReturnTrue() throws SQLException {
        addressBook.updatePhoneNumberDB("Manu", "KV", 2121232);
        Assertions.assertEquals(87872121,addressBook.contactlist.get(0).phoneNumber);
    }

    @Test
    public void givenContactUpdateDetails_whenUpdatedNonExistingContact_shouldReturnFalse() throws SQLException {
        boolean check = addressBook.updatePhoneNumberDB("anu", "KV", 87876890);
        Assertions.assertTrue(!check);
    }

    @Test
    public void givenDateToFetchContacts_whenExecutedQuery_shouldReturnContacts() throws SQLException {
        LocalDate startDate=LocalDate.of(2021,04,19);
        LocalDate endDate=LocalDate.of(2021,04,30);
        List<Contact> contactListbyDate=addressBook.getContactDBbyDate(startDate,endDate);
        Assertions.assertEquals(contact1,contactListbyDate.get(0));
    }

}