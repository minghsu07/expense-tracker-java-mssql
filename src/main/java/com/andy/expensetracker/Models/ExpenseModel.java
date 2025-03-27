package com.andy.expensetracker.Models;

import javafx.beans.property.*;
import javafx.scene.control.Button;

import java.time.LocalDate;

public class ExpenseModel {
//    private int ID;
//    private String Item;
//    private String Category;
//    private Double Amount;
//    private LocalDate Date;
//    private String Description;
//    private Button Button;
//
//    public int getID() {
//        return ID;
//    }
//
//    public void setID(int ID) {
//        this.ID = ID;
//    }
//
    public ExpenseModel(int id, String item, String category, Double amount, LocalDate date,String description){
        setID(id);
        setItem(item);
        setCategory(category);
        setAmount(amount);
        setDate(date);

        if(description!=null){
            setDescription(description);
            Button=new Button("Detail");
        }
    }
//
//    public String getItem() {
//        return Item;
//    }
//
//    public void setItem(String item) {
//        Item = item;
//    }
//
//    public String getCategory() {
//        return Category;
//    }
//
//    public void setCategory(String category) {
//        Category = category;
//    }
//
//    public Double getAmount() {
//        return Amount;
//    }
//
//    public void setAmount(Double amount) {
//        Amount = amount;
//    }
//
//    public LocalDate getDate() {
//        return Date;
//    }
//
//    public String getDescription() {
//        return Description;
//    }
//
//    public void setDescription(String description) {
//        Description = description;
//    }
//    public void setDate(LocalDate date) {
//        Date = date;
//    }
//    public Button getButton() {
//        return Button;
//    }
//
//    public void setButton(Button button) {
//        Button = button;
//    }

    private IntegerProperty ID = new SimpleIntegerProperty();
    private StringProperty Item = new SimpleStringProperty();
    private StringProperty Category= new SimpleStringProperty();
    private DoubleProperty Amount = new SimpleDoubleProperty();

    private StringProperty Description =  new SimpleStringProperty();
    private Button Button;
    private ObjectProperty<LocalDate>  Date=new SimpleObjectProperty<>();

    public int getID(){
        return ID.get();
    }
    public IntegerProperty idProperty(){
        return ID;
    }
    public void setID(int id){
        this.ID.set(id);
    }


    public String getItem() {
        return Item.get();
    }
    public StringProperty ItemProperty(){
        return Item;
    }

    public void setItem(String item) {
        this.Item.set(item);
    }

    public String getCategory() {
        return Category.get();
    }

    public StringProperty CategoryProperty(){
        return Category;
    }
    public void setCategory(String category) {
        this.Category.set(category);
    }

    public Double getAmount() {
        return Amount.get();
    }

    public DoubleProperty AmountProperty(){
        return Amount;
    }

    public void setAmount(Double amount) {
        this.Amount.set(amount);
    }

    public String getDescription() {
        return Description.get();
    }
    public StringProperty DescriptionProperty(){
        return Description;
    }

    public void setDescription(String description) {
        this.Description.set(description);
    }

    public LocalDate getDate() {
        return Date.get();
    }

    public ObjectProperty<LocalDate> dateProperty(){
        return Date;
    }
    public void setDate(LocalDate date){
        this.Date.set(date);
    }
}
