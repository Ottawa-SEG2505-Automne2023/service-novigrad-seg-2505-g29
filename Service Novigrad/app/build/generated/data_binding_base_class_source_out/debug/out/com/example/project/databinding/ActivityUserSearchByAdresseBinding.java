// Generated by view binder compiler. Do not edit!
package com.example.project.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.project.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityUserSearchByAdresseBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnBack;

  @NonNull
  public final Button btnSearch;

  @NonNull
  public final TextView txtSearch;

  @NonNull
  public final EditText txtSearchField;

  private ActivityUserSearchByAdresseBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button btnBack, @NonNull Button btnSearch, @NonNull TextView txtSearch,
      @NonNull EditText txtSearchField) {
    this.rootView = rootView;
    this.btnBack = btnBack;
    this.btnSearch = btnSearch;
    this.txtSearch = txtSearch;
    this.txtSearchField = txtSearchField;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityUserSearchByAdresseBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityUserSearchByAdresseBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_user_search_by_adresse, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityUserSearchByAdresseBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnBack;
      Button btnBack = ViewBindings.findChildViewById(rootView, id);
      if (btnBack == null) {
        break missingId;
      }

      id = R.id.btnSearch;
      Button btnSearch = ViewBindings.findChildViewById(rootView, id);
      if (btnSearch == null) {
        break missingId;
      }

      id = R.id.txtSearch;
      TextView txtSearch = ViewBindings.findChildViewById(rootView, id);
      if (txtSearch == null) {
        break missingId;
      }

      id = R.id.txtSearchField;
      EditText txtSearchField = ViewBindings.findChildViewById(rootView, id);
      if (txtSearchField == null) {
        break missingId;
      }

      return new ActivityUserSearchByAdresseBinding((ConstraintLayout) rootView, btnBack, btnSearch,
          txtSearch, txtSearchField);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
