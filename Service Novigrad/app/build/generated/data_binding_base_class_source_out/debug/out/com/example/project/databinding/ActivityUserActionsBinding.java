// Generated by view binder compiler. Do not edit!
package com.example.project.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.project.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityUserActionsBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button buttonSubmitRequest;

  @NonNull
  public final EditText editTextServiceName;

  @NonNull
  public final LinearLayout linearLayoutDocuments;

  @NonNull
  public final LinearLayout linearLayoutFormFields;

  private ActivityUserActionsBinding(@NonNull LinearLayout rootView,
      @NonNull Button buttonSubmitRequest, @NonNull EditText editTextServiceName,
      @NonNull LinearLayout linearLayoutDocuments, @NonNull LinearLayout linearLayoutFormFields) {
    this.rootView = rootView;
    this.buttonSubmitRequest = buttonSubmitRequest;
    this.editTextServiceName = editTextServiceName;
    this.linearLayoutDocuments = linearLayoutDocuments;
    this.linearLayoutFormFields = linearLayoutFormFields;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityUserActionsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityUserActionsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_user_actions, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityUserActionsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonSubmitRequest;
      Button buttonSubmitRequest = ViewBindings.findChildViewById(rootView, id);
      if (buttonSubmitRequest == null) {
        break missingId;
      }

      id = R.id.editTextServiceName;
      EditText editTextServiceName = ViewBindings.findChildViewById(rootView, id);
      if (editTextServiceName == null) {
        break missingId;
      }

      id = R.id.linearLayoutDocuments;
      LinearLayout linearLayoutDocuments = ViewBindings.findChildViewById(rootView, id);
      if (linearLayoutDocuments == null) {
        break missingId;
      }

      id = R.id.linearLayoutFormFields;
      LinearLayout linearLayoutFormFields = ViewBindings.findChildViewById(rootView, id);
      if (linearLayoutFormFields == null) {
        break missingId;
      }

      return new ActivityUserActionsBinding((LinearLayout) rootView, buttonSubmitRequest,
          editTextServiceName, linearLayoutDocuments, linearLayoutFormFields);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
