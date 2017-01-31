package modell;

import java.util.LinkedList;

public class KnotenProceedor {
	
	Knoten owner;
	
	private static Knoten[] alleKnoten;
	
	private boolean[] allEffectiveProceedor;
	
	/**wurde die Methode updateFromThis hier schon mal aufgerufen?*/
	private boolean updateFromThisCalled;
	
	KnotenProceedor(Knoten owner){
		assert(alleKnoten != null):"Zuerst muss alleKnoten in KnotenSuccedor initialisiert werden";
		
		updateFromThisCalled = false;
		this.owner = owner;
	}
	
	public boolean isEffectiveSet(){
		return sumAllEffectiveProceedor() > 0; 
	}
	
	private double sumAllEffectiveProceedor(){
		Knoten k;
		double sum = 0;
		for(int i = 0; i< allEffectiveProceedor.length; i++){
			if(allEffectiveProceedor[i]){
				k = alleKnoten[i];
				sum += k.getOwnValue();
			}
		}
		return sum;
	}
	
	/**rekursive Methode
	 * @param proceedor Vorgaenger, der diese Methode aufruft*/
	private void updateFromThis(KnotenProceedor proceedor){
		if(!updateFromThisCalled){
			boolean[] allSucceedor = owner.getKnotenSucceedor().getAllSucceedor();
			Knoten.addArrayBToArrayB(allEffectiveProceedor, allSucceedor);
			updateFromThisCalled = true;
		}
		//wenn es sinnvoll ist "evtlToBeAdded" (sumAllEffectiveProceedor wird dadurch höher), fügt er evtlToBeAdded hinzu
		//und ruft die Methoden für das nachfolgende KnotenProceedor auf
		//TODO vorher sum ausrechnen, bevor man nachfolger aufruft
		//Rekursion
		double sumVorher = sumAllEffectiveProceedor();
		
		boolean[] allEffectiveProceedorVorher = new boolean[allEffectiveProceedor.length];
		for(int i = 0 ; i < allEffectiveProceedor.length; i++){
			allEffectiveProceedorVorher[i] = allEffectiveProceedor[i];
		}
		
		boolean[] evtlToBeAdded = proceedor.getAllEffectiveProceedor();
		Knoten.addArrayBToArrayB(allEffectiveProceedor, evtlToBeAdded);
		
		double sumDanach = sumAllEffectiveProceedor();
		
		if(sumDanach > sumVorher){
			LinkedList<Knoten> directSucceedor = owner.getDirectSuccessor();
			KnotenProceedor kp;
			for(Knoten k : directSucceedor){
				kp = k.getKnotenProceedor();
				kp.updateFromThis(this);
			}
		}else{//rueckgaengig machen 
			allEffectiveProceedor = allEffectiveProceedorVorher;
		}
	}
	
	public boolean[] getAllEffectiveProceedor() {
		return allEffectiveProceedor;
	}
	
	public static void updateAllProceedor(){
		for(Knoten k : alleKnoten){
			k.getKnotenProceedor().updateFromThis(null);
		}
	}
	
	public static void setAlleKnoten(Knoten[] alleKnoten){
		KnotenProceedor.alleKnoten = alleKnoten;
	}
}
