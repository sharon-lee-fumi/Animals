package li.xiaowen.animals;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class DogCategoryFragment extends MainActivity.AnimalCategoryFragment {

    private SQLiteDatabase db;
    private Cursor cursor;

    public DogCategoryFragment() {
        // Required empty public constructor
    }

    public static DogCategoryFragment getInstance()
    {
        return new DogCategoryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dog_category, container, false);

        SQLiteOpenHelper animalsDataBaseHelper = new AnimalsDatabaseHelper(getActivity());
        ListView listDogs = view.findViewById(R.id.list_dogs);

        try {
            db = animalsDataBaseHelper.getReadableDatabase();

            cursor = db.query("DOG",
                    new String[]{"_id", "NAME"},  null, null, null, null, null);
            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(getActivity(),
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},
                    0);
            listDogs.setAdapter(listAdapter);
        }
        catch(SQLiteException e)
        {
            Toast toast = Toast.makeText(getActivity(), "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> listDogs,
                                            View itemView,
                                            int position,
                                            long id) {
                        Intent intent = new Intent(getActivity(), DogActivity.class);
                        intent.putExtra(DogActivity.EXTRA_DOGID, (int) id);
                        startActivity(intent);
                    }
                };

        listDogs.setOnItemClickListener(itemClickListener);

        return view;
    }

}
