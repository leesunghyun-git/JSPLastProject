package com.sist.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSessionFactory;
import com.sist.vo.ReserveVO;

public class MypageDAO {
	private static SqlSessionFactory ssf;
	static
	{
		ssf=CreateSessionFactory.getSsf();
		
	}
	public static List<ReserveVO> reserveMyPageListData(String id)
	{
		List<ReserveVO> list=null;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("reserveMyPageListData",id);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		
		return list;
	}
	public static void reserveCancel(int no)
	{
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.delete("reserveCancel",no);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
	}
	public static ReserveVO reserveOkData(int no)
	{
		ReserveVO vo=null;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			vo=session.selectOne("reserveOkData",no);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		
		return vo;
	}
}
