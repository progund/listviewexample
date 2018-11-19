package se.juneday.examples.listviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    // enum anyone?  ;)
    private static final int MENU_ENTRY_DELETE = 0 ;
    private static final int MENU_ENTRY_DUPLICATE = 1 ;

    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupList();
    }

    private List<Member> members;

    public void setupList() {
        // Populate List
        members = new ArrayList<Member>();
        members.add(new Member("Ingegerd Jonsson"));
        members.add(new Member("Ingemar Jonsson"));
        members.add(new Member("Berit Magnusson"));
        members.add(new Member("Daniel Magnusson"));
        members.add(new Member("Kristina Axelsson"));
        members.add(new Member("Emil Axelsson"));
        members.add(new Member("Monica Johansson"));
        members.add(new Member("Mats Johansson"));
        members.add(new Member("Anette Olsson"));
        members.add(new Member("Viktor Olsson"));
        members.add(new Member("Maria Jakobsson"));
        members.add(new Member("Andreas Jakobsson"));
        members.add(new Member("Inga Björk"));
        members.add(new Member("Hans Björk"));
        members.add(new Member("Siv Wallin"));
        members.add(new Member("Jan Wallin"));
        members.add(new Member("Linda Nilsson"));
        members.add(new Member("Ingemar Nilsson"));
        members.add(new Member("Susanne Forsberg"));
        members.add(new Member("Olov Forsberg"));


        // Lookup ListView
        ListView listView = (ListView) findViewById(R.id.member_list);

        // Create Adapter
        adapter = new ArrayAdapter<Member>(this,
                android.R.layout.simple_list_item_1,
                members);

        // Set listView's adapter to the new adapter
        listView.setAdapter(adapter);

        registerForContextMenu(listView);

        listView.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent,
                                    final View view,
                                    int position /*The position of the view in the adapter.*/,
                                    long id /* The row id of the item that was clicked */) {
                Log.d(LOG_TAG, "item clicked, pos:" + position + " id: " + id);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        ListView listView = (ListView) v;
        AdapterView.AdapterContextMenuInfo acMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Member member = (Member) listView.getItemAtPosition(acMenuInfo.position);
        menu.setHeaderTitle("Manager " + member);
        menu.add(Menu.NONE, MENU_ENTRY_DELETE, Menu.NONE, "Delete");
        menu.add(Menu.NONE, MENU_ENTRY_DUPLICATE, Menu.NONE, "Duplicate");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // get extra information set by the View that added this menu item
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Log.d(LOG_TAG, " itemId: " + item.getItemId());
        Log.d(LOG_TAG, " title:  " + item.getTitle());
        if (item.getItemId() == MENU_ENTRY_DUPLICATE) {
            Log.d(LOG_TAG, " member: " + members.get(info.position));
            members.add(members.get(info.position));
            ((ListView)findViewById(R.id.member_list)).invalidateViews();
        }
        return true;
    }
}