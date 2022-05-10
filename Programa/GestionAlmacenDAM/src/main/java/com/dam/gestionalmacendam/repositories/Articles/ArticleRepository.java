package com.dam.gestionalmacendam.repositories.Articles;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Article;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ArticleRepository implements ArticleInterface{
    private static ArticleRepository instance;
    private final DataBaseManager dataBaseManager;

    private ArticleRepository(DataBaseManager databaseManager) {
        this.dataBaseManager = databaseManager;
    }


    public static ArticleRepository getInstance(DataBaseManager dataBaseManager){
        if(instance ==null){
            instance = new ArticleRepository(dataBaseManager);
        }
        return instance;
    }

    @Override
    public Optional<Article> searchByName(Object name) throws SQLException {
        dataBaseManager.open();
        String query = "select * from Article where article = ?";
        ResultSet result = dataBaseManager.select(query, name).orElseThrow(SQLException::new);
        Optional<Article> article;
        if (result.next()){
            StringProperty PIC = new SimpleStringProperty(result.getString("PIC"));
            StringProperty articleName =  new SimpleStringProperty(result.getString("article"));
            StringProperty description = new SimpleStringProperty(result.getString("description"));
            StringProperty location = new SimpleStringProperty(result.getString("location"));
            IntegerProperty stock = new SimpleIntegerProperty(result.getInt("stock"));
            DoubleProperty price = new SimpleDoubleProperty(result.getDouble("price"));;
            BooleanProperty isActive = new SimpleBooleanProperty(result.getBoolean("isActive"));

            article = Optional.of(new Article(PIC, articleName, description, location,  price, stock, isActive));

        }else{
            article = null;
        }
        dataBaseManager.close();
        return article;
    }

    @Override
    public Optional shearchByUuid(Object identifier) throws SQLException {
        dataBaseManager.open();
        String query = "select * from Article where PIC = ?";
        ResultSet result = dataBaseManager.select(query, identifier).orElseThrow(SQLException::new);
        Optional<Article> article = null;
        if (result.next()){
            StringProperty PIC = new SimpleStringProperty(result.getString("PIC"));
            StringProperty articleName =  new SimpleStringProperty(result.getString("article"));
            StringProperty description = new SimpleStringProperty(result.getString("description"));
            StringProperty location = new SimpleStringProperty(result.getString("location"));
            IntegerProperty stock = new SimpleIntegerProperty(result.getInt("stock"));
            DoubleProperty price = new SimpleDoubleProperty(result.getDouble("price"));;
            BooleanProperty isActive = new SimpleBooleanProperty(result.getBoolean("isActive"));

            article = Optional.of(new Article(PIC, articleName, description, location,  price, stock, isActive));

        }
        dataBaseManager.close();
        return article;
    }

    @Override
    public ObservableList<Article> findAll() throws SQLException {
        dataBaseManager.open();
        ObservableList articles = FXCollections.observableArrayList();
        String querry = "Select * from Article";
        ResultSet result = dataBaseManager.select(querry).orElseThrow(SQLException::new);
        while(result.next()){
            StringProperty PIC = new SimpleStringProperty(result.getString("PIC"));
            StringProperty articleName =  new SimpleStringProperty(result.getString("article"));
            StringProperty description = new SimpleStringProperty(result.getString("description"));
            StringProperty location = new SimpleStringProperty(result.getString("location"));
            IntegerProperty stock = new SimpleIntegerProperty(result.getInt("stock"));
            DoubleProperty price = new SimpleDoubleProperty(result.getDouble("price"));;
            BooleanProperty isActive = new SimpleBooleanProperty(result.getBoolean("isActive"));

            Optional<Article> article = Optional.of(new Article(PIC, articleName, description, location,  price, stock, isActive));

            articles.add(article);
        }
        dataBaseManager.close();
        return articles;
    }

    @Override
    public Optional save(Object entity) throws SQLException {
        dataBaseManager.open();
        Article a = ((Article)entity) ;
        String query = " insert into Article (PIC, article, description, location, stock, price, isActive) " +
                "values(?, ?, ?, ?, ?, ?, ?) ;";
        ResultSet result = dataBaseManager.insert(query, a.getPIC(), a.getArticle(), a.getDescription(),
                a.getLocation(), a.getStock(), a.getPrice(), a.getIsActive()).orElseThrow(SQLException::new);
        dataBaseManager.close();

        return Optional.of(result);
    }

    @Override
    public Optional update(Object o, Object entity) throws SQLException {
        //todo modificar todo
        dataBaseManager.open();
        Article a = ((Article)entity) ;
        String query = " update Article set article = ?, description= ?, location=?, stock=?, price=?," +
                " isActive = ? where PIC = ? ;";
        int result = dataBaseManager.update(query, a.getArticle(), a.getDescription(),
                a.getLocation(), a.getStock(), a.getPrice(), a.getIsActive(), a.getPIC());
        dataBaseManager.close();

        return Optional.of(result);
    }


}
