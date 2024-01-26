import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HouseholdProcessor implements Comparator<Occupant> {

	// Method to sort the occupants with lastName followed by firstName using
	// comparator
	// Input : List of Occupants in household
	// Output : List of Occupants in household (sorted)
	@Override

	public int compare(Occupant o1, Occupant o2) {
		// Compare last names first
		int lastNameCompare = o1.getLastName().compareTo(o2.getLastName());
		if (lastNameCompare != 0) {
			return lastNameCompare;
		}
		// If last names are equal, compare first names
		return o1.getFirstName().compareTo(o2.getFirstName());
	}

	// Method to group occupants based on formatted address, city, and state
	// (address is formatted by removing special characters and spaces around, and
	// converting address, state, and city to uppercase).
	// Input : List of Occupants
	// Output : Map of household details(key contains Household info i.e., address,
	// city, state and values contains list of occupants in household)

	public static Map<String, List<Occupant>> groupOccupantsByHousehold(List<Occupant> OccupantList) {
		Map<String, List<Occupant>> groupedOccupant = new HashMap<>();

		for (Occupant occupant : OccupantList) {
			String address = occupant.getAddress().replaceAll("[^a-zA-Z0-9\\s+]", ""); // Regular expression to remove
																						// all special characters

			String key = address.trim().toUpperCase() + ", " + occupant.getCity().trim().toUpperCase() + ", "
					+ occupant.getState().trim().toUpperCase();

			if (groupedOccupant.containsKey(key)) {

				List<Occupant> containsList = groupedOccupant.get(key);
				containsList.add(occupant);
				groupedOccupant.put(key, containsList);

			} else {
				List<Occupant> occupantList = new ArrayList<>();
				occupantList.add(occupant);
				groupedOccupant.put(key, occupantList);
			}

		}

		return groupedOccupant;
	}

	// Method to filter occupants based on age (above 18)
	// Input : List of Occupants in household
	// Output : List of Occupants in household (filtered based on age)
	public static List<Occupant> filterHouseholdOccupantsByAge(List<Occupant> occupantList) {

		List<Occupant> filteredOccupants = new ArrayList<Occupant>();
		for (Occupant occupant : occupantList) {
			if (occupant.getAge() > 18) {
				filteredOccupants.add(occupant);
			}
		}

		return filteredOccupants;
	}

	// Method to display output to console (Initially, group the occupants based on
	// households, then filter the occupants based on age, and finally, sort the
	// occupants based on last name, followed by first name)
	// Input : List of Occupants
	// Output : Dispalay output to console
	public static void displayHouseholdDetails(List<Occupant> occupantList) {
		Map<String, List<Occupant>> groupedOccupants = groupOccupantsByHousehold(occupantList);

		for (Map.Entry<String, List<Occupant>> entry : groupedOccupants.entrySet()) {

			System.out.println("Household: " + entry.getKey() + ", Number of Occupants: " + entry.getValue().size());
			List<Occupant> filteredOccupantList = filterHouseholdOccupantsByAge(entry.getValue());
			if (filteredOccupantList.size() > 0) {
				Collections.sort(filteredOccupantList, new HouseholdProcessor());
				System.out.println("Details of occupants aged above 18 : ");
				for (Occupant occupant : filteredOccupantList) {
					System.out.println(occupant.toString());
				}

			} else {
				System.out.println("No occupants aged above 18");

			}

			System.out.println("------------------------------------------------------------------");

		}

	}

}
