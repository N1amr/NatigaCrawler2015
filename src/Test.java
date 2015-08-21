import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws Exception {

    }

    private static void rename() {
	File oldFailedFolder = new File("data/");
	File newFailedFolder = new File("data/failed");
	for (File file : newFailedFolder.listFiles()) {
	    String newFileName = file.getAbsolutePath();
	    file.delete();
	    File newFile = new File(newFileName);
	    try {
		newFile.createNewFile();
	    } catch (IOException e) {
		e.printStackTrace();
		break;
	    }

	    // System.out.println(newFileName);
	    // break;
	    // if (file.getName().endsWith(".txt")) {
	    // String newName = file.getName().substring(6, 12);
	    // System.out.println(newName);
	    // File newFile = new File(newFailedFolder.getAbsolutePath() + "/"
	    // + newName);
	    // file.renameTo(newFile);
	    // }
	}
    }

    private static void loadInvalid() {
	try {
	    File invalidFolder = new File("data/invalid");

	    File file = new File("invalids.dat");
	    DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
	    int count = dataInputStream.readInt();
	    System.out.println(count);
	    for (int i = 0; i < count; i++) {
		int x = dataInputStream.readInt();
		File newFile = new File(invalidFolder.getAbsolutePath() + "/" + x);
		newFile.createNewFile();
	    }
	    dataInputStream.close();
	} catch (Exception exception) {
	}
    }

}
