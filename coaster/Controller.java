/**
 * Complete the implementation of this class in line with the FSP model
 */

import display.*;

public class Controller {

	public static int Max = 9;
	protected NumberCanvas passengers;
	int numpass = 0;

	// declarations required

	public Controller(NumberCanvas nc) {
		passengers = nc;
	}

	public synchronized void newPassenger() throws InterruptedException {
		while (numpass >= Max) {
			wait();
		}
		numpass++;
		passengers.setValue(numpass);
		notifyAll();
	}

	public synchronized int getPassengers(int mcar) throws InterruptedException {
		if (mcar > 0) {

			while (numpass < mcar) {
				wait();
			}

			numpass -= mcar;

			passengers.setValue(numpass);

			notifyAll();

			return mcar;
		} else {
			return 0;
		}
	}

	public synchronized void goNow() {
		// complete implementation for part II
	}

}