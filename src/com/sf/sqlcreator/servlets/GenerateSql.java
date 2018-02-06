package com.sf.sqlcreator.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.sf.sqlcreator.io.IOMethods;
import com.sf.sqlcreator.utils.ToolUtils;

/**
 * Servlet implementation class GenerateSql
 */
@WebServlet("/GenerateSql")
public class GenerateSql extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateSql() {
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
		
		String selectedType = ToolUtils.getParameterValueSafely(request, "mrktpub_form_type", true, true);
		String selectedPackage = ToolUtils.getParameterValueSafely(request, "mrktpub_form_package", true, true);
		
		
		/*
		 * Retrieve Base Sql
		 */
		String baseSql = ToolUtils.dataWrapper.getBaseSql();
		
		/*
		 * Retrieve all org paramters
		 */
		ArrayList<String> orgParameterNames = ToolUtils.getSpecificRequestParameterNames(request, "mrktpub_orgParam");
		ArrayList<String> waffleParameterNames = ToolUtils.getSpecificRequestParameterNames(request, "mrktpub_waffleFlag");
		ArrayList<String> providerParameterNames = ToolUtils.getSpecificRequestParameterNames(request, "mrktpub_dataProvider");
		
		
		ArrayList<String> orgSql = new ArrayList();
		for(int i=0;i<orgParameterNames.size();i++) {
			String tempOrgParam = orgParameterNames.get(i);
			if(request.getParameter(tempOrgParam) != null && request.getParameter(tempOrgParam).trim().equalsIgnoreCase("on")) {
				
				tempOrgParam = tempOrgParam.replace("mrktpub_orgParam_", "");
				String tempSql = ToolUtils.dataWrapper.getSqlCommandByName(tempOrgParam);
				orgSql.add(tempSql);
				
			}
		}
		
		ArrayList<String> waffleSql = new ArrayList();
		for(int i=0;i<waffleParameterNames.size();i++) {
			String tempWaffleParam = waffleParameterNames.get(i);
			if(request.getParameter(tempWaffleParam) != null && request.getParameter(tempWaffleParam).trim().equalsIgnoreCase("on")) {
				
				tempWaffleParam = tempWaffleParam.replace("mrktpub_waffleFlag_", "");
				String tempSql = ToolUtils.dataWrapper.getSqlCommandByName(tempWaffleParam);
				waffleSql.add(tempSql);
				
			}
		}
		
		ArrayList<String> providerSql = new ArrayList();
		for(int i=0;i<providerParameterNames.size();i++) {
			String tempProviderParam = providerParameterNames.get(i);
			if(request.getParameter(tempProviderParam) != null && request.getParameter(tempProviderParam).trim().equalsIgnoreCase("on")) {
				
				tempProviderParam = tempProviderParam.replace("mrktpub_dataProvider_", "");
				String tempSql = ToolUtils.dataWrapper.getSqlCommandByName(tempProviderParam);
				providerSql.add(tempSql);
				
			}
		}
		
		String uuid = ToolUtils.getParameterValueSafely(request, "mrktpub_form_uuid", true, true);
		String namespace = ToolUtils.getParameterValueSafely(request, "mrktpub_form_namespace", true, true);
		String lookback = ToolUtils.getParameterValueSafely(request, "mrktpub_form_lookback", true, true);
		String enabledatastudio = ToolUtils.getParameterValueSafely(request, "mrktpub_form_enabledatastudio_true", true, true);
		String email = ToolUtils.getParameterValueSafely(request, "mrktpub_form_email", true, true);
		
		
		String orgParamSqlStr = "";
		for(int i=0;i<orgSql.size();i++) {
			if(i == orgSql.size()-1) {
				orgParamSqlStr = orgParamSqlStr + orgSql.get(i) + "\n";
			}else {
				orgParamSqlStr = orgParamSqlStr + orgSql.get(i) + "," + "\n";
			}
		}
		
		baseSql = baseSql.replace("[[orgparameters]]", orgParamSqlStr);
		
		String waffleSqlStr = "";
		for(int i=0;i<waffleSql.size();i++) {
			if(i == waffleSql.size()-1) {
				waffleSqlStr = waffleSqlStr + waffleSql.get(i) + "\n";
			}else {
				waffleSqlStr = waffleSqlStr + waffleSql.get(i) + "," + "\n";
			}
		}
		
		baseSql = baseSql.replace("[[waffleflags]]", waffleSqlStr);
		
		String providerSqlStr = "";
		for(int i=0;i<providerSql.size();i++) {
			if(i == providerSql.size()-1) {
				providerSqlStr = providerSqlStr + providerSql.get(i) + "\n";
			}else {
				providerSqlStr = providerSqlStr + providerSql.get(i) + "," + "\n";
			}
		}
		
		baseSql = baseSql.replace("[[dataproviders]]", providerSqlStr);
		
		baseSql = baseSql.replace("[[orguuid]]", uuid);
		baseSql = baseSql.replace("[[namespace]]", namespace);
		baseSql = baseSql.replace("[[lookback]]", lookback);
		baseSql = baseSql.replace("[[linkemail]]", email);
		baseSql = baseSql.replace("[[dataproviders]]", "");
		if(enabledatastudio.equalsIgnoreCase("on")) {
			baseSql = baseSql.replace("[[enabledatastudio_1]]", "");
			baseSql = baseSql.replace("[[enabledatastudio_2]]", "");
		}else {
			baseSql = baseSql.replace("[[enabledatastudio_1]]", "/*");
			baseSql = baseSql.replace("[[enabledatastudio_2]]", "*/");
		}
		
	
		
      
		PrintWriter out = response.getWriter();
		out.print(baseSql);
		out.flush();
		

	}
	
}
