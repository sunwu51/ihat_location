package myproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class DitalThread implements Runnable {
    private byte[] buf;

    public DitalThread(byte[] buf) {
        this.buf = buf;
    }

    public void run() {
        if (buf[0] == 1)
            Rdeal(buf);
        else if (buf[0] == 0)
            Pdeal(buf);
        else ;
    }

    /**************************************************************************************************/
                                /*R包的处理：直接存储并对上一seq操作*/
    public void Rdeal(byte[] buf) {
        R r = new R(buf);
        Myproject.D("p", "r,seq" + r.SEQ + "aid" + r.AID + "cr" + r.CR + "ps" + r.PS);
        //System.out.println("AID"+r.AID);
//		System.out.println("R"+"r,seq"+r.SEQ+"aid"+r.AID+"cr"+r.CR+"ps"+r.PS);
        int num = r.AID - 1;
        //aid-1变成数组序号；
//		System.out.println(Myproject.rs[num].size());
        Myproject.rs[num].add(r);

        if (Myproject.rs[num].size() > 30) {
            Myproject.rs[num].remove(0);
        }
        try {
            //设置前一个的发送时间,设置完cs后检查
            R prer = Myproject.rs[num].stream().filter((itr) -> (itr.SEQ == (r.SEQ - 1 < 0 ? 255 : r.SEQ - 1))).findFirst().get();
            prer.CS = r.PS;
            prer.FLAG = true;
        } catch (Exception e) {
            //System.out.println("A"+r.AID+";"+(r.SEQ-1)+"的R包为空");
            //Myproject.D("p","A"+r.AID+";"+(r.SEQ-1)+"的R包为空");
            //System.out.println("R包丢包");
        }
    }
    /**************************************************************************************************/

    /**************************************************************************************************/
			/*P包的操作：直接存储->判断是不是该HID该seq下的第一个来的->若不是没你啥事了->若是则处理*/
    public void Pdeal(byte[] buf) {
        P p = new P(buf);
        Myproject.D("p", "p,seq" + p.SEQ + "aid," + p.AID);
//		System.out.println("P"+"p,seq"+p.SEQ+"aid"+p.AID+"cr"+p.CR);
        int num = p.AID - 1;
        //aid-1变成数组序号；

		/*判断是不是第一个到的，如果是则设置延时函数*/
        Myproject.pfirst[p.HID][p.SEQ]++;

        Myproject.ps[p.HID][num].add(p);
        if (Myproject.ps[p.HID][num].size() > 4) {
            Myproject.ps[p.HID][num].remove(0);
        }

        {
//            if (!Myproject.H2C[p.HID].equals(p.CID)) {
//                Myproject.H2C[p.HID] = p.CID;
//                HC.sqlSet(p.HID, p.CID);
//            }
        }
        //System.out.println(p.AID+":"+p.SEQ);

        //Myproject.D("p", "p,seq"+p.SEQ+"aid"+p.AID+"cr"+p.CR);
        //if(Myproject.pfirst[p.HID][p.SEQ]==3)
        //System.out.println("3个"+p.SEQ);
        if (Myproject.pfirst[p.HID][p.SEQ] == 1) {
            for (int i = 50; i < 100; i++) {
                Myproject.pfirst[p.HID][p.SEQ - i >= 0 ? p.SEQ - i : 256 + p.SEQ - i] = 0;
            }
            try {
                Thread.sleep(200);
                if (Myproject.pfirst[p.HID][p.SEQ] >= 4) {
                    Myproject.D("d", "p;h" + p.HID + "seq" + p.SEQ + "包够仨");
                    //try
                    {
                        int[] r2 = Find2R(4);//寻找4个节点共同的R包
//					System.out.println(r2[0]+","+r2[1]);
                        Map<Integer, Double> Tampmap = new HashMap<>();//这个变量为时间戳映射
                        for (P itemp : PcheckBack(p)) //Pchekback是遍历这个数组new CopyOnWriteArrayList[10][4]，能得到四组数据
                        {
                            R rone = Myproject.rs[itemp.AID - 1].stream().filter(y -> y.SEQ == r2[0]).findFirst().get();
                            R rtwo = Myproject.rs[itemp.AID - 1].stream().filter(y -> y.SEQ == r2[1]).findFirst().get();
                            if (!rone.FLAG || !rtwo.FLAG) System.out.println("未检验错误");
                            //System.out.println("check;"+(rone.CS-rtwo.CS)+";");
                            //if(itemp.CR-rone.CR<0)System.out.println("yes");
                            Tampmap.put(itemp.AID - 1, mod(mod(itemp.CR - rone.CR) / (double) mod(rone.CR - rtwo.CR) * mod(rone.CS - rtwo.CS) + rone.CS));
                        }

                        {
                            double distance_AB = (Tampmap.get(0) - Tampmap.get(1)) / 128 / 499.2 / 1000000 * 299702547;
                            double distance_AC = (Tampmap.get(0) - Tampmap.get(2)) / 128 / 499.2 / 1000000 * 299702547;
                            double distance_AD = (Tampmap.get(0) - Tampmap.get(3)) / 128 / 499.2 / 1000000 * 299702547;
                            double distance_BC = (Tampmap.get(1) - Tampmap.get(2)) / 128 / 499.2 / 1000000 * 299702547;
                            double distance_BD = (Tampmap.get(1) - Tampmap.get(3)) / 128 / 499.2 / 1000000 * 299702547;

//						System.out.println("Tampmap.get(0):"+Tampmap.get(0));
//						System.out.println("Tampmap.get(1):"+Tampmap.get(1));
//						System.out.println("Tampmap.get(2):"+Tampmap.get(2));
//						System.out.println("Tampmap.get(3):"+Tampmap.get(3));

//				        System.out.println("AB"+distance_AB);
//				        System.out.println("AC"+distance_AC);
//				        System.out.println("AD"+distance_AD);
//				        System.out.println("BC"+distance_BC);
//				        System.out.println("BD"+distance_BD);
                            double[] Lo = LocationTool.Calculating(distance_AB, distance_AC, distance_AD, distance_BC, distance_BD, LocationTool.anchorInfoList, LocationTool.map, p.HID);
                            System.out.println("seq" + p.SEQ + "x:" + Lo[0] + ";y:" + Lo[1]);


                            L l = new L(p.CID, Lo[0], Lo[1]);


                            if (SendData.data[p.HID].size() == 0) {
                                SendData.data[p.HID] = new CopyOnWriteArrayList<L>();

                            }
                            {
                                if (l.x != 0) SendData.data[p.HID].add(l);
                            }
                            if (SendData.data[p.HID].size() > 3) {
                                SendData.data[p.HID].remove(0);
                            }
                        }
                    }

                } else {
                    Myproject.D("d", "p" + p.SEQ + "不够三个");
                    //System.out.println("p不足3个"+Myproject.pfirst[p.HID][p.SEQ]+"seq"+p.SEQ+"num:"+Myproject.testb++);
                    try {
                        if (SendData.data[p.HID].size() > 3)
                            SendData.data[p.HID].remove(0);
                    } catch (Exception e) {
                        // TODO: handle exceptions
                    }
                }

            } catch (InterruptedException e1) {
                // TODO 自动生成的 catch 块
                e1.printStackTrace();
            }
        }

    }


    /* 找出该HID该SEQ 不同AID的所有P包(即上面提供的个数的包都找出) */
    public ArrayList<P> PcheckBack(P p) {
        ArrayList<P> arrp = new ArrayList<P>();
        for (int i = 0; i < Myproject.ps.length; i++) {
            try {
                P varp = Myproject.ps[p.HID][i].stream().filter((s) -> (s.SEQ == p.SEQ)).findFirst().get();
                arrp.add(varp);
            } catch (Exception e) {
            }
        }
        return arrp;
    }
    /**************************************************************************************************/

    /**************************************************************************************************/
							/* 找出AID为num+1的flag=true的所有R包 的seq */
    public ArrayList<Integer> FindR(int num) {
        ArrayList<Integer> seqs = new ArrayList<Integer>();
        Myproject.rs[num].stream().filter((x) -> (x.FLAG)).distinct().forEach((x) -> {
            seqs.add(x.SEQ);
        });
        return seqs;
    }
    /**************************************************************************************************/

    /**************************************************************************************************/
						/* 各个锚节点对应的flag置true的R包中取共有的，并且取最晚的俩来做标尺 */
    public int[] Find2R(int count) {
        ArrayList<Integer> s = new ArrayList<>();
        ArrayList<Integer> sq = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < Myproject.ps[0].length; i++) {
            FindR(i).forEach(x -> sq.add(x));
        }
        sq.stream().distinct().forEach(x -> {
            map.put(x, (int) (sq.stream().filter(y -> y.intValue() == x.intValue()).count()));
        });
        map.forEach((k, v) -> {
            if (v == count) {
                s.add(k);
            }
        });
        Collections.sort(s);
        if (s.get(s.size() - 1) - s.get(0) < 100)
            ;
        else {
            Comparator<Integer> comparator = new Comparator<Integer>() {
                public int compare(Integer s1, Integer s2) {
                    if (s1 < 200)
                        s1 = s1 + 256;
                    if (s2 < 200)
                        s2 = s2 + 256;
                    return s1 - s2;
                }
            };
            Collections.sort(s, comparator);
        }
        return new int[]{s.get(s.size() - 1), s.get(s.size() - 2)};//这个地方有时会报s为空的异常
    }

    /**************************************************************************************************/
    public double mod(double a) {
        if (a > Math.pow(2, 40)) return a - Math.pow(2, 40);
        return a > 0 ? a : a + Math.pow(2, 40);
    }

    public double smod(double a) {
        if (a > Math.pow(2, 39) || a < -Math.pow(2, 39)) {
            System.out.println("溢出错误");
            return a > 0 ? a - Math.pow(2, 40) : Math.pow(2, 40) + a;
        }
        return a;
    }

}