package ihat_location;
/*@description 对每个包每次过来的数据具体处理，根据第一个字节判断同步包还是定位包
 * @author www
 * @time:2016
 * @param   	
 * 
 * @throws Exception
 */
import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.gson.Gson;

import ihat_location.LocationTool;


public class DtialThread implements Runnable{
	
	private byte[] buf;
	public DtialThread(byte[] subbuf) {
		// TODO Auto-generated constructor stub
		this.buf=subbuf;
	}

	@Override
	public void run() {
		/********buf第一个字节为包的类型，0或1为帽子或同步点**************/
		if(buf[0]==0)
			Hatdeal(buf);
		 if(buf[0]==1)
			Synchodeal(buf);
			
			
	}

	private void Synchodeal(byte[] buf) {
		
		Synchronode synchnode=new Synchronode(buf);		
//		System.out.println("seq:"+synchnode.getSEQ()+"aid:"+synchnode.getAnchorID());
		
		int synaid=synchnode.getAnchorID();
		int synseq=synchnode.getSEQ();
		
		if(!ihat_project.synchcollect.containsKey(synaid))
		{
			ihat_project.synchcollect.put(synaid, new CopyOnWriteArrayList<Synchronode>());
		}
	
				/*****同步锁*********/
/*同步的锁是aid，即相同aid的同步包会串行执行存储的过程，防止同时写一个对象，不同aid因为对应不同的list对象不需要同步*/
		synchronized(ihat_project.synchcollect.get(synaid))
		{
			/*****相同的aid的同步包累积计数************/
			if(!ihat_project.synchcollect.get(synaid).contains(synseq))
				ihat_project.synchcollect.get(synaid).add(synchnode);	
				if(ihat_project.synchcollect.get(synaid).size()>8){
					ihat_project.synchcollect.get(synaid).remove(0);
				}			
				
			//当list表中同步包数大于5个，则删除前面的，保留内存
			try{
				//设置前一个的发送时间,设置完当前时间后检查
			Synchronode preSynchronode=ihat_project.synchcollect.get(synaid).stream().filter((itr)->(itr.getSEQ()==(synchnode.getSEQ()-1<0?255:synchnode.getSEQ()-1))).findFirst().get();
				
				preSynchronode.setCurSendTime(synchnode.getPreSendTime());
				if(preSynchronode.getCurSendTime()==synchnode.getPreSendTime())
				preSynchronode.setFlag(true);			
				
			}catch (Exception e) {
				// TODO: handle exception				
			}
		}
		
	}
	
	private static void Hatdeal(byte[] buf) {
		
		Hatnode hatnode=new Hatnode(buf);
		int hatid=hatnode.getHatID();
		int hatseq=hatnode.getSEQ();
		//卡号很重要，用来显示页面中判断
		String cardID=hatnode.getCardId();
		//isfirst指的是同一seq，那个锚点先发过来的；用isfirst作为计算的初始标志，从它开始计算，知道所有锚点将该SEQ都发过来为止。
		boolean isfirst=false;
		
		
		if(!ihat_project.hatcollect.containsKey(hatid))
		{
			ihat_project.hatcollect.put(hatid, new HashMap<>());
			
		}
		if(!ihat_project.hatcollect.get(hatid).containsKey(hatseq))
			ihat_project.hatcollect.get(hatid).put(hatseq, new CopyOnWriteArrayList<Hatnode>());
		
		/****同一id同一seq的帽子包基本是同时来的，他们写同一个list，因而需要同步*******/
		synchronized(ihat_project.hatcollect.get(hatid).get(hatseq))
		{
			ihat_project.hatcollect.get(hatid).get(hatseq).add(hatnode);
			if(ihat_project.hatcollect.get(hatid).get(hatseq).size()==1)
            	isfirst=true;						
		}
		/*如果是该序列号第一个到达的，首先保证链表里不多于8个hatseq的数据(节省内存)，然后休眠两个同步间隔，进行同步运算*/
		if(isfirst)
		{
			if(ihat_project.hatcollect.get(hatid).size()>8)
			{

				ihat_project.hatcollect.get(hatid).remove(hatseq-5>0?hatseq-5:hatseq-5+256);
				ihat_project.hatcollect.get(hatid).remove(hatseq-6>0?hatseq-6:hatseq-6+256);
				ihat_project.hatcollect.get(hatid).remove(hatseq-7>0?hatseq-7:hatseq-7+256);

//				System.out.println("remove"+ihat_project.hatcollect.get(hatid).size());
			}
			if(ihat_project.hatcollect.get(hatid).get(hatseq).size()>8)
				ihat_project.hatcollect.get(hatid).get(hatseq).remove(0);
			try {
				Thread.sleep(200);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			
			//关于帽子到达所有锚点计算过程
				double[] result={0,0};
				
				
				CopyOnWriteArrayList<Hatnode> hats=ihat_project.hatcollect.get(hatid).get(hatseq);
				Hatnode[] hatArray=hats.stream().toArray(Hatnode[]::new);
//					System.out.println(hatArray.length);
					result=LocationTool.getPosotion(hatid,hatArray);

//				System.out.println("seq"+hatseq+"x:"+result[0]+";y:"+result[1]);
				if(result[0]!=0){
					LocationResult loc=new LocationResult(cardID,result[0],result[1]);
				
				try {
					ihat_project.outputStreams.write((new Gson().toJson(loc)+"*").getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				}
				
		}
	
			
	}

	
}
