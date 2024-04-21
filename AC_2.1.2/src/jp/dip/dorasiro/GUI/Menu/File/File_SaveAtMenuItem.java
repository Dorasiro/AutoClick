package jp.dip.dorasiro.GUI.Menu.File;

import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import jp.dip.dorasiro.AC.AC_Click;
import jp.dip.dorasiro.GUI.GUI_PanelCenter;
import jp.dip.dorasiro.GUI.Menu.Menu_JMenuItem_IO;

public class File_SaveAtMenuItem extends Menu_JMenuItem_IO {
	/*
	 * フィールド
	 */
	public static final String TITLE = "名前を付けて保存";
	private GUI_PanelCenter panelCenter;

	/*
	 * コンストラクタ
	 */
	public File_SaveAtMenuItem(JFrame frame, GUI_PanelCenter panelCenter) {
		super(TITLE, frame);
		this.panelCenter = panelCenter;
	}

	//サブクラス用
	protected File_SaveAtMenuItem(String TITLE, JFrame frame) {
		super(TITLE, frame);
	}

	/*
	 * メソッド
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ
		if(chooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			//ファイルの上書き確認or新規作成
			olderFileExist(file);
			fileWrite(file);
		}
	}

	private PrintWriter save(File file) throws IOException {
		//ファイルの拡張子の点検、調整
		PrintWriter pw;
		if((file.toString().substring(file.toString().length() - 4).equals(".sav")) == false) {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".sav"))));
		}else {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		}
		lastOpenFile = file.toString();
		return pw;
	}

	protected void fileWrite(File file) {
		PrintWriter pw;
		try {
			pw = save(file);
			//最後の改行を無くすために使用するカウント これを用いることでサイズが動的に変化しても最後の1回だけを的確に判定できる
			int count = 0;
			//エントリーの大きさで書き込み回数を決める 最後の1回はループ外でするため-1が必要
			for(int i=0;i<panelCenter.crateDataList().get(AC_Click.TIMER).size()-1;i++) {
				pw.println(String.valueOf(i+1)+","+String.valueOf(panelCenter.crateDataList().get(AC_Click.TIMER).get(i))+","+String.valueOf(panelCenter.crateDataList().get(AC_Click.CLICKCOUNT).get(i))+","+String.valueOf(panelCenter.crateDataList().get(AC_Click.X).get(i))+","+String.valueOf(panelCenter.crateDataList().get(AC_Click.Y).get(i))+",");
				count++;
			}
			//最後の一回だけはループ外でprintを使用して書き込む
			pw.print(String.valueOf(count+1)+","+String.valueOf(panelCenter.crateDataList().get(AC_Click.TIMER).get(count))+","+String.valueOf(panelCenter.crateDataList().get(AC_Click.CLICKCOUNT).get(count))+","+String.valueOf(panelCenter.crateDataList().get(AC_Click.X).get(count))+","+String.valueOf(panelCenter.crateDataList().get(AC_Click.Y).get(count))+",");
			pw.close();

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	protected boolean olderFileExist(File file) {
		while(true) {
			//ファイルが存在するかを調べる
			if(file.exists()) {
				switch (JOptionPane.showConfirmDialog(this, file.getName() + " は既に存在します。\n上書きしますか？","上書き保存の確認", JOptionPane.YES_NO_CANCEL_OPTION)) {
					case JOptionPane.YES_OPTION:
						try {
							file.createNewFile();
						} catch (IOException e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						}
						break;
					case JOptionPane.NO_OPTION:
						continue;
					case JOptionPane.CANCEL_OPTION:
						return false;
				}
				break;
			}else {
				//ループから脱出
				break;
			}
		}
		return false;
	}
}
