package fr.gosecuri.controller;

import fr.gosecuri.view.MainPage;
import fr.gosecuri.view.StoragePage;

public class StorageController {
    private StoragePage storagePage;
    private MainPage mainPage;

    public StorageController(StoragePage storagePage, MainPage mainPage) {
        this.storagePage = storagePage;
        this.mainPage = mainPage;

        // Add Button Listener
        storagePage.getSwitchButton().addActionListener((e) -> switchToPage());
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
}
