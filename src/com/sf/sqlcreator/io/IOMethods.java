package com.sf.sqlcreator.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import com.sf.sqlcreator.utils.ToolUtils;

public class IOMethods {

	public static void ReadFlagFile() {
		
		BufferedReader br = null; 
		try {
			
			br = new BufferedReader(new FileReader(ToolUtils.FLAGS_FILE_LOCATION));

			String line = "";
		    while ((line = br.readLine()) != null) {
		        
			    	if(line.startsWith("#")) {
			    		continue;
			    	}
			    	
			    	StringTokenizer st = new StringTokenizer(line, "|");
			    	if(st.countTokens() != 11) {
			    		continue;
			    	}
			    	while(st.hasMoreElements()) {
			    		String name = st.nextToken();
			    		String type = st.nextToken();
			    		String appliesfor = st.nextToken();
			    		boolean marketStandard = Boolean.valueOf(st.nextToken());
			    		boolean marketPremium = Boolean.valueOf(st.nextToken());
			    		boolean marketEnterprise = Boolean.valueOf(st.nextToken());
			    		boolean publisherStandard = Boolean.valueOf(st.nextToken());
			    		boolean publisherPremium = Boolean.valueOf(st.nextToken());
			    		boolean publisherEnterprise = Boolean.valueOf(st.nextToken());
			    		String description = st.nextToken();
			    		String sql = st.nextToken();
			    		
			    		ToolUtils.dataWrapper.addFlag(name, type, appliesfor, marketStandard, marketPremium, marketEnterprise, publisherStandard, publisherPremium, publisherEnterprise, description, sql);
			    	}
		        
		    }
		    
		} catch(FileNotFoundException fnfe){
			System.err.println("FileNotFoundException: " + fnfe.getMessage());
		} catch(IOException ioe){
			System.err.println("IOException: " + ioe.getMessage());
		}finally {
		    try{
		    		br.close();
		    }catch(Exception e) {
		    		System.err.println("Exception while closing BufferredReader");
		    }
		}

	}
	
	public static void ReadBaseSqlFile() {
		
		BufferedReader br = null; 
		StringBuffer sb = new StringBuffer();
		try {
			
			br = new BufferedReader(new FileReader(ToolUtils.BASESQL_FILE_LOCATION));

			String line = "";
		    while ((line = br.readLine()) != null) {
		        
			    sb.append(line + "\n");
//		    	sb.append(line + "</br>");
		    }
		    
		} catch(FileNotFoundException fnfe){
			System.err.println("FileNotFoundException: " + fnfe.getMessage());
		} catch(IOException ioe){
			System.err.println("IOException: " + ioe.getMessage());
		}finally {
		    try{
		    		br.close();
		    }catch(Exception e) {
		    		System.err.println("Exception while closing BufferredReader");
		    }
		}
		
		ToolUtils.dataWrapper.setBaseSql(sb.toString());

	}
	
	
}
