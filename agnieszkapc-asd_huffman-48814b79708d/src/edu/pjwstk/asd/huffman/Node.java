package edu.pjwstk.asd.huffman;


public class Node implements Comparable<Node>{

	public static byte getLeftEdgeValue() {
		return LEFT_EDGE_VALUE;
	}

	public static byte getRightEdgeValue() {
		return RIGHT_EDGE_VALUE;
	}

	private static final byte LEFT_EDGE_VALUE = 0;
	private static final byte RIGHT_EDGE_VALUE = 1;
	
	private Node left;
	private Node right;
	Character ch;
	Integer frequency;
	
	public Node(Character character, Integer frequency) {
		this.ch = character;
		this.frequency = frequency;
		assert(frequency>0);
		
		this.isLeaf();
		
	}

	public Node(Node n1, Node n2) {
		this.left = n1;
		this.right = n2;
		this.frequency = n1.frequency + n2.frequency;
	}

	/**
	 * Zwraca true jeśli węzeł jest liściem.
	 * @return
	 */
	public boolean isLeaf() {
		return left==null && right==null;
	}
	
	public Node getLeft() {
		return left;
	}

	public Node getRight() {
		return right;
	}

	@Override
	public int compareTo(Node other) {
		return this.frequency - other.frequency;
	}
	
	@Override
	public String toString() {
		if (isLeaf())
			return ch+"("+(int)ch+") "+frequency;
		return "subtree "+frequency;
	}
	
}
