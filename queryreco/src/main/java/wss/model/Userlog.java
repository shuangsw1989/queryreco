package wss.model;



public class Userlog {
	private int id;
	private String visittime;
	private String uid;
	private String queryword;
	private String urlrank;
	private String clickorder;
	private String clickurl;
	
	
	public Userlog() {
		super();
	}


	public Userlog(String visittime, String uid, String queryword,
			String urlrank, String clickorder, String clickurl) {
		super();
		this.visittime = visittime;
		this.uid = uid;
		this.queryword = queryword;
		this.urlrank = urlrank;
		this.clickorder = clickorder;
		this.clickurl = clickurl;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getVisittime() {
		return visittime;
	}


	public void setVisittime(String visittime) {
		this.visittime = visittime;
	}


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public String getQueryword() {
		return queryword;
	}


	public void setQueryword(String queryword) {
		this.queryword = queryword;
	}


	public String getUrlrank() {
		return urlrank;
	}


	public void setUrlrank(String urlrank) {
		this.urlrank = urlrank;
	}


	public String getClickorder() {
		return clickorder;
	}


	public void setClickorder(String clickorder) {
		this.clickorder = clickorder;
	}


	public String getClickurl() {
		return clickurl;
	}


	public void setClickurl(String clickurl) {
		this.clickurl = clickurl;
	}

	
}
