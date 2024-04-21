package jp.dip.dorasiro.GUI.Menu.File;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class File_SaveMenuItem extends File_SaveAtMenuItem {
	/*
	 * フィールド
	 */
	public static final String TITLE = "上書き保存";

	/*
	 * コンストラクタ
	 */
	public File_SaveMenuItem(JFrame frame) {
		super(TITLE, frame);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/*
	 * メソッド
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(lastOpenFile != null) {
			File file = new File(lastOpenFile);
			fileWrite(file);
		}else {
			if(chooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				fileWrite(file);
			}
		}
	}
}
