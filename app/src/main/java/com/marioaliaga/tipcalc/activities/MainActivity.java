package com.marioaliaga.tipcalc.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.marioaliaga.tipcalc.R;
import com.marioaliaga.tipcalc.TipCalcApp;
import com.marioaliaga.tipcalc.fragments.TipHistoryListFragment;
import com.marioaliaga.tipcalc.fragments.TipHistoryFragmentsListener;
import com.marioaliaga.tipcalc.models.TipRecord;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.inputBill)
    EditText inputBill;
    @Bind(R.id.inputPorcentage)
    EditText inputPorcentage;
    @Bind(R.id.txtTip)
    TextView txtTip;

    private TipHistoryFragmentsListener fragmentsListener;

    private final static int TIP_STEP_CHANGE = 1;
    private final static int DEFAULT_TIP_PORCENTAGE = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        TipHistoryListFragment fragment = (TipHistoryListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);
        fragment.setRetainInstance(true);
        fragmentsListener = fragment;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            about();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnSubmit)
    public void handleCleckSubmit(){
        Log.e(getLocalClassName(),"Click en Submit");
        hideKeyBoard();
        String strInputTotal = inputBill.getText().toString().trim();
        if (!strInputTotal.isEmpty()){
            double total = Double.parseDouble(strInputTotal);
            int tipPorcentage = getTipPorcentage();

            TipRecord tipRecord = new TipRecord();
            tipRecord.setBill(total);
            tipRecord.setTipPorcentage(tipPorcentage);
            tipRecord.setTimestamp(new Date());

            String strTip = String.format(getString(R.string.global_message_tip),
                                          tipRecord.getTip());
            fragmentsListener.addToList(tipRecord);
            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(strTip);
        }
    }

    private int getTipPorcentage() {
        int tipPorcentage = DEFAULT_TIP_PORCENTAGE;
        String strInputTipPorcentage = inputPorcentage.getText().toString().trim();
        if (!strInputTipPorcentage.isEmpty()){
            tipPorcentage = Integer.parseInt(strInputTipPorcentage);
        }else {
            inputPorcentage.setText(String.valueOf(tipPorcentage));
        }
        return tipPorcentage;
    }

    @OnClick(R.id.btnIncrease)
    public void handleClickIncrease(){
        hideKeyBoard();
        handleTipChange(TIP_STEP_CHANGE);
    }

    @OnClick(R.id.btnDecrease)
    public void handleClickDecrease(){
        hideKeyBoard();
        handleTipChange(-TIP_STEP_CHANGE);
    }

    @OnClick(R.id.btnClear)
    public void handleClickClear(){
       fragmentsListener.clearList();
    }

    private void handleTipChange(int change) {
        int tipPorcentage = getTipPorcentage();
        tipPorcentage += change;
        if (tipPorcentage > 0)
            inputPorcentage.setText(String.valueOf(tipPorcentage));
    }

    private void hideKeyBoard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        try{
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }catch (NullPointerException e){
            Log.e(getLocalClassName(), Log.getStackTraceString(e));
        }

    }

    private void about() {
        TipCalcApp tipCalcApp = (TipCalcApp) getApplication();
        String strUrl = tipCalcApp.getAboutUrl();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(strUrl));
        startActivity(intent);

    }
}
