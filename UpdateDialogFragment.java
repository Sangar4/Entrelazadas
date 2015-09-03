package es.aplicaciones.alvaro.entrelazadas;


import android.app.Dialog;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Álvaro on 12/08/2015.
 */
public class UpdateDialogFragment extends DialogFragment{
    Context context;
    Activity activity;
   public UpdateDialogFragment (){

       //this.context = this.activity.getApplicationContext();
    }
    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.download_question)
                .setPositiveButton(R.string.Yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        new UpdateCatalog(getActivity()).execute();
                    }
                })
                .setNegativeButton(R.string.No, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        LoadActivity.GetImages(getActivity().getApplicationContext());

                        //Esto no creo que lo necesite aquí.
                        //Intent intent = new Intent(getActivity(), DownloadImages.class);
                        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        //startActivity(intent);
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }



}