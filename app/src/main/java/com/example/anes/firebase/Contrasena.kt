package com.example.anes.firebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_contrasena.*

class Contrasena : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contrasena)

        val auth= FirebaseAuth.getInstance()


        btn_C.setOnClickListener {
            if(et_correoC.text.isEmpty()){
                Toast.makeText(this,"Por favor llena todos los campos",Toast.LENGTH_SHORT).show()
            }else{
                auth.sendPasswordResetEmail(et_correoC.text.toString()).addOnCompleteListener(this){
                    task ->
                    if (task.isSuccessful){
                        Toast.makeText(this,"Se ha enviado un enlace a tu correo",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this,"Ingresa un correo valido",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
