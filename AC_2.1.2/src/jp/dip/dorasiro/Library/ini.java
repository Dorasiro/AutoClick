package jp.dip.dorasiro.Library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ini {
	/*
	 * フィールド
	 */
	//設定ファイルのパス
	public static String filePath = "config.ini";
	//読み取ったデータを収納するMap
	private static Map<String, String> iniMap = new HashMap<String, String>();
	//キーの一覧を保存しておくList
	private static List<String> keyList = new ArrayList<>();
	//セクション、コメント、改行の位置を記録するMap
	private static Map<Integer, String> map = new HashMap<>();

	//デバッグ用 trueなら標準出力にreadした値とキーの一覧を表示する
	private static final boolean DEBUG = false;

	/*
	 * 初期化子
	 */
	static {
		read();
	}

	/*
	 * メソッド
	 */
	//読み込み
	public static void read() {
		//フィールドの再初期化
		iniMap.clear();
		keyList.clear();
		map.clear();

		File file = new File(filePath);
		int lineCount = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String lineString = "";
			while((lineString = br.readLine()) != null) {
				//空白の場合
				if(lineString.equals("") == false) {
					//頭文字が[あるいは;ではないことを調べる
					if(lineString.substring(0, 1).equals("[") == false) {
						if(lineString.substring(0, 1).equals(";") == false) {
							if(DEBUG) {
								System.out.println(lineString);
							}
							String[] splitStrings = lineString.split("=");
							iniMap.put(splitStrings[0], splitStrings[1]);
							keyList.add(splitStrings[0]);

						}else {
							map.put(lineCount, lineString);
						}

					}else {
						map.put(lineCount, lineString);
					}

				//listに追加
				}else {
					map.put(lineCount, lineString);
				}
				lineCount++;
			}
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//書き込み
	public static void write() {
		File file = new File(filePath);
		try {
			PrintWriter pw = new PrintWriter(file);
			int keyListCount = 0;
			for(int i=0;i<iniMap.size()+map.size();i++) {
				if(map.containsKey(i)) {
					pw.println(map.get(i));
				}else {
					pw.println(keyList.get(keyListCount)+"="+iniMap.get(keyList.get(keyListCount)));
					keyListCount++;
				}
			}
			pw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	//キーの内容を返す
	public static String getValue(String key) {
		return iniMap.get(key);
	}

	//iniMapを初期化する
	public static void clear() {
		iniMap.clear();
	}

	//iniMapに追加
	public static boolean put(String key, String value) {
		iniMap.put(key, value);
		return true;
	}

	//iniMapのサイズを返す
	public static int getSize() {
		return iniMap.size();
	}
}
