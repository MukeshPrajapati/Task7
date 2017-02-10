package mukesh.com.task7;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ArrayList<UserInfo> arrayList = new ArrayList<UserInfo>();

        HttpURLConnection connection;
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            try {
                connection = (HttpURLConnection)url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((reader.readLine()!= null)){
                    line = reader.readLine();
                    buffer.append(line);
                }

                String bufferString = buffer.toString();

                try {
                    JSONArray jsonArray = new JSONArray(bufferString);
                    for (int i = 0; i <jsonArray.length() ; i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        UserInfo info = new UserInfo();
                        info.setUserID(jsonObject.getInt("userId"));
                        info.setId(jsonObject.getInt("id"));
                        info.setTitle(jsonObject.getString("title"));
                        info.setBody(jsonObject.getString("body"));

                        arrayList.add(info);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        ListView listView = (ListView)findViewById(R.id.listviewid);
        UserAdapter userAdapter = new UserAdapter(this,R.layout.row, arrayList);
        listView.setAdapter(userAdapter);
    }
}
