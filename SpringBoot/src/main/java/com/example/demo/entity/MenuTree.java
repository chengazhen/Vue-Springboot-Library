package com.example.demo.entity;
import java.util.List;

public class MenuTree extends Menu {
  private List<MenuTree> children;

  public List<MenuTree> getChildren() {
    return children;
  }

  public void setChildren(List<MenuTree> children) {
    this.children = children;
  }
}
