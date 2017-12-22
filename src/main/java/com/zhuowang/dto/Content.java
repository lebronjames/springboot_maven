package com.zhuowang.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Content {

	private int id;
	private String name;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",locale="zh",timezone="GMT+8")
	private Date createTime;
	
	public Content(){
		
	}
	
	public Content(int id,String name){
		this.id = id;
		this.name = name;
	}
	
	public Content(int id,String name,Date createTime){
		this.id = id;
		this.name = name;
		this.createTime = createTime;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
