package Chat.Auth;

import Chat.User;

import java.sql.*;
import java.util.List;


public class BaseAuthService implements AuthService {

    private static Statement statement;

    private static Connection connection;

    private ResultSet DBclient;

    private static final List<User> client = List.of( //Новый лист, отдающий ArrayList и принимает объекты User с его конструктором
//            new User("user1", "1111", "Инокентий_Петров"),
//            new User("user2", "2222", "Владимир_Иванов"),
//            new User("user3", "3333", "Иван_Смирнов")
    );

    @Override
    public void start() {
        System.out.println("Сервис аутентификации запущен");
        wordDB(); // для создания коннекта и statement(объект для написания комманд в б.д.)

    }

    @Override
    public String getUserNameAndPasswForLogin(String login, String password) {
        try {
            while (DBclient.next()) {//пробегаем по каждому результату вывода из БД

                if (DBclient.getString("login").equals(login) && DBclient.getString("password").equals(password))  //Если логин и пароль одного из объектов юзер соответствует внесенному логину и паролю (.equals() -настроен в самом классе User, как и геттеры)
                    return DBclient.getString("username"); //Вернем клиентский юзернейм
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    //    @Override
//    public String getUserNameAndPasswForLogin(String login, String password) {
//        for (User user : client) { //пробегаем по каждому объекту в листе List<User> client
//
//            if(user.getLogin().equals(login) && user.getPassword().equals(password)){ //Если логин и пароль одного из объектов юзер соответствует внесенному логину и паролю (.equals() -настроен в самом классе User, как и геттеры)
//                return user.getUsername(); //Вернем клиентский юзернейм
//            }
//        }
//        return null;
//    }
    @Override
    public void close() {
        System.out.println("Сервис аутентификации завершен");
    }

    private void wordDB() { //объект для написания комманд в б.д.
        try {
            Class.forName("org.sqlite.JDBC"); //загружает класс, включая запуск его статических инициализаторов
        } catch (ClassNotFoundException e) {
            System.out.println("Нет возможности найти класс \"org.sqlite.JDBC\"");
            e.printStackTrace();
        }
        try { //Подключение БД с указанием пути, типа и имени б.д.
            Connection connection = DriverManager.getConnection("jdbc:sqlite:LocalChatSQL.db"); //Создаем связь с б.д. Обрубаем ее в MyServer методе Main
            Statement statement = connection.createStatement(); //объект для написания комманд в б.д.

            BaseAuthService.connection = connection; //Кидаем в статическую классовую переменную
            BaseAuthService.statement = statement; //Кидаем в статическую классовую переменную

            createtable(statement); //Создаем таблицу в БД

            addAllUser(); //Добавляем в таблицу юзеров

            this.DBclient = statement.executeQuery("SELECT * FROM auth;"); //ResultSet DBclient теперь содержит всю таблицу юзеров (с логинами, паролями и именами)

            updateUsersById(1,"login","user1");// обновление информации о юзерах по их id

        } catch (SQLException b) {
            System.out.println("Неверный путь к БД или возврат даннных клиента");
            b.printStackTrace();
        }
    }

    private void createtable(Statement statement) { //Создаем таблицу в БД
        try {
            statement.execute("CREATE TABLE IF NOT EXISTS auth (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "login VARCHAR(10) UNIQUE NOT NULL," +
                    "password VARCHAR(20) NOT NULL," +
                    "username VARCHAR(20));");
        } catch (SQLException throwables) {
            System.out.println("Не добавляется таблица");
            throwables.printStackTrace();
        }

    }

    private void addAllUser() { //Добавляем в таблицу юзеров
        new User("user1", "1111", "Инокентий_Петров");
        new User("user2", "2222", "Владимир_Иванов");
        new User("user3", "3333", "Иван_Смирнов");
    }



    private void updateUsersById(int id, String column, String newText) { // обновление информации о юзерах по их id
        try {
            statement.execute("DROP TABLE auth;");
            createtable(statement);
            addAllUser();
            statement.execute("UPDATE auth SET " + column + " = \'" + newText + "\' WHERE id = " + id);
        } catch (SQLException throwables) {
            System.out.println("Ошибка в обновлении строки");
            throwables.printStackTrace();
        }
    }


    public static Connection getConnection() {
        return connection;
    }

    public static Statement getStatement() {
        return statement;
    }
}



