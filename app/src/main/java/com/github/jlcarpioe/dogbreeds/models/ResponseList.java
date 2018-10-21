package com.github.jlcarpioe.dogbreeds.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ResponseList {

	@Expose
	@SerializedName("message")
	private List<String> breeds;

	@Expose
	private String status;


	public ResponseList() {

	}

	public List<String> getList() {
		return breeds;
	}

}
