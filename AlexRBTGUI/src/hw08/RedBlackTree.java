package hw08;


public class RedBlackTree<E extends Comparable <E>> extends BinarySearchTree<E> {
	final BSTNode<E> NIL = new BSTNode<E>(null, 'B');
	
	public RedBlackTree() {
	}
	public RedBlackTree(E ... data) {
		super(data);
	}
	
	@Override
	public void insert(E key) {
		BSTNode<E> child = new BSTNode<E>(key, 'R');
		child.left = NIL;
		child.right = NIL;
		
		if(this.root == null) {
			this.root = child;
		} else {
			try {
				BSTNode<E> parent = super.insertionPoint(key);
				if(key.compareTo(parent.getData()) < 0) {
					parent.left = child;
					child.parent = parent;
				}
				else if(key.compareTo(parent.getData()) > 0) {
					parent.right = child;
					child.parent = parent;
				}
			} catch(DuplicateItemException ex) {
				 ex.printStackTrace();
			}
		}
		insertCleanup(child);
	}
	
	@Override
	public void delete(E key) {
		delete(super.toDelete(key));
	}
	
	
	@Override
	protected void delete(BSTNode<E> toDelete) {
		int numChildren = 0;
		if(toDelete.left != null) {
			numChildren++;
		}
		if(toDelete.right != null) {
			numChildren++;
		} 
		
		//Case 1 
		if(isLeaf(toDelete)) {
			if(toDelete == root) {
				this.root = NIL;
			} else if(toDelete.parent.left == toDelete) {
				toDelete.parent.left = NIL;
			} else if(toDelete.parent.right == toDelete) {
				toDelete.parent.right = NIL;
			}
			
			NIL.parent = toDelete.parent;
			if(toDelete.getColor() == 'B') {
				NIL.setColor('D');
				fixDoubleBlack(NIL);
			}
		} //Case 2
		else if(numChildren == 1) {
			BSTNode<E> temp = null;
			if(toDelete.left == null) {
				temp = toDelete.right;
			} else if(toDelete.right == null) {
				temp = toDelete.left;
			}
			if(toDelete.left == null) {
				temp.parent = toDelete.parent;
				toDelete.right = temp.right;
				toDelete.left = temp.left;
				toDelete.setData(temp.getData());
			} else if(toDelete.right == null) {
				temp.parent = toDelete.parent;
				toDelete.right = temp.right;
				toDelete.left = temp.left;
				toDelete.setData(temp.getData());
			}
			
			if(temp.getColor() == 'R' || toDelete.getColor() == 'R') {
				toDelete.setColor('B');
			} else if(temp.getColor() == 'B' || toDelete.getColor() == 'B') {
				temp.setColor('D');
				fixDoubleBlack(temp);
			}
		} //Case 3 
		else if(numChildren == 2) {
			if(maxLeftSubtree(toDelete) != null) {
				BSTNode<E> max = maxLeftSubtree(toDelete);
				toDelete.setData(max.getData());
				delete(max);
			} else if(minRightSubtree(toDelete) != null) {
				BSTNode<E> min = minRightSubtree(toDelete);
				toDelete.setData(min.getData());
				delete(min);
			} else {
				BSTNode<E> temp = toDelete.right;
				toDelete.right = temp.right;
				toDelete.setData(temp.getData());
			}
		}
	}
	
	private void leftRotate(BSTNode<E> root) {
		BSTNode<E> temp = root.right;
		root.right = temp.left;
		temp.left = root;
		root.parent = temp;
	}
	private void rightRotate(BSTNode<E> root) {
		BSTNode<E> temp = root.right;
		root.right = temp.left;
		temp.left = root;
		root.parent = temp;
	}
	private void insertCleanup(BSTNode<E> node) {
		if(this.root.getColor() == 'R') {
			this.root.setColor('B');
		} else if(node != this.root && node.parent.getColor() == 'R' && uncle(node).getColor() == 'R') {
			node.parent.parent.setColor('R');
			node.parent.setColor('B');
			uncle(node).setColor('B');
		} else if(node.getData() == node.parent.right.getData() && node.parent.getData() == node.parent.parent.left.getData() && node.parent.getColor() == 'R' && uncle(node).getColor() == 'B') {
			leftRotate(node.parent);
		} else if(node.getData() == node.parent.left.getData() && node.parent.getData() == node.parent.parent.right.getData() && node.parent.getColor() == 'R' && uncle(node).getColor() == 'B') {
			rightRotate(node.parent);
		} else if(node.getData() == node.parent.left.getData() && node.parent.getData() == node.parent.parent.left.getData() && node.parent.getColor() == 'R' && uncle(node).getColor() == 'B') {
			node.parent.setColor('B');
			node.parent.parent.setColor('R');
			rightRotate(node.parent.parent);
		} else if(node.getData() == node.parent.right.getData() && node.parent.getData() == node.parent.parent.right.getData() && node.parent.getColor() == 'R' && uncle(node).getColor() == 'B') {
			node.parent.setColor('B');
			node.parent.parent.setColor('R');
			leftRotate(node.parent.parent);
		}
	}
	private void fixDoubleBlack(BSTNode<E> node) {
		BSTNode<E> sibling = sibling(node);
		BSTNode<E> parent = node.parent;
		if(node == this.root) {
			node.setColor('B');
		} else if(sibling.getColor() == 'R') {
			if(node == node.parent.right) {
				sibling.setColor('B');
				parent.setColor('R');
				rightRotate(parent);
				fixDoubleBlack(node);
			} else if(node == node.parent.left) {
				sibling.setColor('B');
				parent.setColor('R');
				leftRotate(parent);
				fixDoubleBlack(node);
			}
		} else if(sibling.left.getColor() == 'R' || sibling.right.getColor() == 'R') {
			if(sibling == sibling.parent.left) {
				if(sibling.left.getColor() == 'R') {
					leftRotate(sibling);
					rightRotate(parent);
					sibling.right .setColor(parent.getColor());
					sibling.setColor('B');
					parent.setColor('B');
					node.setColor('B');
				} else if(sibling.left.getColor() == 'R') {
					rightRotate(parent);
					sibling.left.setColor(parent.getColor());
					sibling.setColor('B');
					parent.setColor('B');
					node.setColor('B');
				}
			} else if(sibling == sibling.parent.right) {
				if(sibling.left.getColor() == 'R') {
					rightRotate(sibling);
					leftRotate(parent);
					sibling.right .setColor(parent.getColor());
					sibling.setColor('B');
					parent.setColor('B');
					node.setColor('B');
				} else if(sibling.left.getColor() == 'R') {
					leftRotate(parent);
					sibling.left.setColor(parent.getColor());
					sibling.setColor('B');
					parent.setColor('B');
					node.setColor('B');
				}
			} else if(sibling.getColor() == 'B' && sibling.right.getColor() == 'B' && sibling.left.getColor() == 'B') {
				if(parent.getColor() == 'R') {
					sibling.setColor('R');
					parent.setColor('B');
					node.setColor('B');
				} else if(parent.getColor() == 'B') {
					sibling.setColor('R');
					parent.setColor('D');
					node.setColor('B');
					fixDoubleBlack(parent);
				}
			}
		}
	}
}