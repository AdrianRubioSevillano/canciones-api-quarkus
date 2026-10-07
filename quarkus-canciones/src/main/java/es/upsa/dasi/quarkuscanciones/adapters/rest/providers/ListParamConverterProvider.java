package es.upsa.dasi.quarkuscanciones.adapters.rest.providers;

import es.upsa.dasi.quarkuscanciones.adapters.rest.dtos.Ids;
import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
public class ListParamConverterProvider implements ParamConverterProvider {
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> aClass, Type type, Annotation[] annotations) {

        if (Ids.class.isAssignableFrom(aClass)) {


            return (ParamConverter<T>) new ListParamConverter(":");

        }

        return null;
    }
}
