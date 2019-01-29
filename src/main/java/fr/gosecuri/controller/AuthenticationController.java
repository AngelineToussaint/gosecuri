package fr.gosecuri.controller;

import fr.gosecuri.utils.Camera;
import fr.gosecuri.view.AuthenticationPage;

public class AuthenticationController {
    private AuthenticationPage authenticationPage;
    private Camera camera;

    public AuthenticationController(AuthenticationPage authenticationPage) {
        this.authenticationPage = authenticationPage;
        camera = new Camera();
        while (camera.isOpen()) {
            authenticationPage.setCameraFrame(camera.getFrame());
        }
    }

    public AuthenticationPage getAuthenticationPage() {
        return authenticationPage;
    }
    public void setAuthenticationPage(AuthenticationPage authenticationPage) {
        this.authenticationPage = authenticationPage;
    }
}
