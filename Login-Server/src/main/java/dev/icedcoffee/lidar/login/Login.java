package dev.icedcoffee.lidar.login;

import javax.persistence.*;

@Entity @Table(name="logins")
public class Login {
    @Id @GeneratedValue @Column(name="user_id")
    int userID;

    @Column(name="email",unique=true)
    String email;
    @Column(name="pw_hash")
    String pwHash;
  
    public Login() { }
    
    public Login(String email, String pwHash) {
        this.email = email;
        this.pwHash = pwHash;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return pwHash; }
    public void setPassword(String pwHash) { this.pwHash = pwHash; }
}
