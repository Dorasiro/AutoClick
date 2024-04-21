package jp.dip.dorasiro.AC;

import java.util.ArrayList;
import java.util.Observer;

/**
*@author Dorasiro
*/

public class AC_ClickService implements AC_ClickInterface{
	/*
	 * フィールド
	 */
	private AC_Click click;

	/*
	 * コンストラクタ
	 */
	public AC_ClickService() {
		click = new AC_Click();
	}


	/*
	 * メソッド
	 */
	@Override
	public void run() {
		click.run();
	}

	@Override
	public boolean getRun() {
		return click.getRun();
	}

	@Override
	public void setDataList(ArrayList<ArrayList<Integer>> dataList) {
		click.setDataList(dataList);
	};

	@Override
	public void setRun(boolean run) {
		click.setRun(run);
	}

	@Override
	public void setObserver(Observer observer) {
		click.setObserver(observer);
	}

	@Override
	public int getNextTime() {
		return click.getNextTime();
	}

	@Override
	public int getSelect() {
		return click.getSelect();
	}

}
