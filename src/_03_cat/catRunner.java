package _03_cat;

import java.util.Iterator;

public class catRunner {
public static void main(String[] args) {
	Cat cat = new Cat("Rowanflunkerdunkerleo");
	cat.meow();
	cat.printName();
	cat.kill();
	for (int i = 0; i < 9; i++) {
		cat.kill();
	}
}
}
