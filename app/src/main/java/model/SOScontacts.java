package model;

/**
 * Created by Nanda devi shetty b on 16-01-2018.
 */

public class SOScontacts {
    private String contactName;
    private String contactNumber;
    boolean isSelected;

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactname) {
        contactName = contactname;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactnumber) {
        contactNumber = contactnumber;
    }

    public boolean isSelected() {

        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

}

