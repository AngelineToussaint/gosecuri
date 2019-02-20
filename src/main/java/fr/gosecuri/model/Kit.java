package fr.gosecuri.model;

import com.google.firebase.database.DataSnapshot;
import fr.gosecuri.service.Firebase;

public class Kit extends Firebase {
    private String id;

    private String name;

    private Integer quantity;

    // To create
    public Kit(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    // To update
    public Kit(String id) {
        this.id = id;

        DataSnapshot dataSnapshot = this.get(id);
        this.quantity = dataSnapshot.getValue(Kit.class).getQuantity();
        this.name = dataSnapshot.getValue(Kit.class).getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void operationItem(Integer number, String operation) {
        try {
            if (number > 0) {
                if (operation == "add") {
                    quantity += number;
                } else {

                    if (quantity >= number) {
                        quantity -= number;
                    } else {
                        throw new Exception("Quantité négative !");
                    }

                }

                store(id, "quantity", quantity);
            } else {
                throw new Exception("Quantité doit être supérieur à 0 !");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addItem(Integer number) {
        operationItem(number, "add");
    }

    public void takeItem(Integer number) {
        operationItem(number, "take");
    }
}
