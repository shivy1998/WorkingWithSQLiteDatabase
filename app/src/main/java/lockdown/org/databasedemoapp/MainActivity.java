package lockdown.org.databasedemoapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    SQLiteDatabase db;
    EditText etName,etMobileNo;
    Button btnSave,btnViewRecord;
    protected void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase("studentdb",MODE_PRIVATE,null);
        db.execSQL("Create table if not exists students(name varchar(30),mobileno varchar(100))");

        etName= findViewById(R.id.etName);
        etMobileNo= findViewById(R.id.etMobileNo);
        btnSave= findViewById(R.id.btnSave);
        btnViewRecord= findViewById(R.id.btnViewRecord);
        btnSave.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String name = etName.getText().toString();
                String mobileno = etMobileNo.getText().toString();
                if(name.length()==0)
                {
                    Toast.makeText(MainActivity.this,"Enter your name",Toast.LENGTH_LONG).show();
                }
                else if(mobileno.length()==0)
                {
                    Toast.makeText(MainActivity.this,"Enter your mobileno",Toast.LENGTH_LONG).show();
                }
                else
                {
                    try
                    {
                            db.execSQL("insert into students(name, mobileno) values('"+name+"','"+mobileno+"')");
                            Toast.makeText(MainActivity.this,"Record inserted",Toast.LENGTH_LONG).show();
                    }
                    catch(Exception e)
                    {
                            Toast.makeText(MainActivity.this,"Error : "+e,Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnViewRecord.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i=new Intent(MainActivity.this,DisplayActivity.class);
                startActivity(i);
            }
        });
    }
}