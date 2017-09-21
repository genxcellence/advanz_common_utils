package com.advanz101.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public class DefaultApiDTO<T> implements Serializable {
	private static final long serialVersionUID = 6303563343644453315L;

	private Metadata metadata;
	private List<T> data;
	private ApiErrorDTO error;

	/** default constructor **/
	public DefaultApiDTO() {
		super();
	}

	/**
	 * 
	 * @param metadata
	 *            - a {@link com.advanz101.retailer.dto.Metadata Metadata}
	 *            object
	 * @param data
	 *            - a List of objects that extend
	 *            {@link com.cga.stg.api.domain.ApiDomainObject ApiDomainObject}
	 */
	public DefaultApiDTO(Metadata metadata, List<T> data) {
		this.metadata = metadata;
		this.data = data;
	}

	/**
	 * 
	 * @param metadata
	 *            - a {@link com.advanz101.retailer.dto.Metadata Metadata}
	 *            object
	 * @param data
	 *            - a single {@link com.cga.stg.api.domain.ApiDomainObject
	 *            ApiDomainObject} object
	 */
	public DefaultApiDTO(Metadata metadata, T data) {
		List<T> dataList = new ArrayList<T>();
		dataList.add(data);

		this.metadata = metadata;
		this.data = dataList;
	}

	/**
	 * 
	 * @param data
	 *            - a List of objects that extend
	 *            {@link com.cga.stg.api.domain.ApiDomainObject ApiDomainObject}
	 * @param version
	 *            - the API version of the data included
	 * @param httpStatus
	 *            - {@link org.springframework.http.HttpStatus HttpStatus}
	 *            object that represents the status of the request
	 */
	public DefaultApiDTO(List<T> data, String version, HttpStatus httpStatus, String message) {
		if(data != null) {
			this.data = data;
			this.metadata = new Metadata(version,httpStatus.value(), data.size());
		} else {
			this.metadata = new Metadata(version, httpStatus.value(), 0);
//			this.metadata = new Metadata(version,httpStatus.toString(), 0);
			
		}

		if (!HttpStatus.OK.toString().equals(httpStatus.toString())) {
			this.error = new ApiErrorDTO(httpStatus, message);
		}else {
			this.metadata.setMessage(message);
		}
	}

	/**
	 * 
	 * @param data
	 *            - a single {@link com.cga.stg.api.domain.ApiDomainObject
	 *            ApiDomainObject} object
	 * @param version
	 *            - the API version of the data included
	 * @param httpStatus
	 *            - {@link org.springframework.http.HttpStatus HttpStatus}
	 *            object that represents the status of the request
	 */
	public DefaultApiDTO(T data, String version, HttpStatus httpStatus, String message) {
		List<T> dataList = new ArrayList<T>();
		if(data != null) {
			dataList.add(data);
		}

		this.data = dataList;
		this.metadata = new Metadata(version,httpStatus.value(), dataList.size());
		if (!HttpStatus.OK.toString().equals(httpStatus.toString())) {
			this.error = new ApiErrorDTO(httpStatus, message);
		}else {
			this.metadata.setMessage(message);
		}
	}


	
	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public ApiErrorDTO getError() {
		return error;
	}

	public void setError(ApiErrorDTO error) {
		this.error = error;
	}
}
