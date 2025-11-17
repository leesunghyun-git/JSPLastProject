package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSessionFactory;
import com.sist.vo.ReviewVO;

public class ReviewDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf=CreateSessionFactory.getSsf();
	}
	/*
	 * <select id="reviewListData" resultType="ReviewVO" parameterType="hashmap">
		SELECT no,cno,id,name,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday
		FROM review
		WHERE cno=#{cno} AND type=#{type}
	</select>
	<!-- 리뷰 추가 -->
	<insert id="reviewInsert" parameterType="ReviewVO">
		INSERT INTO review VALUES(
			review_no_seq.nextval,
			#{type},#{id},#{name},#{msg},SYSDATE,#{cno}
		)
	</insert>
	<!-- 리뷰 수정 -->
	<update id="reviewUpdate" parameterType="ReviewVO">
		UPDATE review SET
		msg=#{msg}
		WHERE no=#{no}
	</update>
	<!-- 리뷰 삭제 -->
	<delete id="reviewDelete" parameterType="int">
		DELETE review
		WHERE no=#{no}
	</delete>
	 */
	public  static List<ReviewVO> reviewListData(Map map)
	{
		List<ReviewVO> list=null;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("reviewListData",map);
		}catch(Exception ex)
		{
		ex.printStackTrace();
		}
		finally {
			if(session!=null) session.close();
		}
		return list;
	}
	public  static void reviewInsert(ReviewVO vo)
	{
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.insert("reviewInsert",vo);
		}catch(Exception ex)
		{
		ex.printStackTrace();
		}
		finally {
			if(session!=null) session.close();
		}
	}
	public  static void reviewUpdate(ReviewVO vo)
	{
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.update("reviewUpdate",vo);
		}catch(Exception ex)
		{
		ex.printStackTrace();
		}
		finally {
			if(session!=null) session.close();
		}
	}
	public  static void reviewDelete(int no)
	{
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.delete("reviewDelete",no);
		}catch(Exception ex)
		{
		ex.printStackTrace();
		}
		finally {
			if(session!=null) session.close();
		}
	}
}
