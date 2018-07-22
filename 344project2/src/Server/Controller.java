package Server;

public class Controller extends Thread {
	
	
	Monitor mr;
	
	
	Controller(Monitor m){
		
		mr = m;
		
	}
	
	
	
	public void run(){
		
		int lp = 0;
		
		while(lp!= 3){
		
		mr.cArrived(this.getName());
		
		lp++;
	
		
		}
		
	}

}
