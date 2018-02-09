package adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import model.SOScontacts;
import sample.app.com.sos.R;

/**
 * Created by Nanda devi shetty b on 12-01-2018.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private List<SOScontacts> mContactList;
    public List<SOScontacts> selected_usersList = new ArrayList<>();

    String contacts[];
    //String[] mList;
    Context mContext;
    ContactsViewHolder contactsViewHolder;

    public ContactsAdapter(List<SOScontacts> mContactList, List<SOScontacts> selectedList, String[] contacts, Context mContext) {
        this.mContactList = mContactList;
        this.mContext = mContext;
        this.selected_usersList = selectedList;
        this.contacts = contacts;
        //  this.mList=list;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_contact, null);
        contactsViewHolder = new ContactsViewHolder(view);


        return contactsViewHolder;
    }

    @Override
    public void onBindViewHolder(ContactsAdapter.ContactsViewHolder holder, int position) {
        SOScontacts SOScontacts = mContactList.get(position);
        holder.tv1.setText(SOScontacts.getContactName());
        holder.tv2.setText(SOScontacts.getContactNumber());

        if (selected_usersList.contains(mContactList.get(position)))
            holder.ll.setBackgroundColor(ContextCompat.getColor(mContext, R.color.list_item_normal_state));
        else
            holder.ll.setBackgroundColor(ContextCompat.getColor(mContext, R.color.list_item_selected_state));

    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }


    public class ContactsViewHolder extends RecyclerView.ViewHolder {
        TextView tv1;
        TextView tv2;
        public LinearLayout ll;

        public ContactsViewHolder(View itemView) {
            super(itemView);

            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);

            tv1.setText("create contacts");


            ll = itemView.findViewById(R.id.ll);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "hdgcdsh", Toast.LENGTH_SHORT);
                }
            });
        }
    }
}