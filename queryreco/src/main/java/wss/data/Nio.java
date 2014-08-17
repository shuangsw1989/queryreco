package wss.data;


	import java.io.BufferedReader; 
import java.io.File; 
import java.io.FileInputStream; 
import java.io.IOException; 
import java.io.RandomAccessFile; 
import java.nio.ByteBuffer; 
import java.nio.channels.FileChannel; 

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import wss.service.UserlogService;
import wss.service.impl.UserlogServiceImpl;
	 
	public class Nio { 
	 
	 
	    public static void main(String args[]) throws Exception{ 
	 
	    //String infile = "D:\\workspace\\test\\usagetracking.log"; 
	    //FileInputStream fin= new FileInputStream(infile); 
	    //FileChannel fcin = fin.getChannel(); 
	 
	  
	    	String filePath ="data/SogouQ.mini";
//		String filePath ="E:/Data/querylog";
		ApplicationContext ctx = new ClassPathXmlApplicationContext("springcontext-config.xml");  
		  UserlogService userlogService =(UserlogServiceImpl)ctx.getBean("userlogService");
//		readFile(filePath,userlogService);
		  int bufSize = 100; 
		    File fin = new File(filePath); 
//		    File fout = new File("D:\\workspace\\test\\usagetracking2.log"); 
		 
		    FileChannel fcin = new RandomAccessFile(fin, "r").getChannel(); 
		    ByteBuffer rBuffer = ByteBuffer.allocate(bufSize); 
	    readFileByLine(bufSize, fcin, rBuffer,userlogService); 
	 
	    System.out.print("OK!!!"); 
	    } 
	 
	    public static void readFileByLine(int bufSize, FileChannel fcin, ByteBuffer rBuffer,UserlogService userlogService ){
	        String enterStr = "\n"; 
	        try{ 
	        byte[] bs = new byte[bufSize]; 
	 
	        int size = 0; 
	        StringBuffer strBuf = new StringBuffer(""); 
	        //while((size = fcin.read(buffer)) != -1){ 
	        while(fcin.read(rBuffer) != -1){ 
	              int rSize = rBuffer.position(); 
	              rBuffer.rewind(); 
	              rBuffer.get(bs); 
	              rBuffer.clear(); 
	              String tempString = new String(bs, 0, rSize,"GBK"); 
	              //System.out.print(tempString); 
	              //System.out.print("<200>"); 
	 
	              int fromIndex = 0; 
	              int endIndex = 0; 
	              while((endIndex = tempString.indexOf(enterStr, fromIndex)) != -1){ 
	               String line = tempString.substring(fromIndex, endIndex); 
	               line = new String(strBuf.toString() + line); 
	               System.out.println(line); 
	               //System.out.print("</over/>"); 
	               //write to anthone file 
//	               writeFileByLine(fcout, wBuffer, line); 
	              
					
					  userlogService.insert( OperateFileToDB.lineToUserlog(line));
	                
	               strBuf.delete(0, strBuf.length()); 
	               fromIndex = endIndex + 1; 
	              } 
	              if(rSize > tempString.length()){ 
	              strBuf.append(tempString.substring(fromIndex, tempString.length())); 
	              }else{ 
	              strBuf.append(tempString.substring(fromIndex, rSize)); 
	              } 
	        } 
	        } catch (IOException e) { 
	        // TODO Auto-generated catch block 
	        e.printStackTrace(); 
	        } 
	    } 
	 
	  
	 
	}
