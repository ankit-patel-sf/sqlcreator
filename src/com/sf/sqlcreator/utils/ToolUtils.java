package com.sf.sqlcreator.utils;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.sf.sqlcreator.data.DataWrapper;

public class ToolUtils {

	public static final String FLAGS_FILE_LOCATION = "/Users/ankit.patel/Synced_Files/dev_related/workspace/SqlCreator/flag_data.csv";
	public static final String BASESQL_FILE_LOCATION = "/Users/ankit.patel/Synced_Files/dev_related/workspace/SqlCreator/marketer_sql_template.txt";
	public static final DataWrapper dataWrapper = new DataWrapper();
	
	public static ArrayList<String> getSpecificRequestParameterNames(HttpServletRequest request, String contains){
		ArrayList<String> listOfNames = new ArrayList<String>();
		Enumeration<String> enumList = request.getParameterNames();
		while(enumList.hasMoreElements()) {
			String tempParamName = enumList.nextElement();
			if(tempParamName.contains(contains)) {
				listOfNames.add(tempParamName);
			}
		}
		
		return listOfNames;
	}
	
	public static String getParameterValueSafely(HttpServletRequest request, String paramName, boolean returnBlank, boolean printValue) {
		String value = request.getParameter(paramName);
		if(value == null && returnBlank) {
			value = "";
		}
		if(printValue && value == null) {
			System.out.println("Parameter: " + paramName + " is NULL");
		}else if(printValue && value != null) {
			System.out.println("Parameter: " + paramName + " , Value: " + value);
		}
		
		if(value != null) {
			value = value.trim();
		}
		
		return value;
	}
	
}
