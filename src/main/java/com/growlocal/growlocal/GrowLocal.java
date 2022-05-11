/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.growlocal.growlocal;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author CRISTIAN CARDENAS
 */
public class GrowLocal {

    public static Firestore db;
    public static String email = "";
    public static String account_type = "";
    public static String name = "";
    public static String lastn = "";
    public static String city = "";
    public static boolean sessionActive = false;

    public GrowLocal() {

    }

    public static void main(String[] args) {
        try {
            conectToFb();
        } catch (Exception e) {

        }

        Index index = new Index();
        index.setVisible(true);
        //index.setExtendedState(Index.MAXIMIZED_BOTH);
        index.setExtendedState(Index.MAXIMIZED_VERT);

        Menu menu = new Menu();
        menu.setVisible(true);
    }

    public static void conectToFb() throws IOException {

        FileInputStream serviceAccount
                = new FileInputStream("growlocal-349202.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
        db = FirestoreClient.getFirestore();
        System.out.println("Conexion exitosa");
    }

    public static boolean addToFb(String email, String password) {

        boolean added = false;

        CollectionReference user = db.collection("usuarios");
        Query query = user.whereEqualTo("email", email);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        try {

            if (querySnapshot.get().size() > 0) {
                JOptionPane.showMessageDialog(Index.getWindows()[0], "Una persona ya está registrada con este mismo correo");
            } else {
                // Create a Map to store the data we want to set
                Map<String, Object> docData = new HashMap<>();
                docData.put("email", email);
                docData.put("password", password);
                // Add a new document (asynchronously) in collection "cities" with id "LA"
                ApiFuture<WriteResult> future = db.collection("usuarios").document(UUID.randomUUID().toString()).set(docData);
                try {
                    System.out.println("EXITO //" + "Update time : " + future.get().getUpdateTime());
                    JOptionPane.showMessageDialog(Index.getWindows()[0], "Registro exitoso");
                    added = true;
                } catch (InterruptedException | ExecutionException ex) {
                    ex.printStackTrace();
                }
            }

        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }

        return added;
    }

    public static boolean addToFb(String email, String password, String name, String lastn) {

        boolean added = false;

        CollectionReference user = db.collection("usuarios");
        Query query = user.whereEqualTo("email", email);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        try {

            if (querySnapshot.get().size() > 0) {
                JOptionPane.showMessageDialog(Index.getWindows()[0], "Una persona ya está registrada con este mismo correo");
            } else {
                // Create a Map to store the data we want to set
                Map<String, Object> docData = new HashMap<>();
                docData.put("email", email);
                docData.put("password", password);
                docData.put("name", name);
                docData.put("lastn", lastn);
                docData.put("account_type", "cliente");
                docData.put("ciudad", "medellin");
                // Add a new document (asynchronously) in collection "cities" with id "LA"
                ApiFuture<WriteResult> future = db.collection("usuarios").document(UUID.randomUUID().toString()).set(docData);
                try {
                    System.out.println("EXITO //" + "Update time : " + future.get().getUpdateTime());
                    JOptionPane.showMessageDialog(Index.getWindows()[0], "Registro exitoso");
                    added = true;
                } catch (InterruptedException | ExecutionException ex) {
                    ex.printStackTrace();
                }
            }

        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }

        return added;
    }

    public static boolean searchInFb(String txt_email, String txt_password) {
        boolean inicioCorrecto = false;

        // Create a reference to the cities collection
        CollectionReference user = db.collection("usuarios");
// Create a query against the collection.
        Query query = user.whereEqualTo("email", txt_email);
// retrieve  query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        try {

            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                document.getString("password");
                if (txt_password.equals(document.getString("password"))) {
                    JOptionPane.showMessageDialog(Index.getWindows()[0], "Inicio de sesion correcto");

                    GrowLocal.email = document.getString("email");
                    GrowLocal.name = document.getString("name");
                    GrowLocal.lastn = document.getString("lastn");
                    GrowLocal.city = document.getString("ciudad");
                    GrowLocal.account_type = document.getString("account_type");

                    GrowLocal.sessionActive = true;

                    if (GrowLocal.sessionActive == true) {
                        Index.welcome_txt.setText("Bienvenid@ " + GrowLocal.account_type + " " + GrowLocal.name);
                    } else {
                        Index.welcome_txt.setText("");
                    }

                    inicioCorrecto = true;
                } else {
                    JOptionPane.showMessageDialog(Index.getWindows()[0], "Asegurate de que el email o la contraseña sean correctos");
                }
            }

            if (querySnapshot.get().size() == 0) {
                JOptionPane.showMessageDialog(Index.getWindows()[0], "Asegurate de que el email o la contraseña sean correctos");
            }

        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }

        return inicioCorrecto;

    }

}
