

import java.io.BufferedInputStream;
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
	
	private static BufferedReader reader;

	public static Pattern p = Pattern.compile(",");
	
	public static Userlog lineToUserlog(String lineTxt){
//		String[] logInfo = lineTxt.split("\t");
		String[] logInfo = p.split(lineTxt);
		String visitTime = logInfo[0];
		String uid = logInfo[1];
		String queryWord = logInfo[2];
		String urlRank = logInfo[3];
		String clickOrder = logInfo[4];
		String clickUrl = logInfo[5];
		  
		return new Userlog(visitTime,uid,queryWord,urlRank,clickOrder,clickUrl);
	}
	
	public static void readFile(String filePath,UserlogService userlogService ) throws Exception{		
		File file = new File(filePath);
		String encoding ="GBK";
		
		int maxlines = 1000;
		List<Userlog> logList = new ArrayList<Userlog>();
		BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));    
		reader = new BufferedReader(new InputStreamReader(fis,encoding),200*1024*1024);
		  
		String line = "";
		int count=0;
		while((line = reader.readLine()) != null){		
			logList.add(lineToUserlog(line));
			
			count++;
			if(count%maxlines==0){
				System.out.println(count/maxlines);
				 userlogService.batchInsert(logList);
				 logList.clear();
			}			
		}
		
		if(count%maxlines!=0){
			 userlogService.batchInsert(logList);
		}
	}
	
	public static void main(String[] args) throws Exception {
		String filePath ="E:/Data/querylog";
//		String filePath ="data/SogouQ.mini";
		ApplicationContext ctx = new ClassPathXmlApplicationContext("springcontext-config.xml");  
		UserlogService userlogService =(UserlogServiceImpl)ctx.getBean("userlogService");
		readFile(filePath,userlogService);
	}

}
