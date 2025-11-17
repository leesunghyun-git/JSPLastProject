package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSessionFactory;
import com.sist.dao.*;
import com.sist.vo.*;

public class JjimDAO {
	private static SqlSessionFactory ssf;
	static
	{
		ssf=CreateSessionFactory.getSsf();
	}
	/*
	 * <select id="jjimCheckCount" resultType="int" parameterType="jjimVO">
		SELECT COUNT(*)
		FROM all_jjim
		WHERE id=#{id} AND rno=#{rno} AND type=#{type}
	</select>
	<insert id="jjimInsert" parameterType="JjimVO">
		INSERT INTO all_jjim VALUES(
			aj_jno_seq.nextval,
			#{type},#{rno},#{id}
		)
	</insert>
	 * 
	 */
	public static int jjimCheckCount(JjimVO vo)
	{
		SqlSession session=null;
		int count=0;
		try {
			session=ssf.openSession();
			count=session.selectOne("jjimCheckCount",vo);
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
	public static void jjimInsert(JjimVO vo)
	{
		SqlSession session=null;
		try {
			session=ssf.openSession();
			session.insert("jjimInsert",vo);
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
	/*
	 * 	<select id="jjimFoodListData" resultMap="jjimMap" parameterType="string">
		SELECT jno,aj.type,rno,name,poster
		FROM all_jjim as aj, menupan_food as mf
		WHERE aj.rno=mf.fno
		AND id=#{id} AND aj.type=1;
		ORDER BY jno DESC;
	</select>
	 * 
	 * 
	 */
	//마이페이지에 출력
	public static List<JjimVO> jjimFoodListData(String id)
	{
		List<JjimVO> list=null;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("jjimFoodListData",id);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally {
			if(session!=null)
				session.close();
			
		}
		
		
		
		return list;
	}
	public static void jjimCancel(int jno)
	{
		SqlSession session=null;
		try {
			session=ssf.openSession();
			session.delete("jjimCancel",jno);
			session.commit();
		}catch(Exception ex)
		{
			session.rollback();
			ex.printStackTrace();
		}
		finally {
			if(session!=null)
				session.close();
			
		}
	}
	public static FoodVO foodDetailData(int fno)
	{
		FoodVO vo=null;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			vo=session.selectOne("foodDetailData",fno);
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
