package myproject;

public class getTheLocation {
    public static void main(String[] args) {
//		double result[];
//		result=getPos(5,0,-5,0,5,10,-3,5,1,1);	
//		System.out.println(">>>>    x is "+result[0]);
//		System.out.println(">>>>    y is "+result[1]);
//
//		//Double.isNaN(v)
//		//File aFile;	
    }

    ///鍙傛暟x1,y1,x2,y2,x3,y3鍒嗗埆鏄疉BC涓変釜閿氱偣鐨勫潗鏍�(m)锛宒1,d2鍒嗗埆鏄疢A-MB锛孧A-MC鐨勮窛绂诲樊(m)(M浠ｈ〃绉诲姩鐨勮妭鐐�)锛寈k锛寉k鏄垵鍊肩偣
    public static double[] getPos(double x1, double y1, double x2, double y2, double x3, double y3, double d1, double d2, double xk, double yk) {
        //double xk = 1, yk = 10, ;//杩欐槸鍒濆�肩偣鍜岃凯浠ｇ偣
        //double x1 = 5, x2 = -5, x3 = 5, y1 = 0, y2 = 0, y3 = 10;//杩欐槸涓変釜閿氳妭鐐圭殑鍧愭爣
        // double d1 = -3, d2 = 5;//杩欐槸璺濈宸�

        double result[] = {0, 0};
        double xkk = 0, ykk = 0;
        double fx, fy, gx, gy, f, g;
        double a = 0, b = 0;
        int count = 0;
        do {
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

            xkk = xk + (f * gy - g * fy) / (gx * fy - fx * gy);
            ykk = yk + (g * fx - f * gx) / (gx * fy - fx * gy);

            count++;
            a = Math.abs(xkk - xk);
            b = Math.abs(ykk - yk);

            xk = xkk;
            yk = ykk;
//			System.out.println(xkk+";"+ykk);
        } while (count <= 50);
        result[0] = xkk;
        result[1] = ykk;
//		System.out.println(">>>>    : "+"("+xkk+","+ykk+")" );
        return result;
    }
}