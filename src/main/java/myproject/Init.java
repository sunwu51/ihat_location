package myproject;

import java.io.File;
import java.rmi.server.ExportException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Init {
    static int port;
    static String mqttbroker;
    static String mqtttopic;
    static MapInfo mapInfo;
    static List<Msanchor> msanchorList;


    public static boolean SysInit() {
        System.out.println("-------------------------------");
        System.out.println("配置信息导入...");

        try {
            ObjectMapper mapper = new ObjectMapper();
            Map m = mapper.readValue(new File("Config.json"), Map.class);
            port=Integer.parseInt(m.get("port").toString());
            mqttbroker=m.get("mqttbroker").toString();
            mqtttopic=m.get("mqtttopic").toString();
            mapInfo=mapper.readValue(mapper.writeValueAsString((Map)m.get("map")),MapInfo.class);
            msanchorList=mapper.readValue(mapper.writeValueAsString(m.get("anchors")),new TypeReference<List<Msanchor>>() {});
            System.out.println("port " + port );
            System.out.println("mqttbroker " + mqttbroker );
            System.out.println("mqtttopic " + mqtttopic );
            System.out.println("mapInfo " + mapInfo );
            System.out.println("msanchorList " + msanchorList );
            System.out.println("配置完成，即将启动Mqtt客户端");
            return true;
        } catch (Exception e) {
            System.out.println("初始化配置失败");
            return false;
        }

    }
}