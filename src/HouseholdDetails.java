
import java.util.List;

public class HouseholdDetails {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {

		// The objective is to parse household data, subsequently process the parsed
		// information, and display the results on the console.

		// Parser1 is nothing but approach 1 which takes text file and parse the data
		// using (",") as delimiter by ignoring double quotes at beginning and ending of
		// line
		HouseholdInfoParser1 parser = new HouseholdInfoParser1();
		List<Occupant> occupantList = parser.parseHouseholdInformation("data.txt");

		// Parser2 is nothing but approach 2 which takes text file and parse the data
		// using regular expression
		/*
		 * HouseholdInfoParser2 parser = new HouseholdInfoParser2(); List<Occupant>
		 * occupantList = parser.parseHouseholdInformation("data.txt");
		 */

		// Parser3 is nothing but approach 3 which takes csv file and parse the data
		// using (,) as delimiter. Please note address should not contain delimiter
		/*
		 * HouseholdInfoParser3 parser = new HouseholdInfoParser3(); List<Occupant>
		 * occupantList = parser.parseHouseholdInformation("data.csv");
		 */

		HouseholdProcessor processor = new HouseholdProcessor();
		processor.displayHouseholdDetails(occupantList);

	}

}
