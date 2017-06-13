package com.example.fraunhofer.utilities;

import com.example.fraunhofer.data.model.DataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Venkatesh on 6/12/2017.
 */

public class JSONhelper {
    HttpURLConnection connection;
    List<DataModel> data;

    public List<DataModel> getdatafromurl(String url, String className){
        try{
            URL url1 = new URL(url);

            connection = (HttpURLConnection) url1.openConnection();
            connection.connect();
            InputStream inputStream= connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            StringBuffer buffer = new StringBuffer();
            while ((line=reader.readLine())!=null){

                buffer.append(line);
                String result = buffer.toString();
                JSONArray jsonArray = new JSONArray(result);
                data = new ArrayList<>();

                for (int i =0; i<buffer.length();i++){

                    DataModel dataModel = new DataModel();
                    JSONObject jsonObject =jsonArray.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    String id= jsonObject.getString("id");
                    String kategory= jsonObject.getString("kategory");
                    String image= jsonObject.getString("image");

                    dataModel.setId(id);
                    dataModel.setName(name);
                    dataModel.setKategory(kategory);
                    dataModel.setImage(image);

                    if(className.equals("ChildActivity")) {
                        String info= jsonObject.getString("info");
                        String other= jsonObject.getString("other");
                        String references= jsonObject.getString("references");

                        dataModel.setInfo(info);
                        dataModel.setOther(other);
                        dataModel.setReferences(references);

                    }
                    data.add(dataModel);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
}
