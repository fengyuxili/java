package com.fxs.io.bio.tomcat.servlet;


import com.fxs.io.bio.tomcat.http.GPRequest;
import com.fxs.io.bio.tomcat.http.GPResponse;
import com.fxs.io.bio.tomcat.http.GPServlet;

public class SecondServlet extends GPServlet {

	public void doGet(GPRequest request, GPResponse response) throws Exception {
		this.doPost(request, response);
	}

	public void doPost(GPRequest request, GPResponse response) throws Exception {
		response.write("This is Second Serlvet");
	}

}
