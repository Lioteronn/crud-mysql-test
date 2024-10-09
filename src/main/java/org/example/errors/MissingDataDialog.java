package org.example.errors;

import javax.swing.*;

public class MissingDataDialog extends JOptionPane {

    public MissingDataDialog() {
        showMessageDialog(null, "Missing data", "Error", JOptionPane.ERROR_MESSAGE);
    }

}
