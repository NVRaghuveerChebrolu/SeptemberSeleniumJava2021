package com.java.OOPsConcepts;

class A543 {
	void msg() {
		System.out.println("Hello");
	}
}

class B678 {
	void msg123() {
		System.out.println("Welcome");
	}
}

public class multipleInheritance extends A543//,B678 // suppose if it were
{

	public static void main(String args[]) {
		multipleInheritance obj23 = new multipleInheritance();
		obj23.msg();// Now which msg() method would be invoked?
	}
}