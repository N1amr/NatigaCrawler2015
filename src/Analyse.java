import java.io.File;

import org.json.JSONObject;

public class Analyse {
    public static File resultsFolder = new File("D:\\Lab\\Java\\NatigaCrawler2015\\data\\results");

    public static void main(String[] args) {
	searchByName("ÚÈÏÇáÑÍãä ÚÕÇã");

	// calculateAverage();
	// calculateRankOverCairoMath(384.5f);
	// System.out.println("There are " + savedResultsCount() + " results");
	// System.out.println("************************************************");
    }

    private static int savedResultsCount() {
	return resultsFolder.listFiles().length;
    }

    private static void searchByName(String string) {
	for (File resultFile : resultsFolder.listFiles()) {
	    JSONObject jsonObject = JSONHelper.loadJSONObject(resultFile);
	    Natega natega = new Natega(jsonObject);
	    if (natega.getStudentName().contains(string)) {
		System.out.println(
			natega.getSeatNumber() + ",\t" + natega.getEducationalGov() + ",\t" + natega.getEducationalMan()
				+ ",\t" + natega.getStudentName() + ",\t" + natega.getSchoolName());
		System.out.println("****************************************************");
	    }
	}
	System.out.println("Finished");
    }

    private static void calculateAverage() {
	int count = 0;
	double sum = 0;
	for (File resultFile : resultsFolder.listFiles()) {
	    JSONObject jsonObject = JSONHelper.loadJSONObject(resultFile);
	    Natega natega = new Natega(jsonObject);
	    count++;
	    sum += natega.getDegTotal();
	}
	System.out.println("Average score is " + ((sum / count) / 4.1));
    }

    private static void calculateRankOverCairoMath(float degree) {
	int above = 0;
	int count3lmyryada = 0;
	for (File resultFile : resultsFolder.listFiles()) {
	    JSONObject jsonObject = JSONHelper.loadJSONObject(resultFile);
	    Natega natega = new Natega(jsonObject);
	    if (natega.getEducationalGov().equals("ÇáÞÇåÑÉ") || natega.getEducationalGov().equals("ÇáÌíÒÉ")
		    || natega.getEducationalGov().equals("ÇáÞáíæÈíÉ"))
		if (natega.getDegMath() != 0) {
		    count3lmyryada++;
		    if (natega.getDegTotal() >= degree)
			above++;
		}
	}
	System.out.println("A student got " + degree + " is ranked #" + above + " (estimated #"
		+ (1.0 * above / savedResultsCount() * 102326 * 0) + " over all Cairo)");
	System.out.println(above + " / " + count3lmyryada);

    }
}
