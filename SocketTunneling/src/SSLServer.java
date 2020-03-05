import java.io.*;
import java.net.*;
import java.security.KeyStore;
import javax.net.*;
import javax.net.ssl.*;
import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import javax.security.cert.X509Certificate;

public class SSLServer {
	private static HashMap<Socket, String> hashMap = new HashMap();

	public SSLServer(int ListenPort, int DestinationPort, String DestinationIP) throws Exception {
		int i = 0;
		SystemTrayDemo servericonclose = new SystemTrayDemo("servericonclose.jpg", "Server Not Connected");
		try {
			ServerSocketFactory ssf = SSLServer.getServerSocketFactory("TLS");
			ServerSocket ss = ssf.createServerSocket(ListenPort); 

			while(true) {
				Socket incoming = ss.accept();
				SystemTrayDemo servericonopen = new SystemTrayDemo("servericonopen.jpg", "Server Connected");
				servericonclose.CloseTray();
		    	++i;
				getHashMap().put(incoming, "Client " + i);
				(new ServerHelper(incoming , DestinationPort,  DestinationIP)).start();
			
			}
			
		} catch (Exception var4) {
			var4.printStackTrace();
		}
	
	}

	private static ServerSocketFactory getServerSocketFactory(String type) {
		if (type.equals("TLS")) {
		    SSLServerSocketFactory ssf = null;
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

		        ssf = ctx.getServerSocketFactory();
		        return ssf;
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		} else {
		    return ServerSocketFactory.getDefault();
		}
		return null;
        }

	public static synchronized HashMap<Socket, String> getHashMap() {
		return hashMap;
	}

	public static synchronized void setHashMap(HashMap<Socket, String> hashMap) {
		SSLServer.hashMap = hashMap;
	}
}
