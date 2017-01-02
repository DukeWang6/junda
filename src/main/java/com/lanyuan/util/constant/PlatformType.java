package com.lanyuan.util.constant;

public enum PlatformType {
	Android("安卓", 0), Ios("苹果", 1);
    // 成员变量
    private String name;
    private int index;
 
    // 构造方法，注意：构造方法不能为public，因为enum并不可以被实例化
    private PlatformType(String name, int index) {
      this.name = name;
      this.index = index;
    }
 
    // 普通方法
    public static String getName(int index) {
      for (PlatformType c : PlatformType .values()) {
        if (c.getIndex() == index) {
          return c.name;
        }
      }
      return null;
    }
 
    // get set 方法
    public String getName() {
      return name;
    }
 
    public void setName(String name) {
      this.name = name;
    }
 
    public int getIndex() {
      return index;
    }
 
    public void setIndex(int index) {
      this.index = index;
    }
}
