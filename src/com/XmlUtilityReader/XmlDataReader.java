package com.XmlUtilityReader;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class XmlDataReader {
	
		Workbook wb;
	//To read Path of ExcelFile
		public XmlDataReader(String excelpath)throws Throwable
		{
			FileInputStream fi=new FileInputStream(excelpath);
			wb=WorkbookFactory.create(fi);
		}
	//Count Number of Rows
		public int rowCount(String sheetname)
		{
			return wb.getSheet(sheetname).getLastRowNum();
		}
	//Count Number of Columns
		public int colCount(String sheetname)
		{
			return wb.getSheet(sheetname).getRow(0).getLastCellNum();
		}
	//Get cell data from Excel Sheet
		public String getCellData(String sheetname,int row,int column)
		{
			String data="";
			if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==CellType.NUMERIC)
			{
				int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
				data=String.valueOf(celldata);
			}
			else
			{
				data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
			}
			return data;
		}
	//Set cell data to Excel Sheet
		public void setCellData(String sheetname,int row,int column,String status,String outputexcel) throws Throwable
		{
			//get sheet from Workbook/wb
			Sheet ws=wb.getSheet(sheetname);
			Row rownum=ws.getRow(row);
			//Cell is an interface used for cell creation in excel sheet and perform operation also
			Cell cell=rownum.createCell(column);
			cell.setCellValue(status);
			if(status.equalsIgnoreCase("Pass"))
			{
				//create cell Style
				CellStyle style=wb.createCellStyle();
				//create fony style
				Font font=wb.createFont();
				//Apply color to text
				font.setColor(IndexedColors.BRIGHT_GREEN.getIndex());
				//Apply bold to text
				font.setBold(true);
				//font.setBoldweight(Font.BOLDWEIGHT_BOLD);
				style.setFont(font);
				rownum.getCell(column).setCellStyle(style);
			}
			else if(status.equalsIgnoreCase("Fail"))
			{
				CellStyle style=wb.createCellStyle();
				Font font=wb.createFont();
				font.setColor(IndexedColors.RED.getIndex());
				font.setBold(true);
				//font.setBoldweight(Font.BOLDWEIGHT_BOLD);
				style.setFont(font);
				rownum.getCell(column).setCellStyle(style);
			}
			else if(status.equalsIgnoreCase("Blocked"))
			{
				CellStyle style=wb.createCellStyle();
				Font font=wb.createFont();
				font.setColor(IndexedColors.MAROON.getIndex());
				font.setBold(true);
				//font.setBoldweight(Font.BOLDWEIGHT_BOLD);
				style.setFont(font);
				rownum.getCell(column).setCellStyle(style);
			}
			FileOutputStream fo=new FileOutputStream(outputexcel);
			wb.write(fo);
		}
	

}
