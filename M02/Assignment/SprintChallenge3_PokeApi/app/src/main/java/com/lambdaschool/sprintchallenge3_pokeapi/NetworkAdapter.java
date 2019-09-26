package com.lambdaschool.sprintchallenge3_pokeapi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public final class NetworkAdapter {

    private static final int    TIMEOUT = 3000;

    public static String httpRequest(final String urlString, final String requestType) {
        String            result     = "";
        InputStream       stream     = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            // Timeout for reading InputStream arbitrarily set to 3000ms.
            connection.setReadTimeout(TIMEOUT);
            // Timeout for connection.connect() arbitrarily set to 3000ms.
            connection.setConnectTimeout(TIMEOUT);
            // For this use case, set HTTP method to GET.
            connection.setRequestMethod(requestType);

            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                result = Integer.toString(responseCode);
                throw new IOException("HTTP error code: " + responseCode);
            }
            // Retrieve the response body as an InputStream.
            stream = connection.getInputStream();
            // publishProgress(DownloadCallback.Progress.GET_INPUT_STREAM_SUCCESS, 0);
            if (stream != null) {
                // Converts Stream to String with max length of 500.
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder  sb     = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
                result = sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "failure";
        } finally {

            // Close Stream and disconnect HTTPS connection.
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }

        }
        return result;
    }

    public static String httpRequest(final String urlString, final String requestType, final JSONObject content) {
        String            result     = "";
        InputStream       stream     = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
//                    connection.setRequestProperty("x-api-key", API_KEY);
            // Timeout for reading InputStream arbitrarily set to 3000ms.
            connection.setReadTimeout(TIMEOUT);
            // Timeout for connection.connect() arbitrarily set to 3000ms.
            connection.setConnectTimeout(TIMEOUT);
            // For this use case, set HTTP method to GET.
            connection.setRequestMethod(requestType);

            /*if (headerProperties != null) {
                for (Map.Entry<String, String> entry : headerProperties.entrySet()) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }*/

            // Already true by default but setting just in case; needs to be true since this request
            // is carrying an input (response) body.
            connection.setDoInput(true);

            if (requestType.equals("POST") && content != null) {
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(content.toString().getBytes());
                outputStream.close();
            } else {
                // Open communications link (network traffic occurs here).
                connection.connect();
            }
            //            publishProgress(DownloadCallback.Progress.CONNECT_SUCCESS);
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                result = Integer.toString(responseCode);
                throw new IOException("HTTP error code: " + responseCode);
            }
            // Retrieve the response body as an InputStream.
            stream = connection.getInputStream();
            // publishProgress(DownloadCallback.Progress.GET_INPUT_STREAM_SUCCESS, 0);
            if (stream != null) {
                // Converts Stream to String with max length of 500.
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder  sb     = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
                result = sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // Close Stream and disconnect HTTPS connection.
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }

        }

        /*try {
            downloadThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            callback.onRequestFinished(false, result[0]);
        }*/

        return result;
    }

    public static Bitmap getBitmapFromURL(final String urlString) {
        Bitmap            result     = null;
        InputStream       stream     = null;
        HttpURLConnection connection = null;
        try {

            URL url = new URL(urlString);
            connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
//                    result[0] = BitmapFactory.decodeStream(input);
            result = BitmapFactory.decodeStream(input);
            /*ByteArrayOutputStream compressStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 10, compressStream);
            byte[] byteArray = compressStream.toByteArray();
            result = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);*/
//            result = Bitmap.createScaledBitmap(image, 500, 500, false);
//            Log.i("Bitmaps", String.format("orig %d - comp %d %f", image.getByteCount(), result.getByteCount(), 100f * (float)result.getByteCount() / image.getByteCount()));
//            result = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeStream(input), 1000, 100);
            Log.i("Bitmaps", String.format("thumb %d", result.getByteCount()));


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close Stream and disconnect HTTPS connection.
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;

        /*try {
            downloadThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result[0];*/
    }
}
