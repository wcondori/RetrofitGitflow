package pe.edu.cibertec.retrofitgitflow.paid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.edu.cibertec.retrofitgitflow.JsonPlaceHolderApi;
import pe.edu.cibertec.retrofitgitflow.Post;
import pe.edu.cibertec.retrofitgitflow.PostAdapter;
import pe.edu.cibertec.retrofitgitflow.R;
import pe.edu.cibertec.retrofitgitflow.TriggerClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostDetailActivity extends AppCompatActivity {
    private TextView textViewResult;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        textViewResult = findViewById(R.id.textviewresul);
        textView = findViewById(R.id.textView);
        int valor = getIntent().getExtras().getInt("post_id");
        textView.setText("INFORMACION DETALLE ["+valor+"]");
        callService(valor);
    }
    private void callService(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<Post> call = jsonPlaceHolderApi.getPosts(id);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    //textViewResult.setText("");
                }
                else {
                    Post posts = response.body();
                    textViewResult.setText(posts.toString());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });


    }
}
