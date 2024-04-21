package jp.dip.dorasiro.GUI;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import jp.dip.dorasiro.AC.AC_ClickInterface;
import jp.dip.dorasiro.Library.ErrorDialog;
import jp.dip.dorasiro.Library.ini;

public class GUI_PanelMain implements GUI_Start_Interface, KeyListener {
	/*
	 * フィールド
	 */
	private JPanel panel = new JPanel();

	protected GUI_PanelTop panelTop;
	protected GUI_PanelBottom panelBottom;
	protected AC_ClickInterface clickInterface;

	private GUI_PanelCenter panelCenter;


	/*
	 * コンストラクタ
	 */
	public GUI_PanelMain(AC_ClickInterface clickInterface) {
		//フィールドの初期化
		panelTop = new GUI_PanelTop(this, clickInterface);
		panelBottom = new GUI_PanelBottom(this, this);
		panelCenter = new GUI_PanelCenter(this, clickInterface);
		this.clickInterface = clickInterface;

		panel.setLayout(new BorderLayout());
		panel.add(panelTop.getPanel(), BorderLayout.NORTH);
		panel.add(panelCenter.getPanel(), BorderLayout.CENTER);
		panel.add(panelBottom.getPanel(), BorderLayout.SOUTH);
	}

	/*
	 * メソッド
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == Integer.parseInt(ini.getValue("StartKey"))) {
			start();
		}
	}

	@Override
	public void start() {
		try {
			if(clickInterface.getRun() == false) {
				//セルの編集中ならそれを中断する これをしないとセルの編集を確定し忘れた場合に正確な値が表示されない
				if(panelCenter.getTable().isEditing()) {
					panelCenter.getTable().getCellEditor().stopCellEditing();
				}
				clickInterface.setDataList(panelCenter.crateDataList());
				clickInterface.setRun(true);
				panelBottom.getToggleButton().setText("実行中");
			}else {
				clickInterface.setRun(false);
				panelBottom.getToggleButton().setText("停止中");
			}
		}catch (NullPointerException npe) {
			new ErrorDialog(panel, "行を空白にすることはできません");
			panelBottom.getToggleButton().setSelected(false);
		}
	}
	//ゲッター＆セッター
	public JPanel getPanel() {
		return panel;
	}

	public GUI_PanelTop getPanelTop() {
		return panelTop;

	}

	public GUI_PanelCenter getPanelCenter() {
		return panelCenter;
	}

	public GUI_PanelBottom getPanelBottom() {
		return panelBottom;
	}

	//キーリスナー
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
}
