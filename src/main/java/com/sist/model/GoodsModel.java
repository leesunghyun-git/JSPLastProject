package com.sist.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class GoodsModel {
	private static Map<Integer,String> cnomap=new HashMap<>();
	static {
	cnomap.put(1,"goods_all");
	cnomap.put(2,"goods_new");
	cnomap.put(3,"goods_best");
	cnomap.put(4,"goods_special");
	}
	@RequestMapping("goods/list.do")
	public String goods_list(HttpServletRequest request,HttpServletResponse response) {
		String page=request.getParameter("page");
		String strcno=request.getParameter("cno");
		if (page==null)
		{
			page="1";
		}
		if(strcno==null)
		{
			strcno="1";	
		}
		int cno=Integer.parseInt(strcno);
		String type=cnomap.get(cno);
		Map<String,Object> map=new HashMap<>();
		Map<String,String> pagemap=new HashMap<>();
		Map<Integer,String> tagmap=new HashMap<>();
		tagmap.put(1,"모든 상품");
		tagmap.put(2,"신 상품");
		tagmap.put(3,"베스트 상품");
		tagmap.put(4,"특가 상품");
		String tag=tagmap.get(cno);
		int rowSize=12;
		int curPage=Integer.parseInt(page);
		int start=(rowSize*curPage)-(rowSize-1);
		int end=(rowSize*curPage);
		map.put("start", start);
		map.put("end", end);	
		map.put("goods",type);
		pagemap.put("goods", type);
		int totalPage=GoodsDAO.goodsTotalPage(pagemap);
		List<GoodsVO> list=GoodsDAO.goodsListData(map);
		
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
		request.setAttribute("cno", cno);
		request.setAttribute("tag", tag);
		request.setAttribute("main_jsp", "../goods/list.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("goods/detail.do")
	public String goods_detail(HttpServletRequest request,HttpServletResponse response)
	{
		String page=request.getParameter("page");
		String cno=request.getParameter("cno");
		String no=request.getParameter("no");
		String goods=cnomap.get(Integer.parseInt(cno));
		Map map=new HashMap<>();
		map.put("goods", goods);
		map.put("no", Integer.parseInt(no));
		GoodsVO vo=GoodsDAO.goodsDetailData(map);
		request.setAttribute("page", page);
		request.setAttribute("cno", cno);
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../goods/detail.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("goods/detail_before.do")
	public String goods_detail_before(HttpServletRequest request,HttpServletResponse response)
	{
		String page=request.getParameter("page");
		String cno=request.getParameter("cno");
		String no=request.getParameter("no");

		return "redirect:../goods/detail.do?no="+no+"&page="+page+"&cno="+cno;
	}
}
