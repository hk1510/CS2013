package hw08;

public class Tester {
	public static void main(String[] args) {
		RedBlackTree<Integer> bruh = new RedBlackTree<Integer>(19, 6, 23, 12, 20, 7, 25, 17, 31, 11, 29, 3, 24);
		bruh.printTree();
		bruh.delete(12);
		bruh.printTree();
	}
}
