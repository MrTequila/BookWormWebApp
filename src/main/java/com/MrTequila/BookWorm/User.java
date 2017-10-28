package com.MrTequila.BookWorm;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="user")
public class User {

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Book> books;

    @Id
    private String username;

    private String password;
    private String email;

    public User(){

    }


    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "User{" +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
