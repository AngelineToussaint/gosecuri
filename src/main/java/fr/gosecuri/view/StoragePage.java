package fr.gosecuri.view;

import javax.swing.*;

public class StoragePage extends JPanel {
    private int buttonWidth = 100;
    private int buttonHeight = 40;
    private String buttonName = "Se déconnecter";
    private JButton switchButton;

    public StoragePage() {
        switchButton = new JButton(buttonName);
        switchButton.setBounds(50, 50, buttonWidth, buttonHeight);

        add(switchButton);
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
}
