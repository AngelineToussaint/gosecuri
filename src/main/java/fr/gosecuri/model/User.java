package fr.gosecuri.model;

import com.google.firebase.database.DataSnapshot;
import fr.gosecuri.service.Firebase;

public class User extends Firebase {
    private String id;

    private String firstname;

    private String lastname;

    private String picture;

    // To create
    public User(String firstname, String lastname, String picture) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.picture = picture;
    }

    // To update
    public User(String id) {
        this.id = id;

        DataSnapshot dataSnapshot = this.get(id);
        User user = dataSnapshot.getValue(User.class);

        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.picture = user.getPicture();
    }

    public User() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
