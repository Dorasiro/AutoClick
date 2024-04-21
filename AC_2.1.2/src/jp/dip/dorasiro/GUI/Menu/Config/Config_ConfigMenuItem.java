package jp.dip.dorasiro.GUI.Menu.Config;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import jp.dip.dorasiro.GUI.GUI_PanelBottom;
import jp.dip.dorasiro.GUI.Menu.Menu_JMenuItem_IO;

public class Config_ConfigMenuItem extends Menu_JMenuItem_IO {
	/*
	 * フィールド
	 */
	public static final String TITLE = "設定";
	private GUI_PanelBottom panelBottom;

	/*
	 * コンストラクタ
	 */
	public Config_ConfigMenuItem(JFrame frame, Dimension d, GUI_PanelBottom panelBottom) {
		super(TITLE, frame);
		this.panelBottom = panelBottom;
	}
	/*
	 * メソッド
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		frame.setAlwaysOnTop(false);
		new Config_ConfigFrame(frame.getLocation(), this.chooser, panelBottom);
	}

}
