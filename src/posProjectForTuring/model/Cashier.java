/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posProjectForTuring.model;

/**
 *
 * @author hp
 */
public class Cashier {
    int id;
    String name, username, password, phoneNo, address;
    
    public Cashier(){
        
    }

    public Cashier(int id, String name, String username, String password, String phoneNo, String address) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.phoneNo = phoneNo;
        this.address = address;
    }    
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
