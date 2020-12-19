package com.sama.communicationclass.CustomDialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.sama.communicationclass.Lisetner.OnEditDialogListener;
import com.sama.communicationclass.R;

public class EditDialog extends AlertDialog.Builder implements DialogInterface.OnClickListener {

    EditText editText;
    View viewDialog;


    OnEditDialogListener onEditDialogListener;
    public EditDialog(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        this.viewDialog = inflater.inflate(R.layout.deialog_edit_layout, null, false);
        setPositiveButton("확인",this);
        setNegativeButton("취소", this);
        this.setCancelable(false);
        this.setView(viewDialog);
        this.editText = this.viewDialog.findViewById(R.id.edit_token_field);
    }

    public void setEditDialogListener(OnEditDialogListener onEditDialogListener){
        this.onEditDialogListener = onEditDialogListener;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        ViewGroup dialogParentView = (ViewGroup) this.viewDialog.getParent();
        if(this.onEditDialogListener != null && which == -1 ){
            onEditDialogListener.OnDialogConfirm(this.editText.getText().toString());
            this.editText.setText("");
        }
        dialogParentView.removeView(this.viewDialog);
        dialog.dismiss();

    }
}
