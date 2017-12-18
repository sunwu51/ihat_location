package top.microfrank.ihat_location.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Msanchor {
    private String anchorid;
    private String mapid;
    private String anchorname;
    private double anchorx;
    private double anchory;
    private Double anchorz;
}