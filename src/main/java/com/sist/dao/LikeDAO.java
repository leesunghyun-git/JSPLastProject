package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;
import com.sist.commons.*;
public class LikeDAO {
	private static SqlSessionFactory ssf;
	static
	{
		ssf=CreateSessionFactory.getSsf();
	}
	/*
	 <insert id="likeOn" parameterType="hashmap">
    INSERT INTO all_like 
    VALUES(
      al_lno_seq.nextval,
      #{type},
      #{rno},
      #{id}
    )
  </insert>
  <delete id="likeOff" parameterType="hashmap">
    DELETE FROM all_like
    WHERE rno=#{rno} AND id=#{id} AND type=#{type}
  </delete>
  <update id="likeCountIncrement" parameterType="hashmap">
    UPDATE ${table} SET
    likecount=likecount+1
    WHERE ${checks}=#{rno}
  </update>
  <update id="likeCountDecrement" parameterType="hashmap">
    UPDATE ${table} SET
    likecount=likecount-1
    WHERE ${checks}=#{rno}
  </update>
  <select id="likeCount" parameterType="hashmap" resultType="int">
   SELECT NVL(likecount,0) as likecount
   FROM ${table} 
   WHERE ${checks}=#{rno}
  </select>
  <select id="likeCheck" resultType="int" parameterType="hashmap">
   SELECT COUNT(*) FROM all_like 
   WHERE rno=#{rno} AND type=#{type} 
   AND id=#{id}
  </select>
	 * 
	 * 
	 */
	public static int LikeOn(Map map)
	{
		SqlSession session=null;
		int count=0;
		try {
			session=ssf.openSession();
			session.delete("likeOn",map);
			session.update("likeCountIncrement",map);
			session.commit();
			count=session.selectOne("likeCount",map);
		}catch(Exception ex)
		{
			session.rollback();
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return count;
	}
	public static int LikeOff(Map map)
	{
		SqlSession session=null;
		int count=0;
		try {
			session=ssf.openSession();
			session.insert("likeOff",map);
			session.update("likeCountDecrement",map);
			session.commit();
			count=session.selectOne("likeCount",map);
		}catch(Exception ex)
		{
			session.rollback();
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return count;
	}
	public static int likeCheck(Map map)
	{
		SqlSession session=null;
		int count=0;
		try {
			session=ssf.openSession();
			count=session.selectOne("likeCheck",map);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally {
			if(session!=null)
				session.close();
		}
		return count;
	}
}
