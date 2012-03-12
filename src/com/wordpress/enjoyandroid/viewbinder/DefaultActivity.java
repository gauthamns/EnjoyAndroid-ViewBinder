package com.wordpress.enjoyandroid.viewbinder;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorAdapter.ViewBinder;

public class DefaultActivity extends ListActivity {

	private static final String[] UI_BINDING_FROM = new String[] {
		ClubCP.KEY_NAME, ClubCP.KEY_IS_STAR
	};
	
	private static final int[] UI_BINDING_TO = new int[] {
		R.id.name, R.id.star
	};
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Cursor cursor = managedQuery(ClubCP.CONTENT_URI, null, null, null, null);

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				getApplicationContext(), R.layout.club_row, cursor, UI_BINDING_FROM,
				UI_BINDING_TO);
		adapter.setViewBinder(new CustomViewBinder());
		setListAdapter(adapter);
	}
	
	/**
	 * Custom ViewBinder to handle custom view showing in SimpleCursorAdapter.
	 * @author gautham
	 *
	 */
	private class CustomViewBinder implements ViewBinder {

		@Override
		public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
			if (columnIndex == cursor.getColumnIndex(ClubCP.KEY_IS_STAR)) {
				// If the column is IS_STAR then we use custom view.
				int is_star = cursor.getInt(columnIndex);
				if (is_star != 1) {
					// set the visibility of the view to GONE
					view.setVisibility(View.GONE);
				}
				return true;
			}
			// For others, we simply return false so that the default binding
			// happens.
			return false;
		}
		
	}
}