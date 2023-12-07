// Generated by view binder compiler. Do not edit!
package com.example.project.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.project.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySignupBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final RadioGroup RadioGroup;

  @NonNull
  public final Button btnCreateAccount;

  @NonNull
  public final RadioButton employeeRadioButton;

  @NonNull
  public final EditText inputEmail;

  @NonNull
  public final EditText inputFirstName;

  @NonNull
  public final EditText inputLastName;

  @NonNull
  public final EditText inputPassword;

  @NonNull
  public final EditText inputPasswordConfirm;

  @NonNull
  public final TextView sign;

  @NonNull
  public final TextView switchToSignin;

  @NonNull
  public final RadioButton userRadioButton;

  private ActivitySignupBinding(@NonNull RelativeLayout rootView, @NonNull RadioGroup RadioGroup,
      @NonNull Button btnCreateAccount, @NonNull RadioButton employeeRadioButton,
      @NonNull EditText inputEmail, @NonNull EditText inputFirstName,
      @NonNull EditText inputLastName, @NonNull EditText inputPassword,
      @NonNull EditText inputPasswordConfirm, @NonNull TextView sign,
      @NonNull TextView switchToSignin, @NonNull RadioButton userRadioButton) {
    this.rootView = rootView;
    this.RadioGroup = RadioGroup;
    this.btnCreateAccount = btnCreateAccount;
    this.employeeRadioButton = employeeRadioButton;
    this.inputEmail = inputEmail;
    this.inputFirstName = inputFirstName;
    this.inputLastName = inputLastName;
    this.inputPassword = inputPassword;
    this.inputPasswordConfirm = inputPasswordConfirm;
    this.sign = sign;
    this.switchToSignin = switchToSignin;
    this.userRadioButton = userRadioButton;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySignupBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySignupBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_signup, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySignupBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.RadioGroup;
      RadioGroup RadioGroup = ViewBindings.findChildViewById(rootView, id);
      if (RadioGroup == null) {
        break missingId;
      }

      id = R.id.btnCreateAccount;
      Button btnCreateAccount = ViewBindings.findChildViewById(rootView, id);
      if (btnCreateAccount == null) {
        break missingId;
      }

      id = R.id.employeeRadioButton;
      RadioButton employeeRadioButton = ViewBindings.findChildViewById(rootView, id);
      if (employeeRadioButton == null) {
        break missingId;
      }

      id = R.id.inputEmail;
      EditText inputEmail = ViewBindings.findChildViewById(rootView, id);
      if (inputEmail == null) {
        break missingId;
      }

      id = R.id.inputFirstName;
      EditText inputFirstName = ViewBindings.findChildViewById(rootView, id);
      if (inputFirstName == null) {
        break missingId;
      }

      id = R.id.inputLastName;
      EditText inputLastName = ViewBindings.findChildViewById(rootView, id);
      if (inputLastName == null) {
        break missingId;
      }

      id = R.id.inputPassword;
      EditText inputPassword = ViewBindings.findChildViewById(rootView, id);
      if (inputPassword == null) {
        break missingId;
      }

      id = R.id.inputPasswordConfirm;
      EditText inputPasswordConfirm = ViewBindings.findChildViewById(rootView, id);
      if (inputPasswordConfirm == null) {
        break missingId;
      }

      id = R.id.sign;
      TextView sign = ViewBindings.findChildViewById(rootView, id);
      if (sign == null) {
        break missingId;
      }

      id = R.id.switchToSignin;
      TextView switchToSignin = ViewBindings.findChildViewById(rootView, id);
      if (switchToSignin == null) {
        break missingId;
      }

      id = R.id.userRadioButton;
      RadioButton userRadioButton = ViewBindings.findChildViewById(rootView, id);
      if (userRadioButton == null) {
        break missingId;
      }

      return new ActivitySignupBinding((RelativeLayout) rootView, RadioGroup, btnCreateAccount,
          employeeRadioButton, inputEmail, inputFirstName, inputLastName, inputPassword,
          inputPasswordConfirm, sign, switchToSignin, userRadioButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
