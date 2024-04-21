package jp.dip.dorasiro.GUI.Menu.Config;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Point;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import jp.dip.dorasiro.GUI.GUI_PanelBottom;
import jp.dip.dorasiro.GUI.Menu.Menu_JMenuItem_IO;
import jp.dip.dorasiro.Library.ini;

public class Config_ConfigFrame extends JFrame {
	/*
	 * フィールド
	 */
	private JTextField savePathField;
	private JTextField soundPathTextField;
	private JTextField volumeField;

	private JButton okButton;
	private JButton cancelButton;
	private JButton applyButton;
	private JButton savePathButton;
	private JButton soundPathButton;

	private JComboBox<String> startKeyComboBox;
	private JComboBox<String> spoitKeyComboBox;

	private JCheckBox muteBox;
	private JCheckBox frontBox;

	private JSlider volumeSlider;

	/*
	 * コンストラクタ
	 */
	public Config_ConfigFrame(Point framePoint, final JFileChooser chooser, GUI_PanelBottom panelBottom) {
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));

		JPanel southPanel = new JPanel();
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		okButton = new JButton("OK");
		okButton.addActionListener(e -> {
			applyAction();
			//フレームを閉じる
			this.dispose();
			panelBottom.repaintLabel();
		});
		southPanel.add(okButton);

		cancelButton = new JButton("キャンセル");
		cancelButton.addActionListener(e -> this.dispose());
		southPanel.add(cancelButton);

		applyButton = new JButton("適用");
		applyButton.addActionListener(e -> {
			applyAction();
			panelBottom.repaintLabel();
		});
		southPanel.add(applyButton);

		JPanel centerPanel = new JPanel();
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

		JPanel keyAndOtherPanel = new JPanel();
		centerPanel.add(keyAndOtherPanel);
		keyAndOtherPanel.setLayout(new BoxLayout(keyAndOtherPanel, BoxLayout.X_AXIS));

		JPanel keyConfigPanel = new JPanel();
		keyAndOtherPanel.add(keyConfigPanel);
		keyConfigPanel.setLayout(new BoxLayout(keyConfigPanel, BoxLayout.Y_AXIS));
		keyConfigPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "キー設定"));

		JPanel spoitKeyPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) spoitKeyPanel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		keyConfigPanel.add(spoitKeyPanel);

		JLabel label = new JLabel("取得キー");
		spoitKeyPanel.add(label);
		spoitKeyComboBox = new JComboBox<String>();
		spoitKeyComboBox.addItem("F1");
		spoitKeyComboBox.addItem("F2");
		spoitKeyComboBox.addItem("F3");
		spoitKeyComboBox.addItem("F4");
		spoitKeyComboBox.addItem("F5");
		spoitKeyComboBox.addItem("F6");
		spoitKeyComboBox.addItem("F7");
		spoitKeyComboBox.addItem("F8");
		spoitKeyComboBox.addItem("F9");
		spoitKeyComboBox.addItem("F10");
		spoitKeyComboBox.addItem("F11");
		spoitKeyComboBox.addItem("F12");
		spoitKeyComboBox.setSelectedIndex(Integer.parseInt(ini.getValue("SpoitKey"))-112);
		spoitKeyPanel.add(spoitKeyComboBox);

		JPanel startKeyPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) startKeyPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		keyConfigPanel.add(startKeyPanel);

		JLabel label_1 = new JLabel("開始キー");
		startKeyPanel.add(label_1);
		startKeyComboBox = new JComboBox<String>();
		startKeyComboBox.addItem("F1");
		startKeyComboBox.addItem("F2");
		startKeyComboBox.addItem("F3");
		startKeyComboBox.addItem("F4");
		startKeyComboBox.addItem("F5");
		startKeyComboBox.addItem("F6");
		startKeyComboBox.addItem("F7");
		startKeyComboBox.addItem("F8");
		startKeyComboBox.addItem("F9");
		startKeyComboBox.addItem("F10");
		startKeyComboBox.addItem("F11");
		startKeyComboBox.addItem("F12");
		startKeyComboBox.setSelectedIndex(Integer.parseInt(ini.getValue("StartKey"))-112);
		startKeyPanel.add(startKeyComboBox);

		JPanel otherPanel = new JPanel();
		otherPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "その他"));
		keyAndOtherPanel.add(otherPanel);
		otherPanel.setLayout(new BoxLayout(otherPanel, BoxLayout.Y_AXIS));

		JPanel changeFrontPanel = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) changeFrontPanel.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		otherPanel.add(changeFrontPanel);

		frontBox = new JCheckBox("常に最前列に固定");
		frontBox.setSelected(Boolean.valueOf(ini.getValue("Front")));
		changeFrontPanel.add(frontBox);

		JPanel upDatePanel = new JPanel();
		FlowLayout flowLayout_8 = new FlowLayout();
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		upDatePanel.setLayout(flowLayout_8);
		otherPanel.add(upDatePanel);

		JPanel panel = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panel.getLayout();
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		otherPanel.add(panel);

		JPanel savePathPanel = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) savePathPanel.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		savePathPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "データ保存先"));
		centerPanel.add(savePathPanel);

		savePathField = new JTextField();
		savePathPanel.add(savePathField);
		savePathField.setColumns(30);
		File dataFile = new File(ini.getValue("DataFile"));
		savePathField.setText(dataFile.getPath());

		savePathButton = new JButton("...");
		savePathButton.addActionListener(e -> {
			//選択できるものをディレクトリに限定する
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				savePathField.setText(file.getPath());
			}
			//選択できるものをファイルのみに戻す
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		});
		savePathPanel.add(savePathButton);

		JPanel soundPanel = new JPanel();
		centerPanel.add(soundPanel);
		soundPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "サウンド"));
		soundPanel.setLayout(new BoxLayout(soundPanel, BoxLayout.Y_AXIS));

		JPanel mutePanel = new JPanel();
		soundPanel.add(mutePanel);
		FlowLayout flowLayout_5 = (FlowLayout) mutePanel.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);

		muteBox = new JCheckBox("ミュート");
		mutePanel.add(muteBox);
		muteBox.setSelected(Boolean.valueOf(ini.getValue("EnableMute")));

		JLabel volumeLabel = new JLabel("音量：");
		mutePanel.add(volumeLabel);

		volumeSlider = new JSlider();
		volumeSlider.setValue(Integer.parseInt(ini.getValue("Volume")));
		volumeSlider.setMajorTickSpacing(50);
		volumeSlider.setPaintTicks(true);
		volumeSlider.setPaintLabels(true);
		volumeSlider.addChangeListener(e -> volumeField.setText(String.valueOf(volumeSlider.getValue())));
		mutePanel.add(volumeSlider);

		volumeField = new JTextField(ini.getValue("Volume"));
		mutePanel.add(volumeField);
		volumeField.setColumns(3);
		volumeField.addActionListener(e -> volumeSlider.setValue(Integer.parseInt(volumeField.getText())));

		JPanel soundPathPanel = new JPanel();
		soundPanel.add(soundPathPanel);
		soundPathPanel.setLayout(new BoxLayout(soundPathPanel, BoxLayout.Y_AXIS));

		JPanel soundLabelPanel = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) soundLabelPanel.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		soundPathPanel.add(soundLabelPanel);

		JLabel lblwav = new JLabel("サウンドデータ(.wav)");
		soundLabelPanel.add(lblwav);

		JPanel soundPathTextPanel = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) soundPathTextPanel.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		soundPathPanel.add(soundPathTextPanel);

		soundPathTextField = new JTextField();
		soundPathTextPanel.add(soundPathTextField);
		soundPathTextField.setColumns(30);
		File soundFile = new File(ini.getValue("SoundData"));
		soundPathTextField.setText(soundFile.getPath());

		soundPathButton = new JButton("...");
		soundPathButton.addActionListener(e -> {
			chooser.addChoosableFileFilter(new FileNameExtensionFilter(".wav", "wav"));
			chooser.removeChoosableFileFilter(new FileNameExtensionFilter("AutoClick保存ファイル", Menu_JMenuItem_IO.FILE_ECTENSION));
			if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				soundPathTextField.setText(file.getPath());
			}
		});
		soundPathTextPanel.add(soundPathButton);

		//GUIの設定
		this.setTitle("Config");
		this.setBounds((int)framePoint.getX(), (int)framePoint.getY(), 410, 400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//サイズの変更を禁止する
		this.setResizable(false);
		this.setVisible(true);
	}

	/*
	 * メソッド
	 */
	private void applyAction() {
		//開始と取得のキーに同じものを指定した場合はエラーを発生
		if(startKeyComboBox.getSelectedItem() == spoitKeyComboBox.getSelectedItem()) {
			JOptionPane.showMessageDialog(this, "開始キーと取得キーに同じキーを選択することは出来ません。");

		}else {
			ini.clear();
			switch(startKeyComboBox.getSelectedIndex()) {
				case 0:ini.put("StartKey", "112");break;
				case 1:ini.put("StartKey", "113");break;
				case 2:ini.put("StartKey", "114");break;
				case 3:ini.put("StartKey", "115");break;
				case 4:ini.put("StartKey", "116");break;
				case 5:ini.put("StartKey", "117");break;
				case 6:ini.put("StartKey", "118");break;
				case 7:ini.put("StartKey", "119");break;
				case 8:ini.put("StartKey", "120");break;
				case 9:ini.put("StartKey", "121");break;
				case 10:ini.put("StartKey", "122");break;
				case 11:ini.put("StartKey", "123");break;
			}
			switch(spoitKeyComboBox.getSelectedIndex()) {
				case 0:ini.put("SpoitKey", "112");break;
				case 1:ini.put("SpoitKey", "113");break;
				case 2:ini.put("SpoitKey", "114");break;
				case 3:ini.put("SpoitKey", "115");break;
				case 4:ini.put("SpoitKey", "116");break;
				case 5:ini.put("SpoitKey", "117");break;
				case 6:ini.put("SpoitKey", "118");break;
				case 7:ini.put("SpoitKey", "119");break;
				case 8:ini.put("SpoitKey", "120");break;
				case 9:ini.put("SpoitKey", "121");break;
				case 10:ini.put("SpoitKey", "122");break;
				case 11:ini.put("SpoitKey", "123");break;
			}
			ini.put("DataFile", savePathField.getText());
			ini.put("EnableMute", String.valueOf(muteBox.isSelected()));
			ini.put("SoundData", soundPathTextField.getText());
			ini.put("Front", String.valueOf(frontBox.isSelected()));
			ini.put("Volume", String.valueOf(volumeSlider.getValue()));
			ini.put("Version", ini.getValue("Varsion"));
			ini.write();
		}
	}
}
