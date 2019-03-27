package com.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PrepareData {

  private List<DataModel> headerList = new ArrayList<>();
  private HashMap<DataModel, List<DataModel>> childList = new HashMap<>();

  public void prepareData(){

    for (int i = 0; i < 10; i++) {
      headerList.add(new DataModel("Sample "+i,false,true,false));
    }

    DataModel dataModel = headerList.get(3);
    dataModel.setHasChildren(true);
    headerList.set(3,dataModel);

    List<DataModel> childItems=new ArrayList<>();

    for (int j = 0; j < 4; j++) {
      childItems.add(new DataModel("Child "+j,false,false,false));
    }

    childList.put(dataModel,childItems);

  }

  public List<DataModel> getGroupData() {
    return headerList;
  }

  public HashMap<DataModel, List<DataModel>> getChildData() {
	return childList;
  }
}
