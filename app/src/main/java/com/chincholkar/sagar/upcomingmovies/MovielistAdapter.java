package com.chincholkar.sagar.upcomingmovies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovielistAdapter  extends RecyclerView.Adapter<MovielistAdapter.ViewHolder> {
    private List<MoviesList> listItems;
    private Context c;
    private String imgurl;
    private String imgid;
    private String Sadult;
    private String overview;
    public MovielistAdapter(Context c, List<MoviesList> listItems) {
        this.c = c;
        this.listItems = listItems;
    }

    @Override
    public MovielistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);

        return new MovielistAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MovielistAdapter.ViewHolder holder, int position) {
        final MoviesList  Mlist=listItems.get(position);

        Sadult=Mlist.getAdult();
        imgid= String.valueOf(Mlist.getId());
       // overview=Mlist.getOverview();
        imgurl=Mlist.getPoster_path();
        holder.title.setText(Mlist.getTitle());// DateUtils.formatDateTime(c, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_12HOUR)
        holder.releasedate.setText(Mlist.getDate());
        if(Sadult.equalsIgnoreCase("false")) {
            holder.adult.setText("U/A");
        }else{
            holder.adult.setText("A");
        }
       // picasso
        Picasso.with(c)
                .load("https://image.tmdb.org/t/p/w500"+imgurl)
                .into(holder.posterimg);

        holder.posterimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(c,MovieDetailScreen.class);

                i.putExtra("id",Mlist.getId());
                i.putExtra("title",Mlist.getTitle());
                i.putExtra("Overview",Mlist.getOverview());
                i.putExtra("vote",Mlist.getVote_average());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(i);

            }
        });

        //holder.url.setText(plist.getDiscription());
       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //url2=plist.getDiscription();
                //Intent i =new Intent(c,WebViewActivity.class);
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //i.putExtra("url",url2);
                //Toast.makeText(c,url2,Toast.LENGTH_SHORT).show();
                //c.startActivity(i);

            }
        });*/

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        private ImageView posterimg,play;
        private TextView releasedate;
        private TextView title,adult;





        public ViewHolder(View itemView) {
            super(itemView);
          //  itemView.setOnClickListener(c.getApplicationContext());
            posterimg=(ImageView)itemView.findViewById(R.id.imageView);
            play=(ImageView)itemView.findViewById(R.id.play);
            title=(TextView) itemView.findViewById(R.id.title);
            releasedate=(TextView)itemView.findViewById(R.id.releasedate);
            adult=(TextView) itemView.findViewById(R.id.adulttextview);


        }


    }
}
