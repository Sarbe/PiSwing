package com.pi.swing.util;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pi.swing.bean.ImagePayLoad;

public class ImageURL {
	public static void main(String[] args) throws Exception {

		getImage(null);

	}

	public static ImagePayLoad getImage(String startWindow) throws Exception {
		URL url;
		String responeOutput = "";
		try {
			
			String urlStr = Config.getKey("piclient.address") + startWindow;
//			if(startWindow.equals("Y")){
//				urlStr += "&startWindow="+startWindow;
//			}
			url = new URL(urlStr);
			URLConnection urlCon = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlCon.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				responeOutput += inputLine;
			}
			in.close();
		} catch (Exception e) {
			//e.printStackTrace();
			System.err.println("Unable to Connect to Client");
			throw e;
		}
		return JSONToScheduleObj(responeOutput);
	}
	 
	public static ImagePayLoad JSONToScheduleObj(String jStr) {
		Gson gson = new Gson();
		TypeToken<ImagePayLoad> token = new TypeToken<ImagePayLoad>() {};
		ImagePayLoad img= gson.fromJson(jStr, token.getType());
		return img;
	}
}
