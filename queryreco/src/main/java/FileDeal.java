import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FileDeal {
	private static BufferedReader reader;

	public static Pattern p = Pattern.compile(",");

	public static String lineToUserlog(String lineTxt) {
		// String[] logInfo = lineTxt.split("\t");
		String[] logInfo = p.split(lineTxt);
		String visitTime = logInfo[0];
		String uid = logInfo[1];
		String queryWord = logInfo[2];
		String urlRank = logInfo[3];
		String clickOrder = logInfo[4];
		String clickUrl = logInfo[5];

		return "visitTime" + "uid" + "queryWord" + "urlRank" + "clickOrder"
				+ "clickUrl";
	}

	public static List<String> readFile(String fileName) {
		File file = new File(fileName);
		String encoding = "GBK";
		List<String> lineTxtList = new ArrayList<String>();
		String lineTxt = "";
		InputStreamReader read = null;
		BufferedReader bufferedReader = null;
		try {
			read = new InputStreamReader(new FileInputStream(file), encoding);
			bufferedReader = new BufferedReader(read);
			while ((lineTxt = bufferedReader.readLine()) != null) {

				System.out.println(lineTxt);
				lineTxtList.add(lineTxt);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lineTxtList;
	}
/**
 * 将获取到的查询词和URL进行编码，然后分别存入QueryFile,UrlFile
 * @param lineTxtList
 */
	public static void writeToFile(List<String> lineTxtList) {
		String maxQueryId = "0";
		String queryWord = "";
		String maxUrlId = "0";
		String url = "";
		for (int i = 0; i < lineTxtList.size(); i++) {
			if (lineTxtList.get(i) != null) {
				String[] lineArray = lineTxtList.get(i).split(
						Constant.AttrSplit);
				queryWord = lineArray[2];
				url = lineArray[5];
				maxQueryId = String.valueOf(Integer.valueOf(maxQueryId) + 1);
				maxUrlId = String.valueOf(Integer.valueOf(maxUrlId) + 1);
				Util.appendData(Constant.QueryFile, maxQueryId
						+ Constant.AttrSplit + queryWord.trim());
				Util.appendData(Constant.UrlFile, maxUrlId + Constant.AttrSplit
						+ url.trim());

			}
		}
	}

	public static void main(String[] args) {
		String fileName = Constant.LogFile;
	List<String>lineList=readFile(fileName);
	writeToFile(lineList);
		

	}
}
