package com.semanticsquare.thrillio.entities;

import org.apache.commons.lang3.StringUtils;

import com.semanticsquare.thrillio.partner.Shareable;

public class Weblink extends Bookmark implements Shareable{

	/**
	 * @param args
	 */
	private String url;
	private String host;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public String toString() {
		return "Weblink [url=" + url + ", host=" + host + "]";
	}

	@Override
	public boolean isKidFriendlyEligible() {
		// TODO Auto-generated method stub
		if(url.contains("porn")||getTitle().contains("porn")||host.contains("adult")){
			return false;
		}
		return true;
	}

	public String getItemData() {
		StringBuilder builder = new StringBuilder();
		builder.append("<item>");
			builder.append("<type>Weblink</type>");
			builder.append("<title>").append(getTitle()).append("</title>");
			builder.append("<url>").append(url).append("</url>");
			builder.append("<host>").append(host).append("</host>");
		builder.append("</item>");
		return builder.toString();
	}


}
