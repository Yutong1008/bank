package com.action;

/**
 * 
 * 
 */
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.ComBean; 
import com.util.Constant;
import com.util.SmartFile;
import com.util.SmartUpload;
public class UpServlet extends HttpServlet {

	private ServletConfig config;
	/**
	 * Constructor of the object.
	 */
	public UpServlet() {
		super();
	}

	final public void init(ServletConfig config) throws ServletException
    {
        this.config = config;  
    }

    final public ServletConfig getServletConfig()
    {
        return config;
    }
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding(Constant.CHARACTERENCODING);
		response.setContentType(Constant.CONTENTTYPE);
		 
		HttpSession session = request.getSession();
		String date=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().getTime());
		String date2=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		try{
			 
			 
				 String method = null; 
				 ComBean cb=new ComBean(); 
				 SmartUpload mySmartUpload = new SmartUpload();//init
				 int count = 0;
				 try{
					 mySmartUpload.initialize(config,request,response);
		             mySmartUpload.upload(); 
		             method = mySmartUpload.getRequest().getParameter("method").trim();
		            if(method.equals("addAD")){//增加 
		            	//String mc="";String bm="";String url="";String cd="";String xh="";String jg="";String sl="";String xx=""; 
		            	String mc=mySmartUpload.getRequest().getParameter("mc");
		    			String url=mySmartUpload.getRequest().getParameter("url");  
		    			String sx=mySmartUpload.getRequest().getParameter("sx");  
		    			SmartFile file = mySmartUpload.getFiles().getFile(0);
		            	String fileExt=file.getFileExt();	            
		            	String path="/pic";
	                    count = mySmartUpload.save(path);
		    			
	                   int flag=cb.comUp("insert into ad(mc,url,link,sx)" +
	                   		"values('"+mc+"','"+url+"','"+path+"/"+file.getFileName()+"' ,'"+sx+"')");
	        				//int flag=pb.addBOOK(id,booktype, name, author, path+"/"+file.getFileName(), isbn, price, num, intro, jyjg,addtime);
	        				if(flag==Constant.SUCCESS){
	        					request.setAttribute("message", "操作成功！");
	        					request.getRequestDispatcher("admin/ad/index.jsp").forward(request, response);
	        				}
	        				else{
	        					request.setAttribute("message", "系统维护中，请稍后再试！");
	        					request.getRequestDispatcher("admin/ad/index.jsp").forward(request, response);
	        				}
	        		 
		                   
						 						
		            }
		            else if(method.equals("upAD")){//修改 
		            	String id=mySmartUpload.getRequest().getParameter("id");
		            	String mc=mySmartUpload.getRequest().getParameter("mc");
		    			String url=mySmartUpload.getRequest().getParameter("url");  
		    			String sx=mySmartUpload.getRequest().getParameter("sx");  
						SmartFile file = mySmartUpload.getFiles().getFile(0);
		            	String fileExt=file.getFileExt();	            
		            	String path="/pic";
	                    count = mySmartUpload.save(path);
	                    int flag=cb.comUp("update ad set mc='"+mc+"' ,link='"+path+"/"+file.getFileName()+"'," +
	                    		"url='"+url+"',sx='"+sx+"'  where id='"+id+"'");
	                    //int flag=pb.upBOOK(id, booktype, name, author, path+"/"+file.getFileName(), isbn, price, num, intro, jyjg,addtime);
	        			if(flag==Constant.SUCCESS){
	        				request.setAttribute("message", "操作成功！");
	        				request.getRequestDispatcher("admin/ad/index.jsp").forward(request, response);
	        			}
	        			else{
	        				request.setAttribute("message", "系统维护中，请稍后再试！");
	        				request.getRequestDispatcher("admin/ad/index.jsp").forward(request, response);
	        			}
		            }
		             
		             
		            else{
		            	request.getRequestDispatcher("error.jsp").forward(request, response);
		            }
		        }catch(Exception ex){
		        	ex.printStackTrace();
		        	//request.getRequestDispatcher("error.jsp").forward(request, response);
		        }
			 
		}catch(Exception e){
			e.printStackTrace();
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
