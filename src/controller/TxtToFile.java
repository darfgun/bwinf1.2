package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class TxtToFile {
	
	public static void writeDocument(ArrayList<String> output, String path){
		try {
			run(output, path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void run(ArrayList<String> output, String path) throws IOException{
		File bearbeitetesProblem = new File(path);
		String nameOfProblem = bearbeitetesProblem.getName();
		String nameOfFile = "Solution"+ nameOfProblem;
		PrintStream writer = new PrintStream(nameOfFile); 
		for(String str: output) {
			writer.println(str);
			}
			writer.close();
	}
}
