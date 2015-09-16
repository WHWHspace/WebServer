package connector;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by YBH on 2015/9/16.
 */
public class HttpConnector implements Runnable{

    boolean stopped;
    private String scheme = "http";

    public String getScheme(){
        return scheme;
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try{
            serverSocket = new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            System.exit(1);
        }

        while(!stopped){
            Socket socket = null;

            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                continue;
            }

            HttpProcessor processor = new HttpProcessor(this);
            processor.process(socket);
        }
    }

    public void start(){
        Thread thread = new Thread(this);
        thread.start();
    }
}