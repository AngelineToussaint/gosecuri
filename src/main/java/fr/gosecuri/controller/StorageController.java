package fr.gosecuri.controller;

import com.google.firebase.database.DataSnapshot;
import fr.gosecuri.model.Kit;
import fr.gosecuri.model.User;
import fr.gosecuri.view.MainPage;
import fr.gosecuri.view.StoragePage;

import java.util.HashMap;
import java.util.Map;

public class StorageController {
    private StoragePage storagePage;
    private MainPage mainPage;
    private User user;

    public StorageController(StoragePage storagePage, MainPage mainPage) {
        this.storagePage = storagePage;
        this.mainPage = mainPage;

        // Add Button Listener
        storagePage.getDisconnectButton().addActionListener(e -> switchToPage());

        storagePage.getBrassardDeSecuriteCheckBox().addActionListener(e -> {
            changeQuantity("brassard de sécurité", storagePage.getBrassardDeSecuriteCheckBox().isSelected());
        });

        storagePage.getCeintureDeSecuriteTactiqueCheckBox().addActionListener(e -> {
            // TODO : edit in db
        });

        storagePage.getDetecteurDeMetauxCheckBox().addActionListener(e -> {
            // TODO : edit in db
        });

        storagePage.getGantDInterventionCheckBox().addActionListener(e -> {
            // TODO : edit in db
        });

        storagePage.getGilletParBalleCheckBox().addActionListener(e -> {
            // TODO : edit in db
        });

        storagePage.getLampeTorcheCheckBox().addActionListener(e -> {
            // TODO : edit in db
        });

        storagePage.getMousquetonCheckBox().addActionListener(e -> {
            // TODO : edit in db
        });
    }

    private void changeQuantity(String name, boolean isChecked) {
        Kit kit = new Kit();
        DataSnapshot dataSnapshot1 = kit.get("");

        HashMap<String, HashMap> values = (HashMap<String, HashMap>) dataSnapshot1.getValue();

        for (Map.Entry<String, HashMap> entry : values.entrySet()) {
            if (entry.getValue().get("name").equals(name)) {
                if(isChecked) {
                    kit.store(
                            entry.getKey(), "quantity", (Integer.parseInt(entry.getValue().get("quantity").toString()) + 1)
                    );
                } else {
                    kit.store(
                            entry.getKey(), "quantity", (Integer.parseInt(entry.getValue().get("quantity").toString()) - 1)
                    );
                }
            }
        }
    }

    private void switchToPage() {
        // Switch to Storage page
        mainPage.setPageName(MainPage.AUTHENTICATION_PAGE);
        mainPage.getMainFrame().setTitle(MainPage.AUTHENTICATION_PAGE);
        mainPage.getLayout().show(mainPage.getMainPanel(), MainPage.AUTHENTICATION_PAGE);
    }

    public StoragePage getStoragePage() {
        return storagePage;
    }

    public void setStoragePage(StoragePage storagePage) {
        this.storagePage = storagePage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;

        if (null != user) {
            storagePage.getUserLabel().setText(
                    user.getFirstname() + " " + user.getLastname()
            );
        }
    }
}
