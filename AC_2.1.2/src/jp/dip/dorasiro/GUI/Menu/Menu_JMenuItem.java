package jp.dip.dorasiro.GUI.Menu;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

/**
*@author Dorasiro
*/

public abstract class Menu_JMenuItem extends JMenuItem implements ActionListener {
	/*
	 * フィールド
	 */
	protected JFrame frame;

	/*
	 * コンストラクタ
	 */
	public Menu_JMenuItem(String TITLE, JFrame frame) {
		super(TITLE);
		this.frame = frame;
		this.addActionListener(this);
	}
}
