package adsProject;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BinaryHeapHuffManSolution {

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

	
	private void createHuffTree(HashMap<Integer, Integer> maps) {
		HuffmanTreeStructure left, right;
		binaryHeap<?> pq = new binaryHeap<>();
		for (Map.Entry<Integer, Integer> m : maps.entrySet()) {
			pq.insert(new HuffmanTreeStructure(m.getKey(), m.getValue(), null, null));
		}
		while ((pq.size()!=1)) {
			left = pq.extractMin();
			right = pq.extractMin();
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
			int i=0;
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
		

		BinaryHeapHuffManSolution s = new BinaryHeapHuffManSolution();
		s.createHuffTree(m);
	
		try {
			OutputStream encodedTextWrite = new BufferedOutputStream(new FileOutputStream(new File("encoded.bin")));			
			StringBuilder binary=new StringBuilder();
			for (int i = 0; i < iList.size(); i++) {
				binary.append(map.get(iList.get(i)));
			}

			BitSet b = createFromString(binary.toString());
			System.out.println(b.length());
			encodedTextWrite.write(b.toByteArray());
			System.out.println("Encoded.bin file generated!");
			encodedTextWrite.close();
	
			BufferedWriter codeTableWrite = new BufferedWriter(new FileWriter(new File("code_table.txt")));
			for (Map.Entry<Integer, String> codemap : map.entrySet()) {
				codeTableWrite.write(codemap.getKey() + " " + codemap.getValue() + "\n");
			}
			System.out.println("Code Table file generated!");
			codeTableWrite.close();
			
			String encodedString="";
			try{
				Path fileLocation = Paths.get("C:\\Users\\Rishabh\\workspace\\adsProject\\encoded.bin");
				byte[] data = Files.readAllBytes(fileLocation);
				BitSet set = BitSet.valueOf(data);
				encodedString=s.createfromBits(set);
			}
			catch(Exception e){
				System.out.println("Something went wrong!");
				return;
			}
			
		} catch (IOException e) {
			System.out.println("Error in Writing.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	public static String createfromBits(BitSet s) {
		StringBuilder ans = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if(s.get(i))
			{
				ans.append('1');
			}
			else
				ans.append('0');
		}
		return ans.toString();
	}
	private static BitSet createFromString(String s) {
		BitSet t = new BitSet(s.length());
	    int lastBitIndex = s.length() - 1;

	    for(Character c:s.toString().toCharArray())
	    {
	    	if(c.equals('1')){
	    		t.set(lastBitIndex);
	    	}
	    	lastBitIndex--;
	    }
	    return t;
	}
	
}
