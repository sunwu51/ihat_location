package ihat_location;
/*
 * 同步节点 属性定义
 * ***************同步点ID，锚点ID，序列号seq,上一次发送时间，此次发送时间，此次到达时间
 * 每个同步包中带有上次的发送时间；定义一个flag,若此次包中填补了当前发送时间，flag=1
 */
public class Synchronode {
	private int synchroID;//1-2
	private int AnchorID;//3-4
	private int SEQ;//5
	private long PreSendTime;//6-10
	private long ArrivTime;//11-15  cr
	private long CurSendTime;//cs
	private boolean flag;
	
	public Synchronode(byte[] buf){
		/********对变量的初始化*****/
		//对传进来字节处理
		this.synchroID=buf[1]+buf[2]*256;
		this.AnchorID=buf[3]+buf[4]*256;
		//硬件中锚点ID
		if(this.AnchorID==2) this.AnchorID=1;
		if(this.AnchorID==4) this.AnchorID=2;
		if(this.AnchorID==6) this.AnchorID=3;
		if(this.AnchorID==8) this.AnchorID=4;
		if(this.AnchorID==10) this.AnchorID=5;
		//序列号0~255一个轮回
		this.SEQ=buf[5]>0?buf[5]:256+buf[5];
		
		this.PreSendTime=getStamp(buf,6);
		this.ArrivTime=getStamp(buf,11);
		
		//初始，标志位为false
		this.flag=false;
	}
	//时间的处理
	private static long getStamp(byte[] bytes,int start){
		long s=0;
		//字节数计数
		int i=4;
		while(i>=0){
		long f=bytes[i+start]>0?bytes[i+start]:256+bytes[i+start];
		s=s*256+f;
		i--;
		}
		return s;
}
	/**********私有变量方法**************/
	public int getSynchroID() {
		return synchroID;
	}
	public void setSynchroID(int synchroID) {
		this.synchroID = synchroID;
	}
	public int getAnchorID() {
		return AnchorID;
	}
	public void setAnchorID(int anchorID) {
		AnchorID = anchorID;
	}
	public int getSEQ() {
		return SEQ;
	}
	public void setSEQ(int sEQ) {
		SEQ = sEQ;
	}
	public long getPreSendTime() {
		return PreSendTime;
	}
	public void setPreSendTime(long preSendTime) {
		PreSendTime = preSendTime;
	}
	public long getArrivTime() {
		return ArrivTime;
	}
	public void setArrivTime(long arrivTime) {
		ArrivTime = arrivTime;
	}
	public long getCurSendTime() {
		return CurSendTime;
	}
	public void setCurSendTime(long curSendTime) {
		CurSendTime = curSendTime;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
