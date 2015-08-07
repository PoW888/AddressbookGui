/**
 * An address book Model - MVC pattern
 *    
 * Open a File Reader to (address book file), and buffer it
 * Loop through until end of file
 * Split the data on commas ,
 * Compare partial lastname, ignoring case 
 * Return the found entries  
 */
import java.sql.*;
import java.util.ArrayList;

/*****************************************************************************
 * / Read data from Oracle database /
 ****************************************************************************/
public class MySearch {

	private String LNAME;
	private String FNAME;
	private String STREET;
	private String CITY;
	private String STATE;
	private String ZIP;
	private String PHONE;

	// constructor
	MySearch(String LNAME, String FNAME, String STREET, String CITY,
			String STATE, String ZIP, String PHONE) {
		this.LNAME = LNAME;
		this.FNAME = FNAME;
		this.STREET = STREET;
		this.CITY = CITY;
		this.STATE = STATE;
		this.ZIP = ZIP;
		this.PHONE = PHONE;

	}

	public static ArrayList<MySearch> SqlSearch(String sql, String lnameSearch) {

		String host = "jdbc:oracle:thin:@localhost:1521:"; // from localhost
		// String host = "jdbc:oracle:thin:@oit2.scps.nyu.edu:1521:"; //from
		// remote PC
		String db = "app12c";
		String user = "wong";
		String pswd = "wong";
		Connection connect = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<MySearch> found = new ArrayList<MySearch>();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // dynamically
																// load
																// the JDBC
																// Drive class
			String host_db = host + db;
			connect = DriverManager.getConnection(host_db, user, pswd);

			stmt = connect.createStatement(); // create a statement object

			try {

				System.out.println(sql);
				rs = stmt.executeQuery(sql);
			} catch (Exception e) {
				System.out.println(e);
			}

			while (rs.next()) {

				String lname = rs.getString("LNAME");
				String fname = rs.getString("FNAME");
				String street = rs.getString("STREET");
				String city = rs.getString("CITY");
				String state = rs.getString("STATE");
				String zip = rs.getString("ZIP");
				String phone = rs.getString("PHONE");
				// Reads data from resultset object and adds to arraylist
				MySearch personfound = new MySearch(lname, fname, street, city,
						state, zip, phone);
				found.add(personfound);

			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) { // let JVM handle it
				}
			}
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) { // let JVM handle it
				}
			}
		}
		return found;

	}

	@Override
	public String toString() {
		String format = "%-15s %-15s %-35s %-35s %-15s %-15s %-15s";

		String data = String.format(format, LNAME, FNAME, STREET, CITY, STATE,
				ZIP, PHONE);

		return (data);
	}

}
