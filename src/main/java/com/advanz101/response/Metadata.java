/**
 * 
 */
package com.advanz101.response;

import java.io.Serializable;

/**
 * @author 
 *
 */
public class Metadata implements Serializable{

	private String version;
	private String status;
	private String HTTP_status_code;
	private String result_count;
	
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the hTTP_status_code
	 */
	public String getHTTP_status_code() {
		return HTTP_status_code;
	}
	/**
	 * @param hTTP_status_code the hTTP_status_code to set
	 */
	public void setHTTP_status_code(String hTTP_status_code) {
		HTTP_status_code = hTTP_status_code;
	}
	/**
	 * @return the resultCount
	 */
	public String getResultCount() {
		return result_count;
	}
	/**
	 * @param resultCount the resultCount to set
	 */
	public void setResultCount(String resultCount) {
		this.result_count = resultCount;
	}
}
