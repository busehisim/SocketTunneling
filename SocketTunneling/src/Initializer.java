import java.util.Properties;
import java.io.FileInputStream;
import java.util.*;
public class Initializer {

	static Properties config;
	public static void main(String[] args)  throws Exception {
		
		config = new Properties();
		FileInputStream fis;
		fis = new FileInputStream("src\\config.properties"); 
		config.load(fis);
		
		int ListenPort = Integer.parseInt(config.getProperty("ListenPort"));
    	int DestinationPort = Integer.parseInt(config.getProperty("DestinationPort"));
    	String DestinationIP = config.getProperty("DestinationIP");
    	String Client =config.getProperty("client");
    	
    		
    	if(Client.equals("yes")) {
    		 		
    		 SSLSocketClient sllsocketclient = new SSLSocketClient(ListenPort, DestinationPort, DestinationIP);
    	}
    	
    	if(Client.equals("no")) {
    		   		
    		SSLServer sslserver = new SSLServer(ListenPort, DestinationPort,  DestinationIP);
    	}
	}
	
}