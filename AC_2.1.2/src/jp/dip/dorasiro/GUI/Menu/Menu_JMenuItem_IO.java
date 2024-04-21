package jp.dip.dorasiro.GUI.Menu;


import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import jp.dip.dorasiro.Library.ini;

public abstract class Menu_JMenuItem_IO extends Menu_JMenuItem {
	/*
	 * フィールド
	 */
	protected final JFileChooser chooser;
	protected static String lastOpenFile;

	//保存場所、拡張子の指定をするための定数
	public static final String FILE_PATH = ini.getValue("DataFile");
	public static final String FILE_ECTENSION = "sav";

	/*
	 * コンストラクタ
	 */
	public Menu_JMenuItem_IO(String TITLE, JFrame frame) {
		super(TITLE, frame);
		chooser = new JFileChooser(FILE_PATH);
		FileFilter filter = new FileNameExtensionFilter("AutoClick保存ファイル", FILE_ECTENSION);
		chooser.addChoosableFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(false);
	}
}
