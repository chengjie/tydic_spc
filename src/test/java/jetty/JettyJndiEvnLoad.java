package jetty;

import org.mortbay.naming.NamingUtil;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.naming.InitialContext;

public class JettyJndiEvnLoad {
	
	
	 /** * 
	  * 
	  * 绑定datasource到JNDI
	  * @author elvis
	  **/ 
	public static void bindDataSource() 

	{ 
		   DriverManagerDataSource dataSource = new DriverManagerDataSource();          
		   dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver"); 
		   dataSource.setUrl("jdbc:oracle:thin:@192.168.161.180:1521:ora10");
		   //dataSource.setUrl("jdbc:oracle:thin:@134.64.75.7:1521:wss33"); 
		   dataSource.setUsername("ass_xz");
		   dataSource.setPassword("ass_xz");
		   javax.naming.Context context; 

		   try { 
		          context = new InitialContext(); 
		          javax.naming.Context compCtx = (javax.naming.Context) context.lookup("java:comp");     
		          javax.naming.Context envCtx = compCtx.createSubcontext("env"); 
		          NamingUtil.bind(envCtx,"jdbc/app", dataSource); 
		        } 
		   catch (Exception e) 
		   { 
			   e.printStackTrace();
		   } 
	}
}
