package com.caihua.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;

@SuppressLint("NewApi")
public class plusActionProvider extends ActionProvider implements
		OnMenuItemClickListener {

	private LayoutInflater inflater;
	private Context context;

	public plusActionProvider(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	@Override
	public View onCreateActionView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onPrepareSubMenu(SubMenu subMenu) {
		// TODO Auto-generated method stub
		super.onPrepareSubMenu(subMenu);
		subMenu.clear();
		subMenu.add(0, 0, 0, "备份").setIcon(android.R.drawable.ic_menu_add)
				.setOnMenuItemClickListener(plusActionProvider.this);
		subMenu.add(0, 1, 0, "还原").setIcon(android.R.drawable.ic_menu_agenda)
				.setOnMenuItemClickListener(plusActionProvider.this);
		subMenu.add(0, 2, 0, "更新").setIcon(android.R.drawable.ic_menu_call)
				.setOnMenuItemClickListener(plusActionProvider.this);

	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		// TODO Auto-generate
		inflater = LayoutInflater.from(context);

		switch (item.getItemId()) {
		case 0:
			break;
		case 1:

			break;
		case 2:

			break;

		default:
			break;
		}

		return false;
	}

	@Override
	public boolean hasSubMenu() {
		// TODO Auto-generated method stub

		return true;
	}

}
