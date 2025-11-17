package com.sist.vo;

import java.text.DecimalFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/*
 *  이름                                      널?      유형
 ----------------------------------------- -------- ----------------------------
 NO                                                 NUMBER(38)
 GOODS_NAME                                         VARCHAR2(4000)
 GOODS_SUB                                          VARCHAR2(4000)
 GOODS_PRICE                                        VARCHAR2(128)
 GOODS_DISCOUNT                                     NUMBER(38)
 GOODS_FIRST_PRICE                                  VARCHAR2(128)
 GOODS_DELIVERY                                     VARCHAR2(128)
 GOODS_POSTER                                       VARCHAR2(4000)
 HIT                                                NUMBER(38)
 * 
 */
@Getter
@Setter
public class GoodsVO {
	private int no,goods_discount,hit,price;
	private String goods_name,goods_price,goods_sub,goods_first_price,goods_delivery,goods_poster;
	private String lastprice;
	/*

	 */
	
	
	
	
}
