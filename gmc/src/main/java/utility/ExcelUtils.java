package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;




public class ExcelUtils {

		//private static XSSFSheet ExcelWSheet;
		private static HSSFSheet ExcelWSheet;
		//private static XSSFWorkbook ExcelWBook;
		private static HSSFWorkbook ExcelWBook;
		//private static XSSFCell Cell;
		private static HSSFCell Cell;
		//private static XSSFRow Row;
		private static HSSFRow Row;
		public static Properties prop;

	public static void setExcelFile(String Path,String SheetName) throws Exception {
		try {
			// Open the Excel File
			FileInputStream ExcelFile = new FileInputStream(Path);
			//ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWBook = new HSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			
			} catch (Exception e) {
			// TODO: handle exception
			throw (e);
			}
	}
	
	//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
	public static String getCellData(int RowNum,int ColNum) {
		try {
			String CellDataa = null;
			DataFormatter df = new DataFormatter();
			Cell=ExcelWSheet.getRow(RowNum).getCell(ColNum);
			CellType type = Cell.getCellTypeEnum();
			if(type==CellType.STRING) {
				CellDataa=Cell.getStringCellValue();
			}else if(type==CellType.NUMERIC) {
				CellDataa=df.formatCellValue(Cell);
			
			}
			return CellDataa;
		}catch (Exception e) {
			// TODO: handle exception
			return"";
			
		}
		
		}
	
	public static void setCellData(String Result,int RowNum,int ColNum) throws Exception {
		 try{
             System.out.println("rownum is "+ RowNum + "  "+ ColNum);
             Row=ExcelWSheet.getRow(RowNum);
             System.out.println(Row);
             Cell=Row.getCell(ColNum);
             System.out.println(Cell);
			 Cell=ExcelWSheet.getRow(RowNum).getCell(ColNum,MissingCellPolicy.RETURN_BLANK_AS_NULL);
			 System.out.println("this is new celll "+ Cell);
			 if(Cell==null) {
				 Cell= Row.createCell(ColNum);
				 Cell.setCellValue(Result);
			 }else {
				 Cell.setCellValue(Result);
			 }
			 System.out.println(Result);
 // Constant variables Test Data path and Test Data file name
                 FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);
                 ExcelWBook.write(fileOut);
                 fileOut.flush();
               fileOut.close();
               }catch(Exception e){
                   throw (e);
           }
		
		}
	
		
	
	
	public static int getRowContains(String sTestCaseName, int colNum) throws Exception{
		int i;
		try {
			int rowCount = ExcelUtils.getRowUsed();
			for ( i=0 ; i<rowCount; i++){
				if  (ExcelUtils.getCellData(i,colNum).equalsIgnoreCase(sTestCaseName)){
					break;
				}
			}
			return i;
				}catch (Exception e){
			throw(e);
			}
		}
	
	public static int getRowUsed() throws Exception {
		try{
			int RowCount = ExcelWSheet.getLastRowNum();
			return RowCount;
		}catch (Exception e){
			System.out.println(e.getMessage());
			throw (e);
		}

	}
	
	public static String checkRunMode(int itestCaseRow,int ColNum) {
		return ExcelUtils.getCellData(itestCaseRow, ColNum);
		
	}
}
