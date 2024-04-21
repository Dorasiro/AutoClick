package jp.dip.dorasiro.AC;

import java.awt.AWTException;
import java.util.ArrayList;

public class AC_OneClick extends AC_Click{
	/*
	 * コンストラクタ
	 */
	public AC_OneClick(ArrayList<ArrayList<Integer>> dataList) {
		super();
		super.setDataList(dataList);
		try {
			super.robot(0);
			System.exit(0);
		} catch (InterruptedException | AWTException e) {
			e.printStackTrace();
			Thread.interrupted();
		}
	}
}