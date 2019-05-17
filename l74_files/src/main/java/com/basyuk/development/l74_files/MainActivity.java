package com.basyuk.development.l74_files;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    final String TAG = "myLogs";
    final String FILENAME = "file";
    final String DIR_SD = "MyFiles";
    final String FILENAME_SD = "fileSD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.btnWrite:
                writeFile();
                break;

            case R.id.btnRead:
                readFile();
                break;

            case R.id.btnWriteSD:
                writeFileSD();
                break;

            case R.id.btnReadSD:
                readFileSD();
                break;
        }
    }

    void writeFile(){
        try{
            //открываем поток для записи
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(openFileOutput(FILENAME, MODE_PRIVATE)));
            //пишем данные
            bufferedWriter.write("Содержимое файла");
            //закрываем поток
            bufferedWriter.close();
            Log.d(TAG, "Файл записан");
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    void readFile(){
        try {
            //открываем поток для записи
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput(FILENAME)));
            String str = "";
            //читаем содержимое
            while ((str = bufferedReader.readLine()) != null) {
                Log.d(TAG, str);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    void writeFileSD(){
        //проверяем доступность SD
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            Log.d(TAG, "SD-карта недоступна: " + Environment.getExternalStorageState());
            return;
        }
        //получаем путь к SD
        File sdPath = Environment.getExternalStorageDirectory();
        sdPath = new File(sdPath.getAbsolutePath()+ "/" + DIR_SD);
        //создаем каталог
        sdPath.mkdir();
        //формируем объект File, который содержит путь к файлу
        File sdFile = new File(sdPath, FILENAME_SD);
        try{
            //открываем поток для записи
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(sdFile));
            //пишем данные
            bufferedWriter.write("Содержимое файла на SD");
            //закрываем поток
            bufferedWriter.close();
            Log.d(TAG, "Файл записан на SD" + sdFile.getAbsolutePath());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    void readFileSD(){
        //проверяем доступность SD
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            Log.d(TAG, "SD-карта доступна: " + Environment.getExternalStorageState());
            return;
        }
        //получаем путь к SD
        File sdPath = Environment.getExternalStorageDirectory();
        //добавляем свой каталог к пути
        sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
        //формируем объект File, который содержит путь к файлу
        File sdFile = new File(sdPath, FILENAME_SD);
        try{
            //открываем поток для чтения
            BufferedReader bufferedReader = new BufferedReader(new FileReader(sdFile));
            String str = "";
            //читаем содержимое
            while ((str = bufferedReader.readLine()) != null ){
                Log.d(TAG, str);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
