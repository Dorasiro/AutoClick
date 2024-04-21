package jp.dip.dorasiro.GUI;

import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import jp.dip.dorasiro.AC.AC_ClickInterface;
import jp.dip.dorasiro.AC.Event.AC_EventRun;

/**
*@author Dorasiro
*/

public class GUI_PanelTop implements Observer {
	/*
	 * フィールド
	 */
	private JPanel panel = new JPanel();

	private JLabel timerLabel;
	private AC_ClickInterface clickInterface;

	/*
	 * コンストラクタ
	 */
	public GUI_PanelTop(KeyListener keyListener, AC_ClickInterface clickInterface) {
		JLabel messageJLabel = new JLabel("次の実行まで");
		messageJLabel.addKeyListener(keyListener);
		panel.add(messageJLabel);
		timerLabel = new JLabel("None");
		timerLabel.addKeyListener(keyListener);
		panel.add(timerLabel);
		JLabel messageLabel2 = new JLabel("秒");
		messageLabel2.addKeyListener(keyListener);
		panel.add(messageLabel2);

		this.clickInterface = clickInterface;
	}

	/*
	 * メソッド
	 */
	//モデルからのラベル更新
	@Override
	public void update(Observable o, Object event) {
		if(event instanceof AC_EventRun) {
			timerLabel.setText(String.valueOf(clickInterface.getNextTime()));
		}
	}

	/*
	 * セッター＆ゲッター
	 */
	public JPanel getPanel() {
		return panel;
	}

	public JLabel getTimerLabel() {
		return timerLabel;
	}

	public void setTimerLabel(JLabel label) {
		this.timerLabel = label;
	}
}
