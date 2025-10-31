package com.sist.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSessionFactory;
import com.sist.vo.*;
import java.util.*;
public class FoodDAO {
	/*
	 * <select id="foodListData" resultType="FoodVO" parameterType="hashmpa">
		SELECT fno,name,type,address,poster,likecount,replycount,num
		FROM (SELECT fno,name,type,address,poster,likecount,replycount,rownum as num
		FROM (SELECT fno,name,type,address,poster,likecount,replycount
		FROM menupan_food ORDER BY fno ASC))
		WHERE num BETWEEN #{start} AND #{end}	
	</select>
	<select id="foodTotalPage" resultType="int">
		SELECT CEIL(COUNT(*)/20.0) FROM menupan_food
	</select>
	 */
	private static SqlSessionFactory ssf;
	static
	{
		ssf=CreateSessionFactory.getSsf();
	}
	public static List<FoodVO> foodListData(Map<String,Integer> map)
	{
		List<FoodVO> list=null;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("foodListData", map);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			if(session!=null)
			session.close();
		}
		
		return list;
	}
	public static int foodTotalPage()
	{
		int totalPage =0;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			totalPage=session.selectOne("foodTotalPage");
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			if(session!=null)
			session.close();
		}
		
		return totalPage;
	}
}
