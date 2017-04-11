package ihat_location;

import java.util.ArrayList;

// * 3、数据处理除去离均值较远的数据，返回处理后的坐标
//* @param list
//* @return
//*
public class AvgDeal {
	
	public static double[] dealRes(ArrayList<double[]> list){
		ArrayList<double[]> res=new ArrayList<>();
		res=list;
		
		double[] g=new double[]{1.153,1.463,1.672};
		Double x=0.0,x2=0.0,y=0.0,y2=0.0,fc=0.0,maxv=0.0,avax=0.0,avay=0.0;
		int m=0;
		//先把数据放到数组中
		
		double[][] doubles = (double[][])list.toArray(new double[0][0]);
		//求出x和，y和，x二次方的和，y二次方的和放到变量x，y，x2，y2
		for (double[] double1 : doubles) {
			x+=double1[0];
			x2+=double1[0]*double1[0];
			y+=double1[1];
			y2+=double1[1]*double1[1];
		}
		//求出均值放到avax，avay
		avax=x/doubles.length;
		avay=y/doubles.length;
		//判断现在传进来的数据是否小于三组，如果小于三组无法利用格布，直接输出这个均值
		if(res.size()<3){
			return new double[]{avax,avay};
		}
		//每组数据距离均值的距离 的平方和就是方差，存到fc中(拼音fangcha)
		for (double[] double1 : doubles) {
			fc+=Math.sqrt((double1[0]-avax)*(double1[0]-avax)+(double1[1]-avay)*(double1[1]-avay));
		}
		//方差除以数据个数就是标准差bzc
		double bzc=fc/doubles.length;
		//这个循环用于求距离均值点最远的数据是第几组序号存到m，最大的差值存到maxv
		for (int i=0;i<doubles.length;i++) {
			if((doubles[i][0]-avax)*(doubles[i][0]-avax)+(doubles[i][1]-avay)*(doubles[i][1]-avay)>maxv){
				maxv=(doubles[i][0]-avax)*(doubles[i][0]-avax)+(doubles[i][1]-avay)*(doubles[i][1]-avay);
				m=i;
			}
		}
		//判断最大偏差/标准差 是否大于g表 值 如果是就删掉这个造成最大偏差的数据，把删掉后的结果重新带入本函数
		if(maxv/bzc>g[doubles.length-3]){
			res.remove(m);
			return dealRes(res);
		}
		//如果最大偏差没有超标，就可以返回这组的均值啦
		return new double[]{avax,avay};
	}

}
