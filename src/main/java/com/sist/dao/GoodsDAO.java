package com.sist.dao;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSessionFactory;
import com.sist.vo.FoodVO;
import com.sist.vo.GoodsVO;

public class GoodsDAO {
	private static SqlSessionFactory ssf;
	static
	{
		ssf=CreateSessionFactory.getSsf();
	}
	public static List<GoodsVO> goodsListData(Map<String,Object> map)
	{
		List<GoodsVO> list=null;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("goodsListData", map);
			for(GoodsVO vo:list)
			{
			int saleprice=Integer.parseInt(vo.getGoods_price().substring(0,vo.getGoods_price().indexOf("원")).replace(",", ""))*(100-vo.getGoods_discount())/100;;
			String lastprice=new DecimalFormat("#,###").format(saleprice)+"원";
			vo.setLastprice(lastprice);
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			if(session!=null)
			session.close();
		}
		
		return list;
	}
	public static int goodsTotalPage(Map<String,String> map)
	{
		int totalPage =0;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			totalPage=session.selectOne("goodsTotalPage",map);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			if(session!=null)
			session.close();
		}
		
		return totalPage;
	}
	public static GoodsVO goodsDetailData(Map map)
	{
		GoodsVO vo=null;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			session.update("goodsHitIncrement", map);
			session.commit();
			vo=session.selectOne("goodsDetailData",map);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			if(session!=null)
			session.close();
		}
		
		return vo;
	}
	public static GoodsVO goodsCookieData(Map map)
	{
		GoodsVO vo=null;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			vo=session.selectOne("goodsDetailData",map);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			if(session!=null)
			session.close();
		}
		
		return vo;
	}
}
