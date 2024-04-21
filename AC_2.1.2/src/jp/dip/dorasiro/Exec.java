package jp.dip.dorasiro;

import java.util.ArrayList;

import jp.dip.dorasiro.AC.AC_Click;
import jp.dip.dorasiro.AC.AC_OneClick;
import jp.dip.dorasiro.GUI.GUI_FrameMain;

public class Exec {
	public static void main(String[] args) {
		if(args.length == 0) {
			new GUI_FrameMain();

		}else {
			String[] splitStrings = args[0].split(",");
			ArrayList<ArrayList<Integer>> dataList = new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> timerList = new ArrayList<Integer>();
			ArrayList<Integer> clickCountList = new ArrayList<Integer>();
			ArrayList<Integer> xList = new ArrayList<Integer>();
			ArrayList<Integer> yList = new ArrayList<Integer>();

			timerList.add(0);
			clickCountList.add(Integer.parseInt(splitStrings[0]));
			xList.add(Integer.parseInt(splitStrings[1]));
			yList.add(Integer.parseInt(splitStrings[2]));

			dataList.add(AC_Click.TIMER, timerList);
			dataList.add(AC_Click.CLICKCOUNT, clickCountList);
			dataList.add(AC_Click.X, xList);
			dataList.add(AC_Click.Y, yList);

			new AC_OneClick(dataList);
		}
	}
}
