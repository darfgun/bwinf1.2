package modell;

import java.util.LinkedList;

public class KnotenProceedor {
	
	Knoten owner;
	
	private static Knoten[] alleKnoten;
	
	private boolean[] allEffectiveProceedor;
	
	KnotenProceedor(Knoten owner){
		assert(alleKnoten != null):"Zuerst muss alleKnoten in KnotenSuccedor initialisiert werden";
		
		allEffectiveProceedor = null;
		
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
		
		if(allEffectiveProceedor == null){
			allEffectiveProceedor = owner.getKnotenSucceedor().getAllSucceedor();
		}
		//wenn es sinnvoll ist "evtlToBeAdded" (sumAllEffectiveProceedor wird dadurch höher), fügt er evtlToBeAdded hinzu
		//und ruft die Methoden für das nachfolgende KnotenProceedor auf
		//Rekursion
		if(proceedor != null){
			double sumVorher = sumAllEffectiveProceedor();
			
			boolean[] allEffectiveProceedorVorher = new boolean[allEffectiveProceedor.length];
			for(int i = 0 ; i < allEffectiveProceedor.length; i++){
				allEffectiveProceedorVorher[i] = allEffectiveProceedor[i];
			}
			
			boolean[] evtlToBeAdded = proceedor.getAllEffectiveProceedor();
			Knoten.addArrayBToArrayB(allEffectiveProceedor, evtlToBeAdded);
			
			double sumDanach = sumAllEffectiveProceedor();

			if(sumDanach <= sumVorher){//rueckgaengig machen 
				allEffectiveProceedor = allEffectiveProceedorVorher;
				return;
			}
		}
		
		LinkedList<Knoten> directSucceedor = owner.getDirectSuccessor();
		KnotenProceedor kp;
		for(Knoten k : directSucceedor){
			kp = k.getKnotenProceedor();
			kp.updateFromThis(this);
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
