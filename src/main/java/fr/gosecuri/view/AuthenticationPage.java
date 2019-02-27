package fr.gosecuri.view;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import javax.swing.*;
import java.awt.*;

public class AuthenticationPage extends JPanel {
    private int buttonWidth = 140;
    private int buttonHeight = 70;
    private JLabel errorMessage;
    private JLabel cameraLabel;
    private JButton loginButton;
    private WebcamPanel webcamPanel;
    private String buttonName = "S'identifier";

    public AuthenticationPage() {
        // Create Layout
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Init webcam
        final Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());

        // Init panel for webcam
        this.webcamPanel = new WebcamPanel(webcam);
        this.webcamPanel.setMirrored(true);

        add(this.webcamPanel, c);

        // Init textarea
        this.errorMessage = new JLabel("");
        this.errorMessage.setFont(new Font("Roboto", Font.PLAIN, 15));
        this.errorMessage.setForeground(new Color(193, 49, 34));

        add(this.errorMessage, c);

        // Init JButton
        this.loginButton = new JButton(this.buttonName);
        this.loginButton.setPreferredSize(new Dimension(this.buttonWidth, this.buttonHeight));
        this.loginButton.setFont(new Font("Roboto", Font.BOLD, 14));
        this.loginButton.setForeground(Color.white);
        this.loginButton.setBackground(new Color(55,158,193));

        add(this.loginButton, c);

//        c.anchor = GridBagConstraints.PAGE_START;
    }

    public void setCameraFrame(ImageIcon frame) {
        this.cameraLabel.setIcon(frame);
    }

    public int getButtonWidth() {
        return this.buttonWidth;
    }
    public void setButtonWidth(int buttonWidth) {
        this.buttonWidth = buttonWidth;
    }

    public int getButtonHeight() {
        return this.buttonHeight;
    }
    public void setButtonHeight(int buttonHeight) {
        this.buttonHeight = buttonHeight;
    }

    public JLabel getCameraLabel() {
        return this.cameraLabel;
    }
    public void setCameraLabel(JLabel cameraLabel) {
        this.cameraLabel = cameraLabel;
    }

    public JButton getLoginButton() {
        return this.loginButton;
    }
    public void setLoginButton(JButton loginButton) {
        this.loginButton = loginButton;
    }

    public String getButtonName() {
        return this.buttonName;
    }
    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public WebcamPanel getWebcamPanel() {
        return this.webcamPanel;
    }

    public void setWebcamPanel(WebcamPanel webcamPanel) {
        this.webcamPanel = webcamPanel;
    }


    public JLabel getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(JLabel errorMessage) {
        this.errorMessage = errorMessage;
    }
}
