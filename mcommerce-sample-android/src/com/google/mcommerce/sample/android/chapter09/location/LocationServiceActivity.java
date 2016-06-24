package com.google.mcommerce.sample.android.chapter09.location;

import java.util.List;

import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.mcommerce.sample.android.R;

public class LocationServiceActivity extends Activity implements LocationListener {

	private static final String[] A = { "n/a", "fine", "coarse" };
	private static final String[] P = { "n/a", "low", "medium", "high" };
	private static final String[] S = { "out of service",
			"temporarily unavailable", "available" };

	private LocationManager mgr;
	private TextView output;
	private String best;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c09_location_layout);

		mgr = (LocationManager) getSystemService(LOCATION_SERVICE);
		output = (TextView) findViewById(R.id.location_text);
		Log.d("LocationService", "test1");
		log("Location providers:");
		Log.d("LocationService", "test2");
		dumpProviders();
		Log.d("LocationService", "test3");

		Criteria criteria = new Criteria();
		best = mgr.getBestProvider(criteria, true);
		log("\nBest provider is: " + best);

		log("\nLocations (starting with last known):");
		Location location = mgr.getLastKnownLocation(best);
		dumpLocation(location);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// Start updates (doc recommends delay >= 60000 ms)
		mgr.requestLocationUpdates(best, 15000, 1, this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		// Stop updates to save power while app paused
		mgr.removeUpdates(this);
	}

	public void onLocationChanged(Location location) {
		dumpLocation(location);
	}

	public void onProviderDisabled(String provider) {
		log("\nProvider disabled: " + provider);
	}

	public void onProviderEnabled(String provider) {
		log("\nProvider enabled: " + provider);
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		log("\nProvider status changed: " + provider + ", status=" + S[status]
				+ ", extras=" + extras);
	}

	private void log(String string) {
		output.append(string + "\n");
	}

	private void dumpProviders() {
		List<String> providers = mgr.getAllProviders();
		Log.d("LocationService", "test4");
		for (String provider : providers) {
			dumpProvider(provider);
		}
	}

	private void dumpProvider(String provider) {
		Log.d("LocationService", "test5");
		LocationProvider info = mgr.getProvider(provider);
		Log.d("LocationService", info.getName());
		StringBuilder builder = new StringBuilder();
		builder.append("LocationProvider[").append("name=")
				.append(info.getName()).append(",enabled=")
				.append(mgr.isProviderEnabled(provider))
				.append(",getAccuracy=").append(A[info.getAccuracy()])
				.append(",getPowerRequirement=")
				.append(P[info.getPowerRequirement()])
				.append(",hasMonetaryCost=").append(info.hasMonetaryCost())
				.append(",requiresCell=").append(info.requiresCell())
				.append(",requiresNetwork=").append(info.requiresNetwork())
				.append(",requiresSatellite=").append(info.requiresSatellite())
				.append(",supportsAltitude=").append(info.supportsAltitude())
				.append(",supportsBearing=").append(info.supportsBearing())
				.append(",supportsSpeed=").append(info.supportsSpeed())
				.append("]");
		Log.d("LocationService", builder.toString());
		log(builder.toString());
	}

	private void dumpLocation(Location location) {
		if (location == null)
			log("\nLocation[unknown]");
		else
			log("\n" + location.toString());
	}

}
