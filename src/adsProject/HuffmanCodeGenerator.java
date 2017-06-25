package adsProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HuffmanCodeGenerator {
	static HashMap<Integer, String> map = new HashMap<Integer, String>();
	private static Scanner sc;

	private void printCodes(HuffmanTreeStructure root, String s) {
		if (root == null)
			return;
		if (root.data != -2) {
			map.put(root.data, s);
		}
		printCodes(root.left, s + "0");
		printCodes(root.right, s + "1");
	}

	private void createHuffTree4wayCacheOptimized(HashMap<Integer, Integer> maps) {
		HuffmanTreeStructure left, right;
		fourWayCacheOptimisedHeap pq = new fourWayCacheOptimisedHeap();
		for (Map.Entry<Integer, Integer> m : maps.entrySet()) {
			pq.insert(new HuffmanTreeStructure(m.getKey(), m.getValue(), null, null));
		}
		while ((!pq.isEmpty())) {
			left = pq.delete_min();
			right = pq.delete_min();
			HuffmanTreeStructure top = new HuffmanTreeStructure(-2, left.frequency + right.frequency, left, right);
			pq.insert(top);
		}
		printCodes(pq.peek(), "");
	}

	private void createHuffTreeBinaryHeap(HashMap<Integer, Integer> maps) {
		HuffmanTreeStructure left, right;
		binaryHeap<?> pq = new binaryHeap<>();
		for (Map.Entry<Integer, Integer> m : maps.entrySet()) {
			pq.insert(new HuffmanTreeStructure(m.getKey(), m.getValue(), null, null));
		}
		while ((pq.size() != 1)) {
			left = pq.extractMin();
			right = pq.extractMin();
			HuffmanTreeStructure top = new HuffmanTreeStructure(-2, left.frequency + right.frequency, left, right);
			pq.insert(top);
		}
		printCodes(pq.peek(), "");
	}

	private void createHuffTreePairingHeap(HashMap<Integer, Integer> maps) {
		HuffmanTreeStructure left, right;
		PairingHeap pq = new PairingHeap();
		for (Map.Entry<Integer, Integer> m : maps.entrySet()) {
			pq.insert(new HuffmanTreeStructure(m.getKey(), m.getValue(), null, null));
		}
		while (!pq.isLastNode()) {
			left = pq.deleteMin();
			right = pq.deleteMin();
			HuffmanTreeStructure top = new HuffmanTreeStructure(-2, left.frequency + right.frequency, left, right);
			pq.insert(top);
		}
		printCodes(pq.peek(), "");
	}

	public static void main(String[] args) {

		HuffmanCodeGenerator hcg = new HuffmanCodeGenerator();

		sc = new Scanner(System.in);
		System.out.println("Enter File Name to Read From: ");
		String fileName = sc.nextLine();

		HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
		File inputFile = new File(fileName);
		Scanner input = null;

		try {
			input = new Scanner(inputFile);
			while (input.hasNextLine()) {
				String temp = input.nextLine();
				if (!temp.isEmpty()) {
					m.put(Integer.parseInt(temp), m.getOrDefault(Integer.parseInt(temp), 0) + 1);
				}
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found! Enter a valid file name and path");
			return;
		}

		long start = System.currentTimeMillis();
		for (int i = 0; i < 10; i++)
			hcg.createHuffTreeBinaryHeap(m);
		long time = System.currentTimeMillis() - start;
		System.out.println("BinaryHeap: " + time/10 + " ms");

		start = System.currentTimeMillis();
		for (int i = 0; i < 10; i++)
			hcg.createHuffTreePairingHeap(m);
		time = System.currentTimeMillis() - start;
		System.out.println("PairingHeap: " + time/10 + " ms");

		start = System.currentTimeMillis();
		for (int i = 0; i < 10; i++)
			hcg.createHuffTree4wayCacheOptimized(m);
		time = System.currentTimeMillis() - start;
		System.out.println("FourWayHeapCacheOptimized: " + time/10 + " ms");

	}

}
