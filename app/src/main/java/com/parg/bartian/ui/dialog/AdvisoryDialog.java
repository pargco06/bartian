package com.parg.bartian.ui.dialog;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parg.bartian.R;

public class AdvisoryDialog extends DialogFragment {

    public static final String ADVISORY_INFO = "AdvisoryInfo";
    private TextView advisoryTextView;

    public static AdvisoryDialog newInstance(String advisoryinfo) {
        AdvisoryDialog advisoryDialogFragment = new AdvisoryDialog();
        Bundle args = new Bundle();
        args.putString(ADVISORY_INFO, advisoryinfo);
        advisoryDialogFragment.setArguments(args);
        return advisoryDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.advisory_dialog, container, false);
        advisoryTextView = v.findViewById(R.id.advisorText);
        advisoryTextView.setText(fromHtml(getArguments().getString(ADVISORY_INFO)));
        return v;
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(html);
        }
    }
}
