package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	// DataProvider 1
	
	@DataProvider(name="LoginData")
	public Object [][] getData() throws IOException{
		
		String path = ".\\testData\\Opencart_testData.xlsx";
		
		ExcelUtility xlutil = new ExcelUtility(path);  // create an object for xlutility
		
		int totalrows = xlutil.getRowCount("Login");
		int totalcols = xlutil.getCellCount("Login", 1);
		
		String logindata[][]=new String[totalrows][totalcols]; // Created for two diamenssional array which can store row and column count
		
		for(int i=1;i<=totalrows;i++) //1  //read the data from xl storing in two dimensional array
		{
			for(int j=0; j<totalcols; j++) // 0  i is row and j is column
			{
				logindata[i-1][j]= xlutil.getCellData("Login", i, j);  // 1,0
						
			}
		}
		
		return logindata; // returning to dimension array
	}
		
		

//		    String path = ".\\testData\\Opencart_testData.xlsx";
//
//		    ExcelUtility xlutil = new ExcelUtility(path);
//
//		    int totalrows = xlutil.getRowCount("Login");
//		    int totalcols = xlutil.getCellCount("Login", 0);
//
//		    Object logindata[][] = new Object[totalrows][totalcols];
//
//		    for(int i = 1; i <= totalrows; i++) {
//
//		        for(int j = 0; j < totalcols; j++) {
//
//		            logindata[i-1][j] =
//		                    xlutil.getCellData("Login", i, j);
//
//		            System.out.print(logindata[i-1][j] + "  ");
//		        }
//
//		        System.out.println();
//		    }
//
//		    return logindata;
//		}
}
