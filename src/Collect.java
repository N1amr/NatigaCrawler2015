import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class Collect {
    public static int fromSeatNumber = 355707;
    public static int toSeatNumber = 999999;
    public static int step = 1;
    public static boolean verbose = true;

    public static boolean skipInvalid = true;

    public static WebClient webClient;
    public static HtmlPage page;
    public static HtmlForm form;
    public static HtmlTextInput txtSeatNumber;
    public static HtmlSubmitInput btnSubmit;

    public static boolean[] checked = new boolean[1000000];

    public static File resultsFolder = new File("data/results");

    public static void main(String[] args) throws Exception {
	getInputFromUser();

	initialize();

	retryFailed();

	collectResults(fromSeatNumber, toSeatNumber + 1, step, verbose);

	System.out.println("************Done************");

	System.out.println(howManyChecked());
    }

    public static void collectResults2(int fromSeatNumber, int toSeatNumber, int step, boolean verbose)
	    throws Exception {

	for (int seatNumber = fromSeatNumber; seatNumber < toSeatNumber; seatNumber += step) {
	    if (checked[seatNumber])
		continue;

	    Natega natega = collectResult(seatNumber, verbose);
	    if (natega != null && (natega.getEducationalGov().equals("ÇáÞÇåÑÉ")
		    || natega.getEducationalGov().equals("ÇáÌíÒÉ") || natega.getEducationalGov().equals("ÇáÞáíæÈíÉ"))) {
		int tmp = seatNumber - step;
		for (; seatNumber > tmp; seatNumber--) {
		    if (checked[seatNumber])
			continue;
		    natega = collectResult(seatNumber, verbose);
		}
		for (; seatNumber < toSeatNumber; seatNumber++) {
		    if (checked[seatNumber])
			continue;
		    natega = collectResult(seatNumber, verbose);
		    if (natega != null && !(natega.getEducationalGov().equals("ÇáÞÇåÑÉ")
			    || natega.getEducationalGov().equals("ÇáÌíÒÉ")
			    || natega.getEducationalGov().equals("ÇáÞáíæÈíÉ")))
			break;
		}
	    }
	}
    }

    private static void initialize() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
	loadSavedFiles();
	loadInvalids();

	// Set up form variables
	webClient = new WebClient();
	page = webClient.getPage("http://thanresult.emis.gov.eg/NewNatega.aspx");
	form = page.getForms().get(0);
	txtSeatNumber = form.getInputByName("TextBox1");
	btnSubmit = form.getInputByName("Button3");

    }

    private static void getInputFromUser() {
	Scanner in = new Scanner(System.in);
	System.out.print("Enter Start Number: ");
	fromSeatNumber = in.nextInt();

	System.out.print("Enter End Number: ");
	toSeatNumber = in.nextInt();

	System.out.print("Enter Step: ");
	step = in.nextInt();

	System.out.print("Enter saveInvalidsInterval: ");

	in.close();
    }

    public static Natega collectResult(int seatNumber, boolean verbose) throws Exception {

	if (verbose)
	    System.out.print("" + seatNumber + ": ");

	Natega natega = null;

	try {
	    natega = getNatega(seatNumber);
	} catch (Exception e) {
	    e.printStackTrace();
	    markFailed(seatNumber);
	    return null;
	}
	if (natega != null) {
	    // if (verbose)
	    if (natega.getDegTotal() >= 384.5 && natega.getDegMath() != 0)
		System.err.print("From: " + natega.getEducationalGov() + " got " + natega.getDegTotal() + " ("
			+ natega.getPercentage() + "%)" + "\tName:  " + natega.getStudentName());
	    else
		System.out.print("From: " + natega.getEducationalGov() + " got " + natega.getDegTotal() + " ("
			+ natega.getPercentage() + "%)" + "\tName:  " + natega.getStudentName());

	    File nategaFile = new File("data/results/" + seatNumber + ".json");
	    JSONHelper.saveJSONObject(nategaFile, natega.toJSON());
	    checked[seatNumber] = true;
	    unMarkFailed(seatNumber);
	} else {
	    saveInvalid(seatNumber);
	    checked[seatNumber] = true;
	    unMarkFailed(seatNumber);
	    if (verbose)
		System.out.print("Not available");
	}

	if (verbose)
	    System.out.println();
	return natega;
    }

    private static void markFailed(int seatNumber) throws IOException {
	String newFileName = "data/failed/" + seatNumber;
	File newFile = new File(newFileName);
	if (!newFile.exists())
	    newFile.createNewFile();
    }

    private static void unMarkFailed(int seatNumber) {
	String newFileName = "data/failed/" + seatNumber;
	File newFile = new File(newFileName);
	if (newFile.exists())
	    newFile.delete();
    }

    public static void collectResults(int fromSeatNumber, int toSeatNumber, int step, boolean verbose)
	    throws Exception {

	for (int seatNumber = fromSeatNumber, c = 0; seatNumber < toSeatNumber; seatNumber += step) {
	    if (checked[seatNumber])
		continue;
	    collectResult(seatNumber, verbose);
	}
    }

    private static void saveInvalid(int seatNumber) {
	try {
	    File invalidFolder = new File("data/invalid");
	    File file = new File(invalidFolder.getAbsolutePath() + "/" + seatNumber);
	    if (!file.exists())
		file.createNewFile();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private static void loadInvalids() {
	try {
	    File invalidFolder = new File("data/invalid");

	    for (File file : invalidFolder.listFiles()) {
		int x = Integer.valueOf(file.getName());
		if (skipInvalid) {
		    checked[x] = true;
		    unMarkFailed(x);
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	System.out.println("loadInvalids");
    }

    private static void loadSavedFiles() {
	for (File f : resultsFolder.listFiles()) {
	    String filename = f.getName();
	    int dot = filename.indexOf(".json");
	    if (dot != -1) {
		int x = Integer.valueOf(filename.substring(0, dot));
		checked[x] = true;
		unMarkFailed(x);
	    }
	}
	System.out.println("loadSavedFiles");
    }

    public static Natega getNatega(int seatNumber) throws Exception {
	txtSeatNumber.setValueAttribute(Integer.toString(seatNumber));
	HtmlPage resultPage = btnSubmit.click();

	final List<DomElement> spans = resultPage.getElementsByTagName("span");

	HashMap<String, String> params = Natega.getNulledParametersMap();
	for (DomElement element : spans) {
	    String fieldName = element.getAttribute("id");
	    if (params.containsKey(fieldName)) {
		String fieldContent = element.getTextContent();
		params.put(fieldName, fieldContent);
	    }
	}
	return Natega.fromMap(params);
    }

    public static int howManyChecked() {
	int c = 0;
	for (int i = 0; i < checked.length; i++) {
	    if (checked[i])
		c++;
	}
	return c;
    }

    private static void retryFailed() {
	File failedFolder = new File("data/failed");
	for (File file : failedFolder.listFiles()) {
	    int seatNumber = Integer.valueOf(file.getName());
	    try {
		collectResult(seatNumber, true);
	    } catch (Exception e) {
	    }
	}
    }
}
