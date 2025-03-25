import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class LibraryManagementSystem extends JFrame implements ActionListener {
    // Component declarations
    JLabel jtitle, nameLabel, yearLabel, rollNoLabel, departmentLabel, phoneLabel, attendanceLabel, booksTakenLabel;
    JTextField nameField, yearField, rollNoField, departmentField, phoneField, attendanceField, booksTakenField,
            searchField;
    JButton addStudent, reset, deleteRecord, searchButton;
    JTable studentTable;
    DefaultTableModel tableModel;

    public LibraryManagementSystem() {
        // Set up JFrame properties
        setTitle("Library Management System");
        setLayout(null);
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title label setup
        jtitle = new JLabel("STUDENT LIBRARY MANAGEMENT SYSTEM");
        jtitle.setBounds(200, 10, 700, 50);
        jtitle.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        add(jtitle);

        // Set up form components
        setupFormComponents();

        // Table setup
        String[] columnNames = { "Name", "Year", "Roll No", "Department", "Phone", "Attendance", "Books Taken" };
        tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(studentTable);
        tableScrollPane.setBounds(50, 300, 900, 200);
        add(tableScrollPane);

        // Set up buttons
        addStudent = new JButton("Add Student");
        addStudent.setBounds(50, 250, 150, 30);
        addStudent.addActionListener(this);
        add(addStudent);

        reset = new JButton("Reset Fields");
        reset.setBounds(220, 250, 150, 30);
        reset.addActionListener(this);
        add(reset);

        deleteRecord = new JButton("Delete Record");
        deleteRecord.setBounds(390, 250, 150, 30);
        deleteRecord.addActionListener(this);
        add(deleteRecord);

        searchButton = new JButton("Search by Roll No");
        searchButton.setBounds(560, 250, 150, 30);
        searchButton.addActionListener(this);
        add(searchButton);

        setVisible(true);
    }

    private void setupFormComponents() {
        // Initialize and position all labels and text fields for the form
        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 70, 100, 30);
        add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(150, 70, 200, 30);
        add(nameField);

        yearLabel = new JLabel("Year:");
        yearLabel.setBounds(50, 110, 100, 30);
        add(yearLabel);
        yearField = new JTextField();
        yearField.setBounds(150, 110, 200, 30);
        add(yearField);

        rollNoLabel = new JLabel("Roll No:");
        rollNoLabel.setBounds(50, 150, 100, 30);
        add(rollNoLabel);
        rollNoField = new JTextField();
        rollNoField.setBounds(150, 150, 200, 30);
        add(rollNoField);

        departmentLabel = new JLabel("Department:");
        departmentLabel.setBounds(400, 70, 100, 30);
        add(departmentLabel);
        departmentField = new JTextField();
        departmentField.setBounds(500, 70, 200, 30);
        add(departmentField);

        phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(400, 110, 100, 30);
        add(phoneLabel);
        phoneField = new JTextField();
        phoneField.setBounds(500, 110, 200, 30);
        add(phoneField);

        attendanceLabel = new JLabel("Attendance:");
        attendanceLabel.setBounds(400, 150, 100, 30);
        add(attendanceLabel);
        attendanceField = new JTextField();
        attendanceField.setBounds(500, 150, 200, 30);
        add(attendanceField);

        booksTakenLabel = new JLabel("Books Taken:");
        booksTakenLabel.setBounds(50, 190, 100, 30);
        add(booksTakenLabel);
        booksTakenField = new JTextField();
        booksTakenField.setBounds(150, 190, 200, 30);
        add(booksTakenField);

        JLabel searchLabel = new JLabel("Search Roll No:");
        searchLabel.setBounds(400, 190, 100, 30);
        add(searchLabel);
        searchField = new JTextField();
        searchField.setBounds(500, 190, 200, 30);
        add(searchField);
    }

    // Action handling for buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addStudent) {
            addStudentRecord();
        } else if (e.getSource() == reset) {
            resetFields();
        } else if (e.getSource() == deleteRecord) {
            deleteStudentRecord();
        } else if (e.getSource() == searchButton) {
            searchStudentByRollNo();
        }
    }

    private void addStudentRecord() {
        String name = nameField.getText();
        String year = yearField.getText();
        String rollNo = rollNoField.getText();
        String department = departmentField.getText();
        String phone = phoneField.getText();
        String attendance = attendanceField.getText();
        String booksTaken = booksTakenField.getText();

        if (name.isEmpty() || rollNo.isEmpty() || department.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String[] data = { name, year, rollNo, department, phone, attendance, booksTaken };
            tableModel.addRow(data);
        }
    }

    private void resetFields() {
        nameField.setText("");
        yearField.setText("");
        rollNoField.setText("");
        departmentField.setText("");
        phoneField.setText("");
        attendanceField.setText("");
        booksTakenField.setText("");
        searchField.setText("");
    }

    private void deleteStudentRecord() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Select a record to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchStudentByRollNo() {
        String rollNo = searchField.getText();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 2).toString().equals(rollNo)) {
                studentTable.setRowSelectionInterval(i, i);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Record not found.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    // Main method to start the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibraryManagementSystem::new);
    }
}
