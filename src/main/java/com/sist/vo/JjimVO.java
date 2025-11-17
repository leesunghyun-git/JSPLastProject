package com.sist.vo;

import java.util.Date;

import lombok.Data;

/*
 * 이름      널?       유형           
------- -------- ------------ 
JNO     NOT NULL NUMBER       
TYPE             NUMBER       
RNO              NUMBER       
ID               VARCHAR2(20) 
REGDATE          DATE         

 * 
 */
@Data
public class JjimVO {
	private int jno,type,rno;
	private String id,dbday;
	private Date regdate;
	private FoodVO fvo;
}
