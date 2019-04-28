package servlets;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.bean.Email;

import dao.EmailDao;
 
/**
 * Servlet implementation class UploadHandleServlet
 */
@WebServlet("/upload")
public class UploadHandleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UploadHandleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�õ��ϴ�ʱ���ɵ���ʱ�ļ�����Ŀ¼
		/**
		 * �õ��ϴ��ļ��ı���Ŀ¼�����ַ���
		 * ����1��Ŀ¼������tomcatĿ¼�£�һ��tomcat�����Ŀ�����ļ��ͻ���ʧ����Ϊ����
		 *1��String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		 *����2��AttFilePathλwebxml�������õ�·�����ƣ���Ŀ¼Ϊ�̶�Ӳ��Ŀ¼��������Ϊ��Ŀ�Ƴ�����ʧ���ȶ��ɿ�
		 *2��String savePath = this.getServletContext().getInitParameter("AttFilePath");
		 */		
		//�õ��ϴ��ļ��ı���Ŀ¼
		//String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		//�õ��ϴ��ļ��ı���Ŀ¼
		String tempPath=this.getServletContext().getRealPath("/WEB-INF/temp");
		String title="";
		String addresser_id="";
		String email_content="";
		String userid="";
		String savePath = "E:\\download\\doc_up";
		String saveFilename="";
		String realSavePath="";
		String time="";
		String filename="";
		String type=request.getParameter("type");
		File tmpFile=new File(tempPath);
		//�����ʱ�ļ������ڣ�������ʱĿ¼e
		if(!tmpFile.exists()){
			 tmpFile.mkdir();
		}
        String message = "";
        try{
		 //����һ��DiskFileItemFactory����
		DiskFileItemFactory factory=new DiskFileItemFactory();
		//���û������Ĵ�СΪ1000KB�������ָ������ô�������Ĵ�СĬ����10KB
		factory.setSizeThreshold(1024*100);
		//�����ϴ�ʱ���ɵ���ʱ�ļ��ı���Ŀ¼
		factory.setRepository(tmpFile);
		//����һ���ļ��ϴ�������
        ServletFileUpload upload = new ServletFileUpload(factory);
        //�����ļ��ϴ�����
        upload.setProgressListener(new ProgressListener(){
            public void update(long pBytesRead, long pContentLength, int arg2) {
                //System.out.println("�ļ���СΪ��" + pContentLength + ",��ǰ�Ѵ���" + pBytesRead);
            }
        });
        
        //�����ϴ������ļ��Ĵ�С�����ֵ��Ŀǰ������Ϊ1024*1024�ֽڣ�Ҳ����1MB
        upload.setFileSizeMax(1024*1024*10);
        //�����ϴ��ļ����������ֵ�����ֵ=ͬʱ�ϴ��Ķ���ļ��Ĵ�С�����ֵ�ĺͣ�Ŀǰ����Ϊ10MB
        upload.setSizeMax(1024*1024*20);
        //4��ʹ��ServletFileUpload�����������ϴ����ݣ�����������ص���һ��List<FileItem>���ϣ�ÿһ��FileItem��Ӧһ��Form����������
        List<FileItem> list = upload.parseRequest(request);
        upload.setHeaderEncoding("UTF-8"); 
        for(FileItem item : list){
            //���fileitem�з�װ������ͨ�����������
            if(item.isFormField()){
                String name = item.getFieldName();
                //�����ͨ����������ݵ�������������
                String value = item.getString("UTF-8");
                //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                System.out.println(name + "=" + value);
                if(name.equals("userid")){
                	userid=value;
                }
                if(name.equals("title")){
                	title=value;
                }
                if(name.equals("receiver")){
                	addresser_id=value;
                }
                if(name.equals("email_content")){
                	email_content=value;
                }
            }else{
                //�õ��ϴ����ļ����ƣ�
                filename = item.getName();
                System.out.println(filename);
                if(filename==null || filename.trim().equals("")){
                	
                }
                //����ϴ��ļ�������������
                upload.setHeaderEncoding("UTF-8"); 
                //ע�⣺��ͬ��������ύ���ļ����ǲ�һ���ģ���Щ������ύ�������ļ����Ǵ���·���ģ��磺  c:\a\b\1.txt������Щֻ�ǵ������ļ������磺1.txt
                //�����ȡ�����ϴ��ļ����ļ�����·�����֣�ֻ�����ļ�������
                filename = filename.substring(filename.lastIndexOf("\\")+1);
                /**
                 * ���ϴ����ļ����浽���ݿ�
                 * @author baicai
                 * time�ϴ�ʱ��
                 * filename�ļ���
                 * savePath�ļ�·��
                 * */
    			Date date=new Date();
    		    DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    			time=format.format(date);
            	
                //�õ��ϴ��ļ�����չ��
                String fileExtName = filename.substring(filename.lastIndexOf(".")+1);
                //�����Ҫ�����ϴ����ļ����ͣ���ô����ͨ���ļ�����չ�����ж��ϴ����ļ������Ƿ�Ϸ�
                System.out.println("�ϴ����ļ�����չ���ǣ�"+fileExtName);
                //��ȡitem�е��ϴ��ļ���������
                InputStream in = item.getInputStream();
                //�õ��ļ����������
                saveFilename = makeFileName(title+"."+fileExtName);
                //�õ��ļ��ı���Ŀ¼
               realSavePath = makePath(saveFilename, savePath);
                System.out.println(realSavePath+"\\"+saveFilename);

                
                //����һ���ļ������
                FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFilename);
                //����һ��������
                byte buffer[] = new byte[1024];
                //�ж��������е������Ƿ��Ѿ�����ı�ʶ
                int len = 0;
                //ѭ�������������뵽���������У�(len=in.read(buffer))>0�ͱ�ʾin���滹������
                while((len=in.read(buffer))>0){
                    //ʹ��FileOutputStream�������������������д�뵽ָ����Ŀ¼(savePath + "\\" + filename)����
                    out.write(buffer, 0, len);
                }
                //�ر�������
                in.close();
                //�ر������
                out.close();
                
            }
        }
        EmailDao fileDao=new EmailDao();
        System.out.println("upload:"+email_content);
    	Email email=new Email(userid,addresser_id,title,0,realSavePath+"\\"+saveFilename,time,email_content,filename);
    	fileDao.UpFile(email,type);
        //ɾ�������ļ��ϴ�ʱ���ɵ���ʱ�ļ�
        //item.delete();
//    	 request.setAttribute("userid", userid);
    	 response.sendRedirect("http://localhost:8080/springmvc/user/outbox.do");
        }
        catch (FileUploadBase.FileSizeLimitExceededException e) {
	        e.printStackTrace();
	        request.setAttribute("message", "�����ļ��������ֵ!!!");
	        response.sendRedirect("http://localhost:8080/springmvc/user/inbox.do");
	        return;
	    }catch (FileUploadBase.SizeLimitExceededException e) {
	        e.printStackTrace();
	        request.setAttribute("message", "upload sizeover!!!");
	        response.sendRedirect("http://localhost:8080/springmvc/user/inbox.do");
//	        response.getWriter().println("<script type='text/javascript'>alert('upload sizeover!!!')</script>");
	        return;
	    }catch (Exception e) {
	    	request.setAttribute("message", "upload error!!!");
	        request.getRequestDispatcher("/send.jsp").forward(request, response);
//	    	response.getWriter().println("<script type='text/javascript'>alert('upload error!!!')</script>");
//	        e.printStackTrace();
	    }
}
	 /**
	    * @Method: makeFileName
	    * @Description: �����ϴ��ļ����ļ������ļ����ԣ�uuid+"_"+�ļ���ԭʼ����
	    * @param filename �ļ���ԭʼ����
	    * @return uuid+"_"+�ļ���ԭʼ����
	    */ 
	    private String makeFileName(String filename){  //2.jpg
	        //Ϊ��ֹ�ļ����ǵ���������ҪΪ�ϴ��ļ�����һ��Ψһ���ļ���
	        return UUID.randomUUID().toString() + "_" + filename;
	    }
	    
	    /**
	     * Ϊ��ֹһ��Ŀ¼�������̫���ļ���Ҫʹ��hash�㷨��ɢ�洢
	    * @Method: makePath
	    * @Description:
	    *
	    * @param filename �ļ�����Ҫ�����ļ������ɴ洢Ŀ¼
	    * @param savePath �ļ��洢·��
	    * @return �µĴ洢Ŀ¼
	    */ 
	    private String makePath(String filename,String savePath){	    	
	    	//�����ڵõ��ļ�����
	    	Calendar date=Calendar.getInstance();		 
	 		SimpleDateFormat format1=new SimpleDateFormat( "yyyy-MM-dd"); 
	 		String name=format1.format(date.getTime());
	 		String dir = savePath + "\\" + name;  //upload\2\3  upload\3\5
	 		File file=new File(dir);
	 		//���Ŀ¼������
	        if(!file.exists()){
	            //����Ŀ¼
	            file.mkdirs();
	        }
	        return dir;
	    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
 
}
