package jp.dip.dorasiro.AC;

import java.util.ArrayList;
import java.util.Observer;

/**
*@author Dorasiro
*/

public interface AC_ClickInterface {
	void run();
	boolean getRun();
	void setDataList(ArrayList<ArrayList<Integer>> dataList);
	void setRun(boolean run);
	void setObserver(Observer observer);
	int getNextTime();
	int getSelect();
}
