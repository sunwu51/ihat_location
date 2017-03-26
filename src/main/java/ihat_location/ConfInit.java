package ihat_location;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**************读取配置文件 ,初始化配置**
 * *******port
 * wsport wshost
 * dbhost,dbport,dbname,dbusername,dbpassword,
 * 
 * 
 * *************/
public class ConfInit {
				
	
		private static int port;
		private static int wsport;
		private static String wshost;
		private static int dbport;
		private static String dbhost;
		private static String dbname;
		private static String dbusername;
		private static String dbpassword;
	
	
	public static boolean SysInit() {
		// TODO Auto-generated method stub
		SAXReader reader=new SAXReader();
		Document document;
		try {
			document = reader.read(new File(System.getProperty("user.dir")+"/Config.xml"));
			Element node = document.getRootElement();
			port=Integer.parseInt(node.element("server").elementText("port"));
			wshost=node.element("wsserver").elementText("host");
			wsport=Integer.parseInt(node.element("wsserver").elementText("port"));
			dbhost=node.element("dbconnect").elementText("host");
			dbport=Integer.parseInt(node.element("dbconnect").elementText("port"));
			dbusername=node.element("dbconnect").elementText("username");
			dbname=node.element("dbconnect").elementText("dbname");
			dbpassword=node.element("dbconnect").elementText("password");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
		
		
	}
	public static int getPort() {
		return port;
	}




	public static void setPort(int port) {
		ConfInit.port = port;
	}




	public static int getWsport() {
		return wsport;
	}




	public static void setWsport(int wsport) {
		ConfInit.wsport = wsport;
	}




	public static String getWshost() {
		return wshost;
	}




	public static void setWshost(String wshost) {
		ConfInit.wshost = wshost;
	}




	public static int getDbport() {
		return dbport;
	}




	public static void setDbport(int dbport) {
		ConfInit.dbport = dbport;
	}




	public static String getDbhost() {
		return dbhost;
	}




	public static void setDbhost(String dbhost) {
		ConfInit.dbhost = dbhost;
	}




	public static String getDbname() {
		return dbname;
	}




	public static void setDbname(String dbname) {
		ConfInit.dbname = dbname;
	}




	public static String getDbusername() {
		return dbusername;
	}




	public static void setDbusername(String dbusername) {
		ConfInit.dbusername = dbusername;
	}




	public static String getDbpassword() {
		return dbpassword;
	}




	public static void setDbpassword(String dbpassword) {
		ConfInit.dbpassword = dbpassword;
	}


}
