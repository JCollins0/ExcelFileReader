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
		
		this.addTab(projectPanel.getName(), projectPanel);
		this.addTab("Tab 2", projectPanel2);
		
		//setMnemonicAt(0, KeyEvent.VK_1); //Uses "Alt" Key
		//setToolTipTextAt(0, "Tool Tip");

	}
} 
