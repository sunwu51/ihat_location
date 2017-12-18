package top.microfrank.ihat_location.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapInfo {
    private double longitude;
    private double latitude;
    private double length;
    private double width;
}