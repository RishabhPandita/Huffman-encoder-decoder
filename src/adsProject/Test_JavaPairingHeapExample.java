package adsProject;
/*package adsProject;

import java.util.Scanner;

class PairNode {
	int element;
	PairNode leftChild;
	PairNode nextSibling;
	PairNode prev;

	 Constructor 
	public PairNode(int x) {
		element = x;
		leftChild = null;
		nextSibling = null;
		prev = null;
	}
}

class JavaPairingHeapExample {
	private PairNode root;
	private PairNode[] treeArray = new PairNode[5];

	public JavaPairingHeapExample() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void makeEmpty() {
		root = null;
	}

	public PairNode insert(int x) {
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

		if (second.element < first.element) {
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
			treeArray = doubleIfFull(treeArray, numSiblings);
			treeArray[numSiblings] = firstSibling;
			firstSibling.prev.nextSibling = null;
			firstSibling = firstSibling.nextSibling;
		}
		treeArray = doubleIfFull(treeArray, numSiblings);
		treeArray[numSiblings] = null;
		int i = 0;
		for (; i + 1 < numSiblings; i += 2)
			treeArray[i] = compareAndLink(treeArray[i], treeArray[i + 1]);
		int j = i - 2;
		if (j == numSiblings - 3)
			treeArray[j] = compareAndLink(treeArray[j], treeArray[j + 2]);
		for (; j >= 2; j -= 2)
			treeArray[j - 2] = compareAndLink(treeArray[j - 2], treeArray[j]);
		return treeArray[0];
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

	public int deleteMin() {
		if (isEmpty())
			return -1;
		int x = root.element;
		if (root.leftChild == null)
			root = null;
		else
			root = combineSiblings(root.leftChild);
		return x;
	}

	public void inorder() {
		inorder(root);
	}

	private void inorder(PairNode r) {
		if (r != null) {
			inorder(r.leftChild);
			System.out.print(r.element + " ");
			inorder(r.nextSibling);
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("PairHeap Test\n\n");
		JavaPairingHeapExample ph = new JavaPairingHeapExample();

		char ch;
		do {
			System.out.println("\nPair Heap Operations\n");
			System.out.println("1. insert ");
			System.out.println("2. delete min");
			System.out.println("3. check empty");
			System.out.println("4. clear");

			int choice = scan.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter integer element to insert");
				ph.insert(scan.nextInt());
				break;
			case 2:
				ph.deleteMin();
				break;
			case 3:
				System.out.println("Empty status = " + ph.isEmpty());
				break;
			case 4:
				ph.makeEmpty();
				break;
			default:
				System.out.println("Wrong Entry \n ");
				break;
			}
			System.out.print("\nInorder Traversal : ");
			ph.inorder();

			System.out.println("\nDo you want to continue (Type y or n) \n");
			ch = scan.next().charAt(0);
		} while (ch == 'Y' || ch == 'y');
	}

}
*/