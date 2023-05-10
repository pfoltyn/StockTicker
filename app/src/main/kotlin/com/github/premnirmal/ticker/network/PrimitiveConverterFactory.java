package com.github.premnirmal.ticker.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by bradhawk on 1/29/2016.
 * https://gist.github.com/fathonyfath/b6aa8f6f9e05d148a216
 */
public class PrimitiveConverterFactory extends Converter.Factory {

    public static PrimitiveConverterFactory create() {
        return new PrimitiveConverterFactory();
    }

    private PrimitiveConverterFactory() {
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type == String.class) {
            return new Converter<ResponseBody, String>() {
                @Override
                public String convert(ResponseBody value) throws IOException {
                    //Decode the response text/html (gzip encoded)
                    String result = "";
                    try (GZIPInputStream gzip = new GZIPInputStream(value.byteStream());
                         InputStreamReader reader = new InputStreamReader(gzip);
                         BufferedReader buffer = new BufferedReader(reader)) {
                        String line;
                        while ((line = buffer.readLine()) != null) {
                            result += line + "\n";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return result;
                }
            };
        }
        return null;
    }
}
