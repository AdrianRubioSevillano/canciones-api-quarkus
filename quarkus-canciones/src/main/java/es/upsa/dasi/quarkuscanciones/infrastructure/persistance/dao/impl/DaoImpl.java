package es.upsa.dasi.quarkuscanciones.infrastructure.persistance.dao.impl;

import es.upsa.dasi.quarkuscanciones.domain.exceptions.CancionesRunTimeException;
import es.upsa.dasi.quarkuscanciones.infrastructure.persistance.dao.Dao;
import es.upsa.dasi.quarkuscanciones.infrastructure.persistance.dao.dtos.CancionRow;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.naming.ldap.PagedResultsControl;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DaoImpl implements Dao {

    @Inject
    DataSource dataSource;

    @Override
    public List<CancionRow> findAll() {

        final String SQL = """
                           SELECT c.id, c.titulo, c.artista, c.album, c.duracion_segundos, c.fecha_estreno
                           FROM canciones c
                           ORDER BY c.duracion_segundos
                           """;

        List<CancionRow> caciones = new ArrayList<>();

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
                ){
            try(ResultSet resultSet = preparedStatement.executeQuery()){

                while (resultSet.next()) {

                    CancionRow cancion = CancionRow.builder()
                            .id(resultSet.getString(1))
                            .titulo(resultSet.getString(2))
                            .artista(resultSet.getString(3))
                            .albukm(resultSet.getString(4))
                            .duracion(resultSet.getInt(5))
                            .fechaEstreno(resultSet.getDate(6).toLocalDate())
                            .build();
                    caciones.add(cancion);
                }

            }
            return caciones;

        }catch (SQLException sqlException){
            throw new CancionesRunTimeException(sqlException.getMessage());
        }

    }

    @Override
    public Optional<CancionRow> findById(String id) {

        final String SQL = """
                           SELECT c.id, c.titulo, c.artista, c.album, c.duracion_segundos, c.fecha_estreno
                           FROM canciones c
                           WHERE c.id = ?
                           """;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
                ){
            preparedStatement.setString(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){

                if(!resultSet.next()) return Optional.empty();
                return Optional.of(CancionRow.builder()
                        .id(resultSet.getString(1))
                        .titulo(resultSet.getString(2))
                        .artista(resultSet.getString(3))
                        .albukm(resultSet.getString(4))
                        .duracion(resultSet.getInt(5))
                        .fechaEstreno(resultSet.getDate(6).toLocalDate())
                        .build());
            }

        }catch (SQLException sqlException){
            throw new CancionesRunTimeException(sqlException.getMessage());
        }

    }

    @Override
    public List<CancionRow> findByIds(List<String> ids) {

        final String SQL = """
                           SELECT c.id, c.titulo, c.artista, c.album, c.duracion_segundos, c.fecha_estreno
                           FROM canciones c
                           WHERE c.id = ?
                           """;

        List<CancionRow> caciones = new ArrayList<>();

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
                ){

            for (String id : ids) {
                preparedStatement.setString(1, id);
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    resultSet.next();
                    CancionRow cancion = CancionRow.builder()
                                                    .id(resultSet.getString(1))
                                                    .titulo(resultSet.getString(2))
                                                    .artista(resultSet.getString(3))
                                                    .albukm(resultSet.getString(4))
                                                    .duracion(resultSet.getInt(5))
                                                    .fechaEstreno(resultSet.getDate(6).toLocalDate())
                                                    .build();
                    caciones.add(cancion);
                }
            }

            return caciones;

        }catch (SQLException sqlException){
            throw new CancionesRunTimeException(sqlException.getMessage());
        }

    }

    @Override
    public CancionRow insert(CancionRow cancionRow) {

        final String SQL = """
                           INSERT INTO canciones(id,        titulo, artista, album, duracion_segundos, fecha_estreno)
                           VALUES(NEXTVAL('seq_canciones'), ?,     ?,       ?,          ?,                ?)
                           """;
        final String[] FIELDS = {"id"};

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL, FIELDS);
                ){
            preparedStatement.setString(1, cancionRow.getTitulo());
            preparedStatement.setString(2, cancionRow.getArtista());
            preparedStatement.setString(3, cancionRow.getAlbukm());
            preparedStatement.setInt(4, cancionRow.getDuracion());
            preparedStatement.setDate(5, Date.valueOf(cancionRow.getFechaEstreno()));
            preparedStatement.executeUpdate();
            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){

                resultSet.next();
                return cancionRow.withId(resultSet.getString(1));
            }

        }catch (SQLException sqlException){
            throw new CancionesRunTimeException(sqlException.getMessage());
        }

    }

    @Override
    public Optional<CancionRow> update(CancionRow cancionRow) {

        final String SQL = """
                           UPDATE canciones
                           SET titulo = ?, artista = ?, album = ?, duracion_segundos = ?, fecha_estreno = ?
                           WHERE id = ?
                           """;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
                ) {

            preparedStatement.setString(1, cancionRow.getTitulo());
            preparedStatement.setString(2, cancionRow.getArtista());
            preparedStatement.setString(3, cancionRow.getAlbukm());
            preparedStatement.setInt(4, cancionRow.getDuracion());
            preparedStatement.setDate(5, Date.valueOf(cancionRow.getFechaEstreno()));
            preparedStatement.setString(6, cancionRow.getId());
            int i = preparedStatement.executeUpdate();
            if (i==0) return Optional.empty();
            return Optional.of(cancionRow);

        }catch (SQLException sqlException){
            throw new CancionesRunTimeException(sqlException.getMessage());
        }

    }

    @Override
    public int deleteById(String id) {

        final String SQL = """
                           DELETE FROM canciones
                           WHERE id = ?
                           """;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)
                ){
            preparedStatement.setString(1, id);
            return preparedStatement.executeUpdate();

        }catch (SQLException sqlException){
            throw new CancionesRunTimeException(sqlException.getMessage());
        }

    }

    @Override
    public List<CancionRow> insertSeveral(List<CancionRow> cancionRows) {

        final String SQL = """
                            INSERT INTO canciones(id,        titulo, artista, album, duracion_segundos, fecha_estreno)
                           VALUES(NEXTVAL('seq_canciones'), ?,     ?,       ?,          ?,                ?)
                           """;
        final String[] FIELDS = {"id"};
        List<CancionRow> canciones = new ArrayList<>();

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL, FIELDS);
                ){
            for (CancionRow cancionRow : cancionRows) {
                preparedStatement.clearParameters();
                preparedStatement.setString(1, cancionRow.getTitulo());
                preparedStatement.setString(2, cancionRow.getArtista());
                preparedStatement.setString(3, cancionRow.getAlbukm());
                preparedStatement.setInt(4, cancionRow.getDuracion());
                preparedStatement.setDate(5, Date.valueOf(cancionRow.getFechaEstreno()));
                preparedStatement.executeUpdate();

                try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                    resultSet.next();
                    canciones.add(cancionRow.withId(resultSet.getString(1)));
                }

            }
            return canciones;
        }catch (SQLException sqlException){
            throw new CancionesRunTimeException(sqlException.getMessage());
        }

    }
}
