import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class HouseholdInfoParser3 {
	
	// Method to parse household data from a CSV file and assign the values to a POJO (Plain Old Java Object) instance. 
	// need to make sure "," is not present in any column
	// Input : String (filePath)
	// Output : List of Occupants
	public static List<Occupant> parseHouseholdInformation(String filePath){
        List<Occupant> occupantList = new ArrayList<>();
    	try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
    	    String line;
    	    while ((line = br.readLine()) != null) {
    	        String[] values = line.split(",");
    	        if (values.length == 6) {
    	        	Occupant occupant = new Occupant(values[0],values[1],values[2],values[3],values[4],Integer.valueOf(values[5]));
    	        	occupantList.add(occupant);
    	        } else {
                    System.out.println("Invalid data format: " + line);
                }
    	    }
    	}catch(Exception e) {
    		System.out.println(e);
    	}
    	return occupantList;
    }

}
