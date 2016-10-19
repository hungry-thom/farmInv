package com.marginprogramming.farminv1_0;

import android.app.*;
import android.os.*;
import android.content.Intent;
import android.view.View; 
import android.widget.Button;
import android.widget.Toast;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.EditText;
import android.widget.TextView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.view.View.OnClickListener;
import android.content.res.Resources;

import java.util.Calendar;
import android.database.SQLException;
import android.database.Cursor;
import android.database.sqlite.*;

public class MainActivity extends Activity implements View.OnClickListener
{
    dbFarmAdapter mDbHelper;
    EditText eDate;
    private int mYear, mMonth, mDay, mHour, mMinute;
    int vID=1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mDbHelper = new dbFarmAdapter(getApplicationContext());
        mDbHelper.createDatabase();

        summaryView();
        inflateEntryRow();

        //final Button buttonAdd = (Button) findViewById( R.id.buttonAdd);
        //buttonAdd = (Button) findViewById( R.id.buttonAdd);
        //buttonAdd.setOnClickListener(this);

        //eDate = (EditText)findViewById(R.id.editDate);
        //eDate.setOnClickListener(this);

    }
     //
    public void getDatePicker(final EditText et){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        et.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void inflateEntryRow(){
        final TableLayout tlayout = (TableLayout) findViewById(R.id.tableEntry);
        final TableRow entryRow = (TableRow) getLayoutInflater().inflate(R.layout.entryrow, null);

        final EditText tempDateID = (EditText) entryRow.findViewById(R.id.editDate);
        tempDateID.setId(View.generateViewId());
        //tempDateID.setId(vID);
        //vID++;
        tempDateID.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getDatePicker(tempDateID);
                   
            }
        });

        //Add row to the table
        tlayout.addView(entryRow);
    }

    public void summaryView(){
        //dbFarmAdapter mDbHelper = new dbFarmAdapter(getApplicationContext());
        mDbHelper.open();

        Cursor querySummary = mDbHelper.getSummary();
        
        do{
            inflateTableRow(querySummary);    
        }while(querySummary.moveToNext());

        querySummary.close();
        mDbHelper.close();
    }

    public void inflateTableRow(Cursor c){

        TableLayout tlayout = (TableLayout) findViewById(R.id.tableEntry);
        TableRow tableRow = (TableRow) getLayoutInflater().inflate(R.layout.tablerow, null);

        int textViewCount = 11;

        TextView[] tempRow = new TextView[textViewCount];

        tempRow[0] = (TextView) tableRow.findViewById(R.id.textDate);
        tempRow[1] = (TextView) tableRow.findViewById(R.id.textGroup);
        tempRow[2] = (TextView) tableRow.findViewById(R.id.textBatch);
        tempRow[3] = (TextView) tableRow.findViewById(R.id.textAction);
        tempRow[4] = (TextView) tableRow.findViewById(R.id.textNumber);
        tempRow[5] = (TextView) tableRow.findViewById(R.id.textLbs);
        tempRow[6] = (TextView) tableRow.findViewById(R.id.textCost);
        tempRow[7] = (TextView) tableRow.findViewById(R.id.textCOGS);
        tempRow[8] = (TextView) tableRow.findViewById(R.id.textPricePer);
        tempRow[9] = (TextView) tableRow.findViewById(R.id.textSale);
        tempRow[10] = (TextView) tableRow.findViewById(R.id.textAge);
        
        
            
        //Not sure if each record will display on new row. unique id may be required
        //tempDateID.setId(vID);
        //vID++;
        for (int i=0; i<c.getColumnCount(); i++) {
            //tempRow[i].setId(View.generateViewId());
            String var1 = c.getString(i);
            tempRow[i].setText(var1);
        }

        tempRow[3].setText("Summary");
        tlayout.addView(tableRow);  
    }    
            

    @Override public void onClick( View view) 
    {
    	switch (view.getId()) {
            /*
    		case R.id.buttonAdd: 
    			//Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_SHORT).show(); 
                inflateTableRow();
    			break; 
            */
    		case R.id.editDate:
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                eDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();  
    			break; 
            
    	}
    }

}
