package adsProject;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Test_4wayHeap {

	/** The number of children each node has **/
	private int d;
	private int heapSize;
	private int[] heap;

	/** Constructor **/
	public Test_4wayHeap(int capacity, int numChild) {
		heapSize = 0;
		d = numChild;
		heap = new int[capacity + 1];
		Arrays.fill(heap, -1);
	}

	/** Function to check if heap is empty **/
	public boolean isEmpty() {
		return heapSize == 0;
	}

	/** Check if heap is full **/
	public boolean isFull() {
		return heapSize == heap.length;
	}

	/** Clear heap */
	public void clear() {
		heapSize = 0;
	}

	/** Function to get index parent of i **/
	private int parent(int i) {
		return (((i -1) / d));
		//return (((i +8) / d));
	}

	/** Function to get index of k th child of i **/
	private int kthChild(int i, int k) {
		return d * i + k;
		//return d * i - (k+4);
	}

	/** Function to insert element */
	public void insert(int x) {
		if (isFull())
			throw new NoSuchElementException("Overflow Exception");
		/** Percolate up **/
		heap[heapSize++] = x;
		heapifyUp(heapSize - 1);
	}

	/** Function to find least element **/
	public int findMin() {
		if (isEmpty())
			throw new NoSuchElementException("Underflow Exception");
		return heap[0];
	}

	/** Function to delete element at an index **/
	public int delete(int ind) {
		if (isEmpty())
			throw new NoSuchElementException("Underflow Exception");
		int keyItem = heap[ind];
		heap[ind] = heap[heapSize - 1];
		heapSize--;
		heapifyDown(ind);
		return keyItem;
	}

	/** Function heapifyUp **/
	private void heapifyUp(int childInd) {
		int tmp = heap[childInd];
		while ((childInd) > 0 && tmp < heap[parent(childInd)]) {
//		while ((childInd-3) > 0 && tmp < heap[parent(childInd)]) {
			heap[childInd] = heap[parent(childInd)];
			childInd = parent(childInd);
		}
		heap[childInd] = tmp;
	}

	/** Function heapifyDown **/
	private void heapifyDown(int ind) {
		int child;
		int tmp = heap[ind];
		while (kthChild(ind, 1) < heapSize) {
			child = minChild(ind);
			if (heap[child] < tmp)
				heap[ind] = heap[child];
			else
				break;
			ind = child;
		}
		heap[ind] = tmp;
	}

	/** Function to get smallest child **/
	private int minChild(int ind) {
		int bestChild = kthChild(ind, 1);
		int k = 2;
		int pos = kthChild(ind, k);
		while ((k <= d) && (pos < heapSize)) {
			if (heap[pos] < heap[bestChild])
				bestChild = pos;
			pos = kthChild(ind, k++);
		}
		return bestChild;
	}

	/** Function to print heap **/
	public void printHeap() {
		System.out.print("\nHeap = ");
		for (int i = 0; i < heapSize; i++)
			System.out.print(heap[i] + " ");
		System.out.println();
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("D ary Heap Test\n\n");
		System.out.println("Enter size and D of D-ary Heap");
		/** Make object of DaryHeapHeap **/
		Test_4wayHeap dh = new Test_4wayHeap(scan.nextInt(), scan.nextInt());

		dh.insert(1);
		dh.insert(10);
		dh.insert(60);
		dh.insert(100);
		dh.insert(20);
		dh.insert(40);
		dh.insert(70);
		dh.insert(80);
		dh.insert(90);
		
		
		System.out.println(dh.findMin());
		dh.delete(0);
		System.out.println(dh.findMin());
		dh.delete(0);
		System.out.println(dh.findMin());
		dh.delete(0);
		System.out.println(dh.findMin());
		dh.delete(0);
		System.out.println(dh.findMin());
		dh.delete(0);
		System.out.println(dh.findMin());
		dh.delete(0);
		System.out.println(dh.findMin());
		dh.delete(0);
		System.out.println(dh.findMin());
		dh.delete(0);
		System.out.println(dh.findMin());
		dh.delete(0);
		
		
		/*char ch;
		*//** Perform D-ary Heap operations **//*
		do {
			System.out.println("\nD-ary Heap Operations\n");
			System.out.println("1. insert ");
			System.out.println("2. delete Min");
			System.out.println("3. check full");
			System.out.println("4. check empty");
			System.out.println("5. clear");

			boolean chk;
			int choice = scan.nextInt();
			switch (choice) {
			case 1:
				try {
					System.out.println("Enter integer element to insert");
					dh.insert(scan.nextInt());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				try {
					System.out.println("Enter delete position");
					System.out.println(dh.findMin());
					dh.delete(0);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Full status = " + dh.isFull());
				break;
			case 4:
				System.out.println("Empty status = " + dh.isEmpty());
				break;
			case 5:
				dh.clear();
				System.out.println("Heap Cleared\n");
				break;
			default:
				System.out.println("Wrong Entry \n ");
				break;
			}
			*//** Display heap **//*
			dh.printHeap();

			System.out.println("\nDo you want to continue (Type y or n) \n");
			ch = scan.next().charAt(0);
		} while (ch == 'Y' || ch == 'y');
*/	}
}
