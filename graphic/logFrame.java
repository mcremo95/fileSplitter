package graphic;

import javax.swing.*;

/**
 * @author Matteo Cremonini
 *
 */
public class logFrame {
	private JFrame jf;
	private JTextArea textArea;
	private JScrollPane sta;

	public logFrame(){
		jf = new JFrame();
		jf.setSize(500, 500);  
        jf.setVisible(true);  
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        textArea = new JTextArea(20, 20);  
        sta = new JScrollPane(textArea);
        
        sta.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        sta.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        
        jf.getContentPane().add(sta);
        
        jf.pack();
	}
	
	public void print(String str) {
		textArea.append(str);
		textArea.append("\n");
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
}