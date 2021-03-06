package com.astr.hc.celebrations.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.astr.hc.celebrations.Activity.Editprofile;
import com.astr.hc.celebrations.Activity.MainActivity;
import com.astr.hc.celebrations.Activity.Planupgrade;
import com.astr.hc.celebrations.Api.Api;
import com.astr.hc.celebrations.Config.DBHelper;
import com.astr.hc.celebrations.R;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;


public class Profile extends Fragment {
View view;
    DBHelper dbHelper;
    String id,na,pa;

    TextView personalinfobtn, Bussinessbtn, Officialbtn;
    LinearLayout personalinfo, busslin, office;
    TextView stuid;
    TextView stuname;
    TextView mobileno;
    TextView email;
    TextView weddingdate;
    TextView bloodgroup;
    TextView address;
    TextView membershipid;
    TextView bussness;
    TextView bussesskey,birthdate;
    CircleImageView image;
    TableLayout stk;
    String stuidstr, stunamestr, mobilenostr, emailstr, weddingdatestr, bloodgroupstr, addressstr, membershipidstr, bussnessstr, bussesskeystr, imagestr,dobstr;
    ArrayList<HashMap<String, String>> studentlist;
    ImageView backarrow;
    String subject="";
    String bodyText="";
    ImageView edit;
Button upgrade,logoutt;
    String vendor_id;
    String vendor_fname;
            String vendor_lname ;
    String vendor_dob ;
    String vendor_proof ;
            String vendor_category;
    String vendor_cname ;
            String vendor_mobile ;
    String vendor_whatsapp;
    String vendor_email ;
            String vendor_address;
    String vendor_service;
    String vendor_location;
    String vendor_amount;
    String vendor_status ;
            String vendor_plan ;
    SharedPreferences mPreferences;
    SharedPreferences.Editor mEditor;
    SharedPreferences sh;

    int defalt;
    public Profile() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_profile, container, false);
        dbHelper=new DBHelper(getActivity());
        Cursor res = dbHelper.getAllData();

        while (res.moveToNext()) {
            id = res.getString(0);
            na = res.getString(1);
            pa = res.getString(2);
        }
        mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEditor = mPreferences.edit();

//        sh = getActivity().getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        defalt=sh.getInt("Name", 0);
        Log.e("defalt",""+defalt);

        personalinfobtn = view.findViewById(R.id.personalinfobtn);
        Bussinessbtn = view.findViewById(R.id.Bussinessbtn);
        edit = view.findViewById(R.id.edit);
        upgrade = view.findViewById(R.id.upgrade);
        logoutt = view.findViewById(R.id.logoutt);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), Editprofile.class);
                i.putExtra("vendorid", ""+vendor_id);
                i.putExtra("vendor_fname", ""+vendor_fname);
                i.putExtra("vendor_lname", ""+vendor_lname);
                i.putExtra("vendor_dob", ""+vendor_dob);
                i.putExtra("vendor_mobile", ""+vendor_mobile);
                i.putExtra("vendor_email", ""+vendor_email);
                i.putExtra("vendor_address", ""+vendor_address);
                i.putExtra("vendor_whatsapp", ""+vendor_whatsapp);
                i.putExtra("vendor_category", ""+vendor_category);
                i.putExtra("vendor_location", ""+vendor_location);
                i.putExtra("vendor_amount", ""+vendor_amount);
                startActivity(i);
            }
        });
        upgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (pa.equals("4")){
                    Snackbar.make(view, "You are Already In Premium Plan ", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{
                    Intent i=new Intent(getActivity(), Planupgrade.class);
                    i.putExtra("vendorid", ""+vendor_id);
                    startActivity(i);
                }

            }
        });

        logoutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Logged Out Successfully", Toast.LENGTH_LONG).show();
                SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor spreferencesEditor = spreferences.edit();
                spreferencesEditor.clear();
                spreferencesEditor.commit();
                dbHelper.deleteRow();

                // Redirect to Main Login activity after log out.
                Intent intent = new Intent(getActivity(), MainActivity.class);

                startActivity(intent);
            }
        });
     /*   personalinfobtn = findViewById(R.id.personalinfobtn);
        experiencebtn = findViewById(R.id.experiencebtn);*/
        personalinfo = view.findViewById(R.id.personalinfo);
        busslin = view.findViewById(R.id.busslin);

        /*making personal info visible*/
        personalinfo.setVisibility(View.VISIBLE);
        busslin.setVisibility(View.GONE);
//        office.setVisibility(View.GONE);

//        backarrow=view.findViewById(R.id.backarrow);
        stuid = view.findViewById(R.id.stuid);
        stuname = view.findViewById(R.id.stuname);
        mobileno = view.findViewById(R.id.mobileno);
        email = view.findViewById(R.id.email);
        weddingdate = view.findViewById(R.id.weddingdate);
        bloodgroup = view.findViewById(R.id.bloodgroup);
        address = view.findViewById(R.id.address);
        membershipid = view.findViewById(R.id.membershipid);
        bussness = view.findViewById(R.id.bussiness);
        stuid = view.findViewById(R.id.stuid);
        image = view.findViewById(R.id.image);
        birthdate = view.findViewById(R.id.birthdate);
        while (res.moveToNext()) {
            id = res.getString(0);
            na = res.getString(1);
            pa = res.getString(2);
        }
        getLogin();
        personalinfobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                personalinfo.setVisibility(View.VISIBLE);
                busslin.setVisibility(View.GONE);
//                office.setVisibility(View.GONE);
                personalinfobtn.setTextColor(getResources().getColor(R.color.blue));
                Bussinessbtn.setTextColor(getResources().getColor(R.color.txt_medium_gray));
//                Officialbtn.setTextColor(getResources().getColor(R.color.grey));

            }
        });

        Bussinessbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                personalinfo.setVisibility(View.GONE);
                busslin.setVisibility(View.VISIBLE);
//                office.setVisibility(View.GONE);
                personalinfobtn.setTextColor(getResources().getColor(R.color.txt_medium_gray));
                Bussinessbtn.setTextColor(getResources().getColor(R.color.blue));
//                Officialbtn.setTextColor(getResources().getColor(R.color.grey));

            }
        });


//        backarrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i=new Intent(getActivity(),Bottommenu.class);
//                startActivity(i);
//            }
//        });

//        setSupportActionBar(toolbar);
        return view;
    }

    private void getLogin() {

        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(),
                "Please wait",
                "Loading...");

        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        String url = Api.profileurl;
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //let's parse json data
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject obj = jsonObject.getJSONObject("reg");
                    String msg = obj.getString("msg");

                    if (msg.equals("Success")) {

                         vendor_id = obj.getString("vendor_id");
                         vendor_fname = obj.getString("vendor_fname");
                         vendor_lname = obj.getString("vendor_lname");
                         vendor_dob = obj.getString("vendor_dob");
                         vendor_proof = obj.getString("vendor_image");
                         vendor_category = obj.getString("vendor_category");
                         vendor_cname = obj.getString("vendor_cname");
                         vendor_mobile = obj.getString("vendor_mobile");
                         vendor_whatsapp = obj.getString("vendor_whatsapp");
                         vendor_email = obj.getString("vendor_email");
                         vendor_address = obj.getString("vendor_address");
                         vendor_service = obj.getString("vendor_service");
                         vendor_location = obj.getString("vendor_location");
                         vendor_status = obj.getString("vendor_status");
//                        vendor_amount = obj.getString("vendor_status");
                         vendor_plan = obj.getString("vendor_plan");
                         vendor_plan = obj.getString("vendor_plan");

                        Picasso.with(getActivity()).load(vendor_proof).resize(300, 300).into(image);
                        stuid.setText(""+vendor_id);
                        stuname.setText(""+vendor_fname+""+vendor_lname);
                        birthdate.setText(""+vendor_dob);
                        mobileno.setText(""+vendor_mobile);
                        email.setText(""+vendor_email);
                        weddingdate.setText(""+vendor_category);
                        bloodgroup.setText(""+vendor_whatsapp);
                        address.setText(""+vendor_address);
                        membershipid.setText(""+vendor_plan);
                        bussness.setText(""+vendor_cname);
                            defalt= Integer.parseInt(obj.getString("vendor_basic"));
//                        birthdate.setText(""+dobstr);
                        if (defalt==1){
                            upgrade.setVisibility(View.GONE);
                        }

                    } else {
                        Toast.makeText(getActivity(), ""+msg, Toast.LENGTH_SHORT).show();
                    }

                    progressDialog.dismiss();
                }
                catch (Exception e){
                    e.printStackTrace();
                    progressDialog.dismiss();
//                    Toast.makeText(ProfileActivity.this, "POST DATA : unable to Parse Json", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Post Data : Response Failed", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params=new HashMap<String, String>();
                params.put("vid", ""+na);
                return params;
            }

            @Override
            public Map<String,String> getHeaders() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
//                params.put("Content-Type","application/json;charset=utf-8");
                params.put("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
//                params.put("Authorization","Bearer "+sToken);
//                params.put("X-Requested-With", "XMLHttpRequest");
                return params;
            }
        };

        requestQueue.add(stringRequest);

    }
}