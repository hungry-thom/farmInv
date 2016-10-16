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

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.content.res.Resources;

import java.util.Calendar;

public class MainActivity extends Activity implements View.OnClickListener
{
    EditText eDate;
    private int mYear, mMonth, mDay, mHour, mMinute;
    int vID=1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //inflateTableRow();

        final Button buttonAdd = (Button) findViewById( R.id.buttonAdd);
        //buttonAdd = (Button) findViewById( R.id.buttonAdd);
        buttonAdd.setOnClickListener(this);

        eDate = (EditText)findViewById(R.id.editDate);
        eDate.setOnClickListener(this);

    }
    /* //
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
*/
    public void inflateTableRow(){
        final TableLayout tl = (TableLayout) findViewById(R.id.tableEntry);
        final TableRow tableRow = (TableRow) getLayoutInflater().inflate(R.layout.tablerow, null);

        final EditText tempDateID = (EditText) tableRow.findViewById(R.id.editDate);
        tempDateID.setId(View.generateViewId());
        //tempDateID.setId(vID);
        //vID++;
        tempDateID.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //getDatePicker(tempDateID);
                   
            }
        });

        //Add row to the table
        tl.addView(tableRow);
    }

    @Override public void onClick( View view) 
    {
    	switch (view.getId()) {

    		case R.id.buttonAdd: 
    			//Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_SHORT).show(); 
                inflateTableRow();
    			break; 
            
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
