package com.example.hpnotebook.newstime;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Hp Notebook on 29-01-2018.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    /**
     * Tag for log messages
     */
    private static final String LOG_TAG = NewsLoader.class.getName();

    /**
     * Query URL
     */
    private String mUrl;

    /**
     * Constructs a new {@link NewsLoader}.
     *
     * @param context of the activity
     * @param url     to load data from
     */
    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        // Don't perform the request if there are no URLs, or the first URL is null.
        if (mUrl == null) {
            return null;
        }

        List<News> news = QueryUtils.fetchNewsData(mUrl);
        return news;
    }
}
