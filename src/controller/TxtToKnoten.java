package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import modell.Knoten;
import modell.KnotenVerwaltung;

public class TxtToKnoten {
	
	private KnotenVerwaltung knotenV;
	
	/**Reader der zu lesenden Datei
	 * */
	private BufferedReader reader;
	
	
	protected TxtToKnoten(String pathOfFile) throws FileNotFoundException{
			Reader realReader = new FileReader(pathOfFile);
			reader = new BufferedReader(realReader); 
		
	}

//--- Eingabe
	protected void run() {
		try {
			generateKnotenV();
			generateKnoten();
			addKanten();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void generateKnotenV() throws IOException{
		//TODO
		reader.	readLine();
		String anzahlString = reader.readLine();
		int anzahlInt = Integer.valueOf(anzahlString);
		System.out.println("Anzahl Knoten: " + anzahlInt);
		knotenV = new KnotenVerwaltung(anzahlInt);
	}
	
	private void generateKnoten() throws IOException{
		reader.readLine();
		String line;
		Knoten k;
		int index;
		double knotenValue;
		int i;
		char c;
		int anzahlKnoten = knotenV.getAnzahlKnoten();
		for(int j = 0; j < anzahlKnoten; j++){
			line = reader.readLine();
			index = 0;
			knotenValue = 0;
			i = 0;
			for( ; (c = line.charAt(i)) != ' '; i++){
				index = index * 10 + (c -48);
			}
			line = line.substring(++i);
			knotenValue = (Double.valueOf(line));
//			knotenValue =  Double.valueOf(line).intValue();
			
			k = new Knoten(index, knotenValue);
			knotenV.addKnoten(k);
		}
	}
	
	private void addKanten() throws IOException {
		reader.readLine();	
		int fromKnotenIndex;
		int toKnotenIndex;
		int i;
		char c;
		String line = reader.readLine();;
		while(line != null){
			fromKnotenIndex = 0;
			toKnotenIndex = 0;
			i = 0;
			for( ;(c = line.charAt(i)) != ' '; i++){
				fromKnotenIndex = fromKnotenIndex * 10 + (c -48);
			}
			line = line.substring(++i);
			toKnotenIndex = Integer.valueOf(line);
			knotenV.addKante(fromKnotenIndex, toKnotenIndex);
			line = reader.readLine();
		}
	}

	protected KnotenVerwaltung getKnotenV(){
		return knotenV;
	}
}
