package com.contactbook;

import java.util.Objects;
import java.util.Scanner;

public class Contact {
    String firstName;
    String lastName;
    String city;
    String state;
    int zip;
    long phoneNumber;
    String email;
    String type;

    /* Constructor to initialize global variables
    @param firstname,lastname,city,state,zip,PhoneNumber,email
     */
    public Contact(String firstName, String lastName, String city, String state, int zip, long mob, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = mob;
        this.email = email;
    }

    /* Constructor to initialize global variables
    @param firstname,lastname,city,state,zip,PhoneNumber,email,type
     */
    public Contact(String firstName, String lastName, String city, String state, int zip, long mob, String email,String type){
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = mob;
        this.email = email;
        this.type=type;
    }

    /* Constructor to initialize global variables
    @param Scanner class Object
     */
    public Contact(Scanner consoleinput){
        System.out.println("Please Enter firstName");
        this.firstName = consoleinput.next();
        System.out.println("Enter lastName");
        this.lastName=consoleinput.next();
        System.out.println("Enter city Name");
        this.state=consoleinput.next();
        System.out.println("Enter stateName");
        this.state=consoleinput.next();
        System.out.println("Enter zip");
        this.zip=consoleinput.nextInt();
        System.out.println("Enter PhoneNumber");
        this.phoneNumber=consoleinput.nextLong();
        System.out.println("Enter Email");
        this.email=consoleinput.next();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCity() {
        return city;
    }

    public String getLastName() {
        return lastName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public float getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return zip == contact.zip && phoneNumber == contact.phoneNumber &&
                Objects.equals(firstName, contact.firstName) && Objects.equals(lastName, contact.lastName)
                && Objects.equals(city, contact.city) && Objects.equals(state, contact.state) &&
                Objects.equals(email, contact.email) && Objects.equals(type, contact.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, city, state, zip, phoneNumber, email, type);
    }
}

