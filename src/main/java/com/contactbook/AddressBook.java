package com.contactbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBook {

    ArrayList<Contact> contactlist = new ArrayList<Contact>();

    /* this method is used to display welcom message
     */
    public void message(){
        System.out.println("Welcome to Address book");
    }

    /* This method is used to add the contact, if already exists in the contact book
   not adding else adding
   @param takes contact input
   @return boolean value true if added else false
    */
    public boolean addNewContact(Contact contact) {
        contactlist.add(contact);
        return true;
    }

    /*This method is used to return Arraylist of contacts
     */
    public ArrayList<Contact> getContactlist(){
        return contactlist;
    }

    public static void main(String[] args) {
        AddressBook addressBook=new AddressBook();
        addressBook.addNewContactFromConsole();
    }

    /*This method is used to take input form console and add new contact to AddressBook
    @return boolen value
     */
    public boolean addNewContactFromConsole(){
        Scanner consoleInputReader=new Scanner(System.in);
        contactlist.add(new Contact(consoleInputReader));
        return true;
    }
}
