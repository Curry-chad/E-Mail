package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Email;
import com.util.DBConnection;

/**
 * user/list.do
 * user/add.do
 * @author yingjie
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@RequestMapping(value="/login.do")
	public String login(String userid,String password,HttpServletRequest request) throws SQLException{
		if(userid!=null&&password!=null&&!userid.equals("")&&!password.equals("")){
			DBConnection dao=new  DBConnection();
		    Connection conn = dao.getConnection();
		    String sql="select * from user where userid='"+userid+"'";
			PreparedStatement ptmt=conn.prepareStatement(sql);
			ResultSet rs=ptmt.executeQuery();
			String username="";
			while(rs.next()){
				if(rs.getString("password").equals(password)){
					username=rs.getString("username");
					HttpSession session = request.getSession();  
				    session.setAttribute("userid",userid);
				    session.setAttribute("username",username);
					return "main";
				}else{
					return "login";
				}
			}
				
		}
		return "login";
	}

	@RequestMapping(value="/inbox.do")
	public String inbox(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException{
		HttpSession session = request.getSession();  
		String userid=session.getAttribute("userid").toString();
		List<Email> emails=new ArrayList<>();
		if(userid!=null&&!userid.equals("")){
			DBConnection dao=new  DBConnection();
		    Connection conn = dao.getConnection();
		    String sql="select * from email where addresser_id='"+userid+"'";
			PreparedStatement ptmt=conn.prepareStatement(sql);
			ResultSet rs=ptmt.executeQuery();
			while(rs.next()){
				Email email=new Email();
				email.setId(rs.getInt("id"));
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
		
	    request.setAttribute("in_emails",emails);
	    request.getRequestDispatcher("../inbox.jsp").forward(request, response);
		return "inbox";
	}
	@RequestMapping(value="/outbox.do")
	public String outbox(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException{
		HttpSession session = request.getSession();  
		String userid=session.getAttribute("userid").toString();
		List<Email> emails=new ArrayList<>();
		if(userid!=null&&!userid.equals("")){
			DBConnection dao=new  DBConnection();
		    Connection conn = dao.getConnection();
		    String sql="select * from email where addressee_id='"+userid+"'";
			PreparedStatement ptmt=conn.prepareStatement(sql);
			ResultSet rs=ptmt.executeQuery();
			while(rs.next()){
				Email email=new Email();
				email.setId(rs.getInt("id"));
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
		
	    request.setAttribute("out_emails",emails);
	    request.getRequestDispatcher("../outbox.jsp").forward(request, response);
		return "outbox";
	}
	@RequestMapping(value="/save.do")
	public String save(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException{
		HttpSession session = request.getSession();  
		String userid=session.getAttribute("userid").toString();
		List<Email> emails=new ArrayList<>();
		if(userid!=null&&!userid.equals("")){
			DBConnection dao=new  DBConnection();
		    Connection conn = dao.getConnection();
		    String sql="select * from save where addressee_id='"+userid+"'";
			PreparedStatement ptmt=conn.prepareStatement(sql);
			ResultSet rs=ptmt.executeQuery();
			while(rs.next()){
				Email email=new Email();
				email.setId(rs.getInt("id"));
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
		
	    request.setAttribute("save_emails",emails);
	    request.getRequestDispatcher("../save.jsp").forward(request, response);
		return "save";
	}
	@RequestMapping(value="/sinbox.do")
	public String s_inbox(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException{
		HttpSession session = request.getSession();  
		String userid=session.getAttribute("userid").toString();
		String title=request.getParameter("stitle");
		String addressee_id=request.getParameter("addressee_id");
		String readed=request.getParameter("readed");
		String sql="select * from email where addresser_id='"+userid+"'";
		if(title!=null&&!title.trim().equals("")){
			sql=sql+" and title like'%"+title+"%'";
		}
		if(addressee_id!=null&&!addressee_id.trim().equals("")){
			sql=sql+" and addressee_id like '%"+addressee_id+"%'";
		}
		if(readed!=null&&!readed.trim().equals("")){
			sql=sql+" and readed='"+readed+"'";
		}
		List<Email> emails=new ArrayList<>();
		if(userid!=null&&!userid.equals("")){
			DBConnection dao=new  DBConnection();
		    Connection conn = dao.getConnection();
			PreparedStatement ptmt=conn.prepareStatement(sql);
			ResultSet rs=ptmt.executeQuery();
			while(rs.next()){
				Email email=new Email();
				email.setId(rs.getInt("id"));
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
		
	    request.setAttribute("in_emails",emails);
	    request.getRequestDispatcher("../inbox.jsp").forward(request, response);
		return "inbox";
	}
	@RequestMapping(value="/friend.do")
	public String friend(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException{
		HttpSession session = request.getSession();  
		String userid=session.getAttribute("userid").toString();
		String sql="select * from friend where userid='"+userid+"'";
		List<Email> emails=new ArrayList<>();
		if(userid!=null&&!userid.equals("")){
			DBConnection dao=new  DBConnection();
		    Connection conn = dao.getConnection();
			PreparedStatement ptmt=conn.prepareStatement(sql);
			ResultSet rs=ptmt.executeQuery();
			while(rs.next()){
				Email email=new Email();
				email.setAddressee_id(rs.getString("friend_id"));
				email.setTile(rs.getString("friend_name"));
				emails.add(email);
			}
				
		}
	    request.setAttribute("friends",emails);
	    request.getRequestDispatcher("../friend.jsp").forward(request, response);
		return "inbox";
	}
}
