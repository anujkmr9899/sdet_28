package genericUitility;

import java.io.FileInputStream;
import java.util.Properties;

public class FileUtility {
	public String getPropertyFileData(String key) throws Throwable {
		FileInputStream file = new FileInputStream(IpathConstants.PROPERTY_FILEPATH);
		Properties property = new Properties();
		property.load(file);
		return property.getProperty(key);
	}

}
