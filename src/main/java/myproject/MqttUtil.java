package myproject;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class MqttUtil {
    public static MqttClient getClient(String broker) {

        int qos = 2;//最多一次（0）最少一次（1）只一次（2）
//        String broker       = "tcp://120.76.136.124:1883";
        String clientId = "location+" + new Random().nextInt();
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setAutomaticReconnect(true);
//            connOpts.setUserName("java");
//            connOpts.setPassword("123456".toCharArray());
            System.out.println("-------------------------------");
            System.out.println("Mqtt尝试连接...");
            sampleClient.connect(connOpts);
            //连接成功

            System.out.println("Mqtt连接成功，即将启动Tcp服务");
            System.out.println("-------------------------------");

            //订阅tp1主题
//            sampleClient.subscribe(topic,new IMqttMessageListener() {
//				@Override
//				public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
//					// TODO Auto-generated method stub
//					System.out.println("topic:"+arg0+";message:"+arg1);
//				}
//			});
            //发布tp1主题的消息
            return sampleClient;

        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
            return null;
        }
    }
}
