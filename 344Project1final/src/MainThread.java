import java.util.Vector;

public class MainThread {
	
	
	public static void main(String[] args){
		
		
		int numShuttle = Integer.parseInt(args[0]);
		int numCharge = Integer.parseInt(args[1]);
		
		Monitor mr = new Monitor(numShuttle,numCharge);
		
		Vector<Shuttle> numPlane = new Vector<Shuttle>();
		
		Controller ctlar = new Controller(mr);
		
		Supervisor sp = new Supervisor(mr);
		
		ctlar.start();
		
		sp.start();
		
		for(int i = 0; i<numShuttle; i++){
			
			numPlane.add(new Shuttle(mr));
			
		}
		
		for(int j = 0; j<numShuttle; j++){
			
			numPlane.get(j).start();
			
		}
		
	}

}
