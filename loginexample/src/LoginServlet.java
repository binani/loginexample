
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String userName=request.getParameter("userName");
		String userPass=request.getParameter("userPassWord");
		String userRePass=request.getParameter("userRePassWord");
		
		try {
			String queryString = "SELECT VendorName FROM venderInfo WHERE VendorPass=?";
			connection =ConnectionFactory.getInstance().getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1,userPass);
			resultSet = ptmt.executeQuery();
			//Creating Servlet Context object
			if(resultSet.next() && userName.equalsIgnoreCase(resultSet.getString("VendorName")))
			{
				HttpSession session=request.getSession(true);
				session.setAttribute("loggedVendor", resultSet.getString(1));
				
				ServletContext context=getServletContext();	
				RequestDispatcher dispatcher=context.getRequestDispatcher("/SuccessPage");
				dispatcher.forward(request, response);
				//or you can write whole thing in one line as ........
				
				//getServletContext().getRequestDispatcher("/success").forward(request, response);
				
			}else{
				request.setAttribute("wrongUser",userName);
				
				ServletContext context=getServletContext();	
				RequestDispatcher dispatcher=context.getRequestDispatcher("/Failure");
				dispatcher.forward(request, response);
				
				//or you can write whole thing in one line as ........
				//getServletContext().getRequestDispatcher("/fail").forward(request, response);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
