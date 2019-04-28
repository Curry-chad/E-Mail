package com.bean;

public class Email {
	public Email() {
		
	}
		// TODO Auto-generated constructor stub
	public Email(String addressee_id,String addresser_id,String title,int readed,String filepath,String time,String content,String filename) {
		// TODO Auto-generated constructor stub
		this.addressee_id=addressee_id;
		this.addresser_id=addresser_id;
		this.title=title;
		this.readed=readed;
		this.filepath=filepath;
		this.time=time;
		this.content=content;
		this.filename=filename;
	}
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	private String addressee_id;
	private String addresser_id;
	private String title;
	private int readed;
	private String filepath;
	private String filename;
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	private String time;
	private String content;

	public String getAddressee_id() {
		return addressee_id;
	}
	public void setAddressee_id(String addressee_id) {
		this.addressee_id = addressee_id;
	}
	public String getAddresser_id() {
		return addresser_id;
	}
	public void setAddresser_id(String addresser_id) {
		this.addresser_id = addresser_id;
	}
	public String getTile() {
		return title;
	}
	public void setTile(String tile) {
		this.title = tile;
	}
	public int getReaded() {
		return readed;
	}
	public void setReaded(int readed) {
		this.readed = readed;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
