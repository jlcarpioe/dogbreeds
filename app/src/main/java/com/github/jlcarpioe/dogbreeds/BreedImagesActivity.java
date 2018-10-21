package com.github.jlcarpioe.dogbreeds;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.github.jlcarpioe.dogbreeds.adapters.BreedImagesAdapter;
import com.github.jlcarpioe.dogbreeds.enums.API_ROUTE;
import com.github.jlcarpioe.dogbreeds.enums.PARAM_TYPE;
import com.github.jlcarpioe.dogbreeds.models.ResponseList;
import com.github.jlcarpioe.dogbreeds.net.ApiError;
import com.github.jlcarpioe.dogbreeds.net.ApiParam;
import com.github.jlcarpioe.dogbreeds.net.RequestManager;
import com.github.jlcarpioe.dogbreeds.widgets.ProgressLoader;

import java.util.ArrayList;


/**
 * BreedImagesActivity.
 * Show list of images breed.
 *
 * This code is under the MIT License (MIT). See LICENSE file.
 *
 * @author José Luis Carpio E. <jlcarpioe@gmail.com>.
 *
 */
public class BreedImagesActivity extends AppCompatActivity {

	private ProgressLoader loader;
	private BreedImagesAdapter mAdapter;
	private String breed;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_images);
		loader = ProgressLoader.setup(this);

		// breed selected
		breed = getIntent().getStringExtra("breed_selected");

		// adapter to fill views of items
		mAdapter = new BreedImagesAdapter(this, new ArrayList<String>());

		// recycler view
		RecyclerView recyclerView = findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new GridLayoutManager(this,2));
		recyclerView.setAdapter(mAdapter);
	}

	@Override
	protected void onResume() {
		super.onResume();
		getData();
	}


	/**
	 * Get data from server
	 *
	 */
	private void getData() {
		loader.show();
		ArrayList<ApiParam> params = new ArrayList<>();
		params.add(new ApiParam("breed", PARAM_TYPE.URL_PARAM, breed));

		RequestManager.request(
				this,
				params,
				API_ROUTE.IMAGES,
				ResponseList.class,
				ApiError.class,
				new Response.Listener<ResponseList>() {
					@Override
					public void onResponse(ResponseList response) {
						mAdapter.setItems(response.getList());
						loader.dismiss();
					}
				},
				null,
				null
		);
	}

}
