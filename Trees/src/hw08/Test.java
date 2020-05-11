package hw08;

public class Test {
	public static void main(String[] args) {
//		RedBlackTree<Integer> tree = new RedBlackTree<>(1, 2, 3, 5, 4);
//		System.out.println(tree);
		RedBlackTree<Integer> tree = new RedBlackTree<>();
		tree.insert(1);
		System.out.println(tree);
		tree.insert(2);
		System.out.println(tree);
		tree.insert(3);
		System.out.println(tree);
		tree.delete(2);
		System.out.println(tree);
		
	}
}
