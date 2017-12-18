package top.microfrank.ihat_location;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import top.microfrank.ihat_location.deal.DealThread;
import top.microfrank.ihat_location.deal.SendData;
import top.microfrank.ihat_location.model.Config;
import top.microfrank.ihat_location.model.P;
import top.microfrank.ihat_location.model.Position;
import top.microfrank.ihat_location.model.R;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Configuration
public class Myproject {
    @Autowired
    private ExecutorService exec;
    @Autowired
    private MqttClient mqttClient;
    @Autowired
    private Config config;
    @Autowired
    private SendData sendData;

    /**************************************************************************************************/
    public void execute() throws IOException {
        if (mqttClient == null) {
            throw new IOException("Mqtt connect fail");
        }

        sendData.send();
        ServerSocket ss = new ServerSocket(config.getPort());
        System.out.println("Tcpserver 启动，端口："+config.getPort());

        while (true) {
            Socket s = ss.accept();
            exec.execute(new DealThread(s));
        }
    }
}