package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.BoardVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/*
 * 			header.jsp => link
 * 			mapper.xml => SQL
 * 			DAO => 처리
 * 			Model => 브라우저 전송
 * 			해당 JSP에서 화면 출력
 *									 DispatcherServlet(서블릿)
 *										  |
 * 								--------------------
 * 								|				   |
 * 			BoardDAO 		BoardModel ========> JSP출력
 * 				|				|
 * 			  오라클			  브라우저
 * 				|				|
 * 				-----------------
 * 						|
 * 					BoardVO : 게시물 한개에 대한 정보
 * 
 * 
 * 
 */
@Controller
public class BoardModel {
	@RequestMapping("board/list.do")
	public String board_list(HttpServletRequest request,HttpServletResponse response)
	{
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curPage=Integer.parseInt(page);
		
		int rowSize=10;
		int start=(curPage-1)*rowSize;
		int count=BoardDAO.boardTotalPage();
		int totalPage=(int)(Math.ceil(count/10.0));
		count=count-start;
		List<BoardVO> list=BoardDAO.boardListData(start);
		request.setAttribute("list", list);
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("count", count);
		request.setAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		request.setAttribute("main_jsp", "../board/list.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("board/insert.do")
	public String board_insert(HttpServletRequest request,HttpServletResponse response)
	{
		
		request.setAttribute("main_jsp", "../board/insert.jsp");
		return "../main/main.jsp";
	}
	/*
	 * https://www.google.com/search?q=%EC%9E%90%EB%B0%94&oq=%EC%9E%90%EB%B0%94&gs_lcrp=EgZjaHJvbWUyBggAEEUYOTIGCAEQRRg90gEIMTUyN2owajmoAgCwAgA&sourceid=chrome&ie=UTF-8
	 * 
	 */
	@RequestMapping("board/insert_ok.do")
	public String board_insert_ok(HttpServletRequest request,HttpServletResponse response)
	{
		//%EC%9E%90%EB%B0%94
		// 자바
		try {
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex)
		{
			
		}
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		BoardVO vo=new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		BoardDAO.boardInsert(vo);
		
		return "redirect:../board/list.do";
	}
	@RequestMapping("board/detail.do")
	public String board_detail(HttpServletRequest request,HttpServletResponse response)
	{
		String strno=request.getParameter("no");
		String page=request.getParameter("page");
		int no = Integer.parseInt(strno);
		
		BoardVO vo=BoardDAO.boardDetailData(no);
		request.setAttribute("page", page);
		
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../board/detail.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("board/delete.do")
	public void board_delete(HttpServletRequest request,HttpServletResponse response)
	{
		//1. 사용자 전송값 {"no":no,"pwd",pwd}
		//2. => ?no=1&pwd=1234
		//vue => axios => params:{"no":no,"pwd":pwd}
		String no=request.getParameter("no");
		String pwd=request.getParameter("pwd");
		// => DAO로 전송 => 결과값
		String res=BoardDAO.boardDelete(Integer.parseInt(no), pwd);
		try
		{
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out =response.getWriter();
			out.write(res);
		}catch(Exception ex)
		{
			
		}
	}
	@RequestMapping("board/update.do")
	public String board_update(HttpServletRequest request,HttpServletResponse response)
	{
		String no=request.getParameter("no");
		BoardVO vo=BoardDAO.boardUpdateData(Integer.parseInt(no));
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../board/update.jsp");
		return "../main/main.jsp";
	}
}
