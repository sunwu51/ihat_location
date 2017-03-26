package ihat_location;

public class Nuton3 {
	 public static double[] getPostion(double x1,double y1,double z1,double x2,double y2,double z2,double x3,double y3,double z3,double x4,double y4,double z4,double d1,double d2,double d3,double d4){
    	 //x1,y1,z1..是B CD E几个锚点的坐标，d1,d2,d3,d4是定位点到A点，与定位点到各辅站的距离的差值
    	 double A2,B2,C2,D2,A3,B3,C3,D3,A4,B4,C4,D4;
    	 double xk=0,yk=0,zk=0;
    	 //double x=0,y=0,z=0;
    	   A2=(x2/d2-x1/d1)*2;
    	   B2=(y2/d2-y1/d1)*2;
    	   C2=(z2/d2-z1/d1)*2;
    	   D2=d2-d1-(x2*x2+y2*y2+z2*z2)/d2+(x1*x1+y1*y1+z1*z1)/d1;
    	   
    	   A3=(x3/d3-x1/d1)*2;
    	   B3=(y3/d3-y1/d1)*2;
    	   C3=(z3/d3-z1/d1)*2;
    	   D3=d3-d1-(x3*x3+y3*y3+z3*z3)/d3+(x1*x1+y1*y1+z1*z1)/d1;
    	   
    	   A4=(x4/d4-x1/d1)*2;
    	   B4=(y4/d4-y1/d1)*2;
    	   C4=(z4/d4-z1/d1)*2;
    	   D4=d4-d1-(x4*x4+y4*y4+z4*z4)/d4+(x1*x1+y1*y1+z1*z1)/d1;
    	   
    	   if(A2*xk+B2*yk+C2*zk+D2==0&&A4*xk+B4*yk+C4*zk+D4==0&&A4*xk+B4*yk+C4*zk+D4==0){
    		   xk=-(B2*C3*D4-B2*C4*D3-B3*C2*D4+B3*C4*D2+B4*C2*D3-B4*C3*D2)/(A2*B3*C4-A2*B4*C3-A3*B2*C4+A3*B4*C2+A4*B2*C3-A4*B3*C2);
    		   yk=-(A2*C3*D4-A2*C4*D3-A3*C2*D4+A3*C4*D2+A4*C2*D3-A4*C3*D2)/(A2*B3*C4-A2*B4*C3-A3*B2*C4+A3*B4*C2+A4*B2*C3-A4*B3*C2);
    		   zk=-(A2*B3*D4-A2*B4*D3-A3*B2*D4+A3*B4*D2+A4*B2*D3-A4*B3*D2)/(A2*B3*C4-A2*B4*C3-A3*B2*C4+A3*B4*C2+A4*B2*C3-A4*B3*C2);
    	   }
    	   //如果等式不成立的话，怎么办，是舍弃还是继续带入求值？？
    	   double[] result={xk,yk,zk};
    	   return result;
     }


}
