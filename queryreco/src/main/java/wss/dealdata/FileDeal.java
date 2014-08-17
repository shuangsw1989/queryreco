package wss.dealdata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileDeal {
	
	public static void readFile(String path) {
		OutputStreamWriter write = null;
		BufferedWriter writer = null;
		BufferedReader in = null;
		try {
			File f = new File(path);
			File f2 = new File("data/word.txt");
			write = new OutputStreamWriter(new FileOutputStream(f2), "UTF-8");
			writer = new BufferedWriter(write);
			in = new BufferedReader(new InputStreamReader(new FileInputStream(
					f), "GB2312"));
			String line = null;
			while ((line = in.readLine()) != null) {
				if (line.split("\t").length < 6) {
					System.out.println(line);
				}
				writer.write(line + "\n");
			}
			writer.flush();
			in.close();
			writer.close();
			System.out.println("f");
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	public static void main(String[] args) {
		String path = "data/SogouQ.mini";
		readFile(path);
	}
}
