package com.java.OOPsConcepts;

interface Drawable221 {
	void draw();

	/*static int cube1(int x) {
		return x * x * x;
	}*/
}

class Rectangle112 implements Drawable221 {
	public void draw() {
		System.out.println("drawing rectangle");
	}
}

class interfaceStaticMethod {
	public static void main(String args[]) {
		Drawable221 d = new Rectangle112();
		d.draw();
		//.out.println(Drawable221.cube1(3));
	}
}