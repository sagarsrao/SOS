
package sample.app.com.sos.group;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import sample.app.com.sos.R;

public class GroupAdapter extends RecyclerView.Adapter<GroupViewHolder> {

    private LayoutInflater mInflater;

    private List<? extends Group> mGroups;

    public GroupAdapter(List<? extends Group> groups) {
        mGroups = groups;
    }

    public void setData(List<? extends Group> groups) {
        mGroups = groups;
        notifyDataSetChanged();
    }

    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) {
            mInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        View view = mInflater.inflate(R.layout.cp_group_list_item, parent, false);
        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GroupViewHolder holder, int position) {
        if (mGroups != null) {
            holder.bind( mGroups.get(position) );
        }
    }

    @Override
    public int getItemCount() {
        return mGroups == null ? 0 : mGroups.size();
    }

    @Override
    public long getItemId(int position) {
        return mGroups == null ? super.getItemId(position) : mGroups.get(position).getId();
    }

}
