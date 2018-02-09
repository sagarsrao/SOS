
package sample.app.com.sos.contact;

import android.content.Context;
import android.util.AttributeSet;

import xyz.danoz.recyclerviewfastscroller.sectionindicator.title.SectionTitleIndicator;

public class ContactSectionIndexer extends SectionTitleIndicator<ContactSection> {

    public ContactSectionIndexer(Context context) {
        super(context);
    }

    public ContactSectionIndexer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ContactSectionIndexer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setSection(ContactSection section) {
        setTitleText( section.getLetter() + "");
    }
}
