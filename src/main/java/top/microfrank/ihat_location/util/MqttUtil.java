package top.microfrank.ihat_location.util;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.microfrank.ihat_location.model.Config;

import java.util.Random;
@Configuration
public class MqttUtil {
    @Autowired
    Config config;
    @Bean
    public MqttClient getClient() {

        int qos = 2;//最多一次（0）最少一次（1）只一次（2）
        String clientId = "local";
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            MqttClient sampleClient = new MqttClient(config.getMqttbroker(), clientId, persistence);
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
