package li.xiaowen.animals;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CatActivity extends AppCompatActivity {

    public static final String EXTRA_CATID = "catId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);

        int catId = (Integer)getIntent().getExtras().get(EXTRA_CATID);

        SQLiteOpenHelper animalsDataBaseHelper = new AnimalsDatabaseHelper(this);

        try {
            SQLiteDatabase db = animalsDataBaseHelper.getReadableDatabase();
            Cursor cursor = db.query ("CAT",
                    new String[] {"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"},
                    "_id = ?",
                    new String[] {Integer.toString(catId)},
                    null, null,null);

            if (cursor.moveToFirst()) {

                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int imageId = cursor.getInt(2);

                TextView name = findViewById(R.id.name);
                name.setText(nameText);

                TextView description = findViewById(R.id.description);
                description.setText(descriptionText);

                ImageView photo = findViewById(R.id.image);
                photo.setImageResource(imageId);
                photo.setContentDescription(nameText);
            }
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
