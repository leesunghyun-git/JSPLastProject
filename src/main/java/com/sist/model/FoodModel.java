package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class FoodModel {
	
	@RequestMapping("food/list.do")
	public String food_list(HttpServletRequest request, HttpServletResponse response)
	{
		String page=request.getParameter("page");
		if (page==null)
		{
			page="1";
		}
		int totalPage=FoodDAO.foodTotalPage();
		Map<String,Integer> map=new HashMap<>();
		int rowSize=12;
		int curPage=Integer.parseInt(page);
		int start=(rowSize*curPage)-(rowSize-1);
		int end=(rowSize*curPage);
		map.put("start", start);
		map.put("end", end);	
		List<FoodVO> list=FoodDAO.foodListData(map);
		
		final int BLOCK = 10;
		
		int startPage=((curPage-1)/BLOCK*BLOCK)+1;
		int endPage=((curPage-1)/BLOCK*BLOCK)+BLOCK;
		if(totalPage<endPage)
		{
			endPage=totalPage;
		}
		for(FoodVO vo:list)
		{
			String addr=vo.getAddress();
			addr=addr.substring(0,addr.indexOf(" "));
			vo.setAddress(addr);
		}
		request.setAttribute("list", list);
		request.setAttribute("curPage", curPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		
		
		request.setAttribute("main_jsp", "../food/list.jsp");
		return "../main/main.jsp";
	}
}
