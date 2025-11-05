package com.sist.vo;

import lombok.Data;

@Data
public class RecipeVO {
	private int no,hit,likecount,jjimcount,replycount;
	private String title,poster,chef,link,year,month,day;
	
}
