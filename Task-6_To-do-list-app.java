import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class ToDoListApp extends JFrame {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskInput;

    public ToDoListApp() {
        setTitle("To-Do List Application");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Model & components
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        taskInput = new JTextField();

        // Buttons
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");

        // Top input panel
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.add(taskInput, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        // Bottom button panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        // Main layout
        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Actions
        addButton.addActionListener(e -> {
            String task = taskInput.getText().trim();
            if (!task.isEmpty()) {
                taskListModel.addElement(task);
                taskInput.setText("");
            }
        });

        deleteButton.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index != -1) {
                taskListModel.remove(index);
            }
        });

        saveButton.addActionListener(e -> {
            try (PrintWriter writer = new PrintWriter("tasks.txt")) {
                for (int i = 0; i < taskListModel.getSize(); i++) {
                    writer.println(taskListModel.get(i));
                }
                JOptionPane.showMessageDialog(this, "Tasks saved to file.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving tasks.");
            }
        });

        loadButton.addActionListener(e -> {
            try (BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"))) {
                taskListModel.clear();
                String line;
                while ((line = reader.readLine()) != null) {
                    taskListModel.addElement(line);
                }
                JOptionPane.showMessageDialog(this, "Tasks loaded.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error loading tasks.");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToDoListApp().setVisible(true));
    }
}
