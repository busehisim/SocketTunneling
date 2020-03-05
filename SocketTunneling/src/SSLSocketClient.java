import java.awt.RenderingHints.Key;
import java.io.*;
import java.net.*;
import java.security.KeyStore;
import javax.net.*;
import javax.net.ssl.*;
import java.security.cert.X509Certificate;
import java.security.cert.CertificateException;
import java.util.Properties;



public class SSLSocketClient extends Thread {
	
	static DataInputStream SSLinput;
	static DataInputStream input ;
	static DataOutputStream SSLoutput;
	static DataOutputStream output;

    public SSLSocketClient(int ListenPort, int DestinationPort, String DestinationIP) throws Exception {

    	
    	Properties systemProps = System.getProperties();
    	systemProps.put("javax.net.ssl.trustStore", "keystore.ImportKey"); // 
    	
    	 SystemTrayDemo clienticonclose = new SystemTrayDemo("clienticonclose.jpg", "Client Not Connected");
    	
    try {
    	
    		System.out.println("Client working");
	    	ServerSocket server = new ServerSocket(ListenPort);	//client listening
	    	Socket socket = server.accept();
	    	
	    	SystemTrayDemo clienticonopen = new SystemTrayDemo("clienticonopen.jpg", "Client Connected");
	    	clienticonclose.CloseTray();
	    	
	    	input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
	    	output = new DataOutputStream(socket.getOutputStream());
	    	
	    			SSLSocketFactory factory = getSSLSocketFactory("TLS");
	        SSLSocket SSLsocket =
	            (SSLSocket)factory.createSocket(DestinationIP, DestinationPort);	//ssl socket
	        
	    	 SSLoutput = new DataOutputStream(SSLsocket.getOutputStream());
	    	 SSLinput = new DataInputStream(new BufferedInputStream(SSLsocket.getInputStream()));
	    	
	    	 MultiThread object = new MultiThread(input, SSLoutput); 
	         MultiThread object2 = new MultiThread(SSLinput, output);    
	    	 
	         object.start(); 
	    	 object2.start(); 
	           
    	}
    	
	catch(Exception e )
    	{
			System.out.println("Exception: " + e); 
		}
    	
    }
     	   
  
	private static SSLSocketFactory getSSLSocketFactory(String type) {
		if (type.equals("TLS")) {
		    SocketFactory ssf = null;
		    try {
			SSLContext ctx;
		        KeyManagerFactory kmf;
		        KeyStore ks;
		        char[] passphrase = "importkey".toCharArray();

		        ctx = SSLContext.getInstance("TLS");
		        kmf = KeyManagerFactory.getInstance("SunX509");
		        ks = KeyStore.getInstance("JKS");

		        ks.load(new FileInputStream("keystore.ImportKey"), passphrase);
		        kmf.init(ks, passphrase);
		     

			ctx.init(kmf.getKeyManagers(), null, null);

		        ssf = ctx.getSocketFactory();
		        return (SSLSocketFactory) ssf;
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		} else {
		    return (SSLSocketFactory) SSLSocketFactory.getDefault();
		}
		return null;
        }
}
