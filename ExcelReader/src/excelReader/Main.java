package excelReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main
{
	
	public static void main(String[] args)
	{
		new Main();
		
	}
	
	private XSSFWorkbook doc;
	private FileInputStream fis;
	private FileOutputStream fos;
	
	private ArrayList<String> headings = new ArrayList<String>();
	private int dataCount = 0;
	 
	
	private ArrayList<Data> dataList = new ArrayList<Data>();
	
	
	public Main()
	{
		fillArray();
		readFile();
	}
	
	public void readFile()
	{
		try {
			fis = new FileInputStream(Values.fileName);
			doc = new XSSFWorkbook(fis);
			XSSFSheet sheet = doc.getSheetAt(0);

			XSSFRow row = sheet.getRow(Values.ROW_BEFORE_FIRST_ENTRY);

			XSSFCell cell = row.getCell(0);

			while( Values.ROW_BEFORE_FIRST_ENTRY < sheet.getPhysicalNumberOfRows() )
			{
				
				
				cell = row.getCell(CellReference.convertColStringToIndex(Values.PROJECT_NUMBER_LOCATION) );
				
				String projectNum = cell.getStringCellValue();
				String[] designType= new String[6];
				String[] decision = new String[6];
				String[] decisionDate = new String[6];
				dataCount = 0;
				
				while(projectNum.equals( cell.getStringCellValue() ) )
				{
					
					cell = row.getCell(CellReference.convertColStringToIndex(Values.DESIGN_TYPE_LOCATION) ); //Design
					designType[dataCount] = cell.getStringCellValue();

					cell = row.getCell(CellReference.convertColStringToIndex(Values.DECISION_LOCATION)); //Decision

					decision[dataCount] = cell.getStringCellValue();

					cell = row.getCell(CellReference.convertColStringToIndex(Values.DATE_LOCATION) ); //Date
					try{
					decisionDate[dataCount] = df.format( cell.getDateCellValue() );
					}catch(Exception e)
					{
						decisionDate[dataCount] = null;
					}
					row = sheet.getRow(++Values.ROW_BEFORE_FIRST_ENTRY);
					dataCount++;

					if(Values.ROW_BEFORE_FIRST_ENTRY == sheet.getPhysicalNumberOfRows()-1)
						break;
					else
						cell = row.getCell(CellReference.convertColStringToIndex(Values.PROJECT_NUMBER_LOCATION) );
				}
				
				
				Data dat = new Data(projectNum,designType,decision,decisionDate);
				dataList.add(dat);
				if(Values.ROW_BEFORE_FIRST_ENTRY == sheet.getPhysicalNumberOfRows()-1)
					break;
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			for(int i = Values.ROW_TO_START_COPYING_FROM_ORIGINAL; i < doc.getSheetAt(0).getPhysicalNumberOfRows(); i++){
				doc.getSheetAt(1).createRow(i-Values.ROW_TO_START_COPYING_FROM_ORIGINAL);
				doc.getSheetAt(1).createRow(i-Values.ROW_TO_START_COPYING_FROM_ORIGINAL+1);
				writeOriginalData(i);
			}
			
			System.out.println("Done Writing");
			try {
				fos = new FileOutputStream(Values.fileName);
				doc.write(fos);
				System.out.println("Saved");
				fos.close();
				doc.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

	}
	private int dataListCount = 0;
	public void writeOriginalData(int row)
	{
		XSSFRow destRow = doc.getSheetAt(1).getRow(row-Values.ROW_TO_START_COPYING_FROM_ORIGINAL);
		
		for(int i = 0; i <= doc.getSheetAt(0).getRow(row-1).getPhysicalNumberOfCells(); i++)
		{
			XSSFCell oldCell = doc.getSheetAt(0).getRow(row-1).getCell(i);
			XSSFCell newCell = destRow.createCell(i);
			
			if(i == CellReference.convertColStringToIndex(Values.SHEET2_PROJECT_NUMBER_LOCATION)-1 && row-Values.ROW_TO_START_COPYING_FROM_ORIGINAL < dataList.size())
				for(int j = 0; j < headings.size(); j++)
				{
					XSSFCell destCell = destRow.createCell( CellReference.convertColStringToIndex(Values.SHEET2_PROJECT_NUMBER_LOCATION)+j );
					destCell = destRow.getCell(   CellReference.convertColStringToIndex(Values.SHEET2_PROJECT_NUMBER_LOCATION)+j  );
					if(destRow.getRowNum() == 0)
					{
						destCell.setCellType(XSSFCell.CELL_TYPE_STRING);
						destCell.setCellValue(headings.get(j));
						
					}else
					{
						
						
						XSSFCell cell;
						
						cell = destRow.createCell( j+   CellReference.convertColStringToIndex(Values.SHEET2_PROJECT_NUMBER_LOCATION) );
						cell = destRow.createCell(   j+   CellReference.convertColStringToIndex(Values.SHEET2_PROJECT_NUMBER_LOCATION) +1);
						
						switch(j)
						{
						case 0:
							
							cell = destRow.getCell(CellReference.convertColStringToIndex(Values.SHEET2_PROJECT_NUMBER_LOCATION)); //Project Number
						
							setCellString(cell, dataList.get(dataListCount).getProjectNumber());
							
							break;
						case 1:
							
							cell = destRow.getCell(CellReference.convertColStringToIndex( Values.SHEET2_CDR_DECISION_LOCATION)); //CDR Decision
						
							setCellString(cell, dataList.get(dataListCount).getDecision("CDR"));
							
							
							break;
						case 2:
							
							cell = destRow.getCell(CellReference.convertColStringToIndex(Values.SHEET2_CDR_DATE_LOCATION));
						
							setCellString(cell, dataList.get(dataListCount).getDecisionDate("CDR"));
							
							
							break;
							
						case 3:
							
							cell = destRow.getCell(CellReference.convertColStringToIndex(Values.SHEET2_DDR_DECISION_LOCATION));
						
							setCellString(cell, dataList.get(dataListCount).getDecision("DDR")); 
							
							
							break;

						case 4:
							
							cell = destRow.getCell(CellReference.convertColStringToIndex(Values.SHEET2_DDR_DATE_LOCATION));
							
							setCellString(cell, dataList.get(dataListCount).getDecisionDate("DDR"));
							
							
							
							break;
						case 5:
							
							
							cell = destRow.getCell(CellReference.convertColStringToIndex(Values.SHEET2_PDR_DECISION_LOCATION));
						
							setCellString(cell, dataList.get(dataListCount).getDecision("PDR"));
							
							
							break;
						case 6:
							
							
							cell = destRow.getCell(CellReference.convertColStringToIndex(Values.SHEET2_PDR_DATE_LOCATION));
						
							setCellString(cell, dataList.get(dataListCount).getDecisionDate("PDR")); 
							
							
							break;
						case 7:
							
							cell = destRow.getCell(CellReference.convertColStringToIndex(Values.SHEET2_RTA_DECISION_LOCATION));
						
							setCellString(cell, dataList.get(dataListCount).getDecision("RTA")); 
							
							
							break;
						case 8:
							
							cell = destRow.getCell(CellReference.convertColStringToIndex(Values.SHEET2_RTA_DATE_LOCATION));
						
							setCellString(cell, dataList.get(dataListCount).getDecisionDate("RTA")); 
							
							
							break;
						case 9:
							
							cell = destRow.getCell(CellReference.convertColStringToIndex(Values.SHEET2_RTL_DECISION_LOCATION));
						
							setCellString(cell, dataList.get(dataListCount).getDecision("RTL")); 
							
							break;
						case 10:
							
							cell = destRow.getCell(CellReference.convertColStringToIndex(Values.SHEET2_RTL_DATE_LOCATION));
						
							setCellString(cell, dataList.get(dataListCount++).getDecisionDate("RTL"));
							
							break;
						}
					}
				}
			
			if (oldCell == null) {
				newCell = null;
				continue;
			}

			newCell.setCellType(oldCell.getCellType());

			switch (oldCell.getCellType()) {
			case XSSFCell.CELL_TYPE_STRING:
				newCell.setCellValue(oldCell.getRichStringCellValue());
				break;
			case XSSFCell.CELL_TYPE_BLANK:
				break;
			default:
				
				newCell.setCellValue(df.format(oldCell.getDateCellValue()));
				
				break;
			}


		}
	}
	
	public void setCellString(XSSFCell cell,String data)
	{
		cell.setCellType(XSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(data);
	}
	
	private SimpleDateFormat df = new SimpleDateFormat("M/dd/yy");
	
	public void fillArray()
	{
		headings.add("Op #");
		headings.add("CDR_Decision");
		headings.add("CDR_Decision_Date");
		headings.add("DDR_Decision");
		headings.add("DDR_Decision_Date");
		headings.add("PDR_Decision");
		headings.add("PDR_Decision_Date");
		headings.add("RTA_Decision");
		headings.add("RTA_Decision_Date");
		headings.add("RTL_Decision");
		headings.add("RTL_Decision_Date");
		
	}
	public void message(String m)
	{
		System.out.println(m);
	}
}
