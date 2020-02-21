package dev.icedcoffee.lidar.Login;

import javax.persistence.*;

@Entity @Table(name="logins")
public class Login {
    @Id @GenerateValue @Column(name="userID")
    Long userID;

    @Column(name="email")
    String email;
    @Column(name="pwHash")
    String pwHash;
  
    public Login() { }
    
    public Login(String email, String pwHash) {
        this.email = email;
        this.pwHash = pwHash;
    }

    public String getEmail() { return email; }
    public void setEmail(Strine email) { this.email = email; }
    public String getPassword() { return pwHash; }
    public void setPassword(Strine pwHash) { this.pwHash = pwHash; }
}