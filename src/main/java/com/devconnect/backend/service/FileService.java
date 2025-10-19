package com.devconnect.backend.service;

public class FileService {
//    public static void saveUsersToFile(String filename){
//        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
//            for(User u : InMemoryDataBase.getUsers()){
//                writer.write(u.getId() + "," + u.getName() + "," + u.getEmail());
//                writer.newLine();
//            }
//            System.out.println("Users saved to " + filename);
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//
//    public static void loadUsersFromFile(String filename){
//        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
//            String line;
//            while((line = reader.readLine()) != null){
//                String[] data = line.split(",");
//                InMemoryDataBase.addUser(new User(Integer.parseInt(data[0]), data[1], data[2]));
//            }
//            System.out.println("Users loaded from " + filename);
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//    }
}
