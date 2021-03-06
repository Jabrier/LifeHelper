package com.ns.yc.lifehelper.ui.me.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ns.yc.lifehelper.R;
import com.ycbjie.library.base.mvp.BaseActivity;
import com.ns.yc.yccustomtextlib.pwdEt.PasswordEditText;
import com.pedaily.yc.ycdialoglib.toast.ToastUtils;


/**
 * ================================================
 * 作    者：杨充
 * 版    本：1.0
 * 创建日期：2017/9/27
 * 描    述：注册页面
 * 修订历史：
 * 关于密码自定义控件，直接将EditText改成PasswordEditText即可
 * demo地址，欢迎star：https://github.com/yangchong211/YCCustomText
 * ================================================
 */
public class MeRegisterActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private TextView tvTitleLeft;
    private FrameLayout llTitleMenu;
    private TextView toolbarTitle;
    private FrameLayout llSearch;
    private ImageView ivRightImg;
    private TextView tvTitleRight;
    AutoCompleteTextView tvPersonUsername;
    AutoCompleteTextView tvPersonCode;
    PasswordEditText tvPersonPassword;
    PasswordEditText tvPersonPasswordAgain;
    CheckBox cbIsAgree;
    TextView tvAbout;
    Button btnPersonRegister;

    @Override
    public int getContentView() {
        return R.layout.activity_me_register;
    }



    @Override
    public void initView() {
        toolbar = findViewById(R.id.toolbar);
        tvTitleLeft = findViewById(R.id.tv_title_left);
        llTitleMenu = findViewById(R.id.ll_title_menu);
        toolbarTitle = findViewById(R.id.toolbar_title);
        llSearch = findViewById(R.id.ll_search);
        ivRightImg = findViewById(R.id.iv_right_img);
        tvTitleRight = findViewById(R.id.tv_title_right);
        tvPersonUsername = (AutoCompleteTextView) findViewById(R.id.tv_person_username);
        tvPersonCode = (AutoCompleteTextView) findViewById(R.id.tv_person_code);
        tvPersonPassword = (PasswordEditText) findViewById(R.id.tv_person_password);
        tvPersonPasswordAgain = (PasswordEditText) findViewById(R.id.tv_person_password_again);
        cbIsAgree = (CheckBox) findViewById(R.id.cb_is_agree);
        tvAbout = (TextView) findViewById(R.id.tv_about);
        btnPersonRegister = (Button) findViewById(R.id.btn_person_register);


        initToolBar();
    }

    private void initToolBar() {
        toolbarTitle.setText("注册账号");
    }

    @Override
    public void initListener() {
        llTitleMenu.setOnClickListener(this);
        btnPersonRegister.setOnClickListener(this);
    }


    @Override
    public void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_title_menu:
                finish();
                break;
            case R.id.btn_person_register:
                goToRegister();
                break;
            default:
                break;
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.dispatchTouchEvent(ev);
    }


    // 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }


    private void goToRegister() {
        final String name = tvPersonUsername.getText().toString().trim();
        String code = tvPersonCode.getText().toString().trim();
        final String pwd = tvPersonPassword.getText().toString().trim();
        String pwdAgain = tvPersonPasswordAgain.getText().toString().trim();
        if(TextUtils.isEmpty(name)){
            ToastUtils.showRoundRectToast("用户名不能为空");
            return;
        }
        if(TextUtils.isEmpty(code)){
            ToastUtils.showRoundRectToast("验证码不能为空");
            return;
        }
        if(TextUtils.isEmpty(pwd)){
            ToastUtils.showRoundRectToast("用户名不能为空");
            return;
        }
        if(!pwd.equals(pwdAgain)){
            ToastUtils.showRoundRectToast("两次输入密码不同");
            return;
        }

        final ProgressDialog pd = new ProgressDialog(MeRegisterActivity.this);
        pd.setMessage(getResources().getString(R.string.register_state));
        if(!pd.isShowing()){
            pd.show();
        }
    }


}
