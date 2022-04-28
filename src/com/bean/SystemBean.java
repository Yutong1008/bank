package com.bean;

/**
 * ϵͳ��������
 * @author Administrator
 *
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.Constant;
import com.util.DBO;


public class SystemBean {

	private List list;
	private ResultSet rs;
	
	//update system infomation
	public int updateSystem(String sql){
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate(sql);
			if(i == 1)
				return Constant.SUCCESS;
			else
				return Constant.SYSTEM_ERROR;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	
	//get site infomation
	public List getSystem(){
		String sql = "select * from fz_system ";
		DBO dbo = new DBO();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			rs.next();
			list = new ArrayList();
			list.add(rs.getString(2));
			list.add(rs.getString(3));
			list.add(rs.getString(4));
			list.add(rs.getString(5));
			list.add(rs.getString(6));
			list.add(rs.getString(7));
			list.add(rs.getString(8));
			list.add(rs.getString(9));
			list.add(rs.getString(10));
			list.add(rs.getString(11));
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			dbo.close();
		}
	}
	
	//get site name
	public List getSiteInfo(){
		String sql = "select * from fz_system ";
		DBO dbo = new DBO();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			rs.next();
			list = new ArrayList();
			list.add(rs.getString("sitename")); //0
			list.add(rs.getString("url")); //1
			list.add(rs.getString("keyword")); //2
			list.add(rs.getString("description")); //3
			list.add(rs.getString("email")); //4
			list.add(rs.getString("state")); //5
			list.add(rs.getString("reasons")); //6
			list.add(rs.getString("record")); //7
			list.add(rs.getString("copyright")); //8
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			dbo.close();
		}
	}
	
	//get system dir
	public String getDir(){
		String sql = "select dir from fz_system ";
		DBO dbo = new DBO();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			rs.next();
			String dir = rs.getString(1);
			return dir;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			dbo.close();
		}
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//��ѯ�������
	public List getStopSet(){
		String sql = "select fz_off.reg,fz_off.custom,fz_off.guestbook,fz_off.info,fz_stopreg.stopname,fz_off.classinfo,fz_off.old,fz_off.friend,fz_off.job from fz_off,fz_stopreg ";
		DBO dbo = new DBO();
		dbo.open();
		list = new ArrayList();
		try{
			rs = dbo.executeQuery(sql);
			rs.next();
			list.add(rs.getString(1));//reg
			list.add(rs.getString(2));//custom
			list.add(rs.getString(3));//guestbook
			list.add(rs.getString(4));//info
			list.add(rs.getString(5));//stopname
			list.add(rs.getString(6));//classinfo
			list.add(rs.getString(7));//old
			list.add(rs.getString(8));//friend
			list.add(rs.getString(9));//job
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			dbo.close();
		}
	}
//	�޸�ǰ̨�������
	public int upRegSet(int reg,int custom,int guestbook,int info,int classinfo,int old,int friend,int job, String stopname){
		String sql = "update fz_off set reg='"+reg+"',custom='"+custom+"',guestbook='"+guestbook+"',info='"+info+"',classinfo='"+classinfo+"',old='"+old+"',friend='"+friend+"',job='"+job+"'";
		String sql2 = "update fz_stopreg set stopname='"+stopname+"'";
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate(sql);
			int j = dbo.executeUpdate(sql2);
			if(i == j)
				return Constant.SUCCESS;
			else
				return Constant.SYSTEM_ERROR;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
//	��̨��ҳ��Ѷ��
	public int getNewsCount() { 
		String sql = "select count(*) from  fz_news ";
		 DBO dbo=new DBO();
        dbo.open();
        try { 
            rs = dbo.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        } finally {
            dbo.close();
        }
    }
//	��̨��ҳ����������
	public int getCommentsCount() { 
		String sql = "select count(*) from  fz_renews ";
		 DBO dbo=new DBO();
        dbo.open();
        try { 
            rs = dbo.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        } finally {
            dbo.close();
        }
    }
//	��̨��ҳ����������
	public int getCount() { 
		String sql = "select count(*) from  fz_guestbook ";
		 DBO dbo=new DBO();
        dbo.open();
        try { 
            rs = dbo.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        } finally {
            dbo.close();
        }
    }
//	��̨��ҳ���ѻ�Ա��
	public int getMemberCount() { 
		String sql = "select count(*) from  fz_member ";
		DBO dbo=new DBO();
        dbo.open();
        try { 
            rs = dbo.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        } finally {
            dbo.close();
        }
    }
//	��̨��ҳ��Ƹ��Ϣ��
	public int getJobCount(String str) { 
		String sql = "select count(*) from "+str;
		DBO dbo=new DBO();
        dbo.open();
        try { 
            rs = dbo.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        } finally {
            dbo.close();
        }
    }
//	��̨��ҳ������Ϣ��
	public int getHouseCount(String table) { 
		String sql = "select count(*) from  "+table+" ";//buyhouse salehouse rentin rentout
		DBO dbo=new DBO();
        dbo.open();
        try { 
            rs = dbo.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        } finally {
            dbo.close();
        }
    }
	//�༭��̨Ȩ��
	public int editQuanXian(int id ,String quanxian){
		String sql = "update fz_admin set quanxian='"+quanxian+"' where id='"+id+"' ";
		DBO dbo=new DBO();
        dbo.open();
        try { 
            int i = dbo.executeUpdate(sql);
            if(i == 1){
            	return Constant.SUCCESS;
            }
            else {
            	return Constant.SYSTEM_ERROR;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Constant.SYSTEM_ERROR;
        } finally {
            dbo.close();
        }
	}
	//��ѯȨ��
	public String getQX(int id){
		String sql = "select quanxian from fz_admin where id='"+id+"'";
		DBO dbo=new DBO();
        dbo.open();
        try { 
            rs=dbo.executeQuery(sql);
            rs.next();
            return rs.getString(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            dbo.close();
        }
	}
}
