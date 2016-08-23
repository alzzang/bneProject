package kr.co.bne;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import junit.framework.TestSuite;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/test/resources/spring/applicationContext.xml"})
@TransactionConfiguration(defaultRollback=true)
public class FtpOutboundChannelAdapterSample {

	@Autowired MessageChannel ftpChannel;
	
	
	/*private String getDestinationLocation() {
		String savedir = "c:/uploaded-files";
		File saveDirFile = new File(savedir);

		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		return "c:/uploaded-files/";
	}
	
	
	public void uploadFiles(String id) throws IOException {
		String fileName = null;
		File file = new File("C:\\Users\\bit-pc\\Downloads\\default.png");
		fileName=saveFileToLocalDisk(file, id);		
	}*/



	private boolean saveFileToLocalDisk()
			throws IOException, FileNotFoundException {
		
		
		File file = new File("C:\\Users\\bit-pc\\Downloads\\default.png");
		
		if(file.exists()) {
			System.out.println("파일 존재!");
		}

		Message<File> message = MessageBuilder.withPayload(file).build();

		System.out.println("messageFile.toString"+message.toString());
		boolean result =ftpChannel.send(message);

		System.out.println("result"+result);

		return result;
	}

	
	@Test
	public void test() throws FileNotFoundException, IOException {
		boolean result = saveFileToLocalDisk();
		System.out.println(result);
	}











	/*private static final Logger LOGGER = LoggerFactory.getLogger(FtpOutboundChannelAdapterSample.class);

	private final File baseFolder = new File("target" + File.separator + "toSend");

	@Test
	public void runDemo() throws Exception{

		ConfigurableApplicationContext ctx =
				new ClassPathXmlApplicationContext("META-INF/spring/integration/FtpOutboundChannelAdapterSample-context.xml");

		MessageChannel ftpChannel = ctx.getBean("ftpChannel", MessageChannel.class);

		baseFolder.mkdirs();

		final File fileToSendA = new File(baseFolder, "a.txt");
		final File fileToSendB = new File(baseFolder, "b.txt");

		final InputStream inputStreamA = FtpOutboundChannelAdapterSample.class.getResourceAsStream("/test-files/a.txt");
		final InputStream inputStreamB = FtpOutboundChannelAdapterSample.class.getResourceAsStream("/test-files/b.txt");

		FileUtils.copyInputStreamToFile(inputStreamA, fileToSendA);
		FileUtils.copyInputStreamToFile(inputStreamB, fileToSendB);

		assertTrue(fileToSendA.exists());
		assertTrue(fileToSendB.exists());

		final Message<File> messageA = MessageBuilder.withPayload(fileToSendA).build();
		final Message<File> messageB = MessageBuilder.withPayload(fileToSendB).build();

		ftpChannel.send(messageA);
		ftpChannel.send(messageB);

		Thread.sleep(2000);

		assertTrue(new File(TestSuite.FTP_ROOT_DIR + File.separator + "a.txt").exists());
		assertTrue(new File(TestSuite.FTP_ROOT_DIR + File.separator + "b.txt").exists());

		LOGGER.info("Successfully transfered file 'a.txt' and 'b.txt' to a remote FTP location.");
		ctx.close();
	}

	@After
	public void cleanup() {
		FileUtils.deleteQuietly(baseFolder);
	}*/

}
