package com.prangroup.VisitReporter;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.prangroup.VisitReporter.Adapter.DiscussionAdapter;
import com.prangroup.VisitReporter.Adapter.DistrictAdapter;
import com.prangroup.VisitReporter.Adapter.ThanaAdapter;
import com.prangroup.VisitReporter.Adapter.ZoneAdapter;
import com.prangroup.VisitReporter.BusinessObjects.Discussion;
import com.prangroup.VisitReporter.BusinessObjects.District;
import com.prangroup.VisitReporter.BusinessObjects.DistrictList;
import com.prangroup.VisitReporter.BusinessObjects.Entry;
import com.prangroup.VisitReporter.BusinessObjects.Thana;
import com.prangroup.VisitReporter.BusinessObjects.ThanaList;
import com.prangroup.VisitReporter.BusinessObjects.User;
import com.prangroup.VisitReporter.BusinessObjects.Zone;
import com.prangroup.VisitReporter.BusinessObjects.ZoneList;
import com.prangroup.VisitReporter.Camera.CameraActivity;
import com.prangroup.VisitReporter.DataStore.SyncUtils;
import com.prangroup.VisitReporter.GPSTracker.GPSTracker;
import com.prangroup.VisitReporter.Model.EntryModel;
import com.prangroup.VisitReporter.Model.GeoModel;
import com.prangroup.VisitReporter.Model.ZoneModel;


public class VisitEntryActivity extends AppCompatActivity {
    GPSTracker mService;
    Entry entry;
    ThanaList thanaList, temp;
    ZoneList zoneList;
    ThanaAdapter thanaAdapter;
    Spinner spDistrict, spThana, spZone;
    TextView txtName, txtStaffId, txtMobile, txtDepartment, txtGroup, txtCompany;
    TextView txtProprietorName, txtProprietorCode, txtProprietorAddress, txtDiscussionWith,txtProprietorOwnerName;
    EditText etBazar, etProprietorName, etProprietorCode, etProprietorAdderss, etProprietorOwnerName, etProprietorContact;
    EditText etDiscussionPoint,etAction,etResponsible,etDeadline;
    Button btnTakeImage, btnSave,btnAddPoint,btnImageCapture;
    ListView lvDiscussion;
    ImageView ivImage1, ivImage2;
    DiscussionAdapter discussionAdapter;
    String discussionImage="";
    String discussionPath="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_entry);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            entry = bundle.getParcelable("entry");
        }
        if (mService == null) {
            Intent in = new Intent(this, GPSTracker.class);
            bindService(in, myConnection, this.BIND_AUTO_CREATE);

        }

        final User user = new User(getApplication());
        txtName = (TextView) findViewById(R.id.txtName);
        txtStaffId = (TextView) findViewById(R.id.txtStaffId);
        txtMobile = (TextView) findViewById(R.id.txtMobile);
        txtDepartment = (TextView) findViewById(R.id.txtDepartment);
        txtGroup = (TextView) findViewById(R.id.txtGroup);
        txtCompany = (TextView) findViewById(R.id.txtCompany);

        txtProprietorName = (TextView) findViewById(R.id.txtProprietorName);
        txtProprietorCode = (TextView) findViewById(R.id.txtProprietorCode);
        txtProprietorAddress = (TextView) findViewById(R.id.txtProprietorAddress);
        txtDiscussionWith = (TextView) findViewById(R.id.txtDiscussionWith);
        txtProprietorOwnerName = (TextView) findViewById(R.id.txtProprietorOwnerName);
        if (entry != null) {
            if (entry.getVisitType() == 1) {
                txtProprietorName.setText("Market Name:");
                txtProprietorCode.setText("Market Code:");
                txtProprietorAddress.setText("Market Address:");
                txtDiscussionWith.setText("Discussion With Market:");
                txtProprietorOwnerName.setText("Proprietor Name:");
            } else if (entry.getVisitType() == 2) {
                txtProprietorName.setText("Dealer Name:");
                txtProprietorCode.setText("Dealer Code:");
                txtProprietorAddress.setText("Dealer Address:");
                txtDiscussionWith.setText("Discussion With Dealer:");
                txtProprietorOwnerName.setText("Proprietor Name:");
            } else if (entry.getVisitType() == 3) {
                txtProprietorName.setText("Outlet Name:");
                txtProprietorCode.setText("Outlet Code:");
                txtProprietorAddress.setText("Outlet Address:");
                txtDiscussionWith.setText("Discussion With Outlet:");
                txtProprietorOwnerName.setText("Proprietor Name:");
            }
        }

        spDistrict = (Spinner) findViewById(R.id.spDistrict);
        spThana = (Spinner) findViewById(R.id.spThana);
        spZone = (Spinner) findViewById(R.id.spZone);

        etBazar = (EditText) findViewById(R.id.etBazar);
        etProprietorName = (EditText) findViewById(R.id.etProprietorName);
        etProprietorCode = (EditText) findViewById(R.id.etProprietorCode);
        etProprietorAdderss = (EditText) findViewById(R.id.etProprietorAddress);
        etProprietorOwnerName = (EditText) findViewById(R.id.etProprietorOwnerName);
        etProprietorContact = (EditText) findViewById(R.id.etProprietorContact);

        lvDiscussion = (ListView) findViewById(R.id.lvDiscussion);

        btnTakeImage = (Button) findViewById(R.id.btnTakeImage);
        ivImage1 = (ImageView) findViewById(R.id.ivImage1);

        btnTakeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
                   startActivityForResult(intent, 12);
            }
        });
        btnSave = (Button) findViewById(R.id.btnSave);

        discussionAdapter=new DiscussionAdapter(getApplicationContext(),entry.getDiscussionList().getDiscussionArray());
        lvDiscussion.setAdapter(discussionAdapter);
        lvDiscussion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            final Discussion discussion= (Discussion) view.findViewById(R.id.txtDiscussionPoint).getTag();
                LayoutInflater layoutInflater = LayoutInflater.from(VisitEntryActivity.this);
                View promptView = layoutInflater.inflate(R.layout.create_discussion, null);
                etDiscussionPoint=(EditText)promptView.findViewById(R.id.etDiscussionPoint);
                etDiscussionPoint.setEnabled(false);
                etAction=(EditText)promptView.findViewById(R.id.etAction);
                etAction.setEnabled(false);
                etResponsible=(EditText)promptView.findViewById(R.id.etResponsible);
                etResponsible.setEnabled(false);
                etDeadline=(EditText)promptView.findViewById(R.id.etDeadline);
                etDeadline.setEnabled(false);
                btnImageCapture= (Button) promptView.findViewById(R.id.btnImageCapture);
                btnImageCapture.setVisibility(View.GONE);
                ivImage2 = (ImageView)  promptView.findViewById(R.id.ivImage2);
                etDiscussionPoint.setText(discussion.getDiscussionPoint());
                etAction.setText(discussion.getAction());
                etResponsible.setText(discussion.getResponsible());
                etDeadline.setText(discussion.getDeadline());
                Bitmap bm = BitmapFactory.decodeFile(discussion.getPicturePath());
                ivImage2.setImageBitmap(bm);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(VisitEntryActivity.this);
                alertDialogBuilder.setView(promptView);
                alertDialogBuilder.setCancelable(false).setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        entry.getDiscussionList().remove(discussion);
                        discussionAdapter.notifyDataSetChanged();
                        dialog.cancel();
                    }
                });
                AlertDialog alert = alertDialogBuilder.create();
                alert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                alert.show();


            }
        });
        btnAddPoint = (Button) findViewById(R.id.btnAddPoint);
        btnAddPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(VisitEntryActivity.this);
                View promptView = layoutInflater.inflate(R.layout.create_discussion, null);
                etDiscussionPoint=(EditText)promptView.findViewById(R.id.etDiscussionPoint);
                etAction=(EditText)promptView.findViewById(R.id.etAction);
                etResponsible=(EditText)promptView.findViewById(R.id.etResponsible);
                etDeadline=(EditText)promptView.findViewById(R.id.etDeadline);
                btnImageCapture= (Button) promptView.findViewById(R.id.btnImageCapture);
                ivImage2 = (ImageView)  promptView.findViewById(R.id.ivImage2);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(VisitEntryActivity.this);
                alertDialogBuilder.setView(promptView);
                alertDialogBuilder.setCancelable(false).setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Discussion discussion=new Discussion();
                        discussion.setDiscussionPoint(etDiscussionPoint.getText().toString());
                        discussion.setPicture(discussionImage);
                        discussion.setPicturePath(discussionPath);
                        discussion.setAction(etAction.getText().toString());
                        discussion.setResponsible(etResponsible.getText().toString());
                        discussion.setDeadline(etDeadline.getText().toString());
                        entry.getDiscussionList().add(discussion);
                        discussionAdapter.notifyDataSetChanged();
                        requestHeight(lvDiscussion);
                        discussionImage="";
                        discussionPath="";
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = alertDialogBuilder.create();
                alert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                alert.show();
                btnImageCapture.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
                        startActivityForResult(intent, 14);
                    }
                });
            }
        });

        DistrictList districtList = new DistrictList();
        thanaList = new ThanaList();
        temp = new ThanaList();
        zoneList = new ZoneList();
        GeoModel geoModel = new GeoModel(getContentResolver());
        ZoneModel zoneModel = new ZoneModel(getContentResolver());
        zoneList.setZoneArray(zoneModel.getZone().getZoneArray());
        thanaList.setThanaArray(geoModel.getThana().getThanaArray());
        districtList.setDistrictArray(geoModel.getDisrict().getDistrictArray());
        DistrictAdapter districtAdapter = new DistrictAdapter(getApplicationContext(), districtList.getDistrictArray());
        thanaAdapter = new ThanaAdapter(getApplicationContext(), temp.getThanaArray());
        spThana.setAdapter(thanaAdapter);
        ZoneAdapter zoneAdapter = new ZoneAdapter(getApplicationContext(), zoneList.getZoneArray());
        spZone.setAdapter(zoneAdapter);
        spDistrict.setAdapter(districtAdapter);
        spDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              District  district = (District) view.findViewById(R.id.textView1).getTag();
                temp.setThanaArray(thanaList.getThanaArrayDistrict(district.getDistrictId()));
                entry.setDistrict(district);
                thanaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spThana.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Thana thana = (Thana) view.findViewById(R.id.textView1).getTag();
                entry.setThana(thana);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spZone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Zone zone = (Zone) view.findViewById(R.id.textView1).getTag();
                entry.setZone(zone);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        txtName.setText(user.getFullName());
        txtStaffId.setText(user.getStaffId() + "");
        txtMobile.setText(user.getMobile() + "");
        txtDepartment.setText(user.getDepartment() + "");
        txtGroup.setText(user.getGroup() + "");
        txtCompany.setText(user.getCompany() + "");
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                entry.setBazarName(etBazar.getText().toString());
                entry.setProprietorName(etProprietorName.getText().toString());
                entry.setProprietorCode(etProprietorCode.getText().toString());
                entry.setProprietorAddress(etProprietorAdderss.getText().toString());
                entry.setProprietorOwnerName(etProprietorOwnerName.getText().toString());
                entry.setProprietorContact(etProprietorContact.getText().toString());
                EntryModel entryModel=new EntryModel(getContentResolver());
                entryModel.insertEntry(entry,user.getUserId());
                entryModel.insertDiscussion(entry);
                SyncUtils.TriggerRefresh();
                finish();
            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 12:
                if (resultCode == RESULT_OK) {
                    String imagePath = data.getStringExtra("imageName");
                  String   imageName = imagePath.substring(imagePath.lastIndexOf("/") + 1, imagePath.length());
                    Log.e("result", imageName);
                    entry.setImage(imageName);
                    Bitmap bm = BitmapFactory.decodeFile(imagePath);
                    ivImage1.setImageBitmap(bm);
                }
                break;
            case 14:
                if (resultCode == RESULT_OK) {
                    String imagePath = data.getStringExtra("imageName");
                      String   imageName = imagePath.substring(imagePath.lastIndexOf("/") + 1, imagePath.length());
                    discussionImage=imageName;
                    discussionPath=imagePath;
                   Bitmap bm = BitmapFactory.decodeFile(imagePath);
                    ivImage2.setImageBitmap(bm);
                }
                break;
        }
    }
    public void requestHeight(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {
            int totalHeight = 0;
            for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(0, 0);
                int list_child_item_height = listItem.getMeasuredHeight()+listView.getDividerHeight();//item height
                totalHeight += list_child_item_height;
            }
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();
        }
    }

    private ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            GPSTracker.GPSBinder binder = (GPSTracker.GPSBinder) service;
            mService = binder.getService();
            entry.setLat(mService.lat+"");
            entry.setLon(mService.lon+"");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

}
