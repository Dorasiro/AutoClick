package jp.dip.dorasiro.GUI;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class GUI_TablePopupMenu {
	/*
	 * フィールド
	 */
	private JPopupMenu popupMenu;

	private JMenuItem insertMenuItem;
	private JMenuItem deleteMenuItem;

	/*
	 * コンストラクタ
	 */
	public GUI_TablePopupMenu(final GUI_PanelCenter table) {
		popupMenu = new JPopupMenu();

		insertMenuItem = new JMenuItem("行を挿入");
		insertMenuItem.addActionListener(
			e -> table.insertRow());
		deleteMenuItem = new JMenuItem("行を削除");
		deleteMenuItem.addActionListener(
			e -> table.removeRow());
		popupMenu.add(insertMenuItem);
		popupMenu.add(deleteMenuItem);

		popupMenu.pack();
	}

	/*
	 * メソッド
	 */
	public JPopupMenu getPopupMenu() {
		return popupMenu;
	}
}
