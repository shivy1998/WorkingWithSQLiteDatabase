package lockdown.org.databasedemoapp;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
public class DisplayActivity extends AppCompatActivity
{
    ListView lv;
    SQLiteDatabase db;
    ArrayList al = new ArrayList();
    protected void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.activity_display);
        lv = findViewById(R.id.lv);
        db = openOrCreateDatabase("studentdb",MODE_PRIVATE,null);
        Cursor cur = db.rawQuery("select * from students", null);
        while(cur.moveToNext())
        {
            String name = cur.getString(0);
            String mobileno = cur.getString(1);
            al.add(name+"\n"+mobileno);
        }
        cur.close();
        ArrayAdapter aa = new ArrayAdapter(DisplayActivity.this,android.R.layout.simple_list_item_1,al);
        lv.setAdapter(aa);
    }
}