import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import store.*;

import java.awt.event.MouseEvent;
import java.util.Arrays;


public class ElectronicStoreView extends Pane {
    ListView<Product> electronicStoreList;
    ListView<Product> shoppingCard;
    ListView<Product> popularItems;

    Label numSales = new Label();

    Label itemRevenue = new Label();

    Label itemCost = new Label();

    Button completeSale = new Button("Complete Sale!");
    Button remove = new Button("Remove From Cart");
    Button addToCart = new Button("Add to Cart");
    Button resetStore = new Button("Reset Store");

    Label cart = new Label("Current Cart (%0.0):");
    TextField currentCart = new TextField();

    public ElectronicStoreView (ElectronicStore store){
/*
        Pane mainPane = new Pane();
        Scene scene = new Scene(mainPane, 800, 400);
        primaryStage.setTitle("Tempname"); // Set window title
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(true);

 */

        // Create the labels and textfields


        numSales.relocate(65, 35);
        numSales.setPrefSize(80, 10);
        itemRevenue.relocate(65, 60);
        itemRevenue.setPrefSize(80, 30);
        itemCost.relocate(65, 90);
        itemCost.setPrefSize(80, 30);

        Label label1 = new Label("Store Summary:");
        label1.relocate(20, 0);
        label1.setPrefSize(110, 30);
        Label label2 = new Label("# Sales:");
        label2.relocate(10, 30);
        label2.setPrefSize(80, 30);
        Label label3 = new Label("Revenue");
        label3.relocate(10, 60);
        label3.setPrefSize(80, 30);
        Label label4 = new Label("$ / Sales:");
        label4.relocate(10, 90);
        label4.setPrefSize(80, 30);
        Label label5 = new Label("Most Popular Items: ");
        label5.relocate(10, 120);
        label5.setPrefSize(110, 30);


        TextField salesNum = new TextField();
        salesNum.relocate(60, 30);
        salesNum.setPrefSize(80, 10);
        TextField revenue = new TextField();
        revenue.relocate(60, 60);
        revenue.setPrefSize(80, 10);
        TextField moneyPairSale = new TextField();
        moneyPairSale.relocate(60, 90);
        moneyPairSale.setPrefSize(80, 10);




        resetStore.relocate(20,325);
        resetStore.setPrefSize(130, 40);


        Label stock = new Label("Store Stock:");
        stock.relocate(290, 0);
        stock.setPrefSize(110, 30);

        TextField storeStock = new TextField();
        storeStock.relocate(170, 30);
        storeStock.setPrefSize(310, 290);

        addToCart.relocate(260,325);
        addToCart.setPrefSize(130, 40);



        cart.relocate(600, 0);
        cart.setPrefSize(200, 30);


        currentCart.relocate(490, 30);
        currentCart.setPrefSize(300, 290);


        remove.relocate(490,325);
        remove.setPrefSize(150, 40);


        completeSale.relocate(640,325);
        completeSale.setPrefSize(150, 40);


        salesNum.setEditable(false);
        revenue.setEditable(false);
        moneyPairSale.setEditable(false);


        storeStock.setEditable(false);
        currentCart.setEditable(false);

        remove.setDisable(true);
        completeSale.setDisable(true);
        addToCart.setDisable(true);

        // Add all labels and textfields to the pane
        getChildren().addAll(label1, label2, label3, label4,label5,
                salesNum, revenue,moneyPairSale,
                resetStore,
                stock, storeStock, addToCart,
                cart, currentCart, remove, completeSale);


        electronicStoreList = new ListView<Product>();
        electronicStoreList.relocate(170, 30);
        electronicStoreList.setPrefSize(310, 290);


        electronicStoreList.setItems(FXCollections.observableArrayList(store.rtoString()));












        shoppingCard = new ListView<Product>();
        shoppingCard.relocate(490, 30);
        shoppingCard.setPrefSize(300, 290);

        popularItems = new ListView<Product>();
        popularItems.relocate(10, 150);
        popularItems.setPrefSize(150, 170);

        getChildren().addAll(electronicStoreList, numSales, itemRevenue,itemCost,popularItems);


        update(store);
    }

    public void updateShoppingCart(Product[] shoppingCart) {
        getChildren().removeAll(shoppingCard);
        getChildren().add(shoppingCard);
        shoppingCard.setItems(FXCollections.observableArrayList(shoppingCart));
    }

    public void updateElectronicList(Product[] store) {
        electronicStoreList.setItems(FXCollections.observableArrayList(store));
    }

    public void updateMostPopularItems(ElectronicStore store) {
        popularItems.setItems(FXCollections.observableArrayList(store.mostPopularItems()));
    }





    Product selectedItem;



    public void update(ElectronicStore store) {
        numSales.setText(String.valueOf(store.getNumSales()));
        itemRevenue.setText(String.valueOf(store.getRevenue()));
        itemCost.setText(String.valueOf((store.getRevenue()/store.getNumSales())));

        popularItems.setItems(FXCollections.observableArrayList(store.mostPopularItems()));


        this.electronicStoreList.getSelectionModel().clearSelection();
        if (electronicStoreList.getSelectionModel().getSelectedIndex() < 0){
            addToCart.setDisable(true);
        }

    }


}
