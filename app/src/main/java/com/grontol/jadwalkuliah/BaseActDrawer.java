package com.grontol.jadwalkuliah;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.grontol.jadwalkuliah.db.DataHelper;
import com.grontol.jadwalkuliah.item.ItemJadwal;

import java.util.List;

public class BaseActDrawer extends BaseActivity implements OnNavigationItemSelectedListener, View.OnClickListener
{
	DrawerLayout lytDrawer;
	ActionBarDrawerToggle abdToggle;
	Toolbar toolbar;
	
	NavigationView navView;
	
	protected FrameLayout frmContent;
	
	protected View headerView;
	
	List<ItemJadwal> lJadwal;
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyt_toolbar_nav_drawer);
		
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		
		lytDrawer = (DrawerLayout) findViewById(R.id.lytDrawer);
		abdToggle = new ActionBarDrawerToggle(this, lytDrawer, toolbar, 0, 0);
		lytDrawer.addDrawerListener(abdToggle);
		
		navView = (NavigationView) findViewById(R.id.navigation_view);
		navView.setNavigationItemSelectedListener(this);
		
		frmContent = (FrameLayout) findViewById(R.id.content_frame);
		
		headerView = navView.getHeaderView(0);
		headerView.setOnClickListener(this);
			
		fillMenu();
	}
	
	protected void fillMenu()
	{
		Menu menu = navView.getMenu();
		
		menu.removeGroup(0);
		menu.add(0, 0, 0, "Add");
		
		lJadwal = DataHelper.getJadwal(this);
		int c = 1;
		
		for (ItemJadwal it : lJadwal)
		{
			menu.add(0, it.getIdJadwal(), c++, it.getNamaJadwal());
		}
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		fillMenu();
	}
	
	@Override
	public void onClick(View v)
	{
		if (v == headerView)
		{
			
		}
	}
	
	@Override
	public boolean onNavigationItemSelected(MenuItem i)
	{
		if (i.getItemId() == 0)
		{
			Intent it = new Intent(this, ActAddJadwal.class);
			startActivity(it);
		}
		else
		{
			for (ItemJadwal it : lJadwal)
			{
				if (i.getItemId() == it.getIdJadwal())
				{
					Intent intent = new Intent(this, ActAddJadwal.class);
					intent.putExtra("isEdit", true);
					intent.putExtra("idJadwal", it.getIdJadwal() + "");
					intent.putExtra("namaJadwal", it.getNamaJadwal());
					startActivity(intent);
				}
			}
		}
		
		return false;
	}
	
	@Override
	protected void onPostCreate(@Nullable Bundle savedInstanceState)
	{
		super.onPostCreate(savedInstanceState);
		abdToggle.syncState();
	}
}
