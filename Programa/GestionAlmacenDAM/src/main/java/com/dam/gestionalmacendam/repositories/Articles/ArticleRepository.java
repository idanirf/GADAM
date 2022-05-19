package com.dam.gestionalmacendam.repositories.Articles;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Article;
import com.dam.gestionalmacendam.models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ArticleRepository implements ArticleInterface{
    private static ArticleRepository instance;
    private final DataBaseManager dataBaseManager;

    private final ObservableList<Article> repository = FXCollections.observableArrayList();

    private ArticleRepository(DataBaseManager databaseManager) {
        this.dataBaseManager = databaseManager;
    }


    public static ArticleRepository getInstance(DataBaseManager dataBaseManager){
        if(instance ==null){
            instance = new ArticleRepository(dataBaseManager);
        }
        return instance;
    }
    public DataBaseManager getDb(){
        return dataBaseManager;
    }

    @Override
    public Optional<Article> findByName(String name) throws SQLException {
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
    public Article findByUuid(String PIC) throws SQLException {
        var repo= findAll();
        return repo.stream().filter(article-> article.getPIC().equals(PIC)).findFirst().orElseThrow(()-> new SQLException("No existe"));
    }


    @Override
    public ObservableList<Article> findAll() throws SQLException {
        dataBaseManager.open();
        String querry = "Select * from Article";
        ResultSet result = dataBaseManager.select(querry).orElseThrow(()-> new SQLException("Error al obtener los articulos."));
        repository.clear();
        while(result.next()){
            repository.add(
                    new Article(
                            result.getString("PIC"),
                            result.getString("article"),
                            result.getString("description"),
                            result.getString("location"),
                            result.getDouble("price"),
                            result.getInt("stock"),
                            result.getBoolean("isActive")

                    )
            );
        }
        dataBaseManager.close();
        return repository;
    }

    @Override
    public Optional<Article> save(Article article) throws SQLException {
        dataBaseManager.open();
        String query = " insert into Article (PIC, article, description, location, stock, price, isActive) " +
                "values(?, ?, ?, ?, ?, ?, ?) ;";
        dataBaseManager.insert(query, article.getPIC(),
                article.getArticle().get(), article.getDescription().get(),
                article.getLocation().get(), article.getStock().get(), article.getPrice().get(),
                article.getIsActive().get());
        dataBaseManager.close();
        return Optional.of(article);
    }



    @Override
    public Optional<Article> update(String pic, Article article) throws SQLException {
        var a= findByUuid(pic);
        var index = repository.indexOf(a);

        dataBaseManager.open();
        String query = " update Article set article = ?, description= ?, location=?, stock=?, price=?," +
                " isActive = ? where PIC = ? ;";
        dataBaseManager.update(query, article.getArticle().get(), article.getDescription().get(),
                article.getLocation().get(), article.getStock().get(), article.getPrice().get(), article.getIsActive().get()
                , pic);
        dataBaseManager.close();
        repository.set(index,article);
        return Optional.of(article);
    }

}
