package com.zk.order;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderNumGenerator {
	
	private static int count=0;
	
	public String getOrderNum(){
		
		SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		return smt.format(new Date()) + "-" + ++count;
	}
	
}
