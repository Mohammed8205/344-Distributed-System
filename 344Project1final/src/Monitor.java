import java.util.Random;

public class Monitor {

	
	int nShut;
	int chargeAmount;
	public static Object ob1;
	public static int lineUP;
	public static Object control;
	Random rand = new Random();
	public static int fuel = 200;
	public static Object mutual;
	public boolean done;
	public static int hide;
	public static Object obj2;
	public static int blocked;
	public boolean runways[];
	public static Object runwayQ;
	public static Object superV;
	public static int leftShuttle;
	public static int willUse;
	public static int allLeft;
	public static long time = System.currentTimeMillis();
	
	
	Monitor(int shuttles, int charg){
		
		nShut = shuttles;
		chargeAmount = charg;
		ob1 = new Object();
		control = new Object();
		mutual = new Object();
		done = false;
		hide = 0;
		obj2 = new Object();
		blocked = 0;
		runways = new boolean[3];
		runwayQ = new Object();
		superV = new Object();
		willUse = 0;
		allLeft = 0;
	}
	
	
	public void lineUpforFuel(String s ){
		
		try{
			
		System.out.println("["+(System.currentTimeMillis()-time)+"]"+"Shuttle "+s+" is now on drifting on space...");
			
		Thread.sleep(rand.nextInt(600)+500);
		
		System.out.println("["+(System.currentTimeMillis()-time)+"]"+"This is shuttle"+s+" is now moving in.....");
		
		Thread.sleep(rand.nextInt(300)+200);
		
		System.out.println("["+(System.currentTimeMillis()-time)+"]"+"This is shuttle "+s+" is now coming in to get fuel.....");
		
		synchronized(ob1){
			
			System.out.println("["+(System.currentTimeMillis()-time)+"]"+"This is shuttle "+s+" is now getting in line to get fuel.....");
			
			lineUP++;
			
			if(lineUP == nShut){
				
				synchronized(control){
					control.notify();
				}
			}
			
			ob1.wait();
			
			System.out.println("["+(System.currentTimeMillis()-time)+"]"+"This is shuttle "+s+" is now ready for getting fuel.....");
			
		}
		
		}
		catch(Exception e){
			
			
		}
	}
	
	public void getNumber(String s, int x) throws InterruptedException{
		
		System.out.println("["+(System.currentTimeMillis()-time)+"]"+"This is shuttle "+s+" ready for filling up fuel ");
		
		Random randNumber = new Random();
		
		x = randNumber.nextInt(50)+50;
		
		System.out.println("["+(System.currentTimeMillis()-time)+"]"+"This is shuttle "+s+" I need "+x+" gallons fuel");
		
		Thread.sleep(200);
		
		synchronized(mutual){
		
		if(x < fuel){
			
			fuel = fuel - x;
			
			System.out.println("current fuel = "+ fuel);
			
			System.out.println("["+(System.currentTimeMillis()-time)+"]"+"This is shuttle "+s+" I am full leaving to take off......");
			
			hide++;
			
			mutual.notify();
			
			}
		else if(x > fuel){
			
			blocked++;
			
			System.out.println("["+(System.currentTimeMillis()-time)+"]"+"This is shuttle "+s+" I need fuel but not enough fuel in space tank......");
			
			synchronized(obj2){
				
				synchronized(control){
					control.notify();
				}
				
				mutual.notify();
				
				obj2.wait();
			}
			
			
			fuel = fuel - x;
			
			System.out.println("current fuel = "+ fuel);
			
			System.out.println("["+(System.currentTimeMillis()-time)+"]"+"This is shuttle "+s+" filled up the fuel after getting serve by supervisor now moving to take off......");
			
			hide++;
			
			}
		}
		
		//System.out.println(lokase);
		
		if(hide == nShut){
			
			synchronized(control){
				control.notify();
			}
			hide = 0;
			
			lineUP = 0;
		}
		
	}
	
	public void flyingOut(String x){
		
		System.out.println("["+(System.currentTimeMillis()-time)+"]"+"This is shuttle "+x+" going to fly.....");
		
		Random randNum = new Random();
		
		try{
			Thread.sleep(randNum.nextInt(500)+100);
			
			System.out.println("["+(System.currentTimeMillis()-time)+"]"+"This is shuttle "+x+" getting ready to request a runway....");
			
			synchronized(runwayQ){
				
				leftShuttle++;
				
				if(leftShuttle == nShut){
					
					synchronized(superV){
						superV.notify();
					}
					
					leftShuttle = 0;
					
				}
				
				runwayQ.wait();
				
				
			}
			
			int runwayIndex = willUse;
			
			System.out.println("["+(System.currentTimeMillis()-time)+"]"+"This is shuttle "+x+" ready to take off.....yeee hawww!");
			
			Thread.sleep(rand.nextInt(1000)+500);
			
			runways[runwayIndex] = false;
			
			synchronized(mutual){
				allLeft++;
				
				if(allLeft == nShut){
					allLeft = 0;
					
					synchronized(control){
						control.notify();
					}
				}
				mutual.notify();
			}
			
			
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public void cArrived(String s){
		
		System.out.println("["+(System.currentTimeMillis()-time)+"]"+"This is controller "+s+" I am coming to work.... ");
		
		try{
		
			
			Thread.sleep(rand.nextInt(100)+50);
			
			System.out.println("["+(System.currentTimeMillis()-time)+"]"+"This is controller "+s+" I am now at work......");
			
			synchronized(control){
				control.wait();
			}
			
			for(int i = 0; i< chargeAmount; i++){
				
				System.out.println("["+(System.currentTimeMillis()-time)+"]"+"This is control "+s+" is now releasing "+chargeAmount+" shuttles");
				
					for(int j = 0; j< chargeAmount; j++){
						
						synchronized(ob1){
							ob1.notify();
						}
						
					}
				//	Thread.sleep(500);
					
					synchronized(control){
						control.wait(2000);
						
						System.out.println("looked like someone need fuel ");
						
						if(blocked>0)
						fuel = 200;
						
						System.out.println("From control current fuel = "+fuel);
						
						synchronized(obj2){
							obj2.notifyAll();
						}
					}
					System.out.println("will now wait 30 min to open another group.....");
					
					blocked = 0;
				
					Thread.sleep(2000);
				
			}
			
			synchronized(control){
				control.wait();
			}
		
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public void supervisor(String x){
		
		
		System.out.println("["+(System.currentTimeMillis()-time)+"]"+"This is supervisor "+x+" coming to work....");
		
		try{
			
			Thread.sleep(rand.nextInt(400)+200);
			
			System.out.println("["+(System.currentTimeMillis()-time)+"]"+"This is supervisor "+x+" is now at work....");
			
			synchronized(superV){
				superV.wait();
			}
			int index = 0;
			
			for(int i = 0; i<nShut; i++){
			
			while(!available()){
				
				if(index%100000000 == 0)System.out.println("no runways available please wait....");
			
				index++;
				}
			
			System.out.println("looks like runway "+ willUse+" is available will now assign to the first shuttle on Q.....");
			
			synchronized(runwayQ){
				runwayQ.notify();
			}
			
			Thread.sleep(200);
			}
			
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		
		
	}
	
	public boolean available(){
		
		for(int i = 0; i<runways.length; i++){
			if(runways[i] == false ){
				runways[i] = true;
				willUse = i;
				return true;
			}
		}
		return false;
		
	}
	
	
}
