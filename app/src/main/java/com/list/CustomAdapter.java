package com.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class CustomAdapter extends BaseExpandableListAdapter {


  private final Context context;
  private List<DataModel> groupHeader;
  private HashMap<DataModel, List<DataModel>> childElements;

  public CustomAdapter(Context context, List<DataModel> groupHeader, HashMap<DataModel, List<DataModel>> childElements) {
	this.context = context;
	this.groupHeader = groupHeader;
	this.childElements = childElements;
  }

  @Override
  public int getGroupCount() {
	return groupHeader.size();
  }

  @Override
  public int getChildrenCount(int groupPosition) {
    if (childElements.get(groupHeader.get(groupPosition))==null){
	  return 0;
	}else {
	  return childElements.get(groupHeader.get(groupPosition)).size();
	}
  }

  @Override
  public Object getGroup(int groupPosition) {
	return groupHeader.get(groupPosition);
  }

  @Override
  public Object getChild(int groupPosition, int childPosition) {
	return childElements.get(groupHeader.get(groupPosition)).get(childPosition);
  }

  @Override
  public long getGroupId(int groupPosition) {
	return groupPosition;
  }

  @Override
  public long getChildId(int groupPosition, int childPosition) {
	return childPosition;
  }

  @Override
  public boolean hasStableIds() {
	return false;
  }

  @Override
  public boolean isChildSelectable(int groupPosition, int childPosition) {
	return true;
  }

  @Override
  public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
	String headerTitle = groupHeader.get(groupPosition).getName();
	if (convertView == null) {
	  LayoutInflater infalInflater = (LayoutInflater) this.context
		  .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	  convertView = infalInflater.inflate(R.layout.list_group_header, null);
	}

	TextView lblListHeader = convertView.findViewById(R.id.textHeader);
	ImageView imgDropHeader = convertView.findViewById(R.id.imageDrop);

	lblListHeader.setText(headerTitle);

	if (groupHeader.get(groupPosition).isHasChildren()) {
	  imgDropHeader.setVisibility(VISIBLE);
	} else {
	  imgDropHeader.setVisibility(GONE);
	}

	// icon drop down and up
	if (groupHeader.get(groupPosition).isExpanded()) {
	  imgDropHeader.setImageResource(R.drawable.ic_up_updated);
	} else {
	  imgDropHeader.setImageResource(R.drawable.ic_down_updated);
	}

	return convertView;
  }

  @Override
  public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
	final String childText = childElements.get(groupHeader.get(groupPosition)).get(childPosition).getName();

	if (convertView == null) {
	  LayoutInflater infalInflater = (LayoutInflater) this.context
		  .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	  convertView = infalInflater.inflate(R.layout.list_group_child, null);

	}

	// color of selected row
	if (childElements.get(groupHeader.get(groupPosition)).get(childPosition).isExpanded()) {
	  convertView.setBackgroundColor(convertView.getContext().getResources().getColor(android.R.color.holo_blue_bright));
	} else {
	  convertView.setBackgroundColor(convertView.getContext().getResources().getColor(android.R.color.white));
	}

	TextView txtListChild = convertView
		.findViewById(R.id.textChild);

	txtListChild.setText(childText);


	return convertView;
  }

  public void notifyDataSetChangedExpandable(List<DataModel> groupHeader, HashMap<DataModel, List<DataModel>> childElements) {
	this.groupHeader = groupHeader;
	this.childElements = childElements;
	notifyDataSetChanged();
  }

}
