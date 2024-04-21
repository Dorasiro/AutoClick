package jp.dip.dorasiro.AC.sound;

//import java.applet.Applet;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import jp.dip.dorasiro.Library.ini;

public class AC_Sound implements LineListener {
	/*
	 * フィールド
	 */
	private Clip clip;
	 // WAVEファイルデータ（名前->データ本体）
	//複数個のSound再生に対応させる為に使用している模様
    private Map<String, Clip> clipMap;

	/*
	 * コンストラクタ
	 */
	public AC_Sound() {
		File soundFile = new File(ini.getValue("SoundData"));
		clipMap = new HashMap<String, Clip>();
		try {
			//オーディオストリームを開く
			AudioInputStream stream = AudioSystem.getAudioInputStream(soundFile);
			//ライン情報を取得
			DataLine.Info info = new DataLine.Info(Clip.class, stream.getFormat());
			//空のクリップを作成
			clip = (Clip)AudioSystem.getLine(info);
			//クリップのイベントを監視
			clip.addLineListener(this);
			//オーディオストリームをクリップとして開く
			clip.open(stream);
			//クリップを登録
			clipMap.put("sound", clip);
			stream.close();

		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	/*
	 * メソッド
	 */
	//LineListnerの実装
	@Override
	public void update(LineEvent event) {
		// ストップか最後まで再生された場合
        if (event.getType() == LineEvent.Type.STOP) {
            Clip clip = (Clip) event.getSource();
            clip.stop();
            clip.setFramePosition(0); // 再生位置を最初に戻す
        }
	}

	//演奏
	public void play() {
		//ミュートでない場合のみ演奏
		if(Boolean.valueOf(ini.getValue("EnableMute")) == false) {
			//音量の調整用
			FloatControl control = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			control.setValue((float)Math.log10(Double.parseDouble(ini.getValue("Volume"))/100) * 20);
			clip.start();
		}
	}

	//停止
	public void stop() {
		clip.stop();
	}
}
