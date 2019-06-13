package com.example.creditcard;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import java.util.ArrayList;

public class CreditCardsAdapter extends CreditCards implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.creditcards_list);

        Button one = (Button) findViewById(R.id.oneButton);
        one.setOnClickListener(this);
        Button two = (Button) findViewById(R.id.twoButton);
        two.setOnClickListener(this);
        Button three = (Button) findViewById(R.id.threeButton);
        three.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ImageView imageview =(ImageView) findViewById(R.id.clubListImage3);
        imageview.setVisibility(View.visible);

        switch (v.getId()) {

            case R.id.oneButton:
                clubListImage3.setVisibility(View.visible);
                break;

            case R.id.twoButton:
                clubListImage.setVisibility(View.visible);
                break;

            case R.id.threeButton:
                clubListImage2.setVisibility(View.visible);
                break;

            default:
                break;
        }

    }

    static class viewHolder{
        TextView name;
        TextView location;
        TextView theme;
        TextView dresscode;
        TextView peopeleCapacity;
        ImageView image;
    }

    private Context context;
    private int resource;
    private int lastPosition = -1;

    public ClubListAdapter(Context context, int resource, ArrayList<Clubs> objects) {
        super(context, resource , objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position,View convertView, ViewGroup parent) {

        setupImageLoader();
        String name = getItem(position).getName();
        String location = getItem(position).getLocation();
        String theme = getItem(position).getTheme();
        String dresscode = getItem(position).getDresscode();
        String peopleCapacity = getItem(position).getPeopleCapacity();
        String image = getItem(position).getImage();

        final View result;

        viewHolder holder;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
            holder = new viewHolder();
            holder.name = convertView.findViewById(R.id.clubName);
            holder.location = convertView.findViewById(R.id.clubLocation);
            holder.theme = convertView.findViewById(R.id.clubTheme);
            holder.dresscode = convertView.findViewById(R.id.clubdresscode);
            holder.peopeleCapacity = convertView.findViewById(R.id.clubpeopleCapacity);
            holder.image = convertView.findViewById(R.id.clubListImage);

            result = convertView;
            convertView.setTag(holder);
        }
        else{
            holder = (viewHolder) convertView.getTag();
            result = convertView;
        }

        if(position != 0) {
            Animation animation = AnimationUtils.loadAnimation(context, (position > lastPosition) ? R.anim.animation_down : R.anim.animation_up);

            result.startAnimation(animation);

            lastPosition = position;
        }
        else {
            Animation animation = AnimationUtils.loadAnimation(context, (position >= lastPosition) ? R.anim.animation_none : R.anim.animation_up);

            result.startAnimation(animation);

            lastPosition = position;
        }

        ImageLoader imageLoader = ImageLoader.getInstance();

        int defaultImage = context.getResources().getIdentifier("@mipmap/logo",null,context.getPackageName());

        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(defaultImage)
                .showImageOnFail(defaultImage)
                .showImageOnLoading(defaultImage).build();

        imageLoader.displayImage(image,holder.image,options);

        holder.name.setText("NAME: "+name);
        holder.location.setText("LOCATION: "+location);
        holder.theme.setText("THEME: "+theme);
        holder.dresscode.setText("DRESS CODE: "+dresscode);
        holder.peopeleCapacity.setText("FITS "+peopleCapacity+" PEOPLE");

        return convertView;
    }


    private void setupImageLoader(){
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
    }
}