package pe.edu.cibertec.retrofitgitflow;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PostAdapter extends ArrayAdapter<Post> {
    public PostAdapter(Context context, ArrayList<Post> post) {
        super(context, 0, post);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Post post = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_post, parent, false);
        }
        // Lookup view for data population
        TextView tvtitle =  convertView.findViewById(R.id.tvTitle);
        TextView tvdesc =   convertView.findViewById(R.id.tvDescrip);
        // Populate the data into the template view using the data object
        tvtitle.setText(post.getTitle());
        tvdesc.setText(post.getText());
        // Return the completed view to render on screen

        return convertView;
    }
}
