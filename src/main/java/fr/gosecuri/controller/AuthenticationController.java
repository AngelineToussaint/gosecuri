package fr.gosecuri.controller;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import fr.gosecuri.service.AzureFace;
import fr.gosecuri.service.Property;
import com.github.sarxos.webcam.WebcamPanel;
import fr.gosecuri.view.AuthenticationPage;
import fr.gosecuri.view.MainPage;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;

public class AuthenticationController {
    private AuthenticationPage authenticationPage;
    private MainPage mainPage;

    public AuthenticationController(AuthenticationPage authenticationPage, MainPage mainPage) {
        // Bind References
        this.authenticationPage = authenticationPage;
        this.mainPage = mainPage;

        // Switch to Authentication page when camera is loaded
        switchToPage(MainPage.AUTHENTICATION_PAGE);

        authenticationPage.getSwitchButton().addActionListener((e) -> {
            try {
                // Write picture
                ImageIO.write(authenticationPage.getWebcamPanel().getImage(), "PNG", new File(Property.getProperty("azure.filePicture")));

                this.detectFace();
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

    public AuthenticationPage getAuthenticationPage() {
        return authenticationPage;
    }

    /**
     * @param authenticationPage
     */
    public void setAuthenticationPage(AuthenticationPage authenticationPage) {
        this.authenticationPage = authenticationPage;
    }

    /**
     * Delete the face after capture
     */
    public void detectFace() {

        try {
            // Create new http request to azure face
            AzureFace azureFace = new AzureFace("/detect", "application/octet-stream");

            // Request body
            File file = new File(Property.getProperty("azure.filePicture"));
            FileInputStream targetStream = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];

            targetStream.read(bytes);
            ByteArrayEntity bodyEntity = new ByteArrayEntity(bytes, ContentType.APPLICATION_OCTET_STREAM);

            // Execute request and get result
            String res = azureFace.post(bodyEntity);

            // Create the JSON
            if (res.charAt(0) == '[') {
                JSONArray jsonRes = new JSONArray(res);

                if (jsonRes.length() > 0) {
                    JSONObject object = jsonRes.getJSONObject(0);
                    this.findUser(object.get("faceId").toString());
                }

            } else if (res.charAt(0) == '{') {
                JSONObject jsonRes = new JSONObject(res);
                System.out.println(jsonRes);
            }

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Find a user who correspond to the face detected
     * @param faceId
     * @throws IOException
     * @throws URISyntaxException
     */
    public void findUser(String faceId) throws IOException, URISyntaxException {
        // Create new http request to azure face
        AzureFace azureFace = new AzureFace("/findsimilars", "application/json");

        // Request body
        JSONObject jsonReq = new JSONObject();
        jsonReq.put("faceId", faceId);
        jsonReq.put("faceListId", "user");
        jsonReq.put("mode", "matchPerson");

        StringEntity bodyEntity = new StringEntity(jsonReq.toString());

        // Execute request and get result
        String res = azureFace.post(bodyEntity);

        // Create the JSON
        if (res.charAt(0) == '[') {
            JSONArray jsonRes = new JSONArray(res);

            if (jsonRes.length() > 0) {
                this.switchToPage(MainPage.STORAGE_PAGE);
            }

        } else if (res.charAt(0) == '{') {
            JSONObject jsonRes = new JSONObject(res);
            System.out.println(jsonRes);
        }
    }
}
