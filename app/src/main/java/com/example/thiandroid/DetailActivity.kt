package com.example.thiandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        if(intent!=null){
            tvEmail.text = intent.getStringExtra("email")
            tvFullName.text = intent.getStringExtra("name")
            tvID.text = intent.getStringExtra("id")
            Picasso.get().load(intent.getStringExtra("avatar")).into(ivAvatar);
        }
    }
}