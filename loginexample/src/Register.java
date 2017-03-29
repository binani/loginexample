import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	PreparedStatement psmt = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
        String name = request.getParameter("userName");
        String pass = request.getParameter("userPassWord");
        String repass = request.getParameter("userRePassWord");
        try{
        
        //loading drivers for mysql
        //Class.forName("com.mysql.jdbc.Driver");

	//creating connection with the database 
         // Connection  con=DriverManager.getConnection
                    // ("jdbc:mysql://localhost:3306/java_demo","root","admin");
        con =ConnectionFactory.getInstance().getConnection();
        PreparedStatement ps=con.prepareStatement
                  ("insert into venderInfo values(?,?,?)");

        ps.setString(1, name);
        ps.setString(2, pass);
        ps.setString(3, repass);
        int i=ps.executeUpdate();
        
          if(i>0)
          {
        	
        	out.println("<center>");
      		out.println("<body bgcolor=lightgreen><h1>");
      		out.println(request.getAttribute("wrongUser"));
      		out.println("You are sucessfully registered <br></h1>");
      		out.println("<a href="+"index.html"+">Try to login Here </a>");
      		out.println("</center>");
      		out.println("</body>");  
            
            
            
          }
        
        }
        catch(Exception se)
        {
            se.printStackTrace();
        }
	
      }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		doGet(request, response);    
	}
}