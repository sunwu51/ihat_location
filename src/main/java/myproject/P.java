package myproject;

public class P {
    public int HID;//1-2
    public int AID;//3-4
    public int SEQ;//5
    public String CID;//6-9
    public long CR;//10-14
    public boolean FLAG;

    public P(byte[] buf) {
        this.HID = buf[1] + buf[2] * 256;
        this.AID = buf[3] + buf[4] * 256;
        this.SEQ = buf[5] >= 0 ? buf[5] : 256 + buf[5];
        this.CID = getCID(buf, 6);
        if (this.AID == 2) this.AID = 1;
        else if (this.AID == 4) this.AID = 2;
        else if (this.AID == 6) this.AID = 3;
        else if (this.AID == 8) this.AID = 4;
        else return;


        this.CR = R.getStamp(buf, 10);
        this.FLAG = false;
    }

    public P() {
        this.HID = -1;
        this.AID = -1;
        this.CR = -1;
        this.CID = "";
        this.FLAG = false;
    }

    public String getCID(byte[] buf, int start) {
        String cid = "";
        for (int i = start; i < start + 4; i++) {
            int a = buf[i] >= 0 ? buf[i] : 256 + buf[i];
            cid += lpad(2, Integer.toHexString(a));
        }
        return cid;
    }

    private String lpad(int length, String number) {
        if (number.length() >= length)
            return number;
        else {
            return "0" + number;
        }
    }
}