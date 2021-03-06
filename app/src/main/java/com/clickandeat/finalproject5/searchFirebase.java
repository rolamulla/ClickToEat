//package com.clickandeat.finalproject5;
//
//import android.content.Context;
//import android.media.Image;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.bumptech.glide.Glide;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class searchFirebase extends AppCompatActivity {
//
//        private EditText mSearchField;
//        private ImageButton mSearchBtn;
//        private RecyclerView mResultList;
//        private DatabaseReference mUserDatabase;
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_search_firebase);
//
//            mUserDatabase = FirebaseDatabase.getInstance().getReference("Users");
//
//            mSearchField = (EditText) findViewById(R.id.search_field);
//            mSearchBtn = (ImageButton) findViewById(R.id.search_btn);
//
//            mResultList = (RecyclerView) findViewById(R.id.result_list);
//            mResultList.setHasFixedSize(true);
//            mResultList.setLayoutManager(new LinearLayoutManager(this));
//
//            mSearchBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    String searchText = mSearchField.getText().toString();
//
//                    firebaseUserSearch(searchText);
//
//                }
//            });
//
//        }
//
//        private void firebaseUserSearch(String searchText) {
//
//            Toast.makeText(searchFirebase.this, "Started Search", Toast.LENGTH_LONG).show();
//
//            Query firebaseSearchQuery = mUserDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");
//
//            FirebaseRecyclerAdapter<Users, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Users, UsersViewHolder>(
//
//                    Users.class,
//                    R.layout.list_layout,
//                    UsersViewHolder.class,
//                    firebaseSearchQuery
//
//            ) {
//                @Override
//                protected void populateViewHolder(UsersViewHolder viewHolder, Users model, int position) {
//
//
//                    viewHolder.setDetails(getApplicationContext(), model.getName(), model.getStatus(), model.getImage());
//
//                }
//            };
//
//            mResultList.setAdapter(firebaseRecyclerAdapter);
//
//        }
//
//
//        // View Holder Class
//
//        public static class UsersViewHolder extends RecyclerView.ViewHolder {
//
//            View mView;
//
//            public UsersViewHolder(View itemView) {
//                super(itemView);
//
//                mView = itemView;
//
//            }
//
//            public void setDetails(Context ctx, String userName, String userStatus, String userImage) {
//
//                TextView user_name = (TextView) mView.findViewById(R.id.name_text);
//                TextView user_status = (TextView) mView.findViewById(R.id.status_text);
//                ImageView user_image = (ImageView) mView.findViewById(R.id.profile_image);
//
//
//                user_name.setText(userName);
//                user_status.setText(userStatus);
//
//                Glide.with(ctx).load(userImage).into(user_image);
//            }
//        }
//    }