package modell;

import java.util.LinkedList;

public class KnotenSucceedor {
	
	private Knoten owner;
	
	private static Knoten[] alleKnoten;
	
	private boolean[] allSucceedor;
	
	private boolean allSucceedorAktualisiert;
	
	private double sumSucceedor;
	
	private boolean sumSucceedorAktualisiert;
	
	KnotenSucceedor(Knoten besitzer){
		
		assert(alleKnoten != null):"Zuerst muss alleKnoten in KnotenSuccedor initialisiert werden";
		allSucceedor = new boolean[alleKnoten.length];
		
		allSucceedorAktualisiert = false;
		
		//Jeder KnotenSuccedor hat sich selbst als virtuellen Nachfolger, um die Mathematik zu vereinfachen
		//und damit dieser nicht noch einmal hinzugefügt wird
		addKnoten(besitzer);
		
		owner = besitzer;
	}
	
	private void addKnoten(Knoten k){
		allSucceedor[k.getIndex()] = true;
	}
	
	public void updateAllSucceedor(){
		if(!allSucceedorAktualisiert){
			allSucceedorAktualisiert = true;
			LinkedList<Knoten> directSuccedor = owner.getDirectSuccessor();
			KnotenSucceedor ks;
			boolean[] ksb;
			//fuer jeden der Nachfolgerknoten
			for(Knoten k : directSuccedor){
				ks = k.getKnotenSucceedor();
				ks.updateAllSucceedor();
				//wichtig! in ksb sollte k dabei sein
				ksb = ks.getAllSucceedor();
				//Die Knoten der Nachfolger werden hinzugefügt
				Knoten.addArrayBToArrayB(allSucceedor, ksb);
			}
		}
	}
	
	public boolean[] getAllSucceedor(){
		return allSucceedor;
	}
	
	public double getSumAllSucceedor(){
		if(!sumSucceedorAktualisiert){
			sumSucceedorAktualisiert = true;
			assert(sumSucceedor == 0): "sumSucceedorAktualisiert == false, aber sumSucceedor != 0";
			updateAllSucceedor();
			Knoten[] allSucceedorKnoten = getAllSucceedorKnoten();
			for(Knoten k : allSucceedorKnoten){
				if(k != null){
					sumSucceedor += k.getOwnValue();
				}
			}
			
		}
		return sumSucceedor;
	}
	
	//TODO public?
	public Knoten[] getAllSucceedorKnoten(){
		Knoten[] allSucceedorKnoten = new Knoten[allSucceedor.length];
		for(int i = 0; i < allSucceedorKnoten.length; i++){
			if(allSucceedor[i]){
				allSucceedorKnoten[i] = alleKnoten[i];
			}
		}
		return allSucceedorKnoten;
	}
	
	public static void setAlleKnoten(Knoten[] alleKnoten){
		KnotenSucceedor.alleKnoten = alleKnoten;
	}
}
