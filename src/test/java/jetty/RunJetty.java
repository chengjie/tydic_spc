package jetty;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

import java.io.File;


public class RunJetty {
	public static void main(String[] args) throws Exception {
		
		
		//绑定dataSource到JNDI;
		JettyJndiEvnLoad.bindDataSource();
				
		Server server = new Server(9090);
        File rootDir = new File(RunJetty.class.getResource("/").getPath()).getParentFile()
                .getParentFile();
        String webAppPath = new File(rootDir, "src/main/webapp").getPath();
        webAppPath = java.net.URLDecoder.decode(webAppPath, "utf-8");
        new WebAppContext(server, webAppPath, "/appweb");
        server.start(); 
	}
}