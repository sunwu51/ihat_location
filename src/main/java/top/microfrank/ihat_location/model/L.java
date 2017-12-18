package top.microfrank.ihat_location.model;

import lombok.Data;

@Data
public class L {
    public String CID;
    public double x;
    public double y;
    public double z;

    public L(String CID, double X, double Y) {
        this.CID = CID;
        this.x = X;
        this.y = Y;
        this.z = 0;
    }

    public L() {
    }
}