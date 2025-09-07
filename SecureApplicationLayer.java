import java.sql.*;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SecureApplicationLayer {

  
    private static final String SECRET_KEY = "7576587698679876";

    // These are variabes that needs to be stored in Environmental Variables  
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydata";
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASS = System.getenv("DB_PASS");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        String encryptedPass = encrypt(password);

        if (authenticate(username, encryptedPassword)) {
            System.out.println("Login successful. Session started securely.");
        } else {
            System.out.println("Login failed. Invalid credentials.");
        }

        scanner.close();
    }

    // Sanitize input to prevent injection attacks
    private static String sanitizeInput(String input) {
        return input.replaceAll("[^a-zA-Z0-9]", "");
    }

    // AES encryption for password protection
    private static String encrypt(String data) {
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Encryption error", e);
        }
    }

    
    private static boolean authenticate(String username, String encryptedPassword) {
      // This is my SQL query
        String query = "SELECT * FROM roles WHERE user_name = ? AND pass = ?";
      
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            pstmt.setString(2, encryptedPass);

            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Login will be succeeds if entered credentials exists 

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            return false;
        }
    }
}
