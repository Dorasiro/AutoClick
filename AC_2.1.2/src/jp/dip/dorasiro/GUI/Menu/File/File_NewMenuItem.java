package jp.dip.dorasiro.GUI.Menu.File;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import jp.dip.dorasiro.GUI.GUI_PanelCenter;
import jp.dip.dorasiro.GUI.Menu.Menu_JMenuItem;

public class File_NewMenuItem extends Menu_JMenuItem {
	/*
	 * フィールド
	 */
	public static final String TITLE = "新規";
	private GUI_PanelCenter panelCenter;

	/*
	 * コンストラクタ
	 */
	public File_NewMenuItem(JFrame frame, GUI_PanelCenter panelCenter) {
		super(File_NewMenuItem.TITLE, frame);
		this.panelCenter = panelCenter;
	}

	/*
	 * メソッド
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		panelCenter.resetTable();
	}
}
