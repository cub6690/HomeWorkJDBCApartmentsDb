package com.gmail.sergick6690;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class ApartmentsDb {
    public static void main(String[] args) throws SQLException {
       Scanner sc = new Scanner(System.in);
        boolean end=false;
        completeTable();
        for(;end==false;){
            System.out.println("To search for an address press 1");
            System.out.println("To search for an area press 2");
            System.out.println("To search for an price press 3");
            System.out.println("To search by number of rooms press 4");
            System.out.println("Press 0 for exit ");
            System.out.print("->");
            int num = sc.nextInt();
            if(num==1){
                findWithAdress();
            }else if (num==2) {
                findWithArea();
            }else if(num==3){
                findWithPrice();
            }else if(num==4){
                findWithrooms();
            }
            else if(num==0){
                end=true;
            }
        }

    }
    public static void completeTable(){
        Random random = new Random();
        DbProperties props = new DbProperties();
        try (Connection con = DriverManager.getConnection(props.getUrl(), props.getUser(), props.getPassword())) {
            try (Statement st = con.createStatement()) {
                st.execute("drop table if exists Apartments");
                st.execute("create table Apartments(id serial primary key, Region varchar (100), Adress text, AreaM2 int, Rums int, PriceUSD int )");
            }
            try (PreparedStatement pst = con.prepareStatement("insert into apartments values (?,?,?,?,?,?)")) {
                for (int i = 1; i <= 50; i++) {
                    pst.setInt(1, i);
                    pst.setString(2, "Region" + random.nextInt(10));
                    pst.setString(3, "Street" + random.nextInt(20));
                    pst.setDouble(4, 10 *random.nextInt(10) );
                    pst.setInt(5, 1*random.nextInt(6));
                    pst.setDouble(6, 100 * random.nextInt(500));
                    pst.executeUpdate();
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void findWithAdress() {
    Scanner sc= new Scanner(System.in);
        System.out.println("Write Adress");
        String adress = sc.nextLine();
        DbProperties props = new DbProperties();
        try (Connection con = DriverManager.getConnection(props.getUrl(), props.getUser(), props.getPassword())) {
            PreparedStatement st = con.prepareStatement("select * from apartments where adress='"+adress+"'");
           try( ResultSet rs= st.executeQuery()){
            ResultSetMetaData md = st.getMetaData();
            for(int i=1;i<=md.getColumnCount();i++){
                System.out.printf("%-11s",md.getColumnName(i),"%11s");

            }
               System.out.println();
            for(;rs.next();){
                for (int i =1; i<=md.getColumnCount();i++){
                    System.out.printf("%-11s",rs.getString(i),"%11s");
                }
                System.out.println();

            }
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void findWithArea() {
        Scanner sc= new Scanner(System.in);
        System.out.println("Write Area");
        double area = sc.nextInt();
        DbProperties props = new DbProperties();
        try (Connection con = DriverManager.getConnection(props.getUrl(), props.getUser(), props.getPassword())) {
            PreparedStatement st = con.prepareStatement("select * from apartments where aream2="+area);
            try( ResultSet rs= st.executeQuery()){
                ResultSetMetaData md = st.getMetaData();
                for(int i=1;i<=md.getColumnCount();i++){
                    System.out.printf("%-11s",md.getColumnName(i),"%11s");

                }
                System.out.println();
                for(;rs.next();){
                    for (int i =1; i<=md.getColumnCount();i++){
                        System.out.printf("%-11s",rs.getString(i),"%11s");
                    }
                    System.out.println();

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void findWithPrice() {
        Scanner sc= new Scanner(System.in);
        System.out.println("Write Price");
        int price = sc.nextInt();
        DbProperties props = new DbProperties();
        try (Connection con = DriverManager.getConnection(props.getUrl(), props.getUser(), props.getPassword())) {
            PreparedStatement st = con.prepareStatement("select * from apartments where priceusd ="+price);
            try( ResultSet rs= st.executeQuery()){
                ResultSetMetaData md = st.getMetaData();
                for(int i=1;i<=md.getColumnCount();i++){
                    System.out.printf("%-11s",md.getColumnName(i),"%11s");

                }
                System.out.println();
                for(;rs.next();){
                    for (int i =1; i<=md.getColumnCount();i++){
                        System.out.printf("%-11s",rs.getString(i),"%11s");
                    }
                    System.out.println();

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void findWithrooms() {
        Scanner sc= new Scanner(System.in);
        System.out.println("Write Area");
        int room = sc.nextInt();
        DbProperties props = new DbProperties();
        try (Connection con = DriverManager.getConnection(props.getUrl(), props.getUser(), props.getPassword())) {
            PreparedStatement st = con.prepareStatement("select * from apartments where rums ="+room);
            try( ResultSet rs= st.executeQuery()){
                ResultSetMetaData md = st.getMetaData();
                for(int i=1;i<=md.getColumnCount();i++){
                    System.out.printf("%-11s",md.getColumnName(i),"%11s");

                }
                System.out.println();
                for(;rs.next();){
                    for (int i =1; i<=md.getColumnCount();i++){
                        System.out.printf("%-11s",rs.getString(i),"%11s");
                    }
                    System.out.println();

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}