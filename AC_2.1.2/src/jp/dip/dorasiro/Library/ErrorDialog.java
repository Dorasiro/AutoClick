package jp.dip.dorasiro.Library;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorDialog extends JFrame {
	public ErrorDialog(Component comp, String message) {
		JOptionPane.showOptionDialog(comp, message, "エラー", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
		
	}
	
	public ErrorDialog(Component comp, String message, String title) {
		JOptionPane.showOptionDialog(comp, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
		
	}
	
	public ErrorDialog(Component comp, String message, String title, int option, int type) {
		JOptionPane.showOptionDialog(comp, message, title, option, type, null, null, null);
	}

}
