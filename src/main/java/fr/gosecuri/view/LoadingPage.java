package fr.gosecuri.view;

import javax.swing.*;
import java.awt.*;

public class LoadingPage extends JPanel {
    private String loadingMessage = "Chargement...";

    public LoadingPage() {
        // Create layout
        setLayout(new GridBagLayout());

        // Create label
        JLabel text = new JLabel(loadingMessage);
        text.setFont(new Font("Roboto", Font.PLAIN, 30));
        text.setForeground(new Color(55,158,193));
        add(text);
    }

    public String getLoadingMessage() {
        return loadingMessage;
    }
    public void setLoadingMessage(String loadingMessage) {
        this.loadingMessage = loadingMessage;
    }
}
