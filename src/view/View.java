package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.Controller;


//@SuppressWarnings("serial")
@SuppressWarnings("serial")
public class View extends JFrame {	
	JPanel jp;
	JPanel jpPic;
	BorderLayout bl;
	
	JButton open;
	JButton run;
	
	public JTextArea text;
	JScrollPane sp;
	
	public View() {
		
		Controller controller = new Controller(this);
		
		bl = new BorderLayout();
		getContentPane().setLayout(bl);
		
		jp = new JPanel();
		jp.setLayout(null);
		
		open = new JButton();
		open.setText("oeffnen");
		open.setBounds(50, 300, 100, 50);
		open.addActionListener(controller);
        open.setActionCommand("oeffnen");
        
        run = new JButton();
        run.setText("RUN");
        run.setBounds(200, 300, 100, 50);
        run.addActionListener(controller);
        run.setActionCommand("run");

        sp = new JScrollPane(text = new JTextArea());
        sp.setBounds(50, 50, 400, 200);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        text.setEditable(false);
        
        jpPic = new Bild();
        jpPic.setBounds(500, 50, 400+1000, 300+1000);
        add(jpPic);
        
		jp.add(open);
		jp.add(run);
		jp.add(sp);
		
		this.getContentPane().add(jp, BorderLayout.CENTER);
	}

	
	
	public void start(int breite, int hoehe, String titel){
		setSize(breite, hoehe);
		setTitle(titel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
	}
	
	
	public void fensterStrecken(int breite, int hoehe){
		setSize(this.getWidth()+breite, this.getHeight()+hoehe);
	}
	
	
}
