package SeleniumDBmySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class JDBC_mySQL_Selenium {

public static void testDBmySQL_INSERT_UPDATE_DELETE() throws SQLException {
		
		//Step1: Create Connection
	   Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dbselenium","root", "Test4test!");
		
		//Step2: Create statement
		Statement stmt = con.createStatement();
		
		//Step3: Execute sql statement for UPDATE, INSERT and DELETE
			//String s = "Update seleniumusers Set user = 'Bernd' Where email='deptrai2@gmail.com'";
			//String s = "Insert into seleniumusers values('Ballak','BallakPro@yahoo.com',39)";
			String s = "Delete From seleniumusers Where user='Ballak'";
			stmt.executeUpdate(s);
			
		
		//Step4: Close Connection
		con.close();
		System.out.println("Programm is exited");
	}


	public static void testDBmySQL_SELECT() throws SQLException, InterruptedException {
	System.setProperty("webdriver.chrome.driver","C:\\Users\\hbn\\Desktop\\Bach\\ProjekteDemo\\SeleniumDatabaseSQLTesting\\Drivers\\chromedriver.exe");
	 //Selenium
	 WebDriver driver = new FirefoxDriver();
	 
	 //Don't need, if you use latest JDBC driver.
	 //Class.forName("com.mysql.jdbc.Driver");
		
		//Step1: Create Connection
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dbselenium","root", "Test4test!");
		
		//Step2: Create statement:
		Statement stm = con.createStatement();
		
		//Step3: Execute SQL Statement SELECT
		ResultSet rs = stm.executeQuery("Select user, email From seleniumusers");
		
		//Selenium-Testpage:	
		driver.get("https://www.hotel.de/user/login?language=de");
		TimeUnit.SECONDS.sleep(6);
		
		//-->Cookies verify
		if(driver.findElement(By.id("onetrust-accept-btn-handler")).isDisplayed()) {
			driver.findElement(By.id("onetrust-accept-btn-handler")).click(); 
		}
				
		while(rs.next()) {		
			String name = rs.getString("user");
			String eMail = rs.getString("email");
			System.out.println("Users: " +name+ ", Mail: "+eMail);
			
			driver.get("https://www.hotel.de/user/login?language=de");
			TimeUnit.SECONDS.sleep(2);
			
			driver.findElement(By.id("input_email")).sendKeys(eMail);
			driver.findElement(By.id("input_password")).sendKeys(name);
			// and so on ...
		}
		
		//Step4: Close connection
		con.close();
	
	}

 public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
	
	 testDBmySQL_INSERT_UPDATE_DELETE();
	 testDBmySQL_SELECT(); 
  }

}
