package com.sist.dao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.*;
import com.sist.vo.*;
public class MemberDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf=CreateSessionFactory.getSsf();
	}
	
	public static int memberIdCheck(String id)
	{
		int count=0;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			count=session.selectOne("memberIdCheck",id);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return count;
	}
	public static void memberInsert(MemberVO vo)
	{
		SqlSession session=null;
		try {
			session=ssf.openSession();
			session.insert("memberInsert",vo);
			session.commit();
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
	
	public static MemberVO memberLogin(String id,String pwd)
	{
		MemberVO vo=new MemberVO();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			int count=session.selectOne("memberIdCheck",id);
			if(count==0)
			{
				vo.setMsg("NOID");
			}
			else
			{
				MemberVO dbvo=session.selectOne("memberInfoData", id);
				if(pwd.equals(dbvo.getPwd()))
				{
					vo.setMsg("OK");
					vo.setId(dbvo.getId());
					vo.setName(dbvo.getName());
					vo.setSex(dbvo.getSex());
					vo.setAddr1(dbvo.getAddr1());
					vo.setAddr2(dbvo.getAddr2());
					vo.setPost(dbvo.getPost());
					vo.setAdmin(dbvo.getAdmin());
					vo.setPhone(dbvo.getPhone());
				}
				else
				{
					vo.setMsg("NOPWD");
				}
				
			}
			
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
