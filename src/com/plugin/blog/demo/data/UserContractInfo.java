package com.plugin.blog.demo.data;

public class UserContractInfo extends UserInfo {
    public final static String PROPERTY_PHONE = "phone";
    public final static String PROPERTY_ADDR = "addr";

    private String mPhone;
    private String mAddress;

    public UserContractInfo() {
        super("联系信息", "查看和修改联系信息");
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        String oldValue = mPhone;
        this.mPhone = phone;

        firePropertyChange(PROPERTY_PHONE, oldValue, mPhone);
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        String oldValue = mAddress;
        this.mAddress = address;

        firePropertyChange(PROPERTY_PHONE, oldValue, mAddress);
    }

    @Override
    public String getText() {
        return "手机号：" + ((mPhone != null) ? mPhone : "") + "\n"
                + "联系地址：" + ((mAddress != null) ? mAddress : "");
    }

}
