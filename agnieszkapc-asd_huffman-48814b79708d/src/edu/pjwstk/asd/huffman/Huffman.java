package edu.pjwstk.asd.huffman;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.naming.BinaryRefAddr;

import com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator.HeapNode;

public class Huffman {
	
	//węzły używane podczas budowania drzewa Huffmana
	private List<Node> nodes;

	//drzewo Huffmana
	Node root;
	
	

//-------------------------------
	List<Byte> codes = new ArrayList<Byte>();
	//------------------------------------
	
	/**
	 * Na podstawie danych ze strumienia, tworzy listę węzłów(liści)
	 * dla symboli występujących w tekscie i przypisuje im częstości wystąpień.
	 * @param fis
	 */
	public void countFrequencies(FileInputStream fis) {
		nodes = new LinkedList<Node>();
		int c = -1;
		do {
			try {
				c = fis.read();
			} catch (IOException e) {
				System.err.println("Problem z wczytaniem pliku:");
				e.printStackTrace();
			}
			if (c==-1) break;
			Character ch = (char)c;
			System.out.print(ch);
			addOccurrenceToNodes(ch);
		}
		while (true);
		printFrequencies();
	}

	/**
	 * Na częstości wystąpień znaków w pliku wejściowym
	 * tworzy drzwo Huffmana.
	 */
	public void createCodesTree() {
		
		
		
		
		while(nodes.size()>1){
			
		//wybieranie z listy wezlow tych o najmniejszym indeksie i utworzenie z nich wezlow(lisci) pierwszego poddrzewa
		Node n1 = Collections.min(nodes);
		
		
		//...i usuwanie ich z listy
		nodes.remove(n1);
		
		Node n2 = Collections.min(nodes);
		
		
		nodes.remove(n2);
		
	//za pomoca konstruktora z klasy Node utworzenie korzenia majacego 2 liscie : n1 i n2( w konstruktorze od  razu dodaj� si� cz�sto�ci ) 
		root = new Node(n1, n2);
		
		//dodanie korzenia do listy zamiast 2 wyjetych
		nodes.add(root);
		
		}
		
		
		
	}
	
	public void printCodes() {
		printCodes(root);
	}
	
	private void printCodes(Node n) {
		
		
		if(n.isLeaf())
		{
			System.out.print(n.ch + codes.toString());
			return;
		
		}
		
		else{
			codes.add(n.getLeftEdgeValue());
			printCodes(n.getLeft());
		
		
		
			codes.add(n.getRightEdgeValue());
			printCodes(n.getRight());
		
		}
		
			
		}
		
			
		
	private void printFrequencies() {
		System.out.println();
		for (Node n : nodes) {
			
			System.out.println(n.ch+"("+(int)n.ch+") - "+n.frequency);
			
		}
	}

	private void addOccurrenceToNodes(Character ch) {
		
		//przelatuje po li�cie nod�w i sprawdza czy ich pole ch zgadza sie z parametrem ch, je�eli tak to zwi�ksza czestosc o 1
		for (Node n : nodes){
			if(n.ch.equals(ch)){
				n.frequency++;
			}
		}
		//jak nie ma takiego noda , to dodaje nowego do listy
		nodes.add(new Node(ch, 1));
		
	}

}
