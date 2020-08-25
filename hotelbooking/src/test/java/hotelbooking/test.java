package hotelbooking;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.hotel.constant.BookingConstant;

public class test {

	static void abc(String...strings) {
		System.out.println(strings);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		test.abc("1","2","3");
		List<Integer> l = Arrays.asList(1,2,3);
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern(BookingConstant.YYYY_MM_DD);
		Date d = Date.from(LocalDate.parse("2020-08-22", format.withResolverStyle(ResolverStyle.SMART)).atStartOfDay(ZoneId.systemDefault()).toInstant());
		System.out.println(d);
		
		System.out.println(LocalDate.now());
		System.out.println(LocalDate.now().toString());
		
	//	 d = Date.from(LocalDate.parse(LocalDate.now().toString(), format).atStartOfDay(ZoneId.systemDefault()).toInstant());
		//System.out.println(d);
		
		//System.out.println(format.toFormat().format(d));
		
		Instant i =  d.toInstant();
		LocalDate ld = i.atZone(ZoneId.systemDefault()).toLocalDate();
		System.out.println(ld.format(format));
		
	}

}
