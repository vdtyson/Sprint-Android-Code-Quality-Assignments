package com.lambdaschool.sprintchallenge3_pokeapi

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

import javax.net.ssl.HttpsURLConnection

object NetworkAdapter {

    private const val TIMEOUT = 3000

    fun httpRequest(urlString: String, requestType: String): String {
        var result = ""
        var stream: InputStream? = null
        var connection: HttpURLConnection? = null
        try {
            val url = URL(urlString)
            connection = url.openConnection() as HttpURLConnection
            // Timeout for reading InputStream arbitrarily set to 3000ms.
            connection.readTimeout = TIMEOUT
            // Timeout for connection.connect() arbitrarily set to 3000ms.
            connection.connectTimeout = TIMEOUT
            // For this use case, set HTTP method to GET.
            connection.requestMethod = requestType

            connection.connect()

            val responseCode = connection.responseCode

            if (responseCode != HttpsURLConnection.HTTP_OK)
                throw IOException("HTTP error code: $responseCode")

            // Retrieve the response body as an InputStream.
            stream = connection.inputStream
            // publishProgress(DownloadCallback.Progress.GET_INPUT_STREAM_SUCCESS, 0);
            if (stream != null) {
                // Converts Stream to String with max length of 500.
                val reader = BufferedReader(InputStreamReader(stream))
                val sb = StringBuilder()

                reader.forEachLine {
                    sb.appendln(it)
                }

                result = sb.toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            result = "failure"
        } finally {

            // Close Stream and disconnect HTTPS connection.
            if (stream != null) {
                try {
                    stream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
            connection?.disconnect()

        }
        return result
    }

    fun getBitmapFromURL(urlString: String): Bitmap? {
        var result: Bitmap? = null
        val stream: InputStream? = null
        var connection: HttpURLConnection? = null
        try {

            val url = URL(urlString)
            connection = url
                    .openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input = connection.inputStream
            //                    result[0] = BitmapFactory.decodeStream(input);
            result = BitmapFactory.decodeStream(input)
            /*ByteArrayOutputStream compressStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 10, compressStream);
            byte[] byteArray = compressStream.toByteArray();
            result = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);*/
            //            result = Bitmap.createScaledBitmap(image, 500, 500, false);
            //            Log.i("Bitmaps", String.format("orig %d - comp %d %f", image.getByteCount(), result.getByteCount(), 100f * (float)result.getByteCount() / image.getByteCount()));
            //            result = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeStream(input), 1000, 100);
            Log.i("Bitmaps", String.format("thumb %d", result!!.byteCount))


        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            // Close Stream and disconnect HTTPS connection.
            if (stream != null) {
                try {
                    stream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
            connection?.disconnect()
        }
        return result

        /*try {
            downloadThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result[0];*/
    }
}
