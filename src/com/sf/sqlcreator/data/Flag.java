package com.sf.sqlcreator.data;

import org.json.simple.JSONObject;

public class Flag {

	private String name;
	private String type;
	private String appliesFor;
	private boolean marketStandard;
	private boolean marketPremium;
	private boolean marketEnterprise;
	private boolean publisherStandard;
	private boolean publisherPremium;
	private boolean publisherEnterprise;
	private String description;
	private String sql;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAppliesFor() {
		return appliesFor;
	}
	public void setAppliesFor(String appliesFor) {
		this.appliesFor = appliesFor;
	}
	public boolean isMarketStandard() {
		return marketStandard;
	}
	public void setMarketStandard(boolean marketStandard) {
		this.marketStandard = marketStandard;
	}
	public boolean isMarketPremium() {
		return marketPremium;
	}
	public void setMarketPremium(boolean marketPremium) {
		this.marketPremium = marketPremium;
	}
	public boolean isMarketEnterprise() {
		return marketEnterprise;
	}
	public void setMarketEnterprise(boolean marketEnterprise) {
		this.marketEnterprise = marketEnterprise;
	}
	public boolean isPublisherStandard() {
		return publisherStandard;
	}
	public void setPublisherStandard(boolean publisherStandard) {
		this.publisherStandard = publisherStandard;
	}
	public boolean isPublisherPremium() {
		return publisherPremium;
	}
	public void setPublisherPremium(boolean publisherPremium) {
		this.publisherPremium = publisherPremium;
	}
	public boolean isPublisherEnterprise() {
		return publisherEnterprise;
	}
	public void setPublisherEnterprise(boolean publisherEnterprise) {
		this.publisherEnterprise = publisherEnterprise;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	

	
}
