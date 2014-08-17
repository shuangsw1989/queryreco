package wss.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import wss.model.Userlog;
import wss.service.UserlogService;
import wss.service.impl.UserlogServiceImpl;

public class UserlogSelectAllTest {

	public static void main(String[] args) {
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("springcontext-config.xml");  
		  UserlogService userlogService =(UserlogServiceImpl)ctx.getBean("userlogService");
//		  Userlog ul = new Userlog();
		  System.out.println("sss");
		  List<Userlog> list = new ArrayList<Userlog>();
		  
		  list = userlogService.selectAll();
		  System.out.println("aaa");
		  for(int i=0;i<5;i++){
			  System.out.println(list.get(i).getQueryword());
		  }
	}

}
