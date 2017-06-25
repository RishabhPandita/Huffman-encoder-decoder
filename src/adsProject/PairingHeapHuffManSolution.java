package adsProject;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PairingHeapHuffManSolution {

	static HashMap<Integer, String> map = new HashMap<Integer, String>();
	private static Scanner sc;

	private void printCodes(HuffmanTreeStructure root, String s) {
		if (root == null)
			return;
		if (root.data != -2) {
			// System.out.println(root.data+":"+s);
			map.put(root.data, s);
		}
		printCodes(root.left, s + "0");
		printCodes(root.right, s + "1");

	}

	private void createHuffTree(HashMap<Integer, Integer> maps) {
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

		ArrayList<Integer> iList = new ArrayList<Integer>();
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
					iList.add(Integer.parseInt(temp));
					m.put(Integer.parseInt(temp), m.getOrDefault(Integer.parseInt(temp), 0) + 1);
				}
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found! Enter a valid file name and path");
			return;
		}

		PairingHeapHuffManSolution s = new PairingHeapHuffManSolution();
		s.createHuffTree(m);

		try {
			OutputStream encodedTextWrite = new BufferedOutputStream(new FileOutputStream(new File("encoded.bin")));
			StringBuilder binary = new StringBuilder();
			for (int i = 0; i < iList.size(); i++) {
				binary.append(map.get(iList.get(i)));
			}
			BitSet b = createFromString(binary.toString());
			encodedTextWrite.write(b.toByteArray());
			System.out.println("Encoded.bin file generated!");
			encodedTextWrite.close();

			BufferedWriter codeTableWrite = new BufferedWriter(new FileWriter(new File("code_table.txt")));
			for (Map.Entry<Integer, String> codemap : map.entrySet()) {
				codeTableWrite.write(codemap.getKey() + " " + codemap.getValue() + "\n");
			}
			System.out.println("Code Table file generated!");
			codeTableWrite.close();
		} catch (IOException e) {
			System.out.println("Error in Writing.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static BitSet createFromString(String s) {
		BitSet t = new BitSet(s.length());
		int lastBitIndex = s.length() - 1;

		for (int i = lastBitIndex; i >= 0; i--) {
			if (s.charAt(i) == '1') {
				t.set(lastBitIndex - i);
			}
		}

		return t;
	}
}
