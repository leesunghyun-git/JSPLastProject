package com.sist.dao;
import com.sist.commons.*;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;

public class BoardDAO {
	private static SqlSessionFactory ssf;
	static
	{
		ssf=CreateSessionFactory.getSsf();
	}
	/*
	 * <select id="boardListData" resultType="BoardVO" parameterType="int">
			SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,replycount
			FROM mvcBoard
			ORDER BY no DESC
			OFFSET #{start} ROWS FETCH NEXT 10 ROWS ONLY
		</select>
		<select id="boardTotalPage" resultType="int">
			SELECT CEIL(COUNT(*)/10.0) FROM mvcBoard
		</select>
		<insert id="boardInsert" parameterType="BoardVO">
			INSERT INTO mvcBoard(no,name,subject,content,pwd)
			VALUES(
				mb_no_seq.nextval,
				#{name},
				#{subject},
				#{content},
				#{pwd}			
			)
			
		</insert>
	 */
	public static List<BoardVO> boardListData(int start)
	{
		List<BoardVO> list =null;
		SqlSession session = null;
		try {
			session=ssf.openSession();
			list= session.selectList("boardListData",start);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	public static int boardTotalPage()
	{
		int totalPage =0;
		SqlSession session = null;
		try {
			session=ssf.openSession();
			totalPage=session.selectOne("boardTotalPage");
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return totalPage;
	}
	public static void boardInsert(BoardVO vo)
	{
		SqlSession session = null;
		try {
			session=ssf.openSession();
			session.update("boardInsert",vo);
			session.commit();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
	}
	/*
	 * <update id="boardHitIncrement" parameterType="int">
			UPDATE mvcBoard
			SET hit=hit+1
			WHERE no = #{no}
		</update>
		<select id="boardDetailData" resultType="BoardVO" parameterType="int">
			SELECT no,name,subject,content,hit,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') AS dbday,replycount 
			FROM mvcBoard
			WHERE no = #{no}
		</select>
	 * 
	 */
	public static BoardVO boardDetailData(int no)
	{
		BoardVO vo =null;
		SqlSession session = null;
		try {
			session=ssf.openSession();
			session.update("boardHitIncrement",no);
			session.commit();
			vo= session.selectOne("boardDetailData",no);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return vo;
	}
	/*
	 * <select id="boardGetPassword" resultType="string" parameterType="int">
			SELECT pwd
			FROM mvcBoard
			WHERE no = #{no}
		</select>
		<delete id="boardDelete" parameterType="int">
			DELETE FROM mvcBoard
			WHERE no=#{no}
		</delete>
	 */
	public static String boardDelete(int no,String pwd)
	{
		String res="no";
		SqlSession session = null;
		try {
			session=ssf.openSession();
			String db_pwd=session.selectOne("boardGetPassword",no);
			if(db_pwd.equals(pwd))
			{
				session.delete("boardDelete",no);
				session.commit();
				res="yes";
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}		
		return res;
	}
}
