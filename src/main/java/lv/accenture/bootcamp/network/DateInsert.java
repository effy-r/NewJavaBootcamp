package lv.accenture.bootcamp.network;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateInsert {
	private Connection conn;

	public void DateInsert(String newDate) {

		try {
			File courseIdFile = new File("./documents/course-id.txt");

			FileReader iReader = new FileReader(courseIdFile);

			BufferedReader bReader = new BufferedReader(iReader);

			String line = null;

			StringBuilder strBuilder = new StringBuilder();

			while ((line = bReader.readLine()) != null) {

				strBuilder.append(line + "\n");

			}

			String courseId = strBuilder.toString();
			bReader.close();

			// working with the date

			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
			SimpleDateFormat dateHours = new SimpleDateFormat("HH");
			SimpleDateFormat dateminutes = new SimpleDateFormat("mm");
			SimpleDateFormat dateseconds = new SimpleDateFormat("ss");

			// System.out.println(dateFormated1.format(currentDate));

			Date currentDate = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy").parse(newDate);

			System.out.println("NewDate is : !!!!!! " + newDate);

			System.out.println("CurrentDate is : !!!!!! " + currentDate);

			// Date timeIs = dateFormated1.parse("02.02.2020");

			// String dateString = dateFormated1.format(currentDate);

			// Date parsedDate = dateFormated1.parse("02.02.2020");

			String hours = dateHours.format(currentDate);

			int resultHours = Integer.parseInt(hours);

			System.out.println("--- hours " + resultHours);
			int resultMinutes = Integer.parseInt(dateminutes.format(currentDate));
			// System.out.println("--- hours " + resultHours);
			int resultSeconds = Integer.parseInt(dateseconds.format(currentDate));

			int addingTime = (resultHours * 60 * 60 * 1000) + (resultMinutes * 60 * 1000) + (resultSeconds * 1000);
			System.out.println("--- addingTime " + addingTime);

			// System.out.println("The formated time is: " + currentDate);

			// String formatedLecture = dateFormated2.format(oldTime);

			// working with SQL database

			System.out.println("The new time is" + newDate);

			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bootcamp?autoReconnect=true&useSSL=false&characterEncoding=utf8",
					"admin", "abcd1234");

			Statement st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT LECTION_DTM FROM LECTION WHERE COURSE_ID  = " + courseId + ";");

			rs.next();

			String lectionTime = rs.getString("LECTION_DTM");

			SimpleDateFormat dateFormated1 = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");

			Date parsedDate1 = dateFormated1.parse(lectionTime);

			long oldTime = parsedDate1.getTime() + addingTime;

			String formatedLecture = dateFormated1.format(oldTime);

			System.out.println("!!!!!!!!!!!!" + formatedLecture);

			Statement st2 = (Statement) conn.createStatement();

//			 st2.executeUpdate("INSERT INTO LECTION (LECTION_DTM) VALUES'" + formatedLecture
//			 + "')WHERE COURSE_ID =" + courseId + ";");

			st2.executeUpdate(
					"UPDATE LECTION SET LECTION_DTM = '" + formatedLecture + "' WHERE COURSE_ID=" + courseId + ";");

			conn.close();

			// String newTime = null;

//		Statement st2 = (Statement) conn.createStatement();
//		ResultSet rs2 = st.executeQuery("INSERT INTO LECTION (firstName, lastName) VALUES ('" + newTime + "');");

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
