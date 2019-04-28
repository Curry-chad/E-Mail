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
        //����ļ�������
        if(!file.exists()){
        	request.setCharacterEncoding("utf-8");
        	response.getWriter().println("<script type='text/javascript'>alert('��Ҫ���ص���Դ��ɾ������')</script>");
        }
        //�����ļ���
       // String realname = fileName.substring(fileName.indexOf("_")+1);
        String realname=email.getFilename();
        //������Ӧͷ��������������ظ��ļ�
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
        //��ȡҪ���ص��ļ������浽�ļ�������
        FileInputStream in = new FileInputStream(email.getFilepath());
        //���������
        OutputStream out = response.getOutputStream();
        //����������
        byte buffer[] = new byte[1024];
        int len = 0;
        //ѭ�����������е����ݶ�ȡ������������
        while((len=in.read(buffer))>0){
            //��������������ݵ��������ʵ���ļ�����
            out.write(buffer, 0, len);
        }
        //�ر��ļ�������
        in.close();
        //�ر������
        out.close();
    }
    
    /*
    * @Method: findFileSavePathByFileName
    * @Description: ͨ���ļ����ʹ洢�ϴ��ļ���Ŀ¼�ҳ�Ҫ���ص��ļ�������·��
    * @param filename Ҫ���ص��ļ���
    * @param saveRootPath �ϴ��ļ�����ĸ�Ŀ¼��Ҳ����/WEB-INF/uploadĿ¼
    * @return Ҫ���ص��ļ��Ĵ洢Ŀ¼
    */
    public String findFileSavePathByFileName(String filename,String saveRootPath){
    	//�����ڵõ��ļ�����
    	Calendar date=Calendar.getInstance();		 
 		SimpleDateFormat format1=new SimpleDateFormat( "yyyy-MM-dd"); 
 		String name=format1.format(date.getTime());
 		String dir = saveRootPath + "\\" + name;
 		File file=new File(dir);
 		//���Ŀ¼������
        if(!file.exists()){
            //����Ŀ¼
            file.mkdirs();
        }
        return dir;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
