package jp.dip.dorasiro.GUI.Menu.File;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import jp.dip.dorasiro.GUI.Menu.Menu_JMenuItem;

public class File_ExitMenuItem extends Menu_JMenuItem {
	/*
	 * フィールド
	 */
	public static final String TITLE = "終了";
	/*
	 * コンストラクタ
	 */
	public File_ExitMenuItem(JFrame frame) {
		super(TITLE, frame);
	}

	/*
	 * メソッド
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
	}

}
