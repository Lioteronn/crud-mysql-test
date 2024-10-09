/*
 * Created by JFormDesigner on Mon Oct 07 13:25:20 CEST 2024
 */

package org.example.view;

import javax.swing.*;
import net.miginfocom.swing.*;
import org.example.errors.MissingDataDialog;
import org.example.model.UsuariosDAO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author alex3
 */
public class Application extends JFrame {
    public static DefaultListModel<String> model = new DefaultListModel<>();

    public Application() {
        initComponents();
        addActionListeners();
    }

    private void initComponents() {

        titleLabel = new JLabel();
        createTitle = new JLabel();
        readTitle = new JLabel();
        updateTitle = new JLabel();
        deleteTitle = new JLabel();
        createLabel1 = new JTextField();
        readLabel1 = new JTextField();
        updateLabel1 = new JTextField();
        deleteLabel1 = new JTextField();
        createLabel2 = new JTextField();
        readAll = new JCheckBox();
        updateLabel2 = new JTextField();
        createLabel3 = new JTextField();
        updateLabel3 = new JTextField();
        createLabel4 = new JTextField();
        updateLabel4 = new JTextField();
        createButton = new JButton();
        readButton = new JButton();
        updateButton = new JButton();
        deleteButton = new JButton();
        scrollPane1 = new JScrollPane();
        resultList = new JList(model);

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[sizegroup 1,fill]" +
            "[sizegroup 1,fill]" +
            "[sizegroup 1,fill]" +
            "[sizegroup 1,fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- titleLabel ----
        titleLabel.setText("Operaciones CRUD para la tabla Usuarios");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(titleLabel, "cell 0 0 4 1");

        //---- createTitle ----
        createTitle.setText("Create");
        contentPane.add(createTitle, "cell 0 1,alignx center,growx 0");

        //---- readTitle ----
        readTitle.setText("Read");
        contentPane.add(readTitle, "cell 1 1,alignx center,growx 0");

        //---- updateTitle ----
        updateTitle.setText("Update");
        contentPane.add(updateTitle, "cell 2 1,alignx center,growx 0");

        //---- deleteTitle ----
        deleteTitle.setText("Delete");
        contentPane.add(deleteTitle, "cell 3 1,alignx center,growx 0");
        contentPane.add(createLabel1, "cell 0 2");
        contentPane.add(readLabel1, "cell 1 2");
        contentPane.add(updateLabel1, "cell 2 2");
        contentPane.add(deleteLabel1, "cell 3 2");
        contentPane.add(createLabel2, "cell 0 3");

        //---- readAll ----
        readAll.setText("Leer todos");
        contentPane.add(readAll, "cell 1 3");
        contentPane.add(updateLabel2, "cell 2 3");
        contentPane.add(createLabel3, "cell 0 4");
        contentPane.add(updateLabel3, "cell 2 4");
        contentPane.add(createLabel4, "cell 0 5");
        contentPane.add(updateLabel4, "cell 2 5");

        //---- createButton ----
        createButton.setText("Create");
        contentPane.add(createButton, "cell 0 6");

        //---- readButton ----
        readButton.setText("Read");
        contentPane.add(readButton, "cell 1 6");

        //---- updateButton ----
        updateButton.setText("Update");
        contentPane.add(updateButton, "cell 2 6");

        //---- deleteButton ----
        deleteButton.setText("Delete");
        contentPane.add(deleteButton, "cell 3 6");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(resultList);
        }
        contentPane.add(scrollPane1, "cell 0 7 4 1");
        pack();
        setLocationRelativeTo(getOwner());

    }

    private void addActionListeners() {
        createButton.addActionListener(e -> createUser());
        readButton.addActionListener(e -> readUser());
        updateButton.addActionListener(e -> updateUser());
        deleteButton.addActionListener(e -> deleteUser());
    }

    private void createUser() {
        String DNI = createLabel1.getText();
        String name = createLabel2.getText();
        String surname = createLabel3.getText();
        String date = createLabel4.getText();

        if (DNI.isBlank() || name.isBlank() || surname.isBlank() || date.isBlank()) {
            MissingDataDialog error = new MissingDataDialog();
            return;
        }

        UsuariosDAO.createUser(Integer.parseInt(DNI), name, surname, parseDate(date));
    }

    private void readUser() {
        String DNI = readLabel1.getText();

        if (DNI.isBlank() && !readAll.isSelected()) {
            MissingDataDialog error = new MissingDataDialog();
            return;
        }

        if (readAll.isSelected()) {
            UsuariosDAO.readUser();
        } else {
            UsuariosDAO.readUser(Integer.parseInt(DNI));
        }
    }

    private void updateUser() {
        String DNI = updateLabel1.getText();
        String name = updateLabel2.getText();
        String surname = updateLabel3.getText();
        String date = updateLabel4.getText();

        if (DNI.isBlank() || name.isBlank() || surname.isBlank() || date.isBlank()) {
            MissingDataDialog error = new MissingDataDialog();
            return;
        }

        UsuariosDAO.updateUser(Integer.parseInt(DNI), name, surname, parseDate(date));
    }

    private void deleteUser() {
        String DNI = deleteLabel1.getText();

        if (DNI.isBlank()) {
            MissingDataDialog error = new MissingDataDialog();
            return;
        }

        UsuariosDAO.deleteUser(Integer.parseInt(DNI));
    }

    private String parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate parsedDate = LocalDate.parse(date, formatter);

        return String.valueOf(parsedDate);
    }

    private JLabel titleLabel;
    private JLabel createTitle;
    private JLabel readTitle;
    private JLabel updateTitle;
    private JLabel deleteTitle;
    private JTextField createLabel1;
    private JTextField readLabel1;
    private JTextField updateLabel1;
    private JTextField deleteLabel1;
    private JTextField createLabel2;
    private JCheckBox readAll;
    private JTextField updateLabel2;
    private JTextField createLabel3;
    private JTextField updateLabel3;
    private JTextField createLabel4;
    private JTextField updateLabel4;
    private JButton createButton;
    private JButton readButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JScrollPane scrollPane1;
    private JList resultList;

}
