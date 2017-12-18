
package myproject;

import java.util.Iterator;
import java.util.List;

public class LocationTool {
    public static List<Msanchor> anchorInfoList;
    public static MapInfo map;


    public static void init() {
        anchorInfoList=Init.msanchorList;
        map=Init.mapInfo;
    }

    public static double[] Calculating(double distance_AB, double distance_AC, double distance_AD, double distance_BC, double distance_BD, List anchorInfoList, MapInfo map, int hid) {
        //瀛樺偍璁＄畻缁撴灉鐨�
        double[] location1 = {0, 0};
        double[] location2 = {0, 0};
        double[] location3 = {0, 0};
        double[] location4 = {0, 0};

        //瀛樺偍鍚勪釜閿氱偣鐨勫潗鏍�
        double A[] = {0, 0};
        double B[] = {0, 0};
        double C[] = {0, 0};
        double D[] = {0, 0};
        double R[] = {0, 0};

        //璺濈淇
        double distance_RA = 0;
        double distance_RB = 0;
        double distance_RC = 0;
        double distance_RD = 0;


        Msanchor tmp = new Msanchor();
        Iterator<Msanchor> iterForArchor = null;
        for (iterForArchor = anchorInfoList.iterator(); iterForArchor.hasNext(); ) {
            tmp = (Msanchor) iterForArchor.next();
            if (tmp.getAnchorid().equals("1"))//瀵瑰簲鐨凱鍖呯粍褰撲腑鐨�3涓狝ID,瀵瑰簲鐨勬槸msanchor琛ㄦ牸褰撲腑鐨処D鑺傜偣
            {
                A[0] = tmp.getAnchorx();
                A[1] = tmp.getAnchory();

                //缁忕含搴﹁浆鎹㈡垚瀹為檯闀垮害
                A[0] = (A[0] + map.getLongitude() / 2) / map.getLongitude() * map.getLength();
                A[1] = (A[1] + map.getLatitude() / 2) / map.getLatitude() * map.getWidth();

            }
            if (tmp.getAnchorid().equals("2")) {
                B[0] = tmp.getAnchorx();
                B[1] = tmp.getAnchory();

                B[0] = (B[0] + map.getLongitude() / 2) / map.getLongitude() * map.getLength();
                B[1] = (B[1] + map.getLatitude() / 2) / map.getLatitude() * map.getWidth();
            }
            if (tmp.getAnchorid().equals("3")) {
                C[0] = tmp.getAnchorx();
                C[1] = tmp.getAnchory();

                C[0] = (C[0] + map.getLongitude() / 2) / map.getLongitude() * map.getLength();
                C[1] = (C[1] + map.getLatitude() / 2) / map.getLatitude() * map.getWidth();
            }
            if (tmp.getAnchorid().equals("4")) {
                D[0] = tmp.getAnchorx();
                D[1] = tmp.getAnchory();

                D[0] = (D[0] + map.getLongitude() / 2) / map.getLongitude() * map.getLength();
                D[1] = (D[1] + map.getLatitude() / 2) / map.getLatitude() * map.getWidth();
            }
//        	if(tmp.getAnchorid().equals("5"))
//        	{
//        		E[0]=tmp.getAnchorx();
//        		E[1]=tmp.getAnchory();
//        		
//        		E[0]=(D[0]+map.getLongitude()/2)/map.getLongitude()*map.getLength();
//        		E[1]=(D[1]+map.getLatitude()/2)/map.getLatitude()*map.getWidth();
//        	}


            if (tmp.getAnchorid().equals("5"))//鍚屾鑺傜偣R
            {
                R[0] = tmp.getAnchorx();
                R[1] = tmp.getAnchory();

                R[0] = (R[0] + map.getLongitude() / 2) / map.getLongitude() * map.getLength();
                R[1] = (R[1] + map.getLatitude() / 2) / map.getLatitude() * map.getWidth();
            }
        }


//      System.out.println("A"+A[0]+";"+A[1]);
//      System.out.println("B"+B[0]+";"+B[1]);        
//      System.out.println("C"+C[0]+";"+C[1]);        
//      System.out.println("D"+D[0]+";"+D[1]);               
//      System.out.println("R"+R[0]+";"+R[1]);


        //鑾峰彇鍚屾鑺傜偣鍒板悇涓妭鐐圭殑璺濈
        distance_RA = Math.sqrt((R[0] - A[0]) * (R[0] - A[0]) + (R[1] - A[1]) * (R[1] - A[1]));
        distance_RB = Math.sqrt((R[0] - B[0]) * (R[0] - B[0]) + (R[1] - B[1]) * (R[1] - B[1]));
        distance_RC = Math.sqrt((R[0] - C[0]) * (R[0] - C[0]) + (R[1] - C[1]) * (R[1] - C[1]));
        distance_RD = Math.sqrt((R[0] - D[0]) * (R[0] - D[0]) + (R[1] - D[1]) * (R[1] - D[1]));


//        System.out.println("RA"+distance_RA);
//        System.out.println("RB"+distance_RB);
//        System.out.println("RC"+distance_RC);
//        System.out.println("RD"+distance_RD);
//  
//        
//      System.out.println("淇鍓岮B"+distance_AB);
//      System.out.println("淇鍓岮C"+distance_AC);
//      System.out.println("淇鍓岮D"+distance_AD);
//      System.out.println("淇鍓岯C"+distance_BC);
//      System.out.println("淇鍓岯D"+distance_BD);
//        
//        distance_RA=java.lang.Math.sqrt(distance_RA*distance_RA-1);
//        distance_RB=java.lang.Math.sqrt(distance_RB*distance_RB-1);
//        distance_RC=java.lang.Math.sqrt(distance_RC*distance_RC-1);
//        distance_RD=java.lang.Math.sqrt(distance_RD*distance_RD-1);
        //鍧愭爣淇
        distance_AB = (distance_AB + (distance_RA - distance_RB));
        distance_AC = (distance_AC + (distance_RA - distance_RC));
        distance_AD = (distance_AD + (distance_RA - distance_RD));
        distance_BC = (distance_BC + (distance_RB - distance_RC));
        distance_BD = (distance_BD + (distance_RB - distance_RD));
//        
        //GetTheLocation G1=new GetTheLocation();

        //搴旇鑳借幏鍙栦笂涓�娆¤凯浠ｇ殑缁撴灉锛屾浛鎹�1鍜�1
//       try{
//    	   location1= GetTheLocation.getPos(A[0], A[1], B[0], B[1], C[0], C[1],distance_AB,distance_AC, Myproject.preres[hid].x, Myproject.preres[hid].y );
//       }
//       catch(Exception e){}
        location1 = Myproject.GetTheLocation.getPos(A[0], A[1], B[0], B[1], C[0], C[1], distance_AB, distance_AC, 2, 1);
        //濡傛灉鍑虹幇杩唬涓嶆敹鏁涚殑鐜拌薄锛屽垯鑰冭檻鏇存崲璞￠檺
        if (Double.isNaN(location1[0]) || Double.isNaN(location1[1])) {
            location1 = Myproject.GetTheLocation.getPos(A[0], A[1], B[0], B[1], C[0], C[1], distance_AB, distance_AC, 2, 1);
        }
        if (Double.isNaN(location1[0]) || Double.isNaN(location1[1])) {
            location1 = Myproject.GetTheLocation.getPos(A[0], A[1], B[0], B[1], C[0], C[1], distance_AB, distance_AC, 4, 1);
        }
        if (Double.isNaN(location1[0]) || Double.isNaN(location1[1])) {
            location1 = Myproject.GetTheLocation.getPos(A[0], A[1], B[0], B[1], C[0], C[1], distance_AB, distance_AC, 4, 3);
        }
        if (Double.isNaN(location1[0]) || Double.isNaN(location1[1])) {
            location1 = Myproject.GetTheLocation.getPos(A[0], A[1], B[0], B[1], C[0], C[1], distance_AB, distance_AC, 6, 3);
        }
        if (Double.isNaN(location1[0]) || Double.isNaN(location1[1])) {
            location1 = Myproject.GetTheLocation.getPos(A[0], A[1], B[0], B[1], C[0], C[1], distance_AB, distance_AC, -2, 1);
        }
        if (Double.isNaN(location1[0]) || Double.isNaN(location1[1])) {
            location1 = Myproject.GetTheLocation.getPos(A[0], A[1], B[0], B[1], C[0], C[1], distance_AB, distance_AC, -2, -1);
        }
        if (Double.isNaN(location1[0]) || Double.isNaN(location1[1])) {
            location1 = Myproject.GetTheLocation.getPos(A[0], A[1], B[0], B[1], C[0], C[1], distance_AB, distance_AC, 2, -1);
        }
        if (Double.isNaN(location1[0]) || Double.isNaN(location1[1])) {
            location1[0] = 0;
            location1[1] = 0;
        }

//        try{
//     	   location2= GetTheLocation.getPos(A[0], A[1], B[0], B[1], D[0], D[1],distance_AB,distance_AD, Myproject.preres[hid].x, Myproject.preres[hid].y );
//        }
//        catch(Exception e){}
        location2 = Myproject.GetTheLocation.getPos(A[0], A[1], B[0], B[1], D[0], D[1], distance_AB, distance_AD, 2, 1);
        //濡傛灉鍑虹幇杩唬涓嶆敹鏁涚殑鐜拌薄锛屽垯鑰冭檻鏇存崲璞￠檺
        if (Double.isNaN(location2[0]) || Double.isNaN(location2[1])) {
            location2 = Myproject.GetTheLocation.getPos(A[0], A[1], B[0], B[1], D[0], D[1], distance_AB, distance_AD, 2, 1);
        }
        if (Double.isNaN(location2[0]) || Double.isNaN(location2[1])) {
            location2 = Myproject.GetTheLocation.getPos(A[0], A[1], B[0], B[1], D[0], D[1], distance_AB, distance_AD, 4, 1);
        }
        if (Double.isNaN(location2[0]) || Double.isNaN(location2[1])) {
            location2 = Myproject.GetTheLocation.getPos(A[0], A[1], B[0], B[1], D[0], D[1], distance_AB, distance_AD, 4, 3);
        }
        if (Double.isNaN(location2[0]) || Double.isNaN(location2[1])) {
            location2 = Myproject.GetTheLocation.getPos(A[0], A[1], B[0], B[1], D[0], D[1], distance_AB, distance_AD, 6, 3);
        }
        if (Double.isNaN(location2[0]) || Double.isNaN(location2[1])) {
            location2 = Myproject.GetTheLocation.getPos(A[0], A[1], B[0], B[1], D[0], D[1], distance_AB, distance_AD, -2, 1);
        }
        if (Double.isNaN(location2[0]) || Double.isNaN(location2[1])) {
            location2 = Myproject.GetTheLocation.getPos(A[0], A[1], B[0], B[1], D[0], D[1], distance_AB, distance_AD, -2, -1);
        }
        if (Double.isNaN(location2[0]) || Double.isNaN(location2[1])) {
            location2 = Myproject.GetTheLocation.getPos(A[0], A[1], B[0], B[1], D[0], D[1], distance_AB, distance_AD, 2, -1);
        }
        if (Double.isNaN(location2[0]) || Double.isNaN(location2[1])) {
            location2[0] = 0;
            location2[1] = 0;
        }

//         try{
//      	   location3= GetTheLocation.getPos(A[0], A[1], C[0], C[1], D[0], D[1],distance_AC,distance_AD, Myproject.preres[hid].x, Myproject.preres[hid].y );
//         }
//         catch(Exception e){}
        location3 = Myproject.GetTheLocation.getPos(A[0], A[1], C[0], C[1], D[0], D[1], distance_AC, distance_AD, 2, 1);
        //濡傛灉鍑虹幇杩唬涓嶆敹鏁涚殑鐜拌薄锛屽垯鑰冭檻鏇存崲璞￠檺
        if (Double.isNaN(location3[0]) || Double.isNaN(location3[1])) {
            location3 = Myproject.GetTheLocation.getPos(A[0], A[1], C[0], C[1], D[0], D[1], distance_AC, distance_AD, 2, 1);
        }
        if (Double.isNaN(location3[0]) || Double.isNaN(location3[1])) {
            location3 = Myproject.GetTheLocation.getPos(A[0], A[1], C[0], C[1], D[0], D[1], distance_AC, distance_AD, 4, 1);
        }
        if (Double.isNaN(location3[0]) || Double.isNaN(location3[1])) {
            location3 = Myproject.GetTheLocation.getPos(A[0], A[1], C[0], C[1], D[0], D[1], distance_AC, distance_AD, 4, 3);
        }
        if (Double.isNaN(location3[0]) || Double.isNaN(location3[1])) {
            location3 = Myproject.GetTheLocation.getPos(A[0], A[1], C[0], C[1], D[0], D[1], distance_AC, distance_AD, 6, 3);
        }
        if (Double.isNaN(location3[0]) || Double.isNaN(location3[1])) {
            location3 = Myproject.GetTheLocation.getPos(A[0], A[1], C[0], C[1], D[0], D[1], distance_AC, distance_AD, -2, 1);
        }
        if (Double.isNaN(location3[0]) || Double.isNaN(location3[1])) {
            location3 = Myproject.GetTheLocation.getPos(A[0], A[1], C[0], C[1], D[0], D[1], distance_AC, distance_AD, -2, -1);
        }
        if (Double.isNaN(location3[0]) || Double.isNaN(location3[1])) {
            location3 = Myproject.GetTheLocation.getPos(A[0], A[1], C[0], C[1], D[0], D[1], distance_AC, distance_AD, 2, -1);
        }
        if (Double.isNaN(location3[0]) || Double.isNaN(location3[1])) {
            location3[0] = 0;
            location3[1] = 0;
        }

//          try{
//       	   location4= GetTheLocation.getPos(B[0], B[1], C[0], C[1],D[0], D[1],distance_BC,distance_BD, Myproject.preres[hid].x, Myproject.preres[hid].y );
//          }
//          catch(Exception e){}
        location4 = Myproject.GetTheLocation.getPos(B[0], B[1], C[0], C[1], D[0], D[1], distance_BC, distance_BD, 2, 1);

        //濡傛灉鍑虹幇杩唬涓嶆敹鏁涚殑鐜拌薄锛屽垯鑰冭檻鏇存崲璞￠檺
        if (Double.isNaN(location4[0]) || Double.isNaN(location4[1])) {
            location4 = Myproject.GetTheLocation.getPos(B[0], B[1], C[0], C[1], D[0], D[1], distance_BC, distance_BD, 2, 1);
        }
        if (Double.isNaN(location4[0]) || Double.isNaN(location4[1])) {
            location4 = Myproject.GetTheLocation.getPos(B[0], B[1], C[0], C[1], D[0], D[1], distance_BC, distance_BD, 4, 1);
        }
        if (Double.isNaN(location4[0]) || Double.isNaN(location4[1])) {
            location4 = Myproject.GetTheLocation.getPos(B[0], B[1], C[0], C[1], D[0], D[1], distance_BC, distance_BD, 4, 3);
        }
        if (Double.isNaN(location4[0]) || Double.isNaN(location4[1])) {
            location4 = Myproject.GetTheLocation.getPos(B[0], B[1], C[0], C[1], D[0], D[1], distance_BC, distance_BD, 6, 3);
        }
        if (Double.isNaN(location4[0]) || Double.isNaN(location4[1])) {
            location4 = Myproject.GetTheLocation.getPos(B[0], B[1], C[0], C[1], D[0], D[1], distance_BC, distance_BD, -2, 1);
        }
        if (Double.isNaN(location4[0]) || Double.isNaN(location4[1])) {
            location4 = Myproject.GetTheLocation.getPos(B[0], B[1], C[0], C[1], D[0], D[1], distance_BC, distance_BD, -2, -1);
        }
        if (Double.isNaN(location4[0]) || Double.isNaN(location4[1])) {
            location4 = Myproject.GetTheLocation.getPos(B[0], B[1], C[0], C[1], D[0], D[1], distance_BC, distance_BD, 2, -1);
        }
        if (Double.isNaN(location4[0]) || Double.isNaN(location4[1])) {
            location4[0] = 0;
            location4[1] = 0;
        }
        double[] rx = zhengtai(location1[0], location2[0], location3[0], location4[0]);
        double[] ry = zhengtai(location1[1], location2[1], location3[1], location4[1]);
        double[] location = new double[2];
        double sx = 0, sy = 0, count = 0;
        //0<x<9.6 0<y<4.8
        if (rx[0] > 0 && rx[0] < 12) {
            sx += rx[0];
            count++;
        }


        if (rx[1] > 0 && rx[1] < 12) {
            sx += rx[1];
            count++;
        }

        if (rx[2] > 0 && rx[2] < 12) {
            sx += rx[2];
            count++;
        }

        if (rx[3] > 0 && rx[3] < 12) {
            sx += rx[3];
            count++;
        }

        if (ry[0] > 0 && ry[0] < 6) {
            sy += ry[0];
        }
        if (ry[1] > 0 && ry[1] < 6) {
            sy += ry[1];
        }

        if (ry[2] > 0 && ry[2] < 6) {
            sy += ry[2];
        }

        if (ry[3] > 0 && ry[3] < 6) {
            sy += ry[3];
        }

        location[0] = sx / count;
        location[1] = sy / count;

//           System.out.println("lo1:"+location1[0]+";"+location1[1]);
//           System.out.println("lo2:"+location2[0]+";"+location2[1]);
//           System.out.println("lo3:"+location3[0]+";"+location3[1]);
//           System.out.println("lo4:"+location4[0]+";"+location4[1]);
//           System.out.println("lo:"+location[0]+";"+location[1]);
//           System.out.println("...");


        if (Double.isNaN(location[0]) || Double.isNaN(location[1])) {
            location[0] = 0;
            location[1] = 0;
        } else {
            double a = 0;
            try {
                a = Math.sqrt((Myproject.preres[hid].x - location[0]) * (Myproject.preres[hid].x - location[0])
                        + (Myproject.preres[hid].y - location[1]) * (Myproject.preres[hid].y - location[1]));

            } catch (Exception e) {

            }
//        	System.out.println("################");
//        	System.out.println(a);
//        	System.out.println("################");
            if (location[0] == 0) {
                try {
                    location[0] = Myproject.preres[hid].x;
                    location[1] = Myproject.preres[hid].y;
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }


            if (a < 0.1)
                Myproject.preres[hid] = new Position(location[0], location[1]);
            else if (a >= 0.1 && a < 0.3) {
                location[0] = Myproject.preres[hid].x;
                location[1] = Myproject.preres[hid].y;
                Myproject.preres[hid] = new Position(location[0], location[1]);
            } else if (a >= 0.3 && a < 0.6) {
                location[0] = 0.7 * location[0] + 0.3 * Myproject.preres[hid].x;
                location[1] = 0.7 * location[1] + 0.3 * Myproject.preres[hid].y;
                Myproject.preres[hid] = new Position(location[0], location[1]);
            } else if (a >= 0.6 && a < 1) {
                location[0] = 0.6 * location[0] + 0.4 * Myproject.preres[hid].x;
                location[1] = 0.6 * location[1] + 0.4 * Myproject.preres[hid].y;
                Myproject.preres[hid] = new Position(location[0], location[1]);
            } else {
                location[0] = 0.3 * location[0] + 0.7 * Myproject.preres[hid].x;
                location[1] = 0.3 * location[1] + 0.7 * Myproject.preres[hid].y;
                Myproject.preres[hid] = new Position(location[0], location[1]);
            }
//        	else{
//        		location[0]=Myproject.preres[hid].x;
//        		location[1]=Myproject.preres[hid].y;
//        		Myproject.preres[hid]=new Position(location[0],location[1]);
//      	}
            Myproject.D("p", "x:" + location[0] + "y:" + location[1]);
            location[0] = location[0] * map.getLongitude() / map.getLength() - map.getLongitude() / 2;
            location[1] = location[1] * map.getLatitude() / map.getWidth() - map.getLatitude() / 2;
            if (location[0] < -180 || location[0] > 180 || location[1] < -90 || location[1] > 90) {
                location[0] = Myproject.preres[hid].x * map.getLongitude() / map.getLength() - map.getLongitude() / 2;
                location[1] = Myproject.preres[hid].y * map.getLatitude() / map.getWidth() - map.getLatitude() / 2;
            }
        }
//           System.out.println("haha"+location[0]+";"+location[1]);
        return location;
    }

    public static double[] zhengtai(double x1, double x2, double x3, double x4) {
        double a[] = new double[4];
        double avge = (x1 + x2 + x3 + x4);

        double n2 = Math.sqrt(((x1 - avge) * (x1 - avge) + (x2 - avge) * (x2 - avge) + (x3 - avge) * (x3 - avge) + (x4 - avge) * (x4 - avge)) / 4);
        if (x1 < avge - n2 && x1 > avge + n2) {
            x1 = 0;
        }

        if (x2 < avge - n2 && x2 > avge + n2) {
            x2 = 0;
        }
        if (x3 < avge - n2 && x3 > avge + n2) {
            x3 = 0;
        }
        if (x4 < avge - n2 && x4 > avge + n2) {
            x4 = 0;
        }
        a[0] = x1;
        a[1] = x2;
        a[2] = x3;
        a[3] = x4;
        return a;
    }

}
