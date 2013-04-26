package edu.pjwstk.asd.huffman;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Compress {

	public static void main(String[] args) {
		
		if (args.length < 1) {
			System.err.println("Podaj nazwę pliku, który ma zostać skompresowany.");
			return;
		}
		
		String fileToCompress = args[0];
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileToCompress);
		} catch (FileNotFoundException e) {
			System.err.println("Nie mogę znaleźć pliku '"+fileToCompress+"'.");
			return;
		}
		System.out.println("Kompresuję plik '"+fileToCompress+"'");
		
		Huffman huffman = new Huffman();
		huffman.countFrequencies(fis);
		try {
			if (fis != null)
				fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		huffman.createCodesTree();
		huffman.printCodes();
		
	}
	
}
