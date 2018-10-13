/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.project;

/**
 *
 * @author tejas
 */
import com.teamdev.jxmaps.ControlPosition;
import com.teamdev.jxmaps.GeocoderCallback;
import com.teamdev.jxmaps.GeocoderRequest;
import com.teamdev.jxmaps.GeocoderResult;
import com.teamdev.jxmaps.GeocoderStatus;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapMouseEvent;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.MapViewOptions;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.MouseEvent;
import com.teamdev.jxmaps.swing.MapView;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

public class MainMapCode extends MapView 
{
    MapComplaint mp;
    double lati,longi;
    static double value1,value2;
    
    Connection myConn=null;
        Statement myStmt=null;
        ResultSet rs;
        String user="root";
        String pass="1234";
        String comment;
    
    public void get_lat(double k)
    {   
        value1 = k;
    }
    
    public double get_final_lat()
    {
        return value1;
    }

    public void get_lng(double i)
    {   
        value2 = i;
    }
    
    public double get_final_lng()
    {
        return value2;
    }    
    
    
    
    
    
    
    
    
    public MainMapCode(String ccd) 
    {
        
        setOnMapReadyHandler(new MapReadyHandler() 
        {
            @Override
            public void onMapReady(MapStatus status)
            {
                if (status == MapStatus.MAP_STATUS_OK) 
                {
                    final Map map = getMap();
                    MapOptions options = new MapOptions();
                    MapTypeControlOptions controlOptions = new MapTypeControlOptions();
                    controlOptions.setPosition(ControlPosition.TOP_RIGHT);
                    options.setMapTypeControlOptions(controlOptions);
                    map.setOptions(options);
                    map.setCenter(new LatLng(18.8189,77.8541));
                    map.setZoom(9.0);
                    
                    
                    
                    
                    try {
                        
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        myConn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/river_mini?autoReconnect=true&useSSL=false",user,pass);
                        myStmt = myConn.createStatement();
                        
                        rs = myStmt.executeQuery("select latitude,longitude from complaint_form");
                        
                        
                        while(rs.next()){
                            final Marker marker1 = new Marker(map);
                            marker1.setPosition(new LatLng(Double.parseDouble(rs.getString("latitude")),Double.parseDouble(rs.getString("longitude"))));
                            
                        }
                        
                        
                        
                        /*final Marker marker2 = new Marker(map);
                        marker2.setPosition(new LatLng(18.6989,74.4541));
                        
                        final Marker marker3 = new Marker(map);
                        marker3.setPosition(new LatLng(18.6889,73.6841));
                        
                        final Marker marker4 = new Marker(map);
                        marker4.setPosition(new LatLng(18.5669,73.7241));
                        
                        final Marker marker5 = new Marker(map);
                        marker5.setPosition(new LatLng(18.5093,73.9941));
                        
                        final Marker marker6 = new Marker(map);
                        marker6.setPosition(new LatLng(18.3989,73.7041));
                        
                        final Marker marker7 = new Marker(map);
                        marker7.setPosition(new LatLng(18.7889,73.6441));
                        
                        final Marker marker8 = new Marker(map);
                        marker8.setPosition(new LatLng(18.5989,74.1541));*/
                    } catch (SQLException ex) {
                        Logger.getLogger(MainMapCode.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(MainMapCode.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                    
                    
                    
                    
                    GeocoderRequest request = new GeocoderRequest(map);
                    request.setAddress(ccd);

                    getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
                        @Override
                        public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
                            if (status == GeocoderStatus.OK) {
                                map.setCenter(result[0].getGeometry().getLocation());
                                Marker marker = new Marker(map);
                                marker.setPosition(result[0].getGeometry().getLocation());
                                
                                final InfoWindow window = new InfoWindow(map);
                                window.setContent(ccd);
                                window.open(map, marker);
                            }
                        }
                    });
                }
            }
        });
    }
    public void load_map(String k)
    {
        final MainMapCode view = new MainMapCode(k);
         
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "MAP"));
        this.setSize(700, 500);
        this.setLayout(new BorderLayout());
        this.add(view);
       
    }
    public static void main(String[] args) {

    }
}