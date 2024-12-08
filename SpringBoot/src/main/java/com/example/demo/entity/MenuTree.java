package com.example.demo.entity;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MenuTree extends Menu {
  private List<MenuTree> children;
  private MenuMeta meta;

  public List<MenuTree> getChildren() {
    return children;
  }

  public void setChildren(List<MenuTree> children) {
    this.children = children;
  }

}
