package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);
	public static void main(String[] args) {
		String a="3.9��";
		if(a.contains("��")){
			a=a.replace("��", "0000");
			if(a.contains(".")){
				a=a.replace(".", "");
				a=a.substring(0, a.length()-1);
			}
		}
		System.out.println(a);	
		
		
	}
	
	
}
