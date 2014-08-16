package gitProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class ProjectPanel extends JPanel 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8157901891430086629L;
	
	private JSlider red = new JSlider(0, 255);
	private JSlider blue = new JSlider(0, 255);
	private JSlider green = new JSlider(0, 255);
	private JLabel redLbl = new JLabel("RED: 0");
	private JLabel blueLbl = new JLabel("BLUE: 0");
	private JLabel greenLbl = new JLabel("GREEN: 0");
	
	private int redValue = 0;
	private int blueValue = 0;
	private int greenValue = 0;
	
	private JPanel colorPanel = new ColorDisplayPanel();
	
	
	public ProjectPanel()
	{ 
		setPreferredSize(new Dimension( ProjectFrame.SIZE,ProjectFrame.SIZE )  );
		
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setAutoCreateGaps(true);
		
		layout.setHorizontalGroup(layout.createParallelGroup()

			    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING))
			        .addComponent(redLbl, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			        .addComponent(blueLbl, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			        .addComponent(greenLbl, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING))
			        .addComponent(red)
			        .addComponent(blue)
			        .addComponent(green)
			    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING))
			    	.addComponent(colorPanel)

			);
		layout.setVerticalGroup(layout.createSequentialGroup()

			    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE))
			            .addComponent(redLbl)
			            .addComponent(red)
			    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE))
			            .addComponent(blueLbl)
			            .addComponent(blue)
			    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE))
			            .addComponent(greenLbl)
			            .addComponent(green)
			    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE))
			    		.addComponent(colorPanel)
			);
	
			red.setValue(redValue);
			blue.setValue(blueValue);
			green.setValue(greenValue);
			
			red.addChangeListener(new ChangeListener()
			{
				
				@Override
				public void stateChanged(ChangeEvent c)
				{
					redValue = red.getValue();
					redLbl.setText("RED: " + red.getValue());
					((ColorDisplayPanel) colorPanel).changeColor(new Color(redValue,greenValue,blueValue));
				}
			});
			blue.addChangeListener(new ChangeListener()
			{
				
				@Override
				public void stateChanged(ChangeEvent c)
				{
					blueValue = blue.getValue();
					blueLbl.setText("BLUE: " + blue.getValue());
					((ColorDisplayPanel) colorPanel).changeColor(new Color(redValue,greenValue,blueValue));
				}
			});
			green.addChangeListener(new ChangeListener()
			{
				
				@Override
				public void stateChanged(ChangeEvent c) 
				{
					greenValue = green.getValue();
					greenLbl.setText("GREEN: " + green.getValue());
					((ColorDisplayPanel) colorPanel).changeColor(new Color(redValue,greenValue,blueValue));
				}
			});
			
	}
 
	public String getName()
	{
		return "Color Chooser";
	}
	
	private class ColorDisplayPanel extends JPanel
	{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1903066984191746894L;

		public ColorDisplayPanel()
		{
			setPreferredSize(new Dimension(100,100));
			setBackground(Color.white);
		}
		
		public void changeColor(Color color)
		{
			this.setBackground(color);
		}
	}
}
