package com.sist.vo;

import java.util.Date;

import lombok.Data;
@Data
public class ReserveVO {
	private int no,fno,ok;
	private String id,rday,time,inwon,dbday;
	private Date regdate;
	private FoodVO fvo=new FoodVO();
}
