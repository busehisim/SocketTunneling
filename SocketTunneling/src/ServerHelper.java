import java.io.*;
import java.net.*;
import java.security.KeyStore;
import java.util.Arrays;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;

public class ServerHelper extends Thread {
	    private Socket SSLsocket;
	    private DataInputStream inputSSL;
	    private DataInputStream inputsocket;
	    private DataOutputStream outputsocket;
	    private DataOutputStream outputSSL;
	    private int DestinationPort;
	    private String DestinationIP;

	    public ServerHelper(Socket incoming, int DestinationPort, String DestinationIP) {
	        this.SSLsocket = incoming;
	        this.DestinationIP= DestinationIP;
	        this.DestinationPort = DestinationPort;
	   	    	
	    }
	    byte result[] ;
	public void run() {
		try {
				
				Socket	socket = new Socket(DestinationIP,DestinationPort );	
				
				if (socket.isConnected()) {
					System.out.println("Server working");
					System.out.println("Connected");	
				}	
				
				//takes input from socket
				 inputSSL = new DataInputStream(SSLsocket.getInputStream());
				 inputsocket = new DataInputStream(socket.getInputStream());
				
				// sends output to the socket
				outputsocket = new DataOutputStream(socket.getOutputStream());
				outputSSL = new DataOutputStream(SSLsocket.getOutputStream());
			
				
				MultiThread object = new MultiThread(inputSSL, outputsocket); 
		        MultiThread object2 = new MultiThread(inputsocket, outputSSL);    
		    	 
		         object.start(); 
		    	 object2.start(); 
							
			} 
		catch (Exception var6) 
		{
            var6.printStackTrace();
        }
			
		
		}
				 	
}
