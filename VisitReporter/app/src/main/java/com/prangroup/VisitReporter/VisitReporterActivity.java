package com.prangroup.VisitReporter;
import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.prangroup.VisitReporter.Adapter.VisitAdapter;
import com.prangroup.VisitReporter.BusinessObjects.Entry;
import com.prangroup.VisitReporter.BusinessObjects.User;
import com.prangroup.VisitReporter.BusinessObjects.Visit;
import com.prangroup.VisitReporter.BusinessObjects.VisitList;
import com.prangroup.VisitReporter.DataStore.SyncUtils;
import com.prangroup.VisitReporter.GPSTracker.GPSTracker;
import com.prangroup.VisitReporter.Model.VisitModel;
import com.prangroup.VisitReporter.Utility.Calender;

public class VisitReporterActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String visitNumber = "";
    EditText etPurpose, etArea, etDays, etComment;
    Button btnMarket, btnDealer, btnOutlet, btnConfirm;
    Switch swEndVisit;
    VisitList visitList = new VisitList();
    VisitAdapter adapter;
    User user;
    Entry entry = new Entry();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_reporter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        resetVisitList();
        user = new User(getApplicationContext());
        adapter = new VisitAdapter(getApplicationContext(), visitList.getVisitArray());
        ListView lvVisit = (ListView) findViewById(R.id.lvVisit);
        lvVisit.setAdapter(adapter);
        lvVisit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Visit visit = (Visit) view.findViewById(R.id.txtVisitNumber).getTag();
                if (!visit.isEnd()) {
                    entry.setVisit(visit);
                    LayoutInflater layoutInflater = LayoutInflater.from(VisitReporterActivity.this);
                    View promptView = layoutInflater.inflate(R.layout.visit_report, null);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(VisitReporterActivity.this);
                    alertDialogBuilder.setView(promptView);
                    final AlertDialog alert = alertDialogBuilder.create();
                    alert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                    alert.show();
                    etComment = (EditText) promptView.findViewById(R.id.etComment);
                    btnMarket = (Button) promptView.findViewById(R.id.btnMarket);
                    btnDealer = (Button) promptView.findViewById(R.id.btnDealer);
                    btnOutlet = (Button) promptView.findViewById(R.id.btnOutlet);
                    btnConfirm = (Button) promptView.findViewById(R.id.btnConfirm);
                    swEndVisit = (Switch) promptView.findViewById(R.id.swEndVisit);
                    etComment.setVisibility(View.GONE);
                    btnConfirm.setVisibility(View.GONE);
                    swEndVisit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if (b) {
                                etComment.setVisibility(View.VISIBLE);
                                btnConfirm.setVisibility(View.VISIBLE);
                            } else {
                                etComment.setVisibility(View.GONE);
                                btnConfirm.setVisibility(View.GONE);
                            }
                        }
                    });
                    btnConfirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            visit.setEndDate(Calender.currentDate());
                            visit.setEndDateTime(Calender.currentTimeStamp());
                            visit.setComment(etComment.getText().toString());
                            VisitModel visitModel = new VisitModel(getContentResolver());
                            visitModel.updateVisit(visit);
                            resetVisitList();
                            adapter.notifyDataSetChanged();
                            SyncUtils.TriggerRefresh();
                            alert.dismiss();
                        }
                    });
                    btnMarket.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            entry.setVisitType(1);
                            Intent intent = new Intent(getApplicationContext(), VisitEntryActivity.class);
                            intent.putExtra("entry", entry);
                            startActivity(intent);
                        }
                    });
                    btnDealer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            entry.setVisitType(2);
                            Intent intent = new Intent(getApplicationContext(), VisitEntryActivity.class);
                            intent.putExtra("entry", entry);
                            startActivity(intent);
                        }
                    });
                    btnOutlet.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            entry.setVisitType(3);
                            Intent intent = new Intent(getApplicationContext(), VisitEntryActivity.class);
                            intent.putExtra("entry", entry);
                            startActivity(intent);
                        }
                    });
                } else {
                    Toast.makeText(VisitReporterActivity.this, "Visit Completed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                visitNumber = Calender.generateVisitNumber();
                LayoutInflater layoutInflater = LayoutInflater.from(VisitReporterActivity.this);
                View promptView = layoutInflater.inflate(R.layout.create_visit, null);
                TextView txtVisitNumber = (TextView) promptView.findViewById(R.id.txtVisitNumber);
                etPurpose = (EditText) promptView.findViewById(R.id.etPurpose);
                etArea = (EditText) promptView.findViewById(R.id.etArea);
                etDays = (EditText) promptView.findViewById(R.id.etDays);
                txtVisitNumber.setText(visitNumber);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(VisitReporterActivity.this);
                alertDialogBuilder.setView(promptView);
                alertDialogBuilder.setCancelable(false).setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Visit visit = new Visit();
                        visit.setVisitNumber(visitNumber);
                        visit.setPurpose(etPurpose.getText().toString());
                        visit.setArea(etArea.getText().toString());
                        if (!TextUtils.isEmpty(etDays.getText())) {
                            visit.setVisitDays(Integer.parseInt(etDays.getText().toString()));
                        } else {
                            visit.setVisitDays(0);
                        }
                        visit.setCreateDateTime(Calender.currentTimeStamp());
                        visit.setCreateDate(Calender.currentDate());
                        VisitModel visitModel = new VisitModel(getContentResolver());
                        visitModel.insertVisit(visit);
                        resetVisitList();
                        SyncUtils.TriggerRefresh();
                        adapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = alertDialogBuilder.create();
                alert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                alert.show();
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void resetVisitList() {
        VisitModel visitModel = new VisitModel(getContentResolver());
        visitList.setVisitArray(visitModel.getVisits().getVisitArray());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.vr, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {
            user.clearUser();
            AccountManager am = AccountManager.get(getApplicationContext());
            try {
                if (ActivityCompat.checkSelfPermission(VisitReporterActivity.this, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
                Account[] accounts = AccountManager.get(getApplicationContext()).getAccountsByType(getString(R.string.Account));
                for (Account account : accounts) {
                    am.removeAccount(account, null, null);
                }
            } catch (Exception e) {
            }
            Intent service = new Intent(getApplicationContext(), GPSTracker.class);
            stopService(service);
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.navSync) {
            SyncUtils.TriggerRefresh();
        } else if (id == R.id.navLogout) {
            user.clearUser();
            AccountManager am = AccountManager.get(getApplicationContext());
            try {
                if (ActivityCompat.checkSelfPermission(VisitReporterActivity.this, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
                Account[] accounts = AccountManager.get(getApplicationContext()).getAccountsByType(getString(R.string.Account));
                for (Account account : accounts) {
                    am.removeAccount(account, null, null);
                }
            } catch (Exception e) {
            }
            Intent service = new Intent(getApplicationContext(), GPSTracker.class);
            stopService(service);

            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

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
