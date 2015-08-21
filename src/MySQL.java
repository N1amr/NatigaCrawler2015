
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/natega" + "?useUnicode=true&characterEncoding=UTF-8";
    private static final String DB_USER = "n1amr";
    private static final String DB_PASSWORD = "*******";

    private static Connection connection = null;
    private static Statement statement = null;

    public static void main(String[] args) {
	try {
	    connect();

	    int start = 355707;
	    int end = 355710;
	    for (int i = start; i < end; i++) {
		File file = new File("data/results/" + i + ".json");
		if (!file.exists())
		    continue;
		Natega natega = new Natega(JSONHelper.loadJSONObject(file));
		add(natega);
		// delete(i);
		float percentage = 100f * (i + 1 - start) / (end - start);
		System.out.println("%" + percentage);
	    }
	    System.out.println("***************************Saved************************");
	    System.out.println(getAsNatega(355707));
	    // showAll();
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    try {
		statement.close();
		connection.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	System.out.println("END");
    }

    private static Natega getAsNatega(int seatNumber) {
	try {
	    String sql = "SELECT * FROM `natega_table` WHERE `seatNumber` = " + seatNumber + ";";
	    ResultSet resultSet = statement.executeQuery(sql);
	    resultSet.next();

	    Natega natega = new Natega();
	    natega.setSeatNumber(resultSet.getInt("seatNumber"));
	    natega.setStudentName(resultSet.getString("studentName"));
	    natega.setSchoolName(resultSet.getString("schoolName"));
	    natega.setEducationalGov(resultSet.getString("educationalGov"));
	    natega.setEducationalMan(resultSet.getString("educationalMan"));
	    natega.setState(resultSet.getString("state"));

	    natega.setDegArabic(resultSet.getFloat("degArabic"));
	    natega.setDegEnglish(resultSet.getFloat("degEnglish"));
	    natega.setDeg2ndLanguage(resultSet.getFloat("deg2ndLanguage"));
	    natega.setDegHistory(resultSet.getFloat("degHistory"));
	    natega.setDegGeography(resultSet.getFloat("degGeography"));
	    natega.setDegLogic(resultSet.getFloat("degLogic"));
	    natega.setDegPsychology(resultSet.getFloat("degPsychology"));
	    natega.setDegBiology(resultSet.getFloat("degBiology"));
	    natega.setDegGeology(resultSet.getFloat("degGeology"));
	    natega.setDegChemistry(resultSet.getFloat("degChemistry"));
	    natega.setDegPhysics(resultSet.getFloat("degPhysics"));
	    natega.setDegMath(resultSet.getFloat("degMath"));
	    natega.setDegMechanics(resultSet.getFloat("degMechanics"));
	    natega.setDegTotal(resultSet.getFloat("degTotal"));

	    return natega;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }

    private static void connect() throws ClassNotFoundException, SQLException {
	Class.forName(DB_DRIVER);

	connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    }

    private static boolean exists(int seatNumber) {
	try {
	    String sql = "SELECT * FROM `natega_table` WHERE `seatNumber` = " + seatNumber + ";";
	    ResultSet resultSet = statement.executeQuery(sql);

	    resultSet.last();
	    int count = resultSet.getRow();

	    return count != 0;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return false;
    }

    private static boolean delete(int seatNumber) {
	try {
	    String sql = "DELETE FROM natega_table WHERE seatNumber = " + seatNumber + ";";
	    boolean success = statement.execute(sql);
	    return success;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return false;
    }

    private static boolean deleteAll() {
	try {
	    String sql = "DELETE FROM `natega_table` WHERE 1;";
	    boolean success = statement.execute(sql);
	    return success;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return false;
    }

    private static boolean add(int seatNumber, String studentName) {
	try {
	    if (exists(seatNumber))
		return false;
	    String sql = "INSERT INTO `natega_table`(`studentName`, `seatNumber`) VALUES ('" + studentName + "',"
		    + seatNumber + ")";
	    boolean success = statement.execute(sql);
	    return success;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return false;
    }

    private static boolean add(Natega natega) {
	try {
	    if (exists(natega.getSeatNumber()))
		return false;
	    String sql = String.format(
		    "INSERT INTO `natega_table`(`studentName`, `seatNumber`, `educationalGov`, `educationalMan`, `schoolName`, `state`, `degArabic`, `degEnglish`, `deg2ndLanguage`, `degHistory`, `degGeography`, `degLogic`, `degPsychology`, `degBiology`, `degGeology`, `degChemistry`, `degPhysics`, `degMath`, `degMechanics`, `degTotal`) VALUES ('%s',%d,'%s','%s','%s','%s',%f,%f,%f,%f,%f,%f,%f,%f,%f,%f,%f,%f,%f,%f)",
		    natega.getStudentName(), natega.getSeatNumber(), natega.getEducationalGov(),
		    natega.getEducationalMan(), natega.getSchoolName(), natega.getState(), natega.getDegArabic(),
		    natega.getDegEnglish(), natega.getDeg2ndLanguage(), natega.getDegHistory(),
		    natega.getDegGeography(), natega.getDegLogic(), natega.getDegPsychology(), natega.getDegBiology(),
		    natega.getDegGeology(), natega.getDegChemistry(), natega.getDegPhysics(), natega.getDegMath(),
		    natega.getDegMechanics(), natega.getDegTotal());
	    boolean success = statement.execute(sql);
	    return success;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return false;
    }
}
