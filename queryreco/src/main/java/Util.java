import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;


public class Util {
	/**
	 * ׷�����
	 * @param filePathAndName
	 * @param fileContent
	 */
	public static void appendData(String filePathAndName, String fileContent) {
        try {
            File myFilePath = new File(filePathAndName.toString());
            if (!myFilePath.exists()) { 
                myFilePath.createNewFile();
            }
            // FileWriter(myFilePath, true); 
             //FileWriter(myFilePath); 
            FileWriter resultFile = new FileWriter(myFilePath, true);
            PrintWriter myFile = new PrintWriter(resultFile);
            
            myFile.println(fileContent);
            resultFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
