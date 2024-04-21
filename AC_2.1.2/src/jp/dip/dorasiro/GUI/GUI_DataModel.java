package jp.dip.dorasiro.GUI;

/**
*@author Dorasiro
*/

public class GUI_DataModel {
	/*
	 * フィールド
	 */
	private final String[] DEFAULT_COL = {"No","Timer","ClickCount","Points"};
	private final String[][] DEFAULT_TABLE_DATA =  {{"1","","",""},{"2","","",""},{"3","","",""},{"4","","",""},{"5","","",""},{"6","","",""},{"7","","",""},{"8","","",""},{"9","","",""},{"10","","",""},
									{"11","","",""},{"12","","",""},{"13","","",""},{"14","","",""},{"15","","",""},{"16","","",""},{"17","","",""},{"18","","",""},{"19","","",""},{"20","","",""}};
	public String[] getDefaultCol() {
		return DEFAULT_COL;
	}

	public String[][] getDefaultTableData() {
		return DEFAULT_TABLE_DATA;
	}
}
