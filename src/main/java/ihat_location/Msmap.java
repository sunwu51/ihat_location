package ihat_location;

public class Msmap {
	
	private int mapid;
	public int getMapid() {
		return mapid;
	}
	public void setMapid(int mapid) {
		this.mapid = mapid;
	}
	public String getMapname() {
		return mapname;
	}
	public void setMapname(String mapname) {
		this.mapname = mapname;
	}
	public double getMapx() {
		return mapx;
	}
	public void setMapx(double mapx) {
		this.mapx = mapx;
	}
	public double getMapy() {
		return mapy;
	}
	public void setMapy(double mapy) {
		this.mapy = mapy;
	}
	private String mapname;
	private double mapx;
	private double mapy;
	private double longitude;
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	private double latitude;
	private double length;
	private double width;
	public Msmap(double longitude, double latitude, double length,
			double width)
		{
		super();
		this.longitude = longitude;
		this.latitude  = latitude;
		this.length    = length;
		this.width     = width;
		}
	public Msmap()
	{
	}
	
	}
	

