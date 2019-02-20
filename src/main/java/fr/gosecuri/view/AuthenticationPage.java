package fr.gosecuri.view;

import javax.swing.*;
import java.awt.*;

public class AuthenticationPage extends JPanel {
    private int buttonWidth = 140;
    private int buttonHeight = 70;
    private JLabel cameraLabel;
    private JButton switchButton;
    private String buttonName = "S'identifier";

    public AuthenticationPage() {
        // Create Layout
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Initiatlisation JLabel
        cameraLabel = new JLabel();
        add(cameraLabel, c);

        //Initialisation JButton
        switchButton = new JButton(buttonName);
        switchButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        switchButton.setFont(new Font("Roboto", Font.BOLD, 14));
        switchButton.setForeground(Color.white);
        switchButton.setBackground(new Color(55,158,193));
        switchButton.setAlignmentY(Component.TOP_ALIGNMENT);

        c.anchor = GridBagConstraints.PAGE_START;
        add(switchButton, c);
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

    public JLabel getCameraLabel() {
        return cameraLabel;
    }
    public void setCameraLabel(JLabel cameraLabel) {
        this.cameraLabel = cameraLabel;
    }

    public JButton getSwitchButton() {
        return switchButton;
    }
    public void setSwitchButton(JButton switchButton) {
        this.switchButton = switchButton;
    }

    public String getButtonName() {
        return buttonName;
    }
    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }
}
