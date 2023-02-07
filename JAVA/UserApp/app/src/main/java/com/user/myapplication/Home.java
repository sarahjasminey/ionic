package com.user.myapplication;

import android.content.Context;
import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class Home extends Fragment {



    private String URLstring = "https://teddiapp.com/app/api/user/getcat";
//    private static ProgressDialog mProgressDialog;
//    private ArrayList<GoodModel> goodModelArrayList;
//    private ArrayList<String> names = new ArrayList<String>();
//    private Spinner spinner;
//    int flags[] = {R.drawable.man, R.drawable.man1, R.drawable.man, R.drawable.man1};

    ImageView creatjov;
    LinearLayout creatjob;
    TextView seeall;
    LinearLayout linearLayout;
//    List<String> jsonResponses = new ArrayList<>();

    TextView textView1,textView2,textView3,textView4;

    RecyclerView recyclerView;
    List<categories> categoriesList=new ArrayList<>();
    categories categories=new categories();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        seeall = (TextView)v.findViewById(R.id.seeall);
        recyclerView=(RecyclerView)v.findViewById(R.id.recyclerview_sub_categories);
        linearLayout = v.findViewById(R.id.lin1);


//        spinner = v.findViewById(R.id.homecat);
        creatjob = v.findViewById(R.id.creatjob);
        retrieveJSON();

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Create_Job.class);
                startActivity(intent);


            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),All_categories.class);
                startActivity(intent);
            }
        });
//
//        textView1 = (TextView)v.findViewById(R.id.Nurse1);
//        textView2 = (TextView)v.findViewById(R.id.teacher1);
//        textView3 = (TextView)v.findViewById(R.id.Gardener1);
//        textView4 = (TextView)v.findViewById(R.id.Vegetable1);

        seeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        creatjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Create_Job.class);
                startActivity(intent);
            }
        });
        return v;
    }

    private void retrieveJSON() {


//         showSimpleProgressDialog(getActivity(), "Loading...","Fetching Json",false);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);

                            JSONObject jsonObject = obj.getJSONObject("data");

                            if(jsonObject.optString("status").equals("success")){

                                categoriesList=new ArrayList<>();


                                JSONArray dataArray  = jsonObject.getJSONArray("sub_cat");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    categories=new categories();

                                    JSONObject dataobj = dataArray.getJSONObject(i);
                                    categories.setFileID(dataobj.getString("id"));
                                    categories.setFilename(dataobj.getString("sub_category"));
                                    categories.setImage(dataobj.getString("images"));
                                   // String nurse = dataobj.getString("sub_category");
                                    categoriesList.add(categories);

                                }
                                CategoriesAdapter movieAdapter = new CategoriesAdapter(getContext(),categoriesList);
                                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
                                recyclerView.setLayoutManager(mLayoutManager);
                                recyclerView.setItemAnimator(new DefaultItemAnimator());
                                recyclerView.setAdapter(movieAdapter);
                                recyclerView.setNestedScrollingEnabled(false);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(stringRequest);


    }

    private void OpennewActivity() {
        Intent intent = new Intent(getActivity(), Create_Job.class);
        startActivity(intent);
       // finish();

    }

    public static class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder>{

        List<categories> categoriesList;
        Context context;

        public CategoriesAdapter(Context mcontext, List<categories> categoriesList){
            this.context=mcontext;
            this.categoriesList =categoriesList;

        }

        @NonNull
        @Override
        public CategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View listItem= layoutInflater.inflate(R.layout.layout_getcategory_row, viewGroup, false);
            CategoriesAdapter.ViewHolder viewHolder = new CategoriesAdapter.ViewHolder(listItem);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull CategoriesAdapter.ViewHolder viewHolder, int i) {
            final categories categories=categoriesList.get(i);
            viewHolder.tv_name.setText(categories.getFilename());
//            viewHolder.imageView.s

            Glide.with(context)
                    .load("https://teddiapp.com/app/assets/admin/image/subcategory/" + categories.getImage())
                    .fitCenter()
                    .dontAnimate() // run ji
                    .into(viewHolder.imageView);


        }

        @Override
        public int getItemCount() {
             return categoriesList.size();
        }



        public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public TextView tv_name;
            public LinearLayout linearLayout;
            public  ImageView imageView;

            public ViewHolder(View itemView) {
                super(itemView);
                this.tv_name=(TextView)itemView.findViewById(R.id.text_name);
                this.imageView=(ImageView)itemView.findViewById(R.id.image);

                itemView.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                Toast.makeText(context,"position"+position,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,Create_Job.class);
                intent.putExtra("name" , categoriesList.get(position).getFilename());
               // intent.putExtra("image",categoriesList.get(position).getImage());
                context.startActivity(intent);


            }
        }
        private void showRadioButtonDialog(String url) {


        }


    }


//    public static void removeSimpleProgressDialog() {
//        try {
//            if (mProgressDialog != null) {
//                if (mProgressDialog.isShowing()) {
//                    mProgressDialog.dismiss();
//                    mProgressDialog = null;
//                }
//            }
//        } catch (IllegalArgumentException ie) {
//            ie.printStackTrace();
//
//        } catch (RuntimeException re) {
//            re.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static void showSimpleProgressDialog(Context context, String title,
//                                                String msg, boolean isCancelable) {
//        try {
//            if (mProgressDialog == null) {
//                mProgressDialog = ProgressDialog.show(context, title, msg);
//                mProgressDialog.setCancelable(isCancelable);
//            }
//
//            if (!mProgressDialog.isShowing()) {
//                mProgressDialog.show();
//            }
//
//        } catch (IllegalArgumentException ie) {
//            ie.printStackTrace();
//        } catch (RuntimeException re) {
//            re.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}