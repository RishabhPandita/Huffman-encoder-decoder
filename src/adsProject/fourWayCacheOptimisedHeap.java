package adsProject;

import java.util.ArrayList;

public class fourWayCacheOptimisedHeap {
	private ArrayList<HuffmanTreeStructure> heapArray;
	private int numElements;

	public fourWayCacheOptimisedHeap() {
		numElements = 3;
		heapArray = new ArrayList<HuffmanTreeStructure>();
		HuffmanTreeStructure dummy = new HuffmanTreeStructure(Integer.MIN_VALUE, -1, null, null);
		heapArray.add(dummy);
		heapArray.add(dummy);
		heapArray.add(dummy);
	}

	public boolean isEmpty() {
		return (numElements) == 4;
	}

	public void insert(HuffmanTreeStructure priority_level) {
		heapArray.add(priority_level);
		heapUp(numElements);
		numElements++;
	}

	public void heapUp(int index) {
		HuffmanTreeStructure lastElement = heapArray.get(index);
		int parentIndex = (index + 8) / 4;
		while ((index > 3) && (lastElement.frequency < heapArray.get(parentIndex).frequency)) {
			heapArray.set(index, heapArray.get(parentIndex));
			index = parentIndex;
			parentIndex = (parentIndex + 8) / 4;
		}
		heapArray.set(index, lastElement);
	}

	public HuffmanTreeStructure delete_min() {
		HuffmanTreeStructure min = heapArray.get(3);
		numElements--;
		heapArray.set(3, heapArray.get(numElements));
		heapDown(3);
		heapArray.remove(numElements);
		return min;
	}

	public HuffmanTreeStructure peek() {
		return heapArray.get(3);
	}

	public void heapDown(int index) {
		int maxChild = findMin((index * 4) - 8, (index * 4) - 5);
		HuffmanTreeStructure tempRoot = heapArray.get(index);
		while ((maxChild < numElements) && (heapArray.get(maxChild).frequency < tempRoot.frequency)) {
			heapArray.set(index, heapArray.get(maxChild));
			index = maxChild;
			maxChild = findMin(maxChild * 4 - 8, maxChild * 4 - 5);
		}
		heapArray.set(index, tempRoot);
	}

	public int findMin(int from, int to) {
		int maxChild = from;
		for (int i = from + 1; (i <= to && i < numElements); i++) {
			if (heapArray.get(maxChild).frequency > heapArray.get(i).frequency)
				maxChild = i;
		}
		return maxChild;
	}

	public static void main(String args[]) {
		fourWayCacheOptimisedHeap h = new fourWayCacheOptimisedHeap();

		h.insert(new HuffmanTreeStructure(0, 4, null, null));
		h.insert(new HuffmanTreeStructure(999, 2, null, null));
		h.insert(new HuffmanTreeStructure(34, 3, null, null));
		h.insert(new HuffmanTreeStructure(2, 1, null, null));
		h.insert(new HuffmanTreeStructure(2245, 4, null, null));
		h.insert(new HuffmanTreeStructure(446, 2, null, null));

		System.out.println(h.delete_min().frequency);
		System.out.println(h.delete_min().frequency);
		System.out.println(h.delete_min().frequency);
		System.out.println(h.delete_min().frequency);
		System.out.println(h.delete_min().frequency);
		System.out.println(h.delete_min().frequency);
	}
}