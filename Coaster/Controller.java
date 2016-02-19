/**
 * Complete the implementation of this class in line with the FSP model
 */

import display.*;

public class Controller {

	public static int Max = 9;
	protected NumberCanvas passengers;
	protected int numpass = 0;
	protected boolean button;

	public Controller(NumberCanvas nc) {
		passengers = nc;
		button = false;
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
		if (mcar >= 0) {

			while ((numpass < mcar) && (!button)){
				wait();
			}

			if (numpass < mcar && button) {
				mcar = numpass;
			}
			numpass -= mcar;
			button = false;

			passengers.setValue(numpass);

			notifyAll();

			return mcar;
		} else {
			return 0;
		}
	}

	public synchronized void goNow() {
		if (0 < numpass) {
			button = true;
		}
		notifyAll();
	}

}