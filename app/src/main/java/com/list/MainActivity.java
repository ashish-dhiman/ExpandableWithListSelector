package com.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private ExpandableListView expandableListView;
  private CustomAdapter customAdapter;
  private List<DataModel> groupHeader = new ArrayList<>();
  private HashMap<DataModel, List<DataModel>> childElements = new HashMap<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

	expandableListView=findViewById(R.id.expandableList);

	PrepareData prepareData=new PrepareData();
	prepareData.prepareData();
	groupHeader=prepareData.getGroupData();
	childElements=prepareData.getChildData();

	customAdapter=new CustomAdapter(this,groupHeader,childElements);
	expandableListView.setAdapter(customAdapter);
	expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
	  @Override
	  public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
		if (groupHeader.get(groupPosition).isGroup()) {

		  // no children
		  if (!groupHeader.get(groupPosition).isHasChildren()) {

		  } else {
			// code reviewed
			boolean isExpanded = groupHeader.get(groupPosition).isExpanded();
			groupHeader.get(groupPosition).setExpanded(!isExpanded);
		  }
		}

		return false;
	  }
	});
	expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
	  @Override
	  public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
		List<DataModel> itemsList = childElements.get(groupHeader.get(groupPosition));

		// code for deselecting any previous selection of child item (background color)
		List<DataModel> navigationListItems = childElements.get(groupHeader.get(3));
		if (navigationListItems != null) {
		  for (DataModel item : navigationListItems) {
			item.setExpanded(false);
		  }
		}

		DataModel model = null;
		if (itemsList != null) {
		  model = itemsList.get(childPosition);

		  // required to highlight child item selected
		  model.setExpanded(true);
		}

		// to refresh data in expendable list view (select/deselect)
		customAdapter.notifyDataSetChangedExpandable(groupHeader, childElements);

		return false;
	  }
	});

  }
}
