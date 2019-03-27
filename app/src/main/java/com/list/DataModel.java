package com.list;

public class DataModel {

  private String name;
  private boolean hasChildren;
  private boolean isGroup;
  private boolean isExpanded;

  public DataModel(String name, boolean hasChildren, boolean isGroup, boolean isExpanded) {
	this.name = name;
	this.hasChildren = hasChildren;
	this.isGroup = isGroup;
	this.isExpanded = isExpanded;
  }

  public String getName() {
	return name;
  }

  public void setName(String name) {
	this.name = name;
  }

  public boolean isHasChildren() {
	return hasChildren;
  }

  public void setHasChildren(boolean hasChildren) {
	this.hasChildren = hasChildren;
  }

  public boolean isGroup() {
	return isGroup;
  }

  public void setGroup(boolean group) {
	isGroup = group;
  }

  public boolean isExpanded() {
	return isExpanded;
  }

  public void setExpanded(boolean expanded) {
	isExpanded = expanded;
  }
}
