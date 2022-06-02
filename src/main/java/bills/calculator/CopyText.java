package bills.calculator;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

// Functional class to abstract from clipboard related logic
public class CopyText implements ClipboardOwner {

	@Override
	public void lostOwnership(Clipboard arg0, Transferable arg1) {

	}

	public void setClipboardContents(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, this);
	}
}
