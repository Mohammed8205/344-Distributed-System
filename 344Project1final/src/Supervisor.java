
public class Supervisor extends Thread {

	Monitor sp;

	
	Supervisor(Monitor c){
		
		sp = c;
		
	}
	
	public void run(){
		
		int cycle = 0;
		
		while(cycle!= 3){
		
		sp.supervisor(this.getName());
		
		cycle++;
		
		}
		
	}
}
