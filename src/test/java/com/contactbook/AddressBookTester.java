package com.contactbook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledForJreRange;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class AddressBookTester {
    AddressBook addressBook;
    Contact contact= new Contact("Manu","Kv","Bengaluru","Karnataka",560076
            ,966339366,"manukvshetty@gmail.com");
    Contact contact1=new Contact("Srinivas","Kv","Bengaluru","Karnataka",560076
            ,526157122,"srinivas@gmail.com");
    Contact newContact =new Contact("Pwanu","re","indore","MP",
            21212,6876786,"manushetty7799@gmail.com");
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
}
