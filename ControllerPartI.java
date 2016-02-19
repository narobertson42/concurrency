// Nick Robertson
// Electronic and Information Engineering Year 3
// Concurrency 

import display.*;

public class Controller {

	public static int Max = 9;
	protected NumberCanvas passengers; 
	int numpass = 0; //Total number of passengers waiting

	public Controller(NumberCanvas nc) {
		passengers = nc;
	}

	public synchronized void newPassenger() throws InterruptedException {
		// Check that new passengers are currently able to arrive a the platform
		while (numpass >= Max) {
			wait();
		}
		numpass++; //Increment waiting passengers
		passengers.setValue(numpass);
		notifyAll(); //Alert others that NewPassenger has been called succesfully
	}

	public synchronized int getPassengers(int mcar) throws InterruptedException {
		// Check that the number of people requested is positive
		if (mcar >= 0) {

			while (numpass < mcar) {
				wait(); //If not enough passengers wait until there are
			}

			numpass -= mcar; //Update number of passengers

			passengers.setValue(numpass); //Update display

			notifyAll(); //Alert others that getPassengers() has been called successfully

			return mcar; //Return the number of people that got into the car
		} else {
			return 0; //Rather than throw an error just don't allow car to take anybody
		}
	}

	public synchronized void goNow() {
		// Implementation considered in part 2
	}

}