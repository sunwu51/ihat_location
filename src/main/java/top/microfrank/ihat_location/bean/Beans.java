package top.microfrank.ihat_location.bean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.microfrank.ihat_location.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Frank on 2017/12/18.
 */
@Configuration
public class Beans {
    @Bean(name="rs")
    public CopyOnWriteArrayList<R>[] rs(){
        CopyOnWriteArrayList<R>[] rs = new CopyOnWriteArrayList[4];
        for (int i = 0; i < 4; i++) {
            rs[i] = new CopyOnWriteArrayList<R>();
        }
        return rs;
    }
    @Bean(name="preres")
    public Position[] preres(){
        Position[] preres = new Position[10];
        for (Position p : preres) {
            p = new Position(0, 0);
        }
        return preres;
    }
    @Bean(name="ps")
    public CopyOnWriteArrayList<P>[][] ps(){
        CopyOnWriteArrayList<P>[][] ps = new CopyOnWriteArrayList[10][4]; //数组4，表示4个节点共同的R包
        for (int j = 0; j < 10; j++)
            for (int i = 0; i < 4; i++) {
                ps[j][i] = new CopyOnWriteArrayList<P>();
            }
        return ps;
    }
    @Bean(name="pfirst")
    public int[][] pfirst(){
        return new int[10][256];
    }
    @Bean(name="exec")
    public ExecutorService exec(){
        return  Executors.newCachedThreadPool();
    }
    @Bean(name="senddata")
    public CopyOnWriteArrayList<L>[] senddata(){
        CopyOnWriteArrayList[] data = new CopyOnWriteArrayList[10];
        for (int i = 0; i < 10; i++) {
            data[i] = new CopyOnWriteArrayList<L>();
        }
        return data;
    }
    @Bean
    public Config config(){
        System.out.println("-------------------------------");
        System.out.println("配置信息导入...");
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map m = mapper.readValue(new File("Config.json"), Map.class);
            int port=Integer.parseInt(m.get("port").toString());
            String mqttbroker=m.get("mqttbroker").toString();
            String mqtttopic=m.get("mqtttopic").toString();
            MapInfo mapInfo=mapper.readValue(mapper.writeValueAsString((Map)m.get("map")),MapInfo.class);
            List<Msanchor> msanchorList=mapper.readValue(mapper.writeValueAsString(m.get("anchors")),new TypeReference<List<Msanchor>>() {});
            System.out.println("port " + port );
            System.out.println("mqttbroker " + mqttbroker );
            System.out.println("mqtttopic " + mqtttopic );
            System.out.println("mapInfo " + mapInfo );
            System.out.println("msanchorList " + msanchorList );
            System.out.println("配置完成，即将启动Mqtt客户端");
            return new Config(port,mqttbroker,mqtttopic,mapInfo,msanchorList);
        } catch (Exception e) {
            System.out.println("初始化配置失败");
            return null;
        }
    }
}
