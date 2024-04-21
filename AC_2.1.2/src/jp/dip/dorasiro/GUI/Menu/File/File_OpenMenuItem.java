package jp.dip.dorasiro.GUI.Menu.File;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import jp.dip.dorasiro.AC.AC_Click;
import jp.dip.dorasiro.GUI.GUI_PanelCenter;
import jp.dip.dorasiro.GUI.Menu.Menu_JMenuItem_IO;

/**
*@author Dorasiro
*/

public class File_OpenMenuItem extends Menu_JMenuItem_IO {
	/*
	 * フィールド
	 */
	public static final String title = "開く";
	private GUI_PanelCenter panelCenter;

	/*
	 * コンストラクタ
	 */
	public File_OpenMenuItem(JFrame frame ,GUI_PanelCenter panelCenter) {
		super(title, frame);
		this.panelCenter = panelCenter;
	}

	/*
	 * メソッド
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				//1行を保存するための変数
				String lineString = "";
				//収納用の配列を確保
				ArrayList<ArrayList<Integer>> dataList = new ArrayList<ArrayList<Integer>>();
				ArrayList<Integer> timerList = new ArrayList<Integer>();
				ArrayList<Integer> clickCountList = new ArrayList<Integer>();
				ArrayList<Integer> xList = new ArrayList<Integer>();
				ArrayList<Integer> yList = new ArrayList<Integer>();
				//行を,ごとに分割して各配列に収納
				while ((lineString = br.readLine()) != null) {
					String[] splitStrings = lineString.split(",");
					timerList.add(Integer.parseInt(splitStrings[1]));
					clickCountList.add(Integer.parseInt(splitStrings[2]));
					xList.add(Integer.parseInt(splitStrings[3]));
					yList.add(Integer.parseInt(splitStrings[4]));
				}
				br.close();
				dataList.add(AC_Click.TIMER, timerList);
				dataList.add(AC_Click.CLICKCOUNT, clickCountList);
				dataList.add(AC_Click.X, xList);
				dataList.add(AC_Click.Y, yList);
				panelCenter.crateTable(dataList);
				lastOpenFile = file.toString();

			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}
}
