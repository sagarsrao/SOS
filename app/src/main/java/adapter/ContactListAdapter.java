package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import model.SOScontacts;
import sample.app.com.sos.R;

/**
 * Created by Nanda devi shetty b on 17-01-2018.
 */
public class ContactListAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflter;
    private List<SOScontacts> contactList;


    public ContactListAdapter(List<SOScontacts> contactList, Context mContext) {
        this.contactList = contactList;
        this.mContext = mContext;
        inflter = (LayoutInflater.from(mContext));
        //  this.mList=list;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

         TextView   tv1 = view. findViewById(R.id.tv1);
         TextView   tv2 = view.findViewById(R.id.tv2);
        view = inflter.inflate(R.layout.adapter_contact,null);
        SOScontacts SOScontacts =contactList.get(i);
        tv1.setText(SOScontacts.getContactName());
        tv2.setText(SOScontacts.getContactNumber());

        return view;
    }
}
