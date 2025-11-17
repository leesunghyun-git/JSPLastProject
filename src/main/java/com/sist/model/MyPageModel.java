package com.sist.model;

import java.io.PrintWriter;
import java.util.List;

import org.json.simple.JSONObject;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.CartDAO;
import com.sist.dao.MypageDAO;
import com.sist.dao.ReserveDAO;
import com.sist.vo.CartVO;
import com.sist.vo.ReserveVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyPageModel {
  @RequestMapping("mypage/mypage_main.do")
  public String mypage_main(HttpServletRequest request,HttpServletResponse response)
  {
	  request.setAttribute("curPage","memberInfo");
	  request.setAttribute("mypage_jsp", "../mypage/mypage_home.jsp");
	  request.setAttribute("main_jsp", "../mypage/mypage_main.jsp");
	  return "../main/main.jsp";
  }
  @RequestMapping("mypage/mypage_reserve.do")
  public String mypage_reserve(HttpServletRequest request,HttpServletResponse response)
  {
	  HttpSession session=request.getSession();
	  String id =(String)session.getAttribute("id");
	  List<ReserveVO> rList=MypageDAO.reserveMyPageListData(id);
	  System.out.println(rList.size());
	  request.setAttribute("curPage","reserve");
	  request.setAttribute("list", rList);
	  request.setAttribute("mypage_jsp", "../mypage/mypage_reserve.jsp");
	  request.setAttribute("main_jsp", "../mypage/mypage_main.jsp");
	  return "../main/main.jsp";
  }
  @RequestMapping("mypage/reserve_cancel.do")
  public String reserve_cancel(HttpServletRequest request,
		   HttpServletResponse response)
  {
	  String no=request.getParameter("no");
	  MypageDAO.reserveCancel(Integer.parseInt(no));
	  return "redirect:../mypage/mypage_reserve.do";
  }
  @RequestMapping("mypage/reserve_detail.do")
  public void reserve_detail(HttpServletRequest request,
		   HttpServletResponse response)
  {
	  String no=request.getParameter("no");
	  ReserveVO vo=MypageDAO.reserveOkData(Integer.parseInt(no));
	  JSONObject obj=new JSONObject();
	  obj.put("no", vo.getNo());
	  obj.put("rday", vo.getRday());
	  obj.put("time", vo.getTime());
	  obj.put("inwon", vo.getInwon());
	  obj.put("dbday", vo.getDbday());
	  obj.put("poster", vo.getFvo().getPoster());
	  obj.put("name", vo.getFvo().getName());
	  obj.put("address", vo.getFvo().getAddress());
	  obj.put("phone", vo.getFvo().getPhone());
	  obj.put("parking", vo.getFvo().getParking());
	  obj.put("score", vo.getFvo().getScore());
	  obj.put("type", vo.getFvo().getType());
	  
	  try {
		  response.setContentType("text/plain;charset=UTF-8");
		  PrintWriter out=response.getWriter();
		  out.write(obj.toJSONString());
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
		  
	  }
			  
	  
  }

  @RequestMapping("mypage/mypage_cart.do")
  public String mypage_cart(HttpServletRequest request,
		   HttpServletResponse response)
  {
		HttpSession session=request.getSession();
		String id =(String)session.getAttribute("id");
		
		List<CartVO> list=CartDAO.cartMyListData(id);
		request.setAttribute("curPage", "cart");
		request.setAttribute("list", list);
		request.setAttribute("mypage_jsp", "../mypage/cart_list.jsp");
		request.setAttribute("main_jsp", "../mypage/mypage_main.jsp");
		return "../main/main.jsp";
  }
}