package modell;

import java.util.ArrayList;

public class KnotenVerwaltung {
	
	private Knoten[] alleKnoten;
	
	private Knoten[] teilmengeArray;
	
	
	public KnotenVerwaltung(int anzahlKnoten){
		alleKnoten = new Knoten[anzahlKnoten];
		teilmengeArray = new Knoten[anzahlKnoten];
		
		Knoten.setAlleKnoten(alleKnoten);
		KnotenSucceedor.setAlleKnoten(alleKnoten);
		KnotenProceedor.setAlleKnoten(alleKnoten);
	}
	
	public void addKnoten(Knoten k){
		int index = k.getIndex();
		assert(alleKnoten[index] == null);
		alleKnoten[index] = k;
	}
	
	public void addKante(int fromKnotenIndex, int toKnotenIndex){
		Knoten kFrom = alleKnoten[fromKnotenIndex];
		Knoten kTo = alleKnoten[toKnotenIndex];
		Knoten.addKante(kFrom, kTo);
	}
	
	public ArrayList<Knoten> getWertvolleTeilmenge(){
		
		long startTime = System.currentTimeMillis();
		
		ArrayList<Knoten> wertvolleTeilmenge = new ArrayList<Knoten>();
		run(); 
		calcTeilmengeArray();
		for(Knoten k : teilmengeArray){
			if(k != null){
				wertvolleTeilmenge.add(k);
			}
		}
		
		System.out.println(System.currentTimeMillis() - startTime);
		return wertvolleTeilmenge;
	}
	
	
	private void run() {
		//update all Succeedor
		KnotenSucceedor ks;
		for(Knoten k : alleKnoten){
			ks = k.getKnotenSucceedor();
			ks.updateAllSucceedor();
		}
		
		KnotenProceedor.updateAllProceedor();
	}

	private void calcTeilmengeArray() {
		boolean[] worthyKnotenB;
		boolean kb;
		for(Knoten k : alleKnoten){
			worthyKnotenB = k.getWorthyKnoten();
			if(worthyKnotenB == null)
				continue;
			
			for(int i = 0; i < worthyKnotenB.length; i++){
				kb = worthyKnotenB[i];
				
				if(kb){
					teilmengeArray[i] = alleKnoten[i];
				}
			}
		}

	}

	public int getAnzahlKnoten(){
		return alleKnoten.length;
	}
	
}