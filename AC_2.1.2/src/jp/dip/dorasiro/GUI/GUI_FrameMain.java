package jp.dip.dorasiro.GUI;


import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JSeparator;

import jp.dip.dorasiro.AC.AC_ClickService;
import jp.dip.dorasiro.GUI.Menu.Config.Config_ConfigMenuItem;
import jp.dip.dorasiro.GUI.Menu.File.File_ExitMenuItem;
import jp.dip.dorasiro.GUI.Menu.File.File_NewMenuItem;
import jp.dip.dorasiro.GUI.Menu.File.File_OpenMenuItem;
import jp.dip.dorasiro.GUI.Menu.File.File_SaveAtMenuItem;
import jp.dip.dorasiro.GUI.Menu.File.File_SaveMenuItem;
import jp.dip.dorasiro.Library.ini;

/**
*@author Dorasiro
*/

public class GUI_FrameMain {
	/*
	 * フィールド
	 */
	private JFrame frame = new JFrame();
	private AC_ClickService service;
	private GUI_PanelMain panelMain;

	/*
	 * コンストラクタ
	 */
	public GUI_FrameMain() {
		Dimension dimension = new Dimension(300, 343);
		service = new AC_ClickService();
		panelMain = new GUI_PanelMain(service);
		frame.add(panelMain.getPanel());
		service.setObserver(panelMain.getPanelTop());;
		JMenuBar bar = new JMenuBar();
		//ファイルメニュー
		JMenu fileJMenu = new JMenu("ファイル");
		fileJMenu.add(new File_NewMenuItem(frame, panelMain.getPanelCenter()));
		fileJMenu.add(new JSeparator());
		fileJMenu.add(new File_OpenMenuItem(frame, panelMain.getPanelCenter()));
		fileJMenu.add(new File_SaveMenuItem(frame));
		fileJMenu.add(new File_SaveAtMenuItem(frame, panelMain.getPanelCenter()));
		fileJMenu.add(new JSeparator());
		fileJMenu.add(new File_ExitMenuItem(frame));
		JMenu configJMenu = new JMenu("設定");
		configJMenu.add(new Config_ConfigMenuItem(frame, dimension, panelMain.getPanelBottom()));
		//メニューバーに追加
		bar.add(fileJMenu);
		bar.add(configJMenu);
		frame.setJMenuBar(bar);
		frame.setSize(dimension);
		//GUIの設定 サイズのみメソッドで決定
		frame.setTitle("AutoClick!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setAlwaysOnTop(Boolean.valueOf(ini.getValue("Front")));
		//複数起動したときに重ならないようにする
		frame.setLocationByPlatform(true);
		//サイズの変更を禁止
		frame.setResizable(true);
		frame.setVisible(true);

		//スレッドの実行
		service.run();
	}

	/*
	 * メソッド
	 */
	public JFrame getJFrame() {
		return frame;
	}

	public GUI_PanelMain getPanelMain() {
		return panelMain;
	}
}
