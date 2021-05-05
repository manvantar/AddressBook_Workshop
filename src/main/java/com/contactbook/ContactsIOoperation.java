package com.contactbook;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ContactsIOoperation {

    /*This method used to write the data of Contacts to a file
     * param it takes contactlist, and filename
     * return boolean value
     */
    public boolean writeToFile(ArrayList<Contact> contactlist, File filename) throws IOException, FileNotFoundException {
        FileWriter fileWriter = new FileWriter(filename);
        try {
            fileWriter.append("firstName,lastName,PhoneNumber,city,state,zip,EmailId"+"\n");
            for (Contact contact: contactlist) {
                fileWriter.append(contact.firstName+",");
                fileWriter.append(contact.lastName+",");
                fileWriter.append(contact.phoneNumber+",");
                fileWriter.append(contact.city+",");
                fileWriter.append(contact.state+",");
                fileWriter.append(String.valueOf(contact.zip)+",");
                fileWriter.append(contact.email+",");
                fileWriter.append("\n");
            }
        } catch (FileNotFoundException e){
            System.out.println("Please enter proper file");
        } catch (IOException e) {
            return false;
        }
        fileWriter.flush();
        fileWriter.close();
        return true;
    }

    /*This method used to read contactsList data from a File
     * @param File to read csv file
     * @return List of Contact
     */
    public  List<Contact> readFromCsv(File fileName) throws IOException,ArrayIndexOutOfBoundsException {
        AddressBook addressBook=new AddressBook();
        try (Reader reader = Files.newBufferedReader(Paths.get(String.valueOf(fileName)));
             CSVReader csvReader = new CSVReader(reader);) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                Contact contact=new Contact(record[0], record[1],record[3],record[4],
                        Integer.parseInt(record[5]),Integer.parseInt(record[2]),record[6]);
                addressBook.addNewContact(contact);
            }
        }
        catch (CsvValidationException e) {  e.printStackTrace();}
        catch (CsvException e) { e.printStackTrace();}
        catch (ArrayIndexOutOfBoundsException e){ e.printStackTrace();}
        return addressBook.contactlist;
    }

}
