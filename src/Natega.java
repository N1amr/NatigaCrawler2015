import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class Natega {

    private String studentName = null;
    private int seatNumber = 0;
    private String educationalGov = null;
    private String educationalMan = null;
    private String schoolName = null;
    private String state = null;

    private float degArabic = 0;
    private float degEnglish = 0;
    private float deg2ndLanguage = 0;
    private float degHistory = 0;
    private float degGeography = 0;
    private float degLogic = 0;
    private float degPsychology = 0;
    private float degBiology = 0;
    private float degGeology = 0;
    private float degChemistry = 0;
    private float degPhysics = 0;
    private float degMath = 0;
    private float degMechanics = 0;
    private float degTotal = 0;

    public static final String FIELD_STUDENT_NAME = "std_name";
    public static final String FIELD_SEAT_NUMBER = "seating_no";
    public static final String FIELD_GOVERNORATE_NAME = "mud_name";
    public static final String FIELD_MANAGEMENT_NAME = "edara_name";
    public static final String FIELD_SCHOOL_NAME = "school_name";
    public static final String FIELD_STATE = "Label7";

    public static final String FIELD_ARABIC_DEGREE = "s1";
    public static final String FIELD_ENGLISH_DEGREE = "s2";
    public static final String FIELD_SECOND_LANGUAGE_DEGREE = "s3";

    public static final String FIELD_HISTORY_DEGREE = "s17";
    public static final String FIELD_GEOGRAPHY_DEGREE = "s8";
    public static final String FIELD_PHILOSOPHY_DEGREE = "s18";
    public static final String FIELD_PSYCHOLOGY_DEGREE = "s9";

    public static final String FIELD_BIOLOGY_DEGREE = "s5";
    public static final String FIELD_GEOLOGY_DEGREE = "s7";
    public static final String FIELD_CHEMISTRY_DEGREE = "s4";
    public static final String FIELD_PHYSICS_DEGREE = "s15";
    public static final String FIELD_MATH_DEGREE = "s6";
    public static final String FIELD_MECHANICS_DEGREE = "s16";
    public static final String FIELD_TOTAL_DEGREE = "total";

    public static Natega fromMap(Map<String, String> params) {
	try {
	    return new Natega(params.get(FIELD_STUDENT_NAME), params.get(FIELD_SEAT_NUMBER),
		    params.get(FIELD_GOVERNORATE_NAME), params.get(FIELD_MANAGEMENT_NAME),
		    params.get(FIELD_SCHOOL_NAME), params.get(FIELD_STATE), params.get(FIELD_ARABIC_DEGREE),
		    params.get(FIELD_ENGLISH_DEGREE), params.get(FIELD_SECOND_LANGUAGE_DEGREE),
		    params.get(FIELD_HISTORY_DEGREE), params.get(FIELD_GEOGRAPHY_DEGREE),
		    params.get(FIELD_PHILOSOPHY_DEGREE), params.get(FIELD_PSYCHOLOGY_DEGREE),
		    params.get(FIELD_BIOLOGY_DEGREE), params.get(FIELD_GEOLOGY_DEGREE),
		    params.get(FIELD_CHEMISTRY_DEGREE), params.get(FIELD_PHYSICS_DEGREE), params.get(FIELD_MATH_DEGREE),
		    params.get(FIELD_MECHANICS_DEGREE), params.get(FIELD_TOTAL_DEGREE));
	} catch (Exception e) {
	    return null;
	}
    }

    public Natega(String std_name, String seating_no, String mud_name, String edara_name, String school_name,
	    String Label7, String s1, String s2, String s3, String s17, String s8, String s18, String s9, String s5,
	    String s7, String s4, String s15, String s6, String s16, String total) {
	studentName = std_name;
	seatNumber = Integer.valueOf(seating_no);
	educationalGov = mud_name;
	educationalMan = edara_name;
	schoolName = school_name;
	state = Label7;

	try {
	    degArabic = Float.valueOf(s1);
	} catch (Exception e) {
	}

	try {
	    degEnglish = Float.valueOf(s2);
	} catch (Exception e) {
	}

	try {
	    deg2ndLanguage = Float.valueOf(s3);
	} catch (Exception e) {
	}

	try {
	    degHistory = Float.valueOf(s17);
	} catch (Exception e) {
	}

	try {
	    degGeography = Float.valueOf(s8);
	} catch (Exception e) {
	}

	try {
	    degLogic = Float.valueOf(s18);
	} catch (Exception e) {
	}

	try {
	    degPsychology = Float.valueOf(s9);
	} catch (Exception e) {
	}

	try {
	    degBiology = Float.valueOf(s5);
	} catch (Exception e) {
	}

	try {
	    degGeology = Float.valueOf(s7);
	} catch (Exception e) {
	}

	try {
	    degChemistry = Float.valueOf(s4);
	} catch (Exception e) {
	}

	try {
	    degPhysics = Float.valueOf(s15);
	} catch (Exception e) {
	}

	try {
	    degMath = Float.valueOf(s6);
	} catch (Exception e) {
	}

	try {
	    degMechanics = Float.valueOf(s16);
	} catch (Exception e) {
	}
	try {
	    degTotal = Float.valueOf(total);
	} catch (Exception e) {
	}

    }

    public Natega(JSONObject jsonObject) {
	studentName = jsonObject.getString("Name");
	seatNumber = jsonObject.getInt("SeatNumber");
	educationalGov = jsonObject.getString("Educational Govenorate");
	educationalMan = jsonObject.getString("Educational Management");
	schoolName = jsonObject.getString("School");
	state = jsonObject.getString("Governemnt");

	JSONObject degrees = jsonObject.getJSONObject("Degrees");
	degArabic = (float) degrees.getDouble("Arabic");
	degEnglish = (float) degrees.getDouble("English");
	deg2ndLanguage = (float) degrees.getDouble("Second Language");
	degHistory = (float) degrees.getDouble("History");
	degGeography = (float) degrees.getDouble("Geography");
	degLogic = (float) degrees.getDouble("Philosophy");
	degPsychology = (float) degrees.getDouble("Psychology");
	degBiology = (float) degrees.getDouble("Biology");
	degGeology = (float) degrees.getDouble("Geology");
	degChemistry = (float) degrees.getDouble("Chemistry");
	degPhysics = (float) degrees.getDouble("Physics");
	degMath = (float) degrees.getDouble("Math");
	degMechanics = (float) degrees.getDouble("Mechanics");
	degTotal = (float) degrees.getDouble("Total");
    }

    public Natega() {
    }

    public JSONObject toJSON() {
	JSONObject jsonObject = new JSONObject();

	jsonObject.put("Name", studentName);
	jsonObject.put("Name", studentName);
	jsonObject.put("SeatNumber", seatNumber);
	jsonObject.put("Educational Govenorate", educationalGov);
	jsonObject.put("Educational Management", educationalMan);
	jsonObject.put("School", schoolName);
	jsonObject.put("Governemnt", state);

	JSONObject degrees = new JSONObject();
	degrees.put("Arabic", degArabic);
	degrees.put("English", degEnglish);
	degrees.put("Second Language", deg2ndLanguage);
	degrees.put("History", degHistory);
	degrees.put("Geography", degGeography);
	degrees.put("Philosophy", degLogic);
	degrees.put("Psychology", degPsychology);
	degrees.put("Biology", degBiology);
	degrees.put("Geology", degGeology);
	degrees.put("Chemistry", degChemistry);
	degrees.put("Physics", degPhysics);
	degrees.put("Math", degMath);
	degrees.put("Mechanics", degMechanics);
	degrees.put("Total", degTotal);

	jsonObject.put("Degrees", degrees);

	return jsonObject;
    }

    public static HashMap<String, String> getNulledParametersMap() {
	HashMap<String, String> params = new HashMap<String, String>();

	params.put(FIELD_ARABIC_DEGREE, null);
	params.put(FIELD_ARABIC_DEGREE, null);
	params.put(FIELD_ARABIC_DEGREE, null);
	params.put(FIELD_ARABIC_DEGREE, null);
	params.put(FIELD_ARABIC_DEGREE, null);
	params.put(FIELD_BIOLOGY_DEGREE, null);
	params.put(FIELD_CHEMISTRY_DEGREE, null);
	params.put(FIELD_ENGLISH_DEGREE, null);
	params.put(FIELD_GEOGRAPHY_DEGREE, null);
	params.put(FIELD_GEOLOGY_DEGREE, null);
	params.put(FIELD_GOVERNORATE_NAME, null);
	params.put(FIELD_HISTORY_DEGREE, null);
	params.put(FIELD_MANAGEMENT_NAME, null);
	params.put(FIELD_MATH_DEGREE, null);
	params.put(FIELD_MECHANICS_DEGREE, null);
	params.put(FIELD_PHILOSOPHY_DEGREE, null);
	params.put(FIELD_PHYSICS_DEGREE, null);
	params.put(FIELD_PSYCHOLOGY_DEGREE, null);
	params.put(FIELD_SCHOOL_NAME, null);
	params.put(FIELD_SEAT_NUMBER, null);
	params.put(FIELD_SECOND_LANGUAGE_DEGREE, null);
	params.put(FIELD_STATE, null);
	params.put(FIELD_STUDENT_NAME, null);
	params.put(FIELD_TOTAL_DEGREE, null);

	return params;
    }

    @Override
    public String toString() {
	String ans = "";
	ans += "—ﬁ„ «·Ã·Ê”: " + seatNumber;
	ans += "\n«·«”„: " + studentName;
	ans += "\n«·„œ—”…: " + schoolName;
	ans += "\n«·≈œ«—… «· ⁄·Ì„Ì…: " + educationalMan;
	ans += "\n«·„œÌ—Ì… «· ⁄·Ì„Ì…: " + educationalGov;
	ans += "\n«·Õ«·…: " + state;
	ans += "\n«·œ—Ã« \n";
	ans += "\n·€… ⁄—»Ì…: " + degArabic;
	ans += "\n·€… ≈‰Ã·Ì“Ì…: " + degEnglish;
	ans += "\n·€… √Ã‰»Ì… À«‰Ì…: " + deg2ndLanguage;
	ans += "\n«· «—ÌŒ: " + degHistory;
	ans += "\n«·Ã€—«›Ì«: " + degGeography;
	ans += "\n›·”›… Ê„‰ÿﬁ: " + degLogic;
	ans += "\n⁄·„ «·‰›”: " + degPsychology;
	ans += "\n«·√ÕÌ«¡: " + degBiology;
	ans += "\n«·ÃÌÊ·ÊÃÌ«: " + degGeology;
	ans += "\n«·ﬂÌ„Ì«¡: " + degChemistry;
	ans += "\n«·›Ì“Ì«¡: " + degPhysics;
	ans += "\n«·—Ì«÷Ì«  «·»Õ‰…: " + degMath;
	ans += "\n«·—Ì«÷Ì«  «· ÿ»ÌﬁÌ…: " + degMechanics;
	ans += "\n\n«·„Ã„Ê⁄: " + degTotal;
	return ans;
    }

    /**
     * @return the studentName
     */
    public String getStudentName() {
	return studentName;
    }

    /**
     * @param studentName
     *            the studentName to set
     */
    public void setStudentName(String studentName) {
	this.studentName = studentName;
    }

    /**
     * @return the seatNumber
     */
    public int getSeatNumber() {
	return seatNumber;
    }

    /**
     * @param seatNumber
     *            the seatNumber to set
     */
    public void setSeatNumber(int seatNumber) {
	this.seatNumber = seatNumber;
    }

    /**
     * @return the educationalGov
     */
    public String getEducationalGov() {
	return educationalGov;
    }

    /**
     * @param educationalGov
     *            the educationalGov to set
     */
    public void setEducationalGov(String educationalGov) {
	this.educationalGov = educationalGov;
    }

    /**
     * @return the educationalMan
     */
    public String getEducationalMan() {
	return educationalMan;
    }

    /**
     * @param educationalMan
     *            the educationalMan to set
     */
    public void setEducationalMan(String educationalMan) {
	this.educationalMan = educationalMan;
    }

    /**
     * @return the schoolName
     */
    public String getSchoolName() {
	return schoolName;
    }

    /**
     * @param schoolName
     *            the schoolName to set
     */
    public void setSchoolName(String schoolName) {
	this.schoolName = schoolName;
    }

    /**
     * @return the state
     */
    public String getState() {
	return state;
    }

    /**
     * @param state
     *            the state to set
     */
    public void setState(String state) {
	this.state = state;
    }

    /**
     * @return the degArabic
     */
    public float getDegArabic() {
	return degArabic;
    }

    /**
     * @param degArabic
     *            the degArabic to set
     */
    public void setDegArabic(float degArabic) {
	this.degArabic = degArabic;
    }

    /**
     * @return the degEnglish
     */
    public float getDegEnglish() {
	return degEnglish;
    }

    /**
     * @param degEnglish
     *            the degEnglish to set
     */
    public void setDegEnglish(float degEnglish) {
	this.degEnglish = degEnglish;
    }

    /**
     * @return the deg2ndLanguage
     */
    public float getDeg2ndLanguage() {
	return deg2ndLanguage;
    }

    /**
     * @param deg2ndLanguage
     *            the deg2ndLanguage to set
     */
    public void setDeg2ndLanguage(float deg2ndLanguage) {
	this.deg2ndLanguage = deg2ndLanguage;
    }

    /**
     * @return the degHistory
     */
    public float getDegHistory() {
	return degHistory;
    }

    /**
     * @param degHistory
     *            the degHistory to set
     */
    public void setDegHistory(float degHistory) {
	this.degHistory = degHistory;
    }

    /**
     * @return the degGeography
     */
    public float getDegGeography() {
	return degGeography;
    }

    /**
     * @param degGeography
     *            the degGeography to set
     */
    public void setDegGeography(float degGeography) {
	this.degGeography = degGeography;
    }

    /**
     * @return the degLogic
     */
    public float getDegLogic() {
	return degLogic;
    }

    /**
     * @param degLogic
     *            the degLogic to set
     */
    public void setDegLogic(float degLogic) {
	this.degLogic = degLogic;
    }

    /**
     * @return the degPsychology
     */
    public float getDegPsychology() {
	return degPsychology;
    }

    /**
     * @param degPsychology
     *            the degPsychology to set
     */
    public void setDegPsychology(float degPsychology) {
	this.degPsychology = degPsychology;
    }

    /**
     * @return the degBiology
     */
    public float getDegBiology() {
	return degBiology;
    }

    /**
     * @param degBiology
     *            the degBiology to set
     */
    public void setDegBiology(float degBiology) {
	this.degBiology = degBiology;
    }

    /**
     * @return the degGeology
     */
    public float getDegGeology() {
	return degGeology;
    }

    /**
     * @param degGeology
     *            the degGeology to set
     */
    public void setDegGeology(float degGeology) {
	this.degGeology = degGeology;
    }

    /**
     * @return the degChemistry
     */
    public float getDegChemistry() {
	return degChemistry;
    }

    /**
     * @param degChemistry
     *            the degChemistry to set
     */
    public void setDegChemistry(float degChemistry) {
	this.degChemistry = degChemistry;
    }

    /**
     * @return the degPhysics
     */
    public float getDegPhysics() {
	return degPhysics;
    }

    /**
     * @param degPhysics
     *            the degPhysics to set
     */
    public void setDegPhysics(float degPhysics) {
	this.degPhysics = degPhysics;
    }

    /**
     * @return the degMath
     */
    public float getDegMath() {
	return degMath;
    }

    /**
     * @param degMath
     *            the degMath to set
     */
    public void setDegMath(float degMath) {
	this.degMath = degMath;
    }

    /**
     * @return the degMechanics
     */
    public float getDegMechanics() {
	return degMechanics;
    }

    /**
     * @param degMechanics
     *            the degMechanics to set
     */
    public void setDegMechanics(float degMechanics) {
	this.degMechanics = degMechanics;
    }

    /**
     * @return the degTotal
     */
    public float getDegTotal() {
	return degTotal;
    }

    /**
     * @param degTotal
     *            the degTotal to set
     */
    public void setDegTotal(float degTotal) {
	this.degTotal = degTotal;
    }

    public float getPercentage() {
	return ((int) (degTotal / 410 * 100 * 1000)) / 1000f;
    }
}
