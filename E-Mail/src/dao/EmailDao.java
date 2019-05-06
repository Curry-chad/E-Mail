package dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Email;
import com.util.DBConnection;
 
public class EmailDao {
	DBConnection dao=new  DBConnection();
    Connection conn = dao.getConnection();
    Email email;
	/**
	 * 上传文件
	 * */
	public void UpFile(Email email,String type) throws SQLException{
		String sql="";
		if(type.equals("0")){
			sql="insert into email(addressee_id,addresser_id,title,filepath,time,readed,content,filename)values(?,?,?,?,?,?,?,?)";
		}else {
			sql="insert into save(addressee_id,addresser_id,title,filepath,time,readed,content,filename)values(?,?,?,?,?,?,?,?)";
		}
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1, email.getAddressee_id());
		ptmt.setString(2, email.getAddresser_id());
		ptmt.setString(3, email.getTile());
		ptmt.setString(4, email.getFilepath());
		ptmt.setString(5, email.getTime());
		ptmt.setInt(6, 0);
		ptmt.setString(7, email.getContent());
		ptmt.setString(8, email.getFilename());
		ptmt.executeUpdate();
	}
	/**
	 *下载查看文件 
	 * */
	public Email Findfile(int id) throws SQLException{
		String sql="select * from email where id='"+id+"'";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()){
			email=new Email();
			email.setId(id);
			email.setFilepath(rs.getString("filepath"));
			email.setFilename(rs.getString("filename"));
		}
		return email;
	}
}
