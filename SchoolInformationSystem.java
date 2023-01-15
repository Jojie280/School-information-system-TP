package schoolinformationsystem;

import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

public class SchoolInformationSystem extends JFrame {
    private JTextField nameField;
    private JTextField idField;
    private JTextField gradeField;
    private JTextField ageField;
    private JTextField emailField;
    private JTextField genderField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton viewButton;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    
    private File studentFile;

    public SchoolInformationSystem() {
       
        nameField = new JTextField();
        idField = new JTextField();
        gradeField = new JTextField();
        ageField = new JTextField();
        emailField = new JTextField();
        genderField = new JTextField();
        addButton = new JButton("Add Students");
        updateButton = new JButton("Update Info");
        deleteButton = new JButton("Remove Students");
        viewButton = new JButton("Show Students");
        tableModel = new DefaultTableModel();
        studentTable = new JTable(tableModel);

        
        tableModel.addColumn("Student Name");
        tableModel.addColumn("Student Num");
        tableModel.addColumn("Grade");
        tableModel.addColumn("Age");
        tableModel.addColumn("Email");
        tableModel.addColumn("Gender");
        
        addButton.setBackground(Color.PINK);
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setPreferredSize(new Dimension(150, 40));
        updateButton.setBackground(Color.ORANGE);
        updateButton.setForeground(Color.WHITE);
        updateButton.setFont(new Font("Arial", Font.BOLD, 14));
        updateButton.setPreferredSize(new Dimension(125, 40));
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteButton.setPreferredSize(new Dimension(160, 40));
        viewButton.setBackground(Color.BLUE);
        viewButton.setForeground(Color.WHITE);
        viewButton.setFont(new Font("Arial", Font.BOLD, 14));
        viewButton.setPreferredSize(new Dimension(160, 40));
        
        addButton.addActionListener(new AddButtonListener());
        updateButton.addActionListener(new UpdateButtonListener());
        deleteButton.addActionListener(new DeleteButtonListener());
        viewButton.addActionListener(new ViewButtonListener());
        
        
        nameField.setPreferredSize(new Dimension(200, 25));
        nameField.setBackground(Color.WHITE);
        nameField.setForeground(Color.BLACK);
        nameField.setFont(new Font("Arial", Font.BOLD, 14));
        idField.setPreferredSize(new Dimension(200, 25));
        idField.setBackground(Color.WHITE);
        idField.setForeground(Color.BLACK);
        idField.setFont(new Font("Arial", Font.BOLD, 14));
        gradeField.setPreferredSize(new Dimension(200, 25));
        gradeField.setBackground(Color.WHITE);
        gradeField.setForeground(Color.BLACK);
        gradeField.setFont(new Font("Arial", Font.BOLD, 14));
        ageField.setPreferredSize(new Dimension(200, 25));
        ageField.setBackground(Color.WHITE);
        ageField.setForeground(Color.BLACK);
        ageField.setFont(new Font("Arial", Font.BOLD, 14));
        emailField.setPreferredSize(new Dimension(200, 25));
        emailField.setBackground(Color.WHITE);
        emailField.setForeground(Color.BLACK);
        emailField.setFont(new Font("Arial", Font.BOLD, 14));
        genderField.setPreferredSize(new Dimension(200, 25));
        genderField.setBackground(Color.WHITE);
        genderField.setForeground(Color.BLACK);
        genderField.setFont(new Font("Arial", Font.BOLD, 14));
     
     
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2));
        inputPanel.add(new JLabel("Student Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Student Num:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Grade:"));
        inputPanel.add(gradeField);
        inputPanel.add(new JLabel("Age:"));
        inputPanel.add(ageField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);
        inputPanel.add(new JLabel("Gender:"));
        inputPanel.add(genderField);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);

        JScrollPane scrollPane = new JScrollPane(studentTable);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
        
        
         studentTable.setBackground(Color.PINK);
         studentTable.setForeground(Color.BLACK);
         studentTable.setFont(new Font("Arial", Font.BOLD, 12));
         studentTable.setRowHeight(25);

        getContentPane().setBackground(new Color(173, 216, 230));

        
        studentFile = new File("students.txt");

        
        loadStudents();

        setTitle("School Information System");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        
    }

    private void loadStudents() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(studentFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                tableModel.addRow(parts);
            }
            reader.close();
        } catch (IOException e) {
         
            e.printStackTrace();
        }
    }

    private void addStudent() {
        String name = nameField.getText();
        String id = idField.getText();
        String grade = gradeField.getText();
        String age = ageField.getText();
        String email = emailField.getText();
        String gender = genderField.getText();
        tableModel.addRow(new String[] {name, id, grade,age,email,gender});
        writeStudents();
    }

    private void updateStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow != -1) {
            String name = nameField.getText();
            String id = idField.getText();
            String grade = gradeField.getText();
            String age = ageField.getText();
            String email =emailField.getText();
            String gender = genderField.getText();
            tableModel.setValueAt(name, selectedRow, 0);
            tableModel.setValueAt(id, selectedRow, 1);
            tableModel.setValueAt(grade, selectedRow, 2);
            tableModel.setValueAt(age, selectedRow, 3);
            tableModel.setValueAt(email, selectedRow, 4);
            tableModel.setValueAt(gender, selectedRow, 5);
            writeStudents();
        } else {
            JOptionPane.showMessageDialog(this, "Sorry no student selected to update the info.");
        }
    }

    private void deleteStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
            writeStudents();
        } else {
            JOptionPane.showMessageDialog(this, "Sorry no student selected to Remove.");
        }
    }

    private void writeStudents() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(studentFile));
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String name = (String)tableModel.getValueAt(i, 0);
                String id = (String)tableModel.getValueAt(i, 1);
                String grade = (String)tableModel.getValueAt(i, 2);
                String age = (String)tableModel.getValueAt(i,3);
                String email = (String)tableModel.getValueAt(i,4);
                String gender = (String)tableModel.getValueAt(i,5);
                writer.write(name + "," + id + "," + grade +"," + age + "," + email + "," + gender);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            addStudent();
        }
    }

    private class UpdateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            updateStudent();
        }
    }

    private class DeleteButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            deleteStudent();
        }
    }
    private class ViewButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            loadStudents();
        }
    }
 public static void main(String[] args) {
        SchoolInformationSystem system = new SchoolInformationSystem();
    }
}