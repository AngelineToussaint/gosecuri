package fr.gosecuri.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class MainPage {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private String pageName = "Authentication";
    private int frameWidth = 800;
    private int frameHeight = 600;

    private AuthenticationPage authenticationPage;
    private StoragePage storagePage;

    private CardLayout layout = new CardLayout(2,2);

    public MainPage(AuthenticationPage authenticationPage, StoragePage storagePage) {
        this.authenticationPage = authenticationPage;
        this.storagePage = storagePage;

        Initialize();
    }

    private void Initialize() {
        // Instantiate JObjects
        mainFrame = new JFrame(pageName);
        mainPanel = new JPanel(layout);

        // Add pages to layout
        mainPanel.add(authenticationPage, "Authentication");
        mainPanel.add(storagePage, "Storage");

        // Initialize app
        layout.show(mainPanel, pageName);
        mainFrame.setSize(frameWidth, frameHeight);
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    // Getter and Setter of main page
    public CardLayout getLayout() {
        return layout;
    }
    public void setLayout(CardLayout layout) {
        this.layout = layout;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }
    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public String getPageName() {
        return pageName;
    }
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public int getFrameWidth() {
        return frameWidth;
    }
    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }
    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

    // Getter and Setter of child pages
    public AuthenticationPage getAuthenticationPage() {
        return authenticationPage;
    }
    public void setAuthenticationPage(AuthenticationPage authenticationPage) {
        this.authenticationPage = authenticationPage;
    }

    public StoragePage getStoragePage() {
        return storagePage;
    }
    public void setStoragePage(StoragePage storagePage) {
        this.storagePage = storagePage;
    }
}
