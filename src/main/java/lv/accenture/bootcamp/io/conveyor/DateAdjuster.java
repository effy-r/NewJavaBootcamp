package lv.accenture.bootcamp.io.conveyor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component()
public class DateAdjuster {

	protected Connection conn;

	public String adjustLectureTime() {

		try {

			// Creating a reader for course ID file

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

			// working with SQL database

			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bootcamp?autoReconnect=true&useSSL=false&characterEncoding=utf8",
					"admin", "abcd1234");

			Statement st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT LECTION_DTM FROM LECTION WHERE COURSE_ID  = " + courseId + ";");

			rs.next();
			String lectionTime = rs.getString("LECTION_DTM");
			
			conn.close();

			System.out.println("The lecture time is:" + lectionTime);
			
		

			//YYYY-MM-DD
			SimpleDateFormat dateFormated1 = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
			SimpleDateFormat dateFormated2 = new SimpleDateFormat("yyyy-MM-dd");

			Date parsedDate1 = dateFormated1.parse(lectionTime);
			long oldTime = parsedDate1.getTime();
			
			System.out.println("The formated time is: "  + oldTime);
			
			String formatedLecture = dateFormated2.format(oldTime);
			
			System.out.println("The formated date is: " + formatedLecture );
			//System.out.println("second format: " + parsedDate2);

			//long oldTime = parsedDate2.getTime();

			return formatedLecture;

			// String newTime = null;

//			Statement st2 = (Statement) conn.createStatement();
//			ResultSet rs2 = st.executeQuery("INSERT INTO LECTION (firstName, lastName) VALUES ('" + newTime + "');");

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// ./documents/course-id.txt
	// No tabulas jadabu visas lekcijas, kas saistitas ar kursu (course ID)
	// japanem lekciju sakuma laiki
	// kads bija saullekta laiks nodarbibas datuma (no SunriseAPI)
	// jaapdeito vertiba sql tabulaa ar sunrise laiku respektivi 00:00:00 vieta
	// jabut sunrise laikam
}
