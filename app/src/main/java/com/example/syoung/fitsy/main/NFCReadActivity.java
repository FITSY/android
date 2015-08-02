package com.example.syoung.fitsy.main;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syoung.fitsy.R;
import com.example.syoung.fitsy.main.nfc.NdefMessageParser;
import com.example.syoung.fitsy.main.nfc.ParsedRecord;
import com.example.syoung.fitsy.main.nfc.TextRecord;
import com.example.syoung.fitsy.main.nfc.UriRecord;

import java.util.List;

public class NFCReadActivity extends AppCompatActivity {

	TextView readResult;

	private NfcAdapter mAdapter;
	private PendingIntent mPendingIntent;
	private IntentFilter[] mFilters;
	private String[][] mTechLists;

	public static final int TYPE_TEXT = 1;
	public static final int TYPE_URI = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nfc_read);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction().add(R.id.nfcContainer, new PlaceholderFragment()).commit();
		}

		readResult = (TextView) findViewById(R.id.readResult);

		mAdapter = NfcAdapter.getDefaultAdapter(this);
		Intent targetIntent = new Intent(this, NFCReadActivity.class);
		targetIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		mPendingIntent = PendingIntent.getActivity(this, 0, targetIntent, 0);

		IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
		try {
			ndef.addDataType("*/*");
		} catch (MalformedMimeTypeException e) {
			throw new RuntimeException("fail", e);
		}

		mFilters = new IntentFilter[] { ndef, };

		mTechLists = new String[][] { new String[] { NfcF.class.getName() } };

		Intent passedIntent = getIntent();
		if (passedIntent != null) {
			String action = passedIntent.getAction();
			if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
				processTag(passedIntent);
			}
		}
	}

	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_nfc_read, container,
					false);
			return rootView;
		}
	}

	public void onResume() {
		super.onResume();

		if (mAdapter != null) {
			mAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters,
					mTechLists);
		}
	}

	public void onPause() {
		super.onPause();

		if (mAdapter != null) {
			mAdapter.disableForegroundDispatch(this);
		}
	}

	public void onNewIntent(Intent passedIntent) {
		Tag tag = passedIntent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
		if (tag != null) {
			byte[] tagId = tag.getId();
			readResult.append("tag ID : " + toHexString(tagId) + "\n");
		}

		if (passedIntent != null) {
			processTag(passedIntent);
		}
	}

	public static final String CHARS = "0123456789ABCDEF";
	public static String toHexString(byte[] data) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < data.length; ++i) {
			sb.append(CHARS.charAt((data[i] >> 4) & 0x0F)).append(
					CHARS.charAt(data[i] & 0x0F));
		}
		return sb.toString();
	}

	private void processTag(Intent passedIntent) {
		Parcelable[] rawMsgs = passedIntent
				.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
		if (rawMsgs == null) {
			return;
		}

		Toast.makeText(getApplicationContext(), "��ĵ ����!", Toast.LENGTH_SHORT).show();

		NdefMessage[] msgs;
		if (rawMsgs != null) {
			msgs = new NdefMessage[rawMsgs.length];
			for (int i = 0; i < rawMsgs.length; i++) {
				msgs[i] = (NdefMessage) rawMsgs[i];
				showTag(msgs[i]);
			}
		}
	}

	private int showTag(NdefMessage mMessage) {
		List<ParsedRecord> records = NdefMessageParser.parse(mMessage);
		final int size = records.size();
		for (int i = 0; i < size; i++) {
			ParsedRecord record = records.get(i);

			int recordType = record.getType();
			String recordStr = "";
			if (recordType == ParsedRecord.TYPE_TEXT) {
				recordStr = "TEXT : " + ((TextRecord) record).getText();
			} else if (recordType == ParsedRecord.TYPE_URI) {
				recordStr = "URI : " + ((UriRecord) record).getUri().toString();
			}

			readResult.append(recordStr + "\n");
		}

		return size;
	}
}
