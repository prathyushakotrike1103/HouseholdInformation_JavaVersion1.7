import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class HouseholdProcessorTest {
	List<Occupant> occupantList = new ArrayList<Occupant>();

	// Data for testing
	/*
	 * "Dave","Smith","123 main st.","seattle","wa","43"
	 * "Alice","Smith","123 Main St.","Seattle","WA","45"
	 * "Bob","Williams","234 2nd Ave.","Tacoma","WA","26"
	 * "Carol","Johnson","234 2nd Ave","Seattle","WA","67"
	 * "Eve","Smith","234 2nd Ave.","Tacoma","WA","25"
	 * "Frank","Jones","234 2nd Ave.","Tacoma","FL","23"
	 * "George","Brown","345 3rd Blvd., Apt. 200","Seattle","WA","18"
	 * "Helen","Brown","345 3rd Blvd. Apt. 200","Seattle","WA","18"
	 * "Ian","Smith","123 main st ","Seattle","Wa","18"
	 * "Jane","Smith","123 Main St.","Seattle","WA","13"
	 */
	@Before
	public void before() {
		occupantList = HouseholdInfoParser1.parseHouseholdInformation("data.txt");
	}

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void restoreStreams() {
		System.setOut(originalOut);
	}

	// Data for after sorting
	/*
	 * "George","Brown","345 3rd Blvd., Apt. 200","Seattle","WA","18"
	 * "Helen","Brown","345 3rd Blvd. Apt. 200","Seattle","WA","18"
	 * "Carol","Johnson","234 2nd Ave","Seattle","WA","67"
	 * "Frank","Jones","234 2nd Ave.","Tacoma","FL","23"
	 * "Alice","Smith","123 Main St.","Seattle","WA","45"
	 * "Bob","Williams","234 2nd Ave.","Tacoma","WA","26"
	 * "Dave","Smith","123 main st.","seattle","wa","43"
	 * "Eve","Smith","234 2nd Ave.","Tacoma","WA","25"
	 * "Ian","Smith","123 main st ","Seattle","Wa","18"
	 * "Jane","Smith","123 Main St.","Seattle","WA","13"
	 */
	@Test
	public void getSortOccupantListSuccess() {
		Collections.sort(occupantList, new HouseholdProcessor());
		assertTrue(occupantList.get(0).getLastName().equals("Brown"));
		assertTrue(occupantList.get(0).getFirstName().equals("George"));
	}

	// Data for after sorting
	/*
	 * "George","Brown","345 3rd Blvd., Apt. 200","Seattle","WA","18"
	 * "Helen","Brown","345 3rd Blvd. Apt. 200","Seattle","WA","18"
	 * "Carol","Johnson","234 2nd Ave","Seattle","WA","67"
	 * "Frank","Jones","234 2nd Ave.","Tacoma","FL","23"
	 * "Alice","Smith","123 Main St.","Seattle","WA","45"
	 * "Bob","Williams","234 2nd Ave.","Tacoma","WA","26"
	 * "Dave","Smith","123 main st.","seattle","wa","43"
	 * "Eve","Smith","234 2nd Ave.","Tacoma","WA","25"
	 * "Ian","Smith","123 main st ","Seattle","Wa","18"
	 * "Jane","Smith","123 Main St.","Seattle","WA","13"
	 */

	@Test
	public void getSortOccupantListFailure() {
		Collections.sort(occupantList, new HouseholdProcessor());
		assertFalse(occupantList.get(1).getLastName().equals("Jones"));
		assertFalse(occupantList.get(1).getFirstName().equals("Frank"));
	}

	// Data after filter
	/*
	 * "Dave","Smith","123 main st.","seattle","wa","43"
	 * "Alice","Smith","123 Main St.","Seattle","WA","45"
	 * "Bob","Williams","234 2nd Ave.","Tacoma","WA","26"
	 * "Carol","Johnson","234 2nd Ave","Seattle","WA","67"
	 * "Eve","Smith","234 2nd Ave.","Tacoma","WA","25"
	 * "Frank","Jones","234 2nd Ave.","Tacoma","FL","23"
	 */

	@Test
	public void getFilterOccupantListSuccess() {
		occupantList = HouseholdProcessor.filterHouseholdOccupantsByAge(occupantList);
		assertTrue(occupantList.size() == 6);
	}

	// Data after filter
	/*
	 * "Dave","Smith","123 main st.","seattle","wa","43"
	 * "Alice","Smith","123 Main St.","Seattle","WA","45"
	 * "Bob","Williams","234 2nd Ave.","Tacoma","WA","26"
	 * "Carol","Johnson","234 2nd Ave","Seattle","WA","67"
	 * "Eve","Smith","234 2nd Ave.","Tacoma","WA","25"
	 * "Frank","Jones","234 2nd Ave.","Tacoma","FL","23"
	 */

	@Test
	public void getFilterOccupantListFailure() {
		occupantList = HouseholdProcessor.filterHouseholdOccupantsByAge(occupantList);
		assertFalse(occupantList.size() == 10);
	}

	// Data for testing
	/*
	 * "Dave","Smith","123 main st.","seattle","wa","43"
	 * "Alice","Smith","123 Main St.","Seattle","WA","45"
	 * "Bob","Williams","234 2nd Ave.","Tacoma","WA","26"
	 * "Carol","Johnson","234 2nd Ave","Seattle","WA","67"
	 * "Eve","Smith","234 2nd Ave.","Tacoma","WA","25"
	 * "Frank","Jones","234 2nd Ave.","Tacoma","FL","23"
	 * "George","Brown","345 3rd Blvd., Apt. 200","Seattle","WA","18"
	 * "Helen","Brown","345 3rd Blvd. Apt. 200","Seattle","WA","18"
	 * "Ian","Smith","123 main st ","Seattle","Wa","18"
	 * "Jane","Smith","123 Main St.","Seattle","WA","13"
	 */

	@Test
	public void groupOccupantsByHouseholdSuccess() {

		Map<String, List<Occupant>> groupedOccupants = HouseholdProcessor.groupOccupantsByHousehold(occupantList);

		List<Occupant> household1 = groupedOccupants.get("123 MAIN ST, SEATTLE, WA");
		assertEquals("Number of occupants in household 1", 4, household1.size());
	}

	// Data for testing
	/*
	 * "Dave","Smith","123 main st.","seattle","wa","43"
	 * "Alice","Smith","123 Main St.","Seattle","WA","45"
	 * "Bob","Williams","234 2nd Ave.","Tacoma","WA","26"
	 * "Carol","Johnson","234 2nd Ave","Seattle","WA","67"
	 * "Eve","Smith","234 2nd Ave.","Tacoma","WA","25"
	 * "Frank","Jones","234 2nd Ave.","Tacoma","FL","23"
	 * "George","Brown","345 3rd Blvd., Apt. 200","Seattle","WA","18"
	 * "Helen","Brown","345 3rd Blvd. Apt. 200","Seattle","WA","18"
	 * "Ian","Smith","123 main st ","Seattle","Wa","18"
	 * "Jane","Smith","123 Main St.","Seattle","WA","13"
	 */

	@Test
	public void groupOccupantsByHouseholdFailure() {

		Map<String, List<Occupant>> groupedOccupants = HouseholdProcessor.groupOccupantsByHousehold(occupantList);

		List<Occupant> household1 = groupedOccupants.get("123 MAIN ST, SEATTLE, WA");
		assertNotEquals("Number of occupants in household 1", 2, household1.size());
	}

	// Data for testing
	/*
	 * "Dave","Smith","123 main st.","seattle","wa","43"
	 * "Alice","Smith","123 Main St.","Seattle","WA","45"
	 * "Bob","Williams","234 2nd Ave.","Tacoma","WA","26"
	 * "Carol","Johnson","234 2nd Ave","Seattle","WA","67"
	 * "Eve","Smith","234 2nd Ave.","Tacoma","WA","25"
	 * "Frank","Jones","234 2nd Ave.","Tacoma","FL","23"
	 * "George","Brown","345 3rd Blvd., Apt. 200","Seattle","WA","18"
	 * "Helen","Brown","345 3rd Blvd. Apt. 200","Seattle","WA","18"
	 * "Ian","Smith","123 main st ","Seattle","Wa","18"
	 * "Jane","Smith","123 Main St.","Seattle","WA","13"
	 */
	@Test
	public void displayHouseholdDetailsSuccess() {

		HouseholdProcessor.displayHouseholdDetails(occupantList);
		String printedOutput = outContent.toString().trim();

		String expectedOutput = "Household: 123 MAIN ST, SEATTLE, WA, Number of Occupants: 4\r\n"
				+ "Details of occupants aged above 18 : \r\n"
				+ "Occupant{firstName='Alice', lastName='Smith', address='123 Main St.', city='Seattle', state='WA', age=45}\r\n"
				+ "Occupant{firstName='Dave', lastName='Smith', address='123 main st.', city='seattle', state='wa', age=43}\r\n"
				+ "------------------------------------------------------------------\r\n"
				+ "Household: 234 2ND AVE, TACOMA, WA, Number of Occupants: 2\r\n"
				+ "Details of occupants aged above 18 : \r\n"
				+ "Occupant{firstName='Eve', lastName='Smith', address='234 2nd Ave.', city='Tacoma', state='WA', age=25}\r\n"
				+ "Occupant{firstName='Bob', lastName='Williams', address='234 2nd Ave.', city='Tacoma', state='WA', age=26}\r\n"
				+ "------------------------------------------------------------------\r\n"
				+ "Household: 345 3RD BLVD APT 200, SEATTLE, WA, Number of Occupants: 2\r\n"
				+ "No occupants aged above 18\r\n"
				+ "------------------------------------------------------------------\r\n"
				+ "Household: 234 2ND AVE, TACOMA, FL, Number of Occupants: 1\r\n"
				+ "Details of occupants aged above 18 : \r\n"
				+ "Occupant{firstName='Frank', lastName='Jones', address='234 2nd Ave.', city='Tacoma', state='FL', age=23}\r\n"
				+ "------------------------------------------------------------------\r\n"
				+ "Household: 234 2ND AVE, SEATTLE, WA, Number of Occupants: 1\r\n"
				+ "Details of occupants aged above 18 : \r\n"
				+ "Occupant{firstName='Carol', lastName='Johnson', address='234 2nd Ave', city='Seattle', state='WA', age=67}\r\n"
				+ "------------------------------------------------------------------";

		assertEquals(expectedOutput, printedOutput);

	}

	// Data for testing
	/*
	 * "Dave","Smith","123 main st.","seattle","wa","43"
	 * "Alice","Smith","123 Main St.","Seattle","WA","45"
	 * "Bob","Williams","234 2nd Ave.","Tacoma","WA","26"
	 * "Carol","Johnson","234 2nd Ave","Seattle","WA","67"
	 * "Eve","Smith","234 2nd Ave.","Tacoma","WA","25"
	 * "Frank","Jones","234 2nd Ave.","Tacoma","FL","23"
	 * "George","Brown","345 3rd Blvd., Apt. 200","Seattle","WA","18"
	 * "Helen","Brown","345 3rd Blvd. Apt. 200","Seattle","WA","18"
	 * "Ian","Smith","123 main st ","Seattle","Wa","18"
	 * "Jane","Smith","123 Main St.","Seattle","WA","13"
	 */
	@Test
	public void displayHouseholdDetailsFailure() {

		HouseholdProcessor.displayHouseholdDetails(occupantList);
		String printedOutput = outContent.toString().trim();

		String expectedOutput = "Household: 123 MAIN ST, SEATTLE, WA, Number of Occupants: 4\r\n"
				+ "Details of occupants aged above 18 : \r\n"
				+ "Occupant{firstName='Alice', lastName='Smith', address='123 Main St.', city='Seattle', state='WA', age=45}\r\n"
				+ "Occupant{firstName='Dave', lastName='Smith', address='123 main st.', city='seattle', state='wa', age=43}\r\n"
				+ "------------------------------------------------------------------\r\n"
				+ "Household: 234 2ND AVE, TACOMA, WA, Number of Occupants: 2\r\n"
				+ "Details of occupants aged above 18 : \r\n"
				+ "Occupant{firstName='Eve', lastName='Smith', address='234 2nd Ave.', city='Tacoma', state='WA', age=25}\r\n"
				+ "Occupant{firstName='Bob', lastName='Williams', address='234 2nd Ave.', city='Tacoma', state='WA', age=26}\r\n"
				+ "------------------------------------------------------------------\r\n"
				+ "Household: 234 2ND AVE, TACOMA, FL, Number of Occupants: 1\r\n"
				+ "Details of occupants aged above 18 : \r\n"
				+ "Occupant{firstName='Frank', lastName='Jones', address='234 2nd Ave.', city='Tacoma', state='FL', age=23}\r\n"
				+ "------------------------------------------------------------------\r\n"
				+ "Household: 234 2ND AVE, SEATTLE, WA, Number of Occupants: 1\r\n"
				+ "Details of occupants aged above 18 : \r\n"
				+ "Occupant{firstName='Carol', lastName='Johnson', address='234 2nd Ave', city='Seattle', state='WA', age=67}\r\n"
				+ "------------------------------------------------------------------";

		assertNotEquals(expectedOutput, printedOutput);

	}

}