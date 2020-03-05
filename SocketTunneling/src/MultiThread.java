import java.io.DataInputStream;
import java.io.DataOutputStream;

class MultiThread extends Thread 
{ 
	DataInputStream input ;
	DataOutputStream output;
	public MultiThread(DataInputStream in, DataOutputStream out) {
		output=out;
		input=in;
	}
    public void run() 
    { 
    	try {
   		 
   		 int in;
			while((in = input.read()) != -1) {
				System.out.format("%c", in);
				output.write(in);
				output.flush();
			
	    	}
	    	    	     
   	 	}	
   	 catch (Exception e)
   	 {
   	     System.out.println( "Interrupted");
   	 }
   	     System.out.println( " exiting.");
   	     
    } 
}  