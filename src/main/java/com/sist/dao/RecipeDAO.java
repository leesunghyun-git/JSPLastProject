package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.*;
import com.sist.vo.*;
public class RecipeDAO {
	private static SqlSessionFactory ssf;
	static
	{
		ssf=CreateSessionFactory.getSsf();
	}
	
	// 쉐프 목록
	public static List<ChefVO> chefListData(Map map)
	{
		SqlSession session=null;
		List<ChefVO> list=null;
		try {
			session=ssf.openSession();
			list=session.selectList("chefListData",map);
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
	
	public static int chefTotalPage()
	{
		SqlSession session=null;
		int totalPage=0;
		try {
			session=ssf.openSession();
			totalPage=session.selectOne("chefTotalPage");
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			session.close();
		}
		
		return totalPage;
	}
	// 레시피 목록
	public static List<RecipeVO> recipeListData(Map map)
	{
		SqlSession session=null;
		List<RecipeVO> list=null;
		try {
			session=ssf.openSession();
			list=session.selectList("recipeListData",map);
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
	
	public static int recipeTotalPage()
	{
		SqlSession session=null;
		int totalPage=0;
		try {
			session=ssf.openSession();
			totalPage=session.selectOne("recipeTotalPage");
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			session.close();
		}
		
		return totalPage;
	}
	public static int recipeCount()
	{
		SqlSession session=null;
		int count=0;
		try {
			session=ssf.openSession();
			count=session.selectOne("recipeCount");
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			session.close();
		}
		
		return count;
	}
	// 쉐프 상세보기
	
	// 레시피 상세보기
	// 레시피 등록
	// 레시피 검색
}
