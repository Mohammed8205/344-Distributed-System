package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
	
	private int port;
	
	
	public MainServer(String port){
		
		this.port = Integer.parseInt(port);
		ServerSocket sServer = null;
		Socket socket = null;
		
		try{
		
		sServer = new ServerSocket(this.port);
		
		System.out.println("Server MainServer is up and now listening to port number "+this.port);
		
		while(true){
			
			socket = sServer.accept();
			//will recieve message from client here
			InputStream is = socket.getInputStream();
			
			InputStreamReader reader = new InputStreamReader(is);
			
			BufferedReader br = new BufferedReader(reader);
			
			String message = br.readLine();
			
			System.out.println("From Client "+message);

			SubServer sub = new SubServer(message);
			
			//once we have the messsage we create a subserver thread with of that type
		}
		
		
		}
		catch(Exception e){
			System.out.println(e);
			
		}
		finally{
			try{
				socket.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		//sServer.close();
	}
	
	
	public static void main(String []args){
		
		//SubServer.numberShuttle=Integer.parseInt(args[1]);
		//SubServer.numberOfCharge=Integer.parseInt(args[2]);
		new MainServer(args[0]);
		
	}

}
