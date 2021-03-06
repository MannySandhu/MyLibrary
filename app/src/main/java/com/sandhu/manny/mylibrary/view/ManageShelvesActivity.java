//package com.sandhu.manny.mylibrary.view;
//
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.database.Cursor;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.ProgressBar;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.sandhu.manny.mylibrary.R;
//import com.sandhu.manny.mylibrary.model.Shelf;
//
//import java.util.ArrayList;
///*
//    add/create shelves to shelf list
// */
//public class ManageShelvesActivity extends AppCompatActivity {
//
//    DatabaseHelper mydb = new DatabaseHelper(this);
//    private ArrayList<Shelf> shelfData = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_shelves);
//
//        addShelfButtonListener();
//        createShelfDataViews();
//    }
//
//
//    private void createShelfDataViews(){
//        final Cursor resultSet = mydb.getLabelData();
//
//        if(resultSet.getCount() == 0){
//            showMessage("No Shelves Exist", "Please create new shelves.");
//        }else{
//
//            LinearLayout scrollableLayout = (LinearLayout)findViewById(R.id.scrollableShelfListLayout);
//            int id = 0;
//            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams
//                    (RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
//            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//
//            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams
//                    (RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
//            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//
//            // load db data
//
//            while(resultSet.moveToNext()){
//
//                final TextView tv = new TextView(this);
//                tv.setText(resultSet.getString(0));
//                ProgressBar pb = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
//                pb.setProgress(40);
//                pb.setPadding(10, 10, 10, 50 );
//
//                final RelativeLayout rl = new RelativeLayout(this);
//                tv.setLayoutParams(layoutParams);
//                pb.setLayoutParams(layoutParams2);
//
//                rl.addView(tv);
//                rl.addView(pb);
//                rl.setId(id++);
//                rl.setClickable(true);
//                rl.setPadding(10, 10, 10, 50);
//
//                // Alert builder
//                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                final Intent loadShelfIntent = new Intent(this, ManageShelfActivity.class);
//
//                rl.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        // inspect book, delete book, recommendations
//                        System.out.println("****INSPECTING**** -->" + rl.getId());
//                        System.out.println(tv.getText().toString());
//
//                        // go to shelf activity
//                        loadShelfIntent.putExtra("Label", tv.getText().toString());
//                        startActivity(loadShelfIntent);
//
////                        // Alert dialogue
////                        builder.setTitle("Delete Shelf?");
////                        builder.setMessage(tv.getText());
////
////                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
////
////                            public void onClick(DialogInterface dialog, int which) {
////                                mydb.deleteLabelData(tv.getText().toString());
////                                mydb.close();
////                                dialog.dismiss();
////                                recreate();
////                            }
////                        });
////
////                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
////
////                            @Override
////                            public void onClick(DialogInterface dialog, int which) {
////                                // Do nothing
////                                dialog.dismiss();
////                            }
////                        });
////                        AlertDialog alert = builder.create();
////                        alert.show();
////                        // End alert dialogue --------------------
//                    }
//                });
//
//                //int currentViewId = rl.getId();
//                //layoutParams3.addRule(RelativeLayout.BELOW, id - 1);
//                scrollableLayout.addView(rl);
//            }
//        }
//
//    }
//
//    private void addShelfButtonListener(){
//        // Alert builder
//        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        final EditText input = new EditText(this);
//        input.setSingleLine(true);
//
//        Button addShelfButton = (Button)findViewById(R.id.newShelfButton);
//        addShelfButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Alert dialogue
//                builder.setTitle("New Shelf");
//                builder.setMessage("Enter shelf name");
//                builder.setView(input);
//
//                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        String shelf_name = input.getText().toString();
//
//                        if(!shelf_name.equalsIgnoreCase("")){
//                            boolean isInserted = mydb.insertLabelData(
//                                    shelf_name, "None");
//
//                            if(isInserted == true){
//                                Toast.makeText(ManageShelvesActivity.this, "Shelf created!", Toast.LENGTH_LONG).show();
//                            }else{
//                                Toast.makeText(ManageShelvesActivity.this, "Shelf already exists", Toast.LENGTH_LONG).show();
//                            }
//                            dialog.dismiss();
//                            recreate();
//                        }
//                    }
//                });
//                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // Do nothing
//                        input.setText("");
//                        dialog.dismiss();
//                    }
//                });
//                if (input.getParent() != null){
//                    ((ViewGroup)input.getParent()).removeView(input);
//                }
//                AlertDialog alert = builder.create();
//                alert.show();
//                // End alert dialogue --------------------
//            }
//        });
//    }
//
//    private void showMessage(String title, String message){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(true);
//        builder.setTitle(title);
//        builder.setMessage(message);
//        builder.show();
//    }
//
////    public void CreateDynamicTables(String Table_Name, String Contact_ID, String Display_Name)
////    {
////
////        mydb = getWritableDatabase();
////        mydb.execSQL("DROP TABLE IF EXISTS " + Table_Name);
////        String query = "CREATE TABLE " + Table_Name + "(" + CID + " TEXT PRIMARY KEY, " + DName + " TEXT);";
////        mydb.execSQL(query);
////        mydb = this.getWritableDatabase();
////        ContentValues cv = new ContentValues();
////        cv.put(CID, Contact_ID);
////        cv.put(DName, Display_Name);
////        mydb.insert(Table_Name, null, cv);
////        mydb.close();
////    }
//
//
//}
