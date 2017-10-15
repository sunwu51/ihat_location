package myproject;

public class R {
    public int RID;//1-2
    public int AID;//3-4
    public int SEQ;//5
    public long PS;//6-10
    public long CR;//11-15
    public long CS;
    public boolean FLAG;

    public R() {
        this.RID = -1;
        this.PS = -1;
        this.CS = -1;
        this.AID = -1;
        this.CR = -1;
        this.FLAG = false;
    }

    public R(byte[] buf) {
        this.RID = buf[1] + buf[2] * 256;
        this.AID = buf[3] + buf[4] * 256;
        if (this.AID == 2) this.AID = 1;
        else if (this.AID == 4) this.AID = 2;
        else if (this.AID == 6) this.AID = 3;
        else if (this.AID == 8) this.AID = 4;
        else return;

        this.SEQ = buf[5] >= 0 ? buf[5] : 256 + buf[5];
        this.PS = getStamp(buf, 6);
        this.CR = getStamp(buf, 11);
        this.FLAG = false;
    }

    public void check() {
        this.FLAG = (this.CR != -1 && this.CS != -1);
    }

    public void clear() {
        this.RID = -1;
        this.PS = -1;
        this.CS = -1;
        this.AID = -1;
        this.CR = -1;
        this.FLAG = false;
    }

    public static long getStamp(byte[] b, int start) {
        long s = 0;
        int i = 4;
        while (i >= 0) {
            long f = b[i + start] > 0 ? b[i + start] : 256 + b[i + start];
            s = s * 256 + f;
            i--;
        }
        return s;
    }

    public void copyR(R rcome) {
        this.AID = rcome.AID;
        this.RID = rcome.RID;
        this.SEQ = rcome.SEQ;
        this.PS = rcome.PS;
        this.CR = rcome.CR;
    }
}