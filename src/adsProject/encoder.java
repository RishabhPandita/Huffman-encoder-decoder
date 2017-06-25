package adsProject;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class encoder {

	static HashMap<Integer, String> huffmanCodeMap = new HashMap<Integer, String>();

	private void makeHuffmanCodes(HuffmanTreeStructure root, String s) {
		if (root == null)
			return;
		if (root.data != Integer.MIN_VALUE) {
			huffmanCodeMap.put(root.data, s);
		}
		makeHuffmanCodes(root.left, s + "0");
		makeHuffmanCodes(root.right, s + "1");
	}

	private void generateHuffmanTreeFourWayHeap(HashMap<Integer, Integer> maps) {
		HuffmanTreeStructure left, right;
		fourWayCacheOptimisedHeap pq = new fourWayCacheOptimisedHeap();
		for (Map.Entry<Integer, Integer> entryMap : maps.entrySet()) {
			pq.insert(new HuffmanTreeStructure(entryMap.getKey(), entryMap.getValue(), null, null));
		}
		while ((!pq.isEmpty())) {
			left = pq.delete_min();
			right = pq.delete_min();
			HuffmanTreeStructure top = new HuffmanTreeStructure(Integer.MIN_VALUE, left.frequency + right.frequency,
					left, right);
			pq.insert(top);
		}
		makeHuffmanCodes(pq.peek(), "");
	}

	public static void main(String[] args) {

		ArrayList<Integer> fileData = new ArrayList<Integer>();
		HashMap<Integer, Integer> freqTableMap = new HashMap<Integer, Integer>();
		File inputFile = new File(args[0]);
		Scanner input = null;

		try {
			input = new Scanner(inputFile);
			while (input.hasNextLine()) {
				String fileLine = input.nextLine();
				if (!fileLine.isEmpty()) {
					fileData.add(Integer.parseInt(fileLine));
					freqTableMap.put(Integer.parseInt(fileLine),
							freqTableMap.getOrDefault(Integer.parseInt(fileLine), 0) + 1);
				}
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found! Enter a valid file name and path");
			return;
		}
		encoder ec = new encoder();
		ec.generateHuffmanTreeFourWayHeap(freqTableMap);
		try {

			// write to encoded.bin
			ec.writeToEncodedFile(fileData, "Encoded.bin");
			// write code table file
			BufferedWriter codeTableWrite = new BufferedWriter(new FileWriter(new File("code_table.txt")));
			for (Map.Entry<Integer, String> codemap : huffmanCodeMap.entrySet()) {
				codeTableWrite.write(codemap.getKey() + " " + codemap.getValue() + "\n");
			}
			codeTableWrite.close();
		} catch (IOException e) {
			System.out.println("Error in Writing.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void writeToEncodedFile(ArrayList<Integer> fileData, String filename) {
		try {
			writeBinaryFile(fileData, filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeBinaryFile(ArrayList<Integer> fileDataList, String filename) throws IOException {
		try {

			ByteArrayOutputStream byteOS = new ByteArrayOutputStream();
			StringBuilder filedataEncoded = new StringBuilder();
			int i = 0;
			while (i < fileDataList.size()) {
				int index = fileDataList.get(i++);
				if (huffmanCodeMap.containsKey(index)) {
					String enodedCode = huffmanCodeMap.get(index);
					filedataEncoded.append(enodedCode);
					if (filedataEncoded.length() >= 8) {
						while (filedataEncoded.length() >= 8) {
							byte[] byteArray = getByteByString(filedataEncoded.substring(0, 8).toString());
							byteOS.write(byteArray);
							filedataEncoded = filedataEncoded.delete(0, 8);
						}
					}
				} else {
					System.out.println("not found in table");
				}
			}
			if (filedataEncoded.length() != 0) {
				byte[] byteArrayData = getByteByString(filedataEncoded.substring(0, 8).toString());
				byteOS.write(byteArrayData);
			}

			BufferedOutputStream enoderWritter = new BufferedOutputStream(new FileOutputStream(filename));
			byte[] byteArray = byteOS.toByteArray();
			enoderWritter.write(byteArray);
			enoderWritter.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static byte[] getByteByString(String encodedString) {
		int splitSize = 8;
		if (encodedString.length() % splitSize == 0) {
			int index = 0;
			int position = 0;
			byte[] resultByteArray = new byte[encodedString.length() / splitSize];
			StringBuilder text = new StringBuilder(encodedString);
			while (index < text.length()) {
				String encodedStringChunk = text.substring(index, Math.min(index + splitSize, text.length()));
				Integer byteAsInt = Integer.parseInt(encodedStringChunk, 2);
				resultByteArray[position] = byteAsInt.byteValue();
				index += splitSize;
				position++;
			}
			return resultByteArray;
		} else {
			System.out.println("Invalid string length");
			return null;
		}
	}

}
