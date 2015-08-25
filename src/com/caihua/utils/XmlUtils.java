package com.caihua.utils;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;

import com.caihua.idcardreader.R;

import android.R.integer;
import android.R.xml;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;

public class XmlUtils {

	public static ArrayList<Integer> getVenderId(Context context) {
		XmlResourceParser xrp = context.getResources().getXml(
				R.xml.device_filter);
		ArrayList<Integer> venderIds = new ArrayList<Integer>();
		try {
			while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
				String name = xrp.getName();
				if (TextUtils.equals(name, "usb-device")) {
					venderIds.add(new Integer(xrp.getAttributeIntValue(0, -1)));
				}
				xrp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return venderIds;
	}

}
