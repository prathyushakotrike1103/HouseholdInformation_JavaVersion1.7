import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HouseholdInfoParser1 {
	
	// Method to parse household data from a txt file and assign the values to a POJO (Plain Old Java Object) instance.
	// Input : String (filePath)
	// Output : List of Occupants
	public static List<Occupant> parseHouseholdInformation(String filePath) {
        List<Occupant> occupantList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	line = line.substring(1, line.length()-1);
                String[] values = line.split("\",\"");
                if (values.length == 6) {
                	Occupant occupant = new Occupant();
                	occupant.setFirstName(values[0]);
                	occupant.setLastName(values[1]);
                	occupant.setAddress(values[2]);
                	occupant.setCity(values[3]);
                	occupant.setState(values[4]);
                	occupant.setAge(Integer.parseInt(values[5]));
                	occupantList.add(occupant);
                } else {
                    System.out.println("Invalid data format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return occupantList;
    }

}
