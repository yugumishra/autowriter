package typer;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	private static Robot rob;

	private static char[] chars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
			'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ',', '.', ' ', '\'', '-', '1', '2',
			'3', '4', '5', '6', '7', '8', '9', '0', 'â', '!', '?', '(', ')', ':', '%', '"' };

	private static Integer[][] values = { { KeyEvent.VK_A }, { KeyEvent.VK_B }, { KeyEvent.VK_C }, { KeyEvent.VK_D },
			{ KeyEvent.VK_E }, { KeyEvent.VK_F }, { KeyEvent.VK_G }, { KeyEvent.VK_H }, { KeyEvent.VK_I },
			{ KeyEvent.VK_J }, { KeyEvent.VK_K }, { KeyEvent.VK_L }, { KeyEvent.VK_M }, { KeyEvent.VK_N },
			{ KeyEvent.VK_O }, { KeyEvent.VK_P }, { KeyEvent.VK_Q }, { KeyEvent.VK_R }, { KeyEvent.VK_S },
			{ KeyEvent.VK_T }, { KeyEvent.VK_U }, { KeyEvent.VK_V }, { KeyEvent.VK_W }, { KeyEvent.VK_X },
			{ KeyEvent.VK_Y }, { KeyEvent.VK_Z }, { KeyEvent.VK_SHIFT, KeyEvent.VK_A },
			{ KeyEvent.VK_SHIFT, KeyEvent.VK_B }, { KeyEvent.VK_SHIFT, KeyEvent.VK_C },
			{ KeyEvent.VK_SHIFT, KeyEvent.VK_D }, { KeyEvent.VK_SHIFT, KeyEvent.VK_E },
			{ KeyEvent.VK_SHIFT, KeyEvent.VK_F }, { KeyEvent.VK_SHIFT, KeyEvent.VK_G },
			{ KeyEvent.VK_SHIFT, KeyEvent.VK_H }, { KeyEvent.VK_SHIFT, KeyEvent.VK_I },
			{ KeyEvent.VK_SHIFT, KeyEvent.VK_J }, { KeyEvent.VK_SHIFT, KeyEvent.VK_K },
			{ KeyEvent.VK_SHIFT, KeyEvent.VK_L }, { KeyEvent.VK_SHIFT, KeyEvent.VK_M },
			{ KeyEvent.VK_SHIFT, KeyEvent.VK_N }, { KeyEvent.VK_SHIFT, KeyEvent.VK_O },
			{ KeyEvent.VK_SHIFT, KeyEvent.VK_P }, { KeyEvent.VK_SHIFT, KeyEvent.VK_Q },
			{ KeyEvent.VK_SHIFT, KeyEvent.VK_R }, { KeyEvent.VK_SHIFT, KeyEvent.VK_S },
			{ KeyEvent.VK_SHIFT, KeyEvent.VK_T }, { KeyEvent.VK_SHIFT, KeyEvent.VK_U },
			{ KeyEvent.VK_SHIFT, KeyEvent.VK_V }, { KeyEvent.VK_SHIFT, KeyEvent.VK_W },
			{ KeyEvent.VK_SHIFT, KeyEvent.VK_X }, { KeyEvent.VK_SHIFT, KeyEvent.VK_Y },
			{ KeyEvent.VK_SHIFT, KeyEvent.VK_Z }, { KeyEvent.VK_COMMA }, { KeyEvent.VK_PERIOD }, { KeyEvent.VK_SPACE },
			{ KeyEvent.VK_A }, { KeyEvent.VK_A }, { KeyEvent.VK_1 }, { KeyEvent.VK_2 }, { KeyEvent.VK_3 },
			{ KeyEvent.VK_4 }, { KeyEvent.VK_5 }, { KeyEvent.VK_6 }, { KeyEvent.VK_7 }, { KeyEvent.VK_8 },
			{ KeyEvent.VK_9 }, { KeyEvent.VK_0 }, { KeyEvent.VK_ENTER }, { KeyEvent.VK_SHIFT, KeyEvent.VK_1 },
			{ KeyEvent.VK_SHIFT, KeyEvent.VK_SLASH }, { KeyEvent.VK_SHIFT, KeyEvent.VK_9 },
			{ KeyEvent.VK_SHIFT, KeyEvent.VK_0 }, { KeyEvent.VK_SHIFT, KeyEvent.VK_SEMICOLON }, { KeyEvent.VK_SHIFT, KeyEvent.VK_5}, { KeyEvent.VK_A} };

	private static HashMap<Character, Integer[]> map;

	private static void initMap() {
		map = new HashMap<Character, Integer[]>();
		for (int i = 0; i < chars.length; i++) {
			map.put(chars[i], values[i]);
		}
	}

	private static void type(String msg, int delay) {
		for (int i = 0; i < msg.length(); i++) {
			Character c = msg.charAt(i);
			Integer[] vals = null;
			try {
				vals = map.get(c);
			} catch (NullPointerException e) {
				System.out.println(c);
				vals = map.get('a');
			}
			// press both(if there are 2) keys
			for (int j = 0; j < vals.length; j++) {

				rob.keyPress(vals[j]);
			}
			// release both(if there are 2) keys
			for (int j = 0; j < vals.length; j++) {
				rob.keyRelease(vals[j]);
			}
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
			}
		}
	}

	// BEWARE THIS MAY CRASH IT
	private static void type(String str) {
		for (int i = 0; i < str.length(); i++) {
			Character c = str.charAt(i);
			Integer[] vals = null;
			try {
				vals = map.get(c);
			} catch (NullPointerException e) {
				System.out.println(c);
				vals = map.get('a');
			}

			// press both(if there are 2) keys
			for (int j = 0; j < vals.length; j++) {

				rob.keyPress(vals[j]);
			}
			// release both(if there are 2) keys
			for (int j = 0; j < vals.length; j++) {
				rob.keyRelease(vals[j]);
			}
		}
	}

	public static void main(String[] args) {
		initMap();
		rob = null;
		try {
			rob = new Robot();
		} catch (AWTException e) {
			System.out.println("We couldn't initialize the robot ;(");
			System.exit(0);
		}
		for (int a = 0; a < 1; a++) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
			}

			// use rob directly to hit the keyboard shortcuts
			rob.mousePress(InputEvent.BUTTON3_DOWN_MASK);
			rob.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
			
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
			}
			rob.keyPress(KeyEvent.VK_UP);
			rob.keyRelease(KeyEvent.VK_UP);
			rob.keyPress(KeyEvent.VK_ENTER);
			rob.keyRelease(KeyEvent.VK_ENTER);
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
			}
			rob.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			rob.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			for (int i = 0; i < 500; i++) {
				rob.keyPress(KeyEvent.VK_UP);
				rob.keyRelease(KeyEvent.VK_UP);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			rob.keyPress(KeyEvent.VK_DOWN);
			rob.keyRelease(KeyEvent.VK_DOWN);
			rob.keyPress(KeyEvent.VK_CONTROL);
			rob.keyPress(KeyEvent.VK_C);
			rob.keyRelease(KeyEvent.VK_C);
			rob.keyRelease(KeyEvent.VK_CONTROL);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			rob.keyPress(KeyEvent.VK_WINDOWS);
			rob.keyRelease(KeyEvent.VK_WINDOWS);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			type("notepad", 20);
			rob.keyPress(KeyEvent.VK_ENTER);
			rob.keyRelease(KeyEvent.VK_ENTER);
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
			}
			rob.keyPress(KeyEvent.VK_CONTROL);
			rob.keyPress(KeyEvent.VK_V);
			rob.keyRelease(KeyEvent.VK_V);
			rob.keyRelease(KeyEvent.VK_CONTROL);
			rob.keyPress(KeyEvent.VK_CONTROL);
			rob.keyPress(KeyEvent.VK_S);
			rob.keyRelease(KeyEvent.VK_S);
			rob.keyRelease(KeyEvent.VK_CONTROL);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			type("ScrapedData", 20);
			rob.keyPress(KeyEvent.VK_ENTER);
			rob.keyRelease(KeyEvent.VK_ENTER);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			rob.keyPress(KeyEvent.VK_LEFT);
			rob.keyRelease(KeyEvent.VK_LEFT);
			rob.keyPress(KeyEvent.VK_ENTER);
			rob.keyRelease(KeyEvent.VK_ENTER);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			rob.keyPress(KeyEvent.VK_CONTROL);
			rob.keyPress(KeyEvent.VK_W);
			rob.keyRelease(KeyEvent.VK_W);
			rob.keyRelease(KeyEvent.VK_CONTROL);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			rob.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			rob.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			rob.keyPress(KeyEvent.VK_CONTROL);
			rob.keyPress(KeyEvent.VK_SHIFT);
			rob.keyPress(KeyEvent.VK_I);
			rob.keyRelease(KeyEvent.VK_I);
			rob.keyRelease(KeyEvent.VK_SHIFT);
			rob.keyRelease(KeyEvent.VK_CONTROL);

			// read data
			File f = new File("C:\\Users\\findy\\Desktop\\ScrapedData.txt");
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(f);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			char[] chars = new char[(int) f.length()];
			for (int i = 0; i < chars.length; i++) {
				try {
					chars[i] = (char) fis.read();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// filter data
			String str = String.copyValueOf(chars);
			int s = str.indexOf(
					"<!-- Note: Insert a line break manually by adding a `<div class=\"screenBasic-lineBreak\"></div>` after the word -->");
			s -= 67;
			str = str.substring(s);

			// first pass - filter all whitespace
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (!(c == ' ' || c == '\t' || c == '\n' || c == '\r')) {
					sb.append(c);
				}
			}
			str = sb.toString();
			// second pass - filter all info in brackets
			sb = new StringBuilder();
			int bracketCount = 0;
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (c == '<')
					bracketCount++;
				if (c == '>')
					bracketCount--;
				if (bracketCount == 0) {
					sb.append(c);
				}
			}
			str = sb.toString();
			// third pass - filter out artifacts
			sb = new StringBuilder();
			boolean inNBSP = false;
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (c == '&')
					inNBSP = true;
				if (c == ';')
					inNBSP = false;
				if (inNBSP == false && c != '>') {
					if (c == ';') {
						sb.append(' ');
					} else {
						sb.append(c);
					}
				}
			}
			str = sb.toString();
			s = str.indexOf("CapsLockWarning!");
			str = str.substring(0, s);
			System.out.println(str);
			type(str, 10);
			// reload the page to get to the next test
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			rob.keyPress(KeyEvent.VK_CONTROL);
			rob.keyPress(KeyEvent.VK_R);
			rob.keyRelease(KeyEvent.VK_R);
			rob.keyRelease(KeyEvent.VK_CONTROL);

		}
	}
}
