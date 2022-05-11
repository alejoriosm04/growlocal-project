/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.growlocal.growlocal;

import com.google.api.core.ApiFuture;
import static com.google.cloud.Identity.user;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import static com.growlocal.growlocal.GrowLocal.db;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.cache.FileBasedLocalCache;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;

/**
 *
 * @author ASUS
 */
public class Map extends javax.swing.JPanel {

    /**
     * Creates new form Map
     */
    public Map() {
        initComponents();
        crearMapa();

    }

    public void crearMapa() {
        // Create a TileFactoryInfo for Virtual Earth
        TileFactoryInfo info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);

        // Setup local file cache
        File cacheDir = new File(System.getProperty("user.home") + File.separator + ".jxmapviewer2");
        tileFactory.setLocalCache(new FileBasedLocalCache(cacheDir, false));

        // Setup JXMapViewer
        JXMapViewer mapViewer = new JXMapViewer();
        mapViewer.setTileFactory(tileFactory);

        //GeoPosition medellin = new GeoPosition(6.5, -71);
        //GeoPosition wiesbaden = new GeoPosition(50, 5, 0, 8, 14, 0);
        //GeoPosition mainz = new GeoPosition(50, 0, 0, 8, 16, 0);
        //GeoPosition darmstadt = new GeoPosition(49, 52, 0, 8, 39, 0);
        //GeoPosition offenbach = new GeoPosition(50, 6, 0, 8, 46, 0);

        // Set the focus
        mapViewer.setZoom(8);
        mapViewer.setAddressLocation(new GeoPosition(6.2443382, -75.573553));

        // Add interactions
        MouseInputListener mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
        mapViewer.addMouseListener(new CenterMapListener(mapViewer));
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));
        mapViewer.addKeyListener(new PanKeyListener(mapViewer));

        
        
        
        // Create waypoints from the geo-positions
        Set<MyWaypoint> waypoints = new HashSet<MyWaypoint>();
        
        // Create a reference to the cities collection
        CollectionReference local = GrowLocal.db.collection("locales");
        // Create a query against the collection.
        Query query = local.whereEqualTo("city", GrowLocal.city);
        // retrieve  query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        Color color = Color.white;

        try {

            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {

                switch(document.getString("category")){
                    
                    case "tienda":
                        color = Color.PINK;
                    break;
                    case "salud":
                        color = Color.RED;
                    break;
                    case "estudio":
                        color = Color.CYAN;
                    break;
                    case "mercado":
                        color = Color.YELLOW;
                    break;
                    default:
                        color = Color.WHITE;
                    break;
                    
                }
                waypoints.add(new MyWaypoint(document.getString("local_name"), color, new GeoPosition(document.getGeoPoint("geopoint").getLatitude(), document.getGeoPoint("geopoint").getLongitude())));

            }

        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }   
        
                
        

        // Create a waypoint painter that takes all the waypoints
        WaypointPainter<MyWaypoint> waypointPainter = new WaypointPainter<MyWaypoint>();
        waypointPainter.setWaypoints(waypoints);
        waypointPainter.setRenderer(new FancyWaypointRenderer());

        mapViewer.setOverlayPainter(waypointPainter);

        // Display the viewer in a JFrame
        this.removeAll();

        Menu menu = new Menu();

        mapViewer.setSize(menu.getPreferredSize());
        System.out.println(mapViewer.getWidth() + " " + mapViewer.getHeight());
        mapViewer.setLocation(0, 0);

        Clicklistener click= new Clicklistener();
        JButton jbutton = new JButton();
        jbutton.setLocation(0, 0);
        jbutton.setText("Cerrar");
        jbutton.setBackground(new Color(35, 41, 49));
        jbutton.setForeground(new Color(78, 204, 163));
        jbutton.setFont(new Font("Roboto", Font.BOLD, 14));
        jbutton.setFocusable(false);
        jbutton.addActionListener(click);
        this.add(mapViewer);
        //mapViewer.add(jbutton);
        this.revalidate();
        this.repaint();
    }

    private class Clicklistener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            closeMap();
        }
    
    }

public void closeMap(){
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(379, 436));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 355, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 517, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
