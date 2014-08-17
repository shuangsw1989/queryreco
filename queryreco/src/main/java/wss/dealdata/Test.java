package wss.dealdata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Test {
	  public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new FileReader(new File("SogouQ")),"GB2312");
         File f = new File("SogouQ_process");
          OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f),"UTF-8");
          BufferedWriter writer=new BufferedWriter(write);
          BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("SogouQ"),"GB2312"));
          String line = null;

          while ((line = in.readLine()) != null) {
                  if(line.split("\t").length<6){
                          System.out.println(line);
                  }
                  writer.write(line+"\n");
          }


        in.close();
          writer.close();
          System.out.println("f");
}

}
