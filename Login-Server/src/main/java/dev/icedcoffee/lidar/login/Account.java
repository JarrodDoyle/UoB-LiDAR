package dev.icedcoffee.lidar.login;

import javax.persistence.*;

@Entity @Table(name="accounts")
public class Account {
    @Id @GeneratedValue @Column(name="user_id")
    int userID;

    @Column(name="email",unique=true)
    String email;
    @Column(name="pw_hash")
    String pwHash;
  
    public Account() { }
    
    public Account(String email, String pwHash) {
        this.email = email;
        this.pwHash = pwHash;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return pwHash; }
    public void setPassword(String pwHash) { this.pwHash = pwHash; }
}
