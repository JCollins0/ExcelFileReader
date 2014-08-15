package gitProject;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ProjectTabbedPanel extends JTabbedPane
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6293692829144127773L;
	
	public static JPanel projectPanel;
	public static JPanel projectPanel2;

	public ProjectTabbedPanel()
	{
		setPreferredSize(new Dimension( ProjectFrame.SIZE,ProjectFrame.SIZE )  );
		
		projectPanel = new ProjectPanel();
		projectPanel2 = new ProjectPanel2();
		
		this.addTab("Tab 1", projectPanel);
		this.addTab("Tab 2", projectPanel2);
	}
}
