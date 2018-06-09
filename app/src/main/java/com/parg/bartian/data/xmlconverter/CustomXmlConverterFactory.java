package com.parg.bartian.data.xmlconverter;


import com.parg.bartian.domain.ScheduleDetail;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by ganga_r on 10/31/2016.
 */

public class CustomXmlConverterFactory extends Converter.Factory {
    public static CustomXmlConverterFactory create() {
        return new CustomXmlConverterFactory();
    }

    private CustomXmlConverterFactory() {}

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {

        if (ScheduleDetail.class.equals(type)) {
            return new Converter<ResponseBody, ScheduleDetail>() {

                @Override
                public ScheduleDetail convert(ResponseBody body) throws IOException {
                    InputStream inputStream = body.byteStream();
                    ScheduleDetail scheduleDetail = null;

                    //must be added to apply custom converter annotations
                    Strategy strategy = new AnnotationStrategy();
                    Serializer serializer = new Persister(strategy);
                    try {
                        scheduleDetail = serializer.read(ScheduleDetail.class, inputStream);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return scheduleDetail;
                }
            };
        }
        return null;
    }
}
