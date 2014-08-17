package wss.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import wss.model.Userlog;
import wss.service.UserlogService;
import wss.service.impl.*;

public class UserlogTest {

	public static void main(String[] args) {
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("springcontext-config.xml");  
		  UserlogService userlogService =(UserlogServiceImpl)ctx.getBean("userlogService");
		  Userlog ul = new Userlog();
		  ul.setQueryword("hui");
		  userlogService.insert(ul);
	}

	}

