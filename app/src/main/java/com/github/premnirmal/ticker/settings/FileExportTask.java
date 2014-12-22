package com.github.premnirmal.ticker.settings;

import android.os.AsyncTask;

import com.github.premnirmal.ticker.Tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by premnirmal on 12/22/14.
 */
class FileExportTask extends AsyncTask<Object, Void, String> {

    @Override
    protected String doInBackground(Object... tickers) {
        final File file = Tools.getTickersFile();
        try {
            if (file.exists()) {
                file.delete();
                file.createNewFile();
            }
            final FileOutputStream fileOutputStream = new FileOutputStream(file);
            for (Object ticker : tickers) {
                fileOutputStream.write((ticker + ",").getBytes());
            }
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
        return file.getPath();
    }
}
