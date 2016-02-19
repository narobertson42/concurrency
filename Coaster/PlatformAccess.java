public class PlatformAccess {

	protected boolean in_use;
  /* declarations required */

  public synchronized void arrive() throws InterruptedException {
	  while (in_use){
		  wait();
	  }
	  in_use=true;
	  notifyAll();
    // complete implementation
  }

  public synchronized void depart(){
	  if (in_use){
	  in_use = false;
	  notifyAll();}
    // complete implementation
  }

}