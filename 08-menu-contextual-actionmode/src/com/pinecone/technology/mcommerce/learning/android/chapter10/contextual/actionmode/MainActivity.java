package com.pinecone.technology.mcommerce.learning.android.chapter10.contextual.actionmode;

import com.pinecone.technology.mcommerce.learning.android.chapter10.contextual.actionmode.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	protected Object mActionMode;
	private EditText view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Define the contextual action mode
		view = (EditText) findViewById(R.id.myView);
		view.setOnLongClickListener(new View.OnLongClickListener() {
			// Called when the user long-clicks on someView
			public boolean onLongClick(View view) {
				if (mActionMode != null) {
					return false;
				}

				// Start the CAB using the ActionMode.Callback defined above
				mActionMode = MainActivity.this
						.startActionMode(mActionModeCallback);
				view.setSelected(true);
				return true;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Toast.makeText(this, "Just a test", Toast.LENGTH_SHORT).show();
		return true;
	}

	private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

		// Called when the action mode is created; startActionMode() was called
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			// Inflate a menu resource providing context menu items
			MenuInflater inflater = mode.getMenuInflater();
			// Assumes that you have "contexual.xml" menu resources
			inflater.inflate(R.menu.contextual, menu);
			return true;
		}

		// Called each time the action mode is shown. Always called after
		// onCreateActionMode, but
		// may be called multiple times if the mode is invalidated.
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			return false; // Return false if nothing is done
		}

		// Called when the user selects a contextual menu item
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			switch (item.getItemId()) {
			case R.id.toast:
				Toast.makeText(MainActivity.this, view.getText(),
						Toast.LENGTH_LONG).show();
				mode.finish(); // Action picked, so close the CAB
				return true;
			default:
				return false;
			}
		}

		// Called when the user exits the action mode
		public void onDestroyActionMode(ActionMode mode) {
			mActionMode = null;
		}
	};

}