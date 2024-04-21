package jp.dip.dorasiro.GUI;

import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import jp.dip.dorasiro.Library.ini;

/**
*@author Dorasiro
*/

public class GUI_PanelBottom {
	/*
	 * フィールド
	 */
	private JPanel panel = new JPanel();

	private JLabel helpLabel;
	private JToggleButton button;

	/*
	 * コンストラクタ
	 */
	public GUI_PanelBottom(KeyListener keyListener, final GUI_Start_Interface start_Interface) {
		panel.setLayout(new FlowLayout());

		//ラベル
		helpLabel = new JLabel(KeyEvent.getKeyText(Integer.parseInt(ini.getValue("SpoitKey")))+"で選択　"+KeyEvent.getKeyText(Integer.parseInt(ini.getValue("StartKey")))+"で開始/終了");
		helpLabel.addKeyListener(keyListener);
		panel.add(helpLabel);

		//トグルボタン
		button = new JToggleButton("停止中");
		button.addKeyListener(keyListener);
		button.addActionListener(e -> start_Interface.start());
		panel.add(button);
	}

	/*
	 * メソッド
	 */
	public void repaintLabel() {
		helpLabel.setText(KeyEvent.getKeyText(Integer.parseInt(ini.getValue("SpoitKey")))+"で選択　"+KeyEvent.getKeyText(Integer.parseInt(ini.getValue("StartKey")))+"で開始/終了切り替え");
		panel.invalidate();
		panel.validate();
	}

	public JPanel getPanel() {
		return panel;
	}

	public JToggleButton getToggleButton() {
		return button;
	}
}
