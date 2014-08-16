package gitProject;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ProjectPanel2 extends JPanel 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8991699199781803162L;
	private JTextArea textArea = new JTextArea(10,44);
	
	public ProjectPanel2()
	{
		setPreferredSize(new Dimension( ProjectFrame.SIZE,ProjectFrame.SIZE )  );
		
		textArea.setAlignmentX(5);
		textArea.setAlignmentY(5);
		this.add(textArea);
	}
 
}
