package ihat_location;

public class Nuton {
	public static double[] getPos(double x1,double y1,double x2,double y2,double x3,double y3,double d1,double d2,double xk,double yk){
	//double xk = 1, yk = 10, ;//这是初值点和迭代点
    //double x1 = 5, x2 = -5, x3 = 5, y1 = 0, y2 = 0, y3 = 10;//这是三个锚节点的坐标
	// double d1 = -3, d2 = 5;//这是距离差

		double result[]={0,0};
		double xkk=0,ykk=0;
		double fx, fy, gx, gy, f, g;
		double a=0,b=0;
		int count=0;
		double dp=1;
		do{
			fx = (xk - x1) / Math.sqrt((xk - x1) * (xk - x1) + (yk - y1) * (yk - y1))
					- (xk - x2) / Math.sqrt((xk - x2) * (xk - x2) + (yk - y2) * (yk - y2));
			fy = (yk - y1) / Math.sqrt((xk - x1) * (xk - x1) + (yk - y1) * (yk - y1))
					- (yk - y2) / Math.sqrt((xk - x2) * (xk - x2) + (yk - y2) * (yk - y2));
			gx = (xk - x1) / Math.sqrt((xk - x1) * (xk - x1) + (yk - y1) * (yk - y1))
					- (xk - x3) / Math.sqrt((xk - x3) * (xk - x3) + (yk - y3) * (yk - y3));
			gy = (yk - y1) / Math.sqrt((xk - x1) * (xk - x1) + (yk - y1) * (yk - y1))
					- (yk - y3) / Math.sqrt((xk - x3) * (xk - x3) + (yk - y3) * (yk - y3));
			f = Math.sqrt((xk - x1) * (xk - x1) + (yk - y1) * (yk - y1)) 
					- Math.sqrt((xk - x2) * (xk - x2) + (yk - y2) * (yk - y2)) - d1;
			g = Math.sqrt((xk - x1) * (xk - x1) + (yk - y1) * (yk - y1)) 
					- Math.sqrt((xk - x3) * (xk - x3) + (yk - y3) * (yk - y3)) - d2;
        
			xkk = xk + dp*(f * gy - g * fy) / (gx * fy - fx * gy);
			ykk = yk + dp*(g * fx - f * gx) / (gx * fy - fx * gy);
       
			count++;
			dp=0.5*dp;
			a=Math.abs(xkk-xk);   
			b=Math.abs(ykk-yk);  
      
			xk = xkk; yk = ykk;
//		System.out.println(xkk+";"+ykk);
			}while(a>=0.001&&b>=0.001&&count<=100);
		
			result[0]=xkk;
			result[1]=ykk;
//	System.out.println(">>>>    坐标为 : "+"("+xkk+","+ykk+")" );
			return result;
		}
	
}
