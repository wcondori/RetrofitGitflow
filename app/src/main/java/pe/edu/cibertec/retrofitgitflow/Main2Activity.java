package pe.edu.cibertec.retrofitgitflow;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main2Activity extends AppCompatActivity {
      static Activity contect = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        contect=this;
        callService();
    }
    private void callService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    //textViewResult.setText("");
                }
                else{
                    List<Post> posts = response.body();
                    ArrayList<Post> arrayOfUsers = (ArrayList<Post>) posts;
                    PostAdapter adapter = new PostAdapter(getApplicationContext(), arrayOfUsers);
                    ListView listView =  findViewById(R.id.list_post);
                    listView.setAdapter(adapter);
                    /*listView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int position = (Integer) v.getTag();
                            Toast.makeText(getApplicationContext(), "Row " + position + " was clicked!", Toast.LENGTH_SHORT).show();
                            //TriggerClick.SelectItem(position,this);
                        }
                    });*/
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            final int pos = position;
                            Toast.makeText(contect, "Row " + position + " was clicked!", Toast.LENGTH_LONG).show();
                            TriggerClick.SelectItem(pos+1,contect);
                        }
                    });
                }


            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });


    }

}
