package es.upsa.dasi.quarkuscanciones.adapters.rest.providers;

import es.upsa.dasi.quarkuscanciones.adapters.rest.dtos.Ids;
import jakarta.ws.rs.ext.ParamConverter;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ListParamConverter implements ParamConverter<Ids> {

    final String delimiter;

    public ListParamConverter(String delimiter) {
        this.delimiter = delimiter;
    }

    @Override
    public Ids fromString(String s) {

        String data = s.trim();

        return data.isEmpty()? Ids.empty() : new Ids(List.of(data.split(Pattern.quote(delimiter))));
    }

    @Override
    public String toString(Ids ids) {
        return ids.getIds().stream().collect(Collectors.joining(delimiter));
    }
}
