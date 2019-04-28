package servlets;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Email;

import dao.EmailDao;
 
/**
 * Servlet implementation class ImgDown
 */
@WebServlet("/downloadFile")
public class FileDown extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /*
     * @see HttpServlet#HttpServlet()
     */
    public FileDown() {
        super();
        // TODO Auto-generated constructor stub
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id")!=null){
			System.out.println(request.getParameter("id"));
		}else{
			System.out.println("!!");
		}
		int id=Integer.parseInt(request.getParameter("id"));
		EmailDao dao=new EmailDao();
		Email email=new Email();
		try {
			email=dao.Findfile(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        File file = new File(email.getFilepath());
        //如果文件不存在
        if(!file.exists()){
        	request.setCharacterEncoding("utf-8");
        	response.getWriter().println("<script type='text/javascript'>alert('您要下载的资源被删除啦！')</script>");
        }
        //处理文件名
       // String realname = fileName.substring(fileName.indexOf("_")+1);
        String realname=email.getFilename();
        //设置响应头，控制浏览器下载该文件
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
        //读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream(email.getFilepath());
        //创建输出流
        OutputStream out = response.getOutputStream();
        //创建缓冲区
        byte buffer[] = new byte[1024];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while((len=in.read(buffer))>0){
            //输出缓冲区的内容到浏览器，实现文件下载
            out.write(buffer, 0, len);
        }
        //关闭文件输入流
        in.close();
        //关闭输出流
        out.close();
    }
    
    /*
    * @Method: findFileSavePathByFileName
    * @Description: 通过文件名和存储上传文件根目录找出要下载的文件的所在路径
    * @param filename 要下载的文件名
    * @param saveRootPath 上传文件保存的根目录，也就是/WEB-INF/upload目录
    * @return 要下载的文件的存储目录
    */
    public String findFileSavePathByFileName(String filename,String saveRootPath){
    	//用日期得到文件名的
    	Calendar date=Calendar.getInstance();		 
 		SimpleDateFormat format1=new SimpleDateFormat( "yyyy-MM-dd"); 
 		String name=format1.format(date.getTime());
 		String dir = saveRootPath + "\\" + name;
 		File file=new File(dir);
 		//如果目录不存在
        if(!file.exists()){
            //创建目录
            file.mkdirs();
        }
        return dir;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
