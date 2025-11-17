package com.sist.vo;
import java.util.*;

import lombok.Data;
/*
 * 이름      널?       유형             
------- -------- -------------- 
NO      NOT NULL NUMBER         
TYPE             NUMBER         
ID               VARCHAR2(20)   
NAME    NOT NULL VARCHAR2(51)   
MSG     NOT NULL VARCHAR2(4000) 
REGDATE          DATE           
CNO              NUMBER         

 * 
 */
@Data
public class ReviewVO {
	private int no,type,cno;
	private String id,name,msg,dbday;
	private Date regdate;
}
