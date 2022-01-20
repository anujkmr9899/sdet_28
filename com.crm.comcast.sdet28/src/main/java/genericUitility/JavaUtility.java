package genericUitility;

import java.util.Date;
import java.util.Random;

/**
 * Java utility class
 * @author Anuj Prajapati
 *
 */

public class JavaUtility {
	/**
	 * This method will return random number
	 * @return
	 */
	public int getRandomNumber() {
		Random ran = new Random();
		 int randomNumber = ran.nextInt(5000);
		 return randomNumber;
	}
	/**
	 * This Method will return system date and time
	 * @return
	 */

	public String systemDateAndTime() {
		 Date date = new Date();
		 String dateandTime = date.toString();
		return dateandTime;
		 
	}

}

