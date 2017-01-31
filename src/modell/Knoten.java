package modell;

import java.util.LinkedList;

public class Knoten{
//---general
	
	private static Knoten[] alleKnoten;
	
	/**contains the index of this knot*/
	private int index;
	
	/**speicher den Wert des Knotens aus der txt */
	private double ownValue; 
	
	
//---successor	
	/**nur direkte Nachfolger, eigener nicht Knoten drin*/
	private LinkedList<Knoten> directSuccessorKnoten = new LinkedList<Knoten>();
	
	private KnotenSucceedor knotenSucceedor;

	//---proceedor
	private KnotenProceedor knotenProceedor;
	
//--general	
	public Knoten(int index, double ownValue){
		this.ownValue = ownValue;
		this.index = index;
		
		knotenSucceedor = new KnotenSucceedor(this);
		knotenProceedor = new KnotenProceedor(this);
		
		System.out.println("Knoten Nummer " + index + " mit dem Wert "+ ownValue+ " erstellt");
	}
	
	
	public boolean[] getWorthyKnoten(){
		
		if(ownValue >= 0 && knotenSucceedor.getSumAllSucceedor() >= 0){
			return knotenSucceedor.getAllSucceedor();
		}
		
		
		
		//nur wenn es knotenSucceedor.getSumAllSucceedor() < 0, kann der hier abgehandelte Grenzfall auftreten
		if(knotenSucceedor.getSumAllSucceedor() < 0 && knotenProceedor.isEffectiveSet()){
			boolean[] worthyKnoten = new boolean[alleKnoten.length];
			addArrayBToArrayB(worthyKnoten, knotenSucceedor.getAllSucceedor());
			addArrayBToArrayB(worthyKnoten, knotenProceedor.getAllEffectiveProceedor());
			return worthyKnoten; 
		}
		
		return null;
	}
	

//methods	
	//---succeedor
	
	public LinkedList<Knoten> getDirectSuccessor() {
		return directSuccessorKnoten;
	}

	public KnotenSucceedor getKnotenSucceedor() {
		return knotenSucceedor;
	}
	
	private void addDirectSuccessor(Knoten k){
		directSuccessorKnoten.add(k);
		System.out.println("Nimmst du Knoten Nummer "+ getIndex()+", musst du auch Knoten Nummer "+ k.getIndex()+" nehmen");
	}
	
//---proceedor
	public KnotenProceedor getKnotenProceedor(){
		return knotenProceedor;
	}

	
//-- normale get- und set-Methoden	
	public double getOwnValue() {
		return ownValue;
	}

	public int getIndex() {
		return index;
	}
	
//static methods	
	public static void addKante(Knoten vonKnoten, Knoten zuKnoten){
		vonKnoten.addDirectSuccessor(zuKnoten);
//		zuKnoten.addProceedor(vonKnoten); 
	}

	public static void setAlleKnoten(Knoten[] alleKnoten){
		Knoten.alleKnoten = alleKnoten;
	}
	
	public static void addArrayBToArrayB(boolean[] toArray, boolean[] fromArray){
		for(int i = 0; i < toArray.length; i++){
			toArray[i] = toArray[i] || fromArray[i];
		}
	}
}
