package ru.job4j.cinemaservice.model;

/**
 * Account entity.
 */
public class Account extends Model {
   private String firstName;
   private String middleName;
   private String lastName;
   private int phoneNumber;

   public Account() { }

   public Account(int id, String name) {
        super(id, name);
   }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
