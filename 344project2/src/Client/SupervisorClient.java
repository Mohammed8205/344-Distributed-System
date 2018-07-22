package Client;

import java.net.InetAddress;
import java.net.Socket;
import java.io.*;

public class SupervisorClient extends Thread {
	
	public static int port;
	
	
	public SupervisorClient(){};

	
	public void run(){
		
		Socket superSock = null;
		
		try{
			
		String s = "SuperVisor";
			
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
	
	public static void main (String[] args){
		
		SupervisorClient.port = Integer.parseInt(args[0]);
		
		new SupervisorClient().start();
		
		
	}
}
