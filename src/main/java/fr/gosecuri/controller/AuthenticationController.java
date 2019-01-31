package fr.gosecuri.controller;

import fr.gosecuri.utils.Camera;
import fr.gosecuri.view.AuthenticationPage;
import fr.gosecuri.view.MainPage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Observer;
import java.util.Observable;

public class AuthenticationController implements Observer {
    public static String PHOTO_DIR = "./src/main/resources/photo.jpg";

    private AuthenticationPage authenticationPage;
    private MainPage mainPage;
    private Camera camera;

    public AuthenticationController(AuthenticationPage authenticationPage, MainPage mainPage) {
        // Bind References
        this.authenticationPage = authenticationPage;
        this.mainPage = mainPage;

        // Initialize Camera
        camera = new Camera();
        camera.addObserver(this);

        // Switch to Authentication page when camera is loaded
        switchToPage(MainPage.AUTHENTICATION_PAGE);

        // Add Button Listener
        authenticationPage.getSwitchButton().addActionListener((e) -> {
            // Take Photo
            File outputFile = new File(PHOTO_DIR);
            try {
                ImageIO.write(camera.getPhoto(), "jpg", outputFile);

                if(true) {
                    switchToPage(MainPage.STORAGE_PAGE);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    private void switchToPage(String pageTitle) {
        // Switch to Storage page
        mainPage.setPageName(pageTitle);
        mainPage.getMainFrame().setTitle(pageTitle);
        mainPage.getLayout().show(mainPage.getMainPanel(), pageTitle);
    }

    public void update(Observable observable, Object arg) {
        // Refresh Authentication Page JLabel
        authenticationPage.setCameraFrame((ImageIcon) arg);
    }

    public AuthenticationPage getAuthenticationPage() {
        return authenticationPage;
    }
    public void setAuthenticationPage(AuthenticationPage authenticationPage) {
        this.authenticationPage = authenticationPage;
    }

    public Camera getCamera() {
        return camera;
    }
    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}
