package com.sist.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSessionFactory;
import java.util.*;
import com.sist.vo.*;

public class BoardReplyDAO {
	private static SqlSessionFactory ssf;
	static
	{
		ssf=CreateSessionFactory.getSsf();
	}
	
	public static List<BoardReplyVO> replyListData(int bno)
	{
		List<BoardReplyVO> list=null;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("replyListData",bno);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			if(session!=null)
				session.close();
		}
		
		
		return list;
	}
	public static int replyCount(int bno)
	{
		int count=0;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			count=session.selectOne("replyCount",bno);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			if(session!=null)
				session.close();
		}
		
		
		return count;
	}
}
