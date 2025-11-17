package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.*;
import com.sist.vo.*;
public class AdminDAO {
	private static SqlSessionFactory ssf;
	static
	{
		ssf=CreateSessionFactory.getSsf();
		// MyBatis에 설정된 XML파일 읽기
	}
	/*<select id="reserveAdminListData" resultMap="reserveMap">
		SELECT no,ri.fno,id,rday,ri.time,inwon,ok,name,phone,poster
		FROM reserve ri,menupan_food mf
		WHERE ri.fno=mf.fno
		ORDER BY no DESC
	</select>
	 */
	public static List<ReserveVO> reserveAdminListData()
	{
		SqlSession session=null;
		List<ReserveVO> list=null;
		try {
			session=ssf.openSession();
			list=session.selectList("reserveAdminListData");
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally {
			if(session!=null)
				session.close();
		}
		
		return list;
	}
	public static void reserveOK(int no)
	{
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.update("reserveOK",no);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally {
			if(session!=null)
				session.close();
		}
	
	}
}
