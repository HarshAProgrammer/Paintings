package com.rackluxury.rolex.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rackluxury.rolex.R;
import com.rackluxury.rolex.activities.CategoriesDetailActivity;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;
import java.util.List;

public class MyCategoriesAdapter extends RecyclerView.Adapter<CategoriesViewHolder>{
    private final Context mContext;
    private List<CategoriesData> myCategoriesList;
    private int lastPosition = -1;

    public MyCategoriesAdapter(Context mContext, List<CategoriesData> myCategoriesList) {
        this.mContext = mContext;
        this.myCategoriesList = myCategoriesList;
    }


    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.categories_recycler_row_item,viewGroup,false);
        return new CategoriesViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoriesViewHolder categoriesViewHolder, int i) {
        categoriesViewHolder.imageView.setImageResource(myCategoriesList.get(i).getCategoriesImage());
        categoriesViewHolder.mTitle.setText(myCategoriesList.get(i).getCategoriesName());
        categoriesViewHolder.mDescription.setText(myCategoriesList.get(i).getCategoriesDescription());
        categoriesViewHolder.mPrice.setText(myCategoriesList.get(i).getCategoriesPrice());

        categoriesViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openDetailActivityFromMContext = new Intent(mContext, CategoriesDetailActivity.class);
                openDetailActivityFromMContext.putExtra("Image",myCategoriesList.get(categoriesViewHolder.getAdapterPosition()).getCategoriesImage());
                openDetailActivityFromMContext.putExtra("Name",myCategoriesList.get(categoriesViewHolder.getAdapterPosition()).getCategoriesName());
                openDetailActivityFromMContext.putExtra("Description",myCategoriesList.get(categoriesViewHolder.getAdapterPosition()).getCategoriesDescription());
                mContext.startActivity(openDetailActivityFromMContext);
                Animatoo.animateSwipeLeft(mContext);


            }
        });

        setAnimation(categoriesViewHolder.itemView,i);
    }
    public void setAnimation(View viewToAnimate,int position){
        if(position>lastPosition){
            ScaleAnimation HomeActivityAnimation = new ScaleAnimation(0.0f,1.0f,0.0f,1.0f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
            HomeActivityAnimation.setDuration(350);
            viewToAnimate.startAnimation(HomeActivityAnimation);
            lastPosition = position;
        }




    }
    @Override
    public int getItemCount() {
        return myCategoriesList.size();
    }
    public void filteredList(ArrayList<CategoriesData> filterList) {

        myCategoriesList = filterList;
        notifyDataSetChanged();
    }
}
class CategoriesViewHolder extends RecyclerView.ViewHolder{
    final ImageView imageView;
    final TextView mTitle;
    final TextView mDescription;
    final TextView mPrice;
    final CardView mCardView;

    public CategoriesViewHolder( View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.ivCategoriesRecyclerRowItemImage);
        mTitle  = itemView.findViewById(R.id.tvCategoriesRecyclerRowItemTitle);
        mDescription = itemView.findViewById(R.id.tvCategoriesRecyclerRowItemDescription);
        mPrice = itemView.findViewById(R.id.tvCategoriesRecyclerRowItemPrice);
        mCardView = itemView.findViewById(R.id.cvCategoriesRecyclerRowItemCard);
    }
}
