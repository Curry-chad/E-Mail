package com.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bean.Email;
import com.util.DBConnection;
@Controller
@RequestMapping("/email")
public class EmailController {
	@RequestMapping(value="/inbox.do")
	public String inbox(HttpServletRequest request) throws SQLException{
		HttpSession session = request.getSession();  
		String userid=session.getAttribute("userid").toString();
		List<Email> emails=new ArrayList<>();
		if(userid!=null&&!userid.equals("")){
			DBConnection dao=new  DBConnection();
		    Connection conn = dao.getConnection();
		    String sql="select * from email where userid='"+userid+"'";
			PreparedStatement ptmt=conn.prepareStatement(sql);
			ResultSet rs=ptmt.executeQuery();
			while(rs.next()){
				Email email=new Email();
				email.setAddressee_id(rs.getString("addressee_id"));
				email.setAddresser_id(rs.getString("addresser_id"));
				email.setReaded(rs.getInt("readed"));
				email.setTile(rs.getString("title"));
				email.setTime(rs.getString("time"));
				if(rs.getString("filepath")!=null){
					email.setFilepath(rs.getString("filepath"));;
				}
				emails.add(email);
			}
				
		}
		
	    session.setAttribute("in_emails",emails);
		return "inbox";
	}

}
