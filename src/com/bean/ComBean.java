package com.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.util.Constant;
import com.util.DBO;

public class ComBean {

	private List list;
	private ResultSet rs = null;
	private int EVERYPAGENUM = 2;
	private int count = -1;
	private int qq = 0;
	 
	//声明时间变量
	String date1=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
	String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
	
	//分页查询所有个人会员
	public void setEVERYPAGENUM(int EVERYPAGENUM){
    	this.EVERYPAGENUM=EVERYPAGENUM;
    }
    public int getMessageCount(String sql) { //得到信息总数
       DBO dbo=new DBO();
       dbo.open();
        try { 
            rs = dbo.executeQuery(sql);
            rs.next();
            count = rs.getInt(1);
            return count;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        } finally {
            dbo.close();
        }
    }
    public int getPageCount() { //得到共多少页（根据每页要显示几条信息）
        if (count % EVERYPAGENUM == 0) {
            return count / EVERYPAGENUM;
        } else {
            return count / EVERYPAGENUM + 1;
        }
    }
    public List getMessage(int page,String sql2,int rr) { //得到每页要显示的信息
        DBO dbo=new DBO();
        dbo.open();
        List list = new ArrayList();
        try {
            rs = dbo.executeQuery(sql2);
            for (int i = 0; i < (page - 1) * EVERYPAGENUM; i++) {
                rs.next();
            }
            for (int t = 0; t < EVERYPAGENUM; t++) {
                if (rs.next()) {
                    qq++;
                    List list2=new ArrayList();
                    for(int cc=1;cc<=rr;cc++){
                    	list2.add(rs.getString(cc));
                    }
    				list.add(list2);
                } else {
                    break; //减少空循环的时间
                }
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            dbo.close();
        }
    }
    
	
	public int comUp(String sql){
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate(sql);
			if(i == 1){
				return Constant.SUCCESS;
			}
			else{
				return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			//dbo.close();
		}
	}
	public List getCom(String sql,int row){
		//String sql = "select * from bank where uid='"+uid+"' order by id desc ";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				List list2 = new ArrayList();
				for(int i=1;i<=row;i++){
					list2.add(rs.getString(i));
				}
				list.add(list2);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			dbo.close();
		}
	}
	public List get1Com(String sql,int row){
		//String sql = "select * from bank where uid='"+uid+"' order by id desc ";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			rs.next();
			for(int i=1;i<=row;i++){
				list.add(rs.getString(i));
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			dbo.close();
		}
	}
	public String getString(String sql){
		//String sql = "select * from bank where uid='"+uid+"' order by id desc ";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			if(rs.next())
			return rs.getString(1);
			else return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			dbo.close();
		}
	}
	public int getInt(String sql){
		//String sql = "select * from bank where uid='"+uid+"' order by id desc ";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			if(rs.next())
			return rs.getInt(1);
			else return 0;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}finally{
			dbo.close();
		}
	}
	public void getBS(List list){
		//String sql = "select * from bank where uid='"+uid+"' order by id desc ";
		DBO dbo = new DBO();
		 
		dbo.open();
		try{
			 
			 if(!list.isEmpty()){
				 for(int i=0;i<list.size();i++){
					 List list2=(List)list.get(i);
					 String str=list2.get(0).toString();
					 //System.out.println(str+">>>>>");
					 String sql="select sum(bs) from bs where member='"+str+"'";
					 //System.out.println(sql+"=======");
					 int count=this.getInt(sql);
					 String sql2="update fz_member set bs="+count+" where username='"+str+"'";
					// System.out.println(sql2+"-------------");
					 this.comUp(sql2);
				 }
			 }
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			dbo.close();
		}
	}
	
	
	
	public void getFS(List list){
		//String sql = "select * from bank where uid='"+uid+"' order by id desc ";
		DBO dbo = new DBO();
		 
		dbo.open();
		try{float sum=0;
			float f=0;
			 if(!list.isEmpty()){
				 for(int i=0;i<list.size();i++){
					 List list2=(List)list.get(i);
					 String str=list2.get(0).toString();
					 List list3=this.getCom("select * from bs where member='"+str+"'  order by id desc", 4);
					 if(!list3.isEmpty()){
							for(int m=0;m<list3.size();m++){
								List pagelist2 =(ArrayList)list3.get(m);
								f=Integer.parseInt(pagelist2.get(2).toString())/10;
								java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#"); 
								df.format(f);
								if(f>10)f=10;
								
								sum+=f;
							}
					 }
//					 //System.out.println(str+">>>>>");
//					 String sql="select sum(bs) from bs where member='"+str+"'";
//					 //System.out.println(sql+"=======");
//					 int count=this.getInt(sql);
					 String sql2="update fz_member set fs="+sum+" where username='"+str+"'";
//					// System.out.println(sql2+"-------------");
					 this.comUp(sql2);
				 }
			 }
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			dbo.close();
		}
	}
	public int getTS(String sj){
		int ts=0;
		try{
			String str[]=sj.split("-");
			
			if(str[1].equals("01"))ts=31;
			else if(str[1].equals("02"))ts=28;
			else if(str[1].equals("03"))ts=31;
			else if(str[1].equals("04"))ts=30;
			else if(str[1].equals("05"))ts=31;
			else if(str[1].equals("06"))ts=30;
			else if(str[1].equals("07"))ts=31;
			else if(str[1].equals("08"))ts=31;
			else if(str[1].equals("09"))ts=30;
			else if(str[1].equals("10"))ts=31;
			else if(str[1].equals("11"))ts=30;
			else if(str[1].equals("12"))ts=31;
			//System.out.println(ts);
			return ts;
		}
		catch(Exception e){
			e.printStackTrace();
			return 30;
		}
	}
}
