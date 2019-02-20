package fr.gosecuri;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import fr.gosecuri.service.AzureFace;
import fr.gosecuri.service.Property;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;


public class WebcamPanel {

    /**
     * Capture the face and create the file
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        final Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());

        com.github.sarxos.webcam.WebcamPanel panel = new com.github.sarxos.webcam.WebcamPanel(webcam);
        panel.setMirrored(true);

        JButton button = new JButton("S'authentifié");

        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    ImageIO.write(webcam.getImage(), "PNG", new File(Property.getProperty("azure.filePicture")));

                    WebcamPanel.detectFace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });


        JPanel jPanel = new JPanel();
        jPanel.add(button);
        jPanel.setBounds(-100, -100, 50, 20);

        JFrame window = new JFrame("GOSECURI Identification");
        window.setLayout(new FlowLayout());
        window.getContentPane().add(panel);
        window.getContentPane().add(button);
        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }

    /**
     * Delete the face after capture
     */
    public static void detectFace() {

        try {

            AzureFace azureFace = new AzureFace("/detect", "application/octet-stream");

            // Request body
            File file = new File(Property.getProperty("azure.filePicture"));
            FileInputStream targetStream = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];

            targetStream.read(bytes);
            ByteArrayEntity bodyEntity = new ByteArrayEntity(bytes, ContentType.APPLICATION_OCTET_STREAM);

            String res = azureFace.post(bodyEntity);

            // Create the JSON
            if (res.charAt(0) == '[') {
                JSONArray json = new JSONArray(res);

                if (json.length() > 0) {
                    JSONObject object = json.getJSONObject(0);
                    WebcamPanel.findUser(object.get("faceId").toString());
                }

            } else if (res.charAt(0) == '{') {
                JSONObject json = new JSONObject(res);
                System.out.println(json);
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
    public static void findUser(String faceId) throws IOException, URISyntaxException {
        AzureFace azureFace = new AzureFace("/findsimilars", "application/json");

        // Request body
        JSONObject json = new JSONObject();
        json.put("faceId", faceId);
        json.put("faceListId", "user");
        json.put("mode", "matchPerson");

        StringEntity bodyEntity = new StringEntity(json.toString());

        String res = azureFace.post(bodyEntity);

        // Create the JSON
        if (res.charAt(0) == '[') {
            JSONArray jsonRes = new JSONArray(res);

            System.out.println(jsonRes);

        } else if (res.charAt(0) == '{') {
            JSONObject jsonRes = new JSONObject(res);
            System.out.println(jsonRes);
        }
    }
}
