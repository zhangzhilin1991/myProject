package com.caihua.ui.online;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;

public class ActivityList {
	public static List<Activity> activities = new ArrayList();
	public static Activity showing;

	public static void removeAllButShowing() {
		int i = 0;
		while (true) {
			if (i >= activities.size()) {
				activities.clear();
				return;
			}
			try {
				((Activity) activities.get(i)).finish();
				i++;
			} catch (Exception localException) {
				break;
			}
		}
	}
}