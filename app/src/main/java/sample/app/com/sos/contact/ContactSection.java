
package sample.app.com.sos.contact;

public class ContactSection {

    private final char mLetter;
    private final int mSectionPos;
    private final int mContactPos;

    ContactSection(char letter, int sectionPos, int contactPos) {
        mLetter = letter;
        mSectionPos = sectionPos;
        mContactPos = contactPos;
    }

    public char getLetter() {
        return mLetter;
    }

    public int getSectionPos() {
        return mSectionPos;
    }

    public int getContactPos() {
        return mContactPos;
    }

}
