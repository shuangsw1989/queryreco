package wss.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import wss.model.Userlog;
import wss.service.UserlogService;
import wss.service.impl.UserlogServiceImpl;

public class OperateFile {

	
	public static List<Userlog> readFile(String filePath){
		List<Userlog> logList = new ArrayList<Userlog>();
		File file = new File(filePath);
		String encoding ="GBK";
		InputStreamReader read;
		try {
			read = new InputStreamReader(new FileInputStream(file),encoding);
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				String[] logInfo = lineTxt.split("\t");
				
					String visitTime = logInfo[0];
					String uid = logInfo[1];
					String queryWord = logInfo[2];
					String urlRank = logInfo[3];
					String clickOrder = logInfo[4];
					String clickUrl = logInfo[5];
					  
				logList.add(new Userlog(visitTime,uid,queryWord,urlRank,clickOrder,clickUrl));
			}
			bufferedReader.close();
			read.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return logList;
	}
	
	

	public static void main(String[] args) {
		String filePath ="data/SogouQ.mini";
		List<Userlog> logList = readFile(filePath);
		ApplicationContext ctx = new ClassPathXmlApplicationContext("springcontext-config.xml");  
		  UserlogService userlogService =(UserlogServiceImpl)ctx.getBean("userlogService");
		for(int i=0;i<logList.size();i++){
			userlogService.insert(logList.get(i));
		}
	}

}
