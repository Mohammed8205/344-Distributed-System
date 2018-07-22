package Client;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ShuttleClient extends Thread {
	
	public static int numShuttle;
	
	public static int port;
	
	public ShuttleClient(){};
	
	
	public void run(){
		
		Socket superSock = null;
		
		try{
			
		String s = "Shuttle";
			
	    superSock = new Socket("localhost",port);
		
		OutputStream os = superSock.getOutputStream();
		
		OutputStreamWriter writer = new OutputStreamWriter(os);
		
		BufferedWriter bw = new BufferedWriter(writer);
		
		bw.write(s);
		
		System.out.println("Message sent to the server is "+s);
		
		bw.flush();
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			
			try{
				superSock.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
	}
	
	
public static void main(String [] args){
	
	ShuttleClient.numShuttle = Integer.parseInt(args[0]);
		
	ShuttleClient.port = Integer.parseInt(args[1]);
	
	for(int i = 0; i<numShuttle; i++){
		new ShuttleClient().start();
		}
	}

}
