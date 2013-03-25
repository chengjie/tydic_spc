package jetty;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

import java.io.File;


public class RunJetty {
	public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        System.out.println("正在启动工程...");        //绑定dataSource到JNDI;
		JettyJndiEvnLoad.bindDataSource();
				
		Server server = new Server(9090);
        File rootDir = new File(RunJetty.class.getResource("/").getPath()).getParentFile()
                .getParentFile();
        String webAppPath = new File(rootDir, "src/main/webapp").getPath();
        webAppPath = java.net.URLDecoder.decode(webAppPath, "utf-8");
        new WebAppContext(server, webAppPath, "/");
        server.start();
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime) + "ms");
        System.out.println("请打开浏览器访问http://localhost:9090");
    }
}