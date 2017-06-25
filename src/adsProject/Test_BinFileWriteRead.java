package adsProject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.BitSet;

public class Test_BinFileWriteRead {

	public static void main(String... aArgs) {
		Test_BinFileWriteRead test = new Test_BinFileWriteRead();
		// read in the bytes
		String inp = "1001101100110100101001111001111001101110";
		byte[] fileContents = inp.getBytes();//test.read("C:\\Users\\Rishabh\\workspace\\adsProject\\si.txt");
		// test.readAlternateImpl(INPUT_FILE_NAME);
		// write it back out to a different file name
		test.write(fileContents,"test");
	}

	void write(byte[] aInput, String aOutputFileName){
	   
	    try {
	      OutputStream output = null;
	      try {
	    	  String t ="1001101100110100101001111001111001101110";
	    	  BitSet b =  BitSet.valueOf(new long[] { Long.parseLong(t, 2) });
	        output = new BufferedOutputStream(new FileOutputStream(aOutputFileName));
	        output.write(b.toByteArray());
	      }
	      finally {
	        output.close();
	      }
	    }
	    catch(FileNotFoundException ex){
	     
	    }
	    catch(IOException ex){
	     
	    }
	  }
	
	byte[] read(String aInputFileName) {
	
		File file = new File(aInputFileName);
		
		byte[] result = new byte[(int) file.length()];
		try {
			InputStream input = null;
			try {
				int totalBytesRead = 0;
				input = new BufferedInputStream(new FileInputStream(file));
				while (totalBytesRead < result.length) {
					int bytesRemaining = result.length - totalBytesRead;
					// input.read() returns -1, 0, or more :
					int bytesRead = input.read(result, totalBytesRead, bytesRemaining);
					if (bytesRead > 0) {
						totalBytesRead = totalBytesRead + bytesRead;
					}
				}
				
				/*
				 * the above style is a bit tricky: it places bytes into the
				 * 'result' array; 'result' is an output parameter; the while
				 * loop usually has a single iteration only.
				 */
				
			} finally {
				
				input.close();
			}
		} catch (FileNotFoundException ex) {
			
		} catch (IOException ex) {
			
		}
		return result;
	}

}
