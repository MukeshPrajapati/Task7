package mukesh.com.task7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<UserInfo> {

    Context userInfos;
    int resource;
    ArrayList<UserInfo> infoArrayAdapter;


    public UserAdapter(Context context, int resource, ArrayList<UserInfo> objects) {
        super(context, resource, objects);
        this.userInfos = context;
        this.resource = resource;
        this.infoArrayAdapter = objects;
    }

    static class ViewHolder
    {
        TextView userId, id, title, body;
    }

    public View getView(int position, View contextView, ViewGroup viewGroup){
        ViewHolder holder;

        if(contextView == null){

            holder = new ViewHolder();
            contextView = LayoutInflater.from(userInfos).inflate(resource, viewGroup, false);
            holder.userId = (TextView)contextView.findViewById(R.id.userId);
            holder.id = (TextView)contextView.findViewById(R.id.id);
            holder.title = (TextView)contextView.findViewById(R.id.title);
            holder.body = (TextView)contextView.findViewById(R.id.body);
            contextView.setTag(holder);

        }
        else {
            holder = (ViewHolder)contextView.getTag();
        }

        UserInfo u = infoArrayAdapter.get(position);

        holder.userId.setText("Userid: " +String.valueOf(u.getUserID()));
        holder.id.setText("ID: "+String.valueOf(u.getId()));
        holder.title.setText("Title: "+u.getTitle());
        holder.body.setText("Body: "+u.getBody());

        return contextView;
    }
}