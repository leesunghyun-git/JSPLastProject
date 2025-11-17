package com.sist.model;

import java.util.List;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.CartDAO;
import com.sist.vo.CartVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartModel {
	@RequestMapping("cart/cart_insert.do")
	public String cart_insert(HttpServletRequest request,HttpServletResponse response)
	{
		String gno=request.getParameter("gno");
		String account=request.getParameter("account");
		HttpSession session=request.getSession();
		String id =(String)session.getAttribute("id");
		CartVO vo=new CartVO();
		vo.setId(id);
		vo.setGno(Integer.parseInt(gno));
		vo.setAccount(Integer.parseInt(account));
		CartDAO.cartInsert(vo);
;
		return "redirect:../mypage/mypage_cart.do";
	}
	@RequestMapping("cart/cart_cancel.do")
	public String cart_cancel(HttpServletRequest request,HttpServletResponse response)
	{
		String no=request.getParameter("no");
		CartDAO.cartCencel(Integer.parseInt(no));
		return "redirect:../mypage/mypage_cart.do";
	}
	
}
