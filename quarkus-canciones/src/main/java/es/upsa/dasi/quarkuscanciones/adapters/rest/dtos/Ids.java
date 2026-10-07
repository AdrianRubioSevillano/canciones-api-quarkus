package es.upsa.dasi.quarkuscanciones.adapters.rest.dtos;

import lombok.*;

import java.util.List;

@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
public class Ids {
    private List<String> ids;

    public static Ids empty(){
        return new Ids(List.of());
    }
    public static Ids of(List<String> ids){
        return new Ids(List.copyOf(ids));
    }
    public static Ids of(String...ids){
        return new Ids(List.of(ids));
    }

    public boolean isEmpty(){
        return ids == null ||ids.isEmpty();
    }
}
