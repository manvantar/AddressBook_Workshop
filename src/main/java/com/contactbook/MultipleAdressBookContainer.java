package com.contactbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

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
}
