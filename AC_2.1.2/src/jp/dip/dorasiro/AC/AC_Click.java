package jp.dip.dorasiro.AC;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import jp.dip.dorasiro.AC.Event.AC_EventRun;
import jp.dip.dorasiro.AC.Event.AC_EventSelect;
import jp.dip.dorasiro.AC.sound.AC_Sound;


public class AC_Click extends Observable implements Runnable {
	/*
	 * フィールド
	 */
	//全体を管理するdataList 内部に収納されるArrayListはtimer, clickCount, x, yのArrayList
	private ArrayList<ArrayList<Integer>> dataList;
	//dataListの収納順を規定するための定数
	public static final int TIMER = 0;
	public static final int CLICKCOUNT = 1;
	public static final int X = 2;
	public static final int Y = 3;
	//リストが実現できればそこのサイズを取得することでentryを使用しなくてもよくなる
	private AC_EventRun eventRun;
	private boolean run;
	private int nextTime;
	//実行中の項目をテーブルに設定させるためのint
	private int select;
	private AC_EventSelect eventSelect;

	private AC_Sound sound;

	/*
	 * コンストラクタ
	 */
	/**
	 * @param gui GUIのクラスを収納する GUIに入力された値を取得するのに利用する
	 */
	public AC_Click() {
		eventRun = new AC_EventRun(this);
		nextTime = -1;
		select = 0;
		eventSelect = new AC_EventSelect(this);

		sound = new AC_Sound();
	}

	/*
	 *メソッド
	 */
	@Override
	public void run() {
		//runの値を取得し、状態の制御をするための無限ループ
		while(true) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			//作動時にこのループでクリック、待機を行う
			while(run) {
				try {
					//一周後、二週目に移行するためのループ 	一周のサイズの分ループし、runが無効になってないか調べ終了の精度を上げる
					for(int i=0;i<dataList.get(TIMER).size() && run;i++) {
						robot(i);
						if(run == true) {
							sleep(i);
						}else {
							break;
						}
					}
					//中断時にどこまで実行したのかを初期化
					select = 0;

				} catch (InterruptedException | AWTException e) {
					e.printStackTrace();
					Thread.interrupted();
				}
			}
		}
	}

	/*
	 * 内部メソッド1
	 */
	//クリックをするメソッド
	protected void robot(int row) throws InterruptedException, AWTException {
		setChanged();
		notifyObservers(eventSelect);
		Robot robot = new Robot();
		robot.mouseMove(dataList.get(X).get(row), dataList.get(Y).get(row));
		for(int i=0;i<dataList.get(CLICKCOUNT).get(row);i++) {
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			sound.play();
		}
		//表示の更新
		nextTime = dataList.get(TIMER).get(row);
		setChanged();
		notifyObservers(eventRun);
	}

	/*
	 * 内部メソッド2
	 */
	//待機をするメソッド
	private void sleep(int row) throws InterruptedException {
		try {
			for(int i=0;i<dataList.get(TIMER).get(row) && run;i++) {
				Thread.sleep(1000);
				if(run) {
					nextTime--;
				}
				setChanged();
				notifyObservers(eventRun);
			}
			select++;
		}catch (IndexOutOfBoundsException ignored) {
			//ここは意図的に例外を無視しているため、修正は不要
		}
	}

	/*
	 * セッター＆ゲッター
	 */
	public boolean getRun() {
		return run;
	}

	public int getNextTime() {
		return nextTime;
	}

	public int getSelect() {
		return select;
	}

	public void setDataList(ArrayList<ArrayList<Integer>> dataList) {
		this.dataList = dataList;
	}

	public void setRun(boolean run) {
		this.run = run;
		if(!run) {
			nextTime = 0;
			setChanged();
			notifyObservers(eventRun);
		}
	}

	public void setObserver(Observer observer) {
		this.addObserver(observer);
	}
}
