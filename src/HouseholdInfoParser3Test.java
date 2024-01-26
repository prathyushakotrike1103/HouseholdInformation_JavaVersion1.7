
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class HouseholdInfoParser3Test {
	List<Occupant> occupants = new ArrayList<Occupant>();

	@Before
	public void before() {
		occupants = HouseholdInfoParser3.parseHouseholdInformation("data.csv");
	}

	@Test
	public void getTotalSize() {
		assertTrue(occupants.size() == 10);
	}
}