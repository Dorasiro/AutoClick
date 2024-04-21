package jp.dip.dorasiro.GUI;


import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import jp.dip.dorasiro.AC.AC_Click;
import jp.dip.dorasiro.AC.AC_ClickInterface;
import jp.dip.dorasiro.AC.Event.AC_EventSelect;
import jp.dip.dorasiro.Library.ini;


/**
*@author Dorasiro
*/

public class GUI_PanelCenter implements Observer {
	/*
	 * フィールド
	 */
	//パネル
	private JPanel panel = new JPanel();
	//モデル
	private GUI_TableModel tableModel;
	//テーブル
	private JTable table;

	private final String[][] DEFAULT_TABLE_DATA = {{"1","","",""},{"2","","",""},{"3","","",""},{"4","","",""},{"5","","",""},{"6","","",""},{"7","","",""},{"8","","",""},{"9","","",""},{"10","","",""},{"11","","",""},{"12","","",""},{"13","","",""},{"14","","",""},{"15","","",""},{"16","","",""},{"17","","",""},{"18","","",""},{"19","","",""},{"20","","",""},{"21","","",""},{"22","","",""},{"23","","",""},{"24","","",""},{"25","","",""},{"26","","",""},{"27","","",""},{"28","","",""},{"29","","",""},{"30","","",""},{"31","","",""},{"32","","",""},{"33","","",""},{"34","","",""},{"35","","",""},{"36","","",""},{"37","","",""},{"38","","",""},{"39","","",""},{"40","","",""},{"41","","",""},{"42","","",""},{"43","","",""},{"44","","",""},{"45","","",""},{"46","","",""},{"47","","",""},{"48","","",""},{"49","","",""},{"50","","",""}};
	public static final String[] DEFAULT_COL = {"Index","Timer","ClickCount","Points"};

	private AC_ClickInterface clickInterface;

	//各テーブルの横列の内容を示す定数
	public static final int INDEX_COL = 0;
	public static final int TIMER_COL = 1;
	public static final int CLICK_COUNT_COL = 2;
	public static final int POINT_COL = 3;

	//右クリック時に表示するメニュー
	private GUI_TablePopupMenu popupMenu;

	/*
	 * コンストラクタ
	 */
	public GUI_PanelCenter(KeyListener keyListener, AC_ClickInterface clickInterface) {
		super();
		tableModel = new GUI_TableModel(DEFAULT_TABLE_DATA, DEFAULT_COL);
		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
			//PopupMenuを開く処理
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.isMetaDown()) {
					if(table.getSelectedRow() != -1) {
						//メニュー表示処理
						popupMenu.getPopupMenu().show(table, e.getX(), e.getY());
					}
				}
			}
		});
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() ==  Integer.parseInt(ini.getValue("SpoitKey"))) {
					//マウスの座標を取得
					Point point = MouseInfo.getPointerInfo().getLocation();
					//処理開始後に別の場所がクリックされてもすべての値が同じ行に入れるため、ここであえて変数に代入
					int row = table.getSelectedRow();
					//timerに初期値をセット
					table.setValueAt("0", row, TIMER_COL);
					//ClickCountに初期値をセット
					table.setValueAt("1", row, CLICK_COUNT_COL);
					//座標をセット
					table.setValueAt(point.x+","+point.y, row, POINT_COL);
					//JTableの選択を下のセルに移動できる場合は移動する
					if(row != DEFAULT_TABLE_DATA.length-1) {
						table.changeSelection(row+1, POINT_COL, false, false);
					}
				}
			}
		});
		this.clickInterface = clickInterface;
		clickInterface.setObserver(this);

		table.addKeyListener(keyListener);
		JScrollPane sp = new JScrollPane(table);
		sp.setPreferredSize(new Dimension(278, 210));
		sp.addKeyListener(keyListener);
		panel.add(sp);

		popupMenu = new GUI_TablePopupMenu(this);
		table.add(popupMenu.getPopupMenu());
	}

	/*
	 * メソッド
	 */
	@Override
	public void update(Observable o, Object event) {
		if(event instanceof AC_EventSelect) {
			table.changeSelection(clickInterface.getSelect(), 0, false, false);
		}
	}

	//Tableをリセットする
	public void resetTable() {
		for(int i=0;i<DEFAULT_COL.length;i++) {
			for(int ii=0;ii<20;ii++) {
				table.setValueAt("", ii, i);
			}
		}
		for(int i=1;i<21;i++) {
			table.setValueAt(i, i-1, INDEX_COL);
		}
	}

	//AC_Clickに渡すために表データを整形するメソッド
	public ArrayList<ArrayList<Integer>> crateDataList() throws NullPointerException {
		ArrayList<ArrayList<Integer>> dataList = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> timerList = new ArrayList<Integer>();
		ArrayList<Integer> clickCountList = new ArrayList<Integer>();
		ArrayList<Integer> xList = new ArrayList<Integer>();
		ArrayList<Integer> yList = new ArrayList<Integer>();
		int loopCounter = 0;
		while(!table.getValueAt(loopCounter, TIMER_COL).equals("")) {
			//ここからが実際にArrayListに値を収納する処理
			timerList.add(Integer.parseInt(String.valueOf(table.getValueAt(loopCounter, TIMER_COL))));
			clickCountList.add(Integer.parseInt(String.valueOf(table.getValueAt(loopCounter, CLICK_COUNT_COL))));
			String[] pointString = String.valueOf(table.getValueAt(loopCounter, POINT_COL)).split(",");
			xList.add(Integer.parseInt(pointString[0]));
			yList.add(Integer.parseInt(pointString[1]));
			loopCounter++;
		}
		//dataListに中身を詰める
		dataList.add(AC_Click.TIMER, timerList);
		dataList.add(AC_Click.CLICKCOUNT, clickCountList);
		dataList.add(AC_Click.X, xList);
		dataList.add(AC_Click.Y, yList);

		return dataList;
	}

	//ロード時にArrayList<ArrayList<Integer>>を受け取ってそれを元に表を作成するメソッド
	public void crateTable(ArrayList<ArrayList<Integer>> dataList) {
		//既存の表を書き換える場合に書き換える範囲外のデータが残るのを防ぐためにリセットする
		resetTable();
		for(int i=1;i<DEFAULT_TABLE_DATA.length+1;i++) {
			table.setValueAt(i, i-1, INDEX_COL);
		}

		for(int i=0;i<dataList.get(AC_Click.TIMER).size();i++) {
			table.setValueAt(dataList.get(AC_Click.TIMER).get(i), i, TIMER_COL);
		}

		for(int i=0;i<dataList.get(AC_Click.CLICKCOUNT).size();i++) {
			table.setValueAt(dataList.get(AC_Click.CLICKCOUNT).get(i), i, CLICK_COUNT_COL);
		}

		for(int i=0;i<dataList.get(AC_Click.X).size();i++) {
			table.setValueAt(dataList.get(AC_Click.X).get(i)+","+dataList.get(AC_Click.Y).get(i), i, POINT_COL);
		}

		panel.invalidate();
		panel.validate();
	}

	//Tableに行を挿入するメソッド
	public void insertRow() {
		tableModel.insertRow(table.getSelectedRow(), (Object[])null);
		for(int i=0;i<20;i++) {
			table.setValueAt(String.valueOf(i+1), i, INDEX_COL);
		}
		tableModel.removeRow(20);
	}

	//Tableから行を削除するメソッド
	public void removeRow() {
		tableModel.removeRow(table.getSelectedRow());
		for(int i=0;i<19;i++) {
			table.setValueAt(String.valueOf(i+1), i, GUI_PanelCenter.INDEX_COL);
		}
		Object[] object = {"20", "", "", ""};
		tableModel.insertRow(19, object);
	}

	public JPanel getPanel() {
		return panel;
	}

	public JTable getTable() {
		return table;
	}

	public GUI_TableModel geTableModel() {
		return tableModel;
	}
}
