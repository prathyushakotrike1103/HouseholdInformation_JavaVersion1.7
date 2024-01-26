import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class HouseholdInfoParser2Test {
	List<Occupant> occupants = new ArrayList<Occupant>();

	@Before
	public void before() {
		occupants = HouseholdInfoParser2.parseHouseholdInformation("data.txt");
	}

	@Test
	public void getTotalSize() {
		assertTrue(occupants.size() == 10);
	}
}