package wss.data;

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

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import wss.model.Userlog;
import wss.service.UserlogService;
import wss.service.impl.UserlogServiceImpl;

public class OperateFileToDB {
	
	public static Pattern p = Pattern.compile("\t"); 
	
	public static Userlog lineToUserlog(String lineTxt){
//		String[] logInfo = lineTxt.split("\t");
		String[] logInfo = p.split( lineTxt);
		String visitTime = logInfo[0];
		String uid = logInfo[1];
		String queryWord = logInfo[2];
		String urlRank = logInfo[3];
		String clickOrder = logInfo[4];
		String clickUrl = logInfo[5];
		  
		return new Userlog(visitTime,uid,queryWord,urlRank,clickOrder,clickUrl);
		
	}
	
	
	public static void readFile(String filePath,UserlogService userlogService ){
		
		File file = new File(filePath);
		String encoding ="GBK";
		InputStreamReader read;
		try {
			read = new InputStreamReader(new FileInputStream(file),encoding);
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				Userlog userlog = lineToUserlog(lineTxt);
				
				  userlogService.insert(userlog);
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
		
	}
	public static void main(String[] args) {
		String filePath ="E:/Data/querylog";
		ApplicationContext ctx = new ClassPathXmlApplicationContext("springcontext-config.xml");  
		  UserlogService userlogService =(UserlogServiceImpl)ctx.getBean("userlogService");
		readFile(filePath,userlogService);
	}

}
