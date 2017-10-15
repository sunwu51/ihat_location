package myproject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class myproject {
    public static CopyOnWriteArrayList<R>[] rs;
    public static position[] preres;
    public static CopyOnWriteArrayList<P>[][] ps;
    public static int[][] pfirst;
    public static int testa, testb;
    public static long teststart;
    public static boolean packinfo;
    public static OutputStream outputStream;
    public static FileOutputStream f;
//    public static String[] H2C;
    public static MqttClient mqttClient;
    /**************************************************************************************************/
                                        /*封装打印方法和宏开关便于调试*/
    public static boolean debuginfo;
    public static boolean clacinfo;

    public myproject() throws IOException {
    }

    public static void D(String type, Object o) {
        switch (type) {
            case "p":
                if (packinfo) {
                    System.out.println(o);
                    try {
                        f.write((o.toString() + "\r\n").getBytes());
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;
            case "d":
                if (debuginfo) {
                    System.out.println(o);
                }
                break;
            case "c":
                if (clacinfo) {
                    System.out.println(o);
                }
                break;
            default:
                break;
        }

    }

    /**************************************************************************************************/

    @SuppressWarnings({"unchecked", "resource"})
    public static void main(String[] args) throws IOException {
        // TODO 自动生成的方法存根
        testa = 0;
        testb = 0;
        teststart = System.currentTimeMillis();
        packinfo = false;
        debuginfo = false;
        clacinfo = false;
        f = new FileOutputStream("p.txt");
        ps = new CopyOnWriteArrayList[10][4]; //数组4，表示4个节点共同的R包
        for (int j = 0; j < 10; j++)
            for (int i = 0; i < 4; i++) {
                ps[j][i] = new CopyOnWriteArrayList<P>();
            }
        rs = new CopyOnWriteArrayList[4];
        for (int i = 0; i < 4; i++) {
            rs[i] = new CopyOnWriteArrayList<R>();
        }
        pfirst = new int[10][256];
        preres = new position[10];

//        H2C = new String[10];
//		for (int i=0;i<H2C.length;i++) {
//			H2C[i]="";
//		}
        for (position p : preres) {
            p = new position(0, 0);
        }

        if (!Init.SysInit()) {
            System.out.println("配置文件读取失败");
            return;
        }
        LocationTool.init();
//        HC.setOnline();

        mqttClient = MqttUtil.getClient(Init.mqttbroker);
        if (mqttClient == null) {
            throw new IOException("Mqtt connect fail");
        }
        SendData.data = new CopyOnWriteArrayList[10];
        for (int i = 0; i < 10; i++) {
            SendData.data[i] = new CopyOnWriteArrayList<L>();
        }
        SendData.Send();
        ServerSocket ss = new ServerSocket(Init.port);
        System.out.println("Tcpserver 启动，端口："+Init.port);
        while (true) {
            Socket s = ss.accept();
            new Thread(new DealThread(s)).start();
        }
    }
}