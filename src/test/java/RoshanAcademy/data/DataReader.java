package RoshanAcademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.nio.charset.*;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	//Read json to string
	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
	String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\RoshanAcademy\\data\\PurchaseData.json"),
			StandardCharsets.UTF_8);
	//String to HashMap Jackson Databind
	
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>()
			{});
	return data;
	}
}