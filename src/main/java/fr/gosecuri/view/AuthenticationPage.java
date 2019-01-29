package fr.gosecuri.view;

import javax.swing.*;

public class AuthenticationPage extends JPanel {
    private int buttonWidth = 100;
    private int buttonHeight = 40;
    private String buttonName = "S'identifier";
    private JButton switchButton;
    private JLabel cameraLabel;

    public AuthenticationPage() {
        // Initiatlisation JLabel
        cameraLabel = new JLabel();
        add(cameraLabel);

        //Initialisation JButton
        switchButton = new JButton(buttonName);
        switchButton.setBounds(50, 50, buttonWidth, buttonHeight);
        add(switchButton);
    }

    public void setCameraFrame(ImageIcon frame) {
        this.cameraLabel.setIcon(frame);
    }

    public int getButtonWidth() {
        return buttonWidth;
    }
    public void setButtonWidth(int buttonWidth) {
        this.buttonWidth = buttonWidth;
    }

    public int getButtonHeight() {
        return buttonHeight;
    }
    public void setButtonHeight(int buttonHeight) {
        this.buttonHeight = buttonHeight;
    }

    public String getButtonName() {
        return buttonName;
    }
    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public JButton getSwitchButton() {
        return switchButton;
    }
    public void setSwitchButton(JButton switchButton) {
        this.switchButton = switchButton;
    }

    public JLabel getCameraLabel() {
        return cameraLabel;
    }
    public void setCameraLabel(JLabel cameraLabel) {
        this.cameraLabel = cameraLabel;
    }
}
