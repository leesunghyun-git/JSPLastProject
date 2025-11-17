package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.ReviewDAO;
import com.sist.vo.ReviewVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class ReviewModel {
	@RequestMapping("review/review_insert.do")
	public String review_insert(HttpServletRequest request,HttpServletResponse response)
	{
		
		try {
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		String msg=request.getParameter("msg");
		String cno=request.getParameter("cno");
		String page=request.getParameter("page");
		String type=request.getParameter("type");
		ReviewVO vo=new ReviewVO();
		vo.setCno(Integer.parseInt(cno));
		vo.setMsg(msg);
		vo.setType(Integer.parseInt(type));
		System.out.println("여기까지 됐음");
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		String name=(String)session.getAttribute("name");
		vo.setId(id);
		vo.setName(name);
		ReviewDAO.reviewInsert(vo);
		String url="";
		if(Integer.parseInt(type)==1)
		{
			url="redirect:../food/detail.do?fno="+cno+"&page="+page;
		}
		else
		{
			url="redirect:../recipe/detail.do?no="+cno+"&page="+page;
		}
		return url;
	}
	@RequestMapping("review/review_delete.do")
	public String review_delete(HttpServletRequest request,HttpServletResponse response)
	{
		String no=request.getParameter("no");
		String cno=request.getParameter("cno");
		String page=request.getParameter("page");
		String type=request.getParameter("type");		
		ReviewDAO.reviewDelete(Integer.parseInt(no));
		String url="";
		if(Integer.parseInt(type)==1)
		{
			url="redirect:../food/detail.do?fno="+cno+"&page="+page;
		}
		else
		{
			url="redirect:../recipe/detail.do?no="+cno+"&page="+page;
		}
		return url;
	}
	@RequestMapping("review/review_update.do")
	public String review_update(HttpServletRequest request,HttpServletResponse response)
	{
		
		try {
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		String msg=request.getParameter("msg");
		String cno=request.getParameter("cno");
		String page=request.getParameter("page");
		String type=request.getParameter("type");
		String no=request.getParameter("no");
		ReviewVO vo=new ReviewVO();
		vo.setNo(Integer.parseInt(no));
		vo.setMsg(msg);
		HttpSession session=request.getSession();
		ReviewDAO.reviewUpdate(vo);
		String url="";
		if(Integer.parseInt(type)==1)
		{
			url="redirect:../food/detail.do?fno="+cno+"&page="+page;
		}
		else
		{
			url="redirect:../recipe/detail.do?no="+cno+"&page="+page;
		}
		return url;
	}
	
}
