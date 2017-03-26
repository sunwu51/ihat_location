package ihat_location;
/*
 * 锚点的定义
 * 锚点的ID，名字，地图的id,锚点的坐标
 */
public class Msanchor {
	
		private String anchorid;
		private String mapid;

	    private String anchorname;

	    private double anchorx;

	    private double anchory;

	    private double anchorz;
	    
	    public Msanchor(String anchorid,String mapid,String anchorname,double anchorx,double anchory,double anchorz)
	    {
	    	this.anchorid=anchorid;
	    	this.mapid=mapid;
	    	this.anchorname=anchorname;
	    	this.anchorx=anchorx;
	    	this.anchory=anchory;
	    	this.anchorz=anchorz;
	    }
		public String getAnchorid() {
			return anchorid;
		}

		public void setAnchorid(String anchorid) {
			this.anchorid = anchorid;
		}

		public String getMapid() {
			return mapid;
		}

		public void setMapid(String mapid) {
			this.mapid = mapid;
		}

		public String getAnchorname() {
			return anchorname;
		}

		public void setAnchorname(String anchorname) {
			this.anchorname = anchorname;
		}

		public double getAnchorx() {
			return anchorx;
		}

		public void setAnchorx(double anchorx) {
			this.anchorx = anchorx;
		}

		public double getAnchory() {
			return anchory;
		}

		public void setAnchory(double anchory) {
			this.anchory = anchory;
		}

		public Double getAnchorz() {
			return anchorz;
		}

		public void setAnchorz(Double anchorz) {
			this.anchorz = anchorz;
		}

		
}
