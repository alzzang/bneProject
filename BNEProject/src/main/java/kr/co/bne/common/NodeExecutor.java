package kr.co.bne.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.springframework.stereotype.Component;

/*@Component*/
public class NodeExecutor {

	private int port = 10000;
	
	public NodeExecutor() {		
		Runtime.getRuntime().exec("node ");
	}
	
	public static void main(String[] args) {
		NodeExecutor ex = new NodeExecutor();
	}
	
}
