
package sample.app.com.sos.contact;

import android.net.Uri;

import java.util.Map;
import java.util.Set;

import sample.app.com.sos.ContactElement;




public interface Contact extends ContactElement {

    String getFirstName();
    String getLastName();
    String getEmail(int type);
    String getPhone(int type);
    Map<Integer,String> getMapPhone();
    String getAddress(int type);
    Map<Integer, String> getMapAddress();


    /*The contact letter is used in the ContactBadge (if no contact picture can be found).*/
    char getContactLetter();
    char getContactLetter(ContactSortOrder sortOrder);
    int getContactColor();

    /* Unique key across all contacts that won't change even if the column id changes.*/
    String getLookupKey();

    Uri getPhotoUri();

    Set<Long> getGroupIds();
}
