package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class library {
	static Properties objprop = new Properties();
	
	public static void ReadFeatureFile() throws FileNotFoundException{
		System.out.println(System.getProperty("user.dir"));
		File Obj = new File(System.getProperty("user.dir")+"//src//test//resources//configurationProperty.properties");
		FileInputStream objFileinputStream= new FileInputStream(Obj);
		try {
			objprop.load(objFileinputStream);
			objprop.get("GmoOnloneURL_SIT");
			System.out.println(objprop.get("GmoOnloneURL_SIT"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
