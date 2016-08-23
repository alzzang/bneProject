package kr.co.bne;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Component;


@Component
public class FtpClientSample {
	
	private static final String host = "192.168.1.18";
	private static final int port = 8086;
	private static final String id = "bne";
	private static final String password = "bne123";
	
	public static void main(String[] args) {
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(host, port);
			int reply = ftpClient.getReplyCode();
			
			if(!FTPReply.isPositiveCompletion(reply)) {
				System.out.println("서버 연결 거부");
			}else {
				System.out.println("서버 연결 성공!!!!!!!!!");
				boolean login = ftpClient.login(id, password);
				
				if(login == true) {
					System.out.println("로그인도 성공!");
					
					FTPFile[] files = null;
					files = ftpClient.listFiles("/upload");
					
					for(FTPFile file : files) {
						System.out.println(file.getName());
					}
					
					
					boolean logout = ftpClient.logout();
					if(logout == true) {
						System.out.println("로그아웃까지 성공!");
					}else {
						System.out.println("로그아웃 실패!!");
					}
				}else {
					System.out.println("로그인 실패!");
				}
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
