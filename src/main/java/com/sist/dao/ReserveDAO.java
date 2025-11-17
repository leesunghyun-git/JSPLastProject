package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.*;
import com.sist.vo.*;
import com.sist.commons.*;

public class ReserveDAO {
	private static SqlSessionFactory ssf;
	static
	{
		ssf=CreateSessionFactory.getSsf();
	}
	
	public static List<FoodVO> reserveFoodListData(String type)
	{
		List<FoodVO> list=null;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("reserveFoodListData",type);
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
	/*
	 * 	<select id="reserveDateTimes" resultType="string" parameterType="int">
		SELECT times FROM reserve_date
		FROM reserve_time
		WHERE dno=#{dno}
	</select>
	<select id="reserveTime" resultType="string" parameterType="int">
		SELECT time FROM reserve_time
		WHERE tno=#{tno}
	</select>
	 * 
	 */
	public static String reserveDateTimes(int dno)
	{
		String times=null;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			times=session.selectOne("reserveDateTimes",dno);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			if(session!=null)
				session.close();
		}
		
		return times;
	}
	public static String reserveTime(int fno)
	{
		String time=null;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			time=session.selectOne("reserveTime",fno);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			if(session!=null)
				session.close();
		}
		
		return time;
	}
	public static void reserveInsert(ReserveVO vo)
	{
		SqlSession session=null;
		try {
			session=ssf.openSession();
			session.insert("reserveInsert",vo);
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
