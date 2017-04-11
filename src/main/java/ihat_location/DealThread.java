package ihat_location;
/*@description 处理网关过来的字节流
 * @author www
 * @time:2016
 * 
 * @param subbuf			每次存储16个字节，包中的一个数据是放在16个字节中表示的
 
 * @throws Exception
 */
import java.io.IOException;
import java.net.Socket;

public class DealThread implements Runnable {
	
	private Socket socket;
	public DealThread(Socket socket)
	{
		this.socket=socket;
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			//创建一个128的字节，将socket接收传入到buf中处理，每16个字节进行一个处理
			byte[] buf=new byte[128];
			try {
				int len=socket.getInputStream().read(buf);
				if(len<0)
					break;
				
					new Thread(new Runnable()
					{				
					@Override
					public void run() {
						
						int i=0;
						
						while((len/16)>i)
						{
							
							int j=16*(i++);
							byte[] subbuf=new byte[]
									{
										buf[j],buf[j+1],buf[j+2],buf[j+3],buf[j+4],buf[j+5],buf[6+j],buf[7+j],buf[8+j],buf[9+j],buf[10+j],buf[11+j],buf[12+j],buf[13+j],buf[14+j],buf[15+j]	
									};
							
							new Thread(	new DtialThread(subbuf)).start();
							
						
						}
						
					}
					
				}).start();
			} catch (IOException e) {
							e.printStackTrace();
			}
			
		}
	}
}
