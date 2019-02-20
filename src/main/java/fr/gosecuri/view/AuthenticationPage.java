package fr.gosecuri.view;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import javafx.scene.control.Button;

import javax.swing.*;
import java.awt.*;

public class AuthenticationPage extends JPanel {
    private int buttonWidth = 140;
    private int buttonHeight = 70;
    private JLabel cameraLabel;
    private JButton switchButton;
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

        // Init JButton
        this.switchButton = new JButton(this.buttonName);
        this.switchButton.setPreferredSize(new Dimension(this.buttonWidth, this.buttonHeight));
        this.switchButton.setFont(new Font("Roboto", Font.BOLD, 14));
        this.switchButton.setForeground(Color.white);
        this.switchButton.setBackground(new Color(55,158,193));

        c.anchor = GridBagConstraints.PAGE_START;
        add(this.switchButton, c);
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

    public JButton getSwitchButton() {
        return this.switchButton;
    }
    public void setSwitchButton(JButton switchButton) {
        this.switchButton = switchButton;
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
}
