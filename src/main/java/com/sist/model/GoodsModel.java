package com.sist.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		List<GoodsVO> cList=new ArrayList<GoodsVO>();
		if(id!=null)
		{
			Cookie[] cookies=request.getCookies();
			if(cookies!=null)
			{
				for(int i=cookies.length-1;i>=0;i--)
				{
					if(cookies[i].getName().startsWith("goods_"+id))
					{
						String etc=cookies[i].getValue();
						StringTokenizer st=new StringTokenizer(etc,"_");
						String cookieno=st.nextToken();
						String cookiecno=st.nextToken();
						Map cmap=new HashMap<>();
						cmap.put("goods",cnomap.get(cookiecno));
						cmap.put("no",cookieno);
						GoodsVO cvo=GoodsDAO.goodsCookieData(cmap);
						cList.add(cvo);
					}
				}
			}
			request.setAttribute("cList", cList);
		}
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
		String price=vo.getGoods_price();
		price=price.replaceAll("[^0-9]", "");
		vo.setPrice(Integer.parseInt(price));
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
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		Cookie[] cookies=request.getCookies();
		for(int i=cookies.length-1;i>=0;i--)
		{
			if(cookies[i].getName().equals("goods_"+id+"_"+no))
			{
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
				break;
			}
		}
		Cookie cookie=new Cookie("goods_"+id+"_"+no+"_"+cno, no+"_"+cno);
		return "redirect:../goods/detail.do?no="+no+"&page="+page+"&cno="+cno;
	}
}
