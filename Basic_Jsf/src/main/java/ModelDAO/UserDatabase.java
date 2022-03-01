/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import BeanClass.UserBean;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Dell
 */
@ManagedBean(name="userBean")
@SessionScoped
public class UserDatabase implements Serializable{
    
    
     private static Connection con;
    static{
        try{
            init(); 
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private static void init() throws Exception  {  
        
        Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/register","root","");
        System.out.print(con);
    }
      
    public static  List<UserBean> getData()throws Exception {
        
        String query="select firstname from userinfo where userId="+2;
        
        System.out.println(query);
        
        List<UserBean> userList = new ArrayList<>() ;
        Statement st=null;
        ResultSet result=null;
       
        try{
           
            st=con.createStatement();
            result=st.executeQuery(query);
                         
            while(result.next()){
                
                UserBean bean=new UserBean();
                
                bean.setUserId(result.getInt("userId"));
                bean.setFirstName(result.getString("firstName"));
                bean.setFatherName(result.getString("fatherName"));
                bean.setEmail(result.getString("email"));
                bean.setAddress(result.getString("address"));
                bean.setDobDate(result.getDate("dob"));
                bean.setGender(result.getString("gender"));
                bean.setImageId(result.getInt("imageId"));
                bean.setJobType(result.getString("jobType"));
                bean.setLanguage(result.getString("language"));
                bean.setMobileNumber(result.getString("mobileNumber"));
                bean.setPassword(result.getString("password"));
                
                userList.add(bean);
            }
            return  userList;
        }finally {
        if(result!=null)result.close();
        if(st!=null)st.close();
        }//try
    }//getUser
}
