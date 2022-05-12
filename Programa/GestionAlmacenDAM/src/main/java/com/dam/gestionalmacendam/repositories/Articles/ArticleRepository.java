package com.dam.gestionalmacendam.repositories.Articles;

import com.dam.gestionalmacendam.managers.DataBaseManager;

import com.dam.gestionalmacendam.models.Article;
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
    public Optional<Article> findByName(String name) throws SQLException {
        //todo no funciona y no se porque
        dataBaseManager.open();
        String query = "select * from Article where article = ? ";
        ResultSet result = dataBaseManager.select(query, name).get();
        Article article = null;
        if (result.next()){
            article = new Article(
                    result.getString("PIC"),
                    result.getString("article"),
                    result.getString("description"),
                    result.getString("location"),
                    result.getDouble("price"),
                    result.getInt("stock"),
                    result.getBoolean("isActive"));

        }
        dataBaseManager.close();
        return Optional.of(article);
    }


    @Override
    public Optional<Article> findByUuid(String PIC) throws SQLException {
        String query = "select * from Article where PIC = ? ";
        dataBaseManager.open();
        ResultSet result = dataBaseManager.select(query, PIC).orElseThrow(SQLException::new);
        Article article = null;
        if (result.next()){
            article = new Article( result.getString("PIC"),
                    result.getString("article"),
                    result.getString("description"),
                    result.getString("location"),
                    result.getDouble("price"),
                    result.getInt("stock"),
                    result.getBoolean("isActive"));

        }
        dataBaseManager.close();
        return Optional.of(article);
    }


    @Override
    public ObservableList<Article> findAll() throws SQLException {
        dataBaseManager.open();
        ObservableList articles = FXCollections.observableArrayList();
        String querry = "Select * from Article";
        ResultSet result = dataBaseManager.select(querry).orElseThrow(SQLException::new);
        while(result.next()){
            String PIC = result.getString("PIC");
            String articleName =  result.getString("article");
            String description = result.getString("description");
            String location = result.getString("location");
            int stock = result.getInt("stock");
            Double price = result.getDouble("price");;
            Boolean isActive = result.getBoolean("isActive");

            Optional<Article> article = Optional.of(new Article(PIC, articleName, description, location,  price, stock, isActive));

            articles.add(article);
        }
        dataBaseManager.close();
        return articles;
    }

    @Override
    public Optional save(Article entity) throws SQLException {
        dataBaseManager.open();
        Article a = ((Article)entity) ;
        String query = " insert into Article (PIC, article, description, location, stock, price, isActive) " +
                "values(?, ?, ?, ?, ?, ?, ?) ;";
        ResultSet result = dataBaseManager.insert(query, a.getPIC().toString(), a.getArticle().toString(), a.getDescription().toString(),
                a.getLocation().toString(), a.getStock().intValue(), a.getPrice().doubleValue(),
                a.getIsActive().toString()).orElseThrow(SQLException::new);
        dataBaseManager.close();
        // todo no se si fallará el string del boolean
        return Optional.of(entity);
    }



    @Override
    public Optional <Article>update( String entity, Article o) throws SQLException {
        //todo modificar todo
        dataBaseManager.open();

        String query = " update Article set article = ?, description= ?, location=?, stock=?, price=?," +
                " isActive = ? where PIC = ? ;";
        int result = dataBaseManager.update(query, o.getArticle().toString(), o.getDescription().toString(),
                o.getLocation().toString(), o.getStock().intValue(), o.getPrice().doubleValue(), o.getIsActive().toString()
                , entity);
        dataBaseManager.close();

        return Optional.of(o);
    }



}
