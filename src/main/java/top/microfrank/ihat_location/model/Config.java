package top.microfrank.ihat_location.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by Frank on 2017/12/18.
 */
@Data
@AllArgsConstructor
public class Config {
    int port;
    String mqttbroker;
    String mqtttopic;
    MapInfo mapInfo;
    List<Msanchor> msanchorList;
}
