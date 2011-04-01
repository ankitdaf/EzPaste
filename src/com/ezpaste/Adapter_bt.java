package com.ezpaste;

import java.util.List;



import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class Adapter_bt extends ArrayAdapter<Btinfo>
{
	private int	itemViewResouceId;
	Btinfo InfoList ;
	
	
	public Adapter_bt(Context context, List<Btinfo> bdlist, int item) {
		super(context, android.R.layout.activity_list_item,bdlist);
		this.itemViewResouceId = item;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		if(position < getCount())
		{
			// File file = getItem(position);
			if(convertView == null)
			{
				LayoutInflater vi = (LayoutInflater)((Activity)getContext()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = vi.inflate(itemViewResouceId, null);
			}
			TextView devlistName = (TextView)convertView.findViewById(R.id.item_TV_name);
			TextView devlistAddr = (TextView)convertView.findViewById(R.id.item_TV_Addr);
			InfoList =(Btinfo)getItem(position);
			devlistName.setText(InfoList.getName());
			devlistAddr.setText(InfoList.getMac());
		
		}
		return convertView;
	
	}}