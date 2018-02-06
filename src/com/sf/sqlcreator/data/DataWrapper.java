package com.sf.sqlcreator.data;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sf.sqlcreator.io.IOMethods;

public class DataWrapper {

	private TreeMap<String, Flag> mapOfFlags = new TreeMap();
	private String BaseSql = "";
	
	public void addFlag(String name, String type, String appliesFor, boolean marketStandard, boolean marketPremium, boolean marketEnterprise, boolean publisherStandard, boolean publisherPremium, boolean publisherEnterprise, String description, String sql) {
		if(mapOfFlags.containsKey(name)) {
			Flag tempFlag = mapOfFlags.get(name);
			tempFlag.setName(name);
			tempFlag.setType(type);
			tempFlag.setAppliesFor(appliesFor);
			tempFlag.setMarketStandard(marketStandard);
			tempFlag.setMarketPremium(marketPremium);
			tempFlag.setMarketEnterprise(marketEnterprise);
			tempFlag.setPublisherStandard(publisherStandard);
			tempFlag.setPublisherPremium(publisherPremium);
			tempFlag.setPublisherEnterprise(publisherEnterprise);
			tempFlag.setDescription(description);
			tempFlag.setSql(sql);
			
			mapOfFlags.put(name, tempFlag);
		}else {
			Flag tempFlag = new Flag();
			tempFlag.setName(name);
			tempFlag.setType(type);
			tempFlag.setAppliesFor(appliesFor);
			tempFlag.setMarketStandard(marketStandard);
			tempFlag.setMarketPremium(marketPremium);
			tempFlag.setMarketEnterprise(marketEnterprise);
			tempFlag.setPublisherStandard(publisherStandard);
			tempFlag.setPublisherPremium(publisherPremium);
			tempFlag.setPublisherEnterprise(publisherEnterprise);
			tempFlag.setDescription(description);
			tempFlag.setSql(sql);
			
			mapOfFlags.put(name, tempFlag);
		}
	}
	
	
	public Flag getFlagByName(String name) {
		if(mapOfFlags.containsKey(name)) {
			Flag tempFlag = mapOfFlags.get(name);
			return tempFlag;
		}else {
			return null;
		}
	}
	
	public String getSqlCommandByName(String name) {
		if(mapOfFlags.containsKey(name)) {
			Flag tempFlag = mapOfFlags.get(name);
			String tempSql = tempFlag.getSql();
			return tempSql;
		}else {
			return null;
		}
	}
	
	public JSONArray toJson(String selectedType, String selectedPackage) {
		JSONArray returnJson = new JSONArray();
		Set<String> keySet = mapOfFlags.keySet();
		Iterator<String> keyIter = keySet.iterator();
		while(keyIter.hasNext()) {
			String key = keyIter.next();
			Flag tempFlag = mapOfFlags.get(key);
			
			String name = tempFlag.getName();
	    		String type = tempFlag.getType();
	    		String appliesfor = tempFlag.getAppliesFor();
	    		boolean marketStandard = tempFlag.isMarketStandard();
	    		boolean marketPremium = tempFlag.isMarketPremium();
	    		boolean marketEnterprise = tempFlag.isMarketEnterprise();
	    		boolean publisherStandard = tempFlag.isPublisherStandard();
	    		boolean publisherPremium = tempFlag.isPublisherPremium();
	    		boolean publisherEnterprise = tempFlag.isPublisherEnterprise();
	    		String description = tempFlag.getDescription();
	    		String sql = tempFlag.getSql();
	    		
			/*
	    		 * Package Logic
	    		 */
	    		String isChecked = "";
	    		boolean isEligibleForDisplay = false;
	    		if(selectedType.equalsIgnoreCase("marketer")) {
	    			
	    			if(appliesfor.toLowerCase().contains("mrkt")) {
	    				isEligibleForDisplay = true;
	    			}
	    			
	    			if(selectedPackage.equalsIgnoreCase("standard") && marketStandard) {
	    				isChecked = "checked";
	    			}else if(selectedPackage.equalsIgnoreCase("premium") && marketPremium) {
	    				isChecked = "checked";
	    			}else if(selectedPackage.equalsIgnoreCase("enterprise") && marketEnterprise) {
	    				isChecked = "checked";
	    			}
	    		}else if(selectedType.equalsIgnoreCase("publisher")) {
	    			if(appliesfor.toLowerCase().contains("pub")) {
	    				isEligibleForDisplay = true;
	    			}
	    			
	    			if(selectedPackage.equalsIgnoreCase("standard") && publisherStandard) {
	    				isChecked = "checked";
	    			}else if(selectedPackage.equalsIgnoreCase("premium") && publisherPremium) {
	    				isChecked = "checked";
	    			}else if(selectedPackage.equalsIgnoreCase("enterprise") && publisherEnterprise) {
	    				isChecked = "checked";
	    			}
	    		}
	    		
	    		if(!isEligibleForDisplay) {
	    			continue;
	    		}
	    		
	    		String defaultvalue = "false";
	    		if(isChecked.equalsIgnoreCase("checked")) {
	    			defaultvalue = "true";
	    		}
	    		
	    		JSONObject tempObj = new JSONObject();
	    		tempObj.put("name", name);
	    		tempObj.put("type", type);
	    		tempObj.put("default", defaultvalue);
	    		tempObj.put("description", description);
	    		tempObj.put("isChecked", isChecked);
	    		tempObj.put("sql", sql);
	    		
	    		returnJson.add(tempObj);
			
		}
		
		
		
		
		return returnJson;
	}
	
	public void setBaseSql(String sqlStr) {
		this.BaseSql = sqlStr;
	}
	
	public String getBaseSql() {
		if(this.BaseSql == null || this.BaseSql.trim().equals("")) {
			IOMethods.ReadBaseSqlFile();
		}
		return this.BaseSql;
	}
}
