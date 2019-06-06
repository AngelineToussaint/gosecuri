package fr.gosecuri.view;

import javax.swing.*;

public class StoragePage {
    private JPanel panel;
    private JCheckBox mousquetonCheckBox;
    private JCheckBox gantDInterventionCheckBox;
    private JCheckBox ceintureDeSecuriteTactiqueCheckBox;
    private JCheckBox detecteurDeMetauxCheckBox;
    private JCheckBox brassardDeSecuriteCheckBox;
    private JCheckBox lampeTorcheCheckBox;
    private JCheckBox gilletParBalleCheckBox;
    private JButton disconnectButton;
    private JLabel userLabel;

    public JPanel getPanel() {
        return panel;
    }

    public JCheckBox getMousquetonCheckBox() {
        return mousquetonCheckBox;
    }

    public JCheckBox getGantDInterventionCheckBox() {
        return gantDInterventionCheckBox;
    }

    public JCheckBox getCeintureDeSecuriteTactiqueCheckBox() {
        return ceintureDeSecuriteTactiqueCheckBox;
    }

    public JCheckBox getDetecteurDeMetauxCheckBox() {
        return detecteurDeMetauxCheckBox;
    }

    public JCheckBox getBrassardDeSecuriteCheckBox() {
        return brassardDeSecuriteCheckBox;
    }

    public JCheckBox getLampeTorcheCheckBox() {
        return lampeTorcheCheckBox;
    }

    public JCheckBox getGilletParBalleCheckBox() {
        return gilletParBalleCheckBox;
    }

    public JButton getDisconnectButton() {
        return disconnectButton;
    }

    public JLabel getUserLabel() {
        return userLabel;
    }
}
