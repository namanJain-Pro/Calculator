package sample.dataModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Data {

    private ObservableList<Result> data;

    public Data(){
        data = FXCollections.observableArrayList();
    }

    public ObservableList<Result> getData(){
        return data;
    }

    public void loadData(){
        try(Scanner sc = new Scanner(new FileReader("log.txt"))){

            while(sc.hasNextLine()){
                String text = sc.nextLine();
                String arr[] = text.split(",");
                Result result = new Result(arr[0], arr[1]);
                data.add(result);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void addData(String exp, String res){
        try(FileWriter wr = new FileWriter("log.txt", true)){
            wr.append(exp+","+res+"\n");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
