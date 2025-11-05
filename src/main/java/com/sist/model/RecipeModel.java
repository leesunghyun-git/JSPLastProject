package com.sist.model;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.text.DecimalFormat;
import java.util.*;
@Controller
public class RecipeModel {
	// 쉐프 출력
	@RequestMapping("recipe/chef_list.do")
	public String chef_list(HttpServletRequest request,HttpServletResponse response)
	{
		String page=request.getParameter("page");
		if (page==null)
		{
			page="1";
		}
		Map<String,Object> map=new HashMap<>();
		int rowSize=12;
		int curPage=Integer.parseInt(page);
		int start=(rowSize*curPage)-(rowSize-1);
		int end=(rowSize*curPage);
		map.put("start", start);
		map.put("end", end);	
		int totalPage=RecipeDAO.chefTotalPage();
		List<ChefVO> list=RecipeDAO.chefListData(map);
		
		final int BLOCK = 10;
		
		int startPage=((curPage-1)/BLOCK*BLOCK)+1;
		int endPage=((curPage-1)/BLOCK*BLOCK)+BLOCK;
		if(totalPage<endPage)
		{
			endPage=totalPage;
		}
		
		request.setAttribute("list", list);
		request.setAttribute("curPage", curPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		
		request.setAttribute("main_jsp", "../recipe/chef_list.jsp");
		return "../main/main.jsp";
	}
	// 레시피 목록
	@RequestMapping("recipe/list.do")
	public String recipe_list(HttpServletRequest request,HttpServletResponse response)
	{
		String page=request.getParameter("page");
		if (page==null)
		{
			page="1";
		}
		Map<String,Object> map=new HashMap<>();
		int rowSize=12;
		int curPage=Integer.parseInt(page);
		int start=(rowSize*curPage)-(rowSize-1);
		int end=(rowSize*curPage);
		map.put("start", start);
		map.put("end", end);	
		int totalPage=RecipeDAO.recipeTotalPage();
		List<RecipeVO> list=RecipeDAO.recipeListData(map);
		int count=RecipeDAO.recipeCount();
		String strcount=new DecimalFormat("#,###").format(count);
		final int BLOCK = 10;
		
		int startPage=((curPage-1)/BLOCK*BLOCK)+1;
		int endPage=((curPage-1)/BLOCK*BLOCK)+BLOCK;
		if(totalPage<endPage)
		{
			endPage=totalPage;
		}
		
		request.setAttribute("list", list);
		request.setAttribute("curPage", curPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("count", strcount);
		request.setAttribute("main_jsp", "../recipe/list.jsp");
		return "../main/main.jsp";
	}
	// 상세보기
	@RequestMapping("recipe/detail.do")
	public String recipe_detail(HttpServletRequest request,HttpServletResponse response)
	{
		
		
		request.setAttribute("main_jsp", "../recipe/detail.jsp");
		return "../main/main.jsp";
	}
}
