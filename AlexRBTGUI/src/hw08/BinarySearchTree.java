package hw08;

import java.util.ArrayList;

public class BinarySearchTree<E extends Comparable <E>> {
	protected BSTNode<E> root;
	
	BinarySearchTree(){}
	BinarySearchTree(E ... data) {
		for(E i : data) {
			insert(i);
		}
	}
	
	/* Public Methods */
	public BSTNode<E> getRoot() {
		return root;
	}
	public void setRoot(BSTNode<E> root) {
		this.root = root;
	}
	
	//Return True or False whether or not key is found in tree
	public boolean find(E key) {
		BSTNode<E> curr = root;
		while(curr != null) {
			if(key.compareTo(curr.getData()) == 0) {
				return true;
			} else if(key.compareTo(curr.getData()) < 0) {
				curr = curr.left;
			} else if(key.compareTo(curr.getData()) > 0) {
				curr = curr.right;
			}
		}
		return false;
	}
	
	//Insert key into tree
	public void insert(E key) {
		BSTNode<E> child = new BSTNode<E>(key);
		
		if(this.root == null) {
			this.root = child;
		} else {
			try {
				BSTNode<E> parent = insertionPoint(key);
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
	}
	protected BSTNode<E> insertionPoint(E key) {
		BSTNode<E> curr = root;
		BSTNode<E> parent = null;
		
		while(curr != null) {
			if(key.compareTo(curr.getData()) == 0) {
				throw new DuplicateItemException("Duplicate values in list");
			} else if(key.compareTo(curr.getData()) < 0) {
				parent = curr;
				curr = curr.left;
			} else if(key.compareTo(curr.getData()) > 0) {
				parent = curr;
				curr = curr.right;
			}
		}
		
		return parent;
	}
	
	//Delete key from tree
	public void delete(E key) {
		delete(toDelete(key));
	}
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
				this.root = null;
			} else if(toDelete.parent.left == toDelete) {
				toDelete.parent.left = null;
			} else if(toDelete.parent.right == toDelete) {
				toDelete.parent.right = null;
			}
		} //Case 2
		else if(numChildren == 1) {
			if(toDelete.left == null) {
				BSTNode<E> temp = toDelete.right;
				temp.parent = toDelete.parent;
				toDelete.right = temp.right;
				toDelete.left = temp.left;
				toDelete.setData(temp.getData());
			} else if(toDelete.right == null) {
				BSTNode<E> temp = toDelete.left;
				temp.parent = toDelete.parent;
				toDelete.right = temp.right;
				toDelete.left = temp.left;
				toDelete.setData(temp.getData());
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
	protected BSTNode<E> toDelete(E key) {
		BSTNode<E> curr = root;
		while(curr != null) {
			if(key.compareTo(curr.getData()) == 0) {
				return curr;
			} else if(key.compareTo(curr.getData()) < 0) {
				curr = curr.left;
			} else if(key.compareTo(curr.getData()) > 0) {
				curr = curr.right;
			}
		}
		return null;
	}
	protected BSTNode<E> maxLeftSubtree(BSTNode<E> delete) {
		return delete.left.right;
	}
	protected BSTNode<E> minRightSubtree(BSTNode<E> delete) {
		return delete.right.left;
	}
	//Return whether or not tree is empty
	public boolean isEmpty() {
		if(this.root == null) {
			return true;
		} else {
			return false;
		}
	}
	
	//Return ArrayList of BST Using Pre-order Traversal
	public ArrayList<E> preorder() {
		ArrayList<E> list = preorder(this.root);
		return list;
	}
	private ArrayList<E> preorder(BSTNode<E> node) {
		ArrayList<E> list = new ArrayList<E>();
		
		if(node == null) {
			return list;
		}
		
		list.add(node.getData());
		
		list.addAll(preorder(node.left));
		list.addAll(preorder(node.right));
		
		return list;
	}
	
	//Returns ArrayList of BST using Inorder Traversal
	public ArrayList<E> inorder() {
		ArrayList<E> list = inorder(this.root);
		return list;
	}
	public ArrayList<E> inorder(BSTNode<E> node) {
		ArrayList<E> list = new ArrayList<E>();
		
		if(node == null) {
			return list;
		}
		
		list.addAll(preorder(node.left));
		list.add(node.getData());
		list.addAll(preorder(node.right));
		
		return list;
	}
	
	//Returns ArrayList of BST using Inorder Traversal
	public ArrayList<E> postorder() {
		ArrayList<E> list = postorder(this.root);
		return list;
	}
	public ArrayList<E> postorder(BSTNode<E> node) {
		ArrayList<E> list = new ArrayList<E>();
		
		if(node == null) {
			return list;
		}
		
		list.addAll(preorder(node.left));
		list.addAll(preorder(node.right));
		list.add(node.getData());
		
		return list;
	}
	
	//Returns ArrayList of BST using Breadth-First Traversal
	public ArrayList<E> breadthfirst() {
		ArrayList<E> list = new ArrayList<E>();
		ArrayList<BSTNode<E>> queue = new ArrayList<BSTNode<E>>();
		queue.add(root);
		
		while(queue.size() != 0) {
			BSTNode<E> element = queue.remove(0);
			list.add(element.getData());
			if(element.left != null) {
				queue.add(element.left);
			}
			if(element.right != null) {
				queue.add(element.right);
			}
		}
		
		return list;
	}
	
	/* Protected Methods */
	//Return whether or not the node is a leaf
	protected boolean isLeaf(BSTNode<E> node) {
		if(node.right == null && node.left == null) {
			return true;
		}
		return false;
	}
	
	//Return whether or not the node is a left child of the parent
	protected boolean isLeftChild(BSTNode<E> node) {
		if(node.parent.left == node) {
			return true;
		}
		return false;
	}
	//Return whether or not the node is a right child of the parent
	protected boolean isRightChild(BSTNode<E> node) {
		if(node.parent.right == node) {
			return true;
		}
		return false;
	}
	
	//Return the sibling of the node
	protected BSTNode<E> sibling(BSTNode<E> node) {
		if(isLeftChild(node)) {
			return node.parent.right;
		} else {
			return node.parent.left;
		}
	}
	
	//Return the uncle of the node
	protected BSTNode<E> uncle(BSTNode<E> node) {
		return sibling(node.parent);
	}
	
	//Return the uncle of the node
	protected BSTNode<E> grandparent(BSTNode<E> node) {
		return node.parent.parent;
	}
	
	//toString
	public String toString() {
		ArrayList<E> list = inorder();
		StringBuilder sb = new StringBuilder();
		
		sb.append("|| ");
		for(E i: list) {
			sb.append(i + " || ");
		}
		return sb.toString();
	}
	
	//Print Tree
	public void printTree() {

	    if (this.root.right != null) {
	        this.printTree(this.root.right, true, "");
	    }

	    printNodeValue(this.root);

	    if (this.root.left != null) {
	        this.printTree(this.root.left, false, "");
	    }
	}

	private void printTree(BSTNode<E> node, boolean isRight, String indent) {
	    if (node.right != null) {
	        printTree(node.right, true, indent + (isRight ? "        " : " |      "));
	    }

	    System.out.print(indent);

	    if (isRight) {
	        System.out.print(" /");
	    }
	    else {
	        System.out.print(" \\");
	    }
	    System.out.print("----- ");
	    printNodeValue(node);
	    if (node.left != null) {
	        printTree(node.left, false, indent + (isRight ? " |      " : "        "));
	    }
	}

	private void printNodeValue(BSTNode<E> node) {
	    if (node == null) {
	        System.out.print("<null>");
	    }
	    else {
	        System.out.print(node.getData());
	    }
	    System.out.println();
	}
}