import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class test {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {

		String date = "2018-08-15T05:30:33.000Z";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date d = sdf.parse(date);
		System.out.println("d "+d);
		
		System.out.println(TimeZone.getDefault());
		
		

	}

}
