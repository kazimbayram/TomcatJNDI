package com.github.kazimbayram.tomcat_jdni;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ListRecords
 */
@WebServlet("/ListRecordsWithResourceAnnotation")
public class ListRecordsWithResourceAnnotation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListRecordsWithResourceAnnotation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	@Resource(name = "jdbc/PhoneDB")
	private DataSource ds;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
		// TODO Auto-generated method stub
		try {

			Connection conn = ds.getConnection();
			PrintWriter writer = response.getWriter();
			writer.println("<html><body><table border='1' cellpadding='5'>");

			PreparedStatement stmt = conn.prepareStatement("select id,name,email,phone from contacts");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				writer.println("<tr>");
				writer.printf("<td>%d</td>", rs.getInt("id"));
				writer.printf("<td>%s</td>", rs.getString("name"));
				writer.printf("<td>%s</td>", rs.getString("email"));
				writer.printf("<td>%s</td>", rs.getString("phone"));
				writer.println("</tr>");
			}
			writer.println("</table></body></html>");
			stmt.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
