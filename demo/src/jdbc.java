
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
public class jdbc {
     /**
     * Connect to a sample database
     */
	 private Connection connect() {
	        // SQLite connection string
	        String url = "jdbc:sqlite:C:/Users/funkbeatz/testDB.db";
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(url);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return conn;
	    }
	 
	    /**
	     * Insert a new row into the testtable table
	     *
	     * @param datetime
	     * @param description
	     */
	    public void insert(String datetime, String description) {
	        String sql = "INSERT INTO testtable(datetime,description) VALUES(?,?)";
	 
	        try (Connection conn = this.connect();
	                PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, datetime);
	            pstmt.setString(2, description);
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	 
	    /**
	     * @param args the command line arguments
	     */
	    public static void main(String[] args) {
	 
	        jdbc app = new jdbc();
	        // insert three new rows
	        app.insert("May 2 11AM", "Something");
	        app.insert("May 2 12PM", "Something else");
	        app.insert("May 2 1PM", "meet fo");
	        System.out.println("rows inserted");
	    }
	 
	}