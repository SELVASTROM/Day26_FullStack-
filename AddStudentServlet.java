import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/add-student")
public class AddStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        long phone = Long.parseLong(req.getParameter("phone"));

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "WJ28@krhps")) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO students(name, email, phone) VALUES (?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setLong(3, phone);
            ps.executeUpdate();
            res.getWriter().println("✅ Student Added!");
        } catch (Exception e) {
            res.getWriter().println("❌ Error: " + e.getMessage());
        }
    }
}
