package com.bbbond.nohttp.activity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bbbond.nohttp.R;

import java.util.List;
import java.util.Locale;

public class DownloadAdapter extends ArrayAdapter<Download> {

    private static final String TAG = "DownloadAdapter";
    private List<Download> downloadList;
    private int viewId;
    private LayoutInflater inflater;

    public DownloadAdapter(Context context, int resource, List<Download> downloadList) {
        super(context, resource, downloadList);
        inflater = LayoutInflater.from(context);
        viewId = resource;
        this.downloadList = downloadList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Download download = downloadList.get(position);
        Log.d(TAG, "------------------------------------------------------------------------");
        Log.d(TAG, "---" + download.getFileName() + "   " + download.getCurrentSize() + "    " + download.getProgress());
        Log.d(TAG, "------------------------------------------------------------------------");
        View view = null;
        Holder holder = null;
        if (convertView != null) {
            view = convertView;
            holder = (Holder) view.getTag();
        } else {
            view = inflater.inflate(viewId, null);
            holder = new Holder();
            holder.filename = (TextView) view.findViewById(R.id.tv_download_filename);
            holder.progress = (TextView) view.findViewById(R.id.tv_download_progress);
            holder.pbDownload = (ProgressBar) view.findViewById(R.id.pb_download);
            view.setTag(holder);
        }
        holder.filename.setText(download.getFileName());
        holder.progress.setText(String.format(Locale.CHINA, "%d", download.getProgress()));
        holder.pbDownload.setProgress(download.getProgress());
        return view;
    }

    public class Holder {
        TextView filename;
        TextView progress;
        ProgressBar pbDownload;
    }
}
