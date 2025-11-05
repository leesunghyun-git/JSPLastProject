package com.sist.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSessionFactory;
import com.sist.vo.ChefVO;
import com.sist.vo.FoodVO;
import com.sist.vo.RecipeVO;

import java.util.*;

public class MainDAO {
	private static SqlSessionFactory ssf;
	static
	{
		ssf=CreateSessionFactory.getSsf();
	}
	/*
	 * <select id="mainTopData" resultType="FoodVO" parameterType="">
		SELECT *
		FROM (SELECT * FROM menupan_food
		ORDER BY DBMS_RANDOM.VALUES)
		WHERE ROWNUM=1
	</select>
	 * 
	 */
	public static FoodVO mainTopData()
	{
		SqlSession session=null;
		FoodVO vo=new FoodVO();
		try
		{
			session=ssf.openSession();
			vo=session.selectOne("mainTopData");
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return vo;
		
	}
	/*
	 * 
	 * 	<select id="mainHitTop4" resultType="FoodVO">
		SELECT fno,name,poster,address,type,theme,likecount,replycount,hit,ROWNUM
		FROM (SELECT fno,name,poster,address,type,theme,likecount,replycount,hit
		FROM menupan_food
		ORDER BY hit DESC)
		WHERE ROWNUM &lt;=4
	</select>
	<select id="mainLikeTop4" resultType="FoodVO">
		SELECT fno,name,poster,address,type,theme,likecount,replycount,hit,ROWNUM
		FROM (SELECT fno,name,poster,address,type,theme,likecount,replycount,hit
		FROM menupan_food
		ORDER BY likecount DESC)
		WHERE ROWNUM &lt;=4
	</select>
	 */
	public static List<FoodVO> mainHitTop4()
	{
		SqlSession session=null;
		List<FoodVO> list=null;
		try
		{
			session=ssf.openSession();
			list=session.selectList("mainHitTop4");
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return list;
		
	}
	public static List<FoodVO> mainLikeTop4()
	{
		SqlSession session=null;
		List<FoodVO> list=null;
		try
		{
			session=ssf.openSession();
			list=session.selectList("mainLikeTop4");
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return list;
		
	}
	/*
	 * 	<select id="mainChefTopData" resultType="ChefVO">
		SELECT *
		FROM (SELECT * FROM chef
		ORDER BY DBMS_RANDOM.VALUE)
		WHERE ROWNUM=1
	</select>
	<select id="mainRecipeTop5" resultType="RecipeVO">
		SELECT no,poster,title,ROWNUM
		FROM (SELECT no,poster,title
		FROM recipe
		ORDER BY no DESC)
		WHERE ROWNUM &lt;=5
	</select>
	 * 
	 */
	public static ChefVO mainChefTopData()
	{
		SqlSession session=null;
		ChefVO vo=null;
		try
		{
			session=ssf.openSession();
			vo=session.selectOne("mainChefTopData");
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return vo;
	}
	public static List<RecipeVO> mainRecipeTop5()
	{
		SqlSession session=null;
		List<RecipeVO> list=null;
		try
		{
			session=ssf.openSession();
			list=session.selectList("mainRecipeTop5");
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return list;
		
	}
}
