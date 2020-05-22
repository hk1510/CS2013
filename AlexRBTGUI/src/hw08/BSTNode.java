package hw08;

import javax.management.InvalidAttributeValueException;

public class BSTNode<E extends Comparable <E>> {
	protected BSTNode<E> parent;
	protected BSTNode<E> left;
	protected BSTNode<E> right;
	protected char color = 'N';
	private E data;
	
	BSTNode(E data) {
		this.setData(data);
	}
	BSTNode(E data, char color) {
		this.setData(data);
		this.setColor(color);
	}
	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}
	public char getColor() {
		return color;
	}
	public void setColor(char val) {
		try {
			if(val != 'N' && val != 'R' && val != 'B' && val != 'D') {
				throw new InvalidAttributeValueException("False Input");
			}
		} catch(InvalidAttributeValueException ex) {
			ex.printStackTrace();
		}
	}
}