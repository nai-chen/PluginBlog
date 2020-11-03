package com.plugin.blog.demo.data;

public class UserBasicInfo extends UserInfo {
    public final static String PROPERTY_MALE = "male";
    public final static String PROPERTY_AGE = "age";

    private boolean mMale = true;
    private int mAge;

    public UserBasicInfo() {
        super("基本信息", "显示和查看基本信息");
    }

    public boolean isMale() {
        return mMale;
    }

    public void setMale(boolean male) {
        boolean oldValue = mMale;
        this.mMale = male;
        firePropertyChange(PROPERTY_MALE, oldValue, mMale);
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        int oldValue = mAge;
        this.mAge = age;
        firePropertyChange(PROPERTY_AGE, oldValue, mAge);
    }

    @Override
    public String getText() {
        return "性别：" + (mMale ? "男" : "女") + "\n"
                + "年龄：" + (mAge > 0 ? (mAge + "岁") : "");
    }

}
