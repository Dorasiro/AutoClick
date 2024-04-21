package jp.dip.dorasiro.Library;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;

public class ConfigProperties{
	/*
	 *フィールド
	 */
	protected static Properties properties;
	protected static JFrame frame;

	/*
	 * コンストラクタ
	 */
	//config.propertiesを参照する警告フレームなしのコンストラクタ
	//非推奨
	public ConfigProperties() {
		properties = new Properties();
		try {
			properties.load(new FileInputStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//config.propertiesを参照する警告フレームありのコンストラクタ
	public ConfigProperties(JFrame frame) {
		properties = new Properties();
		ConfigProperties.frame = frame;
		try {
			properties.load(new FileInputStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			new ErrorDialog(frame, "IOException");
		}
	}


	public ConfigProperties(JFrame frame, String fileName) {
		properties = new Properties();
		ConfigProperties.frame = frame;
		try {
			properties.load(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			 new ErrorDialog(frame, "FileNtFoundException");
		} catch (IOException e) {
			e.printStackTrace();
			new ErrorDialog(frame, "IOException");
		}
	}

	/*
	 * メソッド
	 */
	//config.propertiesからkeyの値を表示するメソッド
	public static Object objectReadKey(String key) {
		Object value = properties.getProperty(key);
		return value;
	}

	//config.propertiesからkeyの値を表示するメソッド
	public static String stringReadKey(String key) {
		String value = properties.getProperty(key);
		return value;
	}

	//config.propertiesからkeyの値を表示するメソッド
	public static int intReadKey(String key) {
		String valueS = properties.getProperty(key);
		int value = Integer.parseInt(valueS);
		return value;
	}

	//config.propertiesからkeyの値を表示するメソッド
	public static boolean booleanReadKey(String key) {
		String valueS = properties.getProperty(key);
		boolean value = Boolean.parseBoolean(valueS);
		return value;
	}

	//propertiesFileにプロパティーリストを追加する
	public static void append(String key, String value) {
		if(properties.containsKey(key)) {
			System.err.println(key+" "+value+"はすでに存在しています");
			new ErrorDialog(frame, "key"+" "+value+"はすでに存在しています");
		}else {
			properties.setProperty(key, value);
		}
	}

	//プロパティーのリストを他のファイルに書き出す
	public static void store(String fileName) {
		try {
			properties.store(new FileOutputStream(fileName), null);
		} catch (IOException e) {
			e.printStackTrace();
			new ErrorDialog(frame, "IOException");
		}
	}

}
