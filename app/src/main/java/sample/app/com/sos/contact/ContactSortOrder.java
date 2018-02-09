
package sample.app.com.sos.contact;

import android.util.Log;

public enum ContactSortOrder {
    FIRST_NAME,     // sort by first name
    LAST_NAME,      // sort by last name
    AUTOMATIC;      // sort by display name (device specific)

    public static ContactSortOrder lookup(String name) {
        try {
            return ContactSortOrder.valueOf(name);
        }
        catch (IllegalArgumentException ignore) {
            Log.e(ContactSortOrder.class.getSimpleName(), ignore.getMessage());
            return AUTOMATIC;
        }
    }

}
