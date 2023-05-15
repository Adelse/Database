package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


class ChessArgumentsProvider {
    static ResultSet resultSet() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/ChessTest";
        String user = "postgres";
        String password = "1";
        Connection connection = DriverManager.getConnection(url, user, password);

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM position");
        return resultSet;
    }

    @ParameterizedTest
    @ArgumentsSource(EmployeesArgumentsProvider.class)
    void testArgumentsSource(int kingX, int kingY, int rookX, int rookY, int bishopX, int bishopY, String result) {
        Assertions.assertEquals(Chess.solve(kingX, kingY, rookX, rookY, bishopX, bishopY), result);
    }

    static class EmployeesArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws SQLException {
            List<Arguments> ss = new ArrayList<>();
            ResultSet resultSet = ChessArgumentsProvider.resultSet();
            while (resultSet.next()){

                ss.add(Arguments.of(
                        resultSet.getInt("kingX"),resultSet.getInt("kingY"),
                        resultSet.getInt("rookX"),resultSet.getInt("rookY"),
                        resultSet.getInt("bishopX"),resultSet.getInt("bishopY"),
                        resultSet.getString("result")));
            }
            return ss.stream();
        }
    }


}
