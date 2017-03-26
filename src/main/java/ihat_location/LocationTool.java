package ihat_location;
/*@description 对同步包进行同步，找到时间标尺，对定位点进行定位解算
 * @author www
 * @time:2016
 * 
 * @param 
 * 
 * 
 * @throws Exception
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationTool {
	
	
	public static double[] getPosotion(int hatid, Hatnode[] hatArray) {
		
		Hatnode[] hatnodes=hatArray;
//		 System.out.println(hatnodes);
		//同步计算求取时间戳
		Map<Integer,Double> Tampmap=synCalulation(hatnodes);
		
//		System.out.println("时间戳1"+Tampmap.get(1)+"时间戳2"+Tampmap.get(2)+"时间戳3"+Tampmap.get(3));
	

		double[] Location=Calculate.Calculating(Tampmap,hatid,hatnodes);
		
		return Location;
	}

	private static Map<Integer, Double> synCalulation(Hatnode[] hatnodes) {
		
		// TODO Auto-generated method stub
		Map<Integer,Double> Tampmap=new HashMap<Integer,Double>();
		
		//将三个帽子到达的锚点的id取出来，寻找该锚点到达的同步包，寻找标尺
		List<Integer> ahchorid=new ArrayList<Integer>();
		for(int i=0;i<hatnodes.length;i++)
		{	
			ahchorid.add(hatnodes[i].getAnchorID());		
		}
//		System.out.println(ahchorid);
		
		int[] rulers=findSyn(ahchorid);
		
		for(int i=0;i<hatnodes.length;i++)
		{
			Synchronode one=ihat_project.synchcollect.get(hatnodes[i].getAnchorID()).stream().filter(y->y.getSEQ()==rulers[1]).findFirst().get();
			Synchronode two=ihat_project.synchcollect.get(hatnodes[i].getAnchorID()).stream().filter(y->y.getSEQ()==rulers[0]).findFirst().get();
	
			Tampmap.put(hatnodes[i].getAnchorID(),  mod(mod(hatnodes[i].getArrivTime()-one.getArrivTime())/(double)mod(two.getArrivTime()-one.getArrivTime())*mod(two.getCurSendTime()-one.getCurSendTime())+one.getCurSendTime()));
//			System.out.println("R1的发送时间："+one.getCurSendTime()+"R2的发送时间："+two.getCurSendTime()+"R1的到达时间："+one.getArrivTime()+"R2的到达时间:"+two.getArrivTime()+"P的到达时间:"+hatnodes[i].getArrivTime());

		}
//		System.out.println(Tampmap.get(1)+"**"+Tampmap.get(2)+"**"+Tampmap.get(3));
		return Tampmap;
	}
//找到同步序列号
	private static int[] findSyn(List<Integer> ahchorid) {
		// TODO Auto-generated method stub
		//创建一个hashmap用来存R包的序列号，三个锚点的序列号都拿出来，
		//如果相同序列号，则加1；如果等于三，说明同步包都到达了这三个锚点，可进行计算
		HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();
		ArrayList<Integer> seqs=new ArrayList<Integer>();
		ArrayList<Synchronode> sq=new ArrayList<Synchronode>();
		
		
				for (int i = 0; i < ahchorid.size(); i++) {	
					// 判断同步包是否丢失
					if (!ihat_project.synchcollect.containsKey(ahchorid.get(i))) {
						System.out.println("同步包不全，无法计算！");
						return null;
					}
					/* 找出AID为的flag=true的所有R包 的seq */
					ihat_project.synchcollect.get(ahchorid.get(i)).stream().filter((x)->(x.isFlag())).distinct().forEach((x)->{sq.add(x);});
				}
					//在所有flag=true的R包中，找到同时发往锚点的R包，存到seqs中。
					for(Synchronode synchronode:sq)
					{
						map.put(synchronode.getSEQ(), map.containsKey(synchronode.getSEQ())?(map.get(synchronode.getSEQ())+1):1);
						if (map.get(synchronode.getSEQ()) == ahchorid.size())
							seqs.add(synchronode.getSEQ());
					}
		
		Collections.sort(seqs);
		
		
		if(seqs.get(seqs.size()-1)-seqs.get(0)<100)
			;
		else
			{
			Comparator<Integer> comparator = new Comparator<Integer>()
				{
				public int compare(Integer s1, Integer s2)
					{
					if(s1<200)
						s1=s1+256;
					if(s2<200)
						s2=s2+256;
					return s1-s2;
					}
				};
				
			Collections.sort(seqs,comparator);
			}
		System.out.println(seqs.get(seqs.size()-1)+",,"+seqs.get(seqs.size()-2));
			return new int[]{seqs.get(seqs.size()-1),seqs.get(seqs.size()-2)};//这个地方有时会报s为空的异常
		
	}
	
	public static double mod(double a)
	{
	if(a>Math.pow(2, 40))return a-Math.pow(2, 40);
	return a>0?a:a+Math.pow(2, 40);
	}
public double smod(double a)
	{
	if(a>Math.pow(2, 39)||a<-Math.pow(2, 39))
		{
		System.out.println("溢出错误");return a>0?a-Math.pow(2,40):Math.pow(2,40)+a;
		}
	return a;
	}	

}
