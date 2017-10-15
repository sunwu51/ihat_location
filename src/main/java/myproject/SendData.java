package myproject;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import com.google.gson.Gson;

public class SendData {
    private static Timer timer;
    public static CopyOnWriteArrayList<L>[] data;

    public static void Send() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                for (CopyOnWriteArrayList<L> hiddata : data) {
                    if (hiddata.size() > 0) {
                        try {
                            if (!sendable(hiddata)) {
                                continue;
                            }
                            myproject.mqttClient.publish(Init.mqtttopic, new MqttMessage(new Gson().toJson(GetAve(hiddata)).getBytes()));
                            hiddata.remove(hiddata.size() - 1);
                            hiddata.add(GetAve(hiddata));
                            //System.out.println(hiddata.size());
                        } catch (Exception e) {
                        }
                    }
                }
            }
        };
        timer.schedule(task, 0L, 500L);
    }

    public static boolean sendable(CopyOnWriteArrayList<L> hiddata) {
        return true;
    }

    public static void main(String[] args) {
    }

    public static L GetAve(CopyOnWriteArrayList<L> arr) {
        double sx = 0, sy = 0;
        String CID = "";
        int k = 0;
        for (int i = 0; i < arr.size(); i++) {
            //sx+=arr.get(i).x*(i+1);sy+=arr.get(i).y*(i+1);
            if (Double.isNaN(arr.get(i).x) || Double.isInfinite(arr.get(i).x)) {
                arr.remove(i);
            }
            sx += arr.get(i).x;
            sy += arr.get(i).y;
            CID = arr.get(i).CID;
        }
        //L res=new L(CID,sx/GetHei(arr.size()),sy/GetHei(arr.size()));
        L res = new L(CID, sx / (arr.size() - k), sy / (arr.size() - k));
        return res;
    }

    public static int GetHei(int num) {
        int s = 0;
        for (int i = 1; i <= num; i++) {
            s += i;
        }
        return s;
    }
}