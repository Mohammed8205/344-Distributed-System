
public class Shuttle extends Thread {
	
	
	Monitor moni;
	
	int capacity;
	
	
	Shuttle(Monitor m){
		
		moni = m;
		
		capacity = 0;
		
	}
	
	
	
	public void run(){
		
		int cycle = 0;
		
		
		while(cycle != 3){ //three times cycling
		try{
		moni.lineUpforFuel(this.getName());
		
		moni.getNumber(this.getName(), capacity);
		
		moni.flyingOut(this.getName());
		}
		catch(Exception e){
			System.out.println(e);
		}
		cycle++;
		}
	}

}
