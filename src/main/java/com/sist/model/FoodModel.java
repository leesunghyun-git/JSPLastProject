package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.io.PrintWriter;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sist.dao.*;
import com.sist.vo.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import oracle.sql.json.OracleJsonArray;

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
		
		HttpSession session=request.getSession();
		String id =(String)session.getAttribute("id");
		List<FoodVO> cList=new ArrayList<FoodVO>();
		if(id!=null)
		{
			Cookie[] cookies=request.getCookies();
			if(cookies!=null)
			{
				for(int i=cookies.length-1;i>=0;i--)
				{
					if(cookies[i].getName().startsWith("food_"+id+"_"))
					{
						String fno=cookies[i].getValue();
						FoodVO vo=FoodDAO.foodCookieData(Integer.parseInt(fno));
						cList.add(vo);
					}
				}
			}
			request.setAttribute("cList", cList);
		}
		request.setAttribute("main_jsp", "../food/list.jsp");
		return "../main/main.jsp";
	}
	/*
	 * 	Spring => JSP
	 * 	| include / forward => Request를 공유
	 *  | sendRedirect => request가 초기화
	 *  | MVC => 모든 화면 DispactherServlet이다
	 *  				---------------------
	 *  				=> .do
	 *  				=> 화면 변경 => HTML만 덮어쓰고 있다
	 * 
	 */
	@RequestMapping("food/detail.do")
	public String food_detail(HttpServletRequest request, HttpServletResponse response)
	{
		
		int fno=Integer.parseInt(request.getParameter("fno"));
		FoodVO vo=FoodDAO.foodDetailData(fno);
		String page=request.getParameter("page");
		request.setAttribute("vo", vo);
		request.setAttribute("page", page);
		// String food_detail(int fno)
		// String food_detail(foodVO vo)
		// => 스프링 : 전송 객체 / 사용자 요청 값
		// => request는 사용하지 않는다
		
		JjimVO jvo=new JjimVO();
		jvo.setRno(fno);
		jvo.setType(1);
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		jvo.setId(id);
		if(id!=null)
		{
			int jcount=JjimDAO.jjimCheckCount(jvo);
			System.out.println(jcount);
			request.setAttribute("jCount", jcount);
		}
		Map map=new HashMap<>();
		map.put("cno", fno);
		map.put("type", 1);
		List<ReviewVO> reList=ReviewDAO.reviewListData(map);
		request.setAttribute("rCount", reList.size());
		request.setAttribute("reList", reList);
		
		request.setAttribute("main_jsp", "../food/detail.jsp");
		return "../main/main.jsp";
	}
	// food_ // goods_ // recipe_
	@RequestMapping("food/detail_before.do")
	public String food_detail_before(HttpServletRequest request, HttpServletResponse response)
	{
		String strfno=request.getParameter("fno");
		int fno=Integer.parseInt(strfno);
		String page=request.getParameter("page");
		// cookie
		// 1. 로그인된 상태
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		if(id!=null)
		{
			Cookie[] cookies=request.getCookies();
			if(cookies!=null)
			{
				for(int i=0;i<cookies.length;i++)
				{
					if(cookies[i].getName().equals("food_"+id+"_"+strfno))
					{
						cookies[i].setMaxAge(0);
						response.addCookie(cookies[i]);
						break;
					}
				}
			}
			Cookie cookie=new Cookie("food_"+id+"_"+fno, strfno);
			cookie.setMaxAge(60*60*24);
			response.addCookie(cookie);
		}
		// 2. 쿠키 저장
		
		if(page==null)
		{
			page="0";
		}		
		return "redirect:../food/detail.do?fno="+fno+"&page="+page;
	}
	@RequestMapping("food/find.do")
	public String food_find(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("main_jsp", "../food/find.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("food/find_ajax.do")
	public void food_find_ajax(HttpServletRequest request, HttpServletResponse response)
	{
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curPage=Integer.parseInt(page);
		String[] types=request.getParameterValues("type");
		String column=request.getParameter("column");
		String fd=request.getParameter("fd");
		System.out.println(column);
		System.out.println(fd);
		Map map=new HashMap<>();
		int rowSize=12;
		int start=(rowSize*curPage)-(rowSize-1);
		int end=rowSize*curPage;
		map.put("start", start);
		map.put("end",end);
		map.put("column",column);
		map.put("fdArr", types);
		map.put("ss", fd);
		List<FoodVO> list=FoodDAO.foodFindData(map);
		int count=FoodDAO.foodFindCount(map);
		final int BLOCK=10;
		int startPage=((curPage-1)/BLOCK*BLOCK)+1;
		int endPage=((curPage-1)/BLOCK*BLOCK)+BLOCK;
		int totalPage=(int)(Math.ceil(count/12.0));
		if(endPage>totalPage)
		{
			endPage=totalPage;
		}
		int i=0;
		JSONObject result=new JSONObject();
		JSONArray arr=new JSONArray();
		for(FoodVO vo:list)
		{
			//fno,name,type,address,poster,likecount,replycount
			JSONObject obj=new JSONObject();
			obj.put("fno", vo.getFno());
			obj.put("name", vo.getName());
			obj.put("type", vo.getType());
			String s=vo.getAddress();
			s=s.substring(0,s.indexOf(" "));
			vo.setAddress(s);
			obj.put("address", vo.getAddress());
			obj.put("poster", vo.getPoster());
			obj.put("likecount", vo.getLikecount());
			obj.put("replycount", vo.getReplycount());
			arr.add(obj);
		}
		result.put("list", arr);
		result.put("startPage",startPage);
		result.put("endPage", endPage);	
		result.put("count", count);
		result.put("totalPage", totalPage);
		try {
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(result.toJSONString());
		}catch(Exception ex)
		{
			
		}
	}
}
