package ihat_location;
/*@description 主函数程序入口
 * @author www
 * @time:2016
 * 
 * @param synchcollect  	同步点收集表
 * @param hatcollect   		定位点收集
 * @param preres       		储存前一个定位结果值
 * @param anchorInfoList	锚点
 * @param map				地图信息
 * 
 * @throws Exception
 */
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;



public class ihat_project {

	public static HashMap<Integer,CopyOnWriteArrayList<Synchronode>> synchcollect=new HashMap<Integer,CopyOnWriteArrayList<Synchronode>>();
	public static HashMap<Integer,HashMap<Integer,CopyOnWriteArrayList<Hatnode>>> hatcollect=new HashMap<Integer,HashMap<Integer,CopyOnWriteArrayList<Hatnode>>>() ;
	public static position[] preres=new position[10];
	public static OutputStream outputStreams;
	public static List<Msanchor> anchorInfoList=new ArrayList<Msanchor>();
	public static Msmap map=new Msmap();

	
	
	private static Socket socket;
	private static ServerSocket serversockets;

	public static void main(String[] args) throws IOException {
		
				/******************初始化变量***************/
		
		for (position p : preres)
		{
		p=new position(0, 0);
		}
		
		/**************读取配置文件 ,初始化配置**********************/
		if(!ConfInit.SysInit())	{
			System.out.println("配置文件加载出错");
			return;
		}
			
		
		//从数据库更新锚节点列表到内存中
		DataInit.anchorInit();
		
		
		
		
				/**********创建套接字，建立通信***************/
		try {

			socket = new Socket(ConfInit.getWshost(), ConfInit.getWsport());
			outputStreams = socket.getOutputStream();

		} catch (Exception e) {
			System.out.println("Node 未开启");
		}

		serversockets = new ServerSocket(ConfInit.getPort());
		while (true) {
			Socket s = serversockets.accept();
			new Thread(new DealThread(s)).start();

		}
		
	}
}
	


