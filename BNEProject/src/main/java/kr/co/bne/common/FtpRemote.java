package kr.co.bne.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;

public class FtpRemote {

	public FTPClient connectionServer(String ip, int port, String id, String password) {

		FTPClient ftp = null;
		int reply;

		try {
			ftp = new FTPClient();
			ftp.connect(ip, port);

			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				System.err.println("FTP server refused connection.");
				System.exit(1);
			}
			if (!ftp.login(id, password)) {
				ftp.logout();
				throw new Exception("ftp 서버에 로그인하지 못했습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ftp;
	}

	public boolean sendFtpServer(String ip, int port, String id, String password, String folder, MultipartFile file,
			String filename) {
		boolean isSuccess = false;
		// FTPClient ftp = null;

		FTPClient ftp = connectionServer(ip, port, id, password);

		try {

			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();

			System.out.println(ftp.printWorkingDirectory());
			try {
				ftp.makeDirectory(folder);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ftp.changeWorkingDirectory(folder);
			System.out.println(ftp.printWorkingDirectory());

			File uploadFile = new File(file.getOriginalFilename());
			file.transferTo(uploadFile);
			//FileUtils.deleteQuietly(uploadFile.getParentFile());

			FileInputStream fis = null;
			try {
	
				fis = new FileInputStream(uploadFile);
				isSuccess = ftp.storeFile(filename, fis);
				
			} catch (IOException e) {
				e.printStackTrace();
				isSuccess = false;
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
					}
				}
			} // end try
			ftp.logout();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftp != null && ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException e) {
				}
			}
		}
		return isSuccess;
	}

}
