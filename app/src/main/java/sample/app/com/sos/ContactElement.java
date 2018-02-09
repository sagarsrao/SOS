
package sample.app.com.sos;

import java.io.Serializable;


public interface ContactElement extends Serializable {

    long getId();

    String getDisplayName();

    boolean isChecked();

    void setChecked(boolean checked, boolean suppressListenerCall);

    void addOnContactCheckedListener(OnContactCheckedListener listener);

    boolean matchesQuery(String[] queryStrings);

}
