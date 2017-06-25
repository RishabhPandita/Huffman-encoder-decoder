package adsProject;

public class HuffmanTreeStructure {
	public int data;
	public long frequency;
	public HuffmanTreeStructure left;
	public HuffmanTreeStructure right;

	public HuffmanTreeStructure(int data, long frequency, HuffmanTreeStructure left, HuffmanTreeStructure right) {
		this.data = data;
		this.frequency = frequency;
		this.left = left;
		this.right = right;
	}

	public int compareTo(HuffmanTreeStructure t) {
		// TODO Auto-generated method stub
		if (frequency > t.frequency) {
			return 1;
		} else if (frequency < t.frequency)
			return -1;
		else
			return 0;
	}

}
