package fr.gosecuri.view;

import fr.gosecuri.model.User;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class MainPage {
    public static String LOADING_PAGE = "Chargement";
    public static String AUTHENTICATION_PAGE = "Authentification";
    public static String STORAGE_PAGE = "Gestion des stocks";

    private JFrame mainFrame;
    private JPanel mainPanel;
    private String pageName;
    private int frameWidth = 900;
    private int frameHeight = 600;

    private LoadingPage loadingPage;
    private AuthenticationPage authenticationPage;
    private StoragePage storagePage;

    private CardLayout layout = new CardLayout(2,2);

    public MainPage(LoadingPage loadingPage, AuthenticationPage authenticationPage, StoragePage storagePage) {
        this.loadingPage = loadingPage;
        this.authenticationPage = authenticationPage;
        this.storagePage = storagePage;

        // Instantiate JObjects
        mainFrame = new JFrame(pageName);
        mainPanel = new JPanel(layout);

        // Add pages to layout
        mainPanel.add(loadingPage, LOADING_PAGE);
        mainPanel.add(authenticationPage, AUTHENTICATION_PAGE);
        mainPanel.add(storagePage.getPanel(), STORAGE_PAGE);

        // Initialize app
        setPageName(LOADING_PAGE);
        layout.show(mainPanel, pageName);
        mainFrame.setSize(frameWidth, frameHeight);
        mainFrame.add(mainPanel);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        // Exit on close windows
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    public LoadingPage getLoadingPage() {
        return loadingPage;
    }
    public void setLoadingPage(LoadingPage loadingPage) {
        this.loadingPage = loadingPage;
    }

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
