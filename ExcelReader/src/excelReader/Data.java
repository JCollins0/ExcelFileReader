package excelReader;

import java.util.ArrayList;

public class Data {

	private String projectNumber;
	private ArrayList<String> decision;
	private ArrayList<String> designReviewType;
	private ArrayList<String> decisionDate;
	
	public Data()
	{
		
	}
	
	public Data(String projectNumber,String[] designReviewType, String[] decision,String[] decisionDate )
	{
		this.projectNumber = projectNumber;
		
		this.decision = convertDataToArrayList(decision);
		this.designReviewType = convertDataToArrayList(designReviewType);
		this.decisionDate = convertDataToArrayList(decisionDate);
		
		for(int i = 0; i < this.decision.size(); i++)
		{
			if(this.decision.get(i) == null)
				this.decision.set(i, "-");
			//System.out.println(decision[i]);
		}
		
		for(int i = 0; i < this.designReviewType.size(); i++)
		{
			if(this.designReviewType.get(i) == null)
				this.designReviewType.set(i, "-");
			//System.out.println(this.designReviewType.get(i));
		}
		
		for(int i = 0; i < this.decisionDate.size(); i++)
		{
			if(this.decisionDate.get(i) == null)
				this.decisionDate.set(i, "-");
			//System.out.println(decisionDate[i]);
		}
		
		
	}

	public ArrayList<String> convertDataToArrayList(String[] dataToConvert)
	{
		
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < dataToConvert.length; i++) {
			list.add(dataToConvert[i]);
		}
		
		return list;
	}
	public String getProjectNumber() {
		return projectNumber;
	}
	
	public String getDecision(String search) {
		try{
		return decision.get(getPositionIndex(search));
		}catch(Exception e)
		{
			return " ";
		}
	}

	public String getDecisionDate(String search) {
		try{
		return decisionDate.get(getPositionIndex(search) );
		}catch(Exception e)
		{
			return " ";
		}
	}
	
	public int getPositionIndex(String search)
	{
		return designReviewType.indexOf(search);
	}
	
	
}
