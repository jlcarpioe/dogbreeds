package com.github.jlcarpioe.dogbreeds.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.jlcarpioe.dogbreeds.GlideApp;
import com.github.jlcarpioe.dogbreeds.R;

import java.util.List;


/**
 * BreedImagessAdapter
 * Adapter to manage breeds information list.
 *
 * This code is under the MIT License (MIT). See LICENSE file.
 *
 * @author José Luis Carpio E. <jlcarpioe@gmail.com>
 *
 */
public class BreedImagesAdapter extends RecyclerView.Adapter<BreedImagesAdapter.CustomViewHolder> {

	private List<String> items;
	private Context mContext;


	public BreedImagesAdapter(Context context, List<String> items) {
		this.items = items;
		this.mContext = context;

	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	@NonNull
	@Override
	public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_breed_image, parent, false);
		return new CustomViewHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull final CustomViewHolder holder, int position) {
		String x = items.get(position);
		GlideApp.with(mContext).load(x)
//				.thumbnail(GlideApp.with(mContext).load(x).override(20,20))
				.thumbnail(/*sizeMultiplier=*/ 0.25f)
				.placeholder(R.drawable.img_dogface).override(100, 100).fitCenter()
				.into(holder.image);
	}

	public void setItems(List<String> images) {
		items = images;
		notifyDataSetChanged();
	}


	/**
	 * ViewHolder class for layout.
	 *
	 */
	static class CustomViewHolder extends RecyclerView.ViewHolder {

		View itemView;
		ImageView image;

		CustomViewHolder(View itemView) {
			super(itemView);

			this.itemView = itemView;
			this.image = itemView.findViewById(R.id.breed_image);
		}
	}

}
