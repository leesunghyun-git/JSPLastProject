package com.sist.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSessionFactory;
import com.sist.vo.CartVO;

public class CartDAO {
	private static SqlSessionFactory ssf;
	static 
	{		
		ssf=CreateSessionFactory.getSsf();
	}
	
	public static void cartInsert(CartVO vo)
	{
		SqlSession session=null;
		try {
			session=ssf.openSession();
			int count=session.selectOne("cartCount",vo);
			if(count==0)
			{
				session.insert("cartInsert",vo);
			}
			else
			{
				session.update("cartUpdate",vo);
			}
			session.commit();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			if(session!=null)
				session.close();
		}
	}
	public static List<CartVO> cartMyListData(String id)
	{
		SqlSession session=null;
		List<CartVO> list=null;
		try {
			session=ssf.openSession();
			list=session.selectList("cartMyListData",id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if(session!=null)
				session.close();
		}
		
		return list;
	}
	public static void cartCencel(int no)
	{
		SqlSession session=null;
		try {
			session=ssf.openSession();
			session.delete("cartCencel",no);
			session.commit();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			if(session!=null)
				session.close();
		}
	}
}
