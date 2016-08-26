package kr.co.bne.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Component;



@Component
public class NoticeDeliver {
	
	private final static String ip = "http://192.168.1.6";
	private final static int port = 10000;	
	private final static String addr = ip + ":" + port + "/";

	
	
	public void sendNotice(String fromName, String toId, int link_id, String notice_type) throws IOException {
		String urlStr = addr + "notice?fromName=" + URLEncoder.encode(fromName, "utf-8") + "&toId=" + toId + "&notice_type=" + notice_type + "&link_id=" + link_id;
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		String temp = "";
		String result = "";
		while((temp = reader.readLine()) != null) {	
			if(temp.length() > 1) {
				result += temp;
			}
		}
		
		reader.close();
		System.out.println(result);
	}
}
