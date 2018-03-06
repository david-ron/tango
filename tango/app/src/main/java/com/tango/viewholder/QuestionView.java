package com.tango.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tango.R;
import com.tango.models.QuestionModel;

public class QuestionView extends RecyclerView.ViewHolder {

    public TextView titleView;
    public TextView authorView;
    public ImageView starView;
    public TextView numStarsView;
    public TextView bodyView;

    public QuestionView(View itemView) {
        super(itemView);

        titleView = itemView.findViewById(R.id.post_title);
        authorView = itemView.findViewById(R.id.post_author);
        starView = itemView.findViewById(R.id.star);
        numStarsView = itemView.findViewById(R.id.post_num_stars);
        bodyView = itemView.findViewById(R.id.post_body);
    }

    public void bindToPost(QuestionModel questionModel, View.OnClickListener starClickListener) {
        titleView.setText(questionModel.title);
        authorView.setText(questionModel.author);
        numStarsView.setText(String.valueOf(questionModel.starCount));
        bodyView.setText(questionModel.body);

        Log.d("POSTING", questionModel.toString());
        Log.d("POSTING-Question", questionModel.title.toString());
        Log.d("POSTING-Answer", questionModel.body.toString());

        starView.setOnClickListener(starClickListener);
    }
}
