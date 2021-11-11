package com.java.OOPsConcepts;

class finalVariable {
	final int speedlimit = 90;// final variable

	void run() {
	//	speedlimit = 400;
	}

	public static void main(String args[]) {
		finalVariable obj = new finalVariable();
		obj.run();
	}
}// end of class