package fr.gosecuri.model;

import com.google.firebase.database.DataSnapshot;
import fr.gosecuri.kernel.Firebase;

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
        this.firstname = dataSnapshot.getValue(User.class).getFirstname();
        this.lastname = dataSnapshot.getValue(User.class).getLastname();
        this.picture = dataSnapshot.getValue(User.class).getPicture();
    }

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
