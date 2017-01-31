package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import modell.KnotenVerwaltung;
import view.View;

public class Controller implements ActionListener
{
    private View view;
    private TxtToKnoten toKnotenConvert;
    boolean initiated;
    private KnotenToTxt toTxtConvert;

    public Controller(View pView)
    {
        view = pView;
        initiated = false;
      
    }

    public void actionPerformed (ActionEvent e)
    {
        String cmd = e.getActionCommand();
        
        if (cmd.equals("oeffnen")) 
        {
            this.oeffnen();
        }
        else if (cmd.equals("run"))
        {
            this.run();
        }
     }
    
    public void oeffnen(){
    	String pathOfFile = null; 
    	JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(null);
		
		if(returnVal == JFileChooser.APPROVE_OPTION){
			pathOfFile = fc.getSelectedFile().getAbsolutePath();
		}
		
		String null1 = null;
		this.setProgress(null1);
		setProgress("Die Datei "+fc.getSelectedFile().getName()+" wurde ausgewaehlt.");
		
		try{
			toKnotenConvert = new TxtToKnoten(pathOfFile);
			initiated = true;
		}catch(Exception e){
			setProgress("Fehler beim Lesen der Datei!");
			initiated = false;
		}

	}
    
    public void run(){
    	if(!initiated){
    		return;
    	}
    	
    	setProgress("running");
    	toKnotenConvert.run();
    	
    	KnotenVerwaltung knotenV = toKnotenConvert.getKnotenV();
    	toTxtConvert = new KnotenToTxt(knotenV);
    	ArrayList<String> output = toTxtConvert.getOutput();
    	setProgress(output);
    }
  
    public void setProgress(ArrayList<String> output){
		view.text.setText("");
    	if(output != null){
    		for(String s : output)
    			view.text.append(s+"\n");
    	}
    }
    public void setProgress(String s){
    	view.text.setText("");
    	if(s != null){
    			view.text.append(s+"\n");
    	}
    }
}