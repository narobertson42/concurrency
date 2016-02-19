// Nick Robertson
// Electronic and Information Engineering Year 3
// Concurrency 

public class PlatformAccess {

	protected boolean in_use; //Flag determining whether platform has car waiting

  public synchronized void arrive() throws InterruptedException {
	  while (in_use){
		  wait(); //If already car in platform wait
	  }
	  in_use=true; // Signify others that there is a car in the platform
	  notifyAll(); //Alert other cars
  }

  public synchronized void depart(){
	  if (in_use){ //Cannot depart unless it is at the platform
	  in_use = false; //Signify that a car has left
	  notifyAll();} //Alert other cars
  }

}