package Server;

public class SubServer extends Thread {
	
	public final String superVisor = "SuperVisor";
	public final String shuttle = "Shuttle";
	public final String control = "control";
	//public static int numberShuttle, numberOfCharge;
	public static Monitor monitor = new Monitor(7, 3);
	public Supervisor sup = new Supervisor(monitor);
	public Shuttle shut = new Shuttle(monitor);
	public Controller controller = new Controller(monitor);
	
	
	public SubServer(String type){
		
		if(type.compareTo(superVisor) == 0){
			
			sup.start();
			
		}
		else if(type.compareTo(shuttle) == 0){
			
			shut.start();
		}
		else if(type.compareToIgnoreCase(control) == 0){
			
			controller.start();
		}
		
	}

}
