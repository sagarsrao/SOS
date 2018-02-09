package sample.app.com.sos;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import sample.app.com.sos.contact.Contact;
import sample.app.com.sos.contact.ContactDescription;
import sample.app.com.sos.contact.ContactSortOrder;
import sample.app.com.sos.core.ContactImpl;
import sample.app.com.sos.core.ContactPickerActivity;
import sample.app.com.sos.group.Group;
import sample.app.com.sos.picture.ContactPictureType;

import static sample.app.com.sos.core.ContactPickerActivity.RESULT_CONTACT_DATA;

public class ContactActivity extends AppCompatActivity {

    // private static final String TAG = ContactActivity.class.getSimpleName();
    // private static final int REQUEST_CODE_PICK_CONTACTS = 1;
    // private Uri uriContact;
    // private String contactID;
    // private LinearLayoutManager mLayoutManager;
    //  String[] contacts ={"Create Contacts","Create Contacts","Create Contacts","Create Contacts"};
    // ListView listView;
    //private boolean mDarkTheme;

    //  RecyclerView rv;
    //TextView tv1;
    //TextView tv2;
    //ContactsAdapter adapter;
    // List<SOScontacts> contactList=new ArrayList<>();
    // List<SOScontacts> selected_usersList = new ArrayList<>();
    // SOScontacts SOScontacts = new SOScontacts();

//    String contactNumber;
    //  String contactName;
    // String[] list = new String[]{"Create Contact", "Create Contact", "Create Contact"};
    //  private static final String EXTRA_DARK_THEME = "EXTRA_DARK_THEME";

    Button createContacts;
    ImageButton sendMsg;
    EditText mEdittext;
    TextView mTextview;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

    String finalResultantValue = null;
    private static final String EXTRA_GROUPS = "EXTRA_GROUPS";
    private static final String EXTRA_CONTACTS = "EXTRA_CONTACTS";

    private static final String SMS_SENT_INTENT_FILTER = "sample.app.com.sos.sms_send";
    private static final String SMS_DELIVERED_INTENT_FILTER = "sample.app.com.sos.sms_delivered";


    private static final int REQUEST_CONTACT = 0;

    private List<Contact> mContacts;

    private List<Contact> mContactsForPhoneNumber;

    private List<Group> mGroups;
    private List<ContactImpl> mContactImpl = new ArrayList<>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            //  mDarkTheme = savedInstanceState.getBoolean(EXTRA_DARK_THEME);

            //  mGroups = (List<Group>) savedInstanceState.getSerializable(EXTRA_GROUPS);
            //mContacts = (List<Contact>) savedInstanceState.getSerializable(EXTRA_CONTACTS);
            //}
            //else {

            //  mDarkTheme = intent.getBooleanExtra(EXTRA_DARK_THEME, false);
            Intent intent = getIntent();
            mGroups = (List<Group>) intent.getSerializableExtra(EXTRA_GROUPS);
            mContacts = (List<Contact>) intent.getSerializableExtra(EXTRA_CONTACTS);
            // mContactImpl=(List<ContactImpl>)intent.getSerializableExtra(RESULT_CONTACT_DATA);
        }

        setContentView(R.layout.activity_contact);
        mEdittext = findViewById(R.id.edittext);
        mTextview = findViewById(R.id.contacts);
        sendMsg = findViewById(R.id.send);
        createContacts = findViewById(R.id.button);
        // tv1 = (TextView) findViewById(R.id.tv1);
        //tv2 = (TextView) findViewById(R.id.tv2);
        // rv = (RecyclerView) findViewById(R.id.rv);
        //rv.setHasFixedSize(true);

        //  mLayoutManager = new LinearLayoutManager(this);
        //  adapter = new ContactsAdapter(contactList,selected_usersList,contacts,getApplicationContext());
        sendMsg.setOnClickListener(new View.OnClickListener() {
            //   private Map<String,ContactImpl> mContactNum = new HashMap<>();
            // Cursor cursor= null;

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Intent data = getIntent();
                sendMsgToContacts(data);

                //         /* ContactElement contactElement = null;
                //            String smsNumber="+918861932696;+919986917021";
                // String smsNumber = edittextSmsNumber.getText().toString();
                //              String smsText = mEdittext.getText().toString();
//
                //          Uri uri = Uri.parse("smsto:" + smsNumber);
                //            Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                //              intent.putExtra("sms_body", smsText);
//                startActivity(intent);
//*/
            }
        });

        if (createContacts != null) {
            createContacts.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ContactActivity.this, ContactPickerActivity.class)
                       /* .putExtra(ContactPickerActivity.EXTRA_THEME, mDarkTheme ?
                                R.style.Theme_Dark : R.style.Theme_Light)*/
                            .putExtra(ContactPickerActivity.EXTRA_CONTACT_BADGE_TYPE,
                                    ContactPictureType.ROUND.name())
                            .putExtra(ContactPickerActivity.EXTRA_CONTACT_DESCRIPTION,
                                    ContactDescription.ADDRESS.name())
                            .putExtra(ContactPickerActivity.EXTRA_SHOW_CHECK_ALL, true)
                            .putExtra(ContactPickerActivity.EXTRA_SELECT_CONTACTS_LIMIT, 0)
                            .putExtra(ContactPickerActivity.EXTRA_ONLY_CONTACTS_WITH_PHONE, false)
                            .putExtra(ContactPickerActivity.EXTRA_WITH_GROUP_TAB, false)
                            .putExtra(ContactPickerActivity.EXTRA_CONTACT_DESCRIPTION_TYPE,
                                    ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                            .putExtra(ContactPickerActivity.EXTRA_CONTACT_SORT_ORDER,
                                    ContactSortOrder.AUTOMATIC.name());

                    startActivityForResult(intent, REQUEST_CONTACT);
                }
                // onClickSelectContact(view);
            });
        } else {
            finish();
        }
        populateContactList(mGroups, mContacts);

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sendMsgToContacts(Intent data) {


        mContactsForPhoneNumber = mContacts;

        List<String> phNum1 = new ArrayList<>();
        //String phNum1=null;

        String s1 = null;
        List<String> phoneNoList = new ArrayList<>();
        for (int i = 0; i <= mContactsForPhoneNumber.size() - 1; i++) { //2
            String phone = String.valueOf(mContactsForPhoneNumber.get(i).getMapPhone().values());
            phone = phone.replaceAll("\\[", "").replaceAll("\\]", "");

            phoneNoList.add(phone);
            //phNum1= String.valueOf(mContactsForPhoneNumber.get(i).getMapPhone().values());
            Log.d("SomeValue", "sendMsgToContacts: " + phNum1);

            Log.d("SomeOtherValueS1", "sendMsgToContacts: " + phone);
            // Log.d("ConTactActivity", "sendMsgToContacts: " + finalResultantValue.replaceAll("\\[", "").replaceAll("\\]", ""));
        }


        final String message = mEdittext.getText().toString();
        //  final String ultimatePhoneNumber = finalResultantValue;
        //  Uri smsToUri = Uri.parse("smsto:" + s1);

      /*  Intent i = new Intent(Intent.ACTION_VIEW);
        i.putExtra("address",s1);
        i.putExtra("sms_body",message);
        i.setType("vnd.android-dir/mms-sms");
        startActivity(i);*/
        for (String phoneNumber : phoneNoList) {
            /*TODO USE HANDLER THREADS OR ASYNC TASK THAREAD HANDLERS*/
            sendSms(phoneNumber, message);
        }


    }

    private void sendSms(String phoneNumber, String message) {
        PendingIntent sentPI = PendingIntent.getBroadcast(ContactActivity.this, 0, new Intent(
                SMS_SENT_INTENT_FILTER), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(ContactActivity.this, 0, new Intent(
                SMS_DELIVERED_INTENT_FILTER), 0);
        SmsManager sms = SmsManager.getDefault();

        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mGroups != null) {
            outState.putSerializable(EXTRA_GROUPS, (Serializable) mGroups);
        }
        if (mContacts != null) {
            outState.putSerializable(EXTRA_CONTACTS, (Serializable) mContacts);
        }
    }


    private void populateContactList(List<Group> groups, List<Contact> contacts) {
        // we got a result from the contact picker --> show the picked contacts
        TextView contactsView = findViewById(R.id.contacts);
        SpannableStringBuilder result = new SpannableStringBuilder();

        try {
            if (groups != null && !groups.isEmpty()) {
                result.append("GROUPS\n");
                for (Group group : groups) {
                    populateContact(result, group, "");
                    for (Contact contact : group.getContacts()) {
                        populateContact(result, contact, "    ");
                    }
                }
            }
            if (contacts != null && !contacts.isEmpty()) {
                 result.append("CONTACTS\n");
                for (ContactElement contact : contacts) {
                    populateContact(result, contact, "");
                }
            }
        } catch (Exception e) {
            result.append(e.getMessage());
        }
        contactsView.setText(result);
    }

    private void populateContact(SpannableStringBuilder result, ContactElement element, String prefix) {
        // int start = result.length();
        String displayName = element.getDisplayName();
        result.append(prefix);
        result.append(displayName + "\n");

        // result.setSpan(new BulletSpan(15), start, result.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }

    @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CONTACT && resultCode == Activity.RESULT_OK && data != null &&
                (data.hasExtra(ContactPickerActivity.RESULT_GROUP_DATA) ||
                        data.hasExtra(RESULT_CONTACT_DATA))) {

            // we got a result from the contact picker --> show the picked contacts
            mGroups = (List<Group>) data.getSerializableExtra(ContactPickerActivity.RESULT_GROUP_DATA);
            mContacts = (List<Contact>) data.getSerializableExtra(RESULT_CONTACT_DATA);
            populateContactList(mGroups, mContacts);
        }
        //  if (requestCode == REQUEST_CODE_PICK_CONTACTS && resultCode == RESULT_OK) {
        //    Log.d(TAG, "Response: " + data.toString());
        //uriContact = data.getData();

        //retrieveContactNumber();
        //retrieveContactName();*/
        //adapter.update(contactList);
        //rv.setLayoutManager(mLayoutManager);
        //rv.setAdapter(adapter);
        //adapter.notifyDataSetChanged();

    }
}

   /* private void retrieveContactNumber() {

        contactNumber = null;
        Cursor cursorID = getContentResolver().query(uriContact, new String[]{ContactsContract.Contacts._ID}, null, null, null);
        if (cursorID.moveToFirst()) {
            contactID = cursorID.getString(cursorID.getColumnIndex(ContactsContract.Contacts._ID));
        }
        cursorID.close();

        Cursor cursorPhone = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},

                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " +
                        ContactsContract.CommonDataKinds.Phone.TYPE + " = " +
                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,

                new String[]{contactID},
                null);

        if (cursorPhone.moveToFirst()) {
            contactNumber = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            SOScontacts.setContactNumber(contactNumber);
        }

        cursorPhone.close();
        Log.d(TAG, "Contact Phone Number: " + contactNumber);
    }

    public void retrieveContactName() {
         contactName = null;
         Cursor cursor = getContentResolver().query(uriContact, null, null, null, null);

        if (cursor.moveToFirst()) {

            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            SOScontacts.setContactName(contactName);

        }
        contactList.add(SOScontacts);
        cursor.close();
        Log.d(TAG, "Contact Name: " + contactName);
    }
*/

    /*public  void retrieveContactPhoto(){
        Bitmap photo = null;

        try {
            InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(getContentResolver(),
                    ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(contactID)));

            if (inputStream != null) {
                photo = BitmapFactory.decodeStream(inputStream);
                ImageView imageView = (ImageView) findViewById(R.id.);
                imageView.setImageBitmap(photo);
            }

            assert inputStream != null;
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/







/*
        Uri queryUri = ContactsContract.Contacts.CONTENT_URI;

        String[] projection = new String[] {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME};

        String selection = ContactsContract.Contacts.DISPLAY_NAME + " IS NOT NULL";

        CursorLoader cursorLoader = new CursorLoader(
                this,
                queryUri,
                projection,
                selection,
                null,
                null);

        Cursor cursor = cursorLoader.loadInBackground();

        String[] from = {ContactsContract.Contacts.DISPLAY_NAME};
        int[] to = {android.R.id.text1};

        ListAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                cursor,
                from,
                to,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        list.setAdapter(adapter);
    }

}*/

       /* list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent=new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivity(intent);


                //Intent intent=new Intent(this, );

            }
        });
    }
}*/