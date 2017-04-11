package ihat_location;
/*
 * 帽子节点 属性定义
 * ***************帽子ID，锚点ID，序列号seq,此次到达时间
 * 每个同步包中带有上次的发送时间；定义一个flag,若此次包中填补了当前发送时间，flag=1
 */
public class Hatnode {
	
			private int HatID;//1-2		
			private int AnchorID;//3-4
			private int SEQ;//5
			private String CardId;//6-9
			private long ArrivTime;//10-14  CR
			private boolean flag;  
		
			public Hatnode(byte[] buf){
				this.HatID=buf[1]+buf[2]*256;
				this.AnchorID=buf[3]+buf[4]*256;
				
				if(this.AnchorID==2) this.AnchorID=1;
				if(this.AnchorID==4) this.AnchorID=2;
				if(this.AnchorID==6) this.AnchorID=3;
				if(this.AnchorID==8) this.AnchorID=4;
				if(this.AnchorID==10) this.AnchorID=5;
				this.SEQ=buf[5]>0?buf[5]:buf[5]+256;
				this.CardId=getCID(buf,6);
				this.ArrivTime=getStamp(buf,10);
				this.flag=false;
			}
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
			public String getCID(byte[] buf,int start){
				String cid="";
				for(int i=start;i<start+4;i++){
					int a=buf[i]>=0?buf[i]:256+buf[i];
					cid+=Integer.toHexString(a);
				}
				return cid;
			}
			
			public int getHatID() {
				return HatID;
			}
			public void setHatID(int hatID) {
				HatID = hatID;
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
			public String getCardId() {
				return CardId;
			}
			public void setCardId(String cardId) {
				CardId = cardId;
			}
			public long getArrivTime() {
				return ArrivTime;
			}
			public void setArrivTime(long arrivTime) {
				ArrivTime = arrivTime;
			}
			public boolean isFlag() {
				return flag;
			}
			public void setFlag(boolean flag) {
				this.flag = flag;
			}
}
