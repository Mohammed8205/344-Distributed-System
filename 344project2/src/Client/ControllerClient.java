package Client;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ControllerClient extends Thread {
	
	public static int port;
	
	public ControllerClient(){};
	
	public void run(){
		
		Socket superSock = null;
		
		try{
			
		String s = "control";
			
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
		
		ControllerClient.port = Integer.parseInt(args[0]);
		
		new ControllerClient().start();
	}
}
