package com.github.jlcarpioe.dogbreeds.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.jlcarpioe.dogbreeds.R;

import java.util.List;


/**
 * BreedsAdapter
 * Adapter to manage breeds information list.
 *
 * This code is under the MIT License (MIT). See LICENSE file.
 *
 * @author José Luis Carpio E. <jlcarpioe@gmail.com>
 *
 */
public class BreedsAdapter extends RecyclerView.Adapter<BreedsAdapter.CustomViewHolder> {

	private List<String> items;
	private OnItemListListener mListener;


	public BreedsAdapter(List<String> items, OnItemListListener listener) {
		this.items = items;
		this.mListener = listener;
	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	@NonNull
	@Override
	public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_breed, parent, false);
		return new CustomViewHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull final CustomViewHolder holder, int position) {
		final String x = items.get(position);

		// set info
		holder.name.setText(x);

		// set click listener
		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if ( mListener != null ) {
					mListener.onSelected(x);
				}
			}
		});
	}

	public void setItems(List<String> breedList) {
		items = breedList;
		notifyDataSetChanged();
	}


	/**
	 * ViewHolder class for layout.
	 *
	 */
	static class CustomViewHolder extends RecyclerView.ViewHolder {

		View itemView;
		TextView name;

		CustomViewHolder(View itemView) {
			super(itemView);

			this.itemView = itemView;
			this.name = itemView.findViewById(R.id.breed_name);
		}
	}


	/**
	 * Interface for tap item.
	 *
	 */
	public interface OnItemListListener {
		void onSelected(String breed);
	}

}
