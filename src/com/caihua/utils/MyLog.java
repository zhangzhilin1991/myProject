package com.caihua.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.os.Environment;
import android.util.Log;

public class MyLog {
	private static FileOutputStream fos;
	private static OutputStreamWriter osw;
	private static BufferedWriter bw;

	private static boolean debug;// 调试模式，输出打印到txt

	static {
		debug = false;

		File file = new File(Environment.getExternalStorageDirectory()
				+ File.separator + "log.txt");
		try {
			fos = new FileOutputStream(file.getAbsolutePath(), true);
			osw = new OutputStreamWriter(fos, "UTF-8");
			bw = new BufferedWriter(osw);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void close() {

		try {
			bw.close();
			osw.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void d(String str, String message) {
		if (message == null) {
			return;
		}
		Log.d(str, message);

		if (debug) {
			try {
				bw.write(str + " ");
				bw.write(TimeUtil.getCurrentTime() + " ");
				bw.write(message);
				bw.write("\n");
				bw.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public static void e(String str, Exception e) {
		if (e == null) {
			return;
		}
		Log.d(str, e.getMessage());

		if (debug) {
			try {
				bw.write(str + " ");
				bw.write(TimeUtil.getCurrentTime() + " ");
				bw.write(e.getMessage());
				bw.write("\n");
				bw.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
