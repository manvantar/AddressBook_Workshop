package com.contactbook;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBook {

    public ArrayList<Contact> contactlist = new ArrayList<Contact>();

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
        int bit_add = 0;
        if (contactlist.size() > 0) {
            boolean checkExists=this.checkContactExists(contact);
            if(checkExists==true)
                return false;
        }
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
        addressBook.updateMainMethod();
    }
    public void updateMainMethod(){
        AddressBook addressBook=new AddressBook();
        // addressBook.addNewContactFromConsole();
        // addressBook=new AddressBook();
        Contact contact= new Contact("Manu","Kv","Bengaluru","Karnataka",560076
                ,966339366,"manukvshetty@gmail.com");
        Contact contact1=new Contact("Srinivas","Kv","Bengaluru","Karnataka",560076
                ,526157122,"srinivas@gmail.com");
        addressBook.addNewContact(contact);
        addressBook.addNewContact(contact1);
        Scanner consoleInputReader=new Scanner(System.in);
        System.out.println("Enter the FirstName of the contact to edit");
        String firstName=consoleInputReader.next();
        System.out.println("Enter the LastName of the contact to edit");
        String lastName=consoleInputReader.next();
        Contact oldContact=addressBook.getContact(firstName,lastName);
        if(oldContact==null) {
            System.out.println("contact doesn't exist");
            return;
        }
        Contact newContact =new Contact(consoleInputReader);
        addressBook.updateContact(oldContact,newContact);
    }

    /*This method is used to take input form console and add new contact to AddressBook
    @return boolen value
     */
    public  boolean addNewContactFromConsole(){
        Scanner consoleInputReader=new Scanner(System.in);
        this.contactlist.add(new Contact(consoleInputReader));
        return true;
    }

    /* This method is used to update the contact,only  if exists in the contact book
    @param takes String oldname and newname as an input
    @return boolean value true if updated else false
    */
    public boolean updateContact(Contact oldcontact, Contact newContact) {
        boolean check = checkContactExists(oldcontact);
        if (check == true) {
            if (contactlist.size() > 0) {
                for (int i = 0; i < contactlist.size(); i++) {
                    if (contactlist.get(i).equals(oldcontact)) {
                        contactlist.remove(i);
                        contactlist.add(i, newContact);
                        return true;
                    }
                }
            }
            else
            {
                System.out.println("name not available to edit");
                return false;
            }
        }
        System.out.println("No contacts");
        return false;
    }

    /* This method is used to search the contact,only  if exists in the contact book
    @param takes contacts
    @return boolean value true if present else false
     */
    public boolean checkContactExists(Contact contact) {
        if (contactlist.size() > 0 && contact!=null) {
            int i = 0;
            while (i < contactlist.size()) {
                Contact contactPresent = contactlist.get(i);
                if (contact.equals(contactPresent)) {
                    return true;
                }
                i++;
            }
            return false;
        }
        return false;
    }

    /* This method is used to print the contacts
     */
    public void printContactList() {
        System.out.println("You have " + contactlist.size() + " contacts in your list");
        for (int i = 0; i < contactlist.size(); i++) {
            Contact contact = contactlist.get(i);
            System.out.println(contact);
            System.out.println("================================");
        }
    }

    /* This method is used to search the contact,only  if exists in the contact book
    @param takes FirstName and LastName
    @return contact
     */
    public Contact getContact(String firstName, String lastName) {
        if (contactlist.size() > 0) {
            int i = 0;
            while (i < contactlist.size()) {
                Contact contactPresent = contactlist.get(i);
                if ((firstName+lastName).equals(contactPresent.firstName+contactPresent.lastName)) {
                    System.out.println("Contact Exists");
                    return contactPresent;
                }
                i++;
            }
            return null;
        }
        return null;
    }

    /* This method is used to search the contact,only  if exists in the contact book
    @param takes contacts
    @return contact value true if present else null
     */
    public Contact getContact(Contact newContact) {
        return contactlist.stream().filter(contact -> contact.equals(newContact))
                .findAny()
                .orElse(null);
    }

    /* This method is used to delete the contact,only  if exists in the contact book
    @param takes FirstName and LastName
    @return true if contact deleted else false
     */
    public boolean deleteContact(String firstName, String lastName) {
        if (contactlist.size() > 0) {
            int i = 0;
            while (i < contactlist.size()) {
                Contact contactPresent = contactlist.get(i);
                if ((firstName+lastName).equals(contactPresent.firstName+contactPresent.lastName)) {
                    contactlist.remove(i);
                    return true;
                }
                i++;
            }
            return false;
        }
        return false;
    }

    /* This method is used to add multiple contacts
    @param takes array of contacts
    @return number of contacts added
    */
    public int addMultipleContacts(Contact[] contactsArray) {
        int contactsAdded=0;
        if(contactsArray.length>0){
            int i=0;
            while(i<contactsArray.length){
                boolean checkadded=this.addNewContact(contactsArray[i]);
                if(checkadded==true)
                    contactsAdded++;
                i++;
            }
        }
        return contactsAdded;
    }

    /* This method is used to get size of AddessBook
    return size of contactList
     */
    public int getSizeOfAddressBook(){
        return contactlist.size();
    }

    /*This method used to search contacts in each AddressBookList by particularCity
    @param takes city name
    return List of Contacts in particular city
    */
    public List<Contact> checkContactByStateStream(String city) {
        List<Contact> contactsByCity = contactlist.stream().filter(contac -> contac.city.equals(city))
                .collect(Collectors.toList());
        return contactsByCity;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    /*This method used to search contacts in each AddressBookList by particularState
    @param takes State name
    return List of Contacts in particular State
    */
    public List<Contact> getContactsByState(String sample_state) {
        List<Contact> contactsByState = contactlist.stream().filter(contac -> contac.state.equals(sample_state))
                .collect(Collectors.toList());
        return contactsByState;
    }

    /*This method used to get contacts in each AddressBookList by particularState
    @param takes State name
    return count of Contacts in particular State
    */
    public int getCountOfContactsByState(String State) {
        List<Contact> contactList=getContactsByState(State);
        return contactList.size();
    }

    /*This method used to get Sorted contacts by FirstName
     * return sorted contactslist
     */
    public List<Contact> sortByNames() {
        List<Contact> newContactList=new ArrayList<>(contactlist);
        newContactList.sort(Comparator.comparing(Contact::getFirstName));
        return newContactList;
    }

    /*This method used to get Sorted contacts by State
     * return sorted contactslist by state
     */
    public List<Contact> sortByStates() {
        List<Contact> newContactList=new ArrayList<>(contactlist);
        newContactList.sort(Comparator.comparing(Contact::getState));
        return newContactList;
    }

    /*This method used to get Sorted contacts by ZIP
     * return sorted contactslist by ZIP
     */
    public List<Contact> sortByZIP() {
        List<Contact> newContactList=new ArrayList<>(contactlist);
        newContactList.sort(Comparator.comparing(Contact::getZip));
        return newContactList;
    }
}
