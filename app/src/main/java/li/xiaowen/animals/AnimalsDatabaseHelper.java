package li.xiaowen.animals;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AnimalsDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "animals";
    private static final int DB_VERSION = 1;

    AnimalsDatabaseHelper (Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private static void insertCat(SQLiteDatabase db, String name, String description,
                                    int resourceId)
    {
        ContentValues catValues = new ContentValues();
        catValues.put("NAME", name);
        catValues.put("DESCRIPTION", description);
        catValues.put("IMAGE_RESOURCE_ID", resourceId);
        db.insert("CAT", null, catValues);
    }

    private static void insertDog(SQLiteDatabase db, String name, String description,
                                  int resourceId)
    {
        ContentValues catValues = new ContentValues();
        catValues.put("NAME", name);
        catValues.put("DESCRIPTION", description);
        catValues.put("IMAGE_RESOURCE_ID", resourceId);
        db.insert("DOG", null, catValues);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE CAT (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER);");
            insertCat(db, "American Bobtail", "The American Bobtail is an uncommon breed of domestic cat which was developed in the late 1960s.", R.drawable.american_bobtail);
            insertCat(db, "Birman", "The Birman is a long-haired, color-pointed cat distinguished by a silky coat, deep blue eyes, and contrasting white gloves or socks on each paw.",
                    R.drawable.birman);
            insertCat(db, "British Shorthair", "The British Shorthair is the pedigreed version of the traditional British domestic cat, with a distinctively chunky body, dense coat and broad face.", R.drawable.british_shorthair);

            db.execSQL("CREATE TABLE DOG (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER);");
            insertDog(db, "Bichon Frise", "A Bichon Frise is a small breed of dog of the bichon type. The Bichon Frise is a member of the Non-sporting Group of dog breeds in the United States, and a member of the Toy Dog Group in the United Kingdom.", R.drawable.bichon_frise);
            insertDog(db, "Old English Sheepdog", "The Old English Sheepdog is a large breed of dog which was developed in England from early herding types of dog. The Old English Sheepdog can grow a very long coat, with fur covering the face and eyes.",
                    R.drawable.old_english_sheepdog);
            insertDog(db, "Alaskan Malamute", "The Alaskan Malamute is a large breed of domestic dog originally bred for hauling heavy freight due to their strength and endurance, and later a sled dog.", R.drawable.alaskan_malamute);
        }

    }
}
