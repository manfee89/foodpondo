package com.example.manfee.foodpanda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private static final String MESSAGES_CHILD = "restaurants";
    private static final String TAG = "debug";
    private FirebaseRecyclerAdapter<Restaurant, CustomViewHolder> mFirebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Instantiate the RequestQueue.

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Log.d("test", "test");
        //        FIREBASE CODE HERE

        // New child entries
        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        SnapshotParser<Restaurant> parser = new SnapshotParser<Restaurant>() {
            @Override
            public Restaurant parseSnapshot(DataSnapshot dataSnapshot) {
                Restaurant restaurant = dataSnapshot.getValue(Restaurant.class);
                if (restaurant != null) {
                    restaurant.setId(dataSnapshot.getKey());
                }
                return restaurant;
            }
        };

        DatabaseReference restaurantsRef = mFirebaseDatabaseReference.child(MESSAGES_CHILD);

        FirebaseRecyclerOptions<Restaurant> options =
                new FirebaseRecyclerOptions.Builder<Restaurant>()
                        .setQuery(restaurantsRef, parser)
                        .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Restaurant, CustomViewHolder>(options) {
            @Override
            public CustomViewHolder onCreateViewHolder(ViewGroup parent, int i) {

                return new CustomViewHolder(LayoutInflater.from(parent.getContext()), parent);
            }

            @Override
            protected void onBindViewHolder(final CustomViewHolder holder, int position, Restaurant restaurant) {
                holder.mRestaurantName.setText(restaurant.getRestaurantName());
                holder.mAddress.setText(restaurant.getAddress());
                holder.mRating.setText(restaurant.getRating().toString());

                Picasso.get().load(restaurant.getImageUrl()).into(holder.imageView);

            }
        };


        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int friendlyMessageCount = mFirebaseAdapter.getItemCount();
                int lastVisiblePosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                // If the recycler view is initially being loaded or the user is at the bottom of the list, scroll
                // to the bottom of the list to show the newly added message.
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (friendlyMessageCount - 1) && lastVisiblePosition == (positionStart - 1))) {
                    recyclerView.scrollToPosition(positionStart);
                }
            }
        });

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mFirebaseAdapter);


//--------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------

    }

    @Override
    public void onPause() {
        mFirebaseAdapter.stopListening();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mFirebaseAdapter.startListening();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {


        ImageView imageView;
        TextView mRestaurantName, mRating, mAddress;

        public CustomViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.custom_row, parent, false));
            Log.d(TAG, "here");
            imageView = itemView.findViewById(R.id.imageView);
            mRestaurantName = itemView.findViewById(R.id.id_RestaurantName);
            mRating = itemView.findViewById(R.id.id_Rating);
            mAddress = itemView.findViewById(R.id.id_Address);
        }
    }

}



