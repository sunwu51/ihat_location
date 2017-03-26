package ihat_location;
/*@description 根据一次收到的定位点的个数，分为3点、4点、5点解算
 * @author www
 * @time:2016
 * 
 * @param 
 * 
 * @throws Exception
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;




public class Calculate {
	//double distance_AB,double distance_AC,double distance_AD,double distance_AE,double distance_BC,double distance_BD
	public static double[] Calculating(Map<Integer,Double> Tampmap,int hatid,Hatnode[] hatnodes){
//		System.out.println("时间戳1"+Tampmap.get(1)+"时间戳2"+Tampmap.get(2)+"时间戳3"+Tampmap.get(4));
		double[] location={0,0,0};
		
		
		
        double A[]={0,0,0};
        double B[]={0,0,0};
        double C[]={0,0,0};
        double D[]={0,0,0};
        double E[]={0,0,0};
        double R[]={0,0,0};
        
      
        double distance_RA=0;
        double distance_RB=0;
        double distance_RC=0;
        double distance_RD=0;
        double distance_RE=0;
        
        Msanchor tmp ;
       Iterator<Msanchor> iterForArchor = null;
      
        for(iterForArchor=ihat_project.anchorInfoList.iterator();iterForArchor.hasNext();){
        	tmp=(Msanchor)iterForArchor.next();
        	if(tmp.getAnchorid().equals("1")){
        		A[0]=tmp.getAnchorx();
        		A[1]=tmp.getAnchory();
        		A[2]=tmp.getAnchory();
        		
//        		System.out.println(A[0]+"y轴："+A[1]);
//        		System.out.println(ihat_project.mapInfo.getLongitude());   
//        		System.out.println(ihat_project.mapInfo.getLength());  
        
        		A[0]=(A[0]+ihat_project.map.getLongitude()/2)/ihat_project.map.getLongitude()*ihat_project.map.getLength();
        		A[1]=(A[1]+ihat_project.map.getLatitude()/2)/ihat_project.map.getLatitude()*ihat_project.map.getWidth();
//        		System.out.println(A[0]+"，，A，："+A[1]);

        	}
        	if(tmp.getAnchorid().equals("2")){
        		B[0]=tmp.getAnchorx();
        		B[1]=tmp.getAnchory();
        		B[2]=tmp.getAnchory();
        		B[0]=(B[0]+ihat_project.map.getLongitude()/2)/ihat_project.map.getLongitude()*ihat_project.map.getLength();
        		B[1]=(B[1]+ihat_project.map.getLatitude()/2)/ihat_project.map.getLatitude()*ihat_project.map.getWidth();
//        		System.out.println(B[0]+"，B，，："+B[1]);
        	}
        	if(tmp.getAnchorid().equals("3")){
        		C[0]=tmp.getAnchorx();
        		C[1]=tmp.getAnchory();
        		C[2]=tmp.getAnchory();
        		C[0]=(C[0]+ihat_project.map.getLongitude()/2)/ihat_project.map.getLongitude()*ihat_project.map.getLength();
        		C[1]=(C[1]+ihat_project.map.getLatitude()/2)/ihat_project.map.getLatitude()*ihat_project.map.getWidth();
//        		System.out.println(C[0]+"，，C，："+C[1]);
        	}
        	if(tmp.getAnchorid().equals("4")){
        		D[0]=tmp.getAnchorx();
        		D[1]=tmp.getAnchory();
        		D[2]=tmp.getAnchory();
        		D[0]=(D[0]+ihat_project.map.getLongitude()/2)/ihat_project.map.getLongitude()*ihat_project.map.getLength();
        		D[1]=(D[1]+ihat_project.map.getLatitude()/2)/ihat_project.map.getLatitude()*ihat_project.map.getWidth();
        	}
        	
        	if(tmp.getAnchorid().equals("5")){
        		E[0]=tmp.getAnchorx();
        		E[1]=tmp.getAnchory();
        		E[2]=tmp.getAnchory();
        		E[0]=(E[0]+ihat_project.map.getLongitude()/2)/ihat_project.map.getLongitude()*ihat_project.map.getLength();
        		E[1]=(E[1]+ihat_project.map.getLatitude()/2)/ihat_project.map.getLatitude()*ihat_project.map.getWidth();
        	}
        	
        	if(tmp.getAnchorid().equals("6")){
        		R[0]=tmp.getAnchorx();
        		R[1]=tmp.getAnchory();
        		R[2]=tmp.getAnchory();
        		R[0]=(R[0]+ihat_project.map.getLongitude()/2)/ihat_project.map.getLongitude()*ihat_project.map.getLength();
        		R[1]=(R[1]+ihat_project.map.getLatitude()/2)/ihat_project.map.getLatitude()*ihat_project.map.getWidth();
        	}
        }
        
       //System.out.println(hatnodes.length);
       
//      distance_AE=(distance_AE+(distance_RA-distance_RE));
        if(hatnodes.length==5){
        	
        	System.out.println("hahaaaaaaaaaaaa");
        	double tdoa_AB=0,tdoa_AC=0,tdoa_AD=0,tdoa_AE=0;
    		
	   		tdoa_AB=(Tampmap.get(1)-Tampmap.get(2))/128/499.2/1000000*299702547;
	   		tdoa_AC=(Tampmap.get(1)-Tampmap.get(3))/128/499.2/1000000*299702547;
	   		tdoa_AD=(Tampmap.get(1)-Tampmap.get(4))/128/499.2/1000000*299702547;
	   		tdoa_AE=(Tampmap.get(1)-Tampmap.get(5))/128/499.2/1000000*299702547;
	   		
			distance_RA=java.lang.Math.sqrt((R[0]-A[0])*(R[0]-A[0])+(R[1]-A[1])*(R[1]-A[1]));
		    distance_RB=java.lang.Math.sqrt((R[0]-B[0])*(R[0]-B[0])+(R[1]-B[1])*(R[1]-B[1]));
		    distance_RC=java.lang.Math.sqrt((R[0]-C[0])*(R[0]-C[0])+(R[1]-C[1])*(R[1]-C[1]));
		    distance_RD=java.lang.Math.sqrt((R[0]-D[0])*(R[0]-D[0])+(R[1]-D[1])*(R[1]-D[1]));
		    distance_RE=java.lang.Math.sqrt((R[0]-C[0])*(R[0]-C[0])+(R[1]-C[1])*(R[1]-C[1]));
		       
		        
		    tdoa_AB=(tdoa_AB+(distance_RA-distance_RB));
		    tdoa_AC=(tdoa_AC+(distance_RA-distance_RC));
		    tdoa_AD=(tdoa_AD+(distance_RA-distance_RD));
		    tdoa_AE=(tdoa_AE+(distance_RA-distance_RE));
	       
		    location=Nuton3.getPostion(B[0], B[1],B[2] ,C[0], C[1],C[2],D[0], D[1],D[2],E[0], E[1],E[2],tdoa_AB,tdoa_AC,tdoa_AD,tdoa_AE);
		}
		if(hatnodes.length==4){
			
			//四个节点，选取其中三个进行计算，然后求其平均值
			double[] pos0={0,0},pos1={0,0},pos2={0,0},pos3={0,0};
			double	distance_AB=(Tampmap.get(1)-Tampmap.get(2))/128/499.2/1000000*299702547;
			double	distance_AC=(Tampmap.get(1)-Tampmap.get(3))/128/499.2/1000000*299702547;
			double  distance_AD=(Tampmap.get(1)-Tampmap.get(4))/128/499.2/1000000*299702547;
			double	distance_BC=(Tampmap.get(2)-Tampmap.get(3))/128/499.2/1000000*299702547;
			double	distance_BD=(Tampmap.get(2)-Tampmap.get(4))/128/499.2/1000000*299702547;

			
			distance_RA=java.lang.Math.sqrt((R[0]-A[0])*(R[0]-A[0])+(R[1]-A[1])*(R[1]-A[1]));
	        distance_RB=java.lang.Math.sqrt((R[0]-B[0])*(R[0]-B[0])+(R[1]-B[1])*(R[1]-B[1]));
	        distance_RC=java.lang.Math.sqrt((R[0]-C[0])*(R[0]-C[0])+(R[1]-C[1])*(R[1]-C[1]));
	        distance_RD=java.lang.Math.sqrt((R[0]-D[0])*(R[0]-D[0])+(R[1]-D[1])*(R[1]-D[1]));
	        
	        distance_AB = (distance_AB + (distance_RA - distance_RB));
			distance_AC = (distance_AC + (distance_RA - distance_RC));
			distance_AD = (distance_AD + (distance_RA - distance_RD));
			distance_BC = (distance_BC + (distance_RB - distance_RC));
			distance_BD = (distance_BD + (distance_RB - distance_RD));
			//pos0定位结果BCD
			pos0 = Nuton.getPos(B[0], B[1], C[0], C[1], D[0], D[1], distance_BC, distance_BD, 2, 1);
//			 System.out.println("坐标x:"+pos0[0]+"坐标y:"+pos0[1]);
			if (Double.isNaN(pos0[0]) || Double.isNaN(pos0[1])) {
				pos0 = Nuton.getPos(B[0], B[1], C[0], C[1], D[0], D[1], distance_BC, distance_BD, 2, 1);
			}
			if (Double.isNaN(pos0[0]) || Double.isNaN(pos0[1])) {
				pos0 = Nuton.getPos(B[0], B[1], C[0], C[1], D[0], D[1], distance_BC, distance_BD, 4, 1);
			}
			if (Double.isNaN(pos0[0]) || Double.isNaN(pos0[1])) {
				pos0 = Nuton.getPos(B[0], B[1], C[0], C[1], D[0], D[1], distance_BC, distance_BD, 4, 3);
			}
			if (Double.isNaN(pos0[0]) || Double.isNaN(pos0[1])) {
				pos0 = Nuton.getPos(B[0], B[1], C[0], C[1], D[0], D[1], distance_BC, distance_BD, 6, 3);
			}
			if (Double.isNaN(pos0[0]) || Double.isNaN(pos0[1])) {
				pos0 = Nuton.getPos(B[0], B[1], C[0], C[1], D[0], D[1], distance_BC, distance_BD, -2, 1);
			}
			if (Double.isNaN(pos0[0]) || Double.isNaN(pos0[1])) {
				pos0= Nuton.getPos(B[0], B[1], C[0], C[1], D[0], D[1], distance_BC, distance_BD, -2, -1);
			}
			if (Double.isNaN(pos0[0]) || Double.isNaN(pos0[1])) {
				pos0 = Nuton.getPos(B[0], B[1], C[0], C[1], D[0], D[1], distance_BC, distance_BD, 2, -1);
			}
			if (Double.isNaN(pos0[0]) || Double.isNaN(pos0[1])) {
				pos0[0] = 0;
				pos0[1] = 0;
			}
		//pos1定位结果A BC
			pos1 = Nuton.getPos(A[0], A[1], B[0], B[1], C[0], C[1], distance_AB, distance_AC, 2, 1);
//			 System.out.println("坐标x:"+pos1[0]+"坐标y:"+pos1[1]);
			if (Double.isNaN(pos1[0]) || Double.isNaN(pos1[1])) {
				pos1 = Nuton.getPos(A[0], A[1], B[0], B[1], C[0], C[1], distance_AB, distance_AC, 2, 1);
			}
			if (Double.isNaN(pos1[0]) || Double.isNaN(pos1[1])) {
				pos1 = Nuton.getPos(A[0], A[1], B[0], B[1], C[0], C[1], distance_AB, distance_AC, 4, 1);
			}
			if (Double.isNaN(pos1[0]) || Double.isNaN(pos1[1])) {
				pos1 = Nuton.getPos(A[0], A[1], B[0], B[1], C[0], C[1], distance_AB, distance_AC, 4, 3);
			}
			if (Double.isNaN(pos1[0]) || Double.isNaN(pos1[1])) {
				pos1 = Nuton.getPos(A[0], A[1], B[0], B[1], C[0], C[1], distance_AB, distance_AC, 6, 3);
			}
			if (Double.isNaN(pos1[0]) || Double.isNaN(pos1[1])) {
				pos1 = Nuton.getPos(A[0], A[1], B[0], B[1], C[0], C[1], distance_AB, distance_AC, -2, 1);
			}
			if (Double.isNaN(pos1[0]) || Double.isNaN(pos1[1])) {
				pos1= Nuton.getPos(A[0], A[1], B[0], B[1], C[0], C[1], distance_AB, distance_AC, -2, -1);
			}
			if (Double.isNaN(pos1[0]) || Double.isNaN(pos1[1])) {
				pos1 = Nuton.getPos(A[0], A[1], B[0], B[1], C[0], C[1], distance_AB, distance_AC, 2, -1);
			}
			if (Double.isNaN(pos1[0]) || Double.isNaN(pos1[1])) {
				pos1[0] = 0;
				pos1[1] = 0;
			}
            
	       //pos2定位结果ABD
			
			pos2 = Nuton.getPos(A[0], A[1], B[0], B[1], D[0], D[1], distance_AB, distance_AD, 2, 1);
//			 System.out.println("坐标x:"+pos2[0]+"坐标y:"+pos2[1]);
			if (Double.isNaN(pos2[0]) || Double.isNaN(pos2[1])) {
				pos2 = Nuton.getPos(A[0], A[1], B[0], B[1], D[0], D[1], distance_AB, distance_AD, 2, 1);
			}
			if (Double.isNaN(pos2[0]) || Double.isNaN(pos2[1])) {
				pos2 = Nuton.getPos(A[0], A[1], B[0], B[1], D[0], D[1], distance_AB, distance_AD, 4, 1);
			}
			if (Double.isNaN(pos2[0]) || Double.isNaN(pos2[1])) {
				pos2 = Nuton.getPos(A[0], A[1], B[0], B[1], D[0], D[1], distance_AB, distance_AD, 4, 3);
			}
			if (Double.isNaN(pos2[0]) || Double.isNaN(pos2[1])) {
				pos2 = Nuton.getPos(A[0], A[1], B[0], B[1], D[0], D[1], distance_AB, distance_AD, 6, 3);
			}
			if (Double.isNaN(pos2[0]) || Double.isNaN(pos2[1])) {
				pos2 = Nuton.getPos(A[0], A[1], B[0], B[1], D[0], D[1], distance_AB, distance_AD, -2, 1);
			}
			if (Double.isNaN(pos2[0]) || Double.isNaN(pos2[1])) {
				pos2= Nuton.getPos(A[0], A[1], B[0], B[1], D[0], D[1], distance_AB, distance_AD, -2, -1);
			}
			if (Double.isNaN(pos2[0]) || Double.isNaN(pos2[1])) {
				pos2 = Nuton.getPos(A[0], A[1], B[0], B[1], D[0], D[1], distance_AB, distance_AD, 2, -1);
			}
			if (Double.isNaN(pos2[0]) || Double.isNaN(pos2[1])) {
				pos2[0] = 0;
				pos2[1] = 0;
			}
			//pos3 A C D
			pos3 = Nuton.getPos(A[0], A[1], C[0], C[1], D[0], D[1], distance_AC, distance_AD, 2, 1);
//			 System.out.println("坐标x:"+pos3[0]+"坐标y:"+pos3[1]);
			if (Double.isNaN(pos3[0]) || Double.isNaN(pos3[1])) {
				pos3 = Nuton.getPos(A[0], A[1], C[0], C[1], D[0], D[1], distance_AC, distance_AD, 2, 1);
			}
			if (Double.isNaN(pos3[0]) || Double.isNaN(pos3[1])) {
				pos3 = Nuton.getPos(A[0], A[1], C[0], C[1], D[0], D[1], distance_AC, distance_AD, 4, 1);
			}
			if (Double.isNaN(pos3[0]) || Double.isNaN(pos3[1])) {
				pos3 = Nuton.getPos(A[0], A[1], C[0], C[1], D[0], D[1], distance_AC, distance_AD, 4, 3);
			}
			if (Double.isNaN(pos3[0]) || Double.isNaN(pos3[1])) {
				pos3 = Nuton.getPos(A[0], A[1], C[0], C[1], D[0], D[1], distance_AC, distance_AD, 6, 3);
			}
			if (Double.isNaN(pos3[0]) || Double.isNaN(pos3[1])) {
				pos3 = Nuton.getPos(A[0], A[1], C[0], C[1], D[0], D[1], distance_AC, distance_AD, -2, 1);
			}
			if (Double.isNaN(pos3[0]) || Double.isNaN(pos3[1])) {
				pos3= Nuton.getPos(A[0], A[1], C[0], C[1], D[0], D[1], distance_AC, distance_AD, -2, -1);
			}
			if (Double.isNaN(pos3[0]) || Double.isNaN(pos3[1])) {
				pos3 = Nuton.getPos(A[0], A[1], C[0], C[1], D[0], D[1], distance_AC, distance_AD, 2, -1);
			}
			if (Double.isNaN(pos3[0]) || Double.isNaN(pos3[1])) {
				pos3[0] = 0;
				pos3[1] = 0;
			}
			
			double[][] poss=new double[][]{pos0,pos1,pos2,pos3};
			ArrayList<double[]> list=new ArrayList<>();
			for (double[] ds : poss) {
				if(ds!=null)
					list.add(ds);
			}	
//			System.out.println(list);
			 location=AvgDeal.dealRes(list);
			 
//			System.out.println("坐标为：x"+location[0]+"y轴："+location[1]);
		}
		if (hatnodes.length == 3) {
			//double distance_AB = 0, distance_AC = 0, 
		//	double distance_AD = 0, distance_BC = 0, distance_BD = 0;
			//时间戳key值是根据锚点的id确定，如果收不到某锚点，可以根据时间戳直接看见1.2。3.4
//			 System.out.print(Tampmap);
//		System.out.println("时间戳"+(Tampmap.get(1)-Tampmap.get(2))+"时间戳"+(Tampmap.get(1)-Tampmap.get(3)));
//	    System.out.println((Tampmap.get(1)-Tampmap.get(2))/128/499.2*299702547/1000000);
			if(Tampmap.get(4)==null){
		double	distance_AB=(Tampmap.get(1)-Tampmap.get(2))/128/499.2/1000000*299702547;
		double	distance_AC=(Tampmap.get(1)-Tampmap.get(3))/128/499.2/1000000*299702547;

//			System.out.println("ab"+distance_AB);
//			System.out.println(distance_AC);
			distance_RA = java.lang.Math.sqrt(
					(R[0] - A[0]) * (R[0] - A[0]) + (R[1] - A[1]) * (R[1] - A[1]) );
			distance_RB = java.lang.Math.sqrt(
					(R[0] - B[0]) * (R[0] - B[0]) + (R[1] - B[1]) * (R[1] - B[1]));
			distance_RC = java.lang.Math.sqrt(
					(R[0] - C[0]) * (R[0] - C[0]) + (R[1] - C[1]) * (R[1] - C[1]) );

			//distance_RD = java.lang.Math.sqrt(
			//		(R[0] - D[0]) * (R[0] - D[0]) + (R[1] - D[1]) * (R[1] - D[1]) );
			

			distance_AB = (distance_AB + (distance_RA - distance_RB));
			distance_AC = (distance_AC + (distance_RA - distance_RC));
			//distance_AD = (distance_AD + (distance_RA - distance_RD));
//			System.out.println("RA的距离:"+distance_RA+"RB的距离:"+distance_RB+"RC的距离:"+distance_RC);
//			System.out.println("修正后的AB距离："+distance_AB+"****修正后的AC距离："+distance_AC);
			
			//			System.out.println("ab:" + distance_AB + "ac:  " + distance_AC);
			// 存储计算结果的
//			double[] location = { 0, 0 };
			

			location = Nuton.getPos(A[0], A[1], B[0], B[1], C[0], C[1], distance_AB, distance_AC, 2, 1);
//			 System.out.println("坐标x:"+location[0]+"坐标y:"+location[1]);
			if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
				location = Nuton.getPos(A[0], A[1], B[0], B[1], C[0], C[1], distance_AB, distance_AC, 2, 1);
			}
			if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
				location = Nuton.getPos(A[0], A[1], B[0], B[1], C[0], C[1], distance_AB, distance_AC, 4, 1);
			}
			if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
				location = Nuton.getPos(A[0], A[1], B[0], B[1], C[0], C[1], distance_AB, distance_AC, 4, 3);
			}
			if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
				location = Nuton.getPos(A[0], A[1], B[0], B[1], C[0], C[1], distance_AB, distance_AC, 6, 3);
			}
			if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
				location = Nuton.getPos(A[0], A[1], B[0], B[1], C[0], C[1], distance_AB, distance_AC, -2, 1);
			}
			if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
				location= Nuton.getPos(A[0], A[1], B[0], B[1], C[0], C[1], distance_AB, distance_AC, -2, -1);
			}
			if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
				location = Nuton.getPos(A[0], A[1], B[0], B[1], C[0], C[1], distance_AB, distance_AC, 2, -1);
			}
			if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
				location[0] = 0;
				location[1] = 0;
			}
            
			}
			else if(Tampmap.get(3)==null){
				
				double	distance_AB=(Tampmap.get(1)-Tampmap.get(2))/128/499.2/1000000*299702547;
				double	distance_AD=(Tampmap.get(1)-Tampmap.get(4))/128/499.2/1000000*299702547;

//					System.out.println("ab"+distance_AB);
//					System.out.println(distance_AC);
				distance_RA = java.lang.Math.sqrt(
							(R[0] - A[0]) * (R[0] - A[0]) + (R[1] - A[1]) * (R[1] - A[1]) );
				distance_RB = java.lang.Math.sqrt(
							(R[0] - B[0]) * (R[0] - B[0]) + (R[1] - B[1]) * (R[1] - B[1]));
				distance_RD = java.lang.Math.sqrt(
							(R[0] - D[0]) * (R[0] - D[0]) + (R[1] - D[1]) * (R[1] - D[1]) );

					//distance_RD = java.lang.Math.sqrt(
					//		(R[0] - D[0]) * (R[0] - D[0]) + (R[1] - D[1]) * (R[1] - D[1]) );
					

				distance_AB = (distance_AB + (distance_RA - distance_RB));
				distance_AD = (distance_AD + (distance_RA - distance_RD));
					//distance_AD = (distance_AD + (distance_RA - distance_RD));
//					System.out.println("RA的距离:"+distance_RA+"RB的距离:"+distance_RB+"RC的距离:"+distance_RC);
//					System.out.println("修正后的AB距离："+distance_AB+"****修正后的AC距离："+distance_AC);
					
					//			System.out.println("ab:" + distance_AB + "ac:  " + distance_AC);
					// 存储计算结果的
//					double[] location = { 0, 0 };
					

				location = Nuton.getPos(A[0], A[1], B[0], B[1], D[0], D[1], distance_AB, distance_AD, 2, 1);
//					 System.out.println("坐标x:"+location[0]+"坐标y:"+location[1]);
				if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
						location = Nuton.getPos(A[0], A[1], B[0], B[1], D[0], D[1], distance_AB, distance_AD, 2, 1);
				}
				if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
						location = Nuton.getPos(A[0], A[1], B[0], B[1], D[0], D[1], distance_AB, distance_AD, 4, 1);
				}
				if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
						location = Nuton.getPos(A[0], A[1], B[0], B[1], D[0], D[1], distance_AB, distance_AD, 4, 3);
				}
				if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
						location = Nuton.getPos(A[0], A[1], B[0], B[1], D[0], D[1], distance_AB, distance_AD, 6, 3);
				}
				if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
						location = Nuton.getPos(A[0], A[1], B[0], B[1], D[0], D[1], distance_AB, distance_AD, -2, 1);
				}
				if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
						location= Nuton.getPos(A[0], A[1], B[0], B[1], D[0], D[1], distance_AB, distance_AD, -2, -1);
				}
				if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
						location = Nuton.getPos(A[0], A[1], B[0], B[1], D[0], D[1], distance_AB, distance_AD, 2, -1);
				}
				if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
						location[0] = 0;
						location[1] = 0;
				}
		            
			}
			else if(Tampmap.get(2)==null){
				
				double	distance_AC=(Tampmap.get(1)-Tampmap.get(3))/128/499.2/1000000*299702547;
				double	distance_AD=(Tampmap.get(1)-Tampmap.get(4))/128/499.2/1000000*299702547;

//					System.out.println("ab"+distance_AB);
//					System.out.println(distance_AC);
				distance_RA = java.lang.Math.sqrt(
							(R[0] - A[0]) * (R[0] - A[0]) + (R[1] - A[1]) * (R[1] - A[1]) );
				distance_RC = java.lang.Math.sqrt(
							(R[0] - C[0]) * (R[0] - C[0]) + (R[1] - C[1]) * (R[1] - C[1]));
				distance_RD = java.lang.Math.sqrt(
							(R[0] - D[0]) * (R[0] - D[0]) + (R[1] - D[1]) * (R[1] - D[1]) );

					//distance_RD = java.lang.Math.sqrt(
					//		(R[0] - D[0]) * (R[0] - D[0]) + (R[1] - D[1]) * (R[1] - D[1]) );
					

				distance_AC = (distance_AC + (distance_RA - distance_RC));
				
				distance_AD = (distance_AD + (distance_RA - distance_RD));

				location = Nuton.getPos(A[0], A[1], C[0], C[1], D[0], D[1], distance_AC, distance_AD, 2, 1);
//					 System.out.println("坐标x:"+location[0]+"坐标y:"+location[1]);
				if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
						location = Nuton.getPos(A[0], A[1], C[0], C[1], D[0], D[1], distance_AC, distance_AD, 2, 1);
				}
				if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
						location = Nuton.getPos(A[0], A[1], C[0], C[1], D[0], D[1], distance_AC, distance_AD, 4, 1);
				}
				if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
						location = Nuton.getPos(A[0], A[1], C[0], C[1], D[0], D[1], distance_AC, distance_AD, 4, 3);
				}
				if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
						location = Nuton.getPos(A[0], A[1], C[0], C[1], D[0], D[1], distance_AC, distance_AD, 6, 3);
				}
				if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
						location = Nuton.getPos(A[0], A[1], C[0], C[1], D[0], D[1], distance_AC, distance_AD, -2, 1);
				}
				if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
						location= Nuton.getPos(A[0], A[1], C[0], C[1], D[0], D[1], distance_AC, distance_AD, -2, -1);
				}
				if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
						location = Nuton.getPos(A[0], A[1], C[0], C[1], D[0], D[1], distance_AC, distance_AD, 2, -1);
				}
				if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
						location[0] = 0;
						location[1] = 0;
				}
			}
			else if(Tampmap.get(1)==null){
				
				double	distance_BC=(Tampmap.get(2)-Tampmap.get(3))/128/499.2/1000000*299702547;
				double	distance_BD=(Tampmap.get(2)-Tampmap.get(4))/128/499.2/1000000*299702547;

//					System.out.println("ab"+distance_AB);
//					System.out.println(distance_AC);
				distance_RB = java.lang.Math.sqrt(
							(R[0] - B[0]) * (R[0] - B[0]) + (R[1] - B[1]) * (R[1] - B[1]) );
					
				distance_RC = java.lang.Math.sqrt(
							(R[0] - C[0]) * (R[0] - C[0]) + (R[1] - C[1]) * (R[1] - C[1]) );
				distance_RD = java.lang.Math.sqrt(
							(R[0] - D[0]) * (R[0] - D[0]) + (R[1] - D[1]) * (R[1] - D[1]));

					//distance_RD = java.lang.Math.sqrt(
					//		(R[0] - D[0]) * (R[0] - D[0]) + (R[1] - D[1]) * (R[1] - D[1]) );
					

				distance_BC = (distance_BC + (distance_RB - distance_RC));
				distance_BD = (distance_BD + (distance_RB - distance_RD));
					

				location = Nuton.getPos(B[0], B[1], C[0], C[1], D[0], D[1], distance_BC, distance_BD, 2, 1);
//					 System.out.println("坐标x:"+location[0]+"坐标y:"+location[1]);
				if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
						location = Nuton.getPos(B[0], B[1], C[0], C[1], D[0], D[1], distance_BC, distance_BD, 2, 1);
				}
				if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
						location = Nuton.getPos(B[0], B[1], C[0], C[1], D[0], D[1], distance_BC, distance_BD, 4, 1);
				}
				if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
						location = Nuton.getPos(B[0], B[1], C[0], C[1], D[0], D[1], distance_BC, distance_BD, 4, 3);
				}
				if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
						location = Nuton.getPos(B[0], B[1], C[0], C[1], D[0], D[1], distance_BC, distance_BD, 6, 3);
				}
				if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
						location = Nuton.getPos(B[0], B[1], C[0], C[1], D[0], D[1], distance_BC, distance_BD, -2, 1);
				}
				if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
						location= Nuton.getPos(B[0], B[1], C[0], C[1], D[0], D[1], distance_BC, distance_BD, -2, -1);
				}
				if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
						location = Nuton.getPos(B[0], B[1], C[0], C[1], D[0], D[1], distance_BC, distance_BD, 2, -1);
				}
				if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
						location[0] = 0;
						location[1] = 0;
				}
			}
		}//二维定位结束
        
	
	
	 {
     	double a=0;
     	try{
     	
		a=Math.sqrt((ihat_project.preres[hatid].x-location[0])*(ihat_project.preres[hatid].x-location[0])
             	+(ihat_project.preres[hatid].y-location[1])*(ihat_project.preres[hatid].y-location[1]));
     	
	        }
	        catch(Exception e){ }
     	if(location[0]==0){
     		try{location[0]=ihat_project.preres[hatid].x;location[1]=ihat_project.preres[hatid].y;}catch (Exception e) {}
     	}	
     		
     	if(a<0.1)
     		ihat_project.preres[hatid]=new position(location[0],location[1]);
    	else if(a>=0.1&&a<0.4){
    		location[0]=ihat_project.preres[hatid].x;
    		location[1]=ihat_project.preres[hatid].y;
    		ihat_project.preres[hatid]=new position(location[0],location[1]);}
    	else if(a>=0.4&&a<1){
    		location[0]=0.7*location[0]+0.3*ihat_project.preres[hatid].x;
    		location[1]=0.7*location[1]+0.3*ihat_project.preres[hatid].y;
    		ihat_project.preres[hatid]=new position(location[0],location[1]);}
    	else{
    		location[0]=0.3*location[0]+0.7*ihat_project.preres[hatid].x;
    		location[1]=0.3*location[1]+0.7*ihat_project.preres[hatid].y;
    		ihat_project.preres[hatid]=new position(location[0],location[1]);
    	}
//     	System.out.println("x:"+location[0]+"y:"+location[1]);
    	location[0]=location[0]*ihat_project.map.getLongitude()/ihat_project.map.getLength()-ihat_project.map.getLongitude()/2;
    	location[1] =location[1]*ihat_project.map.getLatitude()/ihat_project.map.getWidth()-ihat_project.map.getLatitude()/2;
    	if(location[0]<-180||location[0]>180||location[1]<-85||location[1]>85){
    		location[0]=ihat_project.preres[hatid].x*ihat_project.map.getLongitude()/ihat_project.map.getLength()-ihat_project.map.getLongitude()/2;
        	location[1] =ihat_project.preres[hatid].y*ihat_project.map.getLatitude()/ihat_project.map.getWidth()-ihat_project.map.getLatitude()/2;
    	}
	 }
			System.out.println("x:"+location[0]+"y:"+location[1]);
    	return  location; 
	}
	}


