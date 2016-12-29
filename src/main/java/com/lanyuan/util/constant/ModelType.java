package com.lanyuan.util.constant;

public enum ModelType {
	Passenger("乘客", 0), TaxiDriver("出租车司机", 1), SmallpPiecesDriver("小件快运司机", 2), DesignatedDriver("代驾司机", 3);
    // 成员变量
    private String name;
    private int index;
 
    // 构造方法，注意：构造方法不能为public，因为enum并不可以被实例化
    private ModelType(String name, int index) {
      this.name = name;
      this.index = index;
    }
 
    // 普通方法
    public static String getName(int index) {
      for (ModelType c : ModelType .values()) {
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
