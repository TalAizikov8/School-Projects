import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.stage.Stage;
import store.ElectronicStore;

import javafx.event.*;
import javafx.scene.input.*;
import store.Product;

import javax.swing.text.html.ListView;

public class ElectronicStoreApp extends Application {

    ElectronicStore store;
    int[] productsThatCanBeUsed;
    Product[] theStoreList;
    Product[] otherStoreList;


    int[] products;

    int shoppingCartSize = 0;
    Product[] shoppingCart = new Product[shoppingCartSize];
    Product[] tempShoppingCart = new Product[shoppingCartSize];

    ElectronicStoreView view;
    public void start(Stage primaryStage) {
        store = ElectronicStore.createStore();
        theStoreList = store.rtoString();
        otherStoreList = new Product[theStoreList.length];
        productsThatCanBeUsed = new int[theStoreList.length];
        products=new int[theStoreList.length];

        for (int i = 0; i < theStoreList.length; i++) {
            productsThatCanBeUsed[i] = theStoreList[i].getStockQuantity();
            otherStoreList[i] = theStoreList[i];
        }
        view = new ElectronicStoreView(store);

        primaryStage.setTitle(store.getName());
        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(view, 800,400)); // Set size of window
        primaryStage.show();

        //all handles are below
        view.electronicStoreList.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e){enableButton(); }
        });

        view.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e){clearSelection(); }
        });

        view.popularItems.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e){clearSelection(); }
        });

        view.currentCart.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e){clearSelection(); }
        });

        view.addToCart.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent e){addToTheCart(); clearSelection();}
        });

        view.shoppingCard.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e){enableShoppingButton(); }
        });

        view.remove.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e){removeItem(); }
        });

        view.completeSale.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e){completeSale(); }
        });

        view.resetStore.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e){resetStore(); }
        });

    }
    public void clearSelection() {
        view.shoppingCard.getSelectionModel().clearSelection();
        view.electronicStoreList.getSelectionModel().clearSelection();

        view.addToCart.setDisable(true);
        view.remove.setDisable(true);
        if (shoppingCartSize == 0) {
            view.completeSale.setDisable(true);
        }
    }

    public void enableButton() {
        view.addToCart.setDisable(false);
        view.remove.setDisable(true);
    }
    public void enableShoppingButton() {
        if (shoppingCartSize > 0) {
            view.remove.setDisable(false);
        }
        view.addToCart.setDisable(true);
    }

    public void addToTheCart() {
        if (view.electronicStoreList.getSelectionModel().getSelectedIndex() != -1) {
            view.completeSale.setDisable(false);
            Product[] tempShoppingCart = shoppingCart;
            shoppingCartSize++;
            shoppingCart = new Product[shoppingCartSize];
            for (int i = 0; i < tempShoppingCart.length; i++) {
                shoppingCart[i] = tempShoppingCart[i];
            }


            int index = view.electronicStoreList.getSelectionModel().getSelectedIndex();
            //theStoreList[index].setStockQuantity(theStoreList[index].getStockQuantity()-1);
            int counter = 0;
            for (int i = 0; i < productsThatCanBeUsed.length; i++){
                if (i <= index && productsThatCanBeUsed[i] == 0) {
                    counter++;
                }
            }
            productsThatCanBeUsed[index+counter]--;


            products[index+counter] += 1;

            shoppingCart[shoppingCart.length - 1] = store.getChosenItem(index+counter);

            view.updateShoppingCart(shoppingCart);

            counter = 0;
            for (int i = 0; i < theStoreList.length; i++) {
                if (theStoreList[i] != null) {
                    if (productsThatCanBeUsed[i] > 0) {
                        counter++;
                    }
                }
            }
            otherStoreList = new Product[counter];


            int nullCounter = 0;
            for (int i = 0; i < theStoreList.length; i++) {
                theStoreList = store.rtoString();
                if (productsThatCanBeUsed[i] > 0) {
                    otherStoreList[i - nullCounter] = theStoreList[i];
                } else {
                    nullCounter++;
                }
            }
            view.updateElectronicList(otherStoreList);
            getCartPrice();
        }
    }

    public void setFunction() {
        int count = 0;

        for (int i = 0; i < productsThatCanBeUsed.length; i++) {
            if (productsThatCanBeUsed[i] != 0){
                count++;
            }
        }
        otherStoreList = new Product[count];
        count = 0;
        for (int i = 0; i < theStoreList.length; i++) {
            if (productsThatCanBeUsed[i] != 0) {
                otherStoreList[i-count] = theStoreList[i];
            }else {
                count++;
            }
        }
        view.updateElectronicList(otherStoreList);
    }

    public void removeItem () {
        if (view.shoppingCard.getSelectionModel().getSelectedIndex() != -1) {
            int index = view.shoppingCard.getSelectionModel().getSelectedIndex();
            Product temp = view.shoppingCard.getSelectionModel().getSelectedItem();
            for (int i = 0; i < theStoreList.length; i++) {
                if (theStoreList[i] == temp) {
                    products[i] -= 1;
                    productsThatCanBeUsed[i]++;
                    setFunction();
                }
            }


            shoppingCartSize = shoppingCart.length - 1;
            tempShoppingCart = new Product[shoppingCartSize];

            for (int i = 0; i < shoppingCart.length; i++) {
                if (i != index) {
                    if (i < index && index >= 0) {
                        tempShoppingCart[i] = shoppingCart[i];
                    } else {
                        tempShoppingCart[i - 1] = shoppingCart[i];
                    }
                }

            }

            shoppingCart = tempShoppingCart;
            view.updateShoppingCart(shoppingCart);
            getCartPrice();
            clearSelection();
        }
    }

    public void completeSale() {
        shoppingCartSize = 0;
        tempShoppingCart = new Product[0];
        shoppingCart = new Product[0];



        view.updateShoppingCart(shoppingCart);


        //store.sellProducts(index, 1);

        for (int i = 0; i < products.length; i++) {
                store.sellProducts(i, products[i]);
                products[i] = 0;
        }

        view.update(store);
        getCartPrice();
        clearSelection();

    }

    public void getCartPrice() {
        double money = 0;
        for (int i = 0; i < theStoreList.length; i++) {
            if (theStoreList[i] != null) {
                money = money + ((theStoreList[i].getStockQuantity() - productsThatCanBeUsed[i]) * theStoreList[i].getPrice());
            }
        }
        view.cart.setText("Current Cart ($" + money + "):");
    }

    public void resetStore() {
        store = ElectronicStore.createStore();
        theStoreList = store.rtoString();
        otherStoreList = new Product[theStoreList.length];
        productsThatCanBeUsed = new int[theStoreList.length];
        products = new int[theStoreList.length];


        for (int i = 0; i < theStoreList.length; i++) {
            productsThatCanBeUsed[i] = theStoreList[i].getStockQuantity();
            otherStoreList[i] = theStoreList[i];
        }
        view.updateElectronicList(otherStoreList);
        view.updateShoppingCart(new Product[0]);
        view.cart.setText("Current Cart ($0.0):");


        shoppingCartSize = 0;
        shoppingCart = new Product[shoppingCartSize];
        tempShoppingCart = new Product[shoppingCartSize];

        clearSelection();
        view.update(store);
        //for (int i = 0; i < otherStoreList.length; i++) {
        //    System.out.println(otherStoreList[i]);
        //}


    }



    public static void main(String[] args) {
        launch(args);
    }



}
