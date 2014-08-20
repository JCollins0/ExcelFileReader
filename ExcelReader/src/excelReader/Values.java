package excelReader;

public class Values
{
	private Values(){}
	
	protected static String PROJECT_NUMBER_LOCATION = "C";
	protected static String DESIGN_TYPE_LOCATION = "L";
	protected static String DECISION_LOCATION = "P";
	protected static String DATE_LOCATION = "Q";
	
	protected static String SHEET2_START_CELL = "AM";
	protected static String SHEET2_PROJECT_NUMBER_LOCATION = "AM";
	protected static String SHEET2_CDR_DECISION_LOCATION = "AN";
	protected static String SHEET2_CDR_DATE_LOCATION = "AO";
	protected static String SHEET2_DDR_DECISION_LOCATION = "AP";
	protected static String SHEET2_DDR_DATE_LOCATION = "AQ";
	protected static String SHEET2_PDR_DECISION_LOCATION = "AR";
	protected static String SHEET2_PDR_DATE_LOCATION = "AS";
	protected static String SHEET2_RTA_DECISION_LOCATION = "AT";
	protected static String SHEET2_RTA_DATE_LOCATION = "AU";
	protected static String SHEET2_RTL_DECISION_LOCATION = "AV";
	protected static String SHEET2_RTL_DATE_LOCATION = "AX";
	
	protected static String fileName = "DRRAug.xlsx";
	protected static int ROW_BEFORE_FIRST_ENTRY = 4; //Row to start on
	protected static int ROW_TO_START_COPYING_FROM_ORIGINAL = 4;
	
}
