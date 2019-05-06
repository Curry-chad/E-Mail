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
		//得到上传时生成的临时文件保存目录
		/**
		 * 得到上传文件的保存目录的两种方法
		 * 方法1的目录生成在tomcat目录下，一旦tomcat清除项目，此文件就会消失，不为考虑
		 *1、String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		 *方法2的AttFilePath位webxml里面配置的路径名称，此目录为固定硬盘目录，不会因为项目移除而消失，稳定可靠
		 *2、String savePath = this.getServletContext().getInitParameter("AttFilePath");
		 */		
		//得到上传文件的保存目录
		//String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		//得到上传文件的保存目录
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
		//如果临时文件不存在，创建临时目录e
		if(!tmpFile.exists()){
			 tmpFile.mkdir();
		}
        String message = "";
        try{
		 //创建一个DiskFileItemFactory工厂
		DiskFileItemFactory factory=new DiskFileItemFactory();
		//设置缓冲区的大小为1000KB，如果不指定，那么缓冲区的大小默认是10KB
		factory.setSizeThreshold(1024*100);
		//设置上传时生成的临时文件的保存目录
		factory.setRepository(tmpFile);
		//创建一个文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        //监听文件上传进度
        upload.setProgressListener(new ProgressListener(){
            public void update(long pBytesRead, long pContentLength, int arg2) {
                //System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
            }
        });
        
        //设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
        upload.setFileSizeMax(1024*1024*10);
        //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
        upload.setSizeMax(1024*1024*20);
        //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
        List<FileItem> list = upload.parseRequest(request);
        upload.setHeaderEncoding("UTF-8"); 
        for(FileItem item : list){
            //如果fileitem中封装的是普通输入项的数据
            if(item.isFormField()){
                String name = item.getFieldName();
                //解决普通输入项的数据的中文乱码问题
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
                //得到上传的文件名称，
                filename = item.getName();
                System.out.println(filename);
                if(filename==null || filename.trim().equals("")){
                	
                }
                //解决上传文件名的中文乱码
                upload.setHeaderEncoding("UTF-8"); 
                //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                filename = filename.substring(filename.lastIndexOf("\\")+1);
                /**
                 * 将上传的文件保存到数据库
                 * @author baicai
                 * time上传时间
                 * filename文件名
                 * savePath文件路径
                 * */
    			Date date=new Date();
    		    DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    			time=format.format(date);
            	
                //得到上传文件的扩展名
                String fileExtName = filename.substring(filename.lastIndexOf(".")+1);
                //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
                System.out.println("上传的文件的扩展名是："+fileExtName);
                //获取item中的上传文件的输入流
                InputStream in = item.getInputStream();
                //得到文件保存的名称
                saveFilename = makeFileName(title+"."+fileExtName);
                //得到文件的保存目录
               realSavePath = makePath(saveFilename, savePath);
                System.out.println(realSavePath+"\\"+saveFilename);

                
                //创建一个文件输出流
                FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFilename);
                //创建一个缓冲区
                byte buffer[] = new byte[1024];
                //判断输入流中的数据是否已经读完的标识
                int len = 0;
                //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                while((len=in.read(buffer))>0){
                    //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                    out.write(buffer, 0, len);
                }
                //关闭输入流
                in.close();
                //关闭输出流
                out.close();
                
            }
        }
        EmailDao fileDao=new EmailDao();
        System.out.println("upload:"+email_content);
    	Email email=new Email(userid,addresser_id,title,0,realSavePath+"\\"+saveFilename,time,email_content,filename);
    	fileDao.UpFile(email,type);
        //删除处理文件上传时生成的临时文件
        //item.delete();
//    	 request.setAttribute("userid", userid);
    	 response.sendRedirect("http://localhost:8080/springmvc/user/outbox.do");
        }
        catch (FileUploadBase.FileSizeLimitExceededException e) {
	        e.printStackTrace();
	        request.setAttribute("message", "单个文件超出最大值!!!");
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
	    * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
	    * @param filename 文件的原始名称
	    * @return uuid+"_"+文件的原始名称
	    */ 
	    private String makeFileName(String filename){  //2.jpg
	        //为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
	        return UUID.randomUUID().toString() + "_" + filename;
	    }
	    
	    /**
	     * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
	    * @Method: makePath
	    * @Description:
	    *
	    * @param filename 文件名，要根据文件名生成存储目录
	    * @param savePath 文件存储路径
	    * @return 新的存储目录
	    */ 
	    private String makePath(String filename,String savePath){	    	
	    	//用日期得到文件名的
	    	Calendar date=Calendar.getInstance();		 
	 		SimpleDateFormat format1=new SimpleDateFormat( "yyyy-MM-dd"); 
	 		String name=format1.format(date.getTime());
	 		String dir = savePath + "\\" + name;  //upload\2\3  upload\3\5
	 		File file=new File(dir);
	 		//如果目录不存在
	        if(!file.exists()){
	            //创建目录
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
