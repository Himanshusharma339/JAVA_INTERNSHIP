import java.sql.*;
import java.util.Scanner;

public class EmployeeApp {

    // DB Connection Details
    static final String DB_URL = "jdbc:mysql://localhost:3306/employee_db"; // Change for PostgreSQL
    static final String USER = "root";   // PostgreSQL: usually "postgres"
    static final String PASS = "your_password";

    static Connection conn;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to database!");

            int choice;
            do {
                System.out.println("\n1. Add Employee\n2. View All Employees\n3. Update Employee\n4. Delete Employee\n5. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1: addEmployee(); break;
                    case 2: viewEmployees(); break;
                    case 3: updateEmployee(); break;
                    case 4: deleteEmployee(); break;
                    case 5: System.out.println("Exiting..."); break;
                    default: System.out.println("Invalid choice.");
                }

            } while (choice != 5);

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addEmployee() throws SQLException {
        System.out.print("Enter name: ");
        String name = sc.next();
        System.out.print("Enter department: ");
        String dept = sc.next();
        System.out.print("Enter salary: ");
        double salary = sc.nextDouble();

        String sql = "INSERT INTO employee (name, department, salary) VALUES (?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, name);
        pst.setString(2, dept);
        pst.setDouble(3, salary);
        int rows = pst.executeUpdate();
        System.out.println(rows + " employee added.");
    }

    static void viewEmployees() throws SQLException {
        String sql = "SELECT * FROM employee";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        System.out.println("\nID | Name | Department | Salary");
        while (rs.next()) {
            System.out.printf("%d | %s | %s | %.2f\n", rs.getInt("id"), rs.getString("name"), rs.getString("department"), rs.getDouble("salary"));
        }
    }

    static void updateEmployee() throws SQLException {
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        System.out.print("Enter new salary: ");
        double salary = sc.nextDouble();

        String sql = "UPDATE employee SET salary = ? WHERE id = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setDouble(1, salary);
        pst.setInt(2, id);
        int rows = pst.executeUpdate();
        System.out.println(rows + " employee updated.");
    }

    static void deleteEmployee() throws SQLException {
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM employee WHERE id = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, id);
        int rows = pst.executeUpdate();
        System.out.println(rows + " employee deleted.");
    }
}
