package com.action;
/**
 * 系统相关管理 网站核心部分
 */
import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.SystemBean;
import com.util.Constant;
import com.util.Filter;

public class SystemServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SystemServlet() {
		super();
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

		response.setContentType(Constant.CONTENTTYPE);
		request.setCharacterEncoding(Constant.CHARACTERENCODING);
		String sysdir = new SystemBean().getDir();
		HttpSession session = request.getSession();
		try{
			String username2 = (String)session.getAttribute("user");
			if(username2 == null){
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			else{
				String method = Filter.escapeHTMLTags(request.getParameter("method").trim());
				SystemBean systembean = new SystemBean();
				if(method.equals("sys")){//update site info
					String sitename = Filter.escapeHTMLTags(request.getParameter("sitename").trim());
					String url = Filter.escapeHTMLTags(request.getParameter("url").trim());
					String keyword = Filter.escapeHTMLTags(request.getParameter("keyword").trim());
					String email = Filter.escapeHTMLTags(request.getParameter("email").trim());
					String state = Filter.escapeHTMLTags(request.getParameter("state").trim());
					String reasons = Filter.escapeHTMLTags(request.getParameter("reasons").trim());
					String dir = Filter.escapeHTMLTags(request.getParameter("dir").trim());
					String description = Filter.escapeHTMLTags(request.getParameter("description").trim());
					String record = Filter.escapeHTMLTags(request.getParameter("record").trim());
					String copyright = request.getParameter("copyright").trim();
					String sql  = "update fz_system set sitename = '"+sitename+"',url = '"+url+"',keyword = '"+keyword+"',description='"+description+"',email = '"+email+"'," +
							"state = '"+state+"',reasons = '"+reasons+"',dir = '"+dir+"',record = '"+record+"',copyright = '"+copyright+"'";
					String admindir = systembean.getDir();
					String strDirPath = request.getSession().getServletContext().getRealPath("/");
					File file1=new File(strDirPath+"\\"+admindir);  
				    File file2=new File(strDirPath+"\\"+dir); 
			        if(file1.exists()){
			           if(file1.renameTo(file2)==true){   //判断file2是否存在
			        	    int flag = systembean.updateSystem(sql);
							if(flag == Constant.SUCCESS){
								request.setAttribute("message", "网站核心设置修改完成，请点击IE上的刷新按钮刷新页面更新设置，否则有些文件可能无法显示！");
								request.getRequestDispatcher(dir+"/system/site.jsp").forward(request, response);
							}
							else{
								request.setAttribute("message", "系统维护中请稍后再试！");
								request.getRequestDispatcher(sysdir+"/system/site.jsp").forward(request, response);
							}
			           }else{
			        	    request.setAttribute("message", "系统维护中请稍后再试！");
							request.getRequestDispatcher(sysdir+"/system/site.jsp").forward(request, response);
			           }
			        }				
				}
				else if(method.equals("editQUANXIAN")){//编辑后台管理权限
					String id = Filter.escapeHTMLTags(request.getParameter("id").trim());
					String qx[] = request.getParameterValues("quanxian");
					String str="";
					if(qx!=null){
						for(int i=0;i<qx.length;i++){
							str+=qx[i].trim()+"/";
						}
					}
					int flag = systembean.editQuanXian(Integer.parseInt(id), str);
					if(flag == Constant.SUCCESS){
						request.setAttribute("message", "操作成功！");
						request.getRequestDispatcher(sysdir+"/system/user.jsp").forward(request, response);
					}
					else{
						request.setAttribute("message", "系统维护中请稍后再试！");
						request.getRequestDispatcher(sysdir+"/system/user.jsp").forward(request, response);
					}
				}
				else if(method.equals("REGSET")){//会员注册、留言、评论、发布信息是否需要审核、关闭后台设置
					int reg = Integer.parseInt(Filter.escapeHTMLTags(request.getParameter("reg").trim()));
					int guestbook = Integer.parseInt(Filter.escapeHTMLTags(request.getParameter("guestbook").trim()));
					int custom = Integer.parseInt(Filter.escapeHTMLTags(request.getParameter("custom").trim()));
					int info = Integer.parseInt(Filter.escapeHTMLTags(request.getParameter("info").trim()));
					int classinfo = Integer.parseInt(Filter.escapeHTMLTags(request.getParameter("classinfo").trim()));
					int old = Integer.parseInt(Filter.escapeHTMLTags(request.getParameter("old").trim()));
					int friend = Integer.parseInt(Filter.escapeHTMLTags(request.getParameter("friend").trim()));
					int job = Integer.parseInt(Filter.escapeHTMLTags(request.getParameter("job").trim()));
					String stopname = Filter.escapeHTMLTags(request.getParameter("stopname").trim());
					int flag = systembean.upRegSet(reg, custom, guestbook, info, classinfo, old, friend,job, stopname);
					if(flag == Constant.SUCCESS){
						request.getRequestDispatcher(sysdir+"/site/stop.jsp").forward(request, response);
					}
					else{
						request.setAttribute("message", "系统维护中，请稍后再试！");
						request.getRequestDispatcher(sysdir+"/site/stop.jsp").forward(request, response);
					}
				}
				else{
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
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
