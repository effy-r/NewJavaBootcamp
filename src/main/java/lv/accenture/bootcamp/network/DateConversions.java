package lv.accenture.bootcamp.network;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConversions 
{

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Long currentTime = System.currentTimeMillis();
		Date currentDate = new Date(currentTime);

		System.out.println(currentDate);
		System.out.println(currentTime);

		SimpleDateFormat dateFormated1 = new SimpleDateFormat("dd.MM.yyyy");

		System.out.println(dateFormated1.format(currentDate));

		SimpleDateFormat dateFormated2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		System.out.println(dateFormated2.format(currentDate));

		// aa - this is either AM or PM
		SimpleDateFormat dateFormated3 = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss aa z");

		System.out.println(dateFormated3.format(currentDate));
		
		Date timeIs = dateFormated1.parse("02.02.2020");
		String dateString = dateFormated1.format(currentDate);
		Date parsedDate = dateFormated1.parse("02.02.2020");
		long time = timeIs.getTime();
		
		System.out.println(timeIs);
		System.out.println(time);
		
		parsedDate = dateFormated1.parse(dateString);
		

		// https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html -
		// read about it here

	}

}
