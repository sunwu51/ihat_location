package ihat_location;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


/*
 * 从数据库更新锚节点列表到内存中
 * 
 * 
 */
public class DataInit {
	public static Msmap getTheMapInfo(DataSource D1){
		
		
		Msmap map =new Msmap();
		java.sql.PreparedStatement stmt=null;
		Connection conn=null;
		ResultSet rs=null;
		
		try {
			//---------------------------------------------连接数据库------------------------------------------------			
					conn = D1.getConnection();
					if(!conn.isClosed()){
						
					}
					Statement statement = conn.createStatement();
					String sql = "select * from msmap";
					try {
						stmt = conn.prepareStatement(sql);
						rs = stmt.executeQuery(sql);
						while(rs.next()){
							//rs的下标是从1开始的
							map=new Msmap(rs.getDouble(9),rs.getDouble(8),rs.getDouble(10),rs.getDouble(11));
//							System.out.println(">>>>    当前的地图信息为："+(map.getLatitude())+",,"+(map.getLongitude())+",,"+(map.getLength())+",,"+(map.getWidth()));
						}
						
					}catch(Exception e) {   
						e.printStackTrace();   
					}
					
			}
		catch(Exception e1) { 
			e1.printStackTrace();
			}
		finally{
			
			try {
					stmt.close();
					rs.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}	
		return map;
	}
	
	
	

	public static void anchorInit() {
		// TODO Auto-generated method stub
				/********连接数据库***********/
		String url="jdbc:mysql://localhost:3306/ihat"; 
		String user="root";
		String password="";
		
    	ComboPooledDataSource ds =  new ComboPooledDataSource();  
        try {        	
			ds.setDriverClass("com.mysql.jdbc.Driver");
			ds.setJdbcUrl(url);
			ds.setUser(user);
			ds.setPassword(password);			
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //通过getTheAnchor获取锚点数据表
		ihat_project.anchorInfoList=getTheAnchor(ds);
		ihat_project.map=getTheMapInfo(ds);
	//	System.out.println(ihat_project.mapInfo.getLatitude());
	
	}
	public static List<Msanchor> getTheAnchor(DataSource ds)
	{
		//定义变量
				Msanchor anchor=null;
				List<Msanchor> anchorlist=new ArrayList<Msanchor>();
				java.sql.PreparedStatement stmt=null;
				Connection conn=null;
				ResultSet resultset=null;
		try {
				conn = ds.getConnection();
				if(!conn.isClosed())
				{
					System.out.println(">>>>    Succeeded connecting to the Database!");
				}

				String sql = "select * from msanchor";
		try {
				stmt = conn.prepareStatement(sql);
				resultset = stmt.executeQuery(sql);
				while(resultset.next())
					{
						//resultset的下标是从1开始的
						anchor=new Msanchor(resultset.getString(1),resultset.getString(2),resultset.getString(3),resultset.getDouble(4),resultset.getDouble(5),resultset.getDouble(6));
//						System.out.println(">>>>    当前锚点的坐标为 ："+A.toString());
						
						/**********获取一个锚点，加入到数据表中********************/
						anchorlist.add(anchor);
					}
					
				}catch(Exception e) {   
					e.printStackTrace();   
				}	
		}
		catch(Exception e1) {  
			e1.printStackTrace();  
			}
		finally{
			try {
					stmt.close();
					resultset.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}	
		return anchorlist;
	}

}
