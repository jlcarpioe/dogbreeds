package com.github.jlcarpioe.dogbreeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.github.jlcarpioe.dogbreeds.adapters.BreedsAdapter;
import com.github.jlcarpioe.dogbreeds.enums.API_ROUTE;
import com.github.jlcarpioe.dogbreeds.models.ResponseList;
import com.github.jlcarpioe.dogbreeds.net.ApiError;
import com.github.jlcarpioe.dogbreeds.net.ApiParam;
import com.github.jlcarpioe.dogbreeds.net.RequestManager;

import java.util.ArrayList;


/**
 * MainActivity.
 * Show list of breeds.
 *
 * This code is under the MIT License (MIT). See LICENSE file.
 *
 * @author José Luis Carpio E. <jlcarpioe@gmail.com>.
 *
 */
public class MainActivity extends AppCompatActivity implements BreedsAdapter.OnItemListListener {

	private BreedsAdapter mAdapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// adapter to fill views of items
		mAdapter = new BreedsAdapter(new ArrayList<String>(), this);

		// recycler view
		RecyclerView recyclerView = findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(mAdapter);
	}

	@Override
	protected void onResume() {
		super.onResume();
		getData();
	}

	@Override
	public void onSelected(String breed) {
		Intent intent = new Intent(this, BreedImagesActivity.class);
		intent.putExtra("breed_selected", breed);
		startActivity(intent);
	}


	/**
	 * Get data from server
	 *
	 */
	private void getData() {
		RequestManager.request(
				this,
				new ArrayList<ApiParam>(),
				API_ROUTE.LIST,
				ResponseList.class,
				ApiError.class,
				new Response.Listener<ResponseList>() {
					@Override
					public void onResponse(ResponseList response) {
						mAdapter.setItems(response.getList());
					}
				},
				null,
				null
		);
	}

}
