package com.contactbook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledForJreRange;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class AddressBookTester {
    AddressBook addressBook;
    Contact contact= new Contact("Manu","Kv","Bengaluru","Andhra",212121
            ,966339366,"manukvshetty@gmail.com");
    Contact contact1=new Contact("Srinivas","Kv","Bengaluru","MP",560076
            ,526157122,"srinivas@gmail.com");
    Contact newContact =new Contact("Pavan","re","indore","Kerala",
            100076  ,6876786,"manushetty7799@gmail.com");
    Contact newContact2 =new Contact("Manoj","re","indore","MP",
            21212,6876786,"manushetty7799@gmail.com");
    Contact contact6= new Contact("Santhosh","Kv","Bengaluru","Karnataka",560076
            ,212139366,"manukvshetty@gmail.com");
    AddressBook addressBookFamily=new AddressBook();
    AddressBook addressBookFriends=new AddressBook();
    MultipleAdressBookContainer multipleAdressBookContainer=new MultipleAdressBookContainer();

    @Test
    public void givenAddressbook_whenAdded_shouldReturnContactList(){
        addressBook=new AddressBook();
        addressBook.message();
        Contact contact= new Contact("Manu","Kv","Bengaluru","Karnataka",560076
                ,966339366,"manukvshetty@gmail.com");
        Contact contact1=new Contact("Srinivas","Kv","Bengaluru","Karnataka",560076
                ,526157122,"srinivas@gmail.com");
        addressBook.addNewContact(contact);
        addressBook.addNewContact(contact1);
        ArrayList<Contact> contactList= addressBook.getContactlist();
        Assertions.assertEquals(2,contactList.size());
    }


    @Test
    public void givenContactsDataFromConsole_WhenAdded_shouldReturnTrue(){
        addressBook=new AddressBook();
        Assertions.assertTrue(addressBook.addNewContactFromConsole());
    }

    @Test
    public  void givenContactsData_whenAdded_shouldReturnTrue(){
        addressBook=new AddressBook();
        addressBook.addNewContact(contact);
        addressBook.addNewContact(contact1);
        Contact oldContact=addressBook.getContact("Manu","Kv");
        Contact oldContact2=addressBook.getContact("gurasa","Er");
        Assertions.assertEquals(null,oldContact2);
        Assertions.assertTrue(addressBook.updateContact(oldContact,newContact));
        Assertions.assertEquals(newContact,addressBook.getContact(newContact));
        Assertions.assertEquals(false,addressBook.updateContact(oldContact2,newContact2));
    }

    @Test
    public  void givenContactsData_whenDeletedContact_shouldReturnDeletedContact(){
        addressBook =new AddressBook();
        addressBook.addNewContact(contact);
        addressBook.addNewContact(contact1);
        boolean positiveResult=addressBook.deleteContact("Manu","Kv");
        Assertions.assertTrue(positiveResult);
        boolean negativeResult2=addressBook.deleteContact("Manu","Kv");
        Assertions.assertEquals(false,negativeResult2);
    }

    @Test
    public  void givenMultipleContactsData_whenAddedContact_shouldReturnSizeOfAddressBook(){
        addressBook =new AddressBook();
        Contact[] contactsArray={contact,contact1,newContact,newContact2,contact};
        int numberOfContactsAdded=addressBook.addMultipleContacts(contactsArray);
        Assertions.assertEquals(4,numberOfContactsAdded);
    }

    @Test
    public void givenMultipleAddressBooks_whenAdded_shouldReturnNumberOfAddressBooks(){
        Contact[] contactsArray={contact,contact1,newContact,contact};
        Contact[] contactsArray2={contact,contact1,contact6,newContact2};
        addressBookFamily.addMultipleContacts(contactsArray);
        addressBookFriends.addMultipleContacts(contactsArray2);
        multipleAdressBookContainer.addAddressBookList("Family",addressBookFamily);
        multipleAdressBookContainer.addAddressBookList("Friends",addressBookFriends);
        Assertions.assertEquals(2,addressBookFriends.contactlist.size());
        Assertions.assertEquals(addressBookFamily,multipleAdressBookContainer.addressBookDictionary.get("Family"));
    }

    @Test
    public void givenMultipleAddressBooks_whenSearchedForParticularCity_shouldReturnContacts(){
        Contact[] contactsArray={contact,contact1,newContact,contact};
        Contact[] contactsArray2={contact,contact1,contact6,newContact2};
        addressBookFamily.addMultipleContacts(contactsArray);
        addressBookFriends.addMultipleContacts(contactsArray2);
        multipleAdressBookContainer.addAddressBookList("Family",addressBookFamily);
        multipleAdressBookContainer.addAddressBookList("Friends",addressBookFriends);
        List<Contact> contactListInCity=multipleAdressBookContainer.getContactsByCity("indore");
        Assertions.assertEquals("indore",contactListInCity.get(0).city);
        Assertions.assertEquals(2,contactListInCity.size());
        List<Contact> contactListInCity2=multipleAdressBookContainer.getContactsByCity("mysore");
        Assertions.assertEquals(0,contactListInCity2.size());
    }

    @Test
    public void givenMultipleAddressBooks_whenSearchedForParticularState_shouldReturnContacts2(){
        Contact[] contactsArray={contact,contact1,newContact,contact};
        addressBookFriends.addMultipleContacts(contactsArray);
        List<Contact> contactListInState=addressBookFriends.getContactsByState("MP");
        Assertions.assertEquals("MP",contactListInState.get(0).state);
        List<Contact> contactListInState2=addressBookFriends.getContactsByState("kerala");
        Assertions.assertEquals(0,contactListInState2.size());

    }

    @Test
    public void givenMultipleContactsBooks_whenSearchedForParticularState_shouldReturnCountOfContacts(){
        Contact[] contactsArray={contact,contact1,newContact,contact};
        addressBookFriends.addMultipleContacts(contactsArray);
        int countByState=addressBookFriends.getCountOfContactsByState("MP");
        int countByState2=addressBookFriends.getCountOfContactsByState("kerala");
        Assertions.assertEquals(1,countByState);
        Assertions.assertEquals(0,countByState2);
    }

    @BeforeEach
    public void load() {
        Contact[] contactsArray = {contact, contact1, newContact};
        addressBookFriends.addMultipleContacts(contactsArray);
    }
    @Test
    public void givenMultipleContactsBooks_whenAskedForSortingByFirstName_shouldReturnContactsInSortedFormat() {
        List<Contact> contcatListSortedByNames=addressBookFriends.sortByNames();
        Assertions.assertEquals("Manu",contcatListSortedByNames.get(0).firstName);
        Assertions.assertEquals("Pavan",contcatListSortedByNames.get(1).firstName);
        Assertions.assertEquals("Srinivas",contcatListSortedByNames.get(2).firstName);
    }

    @Test
    public void givenMultipleContactsBooks_whenAskedForSortingByState_shouldReturnContactsInSortedFormat() {
        List<Contact> contcatListSortedByStates=addressBookFriends.sortByStates();
        Assertions.assertEquals("Andhra",contcatListSortedByStates.get(0).state);
        Assertions.assertEquals("Kerala",contcatListSortedByStates.get(1).state);
        Assertions.assertEquals("MP",contcatListSortedByStates.get(2).state);
    }

    @Test
    public void givenMultipleContactsBooks_whenAskedForSortingByZIP_shouldReturnContactsInSortedFormat() {
        List<Contact> contcatListSortedByZIP=addressBookFriends.sortByZIP();
        Assertions.assertEquals(100076,contcatListSortedByZIP.get(0).zip);
        Assertions.assertEquals(212121,contcatListSortedByZIP.get(1).zip);
        Assertions.assertEquals(560076,contcatListSortedByZIP.get(2).getZip());
    }


    @Test
    public void givenMultipleContactsBooks_whenAskedToWriteToFile_shouldDeleteTheFileIfExistsThenWriteReturnTrue() throws IOException {
        File fileName= new File("/home/mkv/Desktop/contacts.csv");
        if(fileName.exists())
            fileName.delete();
        Assertions.assertTrue(!fileName.exists());
        Assertions.assertTrue(addressBookFriends.WriteToFile(fileName));
    }

    @Test
    public void givenFileName_whenChecked_shouldReturnTrueIfExists(){
        File fileName= new File("/home/mkv/Desktop/contacts.csv");
        Assertions.assertTrue(fileName.exists());
    }

    @Test
    public void givenFileNameOfCSV_whenLoadedUsingCSVreader_shouldReturnAddressBook() throws IOException {
        File fileName= new File("/home/mkv/Desktop/contacts.csv");
        AddressBook addressBook=new AddressBook();
        List<Contact> contactList=addressBook.readFromFileCSVfileusingOpenCSV(fileName);
        Assertions.assertEquals(contact,contactList.get(0));
    }
}
