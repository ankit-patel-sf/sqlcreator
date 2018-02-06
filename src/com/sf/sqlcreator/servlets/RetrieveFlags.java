package com.sf.sqlcreator.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sf.sqlcreator.io.IOMethods;
import com.sf.sqlcreator.utils.ToolUtils;

/**
 * Servlet implementation class RetrieveFlags
 */
@WebServlet("/RetrieveFlags")
public class RetrieveFlags extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveFlags() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProcessRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProcessRequest(request, response);
	}
	
	protected void ProcessRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String selectedType = ToolUtils.getParameterValueSafely(request, "selectedType", true, true);
		String selectedPackage = ToolUtils.getParameterValueSafely(request, "selectedPackage", true, true);
		if(selectedType == null) {
			selectedType = "";
		}
		if(selectedPackage == null) {
			selectedPackage = "";
		}
		
		/*
		 * Retrieve the JSON to be sent back to the browser
		 */
		IOMethods.ReadFlagFile();
		JSONArray returnJson = ToolUtils.dataWrapper.toJson(selectedType, selectedPackage);
		
		response.setContentType("application/json");
      
		PrintWriter out = response.getWriter();
		out.print(returnJson);
		out.flush();
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
