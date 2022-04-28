package com.action;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.ComBean;
import com.util.Constant;

public class ComServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ComServlet() {
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
		HttpSession session = request.getSession();
		ComBean cBean = new ComBean();
		String date2=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		String method = request.getParameter("method");
		if(method.equals("addYW")){  //add  
			 String mc = request.getParameter("mc");
			 String xm = request.getParameter("xm"); 
			 String nr = request.getParameter("nr"); 
			 String member=(String)session.getAttribute("member");
				 String sql="insert into yw(mc, xm,nr,sj,member) values('"+mc+"', '"+xm+"', '"+nr+"', '"+date2+"', '"+member+"')";
				int flag = cBean.comUp(sql);
				if(flag == Constant.SUCCESS){  
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("member/yw/index.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("member/yw/index.jsp").forward(request, response); 
				}
			 
			
		}
		else if(method.equals("upYW")){ ///update  
			String id = request.getParameter("id");
			String mc = request.getParameter("mc");
			 String xm = request.getParameter("xm"); 
			 String nr = request.getParameter("nr"); 
				 String sql="update yw set mc='"+mc+"', xm='"+xm+"', nr='"+nr+"'  where id= '"+id+"' ";
				int flag = cBean.comUp(sql);
				if(flag == Constant.SUCCESS){ 
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("member/yw/index.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("member/yw/index.jsp").forward(request, response); 
				}
			 
		}
		else if(method.equals("delYW")){  //del  
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from yw where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("member/yw/index.jsp").forward(request, response); 
			}
			else { 
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("member/yw/index.jsp").forward(request, response); 
			}
		}
		else if(method.equals("delYW2")){  //del  
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from yw where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/yw/index.jsp").forward(request, response); 
			}
			else { 
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/yw/index.jsp").forward(request, response); 
			}
		}
		 
		
		
		
		
		if(method.equals("addBS")){  //add  
			 String member = request.getParameter("member");
			 String bs = request.getParameter("bs");  
				 String sql="insert into bs(member,bs,sj) values('"+member+"', '"+bs+"','"+date2+"')";
				int flag = cBean.comUp(sql);
				if(flag == Constant.SUCCESS){  
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/bs/index.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/bs/index.jsp").forward(request, response); 
				}
			 
			
		}
		else if(method.equals("upBS")){ ///update  
			String id = request.getParameter("id");
			String member = request.getParameter("member");
			 String bs = request.getParameter("bs");  
				 String sql="update bs set member='"+member+"', bs='"+bs+"' where id= '"+id+"' ";
				int flag = cBean.comUp(sql);
				if(flag == Constant.SUCCESS){ 
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/bs/index.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/bs/index.jsp").forward(request, response); 
				}
			 
		}
		else if(method.equals("delBS")){  //del  
			String id = request.getParameter("id"); 
			int flag = cBean.comUp("delete from bs where id='"+id+"'");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/bs/index.jsp").forward(request, response); 
			}
			else { 
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/bs/index.jsp").forward(request, response); 
			}
		}
		else if(method.equals("bfSJK")){  //del  
			//String id = request.getParameter("id"); 
			


			 try {       
		            Runtime rt = Runtime.getRuntime();       
		      
		            // 调用 mysql 的 cmd 
		Process child = rt.exec("C:/Program Files/MySQL/MySQL Server 5.0/bin/mysqldump -uroot -p123  jxkh");// 设置导出编码为utf8。这里必须是utf8       
		                  
		            // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行       
		            InputStream in = child.getInputStream();// 控制台的输出信息作为输入流       
		                              
		            InputStreamReader xx = new InputStreamReader(in, "utf8");// 设置输出流编码为utf8。这里必须是utf8，否则从流中读入的是乱 

		            String inStr;       
		            StringBuffer sb = new StringBuffer("");       
		            String outStr;       
		            // 组合控制台输出信息字符串       
		            BufferedReader br = new BufferedReader(xx);       
		            while ((inStr = br.readLine()) != null) {       
		                sb.append(inStr + "\r\n");       
		            }       
		            outStr = sb.toString();       
		                  
		            // 要用来做导入用的sql目标文件：       
		            FileOutputStream fout = new FileOutputStream(       
		                    "c:/xytx.sql");       
		            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");       
		            writer.write(outStr);       
		            // 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免       
		            writer.flush();       
		      
		            // 别忘记关闭输入输出流       
		            in.close ();       
		             
		xx.close();       
		            br.close();       
		            writer.close();       
		            fout.close();       
		      
		            request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/tj/bf.jsp").forward(request, response);       
		      
		        } catch (Exception e) {       
		            e.printStackTrace(); 
		            request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/tj/bf.jsp").forward(request, response); 
		        }       


			 



			//String sql="SELECT * INTO OUTFILE  'D:\\data\\db_testtemp.txt' fields terminated by ',' from jxkh where std_state='1';Query OK, 1 row affected (0.00 sec)";
			//int flag = cBean.comUp("Backup database jxkh to disk='c:/jxkh_bak.dat' ");
//			int flag = cBean.comUp(sql);
//			if(flag == Constant.SUCCESS){ 
//				request.setAttribute("message", "操作成功！");
//				request.getRequestDispatcher("admin/tj/bf.jsp").forward(request, response); 
//			}
//			else { 
//				request.setAttribute("message", "操作失败！");
//				request.getRequestDispatcher("admin/tj/bf.jsp").forward(request, response); 
//			}
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
