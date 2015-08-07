import java.util.ArrayList;

//Controller module

public class Controller {
	static ArrayList<MySearch> sqlresult = new ArrayList<MySearch>();

	// Method to call Model to retrieve results to return to view module
	public static String findLname(String searchName) {
		String stringResult = "";
		String sql = "SELECT * FROM addrbook where lower(lname) like '"
				+ searchName + "%' ORDER BY 1      ";
		sqlresult = MySearch.SqlSearch(sql, searchName);

		int size = sqlresult.size();
		System.out.println(size);

		if (sqlresult.size() > 0) {

			for (MySearch p : sqlresult) {
				System.out.println(p);
				stringResult = stringResult + p.toString() + "\n";
			}

		} else
			stringResult = "No Results Found";
		return (stringResult);
	}

}