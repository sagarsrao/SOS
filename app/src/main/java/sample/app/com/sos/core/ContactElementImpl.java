
package sample.app.com.sos.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import sample.app.com.sos.ContactElement;
import sample.app.com.sos.OnContactCheckedListener;
import sample.app.com.sos.Helper;

/**
 * The concrete but abstract implementation of ContactElement.
 */
abstract class ContactElementImpl implements ContactElement {

    final private long mId;
    private String mDisplayName;

    transient private List<OnContactCheckedListener> mListeners = new ArrayList<>();
    transient private boolean mChecked = false;

    ContactElementImpl(long id, String displayName) {
        mId = id;
        mDisplayName = Helper.isNullOrEmpty(displayName) ? "---" : displayName;
    }

    @Override
    public long getId() {
        return mId;
    }

    @Override
    public String getDisplayName() {
        return mDisplayName != null ? mDisplayName : "";
    }

    protected void setDisplayName(String value) {
        mDisplayName = value;
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void setChecked(boolean checked, boolean suppressListenerCall) {
        boolean wasChecked = mChecked;
        mChecked = checked;
        if (!mListeners.isEmpty() && wasChecked != checked && !suppressListenerCall) {
            for (OnContactCheckedListener listener : mListeners) {
                listener.onContactChecked(this, wasChecked, checked);
            }
        }
    }

    @Override
    public void addOnContactCheckedListener(OnContactCheckedListener listener) {
        mListeners.add(listener);
    }

    @Override
    public boolean matchesQuery(String[] queryStrings) {
        String dispName = getDisplayName();
        if (Helper.isNullOrEmpty(dispName))
            return false;

        dispName = dispName.toLowerCase(Locale.getDefault());
        for (String queryString : queryStrings) {
            if (!dispName.contains(queryString)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return mId + ": " + mDisplayName;
    }

}
