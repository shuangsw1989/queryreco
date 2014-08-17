import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class SSA {

	public static  void readAllDataTxtFile(String filePath) {
		File file = new File(filePath);

		if (file.isFile() && file.exists()) {
			String encoding = "GBK";
			try {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					String[] lineTxtArray = lineTxt.split(Constant.AttrSplit);// 将读取得字符串分割，放入一个数组中
					// System.out.println("line text:" + docInfo[2]);
					// for (int i = 0; i < lineTxtArray.length; i++) {
					String queryWord = "";
					String queryId = "";
					String url = "";
					String urlId = "";
					queryWord = lineTxtArray[2];
					queryId = getQueryId(queryWord);// 调用方法获得症状的id
					url = lineTxtArray[5];
					urlId = getUrlId(url);
					// 将医生id,症状id,相对应症状的诊次，写入ratingall.txt文件中
					Util.appendData(Constant.QueryFile, queryId
							+ Constant.AttrSplit + queryWord);
					Util.appendData(Constant.UrlFile, urlId
							+ Constant.AttrSplit + url);
					// }
				}
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
	}

	/**
	 * 获取urlId
	 * 
	 * @param url
	 * @return
	 */
	public static String getUrlId(String url) {
		File file = new File(Constant.LogFile);

		String encoding = "GBK";
		String maxId = "0";
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					file), encoding);
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = "";
			while ((lineTxt = bufferedReader.readLine()) != null) {
				String[] lineArray = lineTxt.split(Constant.AttrSplit);
				if (lineArray[5].equals(url.trim())) {
					return ;
				}
				maxId = lineArray[0];

			}
			bufferedReader.close();
			read.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int newid = Integer.valueOf(maxId) + 1;
		// 将获得的症状写入到diseaseall.txt文件中
//		Util.appendData(Constant.UrlFile, newid + Constant.AttrSplit + url);
		return String.valueOf(newid);
	}

	/**
	 * 获取查询词的id
	 * 
	 * @param queryWord
	 * @return
	 */
	public static String getQueryId(String queryWord) {
		File file = new File(Constant.LogFile);

		String encoding = "GBK";
		String maxId = "0";
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					file), encoding);
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = "";
			while ((lineTxt = bufferedReader.readLine()) != null) {
				String[] lineArray = lineTxt.split(Constant.AttrSplit);
				if (lineArray[1].equals(queryWord.trim())) {
					return lineArray[0];
				}
				maxId = lineArray[0];

			}
			bufferedReader.close();
			read.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int newid = Integer.valueOf(maxId) + 1;
		// 将获得的症状写入到diseaseall.txt文件中
//		Util.appendData(Constant.QueryFile, newid + Constant.AttrSplit
//				+ queryWord.trim());
		return String.valueOf(newid);
	}
	
	public static void main(String[] args) {
		System.out.println("hhh");
		String fileName=Constant.LogFile;
		System.out.println("sss");
		readAllDataTxtFile(fileName);
		System.out.println("aaa");
	}

}
