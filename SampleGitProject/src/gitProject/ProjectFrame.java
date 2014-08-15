package gitProject;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class ProjectFrame extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7271460466418027103L;
	
	public static final int SIZE = 500;
	private static JTabbedPane projectTabbedPanel1;
	
	
	public static void main(String[] args)
	{
		new ProjectFrame().setVisible(true);
	}
	
	public ProjectFrame()
	{
		super("Git_Project");
		projectTabbedPanel1 = new ProjectTabbedPanel();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(SIZE,SIZE);
		getContentPane().add(projectTabbedPanel1);
		setLocationRelativeTo(null);
		pack();
	}

}
