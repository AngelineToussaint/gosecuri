package fr.gosecuri.controller;

import fr.gosecuri.model.User;
import fr.gosecuri.view.MainPage;
import fr.gosecuri.view.StoragePage;

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
            // TODO : edit in db
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
