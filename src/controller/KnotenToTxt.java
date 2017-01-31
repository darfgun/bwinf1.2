package controller;

import java.util.ArrayList;

import modell.Knoten;
import modell.KnotenVerwaltung;

public class KnotenToTxt {

	private KnotenVerwaltung knotenV;
	private ArrayList<String> output;

	KnotenToTxt(KnotenVerwaltung knotenV){
		this.knotenV = knotenV;
		output = new ArrayList<String>();
	}
	
//---- Ausgabe
	protected ArrayList<String> getOutput() {

			ArrayList<Knoten> wertvolleTeilmenge = knotenV.getWertvolleTeilmenge(); 

			output.add(String.valueOf(wertvolleTeilmenge.size()));
			
			output.add(gesamtwertDerMenge(wertvolleTeilmenge));
			
			for(Knoten k : wertvolleTeilmenge){
				output.add(String.valueOf(k.getIndex()));
			}

		return output;
	}
	
	private String gesamtwertDerMenge(ArrayList<Knoten> wertvolleTeilmenge){
		double sum = 0;
		for(Knoten k : wertvolleTeilmenge){	
			assert(k != null);
				sum += k.getOwnValue();
		}
		return String.valueOf(sum);
	}
}
