package de.htw.selabs.android.namenmanagement;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import de.htw.selabs.android.namenmanagement.domain.Person;

public class PersonItemAdapter extends ArrayAdapter<Person> {

  int resource;

  public PersonItemAdapter(Context context,
                         int resource,
                         List<Person> items) {
    super(context, resource, items);
    this.resource = resource;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LinearLayout personView;

    Person item = getItem(position);

    String lastName = item.getLastName();
    String firstName = item.getFirstName();
    String email = item.geteMail();
    String emailString = (email.isEmpty()?"no Email":email);

    if (convertView == null) {
      personView = new LinearLayout(getContext());
      String inflater = Context.LAYOUT_INFLATER_SERVICE;
      LayoutInflater li;
      li = (LayoutInflater)getContext().getSystemService(inflater);
      li.inflate(resource, personView, true);
    } else {
      personView = (LinearLayout) convertView;
    }

    TextView emailView = (TextView)personView.findViewById(R.id.rowEmail);
    TextView nameView = (TextView)personView.findViewById(R.id.row);

    emailView.setText(emailString);
    nameView.setText(lastName + ", " + firstName);

    return personView;
  }
}