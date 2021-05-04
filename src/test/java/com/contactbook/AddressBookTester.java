package com.contactbook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AddressBookTester {
    AddressBook addressBook;

    @Test
    public void whenGivenAddressbook_whenAdded_shouldReturnContactList(){
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
}
