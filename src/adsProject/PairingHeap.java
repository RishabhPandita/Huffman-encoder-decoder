package adsProject;

class PairNode {
	HuffmanTreeStructure huffElement;
	PairNode leftChild;
	PairNode nextSibling;
	PairNode prev;

	/* Constructor */
	public PairNode(HuffmanTreeStructure data) {
		huffElement = data;
		leftChild = null;
		nextSibling = null;
		prev = null;
	}
}

public class PairingHeap {
	private PairNode root;
	private PairNode[] pairNodeTreeArray = new PairNode[5];

	public PairingHeap() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public boolean isLastNode() {
		return root.leftChild == null;
	}

	public HuffmanTreeStructure peek() {
		return root.huffElement;
	}

	public void makeEmpty() {
		root = null;
	}

	public PairNode insert(HuffmanTreeStructure x) {
		PairNode newNode = new PairNode(x);
		if (root == null)
			root = newNode;
		else
			root = compareAndLink(root, newNode);
		return newNode;
	}

	private PairNode compareAndLink(PairNode first, PairNode second) {
		if (second == null)
			return first;

		if (second.huffElement.frequency < first.huffElement.frequency) {
			second.prev = first.prev;
			first.prev = second;
			first.nextSibling = second.leftChild;
			if (first.nextSibling != null)
				first.nextSibling.prev = first;
			second.leftChild = first;
			return second;
		} else {
			second.prev = first;
			first.nextSibling = second.nextSibling;
			if (first.nextSibling != null)
				first.nextSibling.prev = first;
			second.nextSibling = first.leftChild;
			if (second.nextSibling != null)
				second.nextSibling.prev = second;
			first.leftChild = second;
			return first;
		}
	}

	private PairNode combineSiblings(PairNode firstSibling) {
		if (firstSibling.nextSibling == null)
			return firstSibling;
		int numSiblings = 0;
		for (; firstSibling != null; numSiblings++) {
			pairNodeTreeArray = doubleIfFull(pairNodeTreeArray, numSiblings);
			pairNodeTreeArray[numSiblings] = firstSibling;
			firstSibling.prev.nextSibling = null;
			firstSibling = firstSibling.nextSibling;
		}
		pairNodeTreeArray = doubleIfFull(pairNodeTreeArray, numSiblings);
		pairNodeTreeArray[numSiblings] = null;
		int i = 0;
		for (; i + 1 < numSiblings; i += 2)
			pairNodeTreeArray[i] = compareAndLink(pairNodeTreeArray[i], pairNodeTreeArray[i + 1]);
		int j = i - 2;
		if (j == numSiblings - 3)
			pairNodeTreeArray[j] = compareAndLink(pairNodeTreeArray[j], pairNodeTreeArray[j + 2]);
		for (; j >= 2; j -= 2)
			pairNodeTreeArray[j - 2] = compareAndLink(pairNodeTreeArray[j - 2], pairNodeTreeArray[j]);
		return pairNodeTreeArray[0];
	}

	private PairNode[] doubleIfFull(PairNode[] array, int index) {
		if (index == array.length) {
			PairNode[] oldArray = array;
			array = new PairNode[index * 2];
			for (int i = 0; i < index; i++)
				array[i] = oldArray[i];
		}
		return array;
	}

	public HuffmanTreeStructure deleteMin() {
		if (isEmpty())
			return null;
		HuffmanTreeStructure x = root.huffElement;
		if (root.leftChild == null)
			root = null;
		else
			root = combineSiblings(root.leftChild);
		return x;
	}
}
