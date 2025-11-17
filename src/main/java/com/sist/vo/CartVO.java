package com.sist.vo;

import java.util.Date;

import lombok.Data;
@Data
public class CartVO {
	private int no,gno,account,ok;
	private String id,dbday;
	private Date regdate;
	private GoodsVO gvo=new GoodsVO();
}
