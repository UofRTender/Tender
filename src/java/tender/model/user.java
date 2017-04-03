/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tender.model;

import java.util.HashMap;

/**
 *
 * @author marlon
 */
public class user {

    private int pk;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private String province;

    public void user() {
        pk = 0;
        email = "";
        firstName = "";
        lastName = "";
        address = "";
        city = "";
        country = "";
        province = "";
    }

    public void user(int pk) {
        this.user();
        this.pk = pk;
        HashMap user = new HashMap();
        user.put("pk", pk);
        query data = new query();
        email = data.getValue("person", "email", user);
        firstName = data.getValue("person", "firstname", user);
        lastName = data.getValue("person", "lastname", user);

        HashMap location = new HashMap();
        location.put("pk", data.getValue("person", "location_pk", user));
        address = data.getValue("location", "address", location);
        city = data.getValue("location", "city", location);
        country = data.getValue("location", "country", location);
        province = data.getValue("location", "province", location);

    }

    public void setPK(int pk) {
        this.pk = pk;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddressName(String address) {
        this.address = address;
    }

    public void setCityName(String city) {
        this.city = city;
    }

    public void setProvinceName(String province) {
        this.province = province;
    }

    public void setCountryName(String country) {
        this.country = country;
    }
    
    public String getPk(){
        return Integer.toString(pk);
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getProvince() {
        return province;
    }
    
    public String getString(){
        return this.getFirstName()+this.getLastName();
    }
}
