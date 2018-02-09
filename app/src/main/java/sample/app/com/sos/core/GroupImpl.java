
package sample.app.com.sos.core;

import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import sample.app.com.sos.OnContactCheckedListener;
import sample.app.com.sos.contact.Contact;
import sample.app.com.sos.group.Group;


class GroupImpl extends ContactElementImpl implements Group {

    static GroupImpl fromCursor(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(ContactsContract.Groups._ID));
        String title = cursor.getString(cursor.getColumnIndex(ContactsContract.Groups.TITLE));
        return new GroupImpl(id, title);
    }

    private Map<Long, Contact> mContacts = new HashMap<>();

    private GroupImpl(long id, String displayName) {
        super(id, displayName);
    }

    @Override
    public Collection<Contact> getContacts() {
        return mContacts.values();
    }

    void addContact(Contact contact) {
        long contactId = contact.getId();
        if (!mContacts.keySet().contains(contactId)) {
            mContacts.put(contact.getId(), contact);
        }
    }

    boolean hasContacts() {
        return mContacts.size() > 0;
    }

    @Override
    public void addOnContactCheckedListener(OnContactCheckedListener listener) {

    }


}
