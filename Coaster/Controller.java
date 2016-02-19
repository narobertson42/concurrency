// Nick Robertson
// Electronic and Information Engineering Year 3
// Concurrency 

import display.*;

public class Controller {

	public static int Max = 9;
	protected NumberCanvas passengers;
	protected int numpass = 0; //Total number of passengers waiting
	protected boolean button; 

	public Controller(NumberCanvas nc) {
		passengers = nc;
		button = false; //Initial state of the button is not pressed
	}

	public synchronized void newPassenger() throws InterruptedException {
		// Check that new passengers are currently able to arrive a the platform
		while (numpass >= Max) {
			wait();
		}
		numpass++; //Increment waiting passengers
		passengers.setValue(numpass);
		notifyAll();	//Alert others that NewPassenger has been called succesfully
	}

	public synchronized int getPassengers(int mcar) throws InterruptedException {
		// Check that the number of people requested is positive
		if (mcar >= 0) {
			while ((numpass < mcar) && (!button)){
				wait(); //If not enough passengers and button not pressed wait
			}

			if (numpass < mcar && button) {
				mcar = numpass; //Button pressed, not enough passengers to fill car
				//Not using a temporary variable as mcar passed by value
			}
			numpass -= mcar; //Update number of waiting passengers
			button = false; //Button returns to false

			passengers.setValue(numpass); //Update display

			notifyAll(); //Alert others that getPassengers() has been called successfully

			return mcar; //Return the number of people that got into the car
		} else {
			return 0; //Rather than throw an error just don't allow car to take anybody
		}
	}

	public synchronized void goNow() {
		// Make sure there is at least one passenger to go in the coaster car
		if (0 < numpass) {
			button = true;
			notifyAll(); //Alert others that goNow() has been called successfully
		}
		
	}

}