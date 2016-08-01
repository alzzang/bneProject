package kr.co.bne;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetSocket;

public class socketTest {
	
	public static void main(String[] args) {
		
		Vertx vertx = Vertx.factory.vertx();
		
		NetClientOptions options = (new NetClientOptions()).setConnectTimeout(100000);
		NetClient client = vertx.createNetClient(options);
		int port = 3000;
		String ip = "127.0.0.1";
		
		client.connect(port, ip, new Handler<AsyncResult<NetSocket>>() {
			@Override
			public void handle(final AsyncResult<NetSocket> asyncSocket) {
				if(asyncSocket.succeeded()) {
	               System.out.println("연결됐지롱!!!!!");
	               NetSocket socket = asyncSocket.result();
	               
	               socket.handler(new Handler<Buffer>(){
	                    @Override
	                    public void handle(Buffer buffer) {
	                        System.out.println("Received data: " + buffer.length());
	                        System.out.println(buffer.getString(0, buffer.length()));

	                    }
	                });
	            }
			}
		});
	}

}
