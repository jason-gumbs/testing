import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class Testing
 */
@WebServlet("/Testing")
public class Testing extends HttpServlet {
	
		
		
		    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		        doPost(request,response);
		    }

		    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		        String search = request.getParameter("searchq");

		        PrintWriter out = response.getWriter();
		        response.setContentType("text/html");
		        response.setHeader("Cache-control", "no-cache, no-store");
		        response.setHeader("Pragma", "no-cache");
		        response.setHeader("Expires", "-1");
		        response.setHeader("Access-Control-Allow-Origin", "*");
		        response.setHeader("Access-Control-Allow-Methods", "POST");
		        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		        response.setHeader("Access-Control-Max-Age", "86400");

		        Gson gson = new Gson(); 
		        JsonObject myObj = new JsonObject();

		        register countryInfo =  getSchedule(search);
		        JsonElement countryObj = gson.toJsonTree(countryInfo);
		        if(countryInfo.getDescription() == null){
		            myObj.addProperty("success", false);
		        }
		        else {
		            myObj.addProperty("success", true);
		        }
		        myObj.add("table", countryObj);
		        out.println(myObj.toString());

		        out.close();

		    }

		    //Get Country Information
		    private register  getSchedule(String search) {

		       register Register = new register();
		        Connection conn = null;            
		        PreparedStatement stmt = null;     
		        String sql = null;

		        try {      
		            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
		            conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection(); 

		            sql = "Select * from testtable where description = ?"; 
		            stmt = conn.prepareStatement(sql);
		            stmt.setString(1, search.trim());
		            ResultSet rs = stmt.executeQuery(); 

		            while(rs.next()){ 
		            	Register.setDate_time(rs.getString("date_time").trim());
		            	Register.setDescription(rs.getString("description").trim());
		                
		            }                                                                         

		            rs.close();                                                               
		            stmt.close();                                                             
		            stmt = null;                                                              


		            conn.close();                                                             
		            conn = null;                                                   

		        }                                                               
		        catch(Exception e){System.out.println(e);}                      

		        finally {                                                       
		 
		            if (stmt != null) {                                            
		                try {                                                         
		                    stmt.close();                                                
		                } catch (SQLException sqlex) {                                
		                    // ignore -- as we can't do anything about it here           
		                }                                                             

		                stmt = null;                                            
		            }                                                        

		            if (conn != null) {                                      
		                try {                                                   
		                    conn.close();                                          
		                } catch (SQLException sqlex) {                          
		                    // ignore -- as we can't do anything about it here     
		                }                                                       

		                conn = null;                                            
		            }                                                        
		        }              

		        return Register;

		    }   

		}
	
