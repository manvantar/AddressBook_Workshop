package com.contactbook;

import java.util.*;
import java.util.stream.Collectors;

public class MultipleAdressBookContainer {

    List<AddressBook> addressBookList = new ArrayList<>();
    HashMap<String, AddressBook> addressBookDictionary = new HashMap<>();

    /*This method used to add AddressBook to AddressBookList
    @param takes addressbook
    */
    public void addAddressBookList(String addressBookName, AddressBook addressBook) {
        checkContactsExists(addressBookName, addressBook);
    }

    /*This method used to add AddressBook to AddressBookList
    @param takes addressbook
    return boolean value afterAdding
     */
    private boolean checkContactsExists(String addressBookName, AddressBook addressBook) {
        if (addressBookDictionary.size() > 0) {
            String key[] = addressBookDictionary.keySet().toArray(new String[0]);
            for (int i = 0; i < addressBookDictionary.size(); i++) {
                AddressBook tempAddressBook = addressBookDictionary.get(key[i]);
                for (int j = 0; j < tempAddressBook.contactlist.size(); j++) {
                    int found = 0;
                    for (int k = 0; k < addressBook.contactlist.size() && found == 0; k++) {
                        if (tempAddressBook.contactlist.get(j).equals(addressBook.contactlist.get(k))) {
                            addressBook.contactlist.remove(k);
                            found = 1;
                        }
                    }
                }
            }
        }
        if (addressBook.contactlist.size() > 0) {
            addressBookDictionary.put(addressBookName, addressBook);
            return true;
        } else
            return false;
    }

    /*This method used to search contacts in each AddressBookList by particularCity
    @param takes city name
    return List of Contacts in particular city
    */
    public List<Contact> getContactsByCity(String city) {
        List<Contact> contactList = new ArrayList<>();
        if (addressBookDictionary.size() > 0) {
            String key[] = addressBookDictionary.keySet().toArray(new String[0]);
            for (int i = 0; i < addressBookDictionary.size(); i++) {
                AddressBook tempAddressBook = addressBookDictionary.get(key[i]);
                for (int j = 0; j < tempAddressBook.contactlist.size(); j++) {
                    if (tempAddressBook.contactlist.get(j).city.equals(city)) {
                        contactList.add(tempAddressBook.contactlist.get(j));
                    }
                }
            }
        }
        return contactList;
    }

    /*This method used to search contacts in each AddressBookList by particularCity
    @param takes city name
    return List of Contacts in particular city
    */
    public List<Contact> getContactsByCityByStreams(String city) {
        List<Contact> contactList1 = null;
        if (addressBookDictionary.size() > 0) {
            String key[] = addressBookDictionary.keySet().toArray(new String[0]);
            for (int i = 0; i < addressBookDictionary.size(); i++) {
                AddressBook tempAddressBook = addressBookDictionary.get(key[i]);
                List<Contact> contactList=tempAddressBook.contactlist;
                List<Contact> cityContacts=checkContactsbyCity(contactList,city);
                contactList1.addAll(cityContacts);
            }
        }
        return contactList1;
    }

    private List<Contact> checkContactsbyCity(List<Contact> contactlist, String sample_city) {
        List<Contact> contactListsa=contactlist.stream().filter(contact -> contact.getCity().equals(sample_city)).
                collect(Collectors.toList());
        return contactListsa;

    }
}
